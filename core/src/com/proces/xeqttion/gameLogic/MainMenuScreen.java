package com.proces.xeqttion.gameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;



public class MainMenuScreen implements Screen {

    final private GameClass game;

    private Stage stage;

    final float GAME_WIDTH = Gdx.graphics.getWidth();
    final float GAME_HEIGHT = Gdx.graphics.getHeight();

    private Texture texture;
    private OrthographicCamera camera;

    public MainMenuScreen(final GameClass game) {

        this.game = game;
        stage = new Stage( new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        createLabel();

        texture = new Texture(Gdx.files.internal("images/electro-w-o-back.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // nailed size of screen
    }

    private void createLabel(){

        int labelWidht = 500;
        int labelHeight = 500;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("trueTypeFonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) GAME_WIDTH/10;
        BitmapFont font24 = generator.generateFont(parameter);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;
        labelStyle.fontColor = Color.WHITE;

        Label label = new Label("Pre55 t0 b3g1/|/", labelStyle);
        label.setSize( GAME_WIDTH, 500 );
        label.setPosition( 0, 0 );
        label.setAlignment(Align.center);

// Blue background for debug


//        Pixmap labelColor = new Pixmap(labelWidht, labelHeight, Pixmap.Format.RGB888);
//        labelColor.setColor(Color.BLUE);
//        labelColor.fill();
//        label.getStyle().background = new Image(new Texture(labelColor)).getDrawable();

        stage.addActor(label);

        generator.dispose();
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

        delta = Gdx.graphics.getDeltaTime();
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

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
        stage.dispose();
    }
}
