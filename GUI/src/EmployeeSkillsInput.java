import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EmployeeSkillsInput extends JPanel{
    private DataSingleton singleton;
    public static JButton btnNext, btnBack = null;
    Integer[][] employeeSkills;

    public EmployeeSkillsInput(DataSingleton singleton) {
        super(new BorderLayout());

        JPanel title = new JPanel();
        title.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Employee Skills: 0-no skill, 1-has skill");
        title.add(titleLabel);
        add(title);

        this.singleton = singleton;
        employeeSkills = new Integer[this.singleton.getNumEmployees()][this.singleton.getNumShifts()];

        DefaultTableModel model = new DefaultTableModel(employeeSkills, singleton.getSkills());
        // create table
        JTable table = new JTable(model);

        // add table to frame
        add(new JScrollPane(table));

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(1, 0));

        // Back button
        btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Main.changeFrame(Main.getFrame(), singleton.getEmployeeSkillsInput(), singleton.getEmployeeAvailabilityInput());
            }
        });

        buttonPane.add(btnBack);

        // Next button
        btnNext = new JButton();
        btnNext.setText("Next: Employee Hour Limit");
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                EmployeeSkillsInput employeeSkillsInput = new EmployeeSkillsInput(singleton);
//                singleton.setEmployeeAvailability(employeeSkills);
//                Main.changeFrame(Main.getFrame(), singleton.getEmployeeSkillsInput(), NEXT);
            }
        });

        buttonPane.add(btnNext);

        add(buttonPane, BorderLayout.SOUTH);
    }
}
