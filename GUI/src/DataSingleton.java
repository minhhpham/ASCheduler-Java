public class DataSingleton {
    private static DataSingleton singleton;

    // Number Fields
    private int numEmployees = 0;
    private int numShifts = 0;
    private int numSkills = 0;

    // Employee Input
    private String[] employeeNames;


    private DataSingleton() {

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
