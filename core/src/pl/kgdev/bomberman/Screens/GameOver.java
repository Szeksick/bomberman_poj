package pl.kgdev.bomberman.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import pl.kgdev.bomberman.Bomberman;

public class GameOver implements Screen {
//wielkosc buttonow
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT= 50;
    private static final int PLAYBUTTON_Y= 150;
    private static final int EXITBUTTON_Y= 50;
    private static final int STATBUTTON_Y= 100;


Bomberman game;
//buttony typu texture
Texture playButonActive;
Texture playButtonInacitve;
Texture statsButtonActive;
Texture statsButtonInactive;
Texture exitButtonActive;
Texture exitButtonInactive;
Texture pepe;



    public GameOver(Bomberman game){
        //LIFE IS LIFE
        this.game = game;
        //texturki z assets
        statsButtonActive = new Texture("save.png");
        statsButtonInactive = new Texture("saveA.png");
        exitButtonActive = new Texture("backA.png");
        exitButtonInactive = new Texture("backI.png");
        pepe= new Texture("pepe.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // ZACZYNAMY ZABAWEEEEEEE
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.setColor(Color.GREEN);
        game.font.draw(game.batch,"Punkty : "+game.GLOBAL_POINTS,Bomberman.WIDTH/2-50,PLAYBUTTON_Y+25);
        game.batch.draw(pepe, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), PLAYBUTTON_Y+50,BUTTON_WIDTH,BUTTON_WIDTH);
        //rysowanie buttonow
        game.batch.draw(exitButtonInactive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), EXITBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.draw(statsButtonInactive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), STATBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        //wykrywanie klikniec w menu
        if(Gdx.input.getX() > (Bomberman.WIDTH/2-BUTTON_WIDTH/2) && Gdx.input.getX() < (Bomberman.WIDTH/2+BUTTON_WIDTH/2)){
            if(Gdx.input.getY() > Bomberman.HEIGHT-EXITBUTTON_Y-BUTTON_HEIGHT&& Gdx.input.getY() < Bomberman.HEIGHT-EXITBUTTON_Y ){
                game.batch.draw(exitButtonActive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), EXITBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
                if(Gdx.input.isTouched()){
                    this.dispose();
                    game.setScreen(new MenuScreen(game));
                }
            }else if(Gdx.input.getY() > Bomberman.HEIGHT-STATBUTTON_Y-BUTTON_HEIGHT && Gdx.input.getY() < Bomberman.HEIGHT-STATBUTTON_Y){
                game.batch.draw(statsButtonActive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), STATBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
                if(Gdx.input.isTouched()){
                    this.dispose();
                    game.setScreen(new StatsScreen(game));
                }
            }
        }


        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
