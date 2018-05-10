package com.genius.flappy_bird.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Asort Technologies on 29-03-2018.
 */

public class Animation {

    private Array<TextureRegion> frames; // to store all frames
    private float maxFrameTime; // how long frame has to be viewed
    private float currentFrameTime; //animation time in current frame
    private int frameCount; //no of frames in our animation
    private int frame; //denotes current frame

    public Animation(TextureRegion textureRegion, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = textureRegion.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void updateAnimation(float deltaTime){
        currentFrameTime += deltaTime;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }

}
