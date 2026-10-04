package com.oscar.danmaku.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import com.oscar.danmaku.Patterns.AttackPattern;

public class Player extends Entity{

    private Music cirnoTaunts =  Gdx.audio.newMusic(
        Gdx.files.internal("Music/CirnoBakaLines.mp3")
    );


    public Player(float x, float y, float width, float height, float speed, String name, float health, float maxHealth, float defence, float attackPower, Faction faction, AttackPattern attackPattern){

        super(x, y, width, height, speed, name, health, maxHealth, defence, attackPower, faction, attackPattern);
    }

    int[][] baka = {
        {2, 4},
        {5, 7},
        {8, 10},
        {11, 17},
        {18, 20},
        {21, 24},
        {24, 26},
        {28, 33},
        {38, 43},
        {45, 48}
    };

    private float tauntStopTime = -1f;

    public void move(float delta) {

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float xMovement = 0;
        float yMovement = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yMovement += getSpeed() * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xMovement -= getSpeed() * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yMovement -= getSpeed() * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xMovement += getSpeed() * delta;
        }

        super.move(xMovement, yMovement);

        setX(MathUtils.clamp(
            getX(),
            getWidth() / 2,
            width - getWidth() / 2
        ));

        setY(MathUtils.clamp(
            getY(),
            getHeight() / 2,
            height - getHeight() / 2
        ));
    }

    @Override
    public boolean wantsToShoot() {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    @Override
    public boolean wantsToBomb() {
        return Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }

    @Override
    public void update(float delta) {
        move(delta);
        taunt();
    }

    public void taunt() {
        if (Gdx.input.isKeyPressed(Input.Keys.T)) {
            if (!cirnoTaunts.isPlaying()) {
                int[] voiceLine = baka[(int)(Math.random() * baka.length)];
                cirnoTaunts.play();
                cirnoTaunts.setPosition(voiceLine[0]);
                tauntStopTime = voiceLine[1];
            }
        }

        if (cirnoTaunts.isPlaying() && cirnoTaunts.getPosition() >= tauntStopTime) {
            cirnoTaunts.stop();
            tauntStopTime = -1f;
        }
    }

}
