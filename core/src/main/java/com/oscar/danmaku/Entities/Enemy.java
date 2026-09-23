package com.oscar.danmaku.Entities;

import com.badlogic.gdx.math.MathUtils;

public class Enemy extends Entity {

    private float baseY;
    private float time;
    private float amplitude = 50f;
    
    public Enemy(float x, float y, float entityWidth, float entityHeight, float speed, String name, float health, float maxHealth, float defence, float attackPower){

        super(x, y, entityWidth, entityHeight, speed, name, health, maxHealth, defence, attackPower);

        baseY = y;
    }

    public void move(float delta) {

        time += delta;

        float newY = baseY + MathUtils.sin(time) * amplitude;

        setY(newY);
    }
}
