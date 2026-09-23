package com.oscar.danmaku.Bullets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BulletManager {
    // store, create, update, delete, render

    private List<Bullet> bullets;

    public BulletManager() {
        bullets = new ArrayList<>();
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void update(float delta) {

        Iterator<Bullet> iterator = bullets.iterator();

        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();

            bullet.move(delta);

            if (bullet.isOffScreen()) {
                iterator.remove();
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer) {

        Iterator<Bullet> iterator = bullets.iterator();

        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();

            shapeRenderer.rect(
            bullet.getX(),
            bullet.getY(),
            bullet.getWidth(),
            bullet.getHeight()
        );

        }
    }
}
