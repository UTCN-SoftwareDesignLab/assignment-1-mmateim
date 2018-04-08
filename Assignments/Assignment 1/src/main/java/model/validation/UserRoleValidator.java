package model.validation;

import database.Constants;

public class UserRoleValidator {

    String role;

    public UserRoleValidator(String role) {
        this.role = role;
    }

    public Boolean validate(){
        for (String s : Constants.Roles.ROLES){
            if(s.equals(role))
                return true;
        }
        return false;
    }
}
