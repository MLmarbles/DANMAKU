package com.oscar.danmaku.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Patterns.AttackPattern;

public class Enemy extends Entity {

    private float baseX;
    private float time;
    private float amplitude = 50f;
    
    public Enemy(float x, float y, float width, float height, float speed, String name, float health, float maxHealth, float defence, float attackPower, Faction faction, AttackPattern attackPattern){

        super(x, y, width, height, speed, name, health, maxHealth, defence, attackPower, faction, attackPattern);

        baseX = x;
    }

    public void move(float delta) {

        time += delta;

        float newX = baseX + MathUtils.sin(time) * amplitude;

        setX(newX);
    }

    @Override
    public boolean wantsToShoot() {
        return true;
    }

    @Override
    public void update(float delta) {
        move(delta);
    }
}
