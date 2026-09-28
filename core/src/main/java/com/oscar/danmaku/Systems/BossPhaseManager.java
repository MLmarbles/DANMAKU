package com.oscar.danmaku.Systems;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.Movement.SineMovement;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;
import com.oscar.danmaku.Entities.Enemy;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Patterns.AttackPattern;
import com.oscar.danmaku.Patterns.CompositeAttackPattern;
import com.oscar.danmaku.Patterns.SpreadShotPattern;
import com.oscar.danmaku.Patterns.TriangleShotPattern;
import com.oscar.danmaku.Patterns.Aimed.AimedShotPattern;
import com.oscar.danmaku.Patterns.Aimed.AimedSpreadPattern;
import com.oscar.danmaku.Patterns.Burst.RadialBurstPattern;
import com.oscar.danmaku.Patterns.Burst.SpiralBurstPattern;

public class BossPhaseManager {
    private int currentPhase = 1;

    private Entity target;

    public BossPhaseManager(Entity target) {
        this.target = target;

        aimedSpreadPattern = new AimedSpreadPattern(target, 0.5f, 5, 200f, new StraightMovement());

        phase3Pattern = new CompositeAttackPattern(new RadialBurstPattern(1.0f, 16, 150f, 10f, new StraightMovement()), new AimedSpreadPattern(target, 0.5f, 5, 200f, new StraightMovement()));
    }

    private AimedSpreadPattern aimedSpreadPattern;
    private AttackPattern phase3Pattern;

    private AttackPattern phase2Pattern = new CompositeAttackPattern(
        new SpiralBurstPattern(0.10f, 130f, 10f, 0f,
            new SineMovement(15f, 1.5f)),

        new SpiralBurstPattern(0.10f, 130f, 10f, 90f,
            new SineMovement(15f, 1.5f)),

        new SpiralBurstPattern(0.10f, 130f, 10f, 180f,
            new SineMovement(15f, 1.5f)),

        new SpiralBurstPattern(0.10f, 130f, 10f, 270f,
            new SineMovement(15f, 1.5f))
    );

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void update(Enemy boss, BulletManager bulletManager) {

        if (currentPhase == 1 && boss.getHealth() < (boss.getMaxHealth() * 0.7)) {
            currentPhase = 2;
            bulletManager.clear();
            boss.setAttackPattern(phase2Pattern);
        }

        if (currentPhase == 2 && boss.getHealth() < (boss.getMaxHealth() * 0.4)) {
            currentPhase = 3;
            bulletManager.clear();
            boss.setAttackPattern(phase3Pattern);
        }
    }
}
