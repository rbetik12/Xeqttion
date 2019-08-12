package com.proces.xeqttion.gameLogic.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Electron {
    private Sprite sprite;
    private Color color;
    private Body body;

    public Color getColor() {
        return color;
    }

    public Electron(World world, float x, float y, Color color) {
        sprite = new Sprite(new Texture(Gdx.files.internal("sprites/electron.png")));
        sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
        this.color = color;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(sprite.getHeight() / 1.7f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);
        shape.dispose();

    }

    public void update(float delta) {
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
    }

    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    public Sprite getSprite() {
        return sprite;
    }
}
