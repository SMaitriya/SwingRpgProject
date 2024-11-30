import javax.swing.*;
import java.awt.*;
import java.util.List;
import Personnage.Character;
import Personnage.Classes;
import Swing.CharacterCreationPanel;
import Swing.GamePage;
import Swing.ShopPage;

public class GameSwing {
    public static void main(String[] args) {
        // Créer la fenêtre principale
        JFrame frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Utilisation de CardLayout pour gérer plusieurs panneaux
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Créer le panneau de démarrage
        JPanel menuStartPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome To the Castle of Oblivion");
        titleLabel.setForeground(Color.RED);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuStartPanel.add(titleLabel, BorderLayout.NORTH);
        menuStartPanel.setBackground(Color.BLACK);

        // Obtenir les classes disponibles
        List<Character> characterClasses = Classes.getClasses();

        // Ajouter le panneau de création de personnage
        CharacterCreationPanel creationPanel = new CharacterCreationPanel(characterClasses);
        menuStartPanel.add(creationPanel, BorderLayout.CENTER);



        mainPanel.add(menuStartPanel, "MenuStart");


        // Ajouter le bouton pour démarrer le jeu
        JButton startButton = new JButton("Get inside the castle!");
        startButton.setPreferredSize(new Dimension(50, 200)); // Largeur : 200 px, Hauteur : 50 px

        startButton.addActionListener(e -> {
            if (creationPanel.validateInputs()) {
                // Récupérer le personnage créé dans le panneau de création
                Character character = creationPanel.getCreatedCharacter();
                if (character != null) {
                    // Passer le personnage au panneau ShopPage
                    ShopPage shopPage = new ShopPage(character, cardLayout, mainPanel);
                    mainPanel.add(shopPage, "ShopPage");
                    cardLayout.show(mainPanel, "ShopPage");

                } else {
                    JOptionPane.showMessageDialog(frame, "Character creation failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });




        menuStartPanel.add(startButton, BorderLayout.SOUTH);



        // Ajouter le panneau principal à la fenêtre
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}