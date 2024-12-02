import javax.swing.*;
import java.awt.*;
import java.util.List;
import Personnage.Character;
import Personnage.Classes;
import Swing.CharacterCreationPanel;

import Swing.MapStore;
import Swing.ShopPage;

public class GameSwing {
    public static void main(String[] args) {
        // Créer la fenêtre principale
        JFrame frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1700, 600);

        // Utiliser CardLayout pour gérer les différents panneaux
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Créer le panneau de démarrage avec le titre et le panneau de création de personnage
        JPanel menuStartPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome To the Castle of Oblivion");
        titleLabel.setForeground(Color.RED);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuStartPanel.add(titleLabel, BorderLayout.NORTH);
        menuStartPanel.setBackground(Color.BLACK);

        List<Character> characterClasses = Classes.getClasses(); // Récupérer les classes de personnages
        CharacterCreationPanel creationPanel = new CharacterCreationPanel(characterClasses);
        menuStartPanel.add(creationPanel, BorderLayout.CENTER);

        // Ajouter le panneau de démarrage au mainPanel
        mainPanel.add(menuStartPanel, "MenuStart");

        // Créer et configurer le bouton de démarrage
        JButton startButton = new JButton("Get inside the castle!");
        startButton.setPreferredSize(new Dimension(50, 200));

        startButton.addActionListener(e -> {
            if (creationPanel.validateInputs()) {
                Character character = creationPanel.getCreatedCharacter();
                if (character != null) {
                    // Ajouter le panneau du magasin et de la carte
                    ShopPage shopPage = new ShopPage(character, cardLayout, mainPanel);
                    mainPanel.add(shopPage, "ShopPage");
                    cardLayout.show(mainPanel, "ShopPage");

                    MapStore mapStore = new MapStore(character, cardLayout, mainPanel);
                    mainPanel.add(mapStore, "MapStore");
                } else {
                    JOptionPane.showMessageDialog(frame, "Character creation failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ajouter le bouton au panneau de démarrage
        menuStartPanel.add(startButton, BorderLayout.SOUTH);

        // Ajouter le panneau principal à la fenêtre
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
