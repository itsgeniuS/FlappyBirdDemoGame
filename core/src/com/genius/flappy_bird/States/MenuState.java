package com.genius.flappy_bird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.genius.flappy_bird.FlappyBirdDemo;

/**
 * Created by Asort Technologies on 29-03-2018.
 */

public class MenuState extends State {

    private Texture background, playBtn;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FlappyBirdDemo.WIDTH / 2, FlappyBirdDemo.HEIGHT / 2);

        background = new Texture("background.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0 );
        spriteBatch.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
