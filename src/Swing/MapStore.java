package Swing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Armes.Weapon;
import Armes.WeaponStore;
import Personnage.Character;

public class MapStore extends JPanel {
    private Character characterInfo;  // Référence au personnage
    private JComboBox<Weapon> weaponComboBox;  // ComboBox pour choisir une arme
    private WeaponStore weaponStore;  // Magasin d'armes
    private JLabel characterInfoLabel;  // Label pour afficher l'info du personnage
    private JPanel characterInfoPanel;  // Panel pour contenir les informations du personnage
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GamePage currentGamePage;  // Ajouter cette variable à la classe principale


    public MapStore(Character characterInfo, CardLayout cardLayout, JPanel mainPanel) {
        this.characterInfo = characterInfo;
        this.weaponStore = new WeaponStore();  // Initialiser le magasin d'armes
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Label d'introduction
        JLabel shopLabel = new JLabel("WELCOME TO THE SHOP ");
        shopLabel.setPreferredSize(new Dimension(580, 210));
        shopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shopLabel.setForeground(Color.RED);
        add(shopLabel, BorderLayout.NORTH);

        // ComboBox pour afficher les armes disponibles
        weaponComboBox = new JComboBox<>();
        populateWeaponComboBox();
        add(weaponComboBox, BorderLayout.CENTER);

        // Initialisation du JLabel pour afficher les informations du personnage
        characterInfoLabel = new JLabel();
        updateCharacterInfoLabel();
        characterInfoLabel.setForeground(Color.RED);
        add(characterInfoLabel, BorderLayout.WEST);

        // Bouton pour confirmer le choix de l'arme
        JButton confirmWeaponButton = new JButton("BUY");
        confirmWeaponButton.setForeground(Color.RED);  // Changez la couleur du texte du bouton
        confirmWeaponButton.setBackground(Color.DARK_GRAY); // Couleur de fond du bouton

        confirmWeaponButton.addActionListener(e -> {
            Weapon selectedWeapon = (Weapon) weaponComboBox.getSelectedItem();
            if (selectedWeapon != null) {
                boolean purchaseSuccessful = characterInfo.buyWeapon(selectedWeapon);

                if (purchaseSuccessful) {
                    JOptionPane.showMessageDialog(null,
                            "You successfully bought " + selectedWeapon.getName() + "!",
                            "Purchase Successful",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Mettez à jour les informations du personnage
                    updateCharacterInfoLabel();

                    // Transition vers GamePage avec la nouvelle arme
                    cardLayout.show(mainPanel, "GamePage");
                    mainPanel.remove(this);  // Retire le JPanel actuel de l'interface
                    mainPanel.revalidate();
                    mainPanel.repaint();

                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "No weapon selected.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        });


        // Ajouter le bouton au bas de la page
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmWeaponButton);

        // Bouton "Annuler" pour revenir à la page précédente (ou fermer la page de magasin)
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.RED);  // Changez la couleur du texte du bouton
        cancelButton.setBackground(Color.DARK_GRAY); // Couleur de fond du bouton

        cancelButton.addActionListener(e -> {
            // Utiliser CardLayout pour retourner à la page précédente, par exemple à la page d'accueil
            mainPanel.remove(this);  // Retire le JPanel actuel de l'interface
            mainPanel.revalidate();
            mainPanel.repaint();

        });

        // Ajouter le bouton "Annuler" au panel des boutons
        buttonPanel.add(cancelButton);

        // Ajouter les boutons au panel principal
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void populateWeaponComboBox() {
        ArrayList<Weapon> availableWeapons = weaponStore.getWeaponsForClass(characterInfo);
        if (availableWeapons.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No weapons available for your class.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ajouter les armes au ComboBox
        for (Weapon weapon : availableWeapons) {
            weaponComboBox.addItem(weapon);
        }

        weaponComboBox.setRenderer(new WeaponCellRenderer());
    }

    private void updateCharacterInfoLabel() {
        String characterName = characterInfo.getName();  // Nom du personnage
        String characterClass = characterInfo.getCharacterClass();  // Classe du personnage
        double characterGold = characterInfo.getGold();  // Or actuel du personnage

        // Mise à jour du JLabel avec les nouvelles informations
        characterInfoLabel.setText("<html>Name: " + characterName + "<br/>" +
                "Class: " + characterClass + "<br/>" +
                "Gold: " + characterGold + "</html>");
    }


}
