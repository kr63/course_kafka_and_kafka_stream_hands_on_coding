package org.example.model;

public class Account2 {

    private String customer;

    public Account2() {
        super();
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account2 [customer=" + this.customer + "]";
    }

}
