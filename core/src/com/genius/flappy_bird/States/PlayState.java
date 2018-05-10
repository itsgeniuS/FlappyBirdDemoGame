package com.genius.flappy_bird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.genius.flappy_bird.FlappyBirdDemo;
import com.genius.flappy_bird.Sprites.Bird;
import com.genius.flappy_bird.Sprites.Tube;

import java.util.Vector;

/**
 * Created by Asort Technologies on 29-03-2018.
 */

public class PlayState extends State {

    //private Texture bird;
    private Bird bird;
    private Texture backgroundTexture, groundTexture;
    private Vector2 groundPosition1, groundPosition2;

    private static final int TUBE_SPACING = 150;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Array<Tube> tubeArray;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        // bird = new Texture("bird.png");
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyBirdDemo.WIDTH / 2, FlappyBirdDemo.HEIGHT / 2);
        backgroundTexture = new Texture("background.png");
        groundTexture = new Texture("ground.png");

        groundPosition1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((camera.position.x - camera.viewportWidth /2) + groundTexture.getWidth(), GROUND_Y_OFFSET);

        tubeArray = new Array<Tube>();

        for (int i = 0; i <=  TUBE_COUNT; i++){
            tubeArray.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDHT)));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        updateGround();
        bird.update(deltaTime);

        camera.position.x = bird.getPosition().x + 80;

        for(int i=0; i < tubeArray.size; i++){
            Tube tube = tubeArray.get(i);

            if(camera.position.x - camera.viewportWidth / 2 > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.rePosistion(tube.getPositionTopTube().x +((Tube.TUBE_WIDHT + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collide(bird.getBounds())){
                gameStateManager.set(new PlayState(gameStateManager));
            }
        }

        if(bird.getPosition().y <= groundTexture.getHeight() + GROUND_Y_OFFSET)
            gameStateManager.set(new PlayState(gameStateManager));

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(backgroundTexture, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for(Tube tube : tubeArray){
                spriteBatch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
                spriteBatch.draw(tube.getBottomTube(), tube.getPositionBottomtube().x, tube.getPositionBottomtube().y);
        }

        spriteBatch.draw(groundTexture, groundPosition1.x, groundPosition1.y);
        spriteBatch.draw(groundTexture, groundPosition2.x, groundPosition2  .y);

        spriteBatch.end();

    }

    private void updateGround(){
        if(camera.position.x - (camera.viewportWidth /2) > groundPosition1.x + groundTexture.getWidth())
            groundPosition1.add(groundTexture.getWidth() * 2, 0);
        if(camera.position.x - (camera.viewportWidth / 2) > groundPosition2.x + groundTexture.getWidth())
            groundPosition2.add(groundTexture.getWidth() * 2, 0);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        bird.dispose();
        groundTexture.dispose();
        for(Tube tube : tubeArray){
            tube.dispose();
        }
    }
}
