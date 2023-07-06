package org.example.client;

public class ProjectDoesNotExistException extends Exception {
    @Override
    public String getMessage(){
        return "A project with that id does not exist";
    }
}
