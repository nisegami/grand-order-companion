package world.arshad.grandordercompanion;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;

/**
 * Created by arsha on 03/01/2018.
 */

public class GrandOrderCompanionApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        // Try to unzip database

        try {
            InputStream in = getResources().openRawResource(R.raw.fgodb);
            ZipInputStream zin = new ZipInputStream(in);
            ZipEntry ze;

            while ((ze = zin.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                    File f = new File(getFilesDir() + ze.getName());
                    if (!(f.isDirectory())) {
                        f.mkdirs();
                    }
                } else {
                    FileOutputStream fout = new FileOutputStream(new File(getFilesDir() + ze.getName()));

                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = zin.read(buffer)) != -1) {
                        fout.write(buffer, 0, len);
                    }
                    fout.close();

                    zin.closeEntry();
                }
            }
        } catch (IOException e) {
            Log.e("Updating Database", "Failed to unzip db.", e);
        }

        // Populate read only data into singleton

        DomainDataSingleton.getInstance().loadDomainData(this);
    }
}
