package Destroy;

public abstract class Destructible {
    private double health;

    public Destructible(double health) {
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public void hit(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println("Received damage: " + damage + ". Current health: " + health);
    }
}
