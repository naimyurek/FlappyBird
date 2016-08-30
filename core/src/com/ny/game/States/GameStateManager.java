package com.ny.game.States;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Naim YÜREK on 29.08.2016.
 */
public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states=new Stack<State>();
    }
    public void push(State state){
        states.push(state);
    }
    public void pop(){
        states.pop();
    }
    public void setState(State state){
        states.pop();
        states.push(state);
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }

}
