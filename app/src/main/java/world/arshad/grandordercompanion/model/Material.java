package world.arshad.grandordercompanion.model;

/**
 * This is an enum of all skill and ascension materials.
 * Created by arshad on 19/03/2018.
 */

public enum Material {
    DRAGON_FANG("Dragon Fang", "dragon_fang"),
    EVIL_BONE("Evil Bone", "evil_bone"),
    PROOF_OF_HERO("Proof of Hero", "proof_of_hero"),
    VOIDS_DUST("Void's Dust", "voids_dust"),
    ETERNAL_GEAR("Eternal Gear", "eternal_gear"),
    FORBIDDEN_PAGE("Forbidden Page", "forbidden_page"),
    GHOST_LANTERN("Ghost Lantern", "ghost_lantern"),
    HOMUNCULUS_BABY("Homunculus Baby", "homunculus_baby"),
    METEOR_HORSESHOE("Meteor Horseshoe", "meteor_horseshoe"),
    OCTUPLET_TWIN_CRYSTALS("Octuplet Twin Crystals", "octuplet_twin_crystals"),
    PHOENIX_FEATHER("Phoenix Feather", "phoenix_feather"),
    SEED_OF_YGGDRASSIL("Seed of Yggdrassil", "seed_of_yggdrassil"),
    SERPENT_JEWEL("Serpent Jewel", "serpent_jewel"),
    BLACK_BEAST_GREASE("Black Beast Grease", "black_beast_grease"),
    CLAW_OF_CHAOS("Claw of Chaos", "claw_of_chaos"),
    CRYSTALLIZED_LORE("Crystallized Lore", "crystallized_lore"),
    DRAGONS_REVERSE_SCALE("Dragons Reverse Scale", "dragons_reverse_scale"),
    HEART_OF_THE_FOREIGN_GOD("Heart of the Foreign God", "heart_of_the_foreign_god"),
    SPIRIT_ROOT("Spirit Root", "spirit_root"),
    TEARSTONE_OF_BLOOD("Tearstone of Blood", "tearstone_of_blood"),
    WARHORSES_YOUNG_HORN("Warhorse's Young Horn", "warhorses_young_horn"),
    BIZARRE_GOD_WINE("Bizarre God Wine", "bizarre_god_wine"),
    CHAIN_OF_FOOLS("Chain of Fools", "chain_of_fools"),
    CURSED_BEAST_CHOLECYST("Cursed Beast Cholecyst", "cursed_beast_cholecyst"),
    GREAT_KNIGHT_MEDAL("Great Knight Medal", "great_knight_medal"),
    LAMP_OF_SEALED_EVIL("Lamp of Sealed Evil", "lamp_of_sealed_evil"),
    MAGICAL_CEREBROSPINAL_FLUID("Magical Cerebrospinal Fluid", "magical_cerebrospinal_fluid"),
    SCARAB_OF_WISDOM("Scarab of Wisdom", "scarab_of_wisdom"),
    SHELL_OF_REMINISCENCE("Shell of Reminiscence", "shell_of_reminiscence"),
    STINGER_OF_CERTAIN_DEATH("Stinger of Certain Death", "stinger_of_certain_death"),
    ARCHER_PIECE("Archer Piece", "archer_piece"),
    ASSASSIN_PIECE("Assassin Piece", "assassin_piece"),
    BERSERKER_PIECE("Berserker Piece", "berserker_piece"),
    CASTER_PIECE("Caster Piece", "caster_piece"),
    LANCER_PIECE("Lancer Piece", "lancer_piece"),
    RIDER_PIECE("Rider Piece", "rider_piece"),
    SABER_PIECE("Saber Piece", "saber_piece"),
    ARCHER_MONUMENT("Archer Monument", "archer_monument"),
    ASSASSIN_MONUMENT("Assassin Monument", "assassin_monument"),
    BERSERKER_MONUMENT("Berserker Monument", "berserker_monument"),
    CASTER_MONUMENT("Caster Monument", "caster_monument"),
    LANCER_MONUMENT("Lancer Monument", "lancer_monument"),
    RIDER_MONUMENT("Rider Monument", "rider_monument"),
    SABER_MONUMENT("Saber Monument", "saber_monument"),
    GEM_OF_ARCHER("Gem of Archer", "gem_of_archer"),
    GEM_OF_ASSASSIN("Gem of Assassin", "gem_of_assassin"),
    GEM_OF_BERSERKER("Gem of Berserker", "gem_of_berserker"),
    GEM_OF_CASTER("Gem of Caster", "gem_of_caster"),
    GEM_OF_LANCER("Gem of Lancer", "gem_of_lancer"),
    GEM_OF_RIDER("Gem of Rider", "gem_of_rider"),
    GEM_OF_SABER("Gem of Saber", "gem_of_saber"),
    MAGIC_GEM_OF_ARCHER("Magic Gem of Archer", "magic_gem_of_archer"),
    MAGIC_GEM_OF_ASSASSIN("Magic Gem of Assassin", "magic_gem_of_assassin"),
    MAGIC_GEM_OF_BERSERKER("Magic Gem of Berserker", "magic_gem_of_berserker"),
    MAGIC_GEM_OF_CASTER("Magic Gem of Caster", "magic_gem_of_caster"),
    MAGIC_GEM_OF_LANCER("Magic Gem of Lancer", "magic_gem_of_lancer"),
    MAGIC_GEM_OF_RIDER("Magic Gem of Rider", "magic_gem_of_rider"),
    MAGIC_GEM_OF_SABER("Magic Gem of Saber", "magic_gem_of_saber"),
    SECRET_GEM_OF_ARCHER("Secret Gem of Archer", "secret_gem_of_archer"),
    SECRET_GEM_OF_ASSASSIN("Secret Gem of Assassin", "secret_gem_of_assassin"),
    SECRET_GEM_OF_BERSERKER("Secret Gem of Berserker", "secret_gem_of_berserker"),
    SECRET_GEM_OF_CASTER("Secret Gem of Caster", "secret_gem_of_caster"),
    SECRET_GEM_OF_LANCER("Secret Gem of Lancer", "secret_gem_of_lancer"),
    SECRET_GEM_OF_RIDER("Secret Gem of Rider", "secret_gem_of_rider"),
    SECRET_GEM_OF_SABER("Secret Gem of Saber", "secret_gem_of_saber"),
    TWINKLE_CANDY("Twinkle Candy", "twinkle_candy"),
    SHARP_KNIFE("Sharp Knife", "sharp_knife"),
    BUCKET_OF_CHICKEN("Bucket of Chicken", "bucket_of_chicken"),
    GOLDEN_SKULL("Golden Skull", "golden_skull"),
    CRYSTAL_BALL("Crystal Ball", "crystal_ball"),
    GOLDEN_BEAR_LIGHTER("Golden Bear Lighter", "golden_bear_lighter"),
    BELL_RINGING_BRANCH("Bell-Ringing Branch", "bell_ringing_branch");

    private String humanName;
    private String cleanName;

    Material(String humanName, String cleanName) {
        this.humanName = humanName;
        this.cleanName = cleanName;
    }

    @Override
    public String toString() {
        return this.humanName;
    }

    public String getIconPath() {
        return String.format("img/mats/%s.png", cleanName);
    }
}
