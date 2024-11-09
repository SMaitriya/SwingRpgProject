package Personnage;
import Destroy.Destructible;

public class Mage extends Character{

    private double mana;

    public Mage(String name, double health, String characterClass, double mana) {
        super(name, health, characterClass);
        this.mana = mana;
    }

    @Override
    public String asciiArt() {
        return "🧙 Mage " + getName();
    }


    @Override
    public String getResource() {
        return mana + " mana";
    }

    @Override
    public void specialAttack(Destructible target) {
        if (this.mana >= 10) { // Vérifie si le mage a assez de mana
            this.mana -= 10;
            double specialDamage = 25; // Dégâts de l'attaque spéciale
            System.out.println(getName() + " uses frostbolt and deals " + specialDamage + " damages!");

            target.hit(specialDamage);
        } else {
            System.out.println(getName() + " doesn't have enough mana");
        }
    }


}
