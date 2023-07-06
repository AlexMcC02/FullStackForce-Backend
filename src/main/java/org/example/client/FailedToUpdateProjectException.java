package org.example.client;

public class FailedToUpdateProjectException extends Exception {
    @Override
    public String getMessage(){
        return "Could not update project.";
    }
}
