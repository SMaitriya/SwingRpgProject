package Personnage;

import Destroy.Destructible;

public class Guerrier extends Character {
    private static final double SPECIAL_DAMAGE = 20;
    private double energy;


    public Guerrier(String name, double health, double energy){
        super(name, health);
        this.energy = energy;
}




    @Override
    public void specialAttack(Destructible target) {
        if (this.energy >= 10) {
            this.energy -= 10;

            target.hit(SPECIAL_DAMAGE);
            System.out.println(getName() + " uses berserker attack and deals " + SPECIAL_DAMAGE + " damages !");
        }
        else{
            System.out.println(getName() + " doesn't have enough energy");

        }
    }
}
