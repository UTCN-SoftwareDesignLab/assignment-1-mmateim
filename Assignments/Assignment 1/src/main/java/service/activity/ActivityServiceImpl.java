package service.activity;

import model.Activity;
import repository.activity.ActivityRepository;

public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Boolean add(Activity activity) {
        return activityRepository.add(activity);
    }
}
