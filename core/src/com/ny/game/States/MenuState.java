package com.ny.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ny.game.Flappy;

/**
 * Created by Naim YÜREK on 29.08.2016.
 */
public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background=new Texture("bg.png");
        playBtn=new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gameStateManager.setState(new PlayState(gameStateManager));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background,0,0, Flappy.WIDTH,Flappy.HEIGHT);
        spriteBatch.draw(playBtn,(Flappy.WIDTH/2)-(playBtn.getWidth()/2), Flappy.HEIGHT/2);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Dispose");
    }
}
