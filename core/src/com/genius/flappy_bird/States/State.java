package com.genius.flappy_bird.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Asort Technologies on 29-03-2018.
 */

public abstract class State {

    protected OrthographicCamera camera; // to locate position of object
    protected Vector3 mouse; // for pointer , vector is x,y,z co-ords system
    protected GameStateManager gameStateManager; //to manage multiple game states

    public State(GameStateManager gameStateManager){ //constructor
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    public abstract void handleInput();

    public abstract void update(float deltaTime);  // deltatime = diff btween one frame rendered and the other frame to render

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();
}
