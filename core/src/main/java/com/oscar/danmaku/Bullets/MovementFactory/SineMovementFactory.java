package com.oscar.danmaku.Bullets.MovementFactory;

import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Bullets.Movement.SineMovement;

public class SineMovementFactory implements BulletMovementFactory {

    private float amplitude;
    private float frequency;

    public SineMovementFactory(float amplitude, float frequency) {
        this.amplitude = amplitude;
        this.frequency = frequency;
    }

    @Override
    public BulletMovement create() {
        return new SineMovement(amplitude, frequency);
    }
}