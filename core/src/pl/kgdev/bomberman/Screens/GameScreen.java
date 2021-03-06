package pl.kgdev.bomberman.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import pl.kgdev.bomberman.Bomberman;
import pl.kgdev.bomberman.elementy.*;
import java.util.ArrayList;
import static com.badlogic.gdx.Input.Keys.*;

public class GameScreen implements Screen {

    float x;
    float y;

    Gracz g1;
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
            new Wall(100,600),     new Wall(200,600),   new Wall(300,600),   new Wall(400,600)
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
        g1 = new Gracz(this.x,this.y);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //zostawianie bomby
               if(Gdx.input.isKeyJustPressed(SPACE)){
                   g1.dropBomb(bomby);
                }

        if(Gdx.input.isKeyJustPressed(C)){
            moby.add(new Mob(this.x,this.y));
        }
        for (Wall wall:walls){
            if(wall.getCollisionRect().collidesWith(g1.getCollisionRect())) {
                if(g1.y<=wall.y&&Gdx.input.isKeyPressed(UP)){
                    System.out.println("Gracz koliduje od dolu x:"+g1.x+" y:"+g1.y);
                    g1.UP_BLOCKED = true;
                    g1.LEFT_BLOCKED=true;
                    g1.RIGHT_BLOCKED=true;
                      g1.moveDown();
                }if(wall.y<=g1.y && Gdx.input.isKeyPressed(DOWN)){
                    System.out.println("Gracz koliduje od gory x:"+g1.x+" y:"+g1.y);
                    g1.DOWN_BLOCKED = true;
                    g1.LEFT_BLOCKED=true;
                    g1.RIGHT_BLOCKED=true;
                    g1.moveUp();
                }if(wall.x+wall.width<=g1.x&& Gdx.input.isKeyPressed(LEFT)){
                    System.out.println("Gracz koliduje od prawej x:"+g1.x+" y:"+g1.y);
                    g1.LEFT_BLOCKED = true;
                    g1.UP_BLOCKED=true;
                    g1.DOWN_BLOCKED=true;
                     g1.moveRight();
                }if(g1.x<=wall.x && Gdx.input.isKeyPressed(RIGHT)){
                    System.out.println("Gracz koliduje od lewej x:"+g1.x+" y:"+g1.y);
                    g1.RIGHT_BLOCKED = true;
                    g1.UP_BLOCKED=true;
                    g1.DOWN_BLOCKED=true;
                    g1.moveLeft();
                }

            }
            for (Mob mob: moby){
                if(mob.getCollisionRect().collidesWith(wall.getCollisionRect())) {
                    if(mob.y<=wall.y&&mob.moveDirection == 1){
                        System.out.println("Mob koliduje x:"+mob.x+" y:"+mob.y);
                        mob.UP_BLOCKED = true;
                        mob.LEFT_BLOCKED=true;
                        mob.RIGHT_BLOCKED=true;
                        mob.odwrot();
                        mob.moveDown();
                    }if(wall.y<=mob.y && mob.moveDirection == 2){
                        System.out.println("Mob koliduje x:"+mob.x+" y:"+mob.y);
                        mob.DOWN_BLOCKED = true;
                        mob.LEFT_BLOCKED=true;
                        mob.RIGHT_BLOCKED=true;
                        mob.odwrot();
                        mob.moveUp();
                    }if(wall.x+wall.width<=mob.x && mob.moveDirection == 3){
                        System.out.println("Mob koliduje x:"+mob.x+" y:"+mob.y);
                        mob.LEFT_BLOCKED = true;
                        mob.UP_BLOCKED=true;
                        mob.DOWN_BLOCKED=true;
                        mob.odwrot();
                        mob.moveRight();
                    }if(mob.x<=wall.x && mob.moveDirection == 4){
                        System.out.println("Mob koliduje x:"+mob.x+" y:"+mob.y);
                        mob.RIGHT_BLOCKED = true;
                        mob.UP_BLOCKED=true;
                        mob.DOWN_BLOCKED=true;
                        mob.odwrot();
                        mob.moveLeft();
                    }

                }

            }
        }

        //poruszanie sie postaci
        if(Gdx.input.isKeyPressed(UP)){
           g1.moveUp();
        }if(Gdx.input.isKeyPressed(DOWN)){
            g1.moveDown();
        }if(Gdx.input.isKeyPressed(LEFT)){
           g1.moveLeft();
        }if(Gdx.input.isKeyPressed(RIGHT)){
            g1.moveRight();
        }
        //kolizcje


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
            if(mob.getCollisionRect().collidesWith(g1.getCollisionRect())){
                g1.getHit(20);
            }
            mob.update(delta);
            if(mob.state == 3|| mob.x>0 || mob.y>0||mob.x>Bomberman.WIDTH || mob.y>Bomberman.HEIGHT) {
                mobydousuniecia.add(mob);
                System.out.println("Mob koliduje x:"+mob.x+" y:"+mob.y);
            }
        }

        //śmierć gracza
        if(g1.HIT_POINTS<=0) {
           // this.dispose();
           // game.setScreen(new StatsScreen(game));
            this.pause();
        }
        g1.update(delta);

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
        game.font.setColor(Color.GREEN);
        game.font.draw(game.batch,"ZYCIE : ",300,25);
        game.font.setColor(Color.RED);
        game.font.draw(game.batch,Integer.toString(g1.HIT_POINTS),360,25);
        g1.render(this.game.batch);
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
