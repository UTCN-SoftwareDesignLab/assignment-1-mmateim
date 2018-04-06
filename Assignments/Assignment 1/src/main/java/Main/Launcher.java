package Main;

import controller.MainFlowController;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        new MainFlowController(componentFactory);
        //new MainController(new MainForm(), componentFactory);
       // ClientInfoView viewClientInfo = new ClientInfoView();
    }

}
