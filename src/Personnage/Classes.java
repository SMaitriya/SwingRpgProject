package Personnage;
import java.util.ArrayList;
import java.util.List;

public class Classes {

    // Méthode pour obtenir la liste des classes de personnages
    public static List<Character> getClasses() {
        List<Character> characters = new ArrayList<>();
        characters.add(new Warrior("", 100, "Warrior", 30));
        characters.add(new Mage("", 80, "Mage", 30));
        characters.add(new Archer("", 90, "Archer", 4));
        return characters;
    }

    // Méthode statique pour afficher les classes
    public static void printClasses(List<Character> characterClasses) {
        System.out.println("\nChoose your class:");
        for (int i = 0; i < characterClasses.size(); i++) {
            Character character = characterClasses.get(i);
            System.out.println("[" + i + "] " + character.asciiArt());
        }
    }
}
