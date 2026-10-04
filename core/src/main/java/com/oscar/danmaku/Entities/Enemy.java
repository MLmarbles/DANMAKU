package com.oscar.danmaku.Entities;

import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Patterns.AttackPattern;

public class Enemy extends Entity {

    private float baseX;
    private float baseY;
    private float time;
    private float amplitude = 50f;

    private float hoverRange = 50f;
    private float hoverSpeed = 1.0f;
    
    public Enemy(float x, float y, float width, float height, float speed, String name, float health, float maxHealth, float defence, float attackPower, Faction faction, AttackPattern attackPattern){

        super(x, y, width, height, speed, name, health, maxHealth, defence, attackPower, faction, attackPattern);

        baseX = x;
        baseY = y;
    }

    public void move(float delta) {

        time += delta;

        float newX = baseX + MathUtils.sin(time * hoverSpeed) * hoverRange;
        float newY = baseY + MathUtils.sin(time * hoverSpeed * 0.7f) * 20f;

        setX(newX);
        setY(newY);
    }

    @Override
    public boolean wantsToShoot() {
        return true;
    }

    @Override
    public boolean wantsToBomb() {
        return false;
    }

    @Override
    public void update(float delta) {
        move(delta);
    }
}
