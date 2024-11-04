package Personnage;

import Destroy.Destructible;

public class Warrior extends Character {
    private static final double SPECIAL_DAMAGE = 16;
    private double energy;

    public Warrior(String name, double health, String characterClass, double energy) {
        super(name, health, characterClass);
        this.energy = energy;
    }

    @Override
    public String asciiArt() {
        return "⚔️ Warrior " + getName();
    }

    @Override
    public String getResource() {
        return energy + " energy";
    }


    @Override
    public void specialAttack(Destructible target) {
        if (this.energy >= 10) {
            this.energy -= 10;
            System.out.println(getName() + " uses berserker attack and deals " + SPECIAL_DAMAGE + " damages!");
            target.hit(SPECIAL_DAMAGE);
        } else {
            System.out.println(getName() + " doesn't have enough energy");
        }
    }
}
