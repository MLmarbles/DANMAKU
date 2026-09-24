package com.oscar.danmaku;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oscar.danmaku.Bullets.Bullet;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Enemy;
import com.oscar.danmaku.Entities.EntityManager;
import com.oscar.danmaku.Entities.Faction;
import com.oscar.danmaku.Entities.Player;
import com.oscar.danmaku.Systems.CollisionSystem;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    //private SpriteBatch batch;
    //private Texture image;

    private ShapeRenderer shapeRenderer;

    private Player player;
    private Enemy enemy;

    private CollisionSystem collisionSystem;
    private BulletManager bulletManager;
    private EntityManager entityManager;

    @Override
    public void create() {

        shapeRenderer = new ShapeRenderer();
        bulletManager = new BulletManager();
        collisionSystem = new CollisionSystem();
        entityManager = new EntityManager();

        player = new Player(300, 100, 20, 20, 250f, "player", 100, 100, 10, 10, Faction.PLAYER);

        enemy = new Enemy(300, 300, 20, 20, 250f, "enemy", 100, 100, 10, 10, Faction.ENEMY);

        entityManager.addEntity(player);
        entityManager.addEntity(enemy);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        bulletManager.update(delta);
        entityManager.update(delta);

        collisionSystem.inspectCollision(bulletManager, player);
        collisionSystem.inspectCollision(bulletManager, enemy);

        if (player.isShooting() & player.getHealth() > 0) {
            bulletManager.spawn(player.getX(), player.getY(), 400, 2, 2, Faction.PLAYER, 10, 0, 1);
        }

        if (enemy.shouldShoot() & enemy.getHealth() > 0) {
            bulletManager.spawn(enemy.getX(), enemy.getY(), 400, 2, 2, Faction.ENEMY, 10, 0, -1);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        bulletManager.render(shapeRenderer);
        entityManager.render(shapeRenderer);

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
