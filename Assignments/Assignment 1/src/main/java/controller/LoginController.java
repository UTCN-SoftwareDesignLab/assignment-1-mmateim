package controller;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import service.user.UserServiceImpl;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import static database.Constants.Controller.ADMIN_PASS;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginController extends Observable {
    private final LoginView loginView;
    private AuthenticationService authenticationService;
    private UserServiceImpl userService;

    public LoginController(LoginView loginView, AuthenticationService authenticationService, UserServiceImpl userService) {
        this.loginView = loginView;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    public void setButtonsVisible(Boolean alreadyRegistered) {
        loginView.setButtonsVisible(alreadyRegistered);
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
                    setChanged();
                    notifyObservers("LoginC_RegSucc");
                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            String role = "employee";
            if (loginView.isAdmin())
                role = "administrator";
            Notification<Boolean> registerNotification = authenticationService.register(username, password, role);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    if (loginView.isAdmin()) {
                        String pass = JOptionPane.showInputDialog(loginView.getContentPane(), "Password for admin");
                        if(!pass.equals(ADMIN_PASS)) {
                            JOptionPane.showMessageDialog(loginView.getContentPane(), "Wrong password");
                            userService.delete(userService.findByUsername(username));
                        }
                        else{
                            JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                            setChanged();
                            notifyObservers("LoginC_RegSucc");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                        setChanged();
                        notifyObservers("LoginC_RegSucc");
                    }
                }
            }
        }
    }

    public String getUsername() {
        return loginView.getUsername();
    }

    public void setVisible(Boolean flag) {
        loginView.setVisible(flag);
    }
}
