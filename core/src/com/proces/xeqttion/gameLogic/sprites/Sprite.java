package com.proces.xeqttion.gameLogic.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Sprite {
    protected Texture texture;
    protected Vector2 position;
    protected Vector2 velocity;

    public abstract void update (float delta);
    public void dispose() {
        texture.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Texture getTexture() {
        return texture;
    }
}
