package world.arshad.grandordercompanion.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by arshad on 29/12/2017.
 */

public class Utilities {

    public static Bitmap loadImageFromStorage(String filename) {

        try {
            File f = new File(filename);
            return BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            Log.e("IMAGE LOADING", "Failed to load image: " + filename);
        }

        return Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
    }

}
