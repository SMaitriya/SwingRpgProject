package Armes;

public class WoodenStick extends Weapon {

    private static final double DAMAGE = 5;
    private static final double PRICE = 3;
    private static final String NAME = "Wooden Stick";
    private static final double MONSTER_DAMAGE_RATIO = 1.5;
    private static final double OBSTACLE_DAMAGE_RATIO = 2;


    public WoodenStick() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO);
    }

    @Override
    public String asciiArt() {
        return
                "    ||\n" +
                        "    ||\n" +
                        "    ||\n" +
                        "    ||\n" +
                        "    ||\n" +
                        "    ||\n" +
                        "   /||\\\n" +
                        "  /_||_\\\n";
    }
}