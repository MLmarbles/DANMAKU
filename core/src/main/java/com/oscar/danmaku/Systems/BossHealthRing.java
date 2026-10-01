package com.oscar.danmaku.Systems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Entities.Enemy;

public class BossHealthRing {

    private Color color = Color.WHITE;

    private float[] spectrum = new float[64];
    private float time;

    private Color tempColor = new Color();

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void update(float delta) {
        time += delta;

        for (int i = 0; i < spectrum.length; i++) {
            spectrum[i] = (MathUtils.sin(time * 5f + i * 0.4f) + 1f) / 2f;
        }
    }

    public void render(ShapeRenderer shapeRenderer, Enemy boss) {
        shapeRenderer.setColor(color);

        float healthPercentage = boss.getHealth() / boss.getMaxHealth();
        
        healthPercentage = MathUtils.clamp(healthPercentage, 0f, 1f);

        float radius = 50f;

        float angleStep = 360f / spectrum.length;

        int activeBars = (int)(spectrum.length * healthPercentage);

        for (int i = 0; i < activeBars; i++) {

            float angle = i*angleStep;

            float barHeight = spectrum[i] * 20f;
        
            float innerRadius = radius;
            float outerRadius = radius + barHeight;

            float x1 = boss.getX() + MathUtils.cosDeg(angle) * innerRadius;
            float y1 = boss.getY() + MathUtils.sinDeg(angle) * innerRadius;
            float x2 = boss.getX() + MathUtils.cosDeg(angle) * outerRadius;
            float y2 = boss.getY() + MathUtils.sinDeg(angle) * outerRadius;

            float hue = (time * 80f + (i * 360f / spectrum.length)) % 360f;
            tempColor.fromHsv(hue, 1f, 1f);
            shapeRenderer.setColor(tempColor);

            float barWidth = 3f;

            shapeRenderer.rect(
                x1 - barWidth / 2f,
                y1,
                barWidth / 2f,
                0f,
                barWidth,
                barHeight,
                1f,
                1f,
                angle - 90f
            );
        }
    }
}