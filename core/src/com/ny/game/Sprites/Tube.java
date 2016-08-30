package com.ny.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.Random;

/**
 * Created by Naim YÜREK on 29.08.2016.
 */
public class Tube {
    public static final int TUBE_WIDTH=52;

    private static final int FLUCTUTATION=130;
    private static final int TUBE_GAP=100;
    private static final int LOWEST_OPENING=120;

    private Rectangle boundsTop,boundsBot;
    private Texture topTube,bottomTube;
    private Vector2 posTopTube,posBottomTube;
    private Random random;
    public Tube(float x){
        topTube=new Texture("toptube.png");
        bottomTube=new Texture("bottomtube.png");
        random=new Random();

        posTopTube=new Vector2(x,random.nextInt(FLUCTUTATION)+TUBE_GAP+LOWEST_OPENING);
        posBottomTube=new Vector2(x,posTopTube.y-TUBE_GAP-bottomTube.getHeight());

        boundsTop=new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundsBot=new Rectangle(posBottomTube.x,posBottomTube.y,bottomTube.getWidth(),bottomTube.getHeight());

    }

    public Texture getTopTube() {
        return topTube;
    }

    public void setTopTube(Texture topTube) {
        this.topTube = topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public void setBottomTube(Texture bottomTube) {
        this.bottomTube = bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public void setPosTopTube(Vector2 posTopTube) {
        this.posTopTube = posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void setPosBottomTube(Vector2 posBottomTube) {
        this.posBottomTube = posBottomTube;
    }
    public void reposition(float x){
        posTopTube.set(x,random.nextInt(FLUCTUTATION)+TUBE_GAP+LOWEST_OPENING);
        posBottomTube.set(x,posTopTube.y-TUBE_GAP-bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x,posTopTube.y);
        boundsBot.setPosition(posBottomTube.x,posBottomTube.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop)||player.overlaps(boundsBot);
    }
    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
