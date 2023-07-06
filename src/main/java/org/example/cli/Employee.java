package org.example.cli;

public abstract class Employee {
    private int empId;
    private String firstName;
    private String lastName;
    private double salary;
    private String backAccount;
    private String nationalInsurance;

    public Employee(int empId, String firstName, String lastName, double salary, String backAccount, String nationalInsurance) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.backAccount = backAccount;
        this.nationalInsurance = nationalInsurance;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(String backAccount) {
        this.backAccount = backAccount;
    }

    public String getNationalInsurance() {
        return nationalInsurance;
    }

    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }
}
