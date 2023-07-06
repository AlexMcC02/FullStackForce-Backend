package org.example.cli;

public class Project {
    private int ID;
    private int Client_ID;
    private String Name;
    private double Value;


    public Project(int ID, int client_ID, String name, double value) {
        setID(ID);
        setName(name);
        setValue(value);
        setClient_ID(client_ID);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public int getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(int client_ID) {
        Client_ID = client_ID;
    }
}
