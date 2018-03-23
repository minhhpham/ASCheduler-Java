public class DataSingleton {
    private static DataSingleton singleton;

    // Panels
    private NumberFieldsPanel numberFields;
    private EmployeeInputPanel employeeInput;
    private ShiftsInputPanel shiftsInputPanel;

    // Number Fields
    private int numEmployees = 0;
    private int numShifts = 0;
    private int numSkills = 0;

    // Employee Input
    private String[] employeeNames;


    private DataSingleton() {

    }

    public NumberFieldsPanel getNumberFields() {
        return numberFields;
    }

    public void setNumberFields(NumberFieldsPanel numberFields) {
        this.numberFields = numberFields;
    }

    public EmployeeInputPanel getEmployeeInput() {
        return employeeInput;
    }

    public void setEmployeeInput(EmployeeInputPanel employeeInput) {
        this.employeeInput = employeeInput;
    }

    public ShiftsInputPanel getShiftsInputPanel() {
        return shiftsInputPanel;
    }

    public void setShiftsInputPanel(ShiftsInputPanel shiftsInputPanel) {
        this.shiftsInputPanel = shiftsInputPanel;
    }

    public static DataSingleton getInstance() {
        if (singleton == null) {
            singleton = new DataSingleton();
        }
        return singleton;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }

    public int getNumShifts() {
        return numShifts;
    }

    public void setNumShifts(int numShifts) {
        this.numShifts = numShifts;
    }

    public int getNumSkills() {
        return numSkills;
    }

    public void setNumSkills(int numSkills) {
        this.numSkills = numSkills;
    }

    public String[] getEmployeeNames() {
        return employeeNames;
    }

    public void setEmployeeNames(String[] employeeNames) {
        this.employeeNames = employeeNames;
    }

}
