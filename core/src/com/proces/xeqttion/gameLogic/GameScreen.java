package com.proces.xeqttion.gameLogic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.proces.xeqttion.gameLogic.sprites.Electron;

import java.util.Random;

public class GameScreen implements Screen {
    final private GameClass game;
    private SpriteBatch batch;
    private Stage stage;
    private Array<Electron> electrons;
    private OrthographicCamera camera;
    private Random random;
    private Label fps;
    private Sound popSound;

    public GameScreen(GameClass game) {
        this.game = game;
        this.batch = game.getBatch();
        electrons = new Array<>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        random = new Random(1);
        popSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.mp3"));
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("trueTypeFonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.color = Color.WHITE;
        BitmapFont font50 = generator.generateFont(parameter);
        parameter.size = 40;
        BitmapFont font40 = generator.generateFont(parameter);
        generator.dispose();

        Label.LabelStyle pointsLabelStyle = new Label.LabelStyle();
        pointsLabelStyle.font = font50;
        Label.LabelStyle fpsLabelStyle = new Label.LabelStyle();
        fpsLabelStyle.font = font40;

        final Label pointsLabel = new Label(String.valueOf(game.getPointsAmount()), pointsLabelStyle);
        pointsLabel.setPosition(Gdx.graphics.getWidth() / 2f - pointsLabel.getScaleX() / 2f, Gdx.graphics.getHeight() * 0.9f);
        stage.addActor(pointsLabel);

        fps = new Label(Gdx.graphics.getFramesPerSecond() + " FPS", fpsLabelStyle);
        fps.setPosition(Gdx.graphics.getWidth() - fps.getWidth() * 1.2f, Gdx.graphics.getHeight() - fps.getHeight() * 1.5f);
        stage.addActor(fps);

        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                popSound.play(0.5f);
                game.incPointsAmount();
                pointsLabel.setText(game.getPointsAmount());
                electrons.add(new Electron(x, y, new Color(0, random.nextFloat(), 1, 1)));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getViewport().setCamera(camera);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (Electron electron : electrons) {
            try {
                batch.setColor(electron.getColor());
                batch.draw(electron.getSprite(), electron.getPosition().x, electron.getPosition().y);
            } catch (RuntimeException e) {
                Gdx.app.log("RenderException", e.getMessage());
            }
        }
        batch.end();

        stage.act();
        stage.draw();

        fps.setText(Gdx.graphics.getFramesPerSecond() + " FPS");
        camera.update();
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
