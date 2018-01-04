package world.arshad.grandordercompanion.data.domain_data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;

public class ServantInfo implements Parcelable {

    @SerializedName("base_attack")
    @Expose
    private int baseAttack;
    @SerializedName("base_hp")
    @Expose
    private int baseHp;
    @SerializedName("class")
    @Expose
    private ServantClass servantClass;
    @SerializedName("cost")
    @Expose
    private int cost;
    @SerializedName("growth_curve")
    @Expose
    private GrowthCurve growthCurve;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("max_attack")
    @Expose
    private int maxAttack;
    @SerializedName("max_hp")
    @Expose
    private int maxHp;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rarity")
    @Expose
    private int rarity;

    private List<List<AscensionEntry>> ascensionEntries = null;
    private List<List<SkillUpEntry>> skillUpEntries = null;

    public final static Parcelable.Creator<ServantInfo> CREATOR = new Creator<ServantInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ServantInfo createFromParcel(Parcel in) {
            return new ServantInfo(in);
        }

        public ServantInfo[] newArray(int size) {
            return (new ServantInfo[size]);
        }

    }
            ;

    protected ServantInfo(Parcel in) {
        this.baseAttack = in.readInt();
        this.baseHp = in.readInt();
        this.servantClass = ((ServantClass) in.readSerializable());
        this.cost = in.readInt();
        this.growthCurve = ((GrowthCurve) in.readSerializable());
        this.id = in.readInt();
        this.maxAttack = in.readInt();
        this.maxHp = in.readInt();
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.rarity = in.readInt();
    }

    public ServantInfo() {
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public ServantClass getServantClass() {
        return servantClass;
    }

    public void setServantClass(ServantClass servantClass) {
        this.servantClass = servantClass;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public GrowthCurve getGrowthCurve() {
        return growthCurve;
    }

    public void setGrowthCurve(GrowthCurve growthCurve) {
        this.growthCurve = growthCurve;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(baseAttack);
        dest.writeInt(baseHp);
        dest.writeSerializable(servantClass);
        dest.writeInt(cost);
        dest.writeSerializable(growthCurve);
        dest.writeInt(id);
        dest.writeInt(maxAttack);
        dest.writeInt(maxHp);
        dest.writeValue(name);
        dest.writeInt(rarity);
    }

    public int describeContents() {
        return  0;
    }

    public String getThumbnailURL() {
        return String.format("img/servants/%d_thumb.png", id);
    }

    public String getFullImageURL() {
        return String.format("img/servants/%d_full.png", id);
    }

    public List<List<AscensionEntry>> getAscensionEntries() {
        if (ascensionEntries == null) {
            ascensionEntries = new ArrayList<>();
            ascensionEntries.add(new ArrayList<AscensionEntry>());
            ascensionEntries.add(new ArrayList<AscensionEntry>());
            ascensionEntries.add(new ArrayList<AscensionEntry>());
            ascensionEntries.add(new ArrayList<AscensionEntry>());

            for (AscensionEntry entry : DomainDataSingleton.getInstance().getAscensionEntries(id)) {
                ascensionEntries.get(entry.getAscensionNumber() - 1).add(entry);
            }
        }

        return ascensionEntries;
    }

    public List<List<SkillUpEntry>> getSkillUpEntries() {
        if (skillUpEntries == null) {
            skillUpEntries = new ArrayList<>();
            skillUpEntries.add(new ArrayList<SkillUpEntry>());
            skillUpEntries.add(new ArrayList<SkillUpEntry>());
            skillUpEntries.add(new ArrayList<SkillUpEntry>());
            skillUpEntries.add(new ArrayList<SkillUpEntry>());
            skillUpEntries.add(new ArrayList<SkillUpEntry>());
            skillUpEntries.add(new ArrayList<SkillUpEntry>());
            skillUpEntries.add(new ArrayList<SkillUpEntry>());
            skillUpEntries.add(new ArrayList<SkillUpEntry>());
            skillUpEntries.add(new ArrayList<SkillUpEntry>());

            for (SkillUpEntry entry : DomainDataSingleton.getInstance().getSkillUpEntries(id)) {
                skillUpEntries.get(entry.getDestSkillLevel() - 2).add(entry);
            }
        }

        return skillUpEntries;
    }
}