package world.arshad.grandordercompanion.data.domain_data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillUpEntry implements Parcelable
{

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("dest_skill_level")
    @Expose
    private int destSkillLevel;
    @SerializedName("material_name")
    @Expose
    private String materialName;
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
        this.materialName = ((String) in.readValue((String.class.getClassLoader())));
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

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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
        dest.writeString(materialName);
        dest.writeInt(servantId);
    }

    public int describeContents() {
        return  0;
    }

}
