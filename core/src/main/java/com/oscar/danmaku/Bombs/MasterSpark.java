package com.oscar.danmaku.Bombs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.MovementFactory.BulletMovementFactory;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Patterns.AttackPattern;

public class MasterSpark {

    private boolean isActive;
    private Entity entity;
    private float timer;
    private float duration = 6f;
    private float beamWidth = 80f;
    private float beamHeight = 600f;
    private float chargeDuration = 3f;
    private float damageTimer;
    private float damageInterval = 0.1f;

    private Music voiceline = Gdx.audio.newMusic(Gdx.files.internal("Music/Marisa_MasterSpark.mp3"));

    public MasterSpark(Entity entity) {
        this.entity = entity;
    }

    public float getBeamWidth() {
        return beamWidth;
    }

    public float getBeamHeight() {
        return beamHeight;
    }

    public Rectangle getBeamBounds() {

        float beamX = entity.getX() - beamWidth / 2f;

        float beamY = entity.getY();

        return new Rectangle(
            beamX,
            beamY,
            beamWidth,
            beamHeight
        );
    }

    public void activate() {
        if (entity.wantsToBomb() && !entity.isDead() && entity.getBombs() > 0 && !isActive) {
            damageTimer = 0f;
            entity.setBombs(entity.getBombs()-1);
            timer = 0f;
            isActive = true;
            voiceline.play();
            voiceline.setPosition(8f);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isFullyCharged() {
        return timer >= chargeDuration;
    }
    
    public void update(float delta) {
        if (voiceline.isPlaying() && voiceline.getPosition() >= 14f) {
            voiceline.stop();
        }

        if (!isActive) {
            return;
        }

        timer += delta;

        if (isFullyCharged()) {
            damageTimer += delta;
        }

        if (timer >= duration) {
            isActive = false;
            timer = 0f;
            damageTimer = 0f;
        }
    }

    public boolean canDamage() {
        if (!isFullyCharged()) {
            return false;
        }

        if (damageTimer >= damageInterval) {
            damageTimer = 0f;
            return true;
        }

        return false;
    }

    public void render(ShapeRenderer shapeRenderer) {

        if (!isActive) {
            return;
        }

        float chargeProgress = MathUtils.clamp(timer / duration, 0f, 1f);

        float currentBeamWidth = beamWidth * chargeProgress * 0.33f;

        if (timer > (chargeDuration)) {
            currentBeamWidth = beamWidth;
        }

        float beamX = entity.getX() - currentBeamWidth / 2f;
        float beamY = entity.getY();

        shapeRenderer.setColor(0.4f, 0.1f, 1f, 0.25f);

        shapeRenderer.rect(
            beamX - 15f,
            beamY,
            currentBeamWidth + 30f,
            beamHeight
        );

        shapeRenderer.setColor(0.6f, 0.2f, 1f, 0.8f);

        shapeRenderer.rect(
            beamX,
            beamY,
            currentBeamWidth,
            beamHeight
        );

        float coreWidth = currentBeamWidth * 0.4f;

        shapeRenderer.setColor(1f, 1f, 1f, 1f);

        shapeRenderer.rect(
            entity.getX() - coreWidth / 2f,
            beamY,
            coreWidth,
            beamHeight
        );
    }
}
