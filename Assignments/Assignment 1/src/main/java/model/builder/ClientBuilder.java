package model.builder;

import model.Account;
import model.Client;

import java.util.List;

public class ClientBuilder {

    private Client client;

    public ClientBuilder(){
        client = new Client();
    }

    public ClientBuilder setID(Long ID){
        client.setID(ID);
        return this;
    }

    public ClientBuilder setName(String name){
        client.setName(name);
        return this;
    }

    public ClientBuilder setCNP(String CNP) {
        client.setCNP(CNP);
        return this;
    }

    public ClientBuilder setAccounts(List<Account> accounts) {
        client.setAccounts(accounts);
        return this;
    }

    public ClientBuilder setAdress(String adress) {
        client.setAddress(adress);
        return this;
    }

    public Client build(){
        return client;
    }
}
