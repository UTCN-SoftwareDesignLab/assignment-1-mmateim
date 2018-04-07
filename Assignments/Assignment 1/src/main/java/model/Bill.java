package model;

import java.util.Date;

public class Bill {
    private long id;
    private long clientId;
    private long accountId;
    private String information;
    private float amount;
    private long employeeId;
    private Date date;

    public Bill(String information, float amount, Date date) {
        this.information = information;
        this.amount = amount;
        this.date = date;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getClientId() {
        return clientId;
    }

    public String getInformation() {
        return information;
    }

    public float getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getEmployeeId() {
        return employeeId;
    }
}
