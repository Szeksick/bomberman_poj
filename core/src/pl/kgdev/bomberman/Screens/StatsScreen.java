package pl.kgdev.bomberman.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import pl.kgdev.bomberman.Bomberman;
import pl.kgdev.bomberman.mysql.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatsScreen implements Screen {
    //wielkosc buttonow
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT= 50;
    private static final int BACKBUTTON_Y= 100;


    Bomberman game;
    //buttony typu texture

    Texture backButtonActive;
    Texture backButtonInactive;

    //obiekt połączenia z baza
    MysqlConnect mysqlConnect;



    public StatsScreen(Bomberman game){
        //LIFE IS LIFE
        this.game = game;
        //texturki z assets
        backButtonActive = new Texture("exitA.png");
        backButtonInactive = new Texture("exitI.png");
        mysqlConnect = new MysqlConnect();
        //proba fetcha z bazy
       String sql = "INSERT INTO `stats` (`ID`, `NAME`, `SCORE`) VALUES (NULL, 'XD', '997');";//zapytanie sql
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);//wykonanie zapytania statement to wynik operacji
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }

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
            if(Gdx.input.getY() > Bomberman.HEIGHT-BACKBUTTON_Y-BUTTON_HEIGHT&& Gdx.input.getY() < Bomberman.HEIGHT-BACKBUTTON_Y ){
                game.batch.draw(backButtonActive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), BACKBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
                if(Gdx.input.isTouched()){
                    game.setScreen(new MenuScreen(game));
                }
            }
        }
        //rysowanie buttonow
        game.batch.draw(backButtonInactive, (Bomberman.WIDTH/2-BUTTON_WIDTH/2), BACKBUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
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
