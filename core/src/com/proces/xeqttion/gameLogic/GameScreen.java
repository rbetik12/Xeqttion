package com.proces.xeqttion.gameLogic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    final private GameClass game;
    private Stage stage;

    public GameScreen(GameClass game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("trueTypeFonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.color = Color.WHITE;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        final Label pointsLabel = new Label(String.valueOf(game.getPointsAmount()), labelStyle);
        pointsLabel.setPosition(Gdx.graphics.getWidth() / 2f - pointsLabel.getScaleX() / 2f, Gdx.graphics.getHeight() * 0.9f);
        stage.addActor(pointsLabel);

        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.incPointsAmount();
                pointsLabel.setText(game.getPointsAmount());
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
