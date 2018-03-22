package world.arshad.grandordercompanion.data;

/**
 * Enum representing the different servant classes
 * Created by arshad on 21/03/2018.
 */

public enum ServantClass {

    SHIELDER("Shielder"),
    SABER("Saber"),
    LANCER("Lancer"),
    ARCHER("Archer"),
    RIDER("Rider"),
    CASTER("Caster"),
    ASSASSIN("Assassin"),
    BERSERKER("Berserker"),
    RULER("Ruler"),
    AVENGER("Avenger"),
    ALTER_EGO("Alter Ego"),
    MOON_CANCER("Moon Cancer"),
    FOREIGNER("Foreigner"),
    BEAST("Beast");

    private String name;

    ServantClass(String name) {
        this.name = name;
    }

    public String getIconURL() {
        return String.format("img/class_icons/%s.png", name.toLowerCase().replace(' ', '_'));
    }

    @Override
    public String toString() {
        return name;
    }
}

