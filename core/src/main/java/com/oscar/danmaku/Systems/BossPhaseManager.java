package com.oscar.danmaku.Systems;

import com.oscar.danmaku.Entities.Enemy;
import com.oscar.danmaku.Patterns.SpreadShotPattern;
import com.oscar.danmaku.Patterns.TriangleShotPattern;

public class BossPhaseManager {
    private int currentPhase = 1;

    private TriangleShotPattern triangleShotPattern = new TriangleShotPattern(0, -1, 1.0f);

    private SpreadShotPattern spreadShotPattern = new SpreadShotPattern(270f, 0.5f);

    public BossPhaseManager() {

    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void update(Enemy boss) {
        if (boss.getHealth() < 70) {
            currentPhase = 2;
            boss.setAttackPattern(triangleShotPattern);
        }
        if (boss.getHealth() < 40) {
            currentPhase = 3;
            boss.setAttackPattern(spreadShotPattern);
        }
    }
}
