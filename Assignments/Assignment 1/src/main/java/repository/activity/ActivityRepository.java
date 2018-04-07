package repository.activity;

import model.Activity;

public interface ActivityRepository {

    public boolean add(Activity activity);

    public boolean delete(Long activityId);
}
