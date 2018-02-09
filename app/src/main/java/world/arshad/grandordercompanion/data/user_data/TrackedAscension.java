package world.arshad.grandordercompanion.data.user_data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arshad on 04/01/2018.
 */

@Entity(tableName = "tracked_ascension", primaryKeys = {"servant_id", "ascension_number"})
public class TrackedAscension implements Parcelable {

    @ColumnInfo(name = "servant_id")
    private int servantId;

    @ColumnInfo(name = "ascension_number")
    private int ascensionNumber;

    public TrackedAscension(int servantId, int ascensionNumber) {
        this.servantId = servantId;
        this.ascensionNumber = ascensionNumber;
    }

    public int getServantId() {
        return servantId;
    }

    public void setServantId(int servantId) {
        this.servantId = servantId;
    }

    public int getAscensionNumber() {
        return ascensionNumber;
    }

    public void setAscensionNumber(int ascensionNumber) {
        this.ascensionNumber = ascensionNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackedAscension)) return false;

        TrackedAscension that = (TrackedAscension) o;

        if (servantId != that.servantId) return false;
        return ascensionNumber == that.ascensionNumber;
    }

    @Override
    public int hashCode() {
        int result = servantId;
        result = 31 * result + ascensionNumber;
        return result;
    }

    protected TrackedAscension(Parcel in) {
        servantId = in.readInt();
        ascensionNumber = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(servantId);
        dest.writeInt(ascensionNumber);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TrackedAscension> CREATOR = new Parcelable.Creator<TrackedAscension>() {
        @Override
        public TrackedAscension createFromParcel(Parcel in) {
            return new TrackedAscension(in);
        }

        @Override
        public TrackedAscension[] newArray(int size) {
            return new TrackedAscension[size];
        }
    };
}
