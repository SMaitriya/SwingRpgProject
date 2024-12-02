package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Personnage.Character;


public class GameMapPanel extends JPanel {
    private String[][] referenceMap;
    private int playerX;
    private int playerY;
    private final int tileSize = 32;
    private Image playerImage;
    private Image monsterImage;
    private Image shopImage;
    private Image exitImage;
    private Image backgroundImage;
    private Image wallImage;
    private Character characterInfo;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JLabel inventoryLabel;


    // Nouveau JLabel pour afficher l'or du personnage
    private JLabel goldLabel;

    public GameMapPanel(int playerX, int playerY, Character characterInfo, CardLayout cardLayout, JPanel mainPanel) {
        this.characterInfo = characterInfo;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        ImageIcon playerIcon = new ImageIcon("src/Album/Hurt.png");
        ImageIcon monsterIcon = new ImageIcon("src/Album/Minotaur_03_Taunt_000.png");
        ImageIcon shopIcon = new ImageIcon("src/Album/store.png");
        ImageIcon exitIcon = new ImageIcon("src/Album/end.png");
        ImageIcon wallIcon = new ImageIcon("src/Album/2110.w032.n003.136B.p1.136.jpg");

        monsterImage = monsterIcon.getImage();
        wallImage = wallIcon.getImage();
        exitImage = exitIcon.getImage();
        shopImage = shopIcon.getImage();
        playerImage = playerIcon.getImage();
        ImageIcon bgIcon = new ImageIcon("src/Album/1601.m10.i311.n029.S.c10.164511620 Seamless green grass vector pattern.jpg");
        backgroundImage = bgIcon.getImage();

        referenceMap = new String[][]{
                {" ", "O", "O", " ", " ", " ", " ", " ", "O", " ", " ", "O", "O", "S"},
                {" ", "O", " ", "M", "O", " ", " ", "M", " ", " ", "M", "O", " ", " "},
                {" ", " ", "M", " ", " ", "O", " ", " ", " ", " ", "O", " ", " ", " "},
                {" ", " ", " ", "O", " ", " ", " ", " ", "O", "O", " ", "O", " ", " "},
                {" ", "O", " ", " ", " ", " ", "O", "M", " ", " ", " ", " ", " ", " "},
                {" ", "O", "O", " ", " ", " ", " ", "O", "O", "O", " ", " ", "O", " "},
                {" ", " ", "O", " ", " ", " ", "O", " ", " ", " ", "O", " ", "O", " "},
                {"M", " ", "M", " ", "O", " ", "M", " ", " ", " ", "M", " ", " ", " "},
                {" ", " ", "O", "M", " ", " ", "O", " ", "O", "O", "O", " ", "O", "M"},
                {"S", "M", " ", " ", " ", "M", "O", " ", "M", " ", " ", "O", "O", "E"}
        };


        this.playerX = playerX;
        this.playerY = playerY;


        // Créer et ajouter le panneau d'informations
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(200, 200, 200)); // Fond pour le contraste

        // Ajouter les informations de l'or
        goldLabel = new JLabel("Gold: " + characterInfo.getGold());
        goldLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(goldLabel, BorderLayout.SOUTH);

        // Créer et ajouter l'inventaire
        JPanel inventoryPanel = createInventoryPanel();
        infoPanel.add(inventoryPanel, BorderLayout.CENTER);

        // Ajouter le panneau d'informations à droite de la carte
        add(infoPanel, BorderLayout.SOUTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                movePlayer(e);
            }
        });
        setFocusable(true);
        requestFocusInWindow();
    }

    // Méthode pour dessiner la carte
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dessiner la carte
        for (int i = 0; i < referenceMap.length; i++) {
            for (int j = 0; j < referenceMap[i].length; j++) {
                int x = j * tileSize;
                int y = i * tileSize;

                g2d.drawImage(backgroundImage, x, y, tileSize, tileSize, this);

                switch (referenceMap[i][j]) {
                    case "M":
                        g2d.drawImage(monsterImage, x, y, tileSize, tileSize, this);
                        break;
                    case "O":
                        g2d.drawImage(wallImage, x, y, tileSize, tileSize, this);
                        break;
                    case "E":
                        g2d.drawImage(exitImage, x, y, tileSize, tileSize, this);
                        break;
                    case "S":
                        g2d.drawImage(shopImage, x, y, tileSize, tileSize, this);
                        break;
                    default:
                        break;
                }
            }
        }

        int playerXPos = playerX * tileSize;
        int playerYPos = playerY * tileSize;
        g.drawImage(playerImage, playerXPos, playerYPos, tileSize, tileSize, this);
    }

    // Méthode pour déplacer le joueur
    private void movePlayer(KeyEvent e) {
        int newX = playerX;
        int newY = playerY;

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

        if (referenceMap[newY][newX].equals("O")) {
            return;
        }

        if (referenceMap[newY][newX].equals("E")) {
            JOptionPane.showMessageDialog(this, "Game Finished!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            setFocusable(false);
            return;
        }
        if (referenceMap[newY][newX].equals("S")) {

            MapStore mapStorePage = new MapStore(characterInfo, cardLayout, mainPanel);
            mainPanel.add(mapStorePage, "MapStore");
            add(mainPanel, BorderLayout.SOUTH);
            mainPanel.revalidate();
            mainPanel.repaint();
            cardLayout.show(mainPanel, "MapStore");

        }

        // Mettre à jour l'or du personnage après chaque déplacement
        goldLabel.setText("Gold: " + characterInfo.getGold());
        updateInventory();  // Mise à jour de l'inventaire après déplacement


        if (newX != playerX || newY != playerY) {
            playerX = newX;
            playerY = newY;
            repaint();
        }
    }


    // Méthode pour créer le panneau d'inventaire
    private JPanel createInventoryPanel() {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.setBackground(new Color(220, 220, 220)); // Couleur de fond

        inventoryLabel = new JLabel("Inventory: " + characterInfo.getInventory().toString());
        inventoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inventoryPanel.add(inventoryLabel, BorderLayout.SOUTH);

        return inventoryPanel;
    }

    // Méthode pour mettre à jour l'inventaire
    public void updateInventory() {
        inventoryLabel.setText("Inventory: " + characterInfo.getInventory().toString());
        revalidate();
        repaint();
    }


}
