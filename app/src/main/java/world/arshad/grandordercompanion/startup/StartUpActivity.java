package world.arshad.grandordercompanion.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;
import world.arshad.grandordercompanion.servant_info_list.ServantInfoListActivity;

public class StartUpActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        ButterKnife.bind(this);

        unzip(R.raw.fgodb);

        DomainDataSingleton.getInstance().loadDomainData(this);

        Intent intent = new Intent(this, ServantInfoListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void unzip(int resourceId) {
        try {
            InputStream in = getResources().openRawResource(resourceId);
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

    }
}
