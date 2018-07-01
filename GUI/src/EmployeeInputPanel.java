import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class EmployeeInputPanel extends JPanel implements PropertyChangeListener {

    private DataSingleton singleton;
    public JLabel employeesLabel;
    public static JButton btnNext, btnBack = null;
    public JTextField employeesFields[];
    public String employeeNames[];
    public int numEmployees;

    public void setData(DataSingleton singleton) {
        // Initialize array of text fields with value passed in
        employeesFields = new JTextField[this.numEmployees];
        employeeNames = new String[this.numEmployees];


        String employees = "Employee Names";
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
        employeesLabel = new JLabel(employees);

        listPane.add(employeesLabel);
        listPane.add(Box.createRigidArea(new Dimension(0, 5)));


        for (int i = 0; i < this.numEmployees; i++) {
            employeesFields[i] = new JTextField();
            if (i == 0) {
                employeesFields[i].setText("Grace Hopper");
            }
            final int j = i;
            employeesFields[i].setColumns(10);
            listPane.add(employeesFields[i]);
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
                Main.changeFrame(Main.getFrame(), singleton.getEmployeeInput(), singleton.getNumberFields());
            }
        });

        // Next button
        btnNext = new JButton();
        btnNext.setText("Next: Input shifts");
        buttonPane.add(btnNext);

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ShiftsInputPanel shiftsInputPanel = new ShiftsInputPanel(DataSingleton.getInstance());
                singleton.setShiftsInputPanel(shiftsInputPanel);
                setEmployeeNames();
                Main.changeFrame(Main.getFrame(), singleton.getEmployeeInput(), singleton.getShiftsInputPanel());
            }
        });

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(listPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
    }


    public EmployeeInputPanel(DataSingleton singleton) {
        super(new BorderLayout());

        this.singleton = singleton;
        this.numEmployees = singleton.getNumEmployees();

        setData(singleton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        int i;
        for (i = 0; i < this.numEmployees; i++) {
            if (source == employeesFields[i]) {
                employeeNames[i] = employeesFields[i].getText();
            }
        }
        singleton.setEmployeeNames(employeeNames);
    }
    private void setEmployeeNames() {

        for (int i = 0; i < numEmployees; i++) {
            employeeNames[i] = employeesFields[i].getText();
        }
        this.singleton.setEmployeeNames(employeeNames);
    }
}
