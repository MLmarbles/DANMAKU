package com.oscar.danmaku.Patterns;

import java.util.Arrays;
import java.util.List;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Entity;

public class CompositeAttackPattern implements AttackPattern {

    private List<AttackPattern> attackPatterns;

    public CompositeAttackPattern(AttackPattern... attackPatterns) {
        this.attackPatterns = Arrays.asList(attackPatterns);
    }

    @Override
    public void update(float delta, Entity entity, BulletManager bulletManager) {
        
        for (AttackPattern pattern : attackPatterns) {
            pattern.update(delta, entity, bulletManager);
        }
    }
}
