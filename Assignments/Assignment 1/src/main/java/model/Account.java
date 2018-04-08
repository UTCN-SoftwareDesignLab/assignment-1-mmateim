package model;

import java.util.Date;

/**
 * Created by Alex on 07/03/2017.
 */
public class Account {

    private Long id;

    private String iban;
    private long holderID;
    private float balance;
    private Date creationDate;
    private String type;

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getHolderID() {
        return holderID;
    }

    public void setHolderID(long holderID) {
        this.holderID = holderID;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
