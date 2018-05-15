package world.arshad.grandordercompanion.tracked_entries;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;

public class TrackedEntryViewModel extends ViewModel {
    private List<Object> items;

    /**
     * Call this at the start of any method that is working with the data.
     */
    private void fetchData(){
        if (null == items) {
            items = new ArrayList<>();
            for (Servant servant : ServantRepository.getInstance().getAllServants()) {
                List<Ascension> trackedAscensions = servant.getAscensions(Ascension.TRACKED);
                List<SkillUp> trackedSkillUps = new ArrayList<>();
                trackedSkillUps.addAll(servant.getSkillUps(1, SkillUp.TRACKED));
                trackedSkillUps.addAll(servant.getSkillUps(2, SkillUp.TRACKED));
                trackedSkillUps.addAll(servant.getSkillUps(3, SkillUp.TRACKED));

                if (trackedAscensions.isEmpty() && trackedSkillUps.isEmpty()) {
                    continue;
                }

                items.add(servant);
                items.addAll(trackedAscensions);
                items.addAll(trackedSkillUps);
            }
        }
    }

    public List<Object> getItems() {
        fetchData();
        return items;
    }

    public void refreshData() {
        this.items = null;
    }
}
