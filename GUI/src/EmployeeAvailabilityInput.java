import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EmployeeAvailabilityInput extends JPanel implements PropertyChangeListener {

    public static JButton btnNext, btnBack = null;
    private DataSingleton singleton;
    Integer[][] employeeAvailability;


    public EmployeeAvailabilityInput(DataSingleton singleton) {
        super(new BorderLayout());

        JPanel title = new JPanel();
        title.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Employee Availability: 0-unavailable, 1-available, 2-available, not preferred");
        title.add(titleLabel);
        add(title);

        this.singleton = singleton;
        employeeAvailability = new Integer[this.singleton.getNumEmployees()][this.singleton.getNumShifts()];

        DefaultTableModel model = new DefaultTableModel(employeeAvailability, singleton.getEmployeeNames());
        // create table
        JTable table = new JTable(model);

        // add table to frame
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
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EmployeeSkillsInput employeeSkillsInput = new EmployeeSkillsInput(singleton);
                singleton.setEmployeeSkillsInput(employeeSkillsInput);
                Main.changeFrame(Main.getFrame(), singleton.getEmployeeAvailabilityInput(), singleton.getEmployeeSkillsInput());
            }
        });

        buttonPane.add(btnNext);

        add(buttonPane, BorderLayout.SOUTH);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        for (int i = 0; i < this.singleton.getNumEmployees(); i++) {
            for (int j = 0; j < this.singleton.getNumShifts(); j++) {
                if (source == this.employeeAvailability[i][j]) {
                    //employeeNames[i] = employeesFields[i].getText();
                }
            }

        }
        singleton.setEmployeeAvailability(this.employeeAvailability);
    }
}
