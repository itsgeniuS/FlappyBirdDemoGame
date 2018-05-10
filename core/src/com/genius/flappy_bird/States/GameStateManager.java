package com.genius.flappy_bird.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Asort Technologies on 29-03-2018.
 */

public class GameStateManager {

    private Stack<State> gameState; //stack of game states  (menu, scire card, play screen, pause screen etc...)

    public GameStateManager(){
        gameState = new Stack<State>();
    }

    public void push(State state){
        gameState.push(state);
    }

    public void pop(){
        gameState.pop().dispose();
    }

    public void set(State state){
        gameState.pop().dispose();
        gameState.push(state);
    }

    public void update(float deltaTime){       //to update screen
        gameState.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch){  //to render objects to game screen
        gameState.peek().render(spriteBatch);
    }
}
