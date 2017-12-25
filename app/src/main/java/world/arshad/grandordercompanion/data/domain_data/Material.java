package world.arshad.grandordercompanion.data.domain_data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Material implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<Material> CREATOR = new Creator<Material>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }

        public Material[] newArray(int size) {
            return (new Material[size]);
        }

    }
            ;

    protected Material(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Material() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
