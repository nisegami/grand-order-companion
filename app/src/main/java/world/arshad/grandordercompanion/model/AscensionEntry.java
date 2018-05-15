package world.arshad.grandordercompanion.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * This class represents a single entry in a given Ascension.
 * Created by arshad on 18/03/2018.
 */

@Entity(tableName = "ascension_entry",
        primaryKeys = {"servant_id", "ascension_number", "material"})
public class AscensionEntry {

    @ColumnInfo(name = "ascension_number")
    private int ascensionNumber;

    @ColumnInfo(name = "servant_id")
    private int servantId;

    @NonNull
    @ColumnInfo(name = "material")
    private Material material;

    @ColumnInfo(name = "count")
    private int count;

    public AscensionEntry(int servantId, int ascensionNumber, @NonNull Material material, int count) {
        this.ascensionNumber = ascensionNumber;
        this.servantId = servantId;
        this.material = material;
        this.count = count;
    }

    public int getAscensionNumber() {
        return ascensionNumber;
    }

    public void setAscensionNumber(int ascensionNumber) {
        this.ascensionNumber = ascensionNumber;
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

    @Override
    public String toString() {
        return String.format("Servant: %d; Asc#: %d; %s x%d", servantId, ascensionNumber, material.toString(), count);
    }

}

