package controller;

import Main.ComponentFactory;
import view.MainForm;

import java.util.Observable;
import java.util.Observer;

public class MainFlowController implements Observer {
    private ComponentFactory componentFactory;
    private MainController mainController;
    private LoginController loginController;

    public MainFlowController(ComponentFactory componentFactory){
        this.componentFactory = componentFactory;
      //  mainController = new MainController(new MainForm());
    }

    @Override
    public void update(Observable o, Object arg) {
//        switch (Observable o){
//
//        }
    }
}
