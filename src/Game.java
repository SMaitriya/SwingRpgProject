import Destroy.Destructible;
import Map.GameMap;
import Armes.WeaponStore;
import Personnage.Character;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) throws CloneNotSupportedException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a name: ");
        String name = scanner.nextLine();

        // Crée un personnage avec un nom et des caractéristiques initiales
        Character player = new Character(name, 100) {
            @Override
            public void specialAttack(Destructible target) {

            }
        };

        System.out.println("Welcome, " + player.getName() + "!");


        Character class = new Character()







        WeaponStore store = new WeaponStore();
        store.printWeapons();


        // Créez la carte
        GameMap gameMap = new GameMap(5, 5); // Crée une carte de 5x5

        // Boucle principale du jeu
        boolean running = true;
        while (running) {
            gameMap.displayMap(); // Affiche la carte

            String currentTile = gameMap.getCurrentTile();
            if (currentTile.equals("E")) {
                System.out.println("Vous avez atteint la sortie ! Vous gagnez !");
                break;
            } else if (currentTile.equals("M")) {
                System.out.println("Vous avez rencontré un monstre !");
                // Ajoutez votre logique de combat ici
            } else if (currentTile.equals("O")) {
                System.out.println("Vous êtes bloqué par un obstacle !");
                // Logique pour détruire l'obstacle peut être ajoutée ici
            }

            System.out.print("Entrez une direction (haut, bas, gauche, droite): ");
            String direction = scanner.nextLine();
            gameMap.movePlayer(direction);
        }

        scanner.close(); // Ferme le scanner à la fin
    }
}
