package world.arshad.grandordercompanion.needed_ascension_materials;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import world.arshad.grandordercompanion.data.domain_data.AscensionEntry;
import world.arshad.grandordercompanion.data.domain_data.Material;
import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;
import world.arshad.grandordercompanion.data.user_data.TrackedAscension;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataSingleton;

/**
 * Created by arsha on 04/01/2018.
 */

public class NeededAscensionMaterialViewModel extends AndroidViewModel {
    private List<NeededMaterialEntry> entries;
    private final Context mContext;

    public NeededAscensionMaterialViewModel(
            Application context) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
    }

    public List<NeededMaterialEntry> getEntries() {
        if (entries == null) {

            entries = new ArrayList<>();

            Map<Material, Integer> counts = new HashMap<>();
            Map<Material, List<String>> servants = new HashMap<>();

            List<AscensionEntry> ascensionEntries = DomainDataSingleton.getInstance().getAscensionEntries();
            List<TrackedAscension> trackedAscensions = UserDataSingleton.getInstance().getTrackedAscensions();

            for (AscensionEntry entry : ascensionEntries) {
                if (trackedAscensions.contains(new TrackedAscension(entry.getServantId(), entry.getAscensionNumber()))) {
                    // We care about this entry
                    if (counts.containsKey(entry.getMaterial())) {
                        // Update
                        counts.put(entry.getMaterial(), entry.getCount() + counts.get(entry.getMaterial()));
                    } else {
                        // Add
                        counts.put(entry.getMaterial(), entry.getCount());
                        servants.put(entry.getMaterial(), new ArrayList<String>());
                    }
                    servants.get(entry.getMaterial()).add(String.format("%s | #%d | %d", DomainDataSingleton.getInstance().getServantInfo(entry.getServantId()).getName(), entry.getAscensionNumber(), entry.getCount()));
                }

            }

            for (Material material : counts.keySet()) {
                entries.add(new NeededMaterialEntry(material, counts.get(material), servants.get(material)));
            }

        }

        return entries;
    }
}
