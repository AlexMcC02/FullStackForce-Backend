package org.example.client;

public class FailedToGetDeliveryEmployeeException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to get delivery employee from the database";
    }
}
