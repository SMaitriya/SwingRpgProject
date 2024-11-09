package Armes;
import Personnage.Character;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WeaponSelection {

    public static void selectWeapon(Character chosenCharacter, WeaponStore weaponStore) {
        Scanner scanner = new Scanner(System.in);


        // Permettre au joueur de choisir et d'acheter une arme
        while (chosenCharacter.getEquippedWeapon() == null) {
            System.out.print("Enter the number of the weapon you want to buy: ");
            int weaponChoice;

            try {
                weaponChoice = scanner.nextInt(); // Essaie de lire un entier
                if (weaponChoice >= 0 && weaponChoice < weaponStore.getWeaponsForClass(chosenCharacter).size()) {
                    Weapon selectedWeapon = weaponStore.getWeaponsForClass(chosenCharacter).get(weaponChoice);

                    // Acheter et équiper l'arme
                    chosenCharacter.buyWeapon(selectedWeapon);
                } else {
                    System.out.println("Invalid choice. Please choose a valid weapon number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Consommer la ligne incorrecte pour éviter une boucle infinie
            }
        }

        // Afficher l'arme actuellement équipée
        if (chosenCharacter.getEquippedWeapon() != null) {
            System.out.println("Currently equipped weapon: " + chosenCharacter.getEquippedWeapon());
        }
    }
}
