package com.oscar.danmaku.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player {
    private float x;
    private float y;
    private float speed = 250f;

    private float playerWidth;
    private float playerHeight;

    public Player(float x, float y, float playerWidth, float playerHeight){
        this.x = x;
        this.y = y;
        this.playerWidth = playerWidth;
        this.playerHeight = playerHeight;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void move(float delta) {

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (!(speed + (y + playerHeight/2) >= height)) {
                y += speed * delta;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!(speed - (x - playerWidth/2) <= 0)) {
                x -= speed * delta;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (!(speed - (y - playerHeight/2) <= 0)) {
                y -= speed * delta;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (!(speed + (x + playerWidth/2) >= width)) {
                x += speed * delta;
            }
        }
    }


}
