package com.oscar.danmaku.Patterns;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;
import com.oscar.danmaku.Entities.Entity;

public class StraightShotPattern implements AttackPattern {
    private BulletMovement movement;
    private float directionX;
    private float directionY;

    private float shootTimer;
    private float shootInterval;

    public StraightShotPattern(float directionX, float directionY, float shootInterval, BulletMovement movement) {
        this.directionX = directionX;
        this.directionY = directionY;
        this.shootInterval = shootInterval;
        this.movement = movement;
    }

    @Override
    public void update(float delta, Entity entity, BulletManager bulletManager) {
        shootTimer += delta;
        if (entity.wantsToShoot() && !entity.isDead()) {
            if (shootTimer >= shootInterval) {
                bulletManager.spawn(entity.getX(), entity.getY(), 400, 2, 2, entity.getFaction(), 10, directionX, directionY, movement); 
                shootTimer = 0;     
            }
        }
    }
}
