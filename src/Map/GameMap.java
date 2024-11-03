package Map;

public class GameMap {
    private String[][] map;          // Carte pour affichage
    private String[][] referenceMap;  // Carte de référence
    private int playerX;
    private int playerY;

    public GameMap(int width, int height) {
        map = new String[height][width];
        referenceMap = new String[height][width];
        initializeMap();
        playerX = 0; // Position de départ du joueur
        playerY = 0; // Position de départ du joueur
    }

    private void initializeMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == map.length - 1 && j == map[i].length - 1) {
                    referenceMap[i][j] = "E"; // E pour la sortie
                } else if (Math.random() < 0.2) {
                    referenceMap[i][j] = "M"; // M pour les monstres
                } else if (Math.random() < 0.1) {
                    referenceMap[i][j] = "O"; // O pour les obstacles
                } else {
                    referenceMap[i][j] = "."; // . pour un espace vide
                }
            }
        }
    }

    public void displayMap() {
        // Afficher la carte avec la position du joueur superposée
        for (int i = 0; i < map.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < map[i].length; j++) {
                if (i == playerY && j == playerX) {
                    System.out.print("P | "); // Afficher "P" pour la position du joueur
                } else {
                    System.out.print(referenceMap[i][j] + " | "); // Afficher le contenu de la carte de référence
                }
            }
            System.out.println();

            System.out.print("   ");
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("---"); // Ligne de séparation
            }
            System.out.println();
        }
    }

    public boolean movePlayer(String direction) {
        System.out.println("Current position before moving: (" + playerX + ", " + playerY + ")");

        switch (direction.toLowerCase()) {
            case "up":
                if (playerY > 0) playerY--;
                break;
            case "down":
                if (playerY < map.length - 1) playerY++;
                break;
            case "left":
                if (playerX > 0) playerX--;
                break;
            case "right":
                if (playerX < map[0].length - 1) playerX++;
                break;
            default:
                System.out.println("Invalid direction.");
                return false;
        }

        return true;
    }

    public String getCurrentTile() {
        return referenceMap[playerY][playerX];
    }
}
