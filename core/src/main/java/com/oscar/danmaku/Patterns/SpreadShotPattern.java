package com.oscar.danmaku.Patterns;

import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;
import com.oscar.danmaku.Bullets.MovementFactory.BulletMovementFactory;
import com.oscar.danmaku.Entities.Entity;

public class SpreadShotPattern implements AttackPattern {
    private BulletMovementFactory movementFactory;
    private float centerAngle;

    private float shootTimer;
    private float shootInterval;

    public SpreadShotPattern(float centerAngle, float shootInterval, BulletMovementFactory movementFactory) {
        this.centerAngle = centerAngle;
        this.shootInterval = shootInterval;
        this.movementFactory = movementFactory;
    }

    @Override
    public void update(float delta, Entity entity, BulletManager bulletManager) {
        shootTimer += delta;
        if (entity.wantsToShoot() && !entity.isDead()) {
            if (shootTimer >= shootInterval) {
                for (int i = 0; i < 5; i++) {
                    
                    float angle = centerAngle + (i - 2) * 15f;

                    float redirectX = MathUtils.cosDeg(angle);
                    float redirectY = MathUtils.sinDeg(angle);

                    bulletManager.spawn(entity.getX(), entity.getY(), 400, 5, 5, entity.getFaction(), 10, redirectX, redirectY, movementFactory.create()); 

                }
                shootTimer = 0;     
            }
        }
    }
    
}
