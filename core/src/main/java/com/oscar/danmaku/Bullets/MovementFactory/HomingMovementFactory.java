package com.oscar.danmaku.Bullets.MovementFactory;

import com.oscar.danmaku.Bullets.Movement.BulletMovement;
import com.oscar.danmaku.Bullets.Movement.HomingMovement;
import com.oscar.danmaku.Entities.Entity;

public class HomingMovementFactory implements BulletMovementFactory{
    
    private Entity target;
    private float turnSpeed;

    public HomingMovementFactory(Entity target, float turnSpeed) {
        this.target = target;
        this.turnSpeed = turnSpeed;
    }

    @Override
    public HomingMovement create() {
        return new HomingMovement(target, turnSpeed);
    }
}
