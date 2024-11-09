package Personnage;

import Destroy.Destructible;

public class Archer extends Character {
    private static final double SPECIAL_DAMAGE = 20;
    private double arrows = 10;

    public Archer(String name, double health, String characterClass, double arrows) {
        super(name, health, characterClass);
        this.arrows = arrows;
    }

    @Override
    public String asciiArt() {
        return "🏹 Archer " + getName();
    }

    @Override
    public String getResource() {
        return arrows + " arrows";
    }

    @Override
    public void specialAttack(Destructible target) {
        if (this.arrows > 0) {
            this.arrows -= 1;
            System.out.println(getName() + " uses a deadly arrow and deals " + SPECIAL_DAMAGE + " damage!");

            target.hit(SPECIAL_DAMAGE);
        } else {
            System.out.println(getName() + " doesn't have enough deadly arrow!");
        }
    }
}
