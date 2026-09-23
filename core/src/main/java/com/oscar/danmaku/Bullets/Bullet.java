package com.oscar.danmaku.Bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Bullet {
    private float x;
    private float y;
    private float speed;
    private float width;
    private float height;

    public Bullet(float x, float y, float speed, float width, float height){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void move(float delta) {
        y += speed*delta;
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

        if (x - (height/2) < 0) {
            return true;
        }

        if (x + (width/2) > screenWidth) {
            return true;
        }

        return false;
    }
}
