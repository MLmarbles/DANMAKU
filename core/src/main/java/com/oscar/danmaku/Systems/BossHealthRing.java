package com.oscar.danmaku.Systems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Entities.Enemy;

public class BossHealthRing {

    private float radiusOffset = 10f;
    private Color color = Color.WHITE;

   public void render(ShapeRenderer shapeRenderer, Enemy boss) {

    float healthPercentage =
        boss.getHealth() / boss.getMaxHealth();

    healthPercentage = MathUtils.clamp(healthPercentage, 0f, 1f);

    float radius =
        Math.max(boss.getWidth(), boss.getHeight()) / 2f + 20f;

    float degrees = healthPercentage * 360f;

    float startAngle = 90f;

    int segments = 100;

    for (int i = 0; i < segments; i++) {

        float angle1 =
            startAngle + (degrees * i / segments);

        float angle2 =
            startAngle + (degrees * (i + 1) / segments);

        float x1 =
            boss.getX() + MathUtils.cosDeg(angle1) * radius;

        float y1 =
            boss.getY() + MathUtils.sinDeg(angle1) * radius;

        float x2 =
            boss.getX() + MathUtils.cosDeg(angle2) * radius;

        float y2 =
            boss.getY() + MathUtils.sinDeg(angle2) * radius;

        shapeRenderer.line(x1, y1, x2, y2);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setRadiusOffset(float radiusOffset) {
        this.radiusOffset = radiusOffset;
    }
}