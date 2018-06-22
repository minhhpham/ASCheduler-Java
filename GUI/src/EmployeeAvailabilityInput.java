import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.EventObject;

public class EmployeeAvailabilityInput extends JPanel {

    public static JButton btnNext, btnBack = null;
    private DataSingleton singleton;

    public EmployeeAvailabilityInput(DataSingleton singleton) {
        super(new BorderLayout());

        // Title
        JLabel title = new JLabel("Mark 2 for available, 1 for available, not preferred, and 0 for unavailable");

        this.singleton = singleton;
        Integer[][] employeeAvailability = new Integer[this.singleton.getNumEmployees()][this.singleton.getNumShifts()];

        DefaultTableModel model = new DefaultTableModel(employeeAvailability, singleton.getEmployeeNames());
        // create table
        JTable table = new JTable(model);

        // add title and table to frame
        add(title);
        add(new JScrollPane(table));

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(1,0));

        // Back button
        btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Main.changeFrame(Main.getFrame(), singleton.getEmployeeAvailabilityInput(), singleton.getSkillsInputPanel());
            }
        });

        buttonPane.add(btnBack);

        // Next button
        btnNext = new JButton();
        btnNext.setText("Next: Employee Skills");
        buttonPane.add(btnNext);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(buttonPane, BorderLayout.SOUTH);

    }

}
