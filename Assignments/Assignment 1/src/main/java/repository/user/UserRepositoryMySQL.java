package repository.user;

import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ROLE;
import static database.Constants.Tables.USER;

/**
 * Created by Alex on 11/03/2017.
 */
public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public Long findIdByUsername(String username){
        try {
            Statement statement = connection.createStatement();
            String fetchId = "SELECT id FROM " + USER + " WHERE username = '" + username + "'";
            ResultSet idResultSet = statement.executeQuery(fetchId);
            if (idResultSet.next()) {
                return idResultSet.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        try {
            Statement statement = connection.createStatement();
            String fetchId = "SELECT * FROM " + USER + " WHERE username = '" + username + "'";
            ResultSet rs = statement.executeQuery(fetchId);
            if (rs.next()) {
                return userFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {

        List<User> userList = new ArrayList<>();
        try {
            ResultSet userResultSet = connection.prepareStatement("SELECT * FROM " + USER).executeQuery();
            while (userResultSet.next()){

                userList.add(userFromResultSet(userResultSet));
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User userFromResultSet(ResultSet userResultSet){
        try {
            return new UserBuilder()
                    .setId(userResultSet.getLong("id"))
                    .setUsername(userResultSet.getString("username"))
                    .setPassword(userResultSet.getString("password"))
                    .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(User user){
        try {
            connection.prepareStatement("DELETE from user WHERE id = '" + user.getId() + "'").executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String findRoleByUsername(String username){
            return (rightsRolesRepository.findRolesForUser(findIdByUsername(username))).get(0).getRole();
           // String fetchRoleSql = "SELECT role FROM bank.role JOIN bank.user_role ON bank.user_role.role_id = bank.role.id JOIN bank.user ON bank.user.id = bank.user_role.user_id WHERE bank.user.username='" + username + "'";
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)");
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long findIdByCnp(String cnp) {
        try {
            ResultSet rs = connection.prepareStatement("SELECT * FROM " + USER).executeQuery();
            if(rs.next()){
                return rs.getLong("id");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
