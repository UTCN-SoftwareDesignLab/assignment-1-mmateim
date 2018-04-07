package service.user;

import DTO.UserDTO;
import model.Role;
import model.User;
import repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceMySQL {

    private UserRepository userRepository;

    public UserServiceMySQL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll(){
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user : userList){
            String roles = "";
            for(Role role:user.getRoles()){
                System.out.println("role " + role);
                roles = roles + role.getRole() + " ";
            }
            userDTOS.add(new UserDTO(user.getUsername(), roles));
        }
        return userDTOS;
    }
}
