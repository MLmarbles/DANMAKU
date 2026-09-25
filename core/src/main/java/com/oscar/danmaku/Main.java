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
import com.oscar.danmaku.Patterns.StraightShotPattern;
import com.oscar.danmaku.Systems.AttackSystem;
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

    private AttackSystem attackSystem;

    @Override
    public void create() {

        shapeRenderer = new ShapeRenderer();
        bulletManager = new BulletManager();
        collisionSystem = new CollisionSystem();
        entityManager = new EntityManager();
        attackSystem = new AttackSystem();

        player = new Player(300, 100, 20, 20, 250f, "player", 100, 100, 10, 10, Faction.PLAYER, new StraightShotPattern(0, 1, 0.25f));

        enemy = new Enemy(300, 300, 20, 20, 250f, "enemy", 100, 100, 10, 10, Faction.ENEMY, new StraightShotPattern(0, -1, 1.0f));

        entityManager.addEntity(player);
        entityManager.addEntity(enemy);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        entityManager.update(delta);

        attackSystem.update(delta, entityManager, bulletManager);

        bulletManager.update(delta);

        collisionSystem.inspectCollision(bulletManager, entityManager);



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
