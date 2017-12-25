package world.arshad.grandordercompanion.data.domain_data.sources;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;

/**
 * Created by arsha on 22/12/2017.
 */

public class DomainDataSource {

    private static String readJSON(Resources resources, int id) {
        InputStream resourceReader = resources.openRawResource(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e("JSON HANDLER EXCEPTION", "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e("JSON HANDLER EXCEPTION", "Unhandled exception while using JSONResourceReader", e);
            }
        }

        return writer.toString();
    }

    public static List<ServantInfo> getServants(Resources resources) {
        String jsonText = readJSON(resources, R.raw.servant_info);

        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(jsonText, ServantInfo[].class));
    }
}
