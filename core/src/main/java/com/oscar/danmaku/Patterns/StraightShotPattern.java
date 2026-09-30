package com.oscar.danmaku.Patterns;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;
import com.oscar.danmaku.Bullets.MovementFactory.BulletMovementFactory;
import com.oscar.danmaku.Entities.Entity;

public class StraightShotPattern implements AttackPattern {
    private BulletMovementFactory movementFactory;
    private float directionX;
    private float directionY;

    private float shootTimer;
    private float shootInterval;

    public StraightShotPattern(float directionX, float directionY, float shootInterval, BulletMovementFactory movementFactory) {
        this.directionX = directionX;
        this.directionY = directionY;
        this.shootInterval = shootInterval;
        this.movementFactory = movementFactory;
    }

    @Override
    public void update(float delta, Entity entity, BulletManager bulletManager) {
        shootTimer += delta;
        if (entity.wantsToShoot() && !entity.isDead()) {
            if (shootTimer >= shootInterval) {
                bulletManager.spawn(entity.getX(), entity.getY(), 200, 12, 12, entity.getFaction(), 10, directionX, directionY, movementFactory.create()); 
                shootTimer = 0;     
            }
        }
    }
}
