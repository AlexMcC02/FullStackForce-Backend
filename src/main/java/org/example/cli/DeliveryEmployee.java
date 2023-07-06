package org.example.cli;

public class DeliveryEmployee extends Employee {

    private int deliveryID;
    private String username;

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DeliveryEmployee(int empId, String firstName, String lastName, double salary, String backAccount) {
        super(empId, firstName, lastName, salary, backAccount);



    }

}
