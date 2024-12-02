package Personnage;
import Armes.Weapon;
import Destroy.Destructible;


import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    private String name;
    protected double health;
    protected String characterClass;
    private double gold;
    private Weapon equippedWeapon;
    private List<Weapon> inventory;  // List de Weapon, pas de String


    public Character(String name, double health, String characterClass) {
        this.name = name;
        this.health = health;
        this.characterClass = characterClass;
        this.gold = 0;
        this.equippedWeapon = null;
        this.inventory = new ArrayList<Weapon>(); // Initialisation de l'inventaire

    }

    public boolean buyWeapon(Weapon weapon) {
        if (this.gold >= weapon.getPrice()) {
            this.spendGold(weapon.getPrice());
            this.addItemToInventory(weapon); // Ajoutez l'arme à l'inventaire
            System.out.println("You have successfully bought: " + weapon.getName());
            return true;
        } else {
            System.out.println("You don't have enough gold for " + weapon.getName());
            JOptionPane.showMessageDialog(null,
                    "You don't have enough gold for " + weapon.getName() + ".",
                    "Purchase Failed",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }



    public List<Weapon> getInventory() {
        return inventory;
    }

    public Character(int initialGold) {
        this.gold = initialGold;
        this.inventory = new ArrayList<>();
    }

    public void addItemToInventory(Weapon weapon) {
        inventory.add(weapon); // Ajouter l'objet Weapon à l'inventaire
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