package veikko.vanninen.lutemonht;

import java.io.Serializable;

public class Lutemon implements Serializable {

    protected String name;
    protected String color;
    protected int attack;
    protected int defence;
    protected int experience;
    protected int health;
    protected int maxHealth;
    protected int id;
    private static int idCounter = 0;
    protected int image;
    protected int wins;
    protected int loses;

    public Lutemon (String name, String color, int attack, int defence, int experience, int health, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defence = defence;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = getIdCounter();
        this.wins = wins;
        this.loses = loses;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }
    public int getDefence() {
        return defence;
    }
    public int getExperience() {
        return experience;
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getWins() {
        return wins;
    }
    public int getLoses() {
        return loses;
    }

    public int attack() {
        int attack = this.attack;
        return attack;
    }

    public void defence (Lutemon lutemon) {
        this.health = this.health + this.defence - lutemon.attack();
    }

    public int getId() {
        return id;
    }

    public int getIdCounter() {
        int id;
        id = idCounter++;
        return id;
    }

    public void setExperience(int experience){
        this.experience = this.experience + experience;
    }

    public void healHealth () {
        this.health = this.maxHealth;
    }

    public void setStats (int experience) {
        this.attack = this.attack + experience;
        //this.defence = this.defence + experience;
    }
    // Method for calculating wins.
    public void setWin (int win) {
        this.wins= this.wins + win;
    }
    // Method for calculating loses.
    public void setLoss (int loss) {
        this.loses = this.loses + loss;
    }
    // Method for getting lutemons image.
    public int getImage() {
        return image;
    }
}
