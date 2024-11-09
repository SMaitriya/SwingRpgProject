package GameCore;

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
        boolean hasShop = false;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == map.length - 1 && j == map[i].length - 1) {
                    referenceMap[i][j] = "E"; // E pour la sortie
                } else if (Math.random() < 0.5) {
                    referenceMap[i][j] = "M"; // M pour les monstres
                } else if (Math.random() < 0.1) {
                    referenceMap[i][j] = "O"; // O pour les obstacles
                } else if (!hasShop && Math.random() < 0.05) {
                    referenceMap[i][j] = "S"; // S pour le magasin
                    hasShop = true;
                } else {
                    referenceMap[i][j] = " "; // . pour un espace vide
                }
            }
        }

        // Si aucun magasin n'a été placé, en ajouter un aléatoirement
        if (!hasShop) {
            placeRandomly("S");
        }
    }

    private void placeRandomly(String symbol) {
        int randomRow, randomCol;
        do {
            randomRow = (int) (Math.random() * map.length);
            randomCol = (int) (Math.random() * map[0].length);
        } while (!referenceMap[randomRow][randomCol].equals("."));
        referenceMap[randomRow][randomCol] = symbol;
    }

    public void displayMap() {
        // Afficher la carte avec la position du joueur superposée
        for (int i = 0; i < map.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < map[i].length; j++) {
                if (i == playerY && j == playerX) {
                    // P pour la position du joueur, couleur bleue
                    System.out.print("\033[34mP\033[0m | ");
                } else {
                    String tile = referenceMap[i][j];

                    // Couleurs en fonction du type de case
                    if (tile.equals("M")) {
                        // M pour les monstres, couleur rouge
                        System.out.print("\033[31mM\033[0m | ");
                    } else if (tile.equals("O")) {
                        // O pour les obstacles, couleur grise
                        System.out.print("\033[90mO\033[0m | ");
                    } else if (tile.equals("E")) {
                        // E pour la sortie, couleur verte
                        System.out.print("\033[32mE\033[0m | ");
                    } else if (tile.equals("S")) {
                        // S pour le magasin, couleur jaune
                        System.out.print("\033[33mS\033[0m | ");
                    } else {
                        // Pour les espaces vides, sans couleur
                        System.out.print(tile + " | ");
                    }
                }
            }
            System.out.println();

            System.out.print("   ");
            for (int j = 0; j < map[0].length; j++) {
                System.out.print("----"); // Ligne de séparation
            }
            System.out.println();
        }
    }



    public boolean movePlayer(String direction) {
        System.out.println("Current position before moving: (" + playerX + ", " + playerY + ")");

        switch (direction.toLowerCase()) {
            case "z":
                if (playerY > 0) playerY--;
                break;
            case "s":
                if (playerY < map.length - 1) playerY++;
                break;
            case "q":
                if (playerX > 0) playerX--;
                break;
            case "d":
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
