package com.oscar.danmaku.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.oscar.danmaku.Bullets.Bullet;

public class EntityManager {

    private List<Entity> entities;

    public EntityManager() {
        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void update() {

        Iterator<Entity> iterator = entities.iterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (entity.getHealth() <= 0) {
                iterator.remove();
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        Iterator<Entity> iterator = entities.iterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            shapeRenderer.rect(
            entity.getX(),
            entity.getY(),
            entity.getWidth(),
            entity.getHeight()
        );

        }
    }


}
