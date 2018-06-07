package world.arshad.grandordercompanion;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;

/**
 * Created by arsha on 20/03/2018.
 */

public final class Utilities {

    private Utilities() {
    }

    public static Drawable loadDrawableFromAssets(String filename, AssetManager assetManager) {
        try {
            return Drawable.createFromStream(assetManager.open(filename), null);
        } catch (IOException e) {
            Log.e("IMAGE LOADING", "Failed to load image: " + filename, e);
        }
        return null;
    }

    public static Bitmap loadBitmapFromAssets(String filename, AssetManager assetManager) {
        try {
            return BitmapFactory.decodeStream(assetManager.open(filename));
        } catch (IOException e) {
            Log.e("IMAGE LOADING", "Failed to load image: " + filename, e);
        }
        return null;
    }

    public static int getServantColor(Servant servant, Context context) {
        Integer color;
        Palette palette = (new Palette.Builder(Utilities.loadBitmapFromAssets(servant.getArtworkPath(1), context.getAssets()))).maximumColorCount(40).generate();

        try {
            color = palette.getLightMutedSwatch().getRgb();
        } catch (NullPointerException e) {
            color = palette.getLightMutedColor(context.getColor(R.color.colorBackgroundGray));
        }

        return color;
    }

    public static void updateAscensions(Servant servant, int newStatus, int ascensionNumber) {
        List<Ascension> ascensionList = servant.getAscensions();
        Ascension current = ascensionList.get(ascensionNumber - 1);

        if (newStatus > current.getStatus()) {
            for (int i = ascensionNumber - 1; i >= 0; i--) {
                Ascension a = ascensionList.get(i);
                a.setStatus(newStatus);
                ServantRepository.getInstance().updateAscension(a);
            }
        } else {
            for (int i = ascensionNumber - 1; i < 4; i++) {
                Ascension a = ascensionList.get(i);
                a.setStatus(newStatus);
                ServantRepository.getInstance().updateAscension(a);
            }
        }
    }

    public static void updateSkillUps(Servant servant, int newStatus, int destSkillLevel, int skillNumber) {
        List<SkillUp> skillUpList = servant.getSkillUps(skillNumber);
        SkillUp current = skillUpList.get(destSkillLevel - 2);

        if (newStatus > current.getStatus()) {
            for (int i = destSkillLevel - 2; i >= 0; i--) {
                SkillUp a = skillUpList.get(i);
                a.setStatus(newStatus);
                ServantRepository.getInstance().updateSkillUp(a);
            }
        } else {
            for (int i = destSkillLevel - 2; i < 9; i++) {
                SkillUp a = skillUpList.get(i);
                a.setStatus(newStatus);
                ServantRepository.getInstance().updateSkillUp(a);
            }
        }
    }
}
