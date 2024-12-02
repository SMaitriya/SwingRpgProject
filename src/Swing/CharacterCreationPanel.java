package Swing;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import Personnage.Archer;
import Personnage.Character;
import Personnage.Mage;
import Personnage.Warrior;

public class CharacterCreationPanel extends JPanel {
    private JTextField nameField;
    private JComboBox<String> classComboBox;
    private List<Character> characterClasses;
    private Character createdCharacter;
    private JLabel characterGoldLabel;  // Afficher l'or du personnage

    public CharacterCreationPanel(List<Character> characterClasses) {
        this.characterClasses = characterClasses;
        setupUI();
    }

    private void setupUI() {
        setBackground(Color.BLACK);

        // Champ pour entrer le nom du personnage
        JLabel nameLabel = new JLabel("Enter your character name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 25));
        nameField.setBackground(Color.WHITE);
        nameField.setFont(new Font("Arial", Font.BOLD, 12));
        nameField.setForeground(Color.BLACK);
        add(nameLabel);
        add(nameField);

        // ComboBox pour sélectionner la classe
        JLabel classLabel = new JLabel("Select your class:");
        classLabel.setForeground(Color.WHITE);
        classComboBox = new JComboBox<>();
        for (Character character : characterClasses) {
            classComboBox.addItem(character.getCharacterClass());
        }
        add(classLabel);
        add(classComboBox);
    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getSelectedClass() {
        return (String) classComboBox.getSelectedItem();
    }

    public boolean validateInputs() {
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (classComboBox.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a character class.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public Character getCreatedCharacter() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        int selectedIndex = classComboBox.getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex >= characterClasses.size()) {
            JOptionPane.showMessageDialog(null, "Please select a valid class", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Character selectedClass = characterClasses.get(selectedIndex);

        // Créer une instance spécifique de la classe de personnage
        Character createdCharacter = null;
        if (selectedClass instanceof Warrior) {
            createdCharacter = new Warrior(name, selectedClass.getHealth(), selectedClass.getCharacterClass(), 40);
        } else if (selectedClass instanceof Mage) {
            createdCharacter = new Mage(name, selectedClass.getHealth(), selectedClass.getCharacterClass(), 50);
        } else if (selectedClass instanceof Archer) {
            createdCharacter = new Archer(name, selectedClass.getHealth(), selectedClass.getCharacterClass(), 15);
        }

        if (createdCharacter != null) {
            createdCharacter.addGold(20); // Ajouter de l'or au personnage
        }

        return createdCharacter;
    }
}
