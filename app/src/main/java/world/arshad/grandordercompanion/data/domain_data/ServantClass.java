package world.arshad.grandordercompanion.data.domain_data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arshad on 22/12/2017.
 */

public enum ServantClass {

    @SerializedName("shielder") SHIELDER("Shielder"),
    @SerializedName("saber") SABER("Saber"),
    @SerializedName("lancer") LANCER("Lancer"),
    @SerializedName("archer") ARCHER("Archer"),
    @SerializedName("rider") RIDER("Rider"),
    @SerializedName("caster") CASTER("Caster"),
    @SerializedName("assassin") ASSASSIN("Assassin"),
    @SerializedName("berserker") BERSERKER("Berserker"),
    @SerializedName("ruler") RULER("Ruler"),
    @SerializedName("avenger") AVENGER("Avenger"),
    @SerializedName("alter_ego") ALTER_EGO("Alter Ego"),
    @SerializedName("moon_cancer") MOON_CANCER("Moon Cancer"),
    @SerializedName("foreigner") FOREIGNER("Foreigner"),
    @SerializedName("beast") BEAST("Beast");

    private String name;

    ServantClass(String name) {
        this.name = name;
    }

    public String getIconURL() {
        return String.format("img/class_icons/%s.png", name.toLowerCase());
    }

    @Override
    public String toString() {
        return name;
    }
}
