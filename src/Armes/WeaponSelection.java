package Armes;
import Personnage.Character;
import java.util.Scanner;

public class WeaponSelection {

    public static void selectWeapon(Character chosenCharacter, WeaponStore weaponStore) {
        Scanner scanner = new Scanner(System.in);


        // Permettre au joueur de choisir et d'acheter une arme
        while (chosenCharacter.getEquippedWeapon() == null) {
            System.out.print("Enter the number of the weapon you want to buy: ");
            int weaponChoice = scanner.nextInt();


            if (weaponChoice >= 0 && weaponChoice < weaponStore.getWeaponsForClass(chosenCharacter).size()) {
                Weapon selectedWeapon = weaponStore.getWeaponsForClass(chosenCharacter).get(weaponChoice);

                // Acheter et équiper l'arme
                chosenCharacter.buyWeapon(selectedWeapon);
            } else {
                System.out.println("Invalid choice.");
            }
        }

        // Afficher l'arme actuellement équipée
        if (chosenCharacter.getEquippedWeapon() != null) {
            System.out.println("Currently equipped weapon: " + chosenCharacter.getEquippedWeapon());
        }
    }
}
