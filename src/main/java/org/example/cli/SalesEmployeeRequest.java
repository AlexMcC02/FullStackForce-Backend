package org.example.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesEmployeeRequest {

    private String firstName;
    private String lastName;
    private double salary;
    private String bankAccount;
    private String nationalInsurance;

    public SalesEmployeeRequest(@JsonProperty("firstName") String firstName,
                                @JsonProperty("lastName") String lastName,
                                @JsonProperty("salary") double salary,
                                @JsonProperty("bankAccount") String bankAccount,
                                @JsonProperty("nationalInsurance") String nationalInsurance,
                                @JsonProperty("commission_rate") double commission_rate){


        setFirstName(firstName);
        setLastName(lastName);
        setSalary(salary);
        setBankAccount(bankAccount);
        setNationalInsurance(nationalInsurance);
        setCommission_rate(commission_rate);


    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getNationalInsurance() {
        return nationalInsurance;
    }

    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }

    public double getCommission_rate() {
        return commission_rate;
    }

    public void setCommission_rate(double commission_rate) {
        this.commission_rate = commission_rate;
    }

    private double commission_rate;


}