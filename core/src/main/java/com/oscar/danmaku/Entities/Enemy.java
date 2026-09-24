package com.oscar.danmaku.Entities;

import com.badlogic.gdx.math.MathUtils;

public class Enemy extends Entity {

    private float baseX;
    private float time;
    private float amplitude = 50f;
    private float shootTimer;
    private float shootInterval = 1.0f;
    
    public Enemy(float x, float y, float entityWidth, float entityHeight, float speed, String name, float health, float maxHealth, float defence, float attackPower, Faction faction){

        super(x, y, entityWidth, entityHeight, speed, name, health, maxHealth, defence, attackPower, faction);

        baseX = x;
    }

    public void move(float delta) {

        time += delta;

        float newX = baseX + MathUtils.sin(time) * amplitude;

        setX(newX);

        shootTimer += delta;
    }

    public boolean shouldShoot() {
        if (shootTimer >= shootInterval) {
            shootTimer = 0;
            return true;
        }   

        return false;
    }
}
