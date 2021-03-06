package service.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException;

    boolean save(Client client);

    public Client findByCNP(String CNP);

    public List<String> findByName(String name);

    public Boolean delete(String id);

    public Boolean update(String id, Client client);
}
