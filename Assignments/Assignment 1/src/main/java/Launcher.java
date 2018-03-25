import controller.LoginController;
import controller.MainController;
import view.LoginView;
import view.MainForm;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        //new LoginController(new LoginView(), componentFactory.getAuthenticationService());
        new MainController();
    }

}
