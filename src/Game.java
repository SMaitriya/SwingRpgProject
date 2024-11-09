import Armes.Weapon;
import Armes.WeaponStore;
import Destroy.Monster;
import Destroy.Obstacle;
import Map.Combat;
import Map.GameMap;
import Personnage.*;
import Personnage.Character;

import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Character chosenCharacter = CharacterCreation.createCharacter();

        // Continuer avec le reste du jeu en utilisant 'chosenCharacter'
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
        GameMap gameMap = new GameMap(10, 10); // Exemple de taille de carte
        System.out.println("Map initialized. Here is your starting position:");
        gameMap.displayMap(); // Afficher la carte initiale

// Boucle de déplacement sur la carte
        String direction;
        while (true) {
            System.out.print("\n(M for Monsters, O for Obstacles , S for Weapon Shop and E for Exit)");
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
            // Si il s'agit d'un monstre , un obstacle , du shop ou de la sortie/

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
                System.out.print("You have " + chosenCharacter.getGold() + " gold");
                System.out.print("\nEnter the number of the weapon you want to buy or 'e' to exit: ");

                String input = scanner.next();  // Lire l'entrée en tant que String

                if (input.equalsIgnoreCase("e")) {
                    System.out.println("Have a nice day!");
                    gameMap.displayMap(); // Afficher la carte après le mouvement


                } else {
                    try {
                        int weaponChoice = Integer.parseInt(input);  // Tenter de convertir en entier
                        if (weaponChoice >= 0 && weaponChoice < weaponStore.getWeaponsForClass(chosenCharacter).size()) {
                            Weapon selectedWeapon = weaponStore.getWeaponsForClass(chosenCharacter).get(weaponChoice);
                            chosenCharacter.buyWeapon(selectedWeapon);
                            System.out.print("You have " + chosenCharacter.getGold() + " gold left\n\n");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number or 'e' to exit.");
                    }
                    gameMap.displayMap(); // Afficher la carte après le mouvement

                }



            } else if (currentTile.equals("E")) {
                System.out.println("Congratulations, you've reached the exit!");
                break; // Terminer la boucle car le joueur a atteint la sortie
            }
        }

        scanner.close();
    }
}