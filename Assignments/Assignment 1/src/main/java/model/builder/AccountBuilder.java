package model.builder;

import model.Account;

import java.util.Date;

/**
 * Created by Alex on 07/03/2017.
 */
public class AccountBuilder {

    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setId(Long id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder setIBAN(String iban){
        account.setIban(iban);
        return this;
    }

    public AccountBuilder setHolderID(Long holderID){
        account.setHolderID(holderID);
        return this;
    }

    public AccountBuilder setBalance(Float balance){
        account.setBalance(balance);
        return this;
    }

    public AccountBuilder setType(String type){
        account.setType(type);
        return this;
    }

    public AccountBuilder setCreationDate(Date creationDate) {
        account.setCreationDate(creationDate);
        return this;
    }

    public Account build() {
        return account;
    }
}
