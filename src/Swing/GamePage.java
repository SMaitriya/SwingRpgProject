package Swing;
import java.util.List;
import java.util.ArrayList;
import Armes.Weapon;
import Personnage.Character;

import javax.swing.*;
import java.awt.*;

public class GamePage extends JPanel {
    private Character characterInfo;
    private Weapon selectedWeapon;
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
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Ajouter le panneau de la carte au centre
        GameMapPanel gameMapPanel = new GameMapPanel(playerX, playerY, characterInfo, cardLayout, mainPanel);
        add(gameMapPanel, BorderLayout.CENTER);


    }
}

