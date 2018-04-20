package world.arshad.grandordercompanion;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import world.arshad.grandordercompanion.all_servants.AllServantsActivity;
import world.arshad.grandordercompanion.data.Ascension;
import world.arshad.grandordercompanion.data.AscensionEntry;
import world.arshad.grandordercompanion.data.Model;
import world.arshad.grandordercompanion.data.GrowthCurve;
import world.arshad.grandordercompanion.data.Material;
import world.arshad.grandordercompanion.data.Servant;
import world.arshad.grandordercompanion.data.ServantClass;
import world.arshad.grandordercompanion.data.ServantDatabase;
import world.arshad.grandordercompanion.data.SkillUp;
import world.arshad.grandordercompanion.data.SkillUpEntry;

/**
 * This class will be used to add new servants to the database.
 * Created by arshad on 19/03/2018.
 */

public class DatabaseUpdater extends AsyncTask<Integer, Void, Integer> {

    private Activity activity;

    public DatabaseUpdater(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    protected Integer doInBackground(Integer ... ints) {
        int currVersion = ints[0];
        try {
            if (currVersion < 1) {
                update(Model.getInstance().getDatabase(), activity.getAssets(),1);
                currVersion = 1;
            }
            if (currVersion < 2) {
                update(Model.getInstance().getDatabase(), activity.getAssets(),2);
                currVersion = 2;
            }
            if (currVersion < 3) {
                update(Model.getInstance().getDatabase(), activity.getAssets(),3);
                currVersion = 3;
            }
            return currVersion;
        } catch (IOException e) {
            return currVersion;
        }
    }

    @Override
    protected void onPostExecute(Integer newVersion) {
        ((SplashActivity) activity).onDatabaseUpdated(newVersion);
    }

    private static void update(ServantDatabase database, AssetManager assetManager, int num) throws IOException {

        String filename_base = String.format("csv/%d/", num);

        database.servantDao().insertAllServants(getServants(new InputStreamReader(assetManager.open(filename_base + "servants.csv"))));
        database.servantDao().insertAllAscensions(getAscensions(new InputStreamReader(assetManager.open(filename_base + "ascensions.csv"))));
        database.servantDao().insertAllSkillUps(getSkillUps(new InputStreamReader(assetManager.open(filename_base + "skill_ups.csv"))));
        database.servantDao().insertAllAscensionEntrys(getAscensionEntries(new InputStreamReader(assetManager.open(filename_base + "ascension_entries.csv"))));
        database.servantDao().insertAllSkillUpEntrys(getSkillUpEntries(new InputStreamReader(assetManager.open(filename_base + "skill_up_entries.csv"))));
    }

    private static List<Servant> getServants(Reader file) throws IOException {
        List<Servant> servants = new ArrayList<>();

        CSVReader reader = new CSVReader(file);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            servants.add(new Servant(
                    Integer.valueOf(nextLine[0]),
                    nextLine[1],
                    nextLine[2],
                    nextLine[3],
                    nextLine[4],
                    Integer.valueOf(nextLine[5]),
                    Integer.valueOf(nextLine[6]),
                    nextLine[7],
                    ServantClass.valueOf(nextLine[8].toUpperCase().replace(' ', '_')),
                    GrowthCurve.valueOf(nextLine[9].toUpperCase().replace(' ', '_')),
                    Integer.valueOf(nextLine[10]),
                    Integer.valueOf(nextLine[11]),
                    Integer.valueOf(nextLine[12]),
                    Integer.valueOf(nextLine[13]),
                    Integer.valueOf(nextLine[14]),
                    Integer.valueOf(nextLine[15]),
                    nextLine[16],
                    nextLine[17],
                    nextLine[18],
                    nextLine[19],
                    nextLine[20]
            ));
        }

        return servants;
    }

    private static List<Ascension> getAscensions(Reader file) throws IOException {
        List<Ascension> ascensions = new ArrayList<>();

        CSVReader reader = new CSVReader(file);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            ascensions.add(new Ascension(
                    Integer.valueOf(nextLine[0]),
                    Integer.valueOf(nextLine[1]),
                    Integer.valueOf(nextLine[2])
            ));
        }

        return ascensions;
    }

    private static List<AscensionEntry> getAscensionEntries(Reader file) throws IOException {
        List<AscensionEntry> ascensionEntries = new ArrayList<>();

        CSVReader reader = new CSVReader(file);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            ascensionEntries.add(new AscensionEntry(
                    Integer.valueOf(nextLine[0]),
                    Integer.valueOf(nextLine[1]),
                    Material.valueOf(nextLine[2].toUpperCase()),
                    Integer.valueOf(nextLine[3])
            ));
        }

        return ascensionEntries;
    }

    private static List<SkillUp> getSkillUps(Reader file) throws IOException {
        List<SkillUp> skillUps = new ArrayList<>();

        CSVReader reader = new CSVReader(file);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            skillUps.add(new SkillUp(
                    Integer.valueOf(nextLine[0]),
                    Integer.valueOf(nextLine[1]),
                    Integer.valueOf(nextLine[2]),
                    Integer.valueOf(nextLine[3])
            ));
        }

        return skillUps;
    }

    private static List<SkillUpEntry> getSkillUpEntries(Reader file) throws IOException {
        List<SkillUpEntry> skillUpEntries = new ArrayList<>();

        CSVReader reader = new CSVReader(file);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            skillUpEntries.add(new SkillUpEntry(
                    Integer.valueOf(nextLine[0]),
                    Integer.valueOf(nextLine[1]),
                    Material.valueOf(nextLine[2].toUpperCase()),
                    Integer.valueOf(nextLine[3])
            ));
        }

        return skillUpEntries;
    }
}
