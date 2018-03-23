import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class ShiftsInputPanel extends JPanel implements PropertyChangeListener {

    private DataSingleton singleton;
    private JLabel shiftsLabel;
    private NumberFormat shiftsFormat;
    private String employeeNames[];
    private JFormattedTextField shiftFields[];
    private int numShifts;
    public static JButton btnNext, btnBack = null;

    public ShiftsInputPanel(DataSingleton singleton) {
        super(new BorderLayout());
        this.singleton = singleton;
        this.numShifts = singleton.getNumShifts();

        shiftFields = new JFormattedTextField[this.numShifts];
        shiftsFormat = NumberFormat.getNumberInstance();

        String shifts = "Shift Times (Hour)";
        shiftsLabel = new JLabel(shifts);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));

        JPanel timePane = new JPanel(new GridLayout(0, 1));

        // Have ":00" after each text field to indicate it's an hour
        JLabel time[] = new JLabel[this.numShifts];
//        JLabel time = new JLabel(":00");

        for (int i = 0; i < this.numShifts; i++) {
            shiftFields[i] = new JFormattedTextField(shiftsFormat);
            time[i] = new JLabel(":00");
            if (i == 0) shiftFields[i].setValue(1);

            shiftFields[i].setColumns(2);
            shiftFields[i].addPropertyChangeListener("value", this);

            time[i].setLabelFor(shiftFields[i]);

            fieldPane.add(shiftFields[i]);
            timePane.add(time[i]);
        }

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(1, 0));

        // Back button
        btnBack = new JButton();
        btnBack.setText("Back");
        buttonPane.add(btnBack);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Main.changeFrame(Main.getFrame(), singleton.getShiftsInputPanel(), singleton.getEmployeeInput());
            }
        });

        // Next button
        btnNext = new JButton();
        btnNext.setText("Next: Input skills");
        buttonPane.add(btnNext);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(shiftsLabel, BorderLayout.NORTH);
        add(fieldPane, BorderLayout.CENTER);
        add(timePane, BorderLayout.LINE_END);
        add(buttonPane, BorderLayout.SOUTH);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
