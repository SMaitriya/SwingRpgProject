package Personnage;

import Destroy.Destructible;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    private String name;
    protected double health;
    protected String characterClass;
    private double gold;

    public Character(String name, double health, String characterClass) {
        this.name = name;
        this.health = health;
        this.characterClass = characterClass;
        this.gold = 0;
    }

    public double getGold() {
        return gold;
    }

    public void addGold(double amount) {
        this.gold += amount;
    }

    public void spendGold(double amount) {
        if (amount <= gold) {
            this.gold -= amount;
        }
        else {
            System.out.println("Not enough gold !");
        }
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public double getHealth() {
        return health;
    }

    public void getDamage(double damage) {
        health -= damage;
    }


    @Override
    public String toString() {
        return  name + " has "+  health + " HP" + " and " + gold + " golds";
    }

    public abstract void specialAttack(Destructible target);
    public abstract String asciiArt();



}
