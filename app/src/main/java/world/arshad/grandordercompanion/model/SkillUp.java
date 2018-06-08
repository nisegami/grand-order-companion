package world.arshad.grandordercompanion.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a given skill up.
 * Created by arshad on 18/03/2018.
 */

@Entity(tableName = "skill_up",
        primaryKeys = {"skill_number", "dest_skill_level", "servant_id"})
public class SkillUp {
    public static final int DONTCARE = 0;
    public static final int TRACKED = 1;
    public static final int COMPLETED = 2;

    @ColumnInfo(name = "skill_number")
    private int skillNumber;

    @ColumnInfo(name = "dest_skill_level")
    private int destSkillLevel;

    @ColumnInfo(name = "servant_id")
    private int servantId;

    @ColumnInfo(name = "status")
    private int status;

    @Ignore
    private String skillName = null;

    @Ignore
    private List<SkillUpEntry> skillUpEntries;

    public SkillUp(int skillNumber, int destSkillLevel, int servantId, int status) {
        this.skillNumber = skillNumber;
        this.destSkillLevel = destSkillLevel;
        this.servantId = servantId;
        this.status = status;
    }

    public int getSkillNumber() {
        return skillNumber;
    }

    public void setSkillNumber(int skillNumber) {
        this.skillNumber = skillNumber;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SkillUpEntry> getSkillUpEntries() {
        return skillUpEntries;
    }

    public void setSkillUpEntries(List<SkillUpEntry> skillUpEntries) {
        this.skillUpEntries = skillUpEntries;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getTitle() {
        return toString();
    }

    @Override
    public String toString() {
        if (null != skillName) {
            return String.format("%s (%d): %d ➜ %d", skillName, skillNumber, destSkillLevel - 1, destSkillLevel);
        } else {
            return String.format("%d: %d ➜ %d", skillNumber, destSkillLevel - 1, destSkillLevel);
        }
    }

    public String contents() {
        ArrayList<String> contents = new ArrayList<>();
        int i = 0;
        for (SkillUpEntry skillUpEntry : skillUpEntries) {
            contents.add(String.format("%s x%d", skillUpEntry.getMaterial(), skillUpEntry.getCount()));
        }
        return String.join("\n", contents);
    }
}
