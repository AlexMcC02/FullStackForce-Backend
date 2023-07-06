package org.example.cli;

public class SalesEmployee extends Employee {
    private double commissionRate;
    public SalesEmployee(int empId, String firstName, String lastName, double salary, String backAccount, String nationalInsurance, double commissionRate) {
        super(empId, firstName, lastName, salary, backAccount, nationalInsurance);
        setCommissionRate(commissionRate);
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
}
