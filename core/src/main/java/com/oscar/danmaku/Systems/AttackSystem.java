package com.oscar.danmaku.Systems;

import java.util.List;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Entities.EntityManager;

public class AttackSystem {
    
    public AttackSystem() {}

    public void update(float delta, EntityManager entityManager, BulletManager bulletManager) {

        List<Entity> entities = entityManager.getEntities();
        for (Entity entity : entities) {
            entity.getAttackPattern().update(delta, entity, bulletManager);
        }
    }
}
