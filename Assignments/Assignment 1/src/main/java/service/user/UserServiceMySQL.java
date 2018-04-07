package service.user;

import model.User;
import repository.user.UserRepository;

import java.util.List;

public class UserServiceMySQL {

    private UserRepository userRepository;

    public UserServiceMySQL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
