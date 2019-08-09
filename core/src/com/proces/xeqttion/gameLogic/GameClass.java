package com.proces.xeqttion.gameLogic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClass extends Game {

    private SpriteBatch batch;
    private BitmapFont font;
    private int pointsAmount;
    private float aspectRatio;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
        this.aspectRatio = (float)Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public int getPointsAmount() {
        return pointsAmount;
    }

    public float getAspectRatio() { return aspectRatio; }

    public void incPointsAmount() {
        pointsAmount += 1;
    }

    public void addToPointsAmount(int pointsAmount) {
        this.pointsAmount += pointsAmount;
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
