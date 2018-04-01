package controller;

import view.ViewClientInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ClientInfoController extends Observable{
    private ViewClientInfo viewClientInfo;

    public ClientInfoController(ViewClientInfo viewClientInfo){
        this.viewClientInfo = viewClientInfo;
        viewClientInfo.setClientSearchListener(new SearchClientNameListener());

    }

    private class SearchClientNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = viewClientInfo.getTxtNameClient();
        }
    }

    public void setVisible(Boolean flag){
        viewClientInfo.setVisible(flag);
    }
}
