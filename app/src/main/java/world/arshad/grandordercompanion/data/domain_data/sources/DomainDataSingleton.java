package world.arshad.grandordercompanion.data.domain_data.sources;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import world.arshad.grandordercompanion.data.domain_data.AscensionEntry;
import world.arshad.grandordercompanion.data.domain_data.ExperienceAtLevel;
import world.arshad.grandordercompanion.data.domain_data.Material;
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;
import world.arshad.grandordercompanion.data.domain_data.SkillUpEntry;
import world.arshad.grandordercompanion.data.domain_data.StatIncrease;

/**
 * Created by arsha on 29/12/2017.
 */

public class DomainDataSingleton {
    private static final DomainDataSingleton ourInstance = new DomainDataSingleton();

    public static DomainDataSingleton getInstance() {
        return ourInstance;
    }

    private List<AscensionEntry> ascensionEntries;
    private List<ExperienceAtLevel> experienceAtLevels;
    private List<Material> materials;
    private List<ServantInfo> servantInfos;
    private List<SkillUpEntry> skillUpEntries;
    private List<StatIncrease> statIncreases;

    private DomainDataSingleton() {
    }

    public void loadDomainData(Context context) {

        String jsonText;
        Gson gson = new Gson();

        jsonText = readJSON(new File(context.getFilesDir() + "json/ServantInfo.json"));
        DomainDataSingleton.getInstance().setServantInfos(Arrays.asList(gson.fromJson(jsonText, ServantInfo[].class)));

        jsonText = readJSON(new File(context.getFilesDir() + "json/AscensionEntry.json"));
        DomainDataSingleton.getInstance().setAscensionEntries(Arrays.asList(gson.fromJson(jsonText, AscensionEntry[].class)));

        jsonText = readJSON(new File(context.getFilesDir() + "json/SkillUpEntry.json"));
        DomainDataSingleton.getInstance().setSkillUpEntries(Arrays.asList(gson.fromJson(jsonText, SkillUpEntry[].class)));
    }

    private static String readJSON(File file) {

        FileInputStream resourceReader;

        try {
            resourceReader = new FileInputStream(file);
        } catch (IOException e) {
            Log.e("JSON ERROR", "Could not open file", e);
            return "[]";
        }

        Writer writer = new StringWriter();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            return writer.toString();
        } catch (Exception e) {
            Log.e("JSON ERROR", "Unhandled exception while using JSONResourceReader", e);
            return "[]";
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e("JSON ERROR", "Unhandled exception while using JSONResourceReader", e);
                return "[]";
            }
        }
    }

    public List<AscensionEntry> getAscensionEntries(int id) {
        List<AscensionEntry> entries = new ArrayList<>();

        for (AscensionEntry entry : ascensionEntries) {
            if (entry.getServantId() == id) {
                entries.add(entry);
            }
        }

        return entries;
    }

    public void setAscensionEntries(List<AscensionEntry> ascensionEntries) {
        this.ascensionEntries = ascensionEntries;
    }

    public List<ExperienceAtLevel> getExperienceAtLevels() {
        return experienceAtLevels;
    }

    public void setExperienceAtLevels(List<ExperienceAtLevel> experienceAtLevels) {
        this.experienceAtLevels = experienceAtLevels;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<ServantInfo> getServantInfos() {
        return servantInfos;
    }

    public void setServantInfos(List<ServantInfo> servantInfos) {
        this.servantInfos = servantInfos;
    }

    public List<SkillUpEntry> getSkillUpEntries(int id) {
        List<SkillUpEntry> entries = new ArrayList<>();

        for (SkillUpEntry entry : skillUpEntries) {
            if (entry.getServantId() == id) {
                entries.add(entry);
            }
        }

        return entries;
    }

    public void setSkillUpEntries(List<SkillUpEntry> skillUpEntries) {
        this.skillUpEntries = skillUpEntries;
    }

    public List<StatIncrease> getStatIncreases() {
        return statIncreases;
    }

    public void setStatIncreases(List<StatIncrease> statIncreases) {
        this.statIncreases = statIncreases;
    }
}
