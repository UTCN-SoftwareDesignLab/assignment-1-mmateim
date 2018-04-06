package repository.client;

import model.Client;
import model.validation.Notification;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    boolean save(Client client);

    void removeAll();

    public Client findByCNP(String CNP);

    List<Client> findByName(String name);
}
