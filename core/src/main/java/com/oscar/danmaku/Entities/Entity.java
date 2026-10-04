package com.oscar.danmaku.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Patterns.AttackPattern;

public abstract class Entity{
    private String name;
    private float health;
    private float maxHealth;
    private float defence;
    private float attackPower;

    private float x;
    private float y;
    private float speed;

    private float width;
    private float height;

    private Faction faction;

    private AttackPattern attackPattern;

    private int bombs = 1;

    public Entity(float x, float y, float width, float height, float speed, String name, float health, float maxHealth, float defence, float attackPower, Faction faction, AttackPattern attackPattern) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.defence = defence;
        this.attackPower = attackPower;
        this.faction = faction;
        this.attackPattern = attackPattern;
    }

    public abstract void update(float delta);
    public abstract boolean wantsToShoot();
    public abstract boolean wantsToBomb();

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    protected void setX(float x) {
        this.x = x;
    }

    protected void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Faction getFaction() {
        return faction;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public String getName() {
        return name;
    }

    public AttackPattern getAttackPattern() {
        return attackPattern;
    }

    public void setAttackPattern(AttackPattern attackPattern) {
        this.attackPattern = attackPattern;
    }

    public void move(float xAmount, float yAmount) {
        x += xAmount;
        y += yAmount;
    }

    public void takeDamage(float amount) {
        health = Math.max(0, health - amount);
    }

    public boolean isDead() {
        if (health <= 0) {
            return true;
        }
        return false;
    }

    public void shoot() {

    }
}