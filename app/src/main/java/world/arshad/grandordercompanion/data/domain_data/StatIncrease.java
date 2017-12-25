
package world.arshad.grandordercompanion.data.domain_data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatIncrease implements Parcelable
{

    @SerializedName("dest_level")
    @Expose
    private int destLevel;
    @SerializedName("growth_curve")
    @Expose
    private GrowthCurve growthCurve;
    @SerializedName("multiplier")
    @Expose
    private double multiplier;
    @SerializedName("rarity")
    @Expose
    private int rarity;
    public final static Parcelable.Creator<StatIncrease> CREATOR = new Creator<StatIncrease>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatIncrease createFromParcel(Parcel in) {
            return new StatIncrease(in);
        }

        public StatIncrease[] newArray(int size) {
            return (new StatIncrease[size]);
        }

    }
            ;

    protected StatIncrease(Parcel in) {
        this.destLevel = in.readInt();
        this.growthCurve = ((GrowthCurve) in.readSerializable());
        this.multiplier = in.readDouble();
        this.rarity = in.readInt();
    }

    public StatIncrease() {
    }

    public int getDestLevel() {
        return destLevel;
    }

    public void setDestLevel(int destLevel) {
        this.destLevel = destLevel;
    }

    public GrowthCurve getGrowthCurve() {
        return growthCurve;
    }

    public void setGrowthCurve(GrowthCurve growthCurve) {
        this.growthCurve = growthCurve;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(destLevel);
        dest.writeSerializable(growthCurve);
        dest.writeDouble(multiplier);
        dest.writeInt(rarity);
    }

    public int describeContents() {
        return  0;
    }

}
