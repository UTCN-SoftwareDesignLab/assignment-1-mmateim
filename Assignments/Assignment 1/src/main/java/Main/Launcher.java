package Main;

import controller.MainController;
import view.MainForm;
import view.ViewClientInfo;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        new MainController(new MainForm(), componentFactory);
       // ViewClientInfo viewClientInfo = new ViewClientInfo();
    }

}
