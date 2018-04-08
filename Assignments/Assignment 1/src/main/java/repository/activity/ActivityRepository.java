package repository.activity;

import DTO.ActivityDTO;
import model.Activity;

import java.util.Date;
import java.util.List;

public interface ActivityRepository {

    boolean add(Activity activity);

    boolean delete(Long activityId);

    List<ActivityDTO> findByDate(Date date);
}
