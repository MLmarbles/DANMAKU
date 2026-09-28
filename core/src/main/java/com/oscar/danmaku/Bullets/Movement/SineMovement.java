package com.oscar.danmaku.Bullets.Movement;

import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Bullets.Bullet;

public class SineMovement implements BulletMovement {

    private float startX;
    private float startY;

    private float time;
    private float distanceTravelled;

    private float amplitude;
    private float frequency;

    private boolean initialized;

    public SineMovement(float amplitude, float frequency) {
        this.amplitude = amplitude;
        this.frequency = frequency;
    }

    @Override
    public void update(Bullet bullet, float delta) {

        if (!initialized) {
            startX = bullet.getX();
            startY = bullet.getY();
            initialized = true;
        }

        time += delta;

        distanceTravelled += bullet.getSpeed() * delta;

        float forwardX =
            bullet.getDirectionX() * distanceTravelled;

        float forwardY =
            bullet.getDirectionY() * distanceTravelled;

        float perpendicularX =
            -bullet.getDirectionY();

        float perpendicularY =
            bullet.getDirectionX();

        float waveOffset =
            MathUtils.sin(time * frequency * MathUtils.PI2) * amplitude;

        float waveX =
            perpendicularX * waveOffset;

        float waveY =
            perpendicularY * waveOffset;

        bullet.setX(startX + forwardX + waveX);
        bullet.setY(startY + forwardY + waveY);
    }
}