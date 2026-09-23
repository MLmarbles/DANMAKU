package com.oscar.danmaku.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public abstract class Entity{
    private String name;
    private float health;
    private float maxHealth;
    private float defence;
    private float attackPower;

    private float x;
    private float y;
    private float speed;

    private float entityWidth;
    private float entityHeight;

    public Entity(float x, float y, float entityWidth, float entityHeight, float speed, String name, float health, float maxHealth, float defence, float attackPower) {
        this.x = x;
        this.y = y;
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
        this.speed = speed;

        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.defence = defence;
        this.attackPower = attackPower;
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

    public float getEntityWidth() {
        return entityWidth;
    }

    public float getEntityHeight() {
        return entityHeight;
    }

    public void move(float xAmount, float yAmount) {
        x += xAmount;
        y += yAmount;
    }

}