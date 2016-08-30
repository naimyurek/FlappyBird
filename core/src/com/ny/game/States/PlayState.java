package com.ny.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ny.game.Flappy;
import com.ny.game.Sprites.Bird;
import com.ny.game.Sprites.Tube;

/**
 * Created by Naim YÜREK on 29.08.2016.
 */
public class PlayState extends State {
    private static final int TUBE_SPACING=125;
    private static final int TUBE_COUNT=4;
    private static final int TUBE_START=50;
    private static final int GROUND_Y_OFFSET=-50;

    private Bird bird;
    private Texture ground;
    private Texture bg;
    private Tube tube;
    private Vector2 groundPos1,groundPos2;


    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird=new Bird(50,300);
        camera.setToOrtho(false, Flappy.WIDTH/2,Flappy.HEIGHT/2);
        bg=new Texture("bg.png");
        tube=new Tube(100);
        ground=new Texture("ground.png");
        groundPos1=new Vector2(camera.position.x-camera.viewportWidth/2,0);
        groundPos2=new Vector2(camera.position.x-camera.viewportWidth/2+ground.getWidth(),0);

        tubes=new Array<Tube>();
        for (int i=0;i<TUBE_COUNT;i++){
            tubes.add(new Tube((i+1)*(TUBE_SPACING+Tube.TUBE_WIDTH)+TUBE_START));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x=bird.getPosition().x+80;

        for (Tube tube:tubes){
            if(camera.position.x-(camera.viewportWidth/2)>tube.getPosTopTube().x+tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x+((Tube.TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())){
                gameStateManager.setState(new PlayState(gameStateManager));
            }
        }
        if (bird.getPosition().y<=ground.getHeight()+GROUND_Y_OFFSET){
            gameStateManager.setState(new PlayState(gameStateManager));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg,camera.position.x-(camera.viewportWidth/2),0);
        spriteBatch.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        for (Tube tube:tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        spriteBatch.draw(ground,groundPos1.x,groundPos1.y);
        spriteBatch.draw(ground,groundPos2.x,groundPos2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        System.out.println("PlayState Dispose");
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube:tubes){
            tube.dispose();
        }

    }
    private void updateGround(){
        if(camera.position.x-(camera.viewportWidth/2)>groundPos1.x+ground.getWidth()){
            groundPos1.add(ground.getWidth()*2,0);
        }
        if(camera.position.x-(camera.viewportWidth/2)>groundPos2.x+ground.getWidth()){
            groundPos2.add(ground.getWidth()*2,0);
        }
    }
}
