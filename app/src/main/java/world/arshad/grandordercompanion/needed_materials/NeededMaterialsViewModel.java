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

    private HashMap<Material, NeededMaterialEntry> items;

    /**
     * Call this at the start of any method that is working with the data.
     */
    public void fetchData() {
        if (items == null) {
            // Use LinkedHashMap to guarantee key ordering for populating RecyclerView
            items = new LinkedHashMap<>();

            List<Ascension> ascensions = Model.getInstance().getDatabase().servantDao().getTrackedAscensions();
            for (Ascension ascension : ascensions) {
                for (AscensionEntry ascensionEntry : ascension.getAscensionEntries()) {
                    if (items.containsKey(ascensionEntry.getMaterial())) {
                        NeededMaterialEntry existingEntry = items.get(ascensionEntry.getMaterial());
                        NeededMaterialEntry newEntry = new NeededMaterialEntry(existingEntry.getCount() + ascensionEntry.getCount(), existingEntry.getText() + String.format("\n%s | %s", Model.getInstance().getDatabase().servantDao().getServantNameFromId(ascensionEntry.getServantId()), ascension));
                        items.put(ascensionEntry.getMaterial(), newEntry);
                    } else {
                        NeededMaterialEntry newEntry = new NeededMaterialEntry(ascensionEntry.getCount(), String.format("%s | %s", Model.getInstance().getDatabase().servantDao().getServantNameFromId(ascensionEntry.getServantId()), ascension));
                        items.put(ascensionEntry.getMaterial(), newEntry);
                    }
                }
            }

            List<SkillUp> skillUps = Model.getInstance().getDatabase().servantDao().getTrackedSkillUps();
            for (SkillUp skillUp : skillUps) {
                for (SkillUpEntry skillUpEntry : skillUp.getSkillUpEntries()) {
                    if (items.containsKey(skillUpEntry.getMaterial())) {
                        NeededMaterialEntry existingEntry = items.get(skillUpEntry.getMaterial());
                        NeededMaterialEntry newEntry = new NeededMaterialEntry(existingEntry.getCount() + skillUpEntry.getCount(), existingEntry.getText() + String.format("\n%s | %s", Model.getInstance().getDatabase().servantDao().getServantNameFromId(skillUpEntry.getServantId()), skillUp));
                        items.put(skillUpEntry.getMaterial(), newEntry);
                    } else {
                        NeededMaterialEntry newEntry = new NeededMaterialEntry(skillUpEntry.getCount(), String.format("%s | %s", Model.getInstance().getDatabase().servantDao().getServantNameFromId(skillUpEntry.getServantId()), skillUp));
                        items.put(skillUpEntry.getMaterial(), newEntry);
                    }
                }
            }
        }
    }

    public void refreshData() {
        this.items = null;
    }

    public HashMap<Material, NeededMaterialEntry> getItems() {
        fetchData();
        return items;
    }

}
