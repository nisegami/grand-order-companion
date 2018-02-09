package world.arshad.grandordercompanion.data.domain_data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arshad on 22/12/2017.
 */

public enum GrowthCurve {
    @SerializedName("linear")
    LINEAR("Linear"),

    @SerializedName("reverse_s")
    REVERSE_S("Reverse S"),

    @SerializedName("s")
    S("S"),

    @SerializedName("semi_reverse_s")
    SEMI_REVERSE_S("Semi Reverse S"),

    @SerializedName("semi_s")
    SEMI_S("Semi S");

    private String name;


    GrowthCurve(String name) {
        this.name = name;
    }


}
