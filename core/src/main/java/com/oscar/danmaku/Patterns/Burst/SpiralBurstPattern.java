package com.oscar.danmaku.Patterns.Burst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Bullets.Movement.SineMovement;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Patterns.AttackPattern;

public class SpiralBurstPattern implements AttackPattern {
    private BulletMovement movement;
    private float shootInterval;
    private float bulletSpeed;

    private float shootTimer;

    private float rotationAmount;
    private float rotation;

    private float startingAngle;

    public SpiralBurstPattern(float shootInterval, float bulletSpeed, float rotationAmount, float startingAngle, BulletMovement movement) {
        this.shootInterval = shootInterval;
        this.bulletSpeed = bulletSpeed;
        this.rotationAmount = rotationAmount;
        this.startingAngle = startingAngle;
        this.rotation = startingAngle;
        this.movement = movement;
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
                        directionY,
                        movement
                    );

                rotation += rotationAmount;
            }
        }
    }
}
