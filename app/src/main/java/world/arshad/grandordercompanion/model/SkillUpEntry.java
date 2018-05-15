package world.arshad.grandordercompanion.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * This class represents a single entry in a given skill up.
 * Created by arsha on 18/03/2018.
 */

@Entity(tableName = "skill_up_entry",
        primaryKeys = {"servant_id", "dest_skill_level", "material"})
public class SkillUpEntry {

    @ColumnInfo(name = "dest_skill_level")
    private int destSkillLevel;

    @ColumnInfo(name = "servant_id")
    private int servantId;

    @NonNull
    @ColumnInfo(name = "material")
    private Material material;

    @ColumnInfo(name = "count")
    private int count;

    public SkillUpEntry(int servantId, int destSkillLevel, @NonNull Material material, int count) {
        this.destSkillLevel = destSkillLevel;
        this.servantId = servantId;
        this.material = material;
        this.count = count;
    }

    public int getDestSkillLevel() {
        return destSkillLevel;
    }

    public void setDestSkillLevel(int destSkillLevel) {
        this.destSkillLevel = destSkillLevel;
    }

    public int getServantId() {
        return servantId;
    }

    public void setServantId(int servantId) {
        this.servantId = servantId;
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
}
