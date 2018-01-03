package world.arshad.grandordercompanion.data.user_data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by arsha on 29/12/2017.
 */

public class SummonedServant {

    @PrimaryKey
    private int servantId;

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


    public int getServantId() {
        return servantId;
    }

    public void setServantId(int servantId) {
        this.servantId = servantId;
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


}
