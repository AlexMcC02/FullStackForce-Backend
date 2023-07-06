package org.example.client;

public class DeliveryEmployeeDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "A delivery employee with that id does not exist";
    }
}
