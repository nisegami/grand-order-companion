package world.arshad.grandordercompanion.data.user_data.sources;

import java.util.ArrayList;
import java.util.List;

import world.arshad.grandordercompanion.data.user_data.TrackedAscension;

/**
 * Created by arsha on 04/01/2018.
 */

public class UserDataSingleton {
    private static final UserDataSingleton ourInstance = new UserDataSingleton();

    private List<TrackedAscension> trackedAscensions;

    public static UserDataSingleton getInstance() {
        return ourInstance;
    }

    private UserDataSingleton() {
        trackedAscensions = new ArrayList<>();
        trackedAscensions.add(new TrackedAscension(91, 1));
        trackedAscensions.add(new TrackedAscension(91, 2));
        trackedAscensions.add(new TrackedAscension(91, 3));
        trackedAscensions.add(new TrackedAscension(91, 4));
        trackedAscensions.add(new TrackedAscension(86, 1));
        trackedAscensions.add(new TrackedAscension(86, 2));
        trackedAscensions.add(new TrackedAscension(86, 3));
        trackedAscensions.add(new TrackedAscension(86, 4));
        trackedAscensions.add(new TrackedAscension(3, 4));
    }

    public List<TrackedAscension> getTrackedAscensions() {
        return trackedAscensions;
    }

    public void setTrackedAscensions(List<TrackedAscension> trackedAscensions) {
        this.trackedAscensions = trackedAscensions;
    }
}
