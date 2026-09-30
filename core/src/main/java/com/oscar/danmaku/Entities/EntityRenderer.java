package com.oscar.danmaku.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityRenderer {

    private Texture playerSprite;
    private Texture bossSprite;

    public EntityRenderer() {
        playerSprite = new Texture("Art/Sprites/Player/CirnoSprite.png");

        playerSprite.setFilter(
            Texture.TextureFilter.Nearest,
            Texture.TextureFilter.Nearest
        );

        bossSprite = new Texture("Art/Sprites/Boss/BossFlandreSprite.png");

        bossSprite.setFilter(
            Texture.TextureFilter.Nearest,
            Texture.TextureFilter.Nearest
        );
    }

    public void render(SpriteBatch batch, EntityManager entityManager) {

        for (Entity entity : entityManager.getEntities()) {

            if (entity instanceof Player) {

                batch.setColor(1f, 1f, 1f, 0.7f);

                float width = 36f;
                float height = 48f;

                batch.draw(
                    playerSprite,
                    entity.getX() - width / 2f,
                    entity.getY() - height / 2f,
                    width,
                    height
                );

                batch.setColor(1f, 1f, 1f, 1f);
            }

            if (entity instanceof Enemy) {

                float width = 94f;
                float height = 152f;

                batch.draw(
                    bossSprite,
                    entity.getX() - width / 2f,
                    entity.getY() - height / 2f,
                    width,
                    height
                );
            }
        }
    }

    public void dispose() {
        playerSprite.dispose();
        bossSprite.dispose();
    }
}