package com.oscar.danmaku.Systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.oscar.danmaku.Bullets.Bullet;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Entities.EntityManager;

public class CollisionSystem {
    
    public CollisionSystem() {

    }

    public boolean overlaps(Bullet bullet, Entity entity) {
        
        Rectangle recBullet = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());

        Rectangle recEntity = new Rectangle(entity.getX() - entity.getWidth() / 2, entity.getY() - entity.getHeight() / 2, entity.getWidth(), entity.getHeight());

        return recBullet.overlaps(recEntity);
    }
    
    public void inspectCollision(BulletManager bulletManager, EntityManager entityManager) {
        
        List<Bullet> bullets = bulletManager.getBullets();

        List<Bullet> bulletsToRemove = new ArrayList<>();

        List<Entity> entities = entityManager.getEntities();

        for (Bullet bullet : bullets) {
            for (Entity entity : entities) {
                if (bullet.getFaction() != entity.getFaction()) {
                    if (overlaps(bullet, entity)) {
                        entity.takeDamage(bullet.getDamage());
                        bulletsToRemove.add(bullet);
                        System.out.println(entity.getName() + " " + entity.getHealth());
                        break;
                    }
                }
            }
        }

        for (Bullet bullet : bulletsToRemove) {
            bulletManager.removeBullet(bullet);
        }
    }
}
