package world.arshad.grandordercompanion.data.domain_data;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.user_data.TrackedSkillUp;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataSingleton;

public class SkillUpEntry extends Entry implements Parcelable
{

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("dest_skill_level")
    @Expose
    private int destSkillLevel;
    @SerializedName("material_id")
    @Expose
    private Material material;
    @SerializedName("servant_id")
    @Expose
    private int servantId;
    public final static Parcelable.Creator<SkillUpEntry> CREATOR = new Creator<SkillUpEntry>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SkillUpEntry createFromParcel(Parcel in) {
            return new SkillUpEntry(in);
        }

        public SkillUpEntry[] newArray(int size) {
            return (new SkillUpEntry[size]);
        }

    }
            ;

    protected SkillUpEntry(Parcel in) {
        this.count = in.readInt();
        this.destSkillLevel = in.readInt();
        this.material = ((Material) in.readSerializable());
        this.servantId = in.readInt();
    }

    public SkillUpEntry() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDestSkillLevel() {
        return destSkillLevel;
    }

    public void setDestSkillLevel(int destSkillLevel) {
        this.destSkillLevel = destSkillLevel;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material materialName) {
        this.material = materialName;
    }

    public int getServantId() {
        return servantId;
    }

    public void setServantId(int servantId) {
        this.servantId = servantId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeInt(destSkillLevel);
        dest.writeSerializable(material);
        dest.writeInt(servantId);
    }

    public int describeContents() {
        return  0;
    }
}
