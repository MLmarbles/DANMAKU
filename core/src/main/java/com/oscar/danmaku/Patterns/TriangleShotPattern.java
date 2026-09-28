package com.oscar.danmaku.Patterns;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;
import com.oscar.danmaku.Entities.Entity;

public class TriangleShotPattern implements AttackPattern {
    private BulletMovement movement;
    private float directionX;
    private float directionY;

    private float shootTimer;
    private float shootInterval;

    public TriangleShotPattern(float directionX, float directionY, float shootInterval, BulletMovement movement) {
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
                for (int i = 0; i < 5; i++) {
                    float offset = 30;
                    float startX = entity.getX() + (i - 2) * offset;

                    float startY = (2 - Math.abs(i - 2)) * offset * directionY;

                    bulletManager.spawn(startX, entity.getY() + startY, 400, 5, 5, entity.getFaction(), 10, directionX, directionY, movement); 
                }
                shootTimer = 0;     
            }
        }
    }
    
}
