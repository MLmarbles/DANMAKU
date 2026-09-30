package com.oscar.danmaku.Bullets.Movement;

import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Bullets.Bullet;
import com.oscar.danmaku.Entities.Entity;

public class HomingMovement implements BulletMovement{
    
    private Entity target;
    private float turnSpeed;
    private float homingDuration = 1f;
    private float elapsedTime;
    
    public HomingMovement(Entity target, float turnSpeed) {
        this.target = target;
        this.turnSpeed = turnSpeed;
    }

    @Override
    public void update(Bullet bullet, float delta) {

        if (elapsedTime < homingDuration) {

            float dx = target.getX() - bullet.getX();
            float dy = target.getY() - bullet.getY();

            float length = (float) Math.sqrt(dx * dx + dy * dy);

            if (length == 0) {
                return;
            }

            float targetDirectionX = dx / length;
            float targetDirectionY = dy / length;

            float currentAngle = MathUtils.atan2Deg(
                bullet.getDirectionY(),
                bullet.getDirectionX()
            );

            float targetAngle = MathUtils.atan2Deg(
                targetDirectionY,
                targetDirectionX
            );

            float maxTurn = turnSpeed * delta;

            float angleDifference = MathUtils.atan2Deg(
                MathUtils.sinDeg(targetAngle - currentAngle),
                MathUtils.cosDeg(targetAngle - currentAngle)
            );

            angleDifference = MathUtils.clamp(
                angleDifference,
                -maxTurn,
                maxTurn
            );

            float newAngle = currentAngle + angleDifference;

            bullet.setDirectionX(MathUtils.cosDeg(newAngle));
            bullet.setDirectionY(MathUtils.sinDeg(newAngle));
            
        }

        elapsedTime += delta;


        bullet.setX(
            bullet.getX() + bullet.getDirectionX() * bullet.getSpeed() * delta
        );

        bullet.setY(
            bullet.getY() + bullet.getDirectionY() * bullet.getSpeed() * delta
        );
    }
}
