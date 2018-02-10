package world.arshad.grandordercompanion.tracked_skill_up_management;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import java.util.List;

import world.arshad.grandordercompanion.data.user_data.TrackedSkillUp;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataSingleton;

/**
 * Created by arshad on 09/02/2018.
 */

public class TrackedSkillUpsViewModel extends AndroidViewModel {
    private List<TrackedSkillUp> trackedSkillUps;
    private final Context mContext;

    public TrackedSkillUpsViewModel(
            Application context) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
    }

    public List<TrackedSkillUp> getTrackedSkillUps() {
        trackedSkillUps = UserDataSingleton.getInstance().getRoomDB().trackedSkillUpDao().getAll();
        return trackedSkillUps;
    }
}
