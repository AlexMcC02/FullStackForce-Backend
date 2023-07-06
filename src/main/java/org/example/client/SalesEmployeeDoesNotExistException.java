package org.example.client;

public class SalesEmployeeDoesNotExistException extends Exception {
    @Override
    public String getMessage(){
        return "The sales employee does not exist.";
    }
}
