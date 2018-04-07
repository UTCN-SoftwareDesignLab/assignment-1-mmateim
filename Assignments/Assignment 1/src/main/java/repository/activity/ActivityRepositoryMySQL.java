package repository.activity;

import model.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivityRepositoryMySQL implements ActivityRepository {

    public final Connection connection;

    public ActivityRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(Activity activity) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO activity values (null, ?, ?, ?)");
            insertStatement.setLong(1, activity.getDate().getTime());
            insertStatement.setLong(2, activity.getUserId());
            insertStatement.setString(3, activity.getDescription());
            insertStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long activityId) {
        return false;
    }
}
