package controller;

import view.ViewClientInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientInfoController {
    private ViewClientInfo viewClientInfo;

    public ClientInfoController(ViewClientInfo viewClientInfo){
        this.viewClientInfo = viewClientInfo;
        viewClientInfo.setClientSearchListener(new SearchClientNameListener());

    }

    public class SearchClientNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
