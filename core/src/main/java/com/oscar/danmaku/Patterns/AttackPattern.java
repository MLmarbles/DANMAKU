package com.oscar.danmaku.Patterns;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;

public interface AttackPattern {
    
    void update(float delta, Entity entity, BulletManager bulletManager);
}
