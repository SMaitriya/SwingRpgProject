package Swing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Armes.Weapon;
import Armes.WeaponStore;
import Personnage.Character;

public class ShopPage extends JPanel {
    private Character characterInfo;  // Référence au personnage
    private JComboBox<Weapon> weaponComboBox;  // ComboBox pour choisir une arme
    private WeaponStore weaponStore;  // Magasin d'armes
    private JLabel characterInfoLabel;  // Label pour afficher les infos du personnage
    private CardLayout cardLayout;  // CardLayout pour changer de page
    private JPanel mainPanel;  // Panel principal

    public ShopPage(Character characterInfo, CardLayout cardLayout, JPanel mainPanel) {
        this.characterInfo = characterInfo;
        this.weaponStore = new WeaponStore();  // Initialisation du magasin d'armes
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setupUI();  // Configurer l'interface utilisateur
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Label d'introduction
        JLabel shopLabel = new JLabel("Buy your weapon: ");
        shopLabel.setPreferredSize(new Dimension(100, 200));
        shopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shopLabel.setForeground(Color.RED);
        add(shopLabel, BorderLayout.NORTH);

        // ComboBox pour choisir une arme
        weaponComboBox = new JComboBox<>();
        populateWeaponComboBox();  // Remplir la ComboBox avec les armes disponibles
        add(weaponComboBox, BorderLayout.CENTER);

        // Label pour afficher les informations du personnage
        characterInfoLabel = new JLabel();
        updateCharacterInfoLabel();  // Mettre à jour les infos du personnage
        characterInfoLabel.setForeground(Color.RED);
        add(characterInfoLabel, BorderLayout.WEST);

        // Bouton pour confirmer le choix de l'arme
        JButton confirmWeaponButton = new JButton("Choose Weapon");
        confirmWeaponButton.setForeground(Color.RED);
        confirmWeaponButton.setBackground(Color.DARK_GRAY);
        confirmWeaponButton.addActionListener(e -> {
            Weapon selectedWeapon = (Weapon) weaponComboBox.getSelectedItem();
            if (selectedWeapon != null) {
                boolean purchaseSuccessful = characterInfo.buyWeapon(selectedWeapon);
                if (purchaseSuccessful) {
                    // Afficher un message de succès
                    JOptionPane.showMessageDialog(null,
                            "You successfully bought " + selectedWeapon.getName() + "!",
                            "Purchase Successful",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Mettre à jour les infos du personnage et passer à la page de jeu
                    updateCharacterInfoLabel();
                    GamePage gamePage = new GamePage(characterInfo, selectedWeapon, 0, 0);
                    mainPanel.add(gamePage, "GamePage");
                    cardLayout.show(mainPanel, "GamePage");
                }
            } else {
                // Afficher un message d'erreur si aucune arme n'est sélectionnée
                JOptionPane.showMessageDialog(null,
                        "No weapon selected.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Ajouter le bouton au bas de la page
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmWeaponButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void populateWeaponComboBox() {
        ArrayList<Weapon> availableWeapons = weaponStore.getWeaponsForClass(characterInfo);
        if (availableWeapons.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No weapons available for your class.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ajouter les armes disponibles à la ComboBox
        for (Weapon weapon : availableWeapons) {
            weaponComboBox.addItem(weapon);
        }

        // Personnaliser l'affichage des éléments dans la ComboBox
        weaponComboBox.setRenderer(new WeaponCellRenderer());
    }

    private void updateCharacterInfoLabel() {
        String characterName = characterInfo.getName();
        String characterClass = characterInfo.getCharacterClass();
        double characterGold = characterInfo.getGold();

        // Mettre à jour le label avec les infos du personnage
        characterInfoLabel.setText("<html>Name: " + characterName + "<br/>" +
                "Class: " + characterClass + "<br/>" +
                "Gold: " + characterGold + "</html>");
    }
}
