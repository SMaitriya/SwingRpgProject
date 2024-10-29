package Map;

public class GameMap {
    private String[][] map;
    private int playerX;
    private int playerY;

    public GameMap(int width, int height) {
        map = new String[height][width];
        initializeMap();
        playerX = 0; // Position de départ du joueur
        playerY = 0; // Position de départ du joueur
    }

    private void initializeMap() {
        // Remplir la carte avec des éléments
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == map.length - 1 && j == map[i].length - 1) {
                    map[i][j] = "E"; // E pour la sortie
                } else if (Math.random() < 0.2) {
                    map[i][j] = "M"; // M pour les monstres
                } else if (Math.random() < 0.1) {
                    map[i][j] = "O"; // O pour les obstacles
                } else {
                    map[i][j] = "."; // . pour un espace vide
                }
            }
        }
        map[playerY][playerX] = "P"; // P pour la position du joueur
    }

    public void displayMap() {
        // Afficher la carte avec des séparateurs
        for (int i = 0; i < map.length; i++) {
            System.out.print(" | "); // Espace pour les séparateurs
            for (String tile : map[i]) {
                System.out.print(tile + " | "); // Afficher la tuile avec des séparateurs
            }
            System.out.println(); // Nouvelle ligne

            // Afficher la ligne de séparation
            System.out.print("   ");
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("---"); // Ligne de séparation
            }
            System.out.println();
        }
    }

    public boolean movePlayer(String direction) {
        map[playerY][playerX] = "."; // Efface la position actuelle du joueur
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
                System.out.println("Direction invalide.");
                return false;
        }
        map[playerY][playerX] = "P"; // Met à jour la position du joueur
        return true;
    }

    public String getCurrentTile() {
        return map[playerY][playerX];
    }
}
