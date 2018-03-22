package world.arshad.grandordercompanion.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.List;

/**
 * This class represents a Servant, including both the read-only
 * and user defined aspects.
 * Created by arshad on 18/03/2018.
 */

@Entity(tableName = "servant")
public class Servant implements Comparable<Servant> {

    // This is read only stuff

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "skill_1")
    private String skill1;

    @ColumnInfo(name = "skill_2")
    private String skill2;

    @ColumnInfo(name = "skill_3")
    private String skill3;

    @ColumnInfo(name = "rarity")
    private int rarity;

    @ColumnInfo(name = "cost")
    private int cost;

    @ColumnInfo(name = "attribute")
    private String attribute;

    @ColumnInfo(name = "servant_class")
    private ServantClass servantClass;

    @ColumnInfo(name = "growth_curve")
    private GrowthCurve growthCurve;

    @ColumnInfo(name = "base_atk")
    private int baseAtk;

    @ColumnInfo(name = "base_hp")
    private int baseHp;

    @ColumnInfo(name = "max_atk")
    private int maxAtk;

    @ColumnInfo(name = "max_hp")
    private int maxHp;

    @ColumnInfo(name = "grail_atk")
    private int grailAtk;

    @ColumnInfo(name = "grail_hp")
    private int grailHp;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "alignment")
    private String alignment;

    @ColumnInfo(name = "seiyuu")
    private String seiyuu;

    @ColumnInfo(name = "gamepress_url")
    private String gamepressURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public ServantClass getServantClass() {
        return servantClass;
    }

    public void setServantClass(ServantClass servantClass) {
        this.servantClass = servantClass;
    }

    public GrowthCurve getGrowthCurve() {
        return growthCurve;
    }

    public void setGrowthCurve(GrowthCurve growthCurve) {
        this.growthCurve = growthCurve;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public void setBaseAtk(int baseAtk) {
        this.baseAtk = baseAtk;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public int getMaxAtk() {
        return maxAtk;
    }

    public void setMaxAtk(int maxAtk) {
        this.maxAtk = maxAtk;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getGrailAtk() {
        return grailAtk;
    }

    public void setGrailAtk(int grailAtk) {
        this.grailAtk = grailAtk;
    }

    public int getGrailHp() {
        return grailHp;
    }

    public void setGrailHp(int grailHp) {
        this.grailHp = grailHp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getSeiyuu() {
        return seiyuu;
    }

    public void setSeiyuu(String seiyuu) {
        this.seiyuu = seiyuu;
    }

    public String getGamepressURL() {
        return gamepressURL;
    }

    public void setGamepressURL(String gamepressURL) {
        this.gamepressURL = gamepressURL;
    }

    // Skill and Ascension Stuff

    @Ignore
    private List<Ascension> ascensions;

    @Ignore
    private List<SkillUp> skillUps1;

    @Ignore
    private List<SkillUp> skillUps2;

    @Ignore
    private List<SkillUp> skillUps3;


    public List<Ascension> getAscensions() {
        return ascensions;
    }

    public void setAscensions(List<Ascension> ascensions) {
        this.ascensions = ascensions;
    }

    public List<SkillUp> getSkillUps1() {
        return skillUps1;
    }

    public void setSkillUps1(List<SkillUp> skillUps1) {
        this.skillUps1 = skillUps1;
    }

    public List<SkillUp> getSkillUps2() {
        return skillUps2;
    }

    public void setSkillUps2(List<SkillUp> skillUps2) {
        this.skillUps2 = skillUps2;
    }

    public List<SkillUp> getSkillUps3() {
        return skillUps3;
    }

    public void setSkillUps3(List<SkillUp> skillUps3) {
        this.skillUps3 = skillUps3;
    }

    // This is user defined stuff

    @ColumnInfo(name = "summoned")
    private boolean summoned;

    @ColumnInfo(name = "current_servant_exp")
    private int currentServantExp;

    @ColumnInfo(name = "current_np_level")
    private int currentNPLevel;

    @ColumnInfo(name = "current_bond_points")
    private int currentBondPoints;

    @ColumnInfo(name = "current_ascension_number")
    private int currentAscensionNum;

    @ColumnInfo(name = "current_fou_atk")
    private int currentFouATK;

    @ColumnInfo(name = "current_fou_hp")
    private int currentFouHP;

    @ColumnInfo(name = "current_skill_one_level")
    private int currentSkillOneLevel;

    @ColumnInfo(name = "current_skill_two_level")
    private int currentSkillTwoLevel;

    @ColumnInfo(name = "current_skill_three_level")
    private int currentSkillThreeLevel;

    @ColumnInfo(name = "target_servant_exp")
    private int targetServantExp;

    @ColumnInfo(name = "target_np_level")
    private int targetNPLevel;

    @ColumnInfo(name = "target_bond_points")
    private int targetBondPoints;

    @ColumnInfo(name = "target_ascension_number")
    private int targetAscensionNum;

    @ColumnInfo(name = "target_fou_atk")
    private int targetFouATK;

    @ColumnInfo(name = "target_fou_hp")
    private int targetFouHP;

    @ColumnInfo(name = "target_skill_one_level")
    private int targetSkillOneLevel;

    @ColumnInfo(name = "target_skill_two_level")
    private int targetSkillTwoLevel;

    @ColumnInfo(name = "target_skill_three_level")
    private int targetSkillThreeLevel;

    public boolean isSummoned() {
        return summoned;
    }

    public void setSummoned(boolean summoned) {
        this.summoned = summoned;
    }

    public int getCurrentServantExp() {
        return currentServantExp;
    }

    public void setCurrentServantExp(int currentServantExp) {
        this.currentServantExp = currentServantExp;
    }

    public int getCurrentNPLevel() {
        return currentNPLevel;
    }

    public void setCurrentNPLevel(int currentNPLevel) {
        this.currentNPLevel = currentNPLevel;
    }

    public int getCurrentBondPoints() {
        return currentBondPoints;
    }

    public void setCurrentBondPoints(int currentBondPoints) {
        this.currentBondPoints = currentBondPoints;
    }

    public int getCurrentAscensionNum() {
        return currentAscensionNum;
    }

    public void setCurrentAscensionNum(int currentAscensionNum) {
        this.currentAscensionNum = currentAscensionNum;
    }

    public int getCurrentFouATK() {
        return currentFouATK;
    }

    public void setCurrentFouATK(int currentFouATK) {
        this.currentFouATK = currentFouATK;
    }

    public int getCurrentFouHP() {
        return currentFouHP;
    }

    public void setCurrentFouHP(int currentFouHP) {
        this.currentFouHP = currentFouHP;
    }

    public int getCurrentSkillOneLevel() {
        return currentSkillOneLevel;
    }

    public void setCurrentSkillOneLevel(int currentSkillOneLevel) {
        this.currentSkillOneLevel = currentSkillOneLevel;
    }

    public int getCurrentSkillTwoLevel() {
        return currentSkillTwoLevel;
    }

    public void setCurrentSkillTwoLevel(int currentSkillTwoLevel) {
        this.currentSkillTwoLevel = currentSkillTwoLevel;
    }

    public int getCurrentSkillThreeLevel() {
        return currentSkillThreeLevel;
    }

    public void setCurrentSkillThreeLevel(int currentSkillThreeLevel) {
        this.currentSkillThreeLevel = currentSkillThreeLevel;
    }

    public int getTargetServantExp() {
        return targetServantExp;
    }

    public void setTargetServantExp(int targetServantExp) {
        this.targetServantExp = targetServantExp;
    }

    public int getTargetNPLevel() {
        return targetNPLevel;
    }

    public void setTargetNPLevel(int targetNPLevel) {
        this.targetNPLevel = targetNPLevel;
    }

    public int getTargetBondPoints() {
        return targetBondPoints;
    }

    public void setTargetBondPoints(int targetBondPoints) {
        this.targetBondPoints = targetBondPoints;
    }

    public int getTargetAscensionNum() {
        return targetAscensionNum;
    }

    public void setTargetAscensionNum(int targetAscensionNum) {
        this.targetAscensionNum = targetAscensionNum;
    }

    public int getTargetFouATK() {
        return targetFouATK;
    }

    public void setTargetFouATK(int targetFouATK) {
        this.targetFouATK = targetFouATK;
    }

    public int getTargetFouHP() {
        return targetFouHP;
    }

    public void setTargetFouHP(int targetFouHP) {
        this.targetFouHP = targetFouHP;
    }

    public int getTargetSkillOneLevel() {
        return targetSkillOneLevel;
    }

    public void setTargetSkillOneLevel(int targetSkillOneLevel) {
        this.targetSkillOneLevel = targetSkillOneLevel;
    }

    public int getTargetSkillTwoLevel() {
        return targetSkillTwoLevel;
    }

    public void setTargetSkillTwoLevel(int targetSkillTwoLevel) {
        this.targetSkillTwoLevel = targetSkillTwoLevel;
    }

    public int getTargetSkillThreeLevel() {
        return targetSkillThreeLevel;
    }

    public void setTargetSkillThreeLevel(int targetSkillThreeLevel) {
        this.targetSkillThreeLevel = targetSkillThreeLevel;
    }

    // General Stuff

    @Ignore
    /**
     * This constructor is for building with read only data.
     */
    public Servant(int id, String name, String skill1, String skill2, String skill3, int rarity, int cost, String attribute, ServantClass servantClass, GrowthCurve growthCurve, int baseAtk, int baseHp, int maxAtk, int maxHp, int grailAtk, int grailHp, String gender, String alignment, String seiyuu, String gamepressURL) {
        this.id = id;
        this.name = name;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.rarity = rarity;
        this.cost = cost;
        this.attribute = attribute;
        this.servantClass = servantClass;
        this.growthCurve = growthCurve;
        this.baseAtk = baseAtk;
        this.baseHp = baseHp;
        this.maxAtk = maxAtk;
        this.maxHp = maxHp;
        this.grailAtk = grailAtk;
        this.grailHp = grailHp;
        this.gender = gender;
        this.alignment = alignment;
        this.seiyuu = seiyuu;
        this.gamepressURL = gamepressURL;

        // Set to default
        this.summoned = false;
        this.currentServantExp = 0;
        this.currentNPLevel = 1;
        this.currentBondPoints = 0;
        this.currentAscensionNum = 0;
        this.currentFouATK = 0;
        this.currentFouHP = 0;
        this.currentSkillOneLevel = 1;
        this.currentSkillTwoLevel = 1;
        this.currentSkillThreeLevel = 1;
        this.targetServantExp = 0;
        this.targetNPLevel = 1;
        this.targetBondPoints = 0;
        this.targetAscensionNum = 0;
        this.targetFouATK = 0;
        this.targetFouHP = 0;
        this.targetSkillOneLevel = 1;
        this.targetSkillTwoLevel = 1;
        this.targetSkillThreeLevel = 1;
    }

    public Servant(int id, String name, String skill1, String skill2, String skill3, int rarity, int cost, String attribute, ServantClass servantClass, GrowthCurve growthCurve, int baseAtk, int baseHp, int maxAtk, int maxHp, int grailAtk, int grailHp, String gender, String alignment, String seiyuu, String gamepressURL, boolean summoned, int currentServantExp, int currentNPLevel, int currentBondPoints, int currentAscensionNum, int currentFouATK, int currentFouHP, int currentSkillOneLevel, int currentSkillTwoLevel, int currentSkillThreeLevel, int targetServantExp, int targetNPLevel, int targetBondPoints, int targetAscensionNum, int targetFouATK, int targetFouHP, int targetSkillOneLevel, int targetSkillTwoLevel, int targetSkillThreeLevel) {
        this.id = id;
        this.name = name;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.rarity = rarity;
        this.cost = cost;
        this.attribute = attribute;
        this.servantClass = servantClass;
        this.growthCurve = growthCurve;
        this.baseAtk = baseAtk;
        this.baseHp = baseHp;
        this.maxAtk = maxAtk;
        this.maxHp = maxHp;
        this.grailAtk = grailAtk;
        this.grailHp = grailHp;
        this.gender = gender;
        this.alignment = alignment;
        this.seiyuu = seiyuu;
        this.gamepressURL = gamepressURL;
        this.summoned = summoned;
        this.currentServantExp = currentServantExp;
        this.currentNPLevel = currentNPLevel;
        this.currentBondPoints = currentBondPoints;
        this.currentAscensionNum = currentAscensionNum;
        this.currentFouATK = currentFouATK;
        this.currentFouHP = currentFouHP;
        this.currentSkillOneLevel = currentSkillOneLevel;
        this.currentSkillTwoLevel = currentSkillTwoLevel;
        this.currentSkillThreeLevel = currentSkillThreeLevel;
        this.targetServantExp = targetServantExp;
        this.targetNPLevel = targetNPLevel;
        this.targetBondPoints = targetBondPoints;
        this.targetAscensionNum = targetAscensionNum;
        this.targetFouATK = targetFouATK;
        this.targetFouHP = targetFouHP;
        this.targetSkillOneLevel = targetSkillOneLevel;
        this.targetSkillTwoLevel = targetSkillTwoLevel;
        this.targetSkillThreeLevel = targetSkillThreeLevel;
    }

    public String getThumbnailPath(int num) {
        return String.format("img/servants/thumb/%03d%d.jpg", id, num);
    }

    public String getArtworkPath(int num) {
        return String.format("img/servants/full/%03d%d.jpg", id, num);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Servant that = (Servant) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(@NonNull Servant other) {
        return this.id - other.id;
    }

    public enum Comps {

        ID(ID_COMP), NAME(NAME_COMP), CLASS(CLASS_COMP), RARITY(RARITY_COMP);

        private Comparator<Servant> myComp;

        Comps(Comparator<Servant> myComp) {
            this.myComp = myComp;
        }

        public Comparator<Servant> getComp() {
            return myComp;
        }
    }

    public static final Comparator<Servant> ID_COMP = new Comparator<Servant>() {
        @Override
        public int compare(Servant a, Servant b) {
            return a.id - b.id;
        }
    };

    public static final Comparator<Servant> NAME_COMP = new Comparator<Servant>() {
        @Override
        public int compare(Servant a, Servant b) {
            int result = a.name.compareTo(b.name);
            if (result == 0) {
                return a.compareTo(b);
            } else {
                return result;
            }
        }
    };

    public static final Comparator<Servant> CLASS_COMP = new Comparator<Servant>() {
        @Override
        public int compare(Servant a, Servant b) {
            int result =  a.servantClass.compareTo(b.servantClass);
            if (result == 0) {
                return a.compareTo(b);
            } else {
                return result;
            }
        }
    };

    public static final Comparator<Servant> RARITY_COMP = new Comparator<Servant>() {
        @Override
        public int compare(Servant a, Servant b) {
            int result =  b.rarity - a.rarity;
            if (result == 0) {
                return a.compareTo(b);
            } else {
                return result;
            }
        }
    };
}
