package world.arshad.grandordercompanion.tracked_ascension_management;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import java.util.List;

import world.arshad.grandordercompanion.data.user_data.TrackedAscension;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataSingleton;

/**
 * Created by arshad on 09/02/2018.
 */

public class TrackedAscensionsViewModel extends AndroidViewModel {
    private List<TrackedAscension> trackedAscensions;
    private final Context mContext;

    public TrackedAscensionsViewModel(
            Application context) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
    }

    public List<TrackedAscension> getTrackedAscensions() {
        trackedAscensions = UserDataSingleton.getInstance().getRoomDB().trackedAscensionDao().getAll();
        return trackedAscensions;
    }
}
