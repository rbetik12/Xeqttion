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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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
    final private Label pointsLabel;

    private SpriteBatch batch;
    private Stage stage;
    private Array<Electron> electrons;
    private OrthographicCamera camera;
    private Random random;
    private Label fps;
    private Sound popSound;

    private World world;
    private Box2DDebugRenderer renderer;
    private Body ground;

    public GameScreen(GameClass game) {
        this.game = game;
        this.batch = game.getBatch();
        electrons = new Array<>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        random = new Random(1);
        popSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pop.mp3"));
        renderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, -10), true);
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

        pointsLabel = new Label(String.valueOf(game.getPointsAmount()), pointsLabelStyle);
        pointsLabel.setPosition(Gdx.graphics.getWidth() / 2f - pointsLabel.getScaleX() / 2f, Gdx.graphics.getHeight() * 0.9f);
        stage.addActor(pointsLabel);

        fps = new Label(Gdx.graphics.getFramesPerSecond() + " FPS", fpsLabelStyle);
        fps.setPosition(Gdx.graphics.getWidth() - fps.getWidth() * 1.2f, Gdx.graphics.getHeight() - fps.getHeight() * 1.5f);
        stage.addActor(fps);

        initInputListener();
        addGround();
        addOutterTube();
        addInnerTube();
    }

    @Override
    public void show() {

    }

    private void initInputListener() {
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                popSound.play(0.5f);
                game.incPointsAmount();
                pointsLabel.setText(game.getPointsAmount());
                electrons.add(new Electron(world, x, y, new Color(0, random.nextFloat(), 1, 1)));
                return true;
            }
        });
    }

    private void addGround() {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(0, Gdx.graphics.getHeight() / 6f);

        ground = world.createBody(bDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(Gdx.graphics.getWidth() + 1, 1);

        ground.createFixture(polygonShape, 5f);
        polygonShape.dispose();
    }

    private void addOutterTube() {
        Body outterConductor;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        outterConductor = world.createBody(bodyDef);

        ChainShape outterConductorShape = new ChainShape();
        Vector2[] vector2s = new Vector2[6];
        vector2s[0] = new Vector2(Gdx.graphics.getWidth() / 2f - 2 / 5f * Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6f + 1);
        vector2s[1] = new Vector2(Gdx.graphics.getWidth() / 2f - 2 / 5f * Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 3 / 6f);
        vector2s[2] = new Vector2(Gdx.graphics.getWidth() / 2f - 2 / 5f * Gdx.graphics.getWidth() + Gdx.graphics.getHeight() / 12f, Gdx.graphics.getHeight() * 3 / 6f + Gdx.graphics.getHeight() / 12f);
        vector2s[3] = new Vector2(Gdx.graphics.getWidth() / 2f + 2 / 5f * Gdx.graphics.getWidth() - Gdx.graphics.getHeight() / 12f, Gdx.graphics.getHeight() * 3 / 6f + Gdx.graphics.getHeight() / 12f);
        vector2s[4] = new Vector2(Gdx.graphics.getWidth() / 2f + 2 / 5f * Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 3 / 6f);
        vector2s[5] = new Vector2(Gdx.graphics.getWidth() / 2f + 2 / 5f * Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6f + 1);
        outterConductorShape.createChain(vector2s);

        outterConductor.createFixture(outterConductorShape, 5f);
        outterConductorShape.dispose();
    }

    private void addInnerTube() {
        Body innerConductor;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        innerConductor = world.createBody(bodyDef);

        ChainShape innerConductorShape = new ChainShape();
        Vector2[] vector2s = new Vector2[6];
        vector2s[0] = new Vector2(Gdx.graphics.getWidth() / 2f - 1 / 5f * Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6f + 1);
        vector2s[1] = new Vector2(Gdx.graphics.getWidth() / 2f - 1 / 5f * Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.42f);
        vector2s[2] = new Vector2(Gdx.graphics.getWidth() / 2f - 1 / 5f * Gdx.graphics.getWidth() + Gdx.graphics.getHeight() / 12f, Gdx.graphics.getHeight() * 0.42f + Gdx.graphics.getHeight() / 12f);
        vector2s[3] = new Vector2(Gdx.graphics.getWidth() / 2f + 1 / 5f * Gdx.graphics.getWidth() - Gdx.graphics.getHeight() / 12f, Gdx.graphics.getHeight() * 0.42f + Gdx.graphics.getHeight() / 12f);
        vector2s[4] = new Vector2(Gdx.graphics.getWidth() / 2f + 1 / 5f * Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.42f);
        vector2s[5] = new Vector2(Gdx.graphics.getWidth() / 2f + 1 / 5f * Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6f + 1);
        innerConductorShape.createChain(vector2s);

        innerConductor.createFixture(innerConductorShape, 5f);
        innerConductorShape.dispose();
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
                electron.update(delta);
            } catch (RuntimeException e) {
                Gdx.app.log("RenderException", e.getMessage());
            }
        }
        batch.end();

        stage.act();
        stage.draw();

        fps.setText(Gdx.graphics.getFramesPerSecond() + " FPS");
        camera.update();

        renderer.render(world, camera.combined);
        world.step(1 / 60f, 6, 2);
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
