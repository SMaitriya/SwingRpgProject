package Swing;

import javax.swing.*;
import java.awt.*;
import Armes.Weapon;

public class WeaponCellRenderer extends JLabel implements ListCellRenderer<Weapon> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Weapon> list, Weapon weapon, int index, boolean isSelected, boolean cellHasFocus) {
        // Créer une chaîne HTML contenant l'art ASCII et les informations de l'arme
        String displayText = "<html><pre>" + weapon.asciiArt() + "</pre>" +
                "<b>" + weapon.getName() + "</b><br>" +
                "Damage: " + weapon.getDamage() + "<br>" +
                "Price: " + weapon.getPrice() + " gold" +
                "</html>";

        // Afficher le texte de l'arme
        setText(displayText);

        // Appliquer un fond coloré si l'élément est sélectionné
        if (isSelected) {
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
        setOpaque(true);

        return this;
    }
}
