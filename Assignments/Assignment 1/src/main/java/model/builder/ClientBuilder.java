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
        client.setCnp(CNP);
        return this;
    }

    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public Client build(){
        return client;
    }
}
