package com.oscar.danmaku.Game;

import com.oscar.danmaku.Entities.Entity;
import com.oscar.danmaku.Entities.Player;

public class GameStateManager {
    private GameState gameState;

    public GameStateManager(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void update(Player player) {
        if (player.isDead()) {
            gameState = GameState.GAME_OVER;
        } 
    }
}
