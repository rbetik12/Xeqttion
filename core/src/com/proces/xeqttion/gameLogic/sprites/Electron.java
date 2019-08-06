package com.proces.xeqttion.gameLogic.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Electron extends Sprite {
    public Electron(float x, float y) {
        texture = new Texture(Gdx.files.internal("sprites/electron.png"));
        position = new Vector2(x - texture.getWidth() / 2f, y - texture.getHeight() / 2f);
    }

    @Override
    public void update(float delta) {
    }


}
