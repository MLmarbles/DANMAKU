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
import com.oscar.danmaku.Entities.Faction;
import com.oscar.danmaku.Entities.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    //private SpriteBatch batch;
    //private Texture image;

    private Player player;
    private ShapeRenderer shapeRenderer;
    private BulletManager bulletManager;
    private Enemy enemy;

    @Override
    public void create() {

        shapeRenderer = new ShapeRenderer();

        player = new Player(300, 100, 20, 20, 250f, "player", 100, 100, 10, 10);
        bulletManager = new BulletManager();

        enemy = new Enemy(300, 300, 20, 20, 250f, "enemy", 100, 100, 10, 10);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        player.move(delta);

        enemy.move(delta);

        bulletManager.update(delta);

        if (player.isShooting()) {
            Bullet bullet = new Bullet(player.getX(), player.getY(), 400, 2, 2, Faction.PLAYER);

            bulletManager.addBullet(bullet);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        bulletManager.render(shapeRenderer);

        shapeRenderer.rect(
            player.getX(),
            player.getY(),
            20,
            20
        );

        shapeRenderer.rect(
            enemy.getX(),
            enemy.getY(),
            20,
            20
        );

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
