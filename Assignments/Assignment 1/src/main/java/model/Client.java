package model;

import java.util.List;

public class Client {
    private long ID;
    private String name;
    private String cnp;
    private String address;

    public long getId() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client() {    }

    @Override
    public String toString() {
        return "ID: " + ID + "\n" + "name: " + name + "\n" + "CNP:" + cnp + "\n" + "Address: " + address + "\n";
    }
}
