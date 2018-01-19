package world.arshad.grandordercompanion.data.domain_data;

import com.google.gson.annotations.SerializedName;

public enum Material {

    @SerializedName("holy_grail") HOLY_GRAIL("Holy Grail", "holy_grail"),
    @SerializedName("crystallized_lore") CRYSTALLIZED_LORE("Crystallized Lore", "crystallized_lore"),
    @SerializedName("phoenix_feather") PHOENIX_FEATHER("Phoenix Feather", "phoenix_feather"),
    @SerializedName("seed_of_yggdrassil") SEED_OF_YGGDRASSIL("Seed of Yggdrassil", "seed_of_yggdrassil"),
    @SerializedName("proof_of_hero") PROOF_OF_HERO("Proof of Hero", "proof_of_hero"),
    @SerializedName("voids_dust") VOIDS_DUST("Void's Dust", "voids_dust"),
    @SerializedName("dragons_reverse_scale") DRAGONS_REVERSE_SCALE("Dragon's Reverse Scale", "dragons_reverse_scale"),
    @SerializedName("claw_of_chaos") CLAW_OF_CHAOS("Claw of Chaos", "claw_of_chaos"),
    @SerializedName("ghost_lantern") GHOST_LANTERN("Ghost Lantern", "ghost_lantern"),
    @SerializedName("serpent_jewel") SERPENT_JEWEL("Serpent Jewel", "serpent_jewel"),
    @SerializedName("eternal_gear") ETERNAL_GEAR("Eternal Gear", "eternal_gear"),
    @SerializedName("forbidden_page") FORBIDDEN_PAGE("Forbidden Page", "forbidden_page"),
    @SerializedName("dragon_fang") DRAGON_FANG("Dragon Fang", "dragon_fang"),
    @SerializedName("meteor_horseshoe") METEOR_HORSESHOE("Meteor Horseshoe", "meteor_horseshoe"),
    @SerializedName("homunculus_baby") HOMUNCULUS_BABY("Homunculus Baby ", "homunculus_baby"),
    @SerializedName("octuplet_twin_crystals") OCTUPLET_TWIN_CRYSTALS("Octuplet Twin Crystals", "octuplet_twin_crystals"),
    @SerializedName("evil_bone") EVIL_BONE("Evil Bone", "evil_bone"),
    @SerializedName("heart_of_the_foreign_god") HEART_OF_THE_FOREIGN_GOD("Heart of the Foreign God", "heart_of_the_foreign_god"),
    @SerializedName("tearstone_of_blood") TEARSTONE_OF_BLOOD("Tearstone of Blood", "tearstone_of_blood"),
    @SerializedName("black_tallow") BLACK_TALLOW("Black Tallow", "black_tallow"),
    @SerializedName("spirit_root") SPIRIT_ROOT("Spirit Root", "spirit_root"),
    @SerializedName("war_horses_small_horn") WAR_HORSES_SMALL_HORN("War Horse's Small Horn", "war_horses_small_horn"),
    @SerializedName("chain_of_fools") CHAIN_OF_FOOLS("Chain of Fools", "chain_of_fools"),
    @SerializedName("great_knight_medal") GREAT_KNIGHT_MEDAL("Great Knight Medal", "great_knight_medal"),
    @SerializedName("lamp_of_sealed_evil") LAMP_OF_SEALED_EVIL("Lamp of Sealed Evil", "lamp_of_sealed_evil"),
    @SerializedName("scarab_of_wisdom") SCARAB_OF_WISDOM("Scarab of Wisdom", "scarab_of_wisdom"),
    @SerializedName("shell_of_recollection") SHELL_OF_RECOLLECTION("Shell of Recollection", "shell_of_recollection"),
    @SerializedName("cursed_beast_gallstone") CURSED_BEAST_GALLSTONE("Cursed Beast Gallstone", "cursed_beast_gallstone"),
    @SerializedName("primordial_lanugo") PRIMORDIAL_LANUGO("Primordial Lanugo", "primordial_lanugo"),
    @SerializedName("deadly_poison_stinger") DEADLY_POISON_STINGER("Deadly Poison Stinger", "deadly_poison_stinger"),
    @SerializedName("magic_cerebrospinal_fluid") MAGIC_CEREBROSPINAL_FLUID("Magic Cerebrospinal Fluid", "magic_cerebrospinal_fluid"),
    @SerializedName("chichi_divine_wine") CHICHI_DIVINE_WINE("Chichi Divine Wine", "chichi_divine_wine"),
    @SerializedName("saber_piece") SABER_PIECE("Saber Piece", "saber_piece"),
    @SerializedName("archer_piece") ARCHER_PIECE("Archer Piece", "archer_piece"),
    @SerializedName("lancer_piece") LANCER_PIECE("Lancer Piece", "lancer_piece"),
    @SerializedName("rider_piece") RIDER_PIECE("Rider Piece ", "rider_piece"),
    @SerializedName("caster_piece") CASTER_PIECE("Caster Piece", "caster_piece"),
    @SerializedName("assassin_piece") ASSASSIN_PIECE("Assassin Piece", "assassin_piece"),
    @SerializedName("berserker_piece") BERSERKER_PIECE("Berserker Piece ", "berserker_piece"),
    @SerializedName("saber_monument") SABER_MONUMENT("Saber Monument", "saber_monument"),
    @SerializedName("archer_monument") ARCHER_MONUMENT("Archer Monument", "archer_monument"),
    @SerializedName("lancer_monument") LANCER_MONUMENT("Lancer Monument", "lancer_monument"),
    @SerializedName("rider_monument") RIDER_MONUMENT("Rider Monument", "rider_monument"),
    @SerializedName("caster_monument") CASTER_MONUMENT("Caster Monument", "caster_monument"),
    @SerializedName("assassin_monument") ASSASSIN_MONUMENT("Assassin Monument", "assassin_monument"),
    @SerializedName("berserker_monument") BERSERKER_MONUMENT("Berserker Monument", "berserker_monument"),
    @SerializedName("gem_of_saber") GEM_OF_SABER("Gem of Saber", "gem_of_saber"),
    @SerializedName("gem_of_archer") GEM_OF_ARCHER("Gem of Archer", "gem_of_archer"),
    @SerializedName("gem_of_lancer") GEM_OF_LANCER("Gem of Lancer", "gem_of_lancer"),
    @SerializedName("gem_of_rider") GEM_OF_RIDER("Gem of Rider", "gem_of_rider"),
    @SerializedName("gem_of_caster") GEM_OF_CASTER("Gem of Caster", "gem_of_caster"),
    @SerializedName("gem_of_assassin") GEM_OF_ASSASSIN("Gem of Assassin", "gem_of_assassin"),
    @SerializedName("gem_of_berserker") GEM_OF_BERSERKER("Gem of Berserker", "gem_of_berserker"),
    @SerializedName("magic_gem_of_saber") MAGIC_GEM_OF_SABER("Magic Gem of Saber", "magic_gem_of_saber"),
    @SerializedName("magic_gem_of_archer") MAGIC_GEM_OF_ARCHER("Magic Gem of Archer", "magic_gem_of_archer"),
    @SerializedName("magic_gem_of_lancer") MAGIC_GEM_OF_LANCER("Magic Gem of Lancer", "magic_gem_of_lancer"),
    @SerializedName("magic_gem_of_rider") MAGIC_GEM_OF_RIDER("Magic Gem of Rider", "magic_gem_of_rider"),
    @SerializedName("magic_gem_of_caster") MAGIC_GEM_OF_CASTER("Magic Gem of Caster", "magic_gem_of_caster"),
    @SerializedName("magic_gem_of_assassin") MAGIC_GEM_OF_ASSASSIN("Magic Gem of Assassin", "magic_gem_of_assassin"),
    @SerializedName("magic_gem_of_berserker") MAGIC_GEM_OF_BERSERKER("Magic Gem of Berserker", "magic_gem_of_berserker"),
    @SerializedName("secret_gem_of_saber") SECRET_GEM_OF_SABER("Secret Gem of Saber", "secret_gem_of_saber"),
    @SerializedName("secret_gem_of_archer") SECRET_GEM_OF_ARCHER("Secret Gem of Archer", "secret_gem_of_archer"),
    @SerializedName("secret_gem_of_lancer") SECRET_GEM_OF_LANCER("Secret Gem of Lancer", "secret_gem_of_lancer"),
    @SerializedName("secret_gem_of_rider") SECRET_GEM_OF_RIDER("Secret Gem of Rider", "secret_gem_of_rider"),
    @SerializedName("secret_gem_of_caster") SECRET_GEM_OF_CASTER("Secret Gem of Caster", "secret_gem_of_caster"),
    @SerializedName("secret_gem_of_assassin") SECRET_GEM_OF_ASSASSIN("Secret Gem of Assassin", "secret_gem_of_assassin"),
    @SerializedName("secret_gem_of__berserker") SECRET_GEM_OF_BERSERKER("Secret Gem of Berserker", "secret_gem_of_berserker");

    private String name;
    private String id;

    Material(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getIconURL() {
        return String.format("img/materials/%s.png", id);
    }

}
