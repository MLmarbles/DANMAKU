package com.oscar.danmaku.Bullets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.oscar.danmaku.Entities.Faction;

public class BulletManager {
    // store, create, update, delete, render

    private List<Bullet> bullets;

    public BulletManager() {
        bullets = new ArrayList<>();
    }

    public List<Bullet> getBullets() {
        return Collections.unmodifiableList(bullets);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
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

    public void spawn(float x, float y, float speed, float width, float height, Faction faction, float damage, float directionX, float directionY) {

        Bullet bullet = new Bullet(x, y, speed, width, height, faction, damage, directionX, directionY);

        bullets.add(bullet);
    }
}
