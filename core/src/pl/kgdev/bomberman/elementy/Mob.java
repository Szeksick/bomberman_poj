package pl.kgdev.bomberman.elementy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.kgdev.bomberman.Kolizje.CollisionRect;

import java.util.Random;


public class Mob {

    public static float SPEED = 40;
    public static final float SPEED_ANIMATION = 0.5f;
    public static final int PIXEL_WIDTH = 31;
    public static final int PIXEL_HEIGHT = 29;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private float delta = Gdx.graphics.getDeltaTime();
    private int MOVE_TIMER = 1;
    public int state;//1 odliczanie, 2 ruch, 3 śmierć

    Animation[] moves;//deklaracja tablicy animacji

    float x;
    float y;
    CollisionRect rect;
    int moveDirection;
    int move;
    float moveTimer;
    float stateTime;
    
    public Mob(float x, float y){
    this.x = 50*Math.round(x/50);;
    this.y = 50*Math.round(y/50);;
    this.rect = new CollisionRect(x,y,WIDTH, HEIGHT);
    move = 1;
    moveDirection =1;
    moveTimer = 0;
    moves = new Animation[4];//definicja tablicy animacji
    //siekam sprajta to nie fanta
    TextureRegion[][] moveSpriteSheet = TextureRegion.split(new Texture("chicken.png"), PIXEL_WIDTH, PIXEL_HEIGHT);
    //tablica animacji
    moves[0] = new Animation(SPEED_ANIMATION, moveSpriteSheet[0]);//w dol
    moves[1] = new Animation(SPEED_ANIMATION, moveSpriteSheet[1]);//w lewo
    moves[2] = new Animation(SPEED_ANIMATION, moveSpriteSheet[2]);//w prawo
    moves[3] = new Animation(SPEED_ANIMATION, moveSpriteSheet[3]);//w gore



    }

    public void kierunek(){
        MOVE_TIMER++;
        if(MOVE_TIMER >= 240) {
            moveDirection = new Random().nextInt(4)+1;
            MOVE_TIMER = 1;
        }
    }

    private void ruch() {
        kierunek();

        if(moveDirection == 1){
            this.y += SPEED * Gdx.graphics.getDeltaTime();
            move=3;
            stateTime += delta/SPEED_ANIMATION;
        }
        if(moveDirection == 2){
            this.y -= SPEED * Gdx.graphics.getDeltaTime();
            move=0;
            stateTime += delta/SPEED_ANIMATION;
        }
        if(moveDirection == 3){
            this.x -= SPEED * Gdx.graphics.getDeltaTime();
            move=1;
            stateTime += delta/SPEED_ANIMATION;
        }
        if(moveDirection == 4){
            this.x += SPEED * Gdx.graphics.getDeltaTime();
            move=2;
            stateTime += delta/SPEED_ANIMATION;
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw((TextureRegion) moves[move].getKeyFrame(stateTime,true),x,y,WIDTH,HEIGHT);
    }

    private void dead() {

    }

    public void update(float delta) {
        ruch();
        rect.move(x, y);
    }

    public CollisionRect getCollisionRect(){
        return rect;
    }

}
