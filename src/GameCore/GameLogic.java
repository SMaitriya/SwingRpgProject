package GameCore;

import Armes.Weapon;
import Armes.WeaponStore;
import Destroy.Monster;
import Destroy.Obstacle;
import Personnage.Character;
import java.util.Scanner;

public class GameLogic {
    private final Scanner scanner = new Scanner(System.in);
    private final GameMap gameMap;
    private final Character chosenCharacter;
    private final WeaponStore weaponStore;

    public GameLogic(GameMap gameMap, Character chosenCharacter, WeaponStore weaponStore) {
        this.gameMap = gameMap;
        this.chosenCharacter = chosenCharacter;
        this.weaponStore = weaponStore;
    }

    public void startGame() {

        System.out.println("Map initialized. Here is your starting position:");


        String direction;
        while (true) {
            System.out.print("\n(M for Monsters, O for Obstacles , S for Weapon Shop and E for Exit)");
            System.out.print("\nEnter direction to move (z, s, q, d) or 'exit' to quit: ");
            direction = scanner.next();

            if (direction.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game. Thanks for playing!");
                break;
            }

            if (gameMap.movePlayer(direction)) {
                System.out.println("Moved " + direction + ".");
            } else {
                System.out.println("Cannot move in that direction.");
            }

            gameMap.displayMap();
            handleTileInteraction(gameMap.getCurrentTile());
        }
        scanner.close();
    }

    private void handleTileInteraction(String currentTile) {
        switch (currentTile) {
            case "M" -> handleMonsterEncounter();
            case "O" -> handleObstacleEncounter();
            case "S" -> handleWeaponStore();
            case "E" -> handleExit();
            default -> System.out.println("Nothing interesting here.");
        }
    }

    private void handleMonsterEncounter() {
        System.out.println("You encountered a monster!");
        Monster monster = new Monster();
        Combat combat = new Combat();
        combat.startCombat(chosenCharacter, monster);
        gameMap.displayMap();
    }

    private void handleObstacleEncounter() {
        Obstacle obstacle = new Obstacle();
        Combat combat = new Combat();
        combat.startObstacleCombat(chosenCharacter, obstacle);
        gameMap.displayMap();
    }

    private void handleWeaponStore() {
        System.out.println("Welcome to the Weapon Store");
        weaponStore.printWeapons(chosenCharacter);
        System.out.print("You have " + chosenCharacter.getGold() + " gold\n");
        System.out.print("Enter the number of the weapon you want to buy or 'e' to exit: ");

        String input = scanner.next();
        if (input.equalsIgnoreCase("e")) {
            System.out.println("Have a nice day!");
        } else {
            try {
                int weaponChoice = Integer.parseInt(input);
                if (weaponChoice >= 0 && weaponChoice < weaponStore.getWeaponsForClass(chosenCharacter).size()) {
                    Weapon selectedWeapon = weaponStore.getWeaponsForClass(chosenCharacter).get(weaponChoice);
                    chosenCharacter.buyWeapon(selectedWeapon);
                    System.out.println("You have " + chosenCharacter.getGold() + " gold left\n");
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'e' to exit.");
            }
        }
        gameMap.displayMap();
    }

    private void handleExit() {
        System.out.println("Congratulations, you've reached the exit!");
        scanner.close();
        System.exit(0);

    }

}
