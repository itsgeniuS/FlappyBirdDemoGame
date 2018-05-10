package com.genius.flappy_bird.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asort Technologies on 29-03-2018.
 */

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position, velocity;
    private Rectangle bounds;

    private Animation birdAnimation;
    private Texture texture;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        //bird = new Texture("bird.png");
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void update(float deltaTime){
        birdAnimation.updateAnimation(deltaTime);
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(deltaTime);
        position.add(MOVEMENT * deltaTime, velocity.y, 0);

        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/deltaTime);

        bounds.setPosition(position.x, position.y);
    }

    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }

}
