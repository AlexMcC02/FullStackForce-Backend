package org.example.client;

public class FailedToUpdateDeliveryEmployeeException extends Exception {
    @Override
    public String getMessage(){
        return "Could not update delivery employee.";
    }
}
