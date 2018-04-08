package service.user;

import DTO.UserDTO;
import model.Role;
import model.User;
import model.validation.UserRoleValidator;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl {

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public UserServiceImpl(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
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

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean delete(User user){
        Boolean flag = userRepository.delete(user);
        if(flag){
            rightsRolesRepository.removeUserRole(user.getId());
        }
        return flag;
    }

    public boolean update(Long id, String role){
        UserRoleValidator validator = new UserRoleValidator(role);
        if(validator.validate()){
            return rightsRolesRepository.updateUserRole(id, role);
        }
        else {
            System.out.println("Incorrect role input");
            return false;
        }
    }
}
