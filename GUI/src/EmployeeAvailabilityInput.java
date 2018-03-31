import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
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

public class EmployeeAvailabilityInput extends JPanel implements PropertyChangeListener {

    private DataSingleton singleton;
    public JLabel employeeAvailabilityLabel;
    public static JButton btnNext, btnBack = null;
    public JFormattedTextField[][] employeeAvailability;
    public int[][] availabilityMatrix;
    private NumberFormat availabilityFormat;

    private class EmployeeAvailabilityModel extends AbstractTableModel {

        private final int[] columnTimes = singleton.getShiftTimes();
//        private final String[] rowNames = singleton.getEmployeeNames();

        private final Class[] columnClass = new Class[] {
                Integer.class, Integer.class, Integer.class, Integer.class
        };

        @Override
        public String getColumnName(int column) {
            return String.valueOf(columnTimes[column]);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnClass[columnIndex];
        }

        @Override
        public int getRowCount() {
            return singleton.getNumEmployees();
        }

        @Override
        public int getColumnCount() {
            return singleton.getNumShifts();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return 0;
        }
    }

    static class IntegerEditor extends DefaultCellEditor
    {
        JFormattedTextField ftf;
        NumberFormat integerFormat;
        private Integer minimum, maximum;
        private boolean DEBUG = false;

        public IntegerEditor(int min, int max)
        {
            super(new JFormattedTextField());

            setClickCountToStart(2);

            ftf = (JFormattedTextField)getComponent();
            ftf.setBorder(new LineBorder(Color.BLACK));
            minimum = new Integer(min);
            maximum = new Integer(max);

            //Set up the editor for the integer cells.
            integerFormat = NumberFormat.getIntegerInstance();
            NumberFormatter intFormatter = new NumberFormatter(integerFormat);
            intFormatter.setFormat(integerFormat);
            intFormatter.setMinimum(minimum);
            intFormatter.setMaximum(maximum);

            ftf.setFormatterFactory(new DefaultFormatterFactory(intFormatter));
            ftf.setValue(minimum);
            ftf.setHorizontalAlignment(JTextField.TRAILING);
            ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);

            //React when the user presses Enter while the editor is
            //active.  (Tab is handled as specified by
            //JFormattedTextField's focusLostBehavior property.)
            ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "check");

            ftf.getActionMap().put("check", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if (!ftf.isEditValid())  //The text is invalid.
                    {
                        if (userSaysRevert())
                        {
                            ftf.postActionEvent(); //inform the editor
                        }
                    }
                    else
                        try
                        {
                            ftf.commitEdit();     //so use it.
                            ftf.postActionEvent(); //stop editing
                        }
                        catch (java.text.ParseException exc) { }
                }
            });
        }

        @Override
        public boolean isCellEditable(EventObject event)
        {
            JTable table = (JTable)event.getSource();
            return true;
        }

        //Override to invoke setValue on the formatted text field.
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column)
        {
            JFormattedTextField ftf = (JFormattedTextField)super.getTableCellEditorComponent(
                    table, value, isSelected, row, column);
            ftf.setValue(value);

            return ftf;
        }

        //Override to ensure that the value remains an Integer.
        public Object getCellEditorValue()
        {
            JFormattedTextField ftf = (JFormattedTextField)getComponent();
            Object o = ftf.getValue();
            if (o instanceof Integer)
            {
                return o;
            }
            else if (o instanceof Number)
            {
                return new Integer(((Number)o).intValue());
            }
            else
            {
                try
                {
                    return integerFormat.parseObject(o.toString());
                }
                catch (ParseException exc)
                {
                    System.err.println("getCellEditorValue: can't parse o: " + o);
                    return null;
                }
            }
        }

        //Override to check whether the edit is valid,
        //setting the value if it is and complaining if
        //it isn't.  If it's OK for the editor to go
        //away, we need to invoke the superclass's version
        //of this method so that everything gets cleaned up.
        public boolean stopCellEditing()
        {
            JFormattedTextField ftf = (JFormattedTextField)getComponent();

            if (ftf.isEditValid())
            {
                try
                {
                    ftf.commitEdit();
                }
                catch (java.text.ParseException exc) { }

            }
            else
            {
                if (!userSaysRevert())  //user wants to edit
                {
                    return false; //don't let the editor go away
                }
            }

            return super.stopCellEditing();
        }

        /**
         * Lets the user know that the text they entered is
         * bad. Returns true if the user elects to revert to
         * the last good value.  Otherwise, returns false,
         * indicating that the user wants to continue editing.
         */
        protected boolean userSaysRevert() {
            Toolkit.getDefaultToolkit().beep();
            ftf.selectAll();
            Object[] options = {"Edit",
                    "Revert"};
            int answer = JOptionPane.showOptionDialog(
                    SwingUtilities.getWindowAncestor(ftf),
                    "The value must be an integer between "
                            + minimum + " and "
                            + maximum + ".\n"
                            + "You can either continue editing "
                            + "or revert to the last valid value.",
                    "Invalid Text Entered",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (answer == 1) { //Revert!
                ftf.setValue(ftf.getValue());
                return true;
            }
            return false;
        }


    }

    public EmployeeAvailabilityInput(DataSingleton singleton) {
        super(new BorderLayout());
        this.singleton = singleton;

        String columnNames[] = new String[singleton.getNumShifts()];
        for (int i = 0; i < singleton.getNumShifts(); i++) {
            columnNames[i] = String.valueOf(singleton.getShiftTimes()[i]);
        }

        Object[][] data =
                {
                        {"a", new Integer(1), new Integer(10)},
                        {"b", new Integer(2), new Integer(20)},
                        {"c", new Integer(3), new Integer(30)}
                };

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);

        table.getColumnModel().getColumn(2).setCellEditor(new IntegerEditor(0, 2));
        DefaultCellEditor editor = (DefaultCellEditor)table.getDefaultEditor(Integer.class);


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
                Main.changeFrame(Main.getFrame(), singleton.getEmployeeAvailabilityInput(), singleton.getSkillsInputPanel());
            }
        });

        // Next button
        btnNext = new JButton();
        btnNext.setText("Next:");
        buttonPane.add(btnNext);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(employeeAvailabilityLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        for (int i = 0; i < singleton.getNumEmployees(); i++) {
            for (int j = 0; j < singleton.getNumShifts(); j++) {
                if (source == employeeAvailability[i][j]) {
                    availabilityMatrix[i][j] = Integer.parseInt(employeeAvailability[i][j].getText());
                }
            }
        }
        singleton.setEmployeeAvailability(availabilityMatrix);
    }
}
