package com.oscar.danmaku;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oscar.danmaku.Bombs.MasterSpark;
import com.oscar.danmaku.Bullets.Bullet;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;
import com.oscar.danmaku.Bullets.MovementFactory.StraightMovementFactory;
import com.oscar.danmaku.Entities.Enemy;
import com.oscar.danmaku.Entities.EntityManager;
import com.oscar.danmaku.Entities.EntityRenderer;
import com.oscar.danmaku.Entities.Faction;
import com.oscar.danmaku.Entities.Player;
import com.oscar.danmaku.Game.GameState;
import com.oscar.danmaku.Game.GameStateManager;
import com.oscar.danmaku.Patterns.TriangleShotPattern;
import com.oscar.danmaku.Patterns.Burst.RadialBurstPattern;
import com.oscar.danmaku.Patterns.SpreadShotPattern;
import com.oscar.danmaku.Patterns.StraightShotPattern;
import com.oscar.danmaku.Systems.AttackSystem;
import com.oscar.danmaku.Systems.BossHealthRing;
import com.oscar.danmaku.Systems.BossPhaseManager;
import com.oscar.danmaku.Systems.CollisionSystem;
import com.oscar.danmaku.Systems.PhaseTransitionEffect;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont font;
    private EntityRenderer entityRenderer;
    private Music bossMusic;
    private PhaseTransitionEffect phaseTransitionEffect;

    private Player player;
    private Enemy enemy;

    private CollisionSystem collisionSystem;
    private BulletManager bulletManager;
    private EntityManager entityManager;

    private AttackSystem attackSystem;

    private GameStateManager gameStateManager;

    private BossPhaseManager bossPhaseManager;
    private BossHealthRing bossHealthRing;

    private MasterSpark masterSpark;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        entityRenderer = new EntityRenderer();
        phaseTransitionEffect = new PhaseTransitionEffect();

        bossMusic =
            Gdx.audio.newMusic(
                Gdx.files.internal("Music/FlandreBossMusic.mp3")
            );

        bossMusic.setLooping(true);
        //bossMusic.play();
        bossMusic.setPosition(58f);

        shapeRenderer = new ShapeRenderer();
        bulletManager = new BulletManager();
        collisionSystem = new CollisionSystem();
        entityManager = new EntityManager();
        attackSystem = new AttackSystem();
        gameStateManager = new GameStateManager(GameState.PLAYING);
        bossHealthRing = new BossHealthRing();

        player = new Player(300, 100, 10, 10, 250f, "player", 100, 100, 10, 10, Faction.PLAYER, new SpreadShotPattern(90f, 0.25f, new StraightMovementFactory()));

        masterSpark = new MasterSpark(player);

        enemy = new Enemy(300, 400, 80, 140, 250f, "enemy", 7500, 7500, 10, 10, Faction.ENEMY, new RadialBurstPattern(0.5f, 20, 150f, 15f, new StraightMovementFactory()));

        entityManager.addEntity(player);
        entityManager.addEntity(enemy);

        bossPhaseManager = new BossPhaseManager(player);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();


        if (gameStateManager.getGameState() == GameState.PLAYING){
            entityManager.update(delta);

            attackSystem.update(delta, entityManager, bulletManager);

            bulletManager.update(delta);

            collisionSystem.inspectCollision(bulletManager, entityManager);
            
            gameStateManager.update(player);

            bossPhaseManager.update(enemy, bulletManager);

            masterSpark.activate();
            masterSpark.update(delta);
            collisionSystem.inspectBombCollision(masterSpark, entityManager);

            int phaseNumber = bossPhaseManager.update(enemy, bulletManager);
            if (phaseNumber != phaseTransitionEffect.getPhaseNumber()) {
                phaseTransitionEffect.trigger(phaseNumber);
            }
            phaseTransitionEffect.update(delta);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            bulletManager.render(shapeRenderer);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            masterSpark.render(shapeRenderer);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            bossHealthRing.update(delta);
            bossHealthRing.render(shapeRenderer, enemy);
            shapeRenderer.end();

            batch.begin();
            entityRenderer.render(batch, entityManager);
            batch.end();

            batch.begin();
            entityRenderer.render(batch, entityManager);
            phaseTransitionEffect.render(batch);
            batch.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(
                player.getX(),
                player.getY(),
                5f
            );
            shapeRenderer.end();
        }

        if (gameStateManager.getGameState() == GameState.GAME_OVER) {
            batch.begin();

            font.draw(batch, "GAME OVER", 300, 300);

            batch.end();
        }
        
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
        phaseTransitionEffect.dispose();
    }
}
