package Armes;

public class Wand extends Weapon {

    private static final double DAMAGE = 9;
    private static final double PRICE = 10;
    private static final String NAME = "Wand";
    private static final double MONSTER_DAMAGE_RATIO = 1.5;
    private static final double OBSTACLE_DAMAGE_RATIO = 2;


    public Wand() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO);
    }

    public String asciiArt() {
        return
                "      /\\\n" +
                        "     /__\\\n" +
                        "    |    |\n" +
                        "    |    |\n" +
                        "    |    |\n" +
                        "    |    |\n" +
                        "    |____|\n" +
                        "       ||\n" +
                        "       ||\n" +
                        "       ||\n";
    }

}