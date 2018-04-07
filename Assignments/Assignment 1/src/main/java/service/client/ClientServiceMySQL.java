package service.client;

import model.Client;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.util.ArrayList;
import java.util.List;

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
