import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;


public class NumberFieldsPanel extends JPanel implements PropertyChangeListener {

    private DataSingleton singleton;

    public static JButton btnNext = null;

    // Labels to identify the fields
    private JLabel employeesLabel, shiftsLabel, skillsLabel;

    // Strings for the labels
    private static String employees = "Number of Employees: ";
    private static String shifts = "Number of Shifts: ";
    private static String skills = "Number of Skills: ";

    // Fields for data entry
    private JFormattedTextField employeesField;
    private JFormattedTextField shiftsField;
    private JFormattedTextField skillsField;

    // Format and parse numbers
    private NumberFormat employeesFormat;
    private NumberFormat shiftsFormat;
    private NumberFormat skillsFormat;

    public NumberFieldsPanel(DataSingleton singleton) {
        super(new BorderLayout());
        this.singleton = singleton;
        setUpFormats();

        // Set up labels, text fields, etc
        employeesLabel = new JLabel(employees);
        shiftsLabel = new JLabel(shifts);
        skillsLabel = new JLabel(skills);

        employeesField = new JFormattedTextField(employeesFormat);
        employeesField.setValue(0);
        employeesField.setColumns(10);
        employeesField.addPropertyChangeListener("value", this);

        shiftsField = new JFormattedTextField(shiftsFormat);
        shiftsField.setColumns(10);
        shiftsField.addPropertyChangeListener("value", this);

        skillsField = new JFormattedTextField(skillsFormat);
        skillsField.setColumns(10);
        skillsField.addPropertyChangeListener("value", this);

        // Tell accessibility tools about label/text field pairs
        employeesLabel.setLabelFor(employeesField);
        shiftsLabel.setLabelFor(shiftsField);
        skillsLabel.setLabelFor(skillsField);

        // Next button
        btnNext = new JButton();
        btnNext.setText("Next: Input Employees");
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EmployeeInputPanel employeeInputPanel = new EmployeeInputPanel(singleton);
                singleton.setEmployeeInput(employeeInputPanel);
                Main.changeFrame(Main.getFrame(), singleton.getNumberFields(), singleton.getEmployeeInput());
            }
        });

        // Lay out the labels in a panel
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(employeesLabel);
        labelPane.add(shiftsLabel);
        labelPane.add(skillsLabel);

        // Lay out the text fields in the panel
        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(employeesField);
        fieldPane.add(shiftsField);
        fieldPane.add(skillsField);


        // Put labels on left, text fields on right of panel
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(btnNext, BorderLayout.SOUTH);
    }

    //Create and set up number formats. These objects also
    //parse numbers input by user.
    private void setUpFormats() {
        employeesFormat = NumberFormat.getNumberInstance();
        shiftsFormat = NumberFormat.getNumberInstance();
        skillsFormat = NumberFormat.getNumberInstance();
    }

    // Is called when a field's value changes
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        if (source == employeesField) {
            if (employeesField.getValue() != null) {
                singleton.setNumEmployees(Integer.parseInt(employeesField.getText()));
            }
        } else if (source == shiftsField) {
            if (shiftsField.getValue() != null) {
                singleton.setNumShifts(Integer.parseInt(shiftsField.getText()));
            }
        } else if (source == skillsField) {
            if (skillsField.getValue() != null) {
                singleton.setNumSkills(Integer.parseInt(skillsField.getText()));
            }
        }
    }

}