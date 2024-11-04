import Armes.Weapon;
import Armes.WeaponStore;
import Destroy.Monster;
import Destroy.Obstacle;
import Map.Combat;
import Map.GameMap;
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
        List<Character> characterClasses = Classes.getClasses();

        // Appeler la méthode pour afficher les classes
        Classes.printClasses(characterClasses);

        Character chosenCharacter = null;
        while (chosenCharacter == null) {
            System.out.print("\nEnter the number of your chosen class: ");
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
        }

        chosenCharacter.addGold(20);

        // Afficher la classe choisie par le joueur
        System.out.println("\nWelcome to the game " + name + " the " + chosenCharacter.getClass().getSimpleName() + " and you have " + chosenCharacter.getGold() + " golds !");

        System.out.println(chosenCharacter.toString());
        // Créer un magasin d'armes
        WeaponStore weaponStore = new WeaponStore();

        // Afficher les armes disponibles pour la classe du personnage
        weaponStore.printWeapons(chosenCharacter);





        while (chosenCharacter.getEquippedWeapon() == null) {
            System.out.print("Enter the number of the weapon you want to buy: ");
            int weaponChoice = scanner.nextInt();

            if (weaponChoice >= 0 && weaponChoice < weaponStore.getWeaponsForClass(chosenCharacter).size()) {
                Weapon selectedWeapon = weaponStore.getWeaponsForClass(chosenCharacter).get(weaponChoice);

                chosenCharacter.buyWeapon(selectedWeapon); // L'arme est achetée ici
            } else {
                System.out.println("Invalid choice.");
            }
        }


        // Vérifiez l'arme équipée
        if (chosenCharacter.getEquippedWeapon() != null) {
            System.out.println("Currently equipped weapon: " + chosenCharacter.getEquippedWeapon());
        }
        // Initialiser la carte du jeu
        GameMap gameMap = new GameMap(5, 5); // Exemple de taille de carte
        System.out.println("Map initialized. Here is your starting position:");
        gameMap.displayMap(); // Afficher la carte initiale

// Boucle de déplacement sur la carte
        String direction;
        while (true) {
            System.out.print("Enter direction to move (z, s, q, d) or 'exit' to quit: ");
            direction = scanner.next();

            if (direction.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game. Thanks for playing!");
                break;
            }

            // Effectuer le mouvement et afficher le résultat
            if (gameMap.movePlayer(direction)) {
                System.out.println("Moved " + direction + ".");
            } else {
                System.out.println("Cannot move in that direction.");
            }

            gameMap.displayMap(); // Afficher la carte après le mouvement

            // Vérifier le contenu de la case actuelle
            String currentTile = gameMap.getCurrentTile();
            if (currentTile.equals("M")) {
                System.out.println("You encountered a monster!");
                Monster monster = new Monster();
                Combat combat = new Combat();
                combat.startCombat(chosenCharacter, monster);
                gameMap.displayMap(); // Afficher la carte après le mouvement


            } else if (currentTile.equals("O")) {
                System.out.println("There is an obstacle in the way.");
                Obstacle obstacle = new Obstacle();
                Combat combat = new Combat();
                combat.startObstacleCombat(chosenCharacter, obstacle);
                gameMap.displayMap();
            }
                else if (currentTile.equals("S")) {
                    System.out.println("Welcome to the Weapon Store");

                weaponStore.printWeapons(chosenCharacter);

            } else if (currentTile.equals("E")) {
                System.out.println("Congratulations, you've reached the exit!");
                break; // Terminer la boucle car le joueur a atteint la sortie
            }
        }

        // Fermer le scanner pour éviter les fuites de ressources
        scanner.close();
    }
}