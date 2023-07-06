package org.example.client;

public class FailedToDeleteDeliveryEmployeeException extends Exception {
    @Override
    public String getMessage(){
        return "Could not delivery employee client.";
    }
}
