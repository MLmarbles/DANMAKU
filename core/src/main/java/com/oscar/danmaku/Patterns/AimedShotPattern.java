package com.oscar.danmaku.Patterns;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;

public class AimedShotPattern implements AttackPattern {
    private Entity target;

    private float shootInterval;
    private int bulletCount;
    private float bulletSpeed;

    private float shootTimer;

    public AimedShotPattern(Entity target, float shootInterval, int bulletCount, float bulletSpeed) {
        this.target = target;
        this.shootInterval = shootInterval;
        this.bulletCount = bulletCount;
        this.bulletSpeed = bulletSpeed;
    }

    @Override
    public void update(float delta, Entity entity, BulletManager bulletManager) {
        
        shootTimer += delta;

        if (entity.wantsToShoot() && !entity.isDead()) {

            if (shootTimer >= shootInterval) {

                float dx = target.getX() - entity.getX();
                float dy = target.getY() - entity.getY();

                float length = (float)Math.sqrt(dx * dx + dy * dy);

                float directionX = dx / length;
                float directionY = dy / length;

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

                shootTimer = 0;
            }
        }
    }
}
