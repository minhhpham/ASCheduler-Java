import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SkillsInputPanel extends JPanel implements PropertyChangeListener {

    public JLabel skillsLabel;
    public static JButton btnNext, btnBack = null;
    public JTextField skillsFields[];
    public String skillNames[];
    public int numSkills;

    public void setData(DataSingleton singleton) {

        skillsFields = new JTextField[this.numSkills];

        String skills = "Skills Needed";
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
        skillsLabel = new JLabel(skills);

        listPane.add(skillsLabel);
        listPane.add(Box.createRigidArea(new Dimension(0, 5)));

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
/*
private DataSingleton singleton;

        listPane.add(employeesLabel);
        listPane.add(Box.createRigidArea(new Dimension(0, 5)));


        for (int i = 0; i < this.numEmployees; i++) {
            employeesFields[i] = new JTextField();
            if (i == 0) {
                employeesFields[i].setText("Grace Hopper");
            }
            employeesFields[i].setColumns(10);
            employeesFields[i].addPropertyChangeListener("value", this);
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
        for (int i = 0; i < this.numEmployees; i++) {
            if (source == employeesFields[i]) {
                employeeNames[i] = employeesFields[i].toString();
            }
        }
        singleton.setEmployeeNames(employeeNames);
    }

 */
