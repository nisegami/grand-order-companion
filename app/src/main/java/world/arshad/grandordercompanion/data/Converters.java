package world.arshad.grandordercompanion.data;

import android.arch.persistence.room.TypeConverter;

/**
 * Created by arsha on 19/03/2018.
 */

public class Converters {

    @TypeConverter
    public static Material toMaterialFromString(String val) {
        return Material.valueOf(val);
    }

    @TypeConverter
    public static String toStringFromMaterial(Material material) {
        return material.name();
    }

    @TypeConverter
    public static ServantClass toClassFromString(String val) {
        return ServantClass.valueOf(val);
    }

    @TypeConverter
    public static String toStringFromClass(ServantClass servantClass) {
        return servantClass.name();
    }

    @TypeConverter
    public static GrowthCurve toCurveFromString(String val) {
        return GrowthCurve.valueOf(val);
    }

    @TypeConverter
    public static String toStringFromCurve(GrowthCurve growthCurve) {
        return growthCurve.name();
    }
}
