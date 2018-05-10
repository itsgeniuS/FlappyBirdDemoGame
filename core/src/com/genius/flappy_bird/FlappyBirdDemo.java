package com.genius.flappy_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.genius.flappy_bird.States.GameStateManager;
import com.genius.flappy_bird.States.MenuState;

public class FlappyBirdDemo extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";

	private GameStateManager gameStateManager;
	private SpriteBatch spriteBatch; //create one for whole game - heavy files so no multiple creations

    private Sound sound;
    private Music music;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		gameStateManager = new GameStateManager();

		music = Gdx.audio.newMusic(Gdx.files.internal("butterfly.wav"));
		music.setLooping(true);
		music.setVolume(1f);
		music.play();

		Gdx.gl.glClearColor(1, 0, 0, 1); //to wipe the screen out completly

        gameStateManager.push(new MenuState(gameStateManager));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStateManager.update(Gdx.graphics.getDeltaTime());

		gameStateManager.render(spriteBatch);

	}
	
	@Override
	public void dispose () {
        spriteBatch.dispose();
        music.dispose();
	}
}
