package repository.client;

import model.Client;
import model.builder.ClientBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;

public class ClientRepositoryMySQL implements ClientRepository{

    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + CLIENT);
            ResultSet rs = preparedStatement.executeQuery();
            List<Client> clientList = new ArrayList<>();
            while (rs.next()) {
                Client client = new ClientBuilder()
                        .setID(rs.getLong("id"))
                        .setName(rs.getString("name"))
                        .setAdress(rs.getString("address"))
                        .setCNP(rs.getString("CNP"))
                        .build();
                clientList.add(client);
            }
            return clientList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Client client) {
        return false;
    }

    @Override
    public Client findByCNP(String CNP) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + CLIENT + " WHERE CNP = '" + CNP + "'");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Client client = new ClientBuilder()
                        .setID(rs.getLong("id"))
                        .setName(rs.getString("name"))
                        .setAdress(rs.getString("address"))
                        .setCNP(rs.getString("CNP"))
                        .build();
                return client;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        try {
            connection.prepareStatement("DELETE from client WHERE id = '" + id + "'").executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void removeAll() {

    }

    @Override
    public List<Client> findByName(String name) {
        List<Client> clientList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + CLIENT + " WHERE name LIKE '%" + name + "%'" );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Client client = new ClientBuilder()
                        .setID(rs.getLong("id"))
                        .setName(name)
                        .setAdress(rs.getString("address"))
                        .setCNP(rs.getString("CNP"))
                        .build();
                clientList.add(client);
            }
            return clientList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
