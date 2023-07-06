package org.example.cli;

import static org.apache.commons.lang3.StringUtils.left;

public class DeliveryEmployee extends Employee {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DeliveryEmployee(int empId, String firstName, String lastName, double salary, String backAccount, String nationalInsurance) {
        super(empId, firstName, lastName, salary, backAccount, nationalInsurance);
        setUsername(left(firstName,1) + lastName + empId);
    }

}
