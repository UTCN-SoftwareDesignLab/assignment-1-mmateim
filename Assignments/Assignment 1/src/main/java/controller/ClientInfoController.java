package controller;

import model.Client;
import repository.client.ClientRepository;
import view.ViewClientInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ClientInfoController extends Observable{
    private ViewClientInfo viewClientInfo;
    private ClientRepository clientRepository;

    public ClientInfoController(ViewClientInfo viewClientInfo, ClientRepository clientRepository){
        this.viewClientInfo = viewClientInfo;
        this.clientRepository = clientRepository;
        viewClientInfo.setClientSearchListener(new SearchClientNameListener());
        viewClientInfo.setClientComboListener(new ClientComboListener());
    }

    private class SearchClientNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = viewClientInfo.getTxtNameClient();
            System.out.println("Search button pressed " + clientName);
            viewClientInfo.populateComboBox(clientRepository.findByName(clientName));
        }
    }

    private class ClientComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String CNP = viewClientInfo.getChosenCNP();
            if(CNP != null) {
                Client client = clientRepository.findByCNP(CNP);
                viewClientInfo.setTxtInfoClient(client.toString());
            }
        }
    }

    public void setVisible(Boolean flag){
        viewClientInfo.setVisible(flag);
    }
}
