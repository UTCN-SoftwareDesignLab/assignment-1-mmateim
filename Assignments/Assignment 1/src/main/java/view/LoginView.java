package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginView extends JFrame {

    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JCheckBox CBAdmin;
    private JLabel lAdmin;

    public LoginView() throws HeadlessException {
        setSize(300, 200);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(tfUsername);
        add(tfPassword);
        add(btnLogin);
        add(btnRegister);
        add(lAdmin);
        add(CBAdmin);
        tfUsername.setText("mm@gmail.com");
        tfPassword.setText("Parola_9");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void setButtonsVisible(Boolean alreadyRegistered) {
        if(alreadyRegistered){
            btnRegister.setVisible(false);
            CBAdmin.setVisible(false);
            btnLogin.setVisible(true);
            lAdmin.setVisible(false);
        }
        else {
            btnLogin.setVisible(false);
            btnRegister.setVisible(true);
            CBAdmin.setVisible(true);
            lAdmin.setVisible(true);
        }
    }

    private void initializeFields() {
        tfUsername = new JTextField();
        tfPassword = new JPasswordField();
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");
        CBAdmin = new JCheckBox();
        lAdmin = new JLabel("admin");
    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }

    public void setLoginButtonListener(ActionListener loginButtonListener) {
        btnLogin.addActionListener(loginButtonListener);
    }

    public void setRegisterButtonListener(ActionListener registerButtonListener) {
        btnRegister.addActionListener(registerButtonListener);
    }

    public Boolean isAdmin(){
        return CBAdmin.isSelected();
    }

}
