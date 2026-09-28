package com.oscar.danmaku.Bullets;

import com.badlogic.gdx.Gdx;
import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Entities.Faction;

public class Bullet {
    private float x;
    private float y;
    private float speed;
    private float width;
    private float height;
    private Faction faction;
    private float damage;
    private float directionX;
    private float directionY;
    private BulletMovement movement;

    public Bullet(float x, float y, float speed, float width, float height, Faction faction, float damage, float directionX, float directionY, BulletMovement movement){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.faction = faction;
        this.damage = damage;
        this.directionX = directionX;
        this.directionY = directionY;
        this.movement = movement;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDirectionX() {
        return directionX;
    }

    public float getDirectionY() {
        return directionY;
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

    public float getDamage() {
        return damage;
    }

    public void move(float delta) {
        movement.update(this, delta);
    }

    public boolean isOffScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        if (y - (height/2) > screenHeight) {
            return true;
        }

        if (y + (height/2) < 0) {
            return true;
        }

        if (x - (width/2) < 0) {
            return true;
        }

        if (x + (width/2) > screenWidth) {
            return true;
        }

        return false;
    }
}
