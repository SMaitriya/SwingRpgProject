package Armes;

public class Crossbow extends Weapon {

    private static final double DAMAGE = 25;
    private static final double PRICE = 100;
    private static final String NAME = "Crossbow";

    private static final double MONSTER_DAMAGE_RATIO = 1.5;
    private static final double OBSTACLE_DAMAGE_RATIO = 2;


    public Crossbow() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO);
    }

    public String asciiArt() {
        return
                "       _\n" +
                        "     _|=|\n" +
                        "    |     |\n" +
                        "   /_______\\\n" +
                        "     \\   /\n" +
                        "      \\_/\n" +
                        "      | |\n" +
                        "      | |\n";
    }
}