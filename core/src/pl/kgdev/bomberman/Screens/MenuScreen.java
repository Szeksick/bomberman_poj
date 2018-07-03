package pl.kgdev.bomberman.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import pl.kgdev.bomberman.Bomberman;

public class MenuScreen implements Screen {
//wielkosc buttonow
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT= 50;
    private static final int PLAYBUTTON_Y= 300;
    private static final int EXITBUTTON_Y= 100;
    private static final int STATBUTTON_Y= 200;


Bomberman game;
//buttony typu texture
Texture playButonActive;
Texture playButtonInacitve;
Texture statsButtonActive;
Texture statsButtonInactive;
Texture exitButtonActive;
Texture exitButtonInactive;



    public MenuScreen(Bomberman game){
        //LIFE IS LIFE
        this.game = game;
        //texturki z assets
        playButonActive = new Texture("playA.png");
        playButtonInacitve= new Texture("playI.png");
        statsButtonActive = new Texture("statA.png");
        statsButtonInactive = new Texture("statI.png");
        exitButtonActive = new Texture("exitA.png");
        exitButtonInactive = new Texture("exitI.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // ZACZYNAMY ZABAWEEEEEEE
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //wykrywanie klikniec w menu
        if(Gdx.input.getX() > (Bomberman.WIDTH/2-BUTTON_WIDTH/2) && Gdx.input.getX() < (Bomberman.WIDTH/2+BUTTON_WIDTH/2)){
            if(Gdx.input.getY() > Bomberman.HEIGHT-EXITBUTTON_Y-BUTTON_HEIGHT&& Gdx.input.getY() < Bomberman.HEIGHT-EXITBUTTON_Y ){
                game.batch.draw(exitButtonActive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), EXITBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
                if(Gdx.input.isTouched()){
                    Gdx.app.exit();
                }
            }else if(Gdx.input.getY() > Bomberman.HEIGHT-STATBUTTON_Y-BUTTON_HEIGHT && Gdx.input.getY() < Bomberman.HEIGHT-STATBUTTON_Y){
                game.batch.draw(statsButtonActive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), STATBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
                if(Gdx.input.isTouched()){
                    this.dispose();
                    game.setScreen(new StatsScreen(game));
                }
            }else if(Gdx.input.getY() > Bomberman.HEIGHT-PLAYBUTTON_Y-BUTTON_HEIGHT&& Gdx.input.getY() < Bomberman.HEIGHT-PLAYBUTTON_Y){
                game.batch.draw(playButonActive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), PLAYBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
                if(Gdx.input.isTouched()){
                    this.dispose();
                    game.setScreen(new GameScreen(game));
                }
            }
        }

        //rysowanie buttonow
        game.batch.draw(exitButtonInactive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), EXITBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.draw(statsButtonInactive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), STATBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        game.batch.draw(playButtonInacitve, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), PLAYBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
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
