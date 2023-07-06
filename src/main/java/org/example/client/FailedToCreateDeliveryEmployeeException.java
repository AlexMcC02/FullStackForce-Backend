package org.example.client;

public class FailedToCreateDeliveryEmployeeException extends Exception {
    @Override
    public String getMessage(){
        return "Could not create delivery employee.";
    }
}
