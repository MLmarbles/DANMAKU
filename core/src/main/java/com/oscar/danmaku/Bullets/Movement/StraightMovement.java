package com.oscar.danmaku.Bullets.Movement;

import com.oscar.danmaku.Bullets.Bullet;

public class StraightMovement implements BulletMovement {

    @Override
    public void update(Bullet bullet, float delta) {

        bullet.setX(
            bullet.getX() + bullet.getDirectionX() * bullet.getSpeed() * delta
        );

        bullet.setY(
            bullet.getY() + bullet.getDirectionY() * bullet.getSpeed() * delta
        );
    }
}