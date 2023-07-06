package org.example.client;

public class FailedToUpdateSalesEmployeeException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to update sales employee.";
    }
}
