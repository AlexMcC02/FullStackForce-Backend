package org.example.client;

public class FailedToDeleteSalesEmployeeException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to delete sales employee.";
    }

}
