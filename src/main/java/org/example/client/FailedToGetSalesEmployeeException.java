package org.example.client;

public class FailedToGetSalesEmployeeException extends Exception {
    @Override
    public String getMessage(){
        return "Unable to get sales employees.";
    }
}
