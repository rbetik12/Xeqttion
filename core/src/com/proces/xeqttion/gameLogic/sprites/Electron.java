package com.proces.xeqttion.gameLogic.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Electron {
    private Sprite sprite;
    private Vector2 position;
    private Vector2 velocity;
    private Color color;

    public Color getColor() {
        return color;
    }

    public Electron(float x, float y, Color color) {
        sprite = new Sprite(new Texture(Gdx.files.internal("sprites/electron.png")));
        position = new Vector2(x - sprite.getWidth() / 2f, y - sprite.getHeight() / 2f);
        this.color = color;
        sprite.setPosition(position.x, position.y);
    }

    public void update(float delta) {
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
