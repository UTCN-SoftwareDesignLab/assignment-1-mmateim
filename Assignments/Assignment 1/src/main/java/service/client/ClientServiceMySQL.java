package service.client;

import model.Client;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClientServiceMySQL implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceMySQL(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public DefaultTableModel findAllTable() {
        List<Client> clientList = clientRepository.findAll();
        if(clientList == null)
            return null;
        String[] columnNames = {"id", "name", "CNP", "address"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Client client : clientList){
            Vector row = new Vector();
            row.addElement(client.getId());
            row.addElement(client.getName());
            row.addElement(client.getCnp());
            row.addElement(client.getAddress());
            model.addRow(row);
        }
        return model;
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public boolean save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public String findByCNP(String CNP) {
        return clientRepository.findByCNP(CNP).toString();
    }

    @Override
    public List<String> findByName(String name) {
        List<Client> clientList = clientRepository.findByName(name);
        List<String> cnpList = new ArrayList<>();
        for (Client c:clientList) {
            cnpList.add(c.getCnp());
        }
        return cnpList;
    }


}
