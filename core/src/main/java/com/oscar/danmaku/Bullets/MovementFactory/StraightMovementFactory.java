package com.oscar.danmaku.Bullets.MovementFactory;

import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;

public class StraightMovementFactory implements BulletMovementFactory {
    
    @Override
    public BulletMovement create() {
        return new StraightMovement();
    }
}
