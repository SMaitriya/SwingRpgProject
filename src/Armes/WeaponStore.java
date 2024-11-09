package Armes;
import java.util.ArrayList;
import Personnage.Character;

public class WeaponStore {

    private final ArrayList<Weapon> weapons;

    public WeaponStore() {
        this.weapons = new ArrayList<>();
        this.weapons.add(new Axe());
        this.weapons.add(new Bow());
        this.weapons.add(new Crossbow());
        this.weapons.add(new Spellbook());
        this.weapons.add(new Sword());
        this.weapons.add(new Wand());
    }

    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }


    public ArrayList<Weapon> getWeaponsForClass(Character character) {
        return this.getAvailableWeapons(character);
    }

    public void printWeapons(Character character) {
        ArrayList<Weapon> availableWeapons = getAvailableWeapons(character);
        if (availableWeapons.isEmpty()) {
            System.out.println("No weapons available for your class.");
            return;
        }

        for (int i = 0; i < availableWeapons.size(); i++) {
            Weapon w = availableWeapons.get(i);
            System.out.println("[" + i + "] " + w.toString() + "\n" + w.asciiArt());
        }
    }

    private ArrayList<Weapon> getAvailableWeapons(Character character) {
        ArrayList<Weapon> availableWeapons = new ArrayList<>();
        String characterClass = character.getCharacterClass();

        switch (characterClass) {
            case "Warrior":
                availableWeapons.add(new Axe());
                availableWeapons.add(new Sword());
                availableWeapons.add(new WoodenStick());
                break;
            case "Mage":
                availableWeapons.add(new Spellbook());
                availableWeapons.add(new Wand());
                availableWeapons.add(new WoodenStick());
                break;
            case "Archer":
                availableWeapons.add(new Bow());
                availableWeapons.add(new Crossbow());
                availableWeapons.add(new WoodenStick());
                break;
            default:
                System.out.println("Unknown class: " + characterClass);
        }
        return availableWeapons;
    }
}
