import Armes.WeaponStore;
import Personnage.Archer;
import Personnage.Character;
import Personnage.Mage;
import Personnage.Warrior;
import Personnage.Classes;

import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        // Créer un scanner pour la saisie utilisateur
        Scanner scanner = new Scanner(System.in);

        // Demander le nom de l'utilisateur
        System.out.print("Please enter a name for your character: ");
        String name = scanner.nextLine();

        // Afficher le nom choisi par l'utilisateur
        System.out.println("You have chosen the name: " + name);

        // Obtenir la liste des classes de personnages disponibles
        List<Character> characterClasses = Classes.getClasses(); // Correction du nom de méthode

        // Appeler la méthode pour afficher les classes
        Classes.printClasses(characterClasses);

        Character chosenCharacter = null;
        while (chosenCharacter == null) {
            System.out.print("\nEnter the number of your chosen class: ");
            int choice = scanner.nextInt();
            if (choice >= 0 && choice < characterClasses.size()) {
                Character selectedCharacter = characterClasses.get(choice);
                if (selectedCharacter instanceof Warrior) {
                    chosenCharacter = new Warrior(name, selectedCharacter.getHealth(), selectedCharacter.getCharacterClass(), 50); // Exemple d'or
                } else if (selectedCharacter instanceof Mage) {
                    chosenCharacter = new Mage(name, selectedCharacter.getHealth(), selectedCharacter.getCharacterClass(), 100); // Exemple d'or
                } else if (selectedCharacter instanceof Archer) {
                    chosenCharacter = new Archer(name, selectedCharacter.getHealth(), selectedCharacter.getCharacterClass(), 10); // Exemple d'or
                }
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }

        chosenCharacter.addGold(10);

        // Afficher la classe choisie par le joueur
        System.out.println("\nWelcome to the game " + name + " the " + chosenCharacter.getClass().getSimpleName() + " and you have " + chosenCharacter.getGold() + " golds !");

        System.out.println(chosenCharacter.toString());
        // Créer un magasin d'armes
        WeaponStore weaponStore = new WeaponStore();

      // Afficher les armes disponibles pour la classe du personnage
        weaponStore.printWeapons(chosenCharacter);

        System.out.println("Enter the number of the weapon you want to buy ");

        int choice = scanner.nextInt();
        if (choice >= 0 && weaponStore.getWeapons().size() < choice) {

        }



        // Fermer le scanner pour éviter les fuites de ressources
        scanner.close();
    }
}
