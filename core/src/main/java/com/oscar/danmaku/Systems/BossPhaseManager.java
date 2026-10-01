package com.oscar.danmaku.Systems;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Bullets.Movement.SineMovement;
import com.oscar.danmaku.Bullets.Movement.StraightMovement;
import com.oscar.danmaku.Bullets.MovementFactory.HomingMovementFactory;
import com.oscar.danmaku.Bullets.MovementFactory.SineMovementFactory;
import com.oscar.danmaku.Bullets.MovementFactory.StraightMovementFactory;
import com.oscar.danmaku.Entities.Enemy;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Patterns.AttackPattern;
import com.oscar.danmaku.Patterns.CompositeAttackPattern;
import com.oscar.danmaku.Patterns.SpreadShotPattern;
import com.oscar.danmaku.Patterns.StraightShotPattern;
import com.oscar.danmaku.Patterns.TriangleShotPattern;
import com.oscar.danmaku.Patterns.Aimed.AimedShotPattern;
import com.oscar.danmaku.Patterns.Aimed.AimedSpreadPattern;
import com.oscar.danmaku.Patterns.Burst.RadialBurstPattern;
import com.oscar.danmaku.Patterns.Burst.SpiralBurstPattern;

public class BossPhaseManager {
    private int currentPhase = 1;

    private Entity target;

    private AttackPattern phase1Pattern;
    private AttackPattern phase2Pattern;
    private AttackPattern phase3Pattern;

    public BossPhaseManager(Entity target) {
        this.target = target;

        phase1Pattern = new CompositeAttackPattern(
            new AimedSpreadPattern(target, 0.5f, 5, 200f,       new StraightMovementFactory()),
            new RadialBurstPattern(0.5f, 20, 150f, 15f, new StraightMovementFactory()));

        phase2Pattern = new CompositeAttackPattern(
            new SpiralBurstPattern(0.10f, 130f, 10f, 0f,
                new SineMovementFactory(5f, 1f)),

            new SpiralBurstPattern(0.10f, 130f, 10f, 90f,
                new SineMovementFactory(5f, 1f)),

            new SpiralBurstPattern(0.10f, 130f, 10f, 180f,
                new SineMovementFactory(5f, 1f)),

            new SpiralBurstPattern(0.10f, 130f, 10f, 270f,
                new SineMovementFactory(5f, 1f)),
            new StraightShotPattern(0, -1, 1f, 
                new HomingMovementFactory(target, 180f)),
            new SpreadShotPattern(270f, 0.5f, 
                new StraightMovementFactory())
            );

        phase3Pattern = new CompositeAttackPattern(
            new RadialBurstPattern(0.5f, 20, 60f, 15f, new StraightMovementFactory()),
            new RadialBurstPattern(0.5f, 20, 120f, 67f, new StraightMovementFactory()),
            new RadialBurstPattern(0.5f, 20, 90f, 99f, new StraightMovementFactory()),
            new RadialBurstPattern(0.7f, 8, 150f, 77f, new StraightMovementFactory())
        );
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void update(Enemy boss, BulletManager bulletManager) {

        if (currentPhase == 1 && boss.getHealth() < (boss.getMaxHealth() * 0.7)) {
            currentPhase = 2;
            bulletManager.clear();
            boss.setAttackPattern(phase2Pattern);
        }

        if (currentPhase == 1) {
            boss.setAttackPattern(phase1Pattern);
        }

        if (currentPhase == 2 && boss.getHealth() < (boss.getMaxHealth() * 0.4)) {
            currentPhase = 3;
            bulletManager.clear();
            boss.setAttackPattern(phase3Pattern);
        }
    }
}
