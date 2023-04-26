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

    public Lutemon (String name, String color, int attack, int defence, int experience, int health, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defence = defence;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = idCounter();
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

    public int idCounter() {
        int id;
        id = idCounter++;
        return id;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public void setExperience(int experience){
        this.experience = this.experience + experience;
    }

    public void healHealth () {
        this.health = this.maxHealth;
    }

    public void setStats (int experience) {
        this.attack = this.attack + experience;
        this.defence = this.defence + experience;
    }
}
