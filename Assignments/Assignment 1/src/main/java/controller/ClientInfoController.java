package controller;

import model.Client;
import repository.client.ClientRepository;
import service.client.ClientService;
import view.ClientInfoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import static database.Constants.Controller.CLIENT_INFO_ADVANCED;
import static database.Constants.Controller.CLIENT_INFO_BACK;

public class ClientInfoController extends Observable{
    private ClientInfoView clientInfoView;
    private ClientService clientService;

    public ClientInfoController(ClientInfoView clientInfoView, ClientService clientService){
        this.clientInfoView = clientInfoView;
        this.clientService = clientService;
        clientInfoView.setClientSearchListener(new SearchClientNameListener());
        clientInfoView.setBackListener(new BackListener());
        clientInfoView.setAdvancedListener(new AdvancedOperationsListener());
    }

    private class AdvancedOperationsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(CLIENT_INFO_ADVANCED);
        }
    }

    private class SearchClientNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = clientInfoView.getTxtNameClient();
            System.out.println("Search button pressed " + clientName);
            clientInfoView.populateComboBox(clientService.findByName(clientName));
            clientInfoView.setClientComboListener(new ClientComboListener());
        }
    }

    private class ClientComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String CNP = clientInfoView.getChosenCNP();
            if(CNP != null) {
                String clientInfo = clientService.findByCNP(CNP);
                clientInfoView.setTxtInfoClient(clientInfo);
            }
            else {
                clientInfoView.setTxtInfoClient("");
            }
        }
    }

    private class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(CLIENT_INFO_BACK);
        }
    }

    public void setVisible(Boolean flag){
        clientInfoView.setVisible(flag);
    }
}
