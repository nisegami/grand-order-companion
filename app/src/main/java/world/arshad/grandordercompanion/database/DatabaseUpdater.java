package world.arshad.grandordercompanion.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import world.arshad.grandordercompanion.Utilities;
import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.model.AscensionEntry;
import world.arshad.grandordercompanion.model.GrowthCurve;
import world.arshad.grandordercompanion.model.Material;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.ServantClass;
import world.arshad.grandordercompanion.model.SkillUp;
import world.arshad.grandordercompanion.model.SkillUpEntry;

/**
 * This class will be used to add new servants to the database.
 * Created by arshad on 19/03/2018.
 */

public class DatabaseUpdater extends AsyncTask<Integer, Void, Integer> {

    private PostUpdateCallback callback;
    private ServantDao dao;
    @SuppressLint("StaticFieldLeak") //Only ever supplied with app context
    private Context context;

    public DatabaseUpdater(PostUpdateCallback callback, Context context, ServantDao dao) {
        this.callback = callback;
        this.context = context;
        this.dao = dao;
    }

    @Override
    protected Integer doInBackground(Integer ... ints) {
        int currVersion = ints[0];
        try {
            if (1 > currVersion) {
                update(context.getAssets(),1);
                currVersion = 1;
            }
            if (2 > currVersion) {
                update(context.getAssets(),2);
                currVersion = 2;
            }
            if (3 > currVersion) {
                update(context.getAssets(),3);
                currVersion = 3;
            }
            if (4 > currVersion) {
                update(context.getAssets(),4);
                currVersion = 4;
            }
            if (5 > currVersion) {
                List<Servant> currentServants = dao.getAllServants();
                for (Servant servant : currentServants) {
                    servant.setColor(Utilities.getServantColor(servant, context));
                    dao.updateServant(servant);
                }
                currVersion = 5;
            }
            return currVersion;
        } catch (IOException e) {
            return currVersion;
        }
    }

    @Override
    protected void onPostExecute(Integer newVersion) {
        this.callback.onDatabaseUpdated(newVersion);
    }

    private void update(AssetManager assetManager, int num) throws IOException {

        String filename_base = String.format("csv/%d/", num);

        dao.insertAllServants(DatabaseUpdater.getServants(new InputStreamReader(assetManager.open(filename_base + "servants.csv"))));
        dao.insertAllAscensions(DatabaseUpdater.getAscensions(new InputStreamReader(assetManager.open(filename_base + "ascensions.csv"))));
        dao.insertAllSkillUps(DatabaseUpdater.getSkillUps(new InputStreamReader(assetManager.open(filename_base + "skill_ups.csv"))));
        dao.insertAllAscensionEntries(DatabaseUpdater.getAscensionEntries(new InputStreamReader(assetManager.open(filename_base + "ascension_entries.csv"))));
        dao.insertAllSkillUpEntries(DatabaseUpdater.getSkillUpEntries(new InputStreamReader(assetManager.open(filename_base + "skill_up_entries.csv"))));
    }

    private static List<Servant> getServants(Reader file) throws IOException {
        List<Servant> servants = new ArrayList<>();

        CSVReader reader = new CSVReader(file);
        String [] nextLine;
        while (null != (nextLine = reader.readNext())) {
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
                    nextLine[20],
                    -1
            ));
        }

        return servants;
    }

    private static List<Ascension> getAscensions(Reader file) throws IOException {
        List<Ascension> ascensions = new ArrayList<>();

        CSVReader reader = new CSVReader(file);
        String [] nextLine;
        while (null != (nextLine = reader.readNext())) {
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
        while (null != (nextLine = reader.readNext())) {
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
        while (null != (nextLine = reader.readNext())) {
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
        while (null != (nextLine = reader.readNext())) {
            skillUpEntries.add(new SkillUpEntry(
                    Integer.valueOf(nextLine[0]),
                    Integer.valueOf(nextLine[1]),
                    Material.valueOf(nextLine[2].toUpperCase()),
                    Integer.valueOf(nextLine[3])
            ));
        }

        return skillUpEntries;
    }

    public interface PostUpdateCallback {
        void onDatabaseUpdated(int newVersion);
    }
}
