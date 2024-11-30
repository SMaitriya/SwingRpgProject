package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameMapPanel extends JPanel {
    private String[][] referenceMap;
    private int playerX;
    private int playerY;
    private final int tileSize = 32; // Taille des cases (en pixels)
    private Image playerImage; // Image du joueur


    public GameMapPanel(int playerX, int playerY) {
        ImageIcon playerIcon = new ImageIcon("src/Personnage/Hurt.png");
        playerImage = playerIcon.getImage(); // Convertir l'ImageIcon en Image
        // Initialisation de la carte directement dans GameMapPanel
        referenceMap = new String[][]{
                {" ", "M", "O", " ", "M", " ", "S", " ", "O", " "},
                {" ", " ", " ", "M", "O", " ", " ", "M", " ", " "},
                {"O", " ", "M", " ", " ", "O", " ", "M", " ", " "},
                {" ", " ", "M", "O", "S", " ", " ", " ", "M", " "},
                {"M", "O", " ", " ", "M", " ", "O", "M", " ", "S"},
                {" ", "S", "O", " ", "M", " ", " ", "O", " ", " "},
                {" ", "M", "O", " ", "M", " ", "O", "M", " ", " "},
                {"M", " ", "M", " ", "O", " ", "M", " ", " ", " "},
                {" ", " ", "O", "M", " ", "M", "O", " ", "O", "O"},
                {" ", "M", " ", " ", " ", "M", "O", " ", " ", "E"}
        };

        this.playerX = playerX;
        this.playerY = playerY;
        System.out.println("test");  // Affiche la position du joueur




        // Ajouter un KeyListener pour capturer les entrées du clavier
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));  // Affiche la touche pressée
                movePlayer(e);
            }
        });
        setFocusable(true);  // Permet au panneau de recevoir les événements de touche
        requestFocusInWindow(); // Demande le focus sur le panneau

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dessiner la carte
        for (int i = 0; i < referenceMap.length; i++) {
            for (int j = 0; j < referenceMap[i].length; j++) {
                int x = j * tileSize;
                int y = i * tileSize;

                // Dessiner le sol
                g2d.setColor(new Color(135, 206, 250, 128));
                g2d.fillRect(x, y, tileSize, tileSize);

                // Dessiner les murs, obstacles, etc.
                switch (referenceMap[i][j]) {
                    case "M":
                        g2d.setColor(Color.RED);
                        g2d.fillRect(x, y, tileSize, tileSize);
                        break;
                    case "O":
                        g2d.setColor(Color.GRAY);
                        g2d.fillRect(x, y, tileSize, tileSize);
                        break;
                    case "E":
                        g2d.setColor(Color.GREEN);
                        g2d.fillRect(x, y, tileSize, tileSize);
                        break;
                    case "S":
                        g2d.setColor(Color.YELLOW);
                        g2d.fillRect(x, y, tileSize, tileSize);
                        break;
                    default:
                        break;
                }

                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, tileSize, tileSize);
            }
        }

        // Dessiner le personnage
        int playerXPos = playerX * tileSize;
        int playerYPos = playerY * tileSize;
        g2d.setColor(Color.BLUE);
        g.drawImage(playerImage, playerXPos, playerYPos, tileSize, tileSize, this);
    }

    // Méthode pour déplacer le joueur
    // Méthode pour déplacer le joueur
    private void movePlayer(KeyEvent e) {
        int newX = playerX;
        int newY = playerY;
        System.out.println("Before move - X: " + playerX + ", Y: " + playerY);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                newY = Math.max(0, playerY - 1);
                break;
            case KeyEvent.VK_DOWN:
                newY = Math.min(referenceMap.length - 1, playerY + 1);
                break;
            case KeyEvent.VK_LEFT:
                newX = Math.max(0, playerX - 1);
                break;
            case KeyEvent.VK_RIGHT:
                newX = Math.min(referenceMap[0].length - 1, playerX + 1);
                break;
        }

        // Vérifier si la case cible est un obstacle
        if (referenceMap[newY][newX].equals("O")) {
            System.out.println("Can't move to the obstacle!");  // Afficher le message dans la console
            return;  // Ne pas déplacer le joueur si c'est un obstacle
        }

        // Vérifier si la case cible est la fin du jeu
        if (referenceMap[newY][newX].equals("E")) {
            // Afficher un message de fin de jeu
            JOptionPane.showMessageDialog(this, "Game Finished!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

            // Stopper le jeu (par exemple, désactiver les entrées du clavier et ne plus bouger le joueur)
            setFocusable(false);
            return;  // Ne pas déplacer le joueur si c'est la fin du jeu
        }

        // Si la case cible est valide (ni obstacle ni fin de jeu), déplacer le joueur
        if (newX != playerX || newY != playerY) {
            playerX = newX;
            playerY = newY;
            System.out.println("Player Position: X = " + playerX + ", Y = " + playerY);  // Affiche la position du joueur
            repaint();  // Repeindre le panneau après le déplacement du joueur
        }
    }


}
