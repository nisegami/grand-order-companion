package world.arshad.grandordercompanion.data.user_data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arshad on 04/01/2018.
 */

@Entity(tableName = "tracked_skill_up", primaryKeys = {"servant_id", "skill_dest_level", "skill_number"})
public class TrackedSkillUp implements Parcelable {

    @ColumnInfo(name = "servant_id")
    private int servantId;

    @ColumnInfo(name = "skill_dest_level")
    private int skillDestLevel;

    @ColumnInfo(name = "skill_number")
    private int skillNumber;

    public TrackedSkillUp(int servantId, int skillDestLevel, int skillNumber) {
        this.servantId = servantId;
        this.skillDestLevel = skillDestLevel;
        this.skillNumber = skillNumber;
    }

    public int getServantId() {
        return servantId;
    }

    public void setServantId(int servantId) {
        this.servantId = servantId;
    }

    public int getSkillDestLevel() {
        return skillDestLevel;
    }

    public void setSkillDestLevel(int skillDestLevel) {
        this.skillDestLevel = skillDestLevel;
    }

    public int getSkillNumber() {
        return skillNumber;
    }

    public void setSkillNumber(int skillNumber) {
        this.skillNumber = skillNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackedSkillUp)) return false;

        TrackedSkillUp that = (TrackedSkillUp) o;

        if (servantId != that.servantId) return false;
        if (skillDestLevel != that.skillDestLevel) return false;
        return skillNumber == that.skillNumber;
    }

    @Override
    public int hashCode() {
        int result = servantId;
        result = 31 * result + skillDestLevel;
        result = 31 * result + skillNumber;
        return result;
    }

    protected TrackedSkillUp(Parcel in) {
        servantId = in.readInt();
        skillDestLevel = in.readInt();
        skillNumber = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(servantId);
        dest.writeInt(skillDestLevel);
        dest.writeInt(skillNumber);
    }

    @SuppressWarnings("unused")
    public static final Creator<TrackedSkillUp> CREATOR = new Creator<TrackedSkillUp>() {
        @Override
        public TrackedSkillUp createFromParcel(Parcel in) {
            return new TrackedSkillUp(in);
        }

        @Override
        public TrackedSkillUp[] newArray(int size) {
            return new TrackedSkillUp[size];
        }
    };
}
