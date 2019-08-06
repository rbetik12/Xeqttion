package com.proces.xeqttion.gameLogic.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Electron extends Sprite {

    public Electron(float x, float y) {
        super(x, y);
        texture = new Texture(Gdx.files.internal("sprites/electron-scaled.png"));
    }

    @Override
    public void update(float delta) {
    }


}
