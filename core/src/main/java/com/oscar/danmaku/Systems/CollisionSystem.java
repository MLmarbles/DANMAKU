package com.oscar.danmaku.Systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.oscar.danmaku.Bullets.Bullet;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;

public class CollisionSystem {
    
    public CollisionSystem() {

    }

    public boolean overlaps(Bullet bullet, Entity entity) {
        
        Rectangle recBullet = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());

        Rectangle recEntity = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

        if (recBullet.overlaps(recEntity)) {
            return true;
        }

        return false;
    }
    
    public void inspectCollision(BulletManager bulletManager, Entity entity) {
        
        List<Bullet> bullets = bulletManager.getBullets();

        List<Bullet> bulletsToRemove = new ArrayList<>();

        for (Bullet bullet : bullets) {
            if (bullet.getFaction() != entity.getFaction()) {
                if (overlaps(bullet, entity)) {
                    entity.takeDamage(bullet.getDamage());
                    bulletsToRemove.add(bullet);
                    System.out.println(entity.getName() + " " + entity.getHealth());
                }
            }
        }

        for (Bullet bullet : bulletsToRemove) {
            bulletManager.removeBullet(bullet);
        }
    }
}
