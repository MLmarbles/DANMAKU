package com.oscar.danmaku.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

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
            y += speed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= speed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= speed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += speed * delta;
        }

        x = MathUtils.clamp(x, playerWidth / 2, width - (playerWidth / 2));
        y = MathUtils.clamp(y, playerHeight / 2, height - (playerHeight / 2));
    }


}
