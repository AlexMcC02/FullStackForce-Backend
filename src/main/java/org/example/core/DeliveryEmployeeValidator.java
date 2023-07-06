package org.example.core;

import org.example.cli.DeliveryEmployeeRequest;

public class DeliveryEmployeeValidator {
    public String isValidDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee) {
        if (deliveryEmployee.getFirstName().length() > 50) {
            return "Delivery employee first name should be under 50 characters";
        }
        if (deliveryEmployee.getLastName().length() > 50) {
            return "Delivery employee last name should be under 50 characters";
        }
        if (deliveryEmployee.getSalary() < 10) {
            return "Invalid salary";
        }
        if (deliveryEmployee.getNationalInsurance().length() != 9) {
            return "National insurance number should be 9 digits";
        }
        if (deliveryEmployee.getBackAccount().length() != 10) {
            return "Bank account number should be 10 digits";
        }
        return null;
    }
}
