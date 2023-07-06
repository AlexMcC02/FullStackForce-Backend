package org.example.client;

public class InvalidDeliveryEmployeeException extends Exception {
    public InvalidDeliveryEmployeeException(String error){
        super(error);
    }
}
