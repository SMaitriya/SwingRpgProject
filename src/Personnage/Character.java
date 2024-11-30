package Personnage;
import Armes.Weapon;
import Destroy.Destructible;
import Swing.GamePage;

import javax.swing.*;

public abstract class Character {
    private String name;
    protected double health;
    protected String characterClass;
    private double gold;
    private Weapon equippedWeapon;


    public Character(String name, double health, String characterClass) {
        this.name = name;
        this.health = health;
        this.characterClass = characterClass;
        this.gold = 0;
        this.equippedWeapon = null;
    }

    public boolean buyWeapon(Weapon weapon) {
        if (this.gold >= weapon.getPrice()) {
            this.spendGold(weapon.getPrice());
            this.setEquippedWeapon(weapon);
            System.out.println("You have successfully bought: " + weapon.getName());
            return true; // Achat réussi
        } else {
            System.out.println("You don't have enough gold for " + weapon.getName());
            JOptionPane.showMessageDialog(null,
                    "You don't have enough gold for " + weapon.getName() + ".",
                    "Purchase Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false; // Achat échoué
        }
    }


    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public double getGold() {
        return gold;
    }

    public void addGold(double amount) {
        this.gold += amount;
    }

    public void addHealth(double amount) {
        this.health += amount;
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
        return  name + " has "+  health + " HP" +  " - " + getResource() +  " -- " + gold + " golds" ;
    }

    public abstract void specialAttack(Destructible target);
    public abstract String asciiArt();
    public abstract String getResource(); // Méthode abstraite pour obtenir le mana, l'énergie ou les flèches




}
