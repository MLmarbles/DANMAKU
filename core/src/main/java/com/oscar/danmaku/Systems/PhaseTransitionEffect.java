package com.oscar.danmaku.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class PhaseTransitionEffect {

    private Texture banner;
    private Music transitionSound;

    private boolean active;
    private float timer;
    private float duration = 1.8f;

    private int phaseNumber = 1;

    public int getPhaseNumber() {
        return phaseNumber;
    }

    public void setPhaseNumber(int phaseNumber) {
        this.phaseNumber = phaseNumber;
    }

    public PhaseTransitionEffect() {
        banner = new Texture("Art/Transitions/FlandreBossPhaseSwitch.png");
        transitionSound = Gdx.audio.newMusic(Gdx.files.internal("Music/BossTransition.mp3"));
    }

    public void trigger(int phaseNumber) {
        this.phaseNumber = phaseNumber;
        this.active = true;
        this.timer = 0f;

        transitionSound.play();
        transitionSound.setPosition(1f);
    }

    public void update(float delta) {
        if (!active) {
            return;
        }

        timer += delta;

        if (timer >= duration) {
            transitionSound.stop();
            active = false;
        }
    }

    public void render(SpriteBatch batch) {
        if (!active) {
            return;
        }

        float progress = timer/duration;
        progress = MathUtils.clamp(progress, 0f, 1f);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float bannerWidth = 300f;
        float bannerHeight = 273f;

        float startX = screenWidth;
        float startY = screenHeight;

        float endX = -bannerWidth;
        float endY = -bannerHeight;

        float x = MathUtils.lerp(startX, endX, progress);
        float y = MathUtils.lerp(startY, endY, progress);

        batch.draw(
            banner,
            x,
            y,
            bannerWidth,
            bannerHeight
        );
    }

    public boolean isActive() {
        return active;
    }

    public void dispose() {
        banner.dispose();
        transitionSound.dispose();
    }
}
