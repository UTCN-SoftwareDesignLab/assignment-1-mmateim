package service.activity;

import DTO.ActivityDTO;
import model.Activity;
import repository.activity.ActivityRepository;

import java.util.Date;
import java.util.List;

public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Boolean add(Activity activity) {
        return activityRepository.add(activity);
    }

    @Override
    public List<ActivityDTO> findByDate(Date date) {
        return activityRepository.findByDate(date);
    }
}
