package controller;


import DTO.ActivityDTO;
import DTO.UserDTO;
import model.User;
import service.FileGenerator;
import service.activity.ActivityService;
import service.user.UserServiceImpl;
import view.AdminChooseAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static database.Constants.Controller.ADMINACT_ADMIN;
import static database.Constants.Controller.ADMINACT_CLIENT;

public class AdminActionController extends java.util.Observable {

    AdminChooseAction adminChooseAction;
    UserServiceImpl userService;
    ActivityService activityService;

    public AdminActionController(AdminChooseAction adminChooseAction, UserServiceImpl userService, ActivityService activityService) {
        this.adminChooseAction = adminChooseAction;
        this.userService = userService;
        this.activityService = activityService;
        adminChooseAction.setClientButtonListener(new ClientOpListener());
        adminChooseAction.setEmployeeButtonListener(new EmployeeOpListener());
        adminChooseAction.setReportListener(new reportListener());
    }

    public void populateComboBox() {
        List<UserDTO> userList = userService.findAll();
        List<String> usernames = new ArrayList<>();
        for(UserDTO user : userList){
            usernames.add(user.getUsername());
        }
        adminChooseAction.populateComboBox(usernames);
    }

    private class reportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String username = adminChooseAction.getSelectedUser();
            Date date = adminChooseAction.getDate();
            if(username == null || date == null) {
                JOptionPane.showMessageDialog(adminChooseAction.getContentPane(), "Incorrect input");
            }
            else {
                User user = userService.findByUsername(username);
                List<ActivityDTO> activities = activityService.findByDate(date);
                FileGenerator fileGenerator = new FileGenerator(user.getId() + user.getUsername() + ".txt", activities);
                if(fileGenerator.generate()){
                    JOptionPane.showMessageDialog(adminChooseAction.getContentPane(), "Report generated");
                }
                else
                {
                    JOptionPane.showMessageDialog(adminChooseAction.getContentPane(), "Error");
                }
            }
        }
    }

    private class ClientOpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(ADMINACT_CLIENT);
        }
    }

    private class EmployeeOpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(ADMINACT_ADMIN);
        }
    }

    public void setVisible(Boolean flag){
        adminChooseAction.setVisible(flag);
    }
}