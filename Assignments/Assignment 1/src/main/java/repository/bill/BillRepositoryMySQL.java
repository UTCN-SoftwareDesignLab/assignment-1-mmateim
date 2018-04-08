package repository.bill;

import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillRepositoryMySQL implements BillRepository {

    Connection connection;

    public BillRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean addBill(Bill bill) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO bill values (null, ?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, bill.getInformation());
            insertStatement.setFloat(2, bill.getAmount());
            insertStatement.setLong(3, bill.getDate().getTime());
            insertStatement.setLong(4, bill.getEmployeeId());
            insertStatement.setLong(5, bill.getAccountId());
            insertStatement.setLong(6, bill.getClientId());
            insertStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
