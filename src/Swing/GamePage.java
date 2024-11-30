package Swing;

import Armes.Weapon;
import Personnage.Character;

import javax.swing.*;
import java.awt.*;

public class GamePage extends JPanel {
    private Character characterInfo;  // Conformité à la convention de nommage
    private Weapon selectedWeapon;

    // Déclaration des attributs playerX et playerY
    private int playerX;
    private int playerY;

    // Constructeur
    public GamePage(Character characterInfo, Weapon selectedWeapon, int playerX, int playerY) {
        // Vérification des valeurs nulles
        if (characterInfo == null || selectedWeapon == null) {
            throw new IllegalArgumentException("Character or Weapon cannot be null");
        }

        this.characterInfo = characterInfo;
        this.selectedWeapon = selectedWeapon;
        this.playerX = playerX;
        this.playerY = playerY;

        // Initialisation du Layout
        setLayout(new BorderLayout());

        // Exemple d'affichage de l'arme sélectionnée
        JLabel weaponLabel = new JLabel("Selected Weapon: " + selectedWeapon.getName());
        this.add(weaponLabel, BorderLayout.NORTH);

        // Ajouter le panneau de la carte
        GameMapPanel gameMapPanel = new GameMapPanel(playerX, playerY);  // Assurez-vous que cette classe existe
        add(gameMapPanel, BorderLayout.CENTER);  // Pas besoin de re-spécifier BorderLayout.CENTER
    }
}
