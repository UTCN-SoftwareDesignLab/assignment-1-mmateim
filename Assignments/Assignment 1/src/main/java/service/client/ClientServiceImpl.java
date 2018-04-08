package service.client;

import model.Client;
import model.validation.UserRoleValidator;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
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
    public Boolean delete(String id) {
        return clientRepository.delete(id);
    }

    @Override
    public Boolean update(String id, Client client) {
        return clientRepository.update(id,client);
    }

    @Override
    public Client findByCNP(String CNP) {
        return clientRepository.findByCNP(CNP);
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
