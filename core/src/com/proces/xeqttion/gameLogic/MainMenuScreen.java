package com.proces.xeqttion.gameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    final private GameClass game;

    final float GAME_WIDTH = Gdx.graphics.getWidth();
    final float GAME_HEIGHT = Gdx.graphics.getHeight();

    private Texture texture;
    private OrthographicCamera camera;

    public MainMenuScreen(final GameClass game) {
        this.game = game;

        texture = new Texture(Gdx.files.internal("images/electro-w-o-back.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // nailed size of screen
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getBatch().draw(texture, 0, GAME_HEIGHT / 2, GAME_WIDTH, GAME_WIDTH / 2);
        game.getBatch().end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {
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
        texture.dispose();
    }
}
