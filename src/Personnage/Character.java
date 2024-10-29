package Personnage;

import Destroy.Destructible;

public abstract class Character {
    private String name;
    protected double health;
    protected String CharacterClass;



    public Character(String name, double health, String CharacterClass) {
        this.name = name;
        this.health = health;
        this.CharacterClass = CharacterClass;
    }

    public String getCharacterClass() {
        return CharacterClass;
    }

    public String getName() {
        return name;
    }

    public String setName(String newName){
        return this.name = newName;
    }

    public double getHealth() {
        return health;
    }

    public void getDamage(double damage) {
        health -= damage;
    }

    @Override
    public String toString() {
        return "Character{name='" + name + "', health=" + health + "}";
    }

    public abstract void specialAttack(Destructible target);
    public abstract String asciiArt();




}
