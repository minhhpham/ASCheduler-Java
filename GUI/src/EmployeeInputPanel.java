import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class EmployeeInputPanel extends JPanel implements PropertyChangeListener {

    public JList list;
    public JLabel employeesLabel;
    public static JButton btnNext, btnBack = null;
    public JTextField employeesFields[];
    public String employeeNames[];
    public int numEmployees;

    public EmployeeInputPanel(DataSingleton singleton) {
        super(new BorderLayout());

        this.numEmployees = singleton.getNumEmployees();
        // Initialize array of text fields with value passed in
        employeesFields = new JTextField[this.numEmployees];

//        System.out.println(this.numEmployees);

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

        // Next button
        btnNext = new JButton();
        btnNext.setText("Next: Input shifts");
        buttonPane.add(btnNext);

        // Lay out components before adding to main frame
        listPane.repaint();
        listPane.revalidate();
        buttonPane.repaint();
        buttonPane.revalidate();

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(listPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        for (int i = 0; i < this.numEmployees; i++) {
            if (source == employeesFields[i]) {
                employeeNames[i] = employeesFields[i].toString();
            }
        }
    }
}
