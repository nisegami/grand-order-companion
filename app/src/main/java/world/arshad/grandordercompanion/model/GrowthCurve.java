package world.arshad.grandordercompanion.model;

/**
 * Created by arsha on 21/03/2018.
 */

public enum GrowthCurve {
    LINEAR("Linear"),
    REVERSE_S("Reverse S"),
    S("S"),
    SEMI_REVERSE_S("Semi Reverse S"),
    SEMI_S("Semi S");

    private String name;

    GrowthCurve(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
