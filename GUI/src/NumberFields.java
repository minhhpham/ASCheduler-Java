import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class NumberFields extends JPanel implements PropertyChangeListener{
    // Default values for the fields
    private long numEmployees, numShifts, numSkills = 0;

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

    public NumberFields() {
        super(new BorderLayout());
        setUpFormats();

        // Set up labels, text fields, etc
        employeesLabel = new JLabel(employees);
        shiftsLabel = new JLabel(shifts);
        skillsLabel = new JLabel(skills);

        employeesField = new JFormattedTextField(employeesFormat);
        employeesField.setValue(numEmployees);
        employeesField.setColumns(10);
        employeesField.addPropertyChangeListener("value", this);

        shiftsField = new JFormattedTextField(shiftsFormat);
        shiftsField.setValue(numShifts);
        shiftsField.setColumns(10);
        shiftsField.addPropertyChangeListener("value", this);

        skillsField = new JFormattedTextField(skillsFormat);
        skillsField.setValue(numSkills);
        skillsField.setColumns(10);
        skillsField.addPropertyChangeListener("value", this);

        // Tell accessibility tools about label/text field pairs
        employeesLabel.setLabelFor(employeesField);
        shiftsLabel.setLabelFor(shiftsField);
        skillsLabel.setLabelFor(skillsField);

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
            numEmployees = (long)employeesField.getValue();
        } else if (source == shiftsField) {
            numShifts = (long)shiftsField.getValue();
        } else if (source == skillsField) {
            numSkills = (long)skillsField.getValue();
        }
    }

    public static void main(String[] args) {
        // Create and set up window
        JFrame frame = new JFrame("ASCheduler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add contents to the window
        frame.add(new NumberFields());

        // Display the window
        frame.pack();
        frame.setVisible(true);
    }
}