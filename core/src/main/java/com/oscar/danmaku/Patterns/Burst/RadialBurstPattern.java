package com.oscar.danmaku.Patterns.Burst;

import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Patterns.AttackPattern;

public class RadialBurstPattern implements AttackPattern {

    private float shootInterval;
    private int bulletCount;
    private float bulletSpeed;

    private float shootTimer;

    private float rotationAmount;
    private float rotation;

    public RadialBurstPattern(float shootInterval, int bulletCount, float bulletSpeed, float rotationAmount) {
        this.shootInterval = shootInterval;
        this.bulletCount = bulletCount;
        this.bulletSpeed = bulletSpeed;
        this.rotationAmount = rotationAmount;
    }

    @Override
    public void update(float delta, Entity entity, BulletManager bulletManager) {

        shootTimer += delta;

        if (entity.wantsToShoot() && !entity.isDead()) {

            if (shootTimer >= shootInterval) {

                float angleStep = 360f / bulletCount;

                for (int i = 0; i < bulletCount; i++) {

                    float angle = (i * angleStep) + rotation;

                    float directionX = MathUtils.cosDeg(angle);
                    float directionY = MathUtils.sinDeg(angle);

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
                }

                rotation += rotationAmount;

                shootTimer = 0f;
            }
        }
    }
}
