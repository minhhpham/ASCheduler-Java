import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SkillsInputPanel extends JPanel implements PropertyChangeListener {

    public DataSingleton singleton;
    public JLabel skillsLabel;
    public static JButton btnNext, btnBack = null;
    public JTextField skillsFields[];
    public String skillNames[];
    public int numSkills;

    public void setData(DataSingleton singleton) {
        this.numSkills = singleton.getNumSkills();

        this.skillsFields = new JTextField[this.numSkills];

        String skills = "Skills Needed";
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
        this.skillsLabel = new JLabel(skills);

        listPane.add(this.skillsLabel);
        listPane.add(Box.createRigidArea(new Dimension(0, 5)));

        for (int i = 0; i < this.numSkills; i++) {
            skillsFields[i] = new JTextField();
            if (i == 0) {
                skillsFields[i].setText("Can speak Spanish");
            }
            skillsFields[i].setColumns(10);
            skillsFields[i].addPropertyChangeListener("value", this);
            listPane.add(skillsFields[i]);
        }

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(1,0));

        // Back button
        btnBack = new JButton();
        btnBack.setText("Back");
        buttonPane.add(btnBack);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Main.changeFrame(Main.getFrame(), singleton.getSkillsInputPanel(), singleton.getShiftsInputPanel());
            }
        });

        // Next button
        btnNext = new JButton();
        btnNext.setText("Next: Employee Availability");
        buttonPane.add(btnNext);

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EmployeeAvailabilityInput employeeAvailabilityInput = new EmployeeAvailabilityInput(DataSingleton.getInstance());
                singleton.setEmployeeAvailabilityInput(employeeAvailabilityInput);
                Main.changeFrame(Main.getFrame(), singleton.getSkillsInputPanel(), singleton.getEmployeeAvailabilityInput());
            }
        });

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(listPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
    }

    public SkillsInputPanel(DataSingleton singleton) {
        super(new BorderLayout());
        this.singleton = singleton;
        this.numSkills = singleton.getNumSkills();
        setData(singleton);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        for (int i = 0; i < this.numSkills; i++) {
            if (source == skillsFields[i]) {
                skillNames[i] = skillsFields[i].toString();
            }
        }
        singleton.setSkills(skillNames);
    }
    private void setSkills() {

        for (int i = 0; i < numSkills; i++) {
            if (skillsFields[i].getText() != null) {
                skillNames[i] = skillsFields[i].getText();
            }
        }
        this.singleton.setSkills(skillNames);
    }
}