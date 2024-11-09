package GameCore;
import java.util.Scanner;
import Destroy.Monster;
import java.util.Random;
import Destroy.Obstacle;
import Personnage.Character;

public class Combat {
    private final Scanner scanner = new Scanner(System.in);

    public void startCombat(Character player, Monster monster) {
        System.out.println("A battle begins with " + monster.getClass().getSimpleName() + "!");

        while (player.getHealth() > 0 && monster.getHealth() > 0) {
            System.out.println("\nYour turn!");
            System.out.println("Choose an action: 1) Attack  2) Special Attack");

            int choice = scanner.nextInt();
            double damage = 0;

            // Action du joueur
            if (choice == 1) {
                // Attaque standard avec l'arme équipée
                System.out.println("You attacked the monster with " + player.getEquippedWeapon().getName() + " and dealt " + player.getEquippedWeapon().getDamage() + " damage!");
                player.getEquippedWeapon().attack(monster);

            } else if (choice == 2) {
                // Attaque spéciale
                player.specialAttack(monster);

            } else {
                System.out.println("Invalid choice. Turn skipped.");
            }

            // Vérifier si le monstre est vaincu
            if (monster.getHealth() <= 0) {
                System.out.println("You defeated the monster!");
                System.out.println("You get 20 hp and 10 gold !");
                player.addGold(10);
                player.addHealth(20);
                System.out.println(player);
                break;
            }

            // Tour du monstre
            Random random = new Random();
            System.out.println("\nThe monster attacks!");
            damage = 6 + random.nextInt(10);  // 10 correspond à l'écart entre 6 et 15
            player.getDamage(damage);
            System.out.println("The monster dealt " + damage + " damage to you!");

            // Vérifier si le joueur est vaincu
            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the monster.");
                System.out.println("GAME");
                System.out.println("OVER");
                System.exit(0);
                break;

            }

            // Afficher l'état de santé actuel de chaque participant
            System.out.println("\nCurrent Status:");
            System.out.println(player);
            System.out.println("Monster has " + monster.getHealth() + " HP left.");
        }
    }

    public void startObstacleCombat(Character player, Obstacle obstacle) {
        System.out.println("You encounter an obstacle !");
        System.out.println("You need to destroy it to proceed.");

        while (obstacle.getHealth() > 0) {
            System.out.println("\nChoose an action: 1) Attack  2) Special Attack");

            int choice = scanner.nextInt();

            // Action du joueur
            if (choice == 1) {
                // Attaque standard avec l'arme équipée
                System.out.println("You attacked the obstacle with " + player.getEquippedWeapon().getName() + " and dealt " + player.getEquippedWeapon().getDamage() + " damage!");
                player.getEquippedWeapon().attack(obstacle);

            } else if (choice == 2) {
                // Attaque spéciale
                player.specialAttack(obstacle);

            } else {
                System.out.println("Invalid choice. Turn skipped.");
            }

            // Vérifier si l'obstacle est détruit
            if (obstacle.getHealth() <= 0) {
                System.out.println("You successfully destroyed the obstacle!");
                System.out.println("You get 10 hp and 5 gold !");
                player.addGold(5);
                player.addHealth(10);
                System.out.println(player);

                break;
            }

            // Afficher l'état de l'obstacle
            System.out.println("\nCurrent Status:");
            System.out.println("Obstacle has " + obstacle.getHealth() + " HP left.");
        }

    }
}
