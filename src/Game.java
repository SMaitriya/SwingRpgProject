import Armes.WeaponSelection;
import Armes.WeaponStore;
import GameCore.GameLogic;
import GameCore.GameMap;
import Personnage.*;
import Personnage.Character;

public class Game {
    public static void main(String[] args) {

        Character chosenCharacter = CharacterCreation.createCharacter();

        // Continuer avec le reste du jeu en utilisant 'chosenCharacter'
        System.out.println(chosenCharacter.toString());

        // Création du magasin d'armes
        WeaponStore weaponStore = new WeaponStore();

        // Affichage des armes disponibles pour le personnage
        weaponStore.printWeapons(chosenCharacter);
        // Sélection de l'arme pour le personnage
        WeaponSelection.selectWeapon(chosenCharacter, weaponStore);

        // Poursuite du jeu avec le personnage et son arme sélectionnée
        System.out.println(chosenCharacter.toString());

        // Création de la map

        GameMap gameMap = new GameMap(5, 5);
        gameMap.displayMap();

        // Initialisation de la logic

        GameLogic gameLogic = new GameLogic(gameMap, chosenCharacter, weaponStore);

        // Game Start
        gameLogic.startGame();
    }
}


