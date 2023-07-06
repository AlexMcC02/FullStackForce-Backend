package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectRequest {

    private int Client_ID;
    private String Name;
    private double Value;

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

    @JsonCreator
    public ProjectRequest(
            @JsonProperty("Client_ID")int Client_ID,
            @JsonProperty("Name")String Name,
            @JsonProperty("Value")double Value) {
        this.Client_ID = Client_ID;
        this.Name = Name;
        this.Value = Value;
    }
}
