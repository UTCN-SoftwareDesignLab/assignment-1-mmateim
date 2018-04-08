package repository.user;

import model.User;
import model.validation.Notification;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public interface UserRepository {

    public Long findIdByUsername(String username);

    public User findByUsername(String username);

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException;

    boolean save(User user);

    String findRoleByUsername(String username);

    void removeAll();

    Long findIdByCnp(String cnp);

    public Boolean delete(User user);

}
