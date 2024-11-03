package Armes;

public class Spellbook extends Weapon {

    private static final double DAMAGE = 25;
    private static final double PRICE = 50;
    private static final String NAME = "Spellbook";

    private static final double MONSTER_DAMAGE_RATIO = 1.5;
    private static final double OBSTACLE_DAMAGE_RATIO = 2;


    public Spellbook() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO);
    }

    public String asciiArt() {
        return
                "       ______________\n" +
                        "      /             /|\n" +
                        "     /             / |\n" +
                        "    /             /  |\n" +
                        "   /____________ /   |\n" +
                        "   |  SPELLBOOK |    |\n" +
                        "   |             |    |\n" +
                        "   |             |    |\n" +
                        "   |_____________|    |\n" +
                        "   |             |   /\n" +
                        "   |             |  /\n" +
                        "   |_____________| /\n" +
                        "   |______________|/\n";
    }

}