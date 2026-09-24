package com.oscar.danmaku.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

public class Player extends Entity{

    public Player(float x, float y, float entityWidth, float entityHeight, float speed, String name, float health, float maxHealth, float defence, float attackPower, Faction faction){

        super(x, y, entityWidth, entityHeight, speed, name, health, maxHealth, defence, attackPower, faction);
    }


    public void move(float delta) {

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float xMovement = 0;
        float yMovement = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yMovement += getSpeed() * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xMovement -= getSpeed() * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yMovement -= getSpeed() * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xMovement += getSpeed() * delta;
        }

        super.move(xMovement, yMovement);

        setX(MathUtils.clamp(
            getX(),
            getWidth() / 2,
            width - getWidth() / 2
        ));

        setY(MathUtils.clamp(
            getY(),
            getHeight() / 2,
            height - getHeight() / 2
        ));
    }


    public boolean isShooting() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            return true;
        }

        return false;
    }


}
