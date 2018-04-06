package service.client;

import model.Client;
import repository.EntityNotFoundException;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface ClientService {

    List<Client> findAll();

    DefaultTableModel findAllTable();

    Client findById(Long id) throws EntityNotFoundException;

    boolean save(Client client);

    public String findByCNP(String CNP);

    public List<String> findByName(String name);
}
