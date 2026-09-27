package com.oscar.danmaku.Systems;

import com.oscar.danmaku.Bullets.BulletManager;
import com.oscar.danmaku.Entities.Enemy;
import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Patterns.AimedShotPattern;
import com.oscar.danmaku.Patterns.AimedSpreadPattern;
import com.oscar.danmaku.Patterns.SpiralBurstPattern;
import com.oscar.danmaku.Patterns.SpreadShotPattern;
import com.oscar.danmaku.Patterns.TriangleShotPattern;

public class BossPhaseManager {
    private int currentPhase = 1;

    private Entity target;

    public BossPhaseManager(Entity target) {
        this.target = target;

        aimedSpreadPattern = new AimedSpreadPattern(target, 0.5f, 5, 200f);
    }

    private SpiralBurstPattern spiralBurstPattern = new SpiralBurstPattern(0.02f, 150f, 15);

    private AimedSpreadPattern aimedSpreadPattern;

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void update(Enemy boss, BulletManager bulletManager) {

        if (currentPhase == 1 && boss.getHealth() < (boss.getMaxHealth() * 0.7)) {
            currentPhase = 2;
            bulletManager.clear();
            boss.setAttackPattern(spiralBurstPattern);
        }

        if (currentPhase == 2 && boss.getHealth() < (boss.getMaxHealth() * 0.4)) {
            currentPhase = 3;
            bulletManager.clear();
            boss.setAttackPattern(aimedSpreadPattern);
        }
    }
}
