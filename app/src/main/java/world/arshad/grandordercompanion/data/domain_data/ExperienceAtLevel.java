package world.arshad.grandordercompanion.data.domain_data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExperienceAtLevel implements Parcelable
{

    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("total_exp")
    @Expose
    private Integer totalExp;
    public final static Parcelable.Creator<ExperienceAtLevel> CREATOR = new Creator<ExperienceAtLevel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ExperienceAtLevel createFromParcel(Parcel in) {
            return new ExperienceAtLevel(in);
        }

        public ExperienceAtLevel[] newArray(int size) {
            return (new ExperienceAtLevel[size]);
        }

    }
            ;

    protected ExperienceAtLevel(Parcel in) {
        this.level = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalExp = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ExperienceAtLevel() {
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(Integer totalExp) {
        this.totalExp = totalExp;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(level);
        dest.writeValue(totalExp);
    }

    public int describeContents() {
        return  0;
    }

}