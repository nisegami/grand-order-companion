package world.arshad.grandordercompanion.needed_ascension_materials;

import java.util.List;

import world.arshad.grandordercompanion.data.domain_data.Material;

/**
 * Created by arsha on 04/01/2018.
 */

public class NeededMaterialEntry {

    private Material material;
    private int count;
    private List<String> servantNames;

    public NeededMaterialEntry(Material material, int count, List<String> servantNames) {
        this.material = material;
        this.count = count;
        this.servantNames = servantNames;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getServantNames() {
        return servantNames;
    }

    public void setServantNames(List<String> servantNames) {
        this.servantNames = servantNames;
    }
}
