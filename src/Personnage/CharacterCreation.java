package Personnage;// CharacterCreation.java

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CharacterCreation {

    public static Character createCharacter() {
        Scanner scanner = new Scanner(System.in);

        // Demander le nom de l'utilisateur
        System.out.print("Please enter a name for your character: ");
        String name = scanner.nextLine();
        System.out.println("You have chosen the name: " + name);

        // Afficher les classes de personnages disponibles
        List<Character> characterClasses = Classes.getClasses();
        Classes.printClasses(characterClasses);

        // Choisir la classe de personnage
        Character chosenCharacter = null;
        while (chosenCharacter == null) {
            System.out.print("\nEnter the number of your chosen class: ");
            try {
                int choice = scanner.nextInt();
                if (choice >= 0 && choice < characterClasses.size()) {
                    Character selectedCharacter = characterClasses.get(choice);
                    if (selectedCharacter instanceof Warrior) {
                        chosenCharacter = new Warrior(name, selectedCharacter.getHealth(), selectedCharacter.getCharacterClass(), 40);
                    } else if (selectedCharacter instanceof Mage) {
                        chosenCharacter = new Mage(name, selectedCharacter.getHealth(), selectedCharacter.getCharacterClass(), 30);
                    } else if (selectedCharacter instanceof Archer) {
                        chosenCharacter = new Archer(name, selectedCharacter.getHealth(), selectedCharacter.getCharacterClass(), 4);
                    }
                } else {
                    System.out.println("Invalid choice, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        // Ajouter de l'or initial au personnage
        chosenCharacter.addGold(20);
        System.out.println("\nWelcome to the game " + name + " the " + chosenCharacter.getClass().getSimpleName() +
                ", you have " + chosenCharacter.getGold() + " gold!");

        return chosenCharacter;
    }
}
