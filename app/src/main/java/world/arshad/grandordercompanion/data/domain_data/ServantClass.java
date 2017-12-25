package world.arshad.grandordercompanion.data.domain_data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arsha on 22/12/2017.
 */

public enum ServantClass {

    @SerializedName("saber")
    SABER("Saber"),
    @SerializedName("lancer")
    LANCER("Lancer");

    private String name;

    ServantClass(String name) {
        this.name = name;
    }
}
