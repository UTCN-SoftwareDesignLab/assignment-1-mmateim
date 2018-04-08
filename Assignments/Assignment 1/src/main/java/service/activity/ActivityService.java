package service.activity;

import DTO.ActivityDTO;
import model.Activity;

import java.util.Date;
import java.util.List;

public interface ActivityService {

    Boolean add(Activity activity);
    List<ActivityDTO> findByDate(Date date);
}
