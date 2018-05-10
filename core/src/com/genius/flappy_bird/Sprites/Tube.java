package com.genius.flappy_bird.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Asort Technologies on 29-03-2018.
 */

public class Tube {

    private static final int FLUCTUATION = 130, TUBE_GAP = 100, LOWEST_OPENING = 120;
    public static final int TUBE_WIDHT = 52;

    private Texture topTube, bottomTube;
    private Vector2 positionTopTube, positionBottomtube;
    private Random random;

    private Rectangle topBounds, bottomBounds;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();

        positionTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomtube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        topBounds = new Rectangle(positionTopTube.x, positionTopTube.y, topTube.getWidth(), topTube.getHeight());
        bottomBounds = new Rectangle(positionBottomtube.x, positionBottomtube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public void rePosistion(float x){
        positionTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomtube.set(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        topBounds.setPosition(positionTopTube.x, positionTopTube.y);
        bottomBounds.setPosition(positionBottomtube.x, positionBottomtube.y);
    }

    public boolean collide(Rectangle player){
        return player.overlaps(topBounds) || player.overlaps(bottomBounds);
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomtube() {
        return positionBottomtube;
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }

}
