package world.arshad.grandordercompanion.needed_materials;

import android.arch.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import world.arshad.grandordercompanion.data.Model;
import world.arshad.grandordercompanion.data.Ascension;
import world.arshad.grandordercompanion.data.AscensionEntry;
import world.arshad.grandordercompanion.data.Material;
import world.arshad.grandordercompanion.data.SkillUp;
import world.arshad.grandordercompanion.data.SkillUpEntry;

/**
 * Created by arsha on 22/03/2018.
 */

public class NeededMaterialsViewModel extends ViewModel {

    private HashMap<Material, Integer> materialCounts;
    private HashMap<Material, String> materialStrings;

    /**
     * Call this at the start of any method that is working with the data.
     */
    public void fetchData() {
        if (materialCounts == null) {
            // Use LinkedHashMap to guarantee key ordering for populating RecyclerView
            materialCounts = new LinkedHashMap<>();
            materialStrings = new LinkedHashMap<>();

            List<Ascension> ascensions = Model.getInstance().getDatabase().servantDao().getTrackedAscensions();
            for (Ascension ascension : ascensions) {
                for (AscensionEntry ascensionEntry : ascension.getAscensionEntries()) {
                    if (materialCounts.containsKey(ascensionEntry.getMaterial())) {
                        materialCounts.put(ascensionEntry.getMaterial(), ascensionEntry.getCount() + materialCounts.get(ascensionEntry.getMaterial()));
                        materialStrings.put(ascensionEntry.getMaterial(), materialStrings.get(ascensionEntry.getMaterial()) + String.format("\n%s | %s", Model.getInstance().getDatabase().servantDao().getServantNameFromId(ascensionEntry.getServantId()), ascension));
                    } else {
                        materialCounts.put(ascensionEntry.getMaterial(), ascensionEntry.getCount());
                        materialStrings.put(ascensionEntry.getMaterial(), String.format("%s | %s", Model.getInstance().getDatabase().servantDao().getServantNameFromId(ascensionEntry.getServantId()), ascension));
                    }
                }
            }

            List<SkillUp> skillUps = Model.getInstance().getDatabase().servantDao().getTrackedSkillUps();
            for (SkillUp skillUp : skillUps) {
                for (SkillUpEntry skillUpEntry : skillUp.getSkillUpEntries()) {
                    if (materialCounts.containsKey(skillUpEntry.getMaterial())) {
                        materialCounts.put(skillUpEntry.getMaterial(), skillUpEntry.getCount() + materialCounts.get(skillUpEntry.getMaterial()));
                        materialStrings.put(skillUpEntry.getMaterial(), materialStrings.get(skillUpEntry.getMaterial()) + String.format("\n%s | %s", Model.getInstance().getDatabase().servantDao().getServantNameFromId(skillUpEntry.getServantId()), skillUp));
                    } else {
                        materialCounts.put(skillUpEntry.getMaterial(), skillUpEntry.getCount());
                        materialStrings.put(skillUpEntry.getMaterial(), String.format("%s | %s", Model.getInstance().getDatabase().servantDao().getServantNameFromId(skillUpEntry.getServantId()), skillUp));
                    }
                }
            }
        }
    }

    public HashMap<Material, Integer> getCounts() {
        fetchData();
        return materialCounts;
    }

    public HashMap<Material, String> getStrings() {
        fetchData();
        return materialStrings;
    }
}
