package pl.kgdev.bomberman.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.kgdev.bomberman.Bomberman;
import pl.kgdev.bomberman.Kolizje.CollisionRect;
import pl.kgdev.bomberman.elementy.Bomb;
import pl.kgdev.bomberman.elementy.Bush;
import pl.kgdev.bomberman.elementy.Mob;
import pl.kgdev.bomberman.elementy.Wall;

import java.util.ArrayList;

import static com.badlogic.gdx.Input.Keys.*;

public class GameScreen implements Screen {


    public static final float SPEED = 150;
    public static final float CHICKEN_SPEED_ANIMATION = 0.5f;
    public static final int CHICKEN_PIXEL_WIDTH = 31;
    public static final int CHICKEN_PIXEL_HEIGHT = 29;
    public static final float CHICKEN_WIDTH = 40;
    public static final float CHICKEN_HEIGHT = 40;
    private static final float BOMB_WAIT_TIME = 0.9f;
    private int HIT_POINTS = 100;



    Animation[] moves;//deklaracja tablicy animacji

    float x;
    float y;
    int move;
    float moveTimer;
    float stateTime;
    float bomb_timer;


    CollisionRect rect;
    Bomberman game;
    ArrayList<Bomb> bomby;
    ArrayList<Mob> moby;
    Wall[] walls = {
            //dol
            new Wall(0,0),     new Wall(0,50),   new Wall(0,100),    new Wall(0,150),    new Wall(0,200),    new Wall(0,250),
            new Wall(0,300),   new Wall(0,350),  new Wall(0,400),    new Wall(0,450),    new Wall(0,500),    new Wall(0,550),
            new Wall(0,600),   new Wall(0,650),  new Wall(0,700),
            //gora
            new Wall(500,0),   new Wall(500,50),  new Wall(500,100), new Wall(500,150),  new Wall(500,200),  new Wall(500,250),
            new Wall(500,300), new Wall(500,350), new Wall(500,400), new Wall(500,450),  new Wall(500,500),  new Wall(500,550),
            new Wall(500,600), new Wall(500,650), new Wall(500,700),
            //prawa
            new Wall(50,700),  new Wall(100,700), new Wall(150,700), new Wall(200,700),  new Wall(250,700),   new Wall(300,700),
            new Wall(350,700), new Wall(400,700), new Wall(450,700),
            //lewa
            new Wall(50,0),  new Wall(100,0), new Wall(150,0), new Wall(200,0),  new Wall(250,0),   new Wall(300,0),
            new Wall(350,0), new Wall(400,0), new Wall(450,0),
            //pierwszy rzad
            new Wall(100,100),     new Wall(200,100),   new Wall(300,100),    new Wall(400,100),
            //drugi rzad
            new Wall(100,200),     new Wall(200,200),   new Wall(300,200),    new Wall(400,200),
            //trzeci rzad
            new Wall(100,300),     new Wall(200,300),   new Wall(300,300),    new Wall(400,300),
            //czwarty rzad
            new Wall(100,400),     new Wall(200,400),   new Wall(300,400),    new Wall(400,400),
            //piaty rzad
            new Wall(100,500),     new Wall(200,500),   new Wall(300,500),    new Wall(400,500),
            //szosty rzad
            new Wall(100,600),     new Wall(200,600),   new Wall(300,600),    new Wall(400,600)
    };

    Bush[] bush = {

            new Bush(150,100),     new Bush(250,100),   new Bush(250,150),    new Bush(300,150),
            new Bush(400,150),     new Bush(350,200),   new Bush(200,250),    new Bush(400,250),
            new Bush(150,300),     new Bush(100,350),   new Bush(300,350),    new Bush(250,400),
            new Bush(350,400),     new Bush(200,450),   new Bush(250,450),    new Bush(300,450),
            new Bush(350,450),     new Bush(400,450),   new Bush(150,500),    new Bush(350,500),
            new Bush(100,550),     new Bush(300,550),   new Bush(250,600)
    };





    public GameScreen(Bomberman game) {
        this.game = game;
        bomby = new ArrayList<Bomb>();
        moby = new ArrayList<Mob>();
        y = 51;
        x = 51;
        this.rect = new CollisionRect(x,y,10, 10);
        move = 1;
        moveTimer = 0;
        bomb_timer = 0;
        moves = new Animation[4];//definicja tablicy animacji
        //siekam sprajta to nie fanta
        TextureRegion[][] moveSpriteSheet = TextureRegion.split(new Texture("Wall.png"), CHICKEN_PIXEL_WIDTH, CHICKEN_PIXEL_HEIGHT);
        //tablica animacji
        moves[0] = new Animation(CHICKEN_SPEED_ANIMATION, moveSpriteSheet[0]);//w dol
        moves[1] = new Animation(CHICKEN_SPEED_ANIMATION, moveSpriteSheet[1]);//w lewo
        moves[2] = new Animation(CHICKEN_SPEED_ANIMATION, moveSpriteSheet[2]);//w prawo
        moves[3] = new Animation(CHICKEN_SPEED_ANIMATION, moveSpriteSheet[3]);//w gore

        //Sciany

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //zostawianie bomby
        bomb_timer += delta;
               if(Gdx.input.isKeyJustPressed(SPACE) && bomb_timer>=BOMB_WAIT_TIME){
                   bomb_timer = 0;
                   bomby.add(new Bomb(this.x,this.y));
                }

        if(Gdx.input.isKeyJustPressed(C)){
            moby.add(new Mob(this.x,this.y));
        }
        for (Wall wall:walls){
            if(wall.getCollisionRect().collidesWith(this.rect)) {
                System.out.println("Gracz koliduje x:"+x+" y:"+y);
                if(Gdx.input.isKeyPressed(UP)){
                    this.y -= 10;//SPEED * Gdx.graphics.getDeltaTime();
                    move=3;
                    stateTime += delta/CHICKEN_SPEED_ANIMATION;
                }
                if(Gdx.input.isKeyPressed(DOWN)){
                    this.y += 10;//SPEED * Gdx.graphics.getDeltaTime();
                    move=0;
                    stateTime += delta/CHICKEN_SPEED_ANIMATION;
                }
                if(Gdx.input.isKeyPressed(LEFT)){
                    this.x += 10;//SPEED * Gdx.graphics.getDeltaTime();
                    move=1;
                    stateTime += delta/CHICKEN_SPEED_ANIMATION;
                }
                if(Gdx.input.isKeyPressed(RIGHT)){
                    this.x -= 10;//SPEED * Gdx.graphics.getDeltaTime();
                    move=2;
                    stateTime += delta/CHICKEN_SPEED_ANIMATION;
                }

            }
            for (Mob mob: moby){
                if(mob.getCollisionRect().collidesWith(wall.getCollisionRect())) {

                    System.out.println("Kolizja mob x:"+mob.getCollisionRect().getX()+" y:"+mob.getCollisionRect().getY());
                }

            }
        }

        //poruszanie sie postaci
        if(Gdx.input.isKeyPressed(UP)){
            this.y += SPEED * Gdx.graphics.getDeltaTime();
            move=3;
            stateTime += delta/CHICKEN_SPEED_ANIMATION;
        }else
        if(Gdx.input.isKeyPressed(DOWN)){
            this.y -= SPEED * Gdx.graphics.getDeltaTime();
            move=0;
            stateTime += delta/CHICKEN_SPEED_ANIMATION;
        }else
        if(Gdx.input.isKeyPressed(LEFT)){
            this.x -= SPEED *  Gdx.graphics.getDeltaTime();
            move=1;
            stateTime += delta/CHICKEN_SPEED_ANIMATION;
        }else
        if(Gdx.input.isKeyPressed(RIGHT)){
            this.x += SPEED * Gdx.graphics.getDeltaTime();
            move=2;
            stateTime += delta/CHICKEN_SPEED_ANIMATION;
        }
        //kolizcje
        this.rect.move(x, y);


        //update bomb i mobów
        ArrayList<Bomb> bombydousunieca = new ArrayList<Bomb>();
        for(Bomb bomb: bomby){
            bomb.update(delta);
            if(bomb.remove) {
                bombydousunieca.add(bomb);
            }
        }
        bombydousunieca.removeAll(bombydousunieca);
        ArrayList<Mob> mobydousuniecia = new ArrayList<Mob>();
        for (Mob mob: moby) {
            mob.update(delta);
            if(mob.state == 3)
                mobydousuniecia.add(mob);
        }

        //śmierć gracza
        if(HIT_POINTS<=0) {
            this.dispose();
            game.setScreen(new StatsScreen(game));
        }
        //rysowanie na ekranie
        //czyszczenie ekanu na kolor w rgba
        Gdx.gl.glClearColor(130/255f, 130/255f, 130/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //otwieram wiadro z farba i maluje
        game.batch.begin();
        for(Bomb bomb: bomby) bomb.render(this.game.batch);
        for(Mob mob: moby)mob.render(this.game.batch);
        for (Wall wall:walls) wall.render(this.game.batch);
        for (Bush busz:bush) busz.render(this.game.batch);
        game.batch.draw((TextureRegion) moves[move].getKeyFrame(stateTime,true),x,y,CHICKEN_WIDTH,CHICKEN_HEIGHT);
            //zamykam koniec malowania
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
