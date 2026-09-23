package com.oscar.danmaku.Entities;

public abstract class Entity{
    private String name;
    private int health;
    private int maxHealth;
    private int defence;
    private int attackPower;
    private float x;
    private float y;

    public Entity(String name, int health, int maxHealth, int defence, int attackPower) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.defence = defence;
        this.attackPower = attackPower;
    }

    public void attack(Entity target) {
        int damage = attackPower - (target.health * defence);
        target.health = Math.max(0, target.health - damage);
    }


}