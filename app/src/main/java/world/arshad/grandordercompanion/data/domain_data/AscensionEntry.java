package world.arshad.grandordercompanion.data.domain_data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AscensionEntry implements Parcelable
{

    @SerializedName("ascension_number")
    @Expose
    private int ascensionNumber;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("material_name")
    @Expose
    private String materialName;
    @SerializedName("servant_id")
    @Expose
    private int servantId;
    public final static Parcelable.Creator<AscensionEntry> CREATOR = new Creator<AscensionEntry>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AscensionEntry createFromParcel(Parcel in) {
            return new AscensionEntry(in);
        }

        public AscensionEntry[] newArray(int size) {
            return (new AscensionEntry[size]);
        }

    }
            ;

    protected AscensionEntry(Parcel in) {
        this.ascensionNumber = in.readInt();
        this.count =  in.readInt();
        this.materialName = ((String) in.readValue((String.class.getClassLoader())));
        this.servantId = in.readInt();
    }

    public AscensionEntry() {
    }

    public int getAscensionNumber() {
        return ascensionNumber;
    }

    public void setAscensionNumber(Integer ascensionNumber) {
        this.ascensionNumber = ascensionNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
        dest.writeInt(ascensionNumber);
        dest.writeInt(count);
        dest.writeString(materialName);
        dest.writeInt(servantId);
    }

    public int describeContents() {
        return  0;
    }

}