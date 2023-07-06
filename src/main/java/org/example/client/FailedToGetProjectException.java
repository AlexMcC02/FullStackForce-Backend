package org.example.client;

public class FailedToGetProjectException extends Exception {
    @Override
    public String getMessage(){
        return "Could not delete project.";
    }
}
