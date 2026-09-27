package com.oscar.danmaku.Patterns.Burst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Patterns.AttackPattern;

public class SpiralBurstPattern implements AttackPattern {
    private float shootInterval;
    private float bulletSpeed;

    private float shootTimer;

    private float rotationAmount;
    private float rotation;

    public SpiralBurstPattern(float shootInterval, float bulletSpeed, float rotationAmount) {
        this.shootInterval = shootInterval;
        this.bulletSpeed = bulletSpeed;
        this.rotationAmount = rotationAmount;
    }

    @Override
    public void update(float delta, Entity entity, BulletManager bulletManager) {

        shootTimer += delta;

        if (entity.wantsToShoot() && !entity.isDead()) {

            if (shootTimer >= shootInterval) {

                shootTimer = 0;

                    float directionX = MathUtils.cosDeg(rotation);
                    float directionY = MathUtils.sinDeg(rotation);

                    bulletManager.spawn(
                        entity.getX(),
                        entity.getY(),
                        bulletSpeed,
                        5f,
                        5f,
                        entity.getFaction(),
                        10f,
                        directionX,
                        directionY
                    );

                rotation += rotationAmount;
            }
        }
    }
}
