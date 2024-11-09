import Armes.WeaponSelection;
import Armes.WeaponStore;
import GameCore.GameLogic;
import GameCore.GameMap;
import Personnage.*;
import Personnage.Character;

public class Game {
    public static void main(String[] args) {

        // Création du personnage avec sa classe
        Character chosenCharacter = CharacterCreation.createCharacter();
        System.out.println(chosenCharacter);

        // Création du magasin d'armes
        WeaponStore weaponStore = new WeaponStore();

        // Affichage des armes disponibles pour le personnage
        weaponStore.printWeapons(chosenCharacter);
        // Sélection de l'arme pour le personnage
        WeaponSelection.selectWeapon(chosenCharacter, weaponStore);
        System.out.println(chosenCharacter);

        // Création de la map
        GameMap gameMap = new GameMap(8, 8);
        gameMap.displayMap();

        // Initialisation de la logic
        GameLogic gameLogic = new GameLogic(gameMap, chosenCharacter, weaponStore);

        // Game Start
        gameLogic.startGame();
    }
}


