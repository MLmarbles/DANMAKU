package com.oscar.danmaku.Patterns;

import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;

public class AimedSpreadPattern implements AttackPattern {
    private Entity target;

    private float shootInterval;
    private int bulletCount;
    private float bulletSpeed;

    private float shootTimer;

    public AimedSpreadPattern(Entity target, float shootInterval, int bulletCount, float bulletSpeed) {
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

                float targetAngle = MathUtils.atan2Deg(dy, dx);
                float spreadAngle = 15f;
                
                float angleStep = spreadAngle;
                float startAngle = targetAngle - (angleStep * (bulletCount - 1) / 2f);

                for (int i = 0; i < bulletCount; i++) {

                    float angle = startAngle + i * angleStep;

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

                shootTimer = 0;
            }
        }
    }
}
