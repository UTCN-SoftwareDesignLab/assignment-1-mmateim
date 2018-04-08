package repository.activity;

import DTO.ActivityDTO;
import model.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public List<ActivityDTO> findByDate(Date date) {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from activity WHERE date > '" + date.getTime() + "'");
            ResultSet rs = ps.executeQuery();
            List<ActivityDTO> activityDTOS = new ArrayList<>();
            while (rs.next()){
                ActivityDTO activityDTO = new ActivityDTO(rs.getString("description"), new Date(rs.getLong("date")));
                activityDTOS.add(activityDTO);
            }
            return activityDTOS;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long activityId) {
        return false;
    }
}
