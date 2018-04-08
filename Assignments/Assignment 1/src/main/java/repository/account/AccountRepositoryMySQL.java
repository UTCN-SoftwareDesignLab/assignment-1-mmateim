package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
            ResultSet rs = connection.prepareStatement("SELECT * FROM account").executeQuery();
            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Boolean delete(Long id) {
        try {
            connection.prepareStatement("DELETE from account WHERE id = '" + id.toString() + "'").executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Long id, Account account) {
        try {
            PreparedStatement updateSt = connection.prepareStatement("UPDATE account set client_id = ?, type = ?, iban = ?, amount = ? where id = '" + id.toString() + "'");
            updateSt.setLong(1, account.getHolderID() );
            updateSt.setString(2, account.getType());
            updateSt.setString(3, account.getIban());
            updateSt.setFloat(4,account.getBalance());
            updateSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, account.getIban());
            insertStatement.setString(2, account.getType());
            insertStatement.setLong(3, new Date().getTime());
            insertStatement.setFloat(4, account.getBalance());
            insertStatement.setLong(5, account.getHolderID());

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
                .setCreationDate(new Date(rs.getLong("date")))
                .build();
    }

}
