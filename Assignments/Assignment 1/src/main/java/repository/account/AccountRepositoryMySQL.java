package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ACCOUNT;

/**
 * Created by Alex on 07/03/2017.
 */
public class AccountRepositoryMySQL implements AccountRepository {

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?)");
            insertStatement.setString(1, account.getIban());
            insertStatement.setLong(2, account.getHolderID());
            insertStatement.setFloat(3, account.getBalance());
            insertStatement.executeUpdate();
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
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account findByIban(String iban) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + ACCOUNT + " WHERE `iban` = '" + iban + "'");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return getAccountFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> findByUser(Long userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + ACCOUNT + " WHERE `client_id` = '" + userId.toString() + "'");
            ResultSet rs = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                Account account = getAccountFromResultSet(rs);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder().setId(rs.getLong("id"))
                .setType(rs.getString("type"))
                .setIBAN(rs.getString("iban"))
                .setBalance(rs.getFloat("amount"))
                .setHolderID(rs.getLong("client_id"))
                .setCreationDate(new Date(rs.getInt("date")))
                .build();
    }

}
