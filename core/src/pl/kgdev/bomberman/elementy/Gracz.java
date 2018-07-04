package pl.kgdev.bomberman.elementy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.kgdev.bomberman.KolizjeiEventy.CollisionRect;

import java.util.ArrayList;


public class Gracz{

    public static float SPEED = 150;
    public static final float SPEED_ANIMATION = 0.5f;
    public static final int PIXEL_WIDTH = 31;
    public static final int PIXEL_HEIGHT = 29;
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    private float delta = Gdx.graphics.getDeltaTime();
    private int MOVE_TIMER = 1;
    private static final float BOMB_WAIT_TIME = 0.9f, HIT_WAIT_TIME = 1.9f;
    public int HIT_POINTS = 100;

    Animation[] moves;//deklaracja tablicy animacji

    public float x;
    public float y;
    public boolean RIGHT_BLOCKED = false;
    public boolean LEFT_BLOCKED = false;
    public boolean UP_BLOCKED = false;
    public boolean DOWN_BLOCKED = false;
    CollisionRect rect;
    int move;
    float moveTimer,bomb_timer,stateTime, hit_timer;


    public Gracz(float x, float y){
    this.x = 50*Math.round(x/50);;
    this.y = 50*Math.round(y/50);;
    this.rect = new CollisionRect(x,y,WIDTH, HEIGHT);
    move = 1;
    bomb_timer = 0;
    hit_timer = 0;
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

    public void moveRight(){
        if(this.RIGHT_BLOCKED == false) this.x += SPEED * Gdx.graphics.getDeltaTime();
            move = 2;
            stateTime += delta / SPEED_ANIMATION;
    }
    public void moveLeft(){
        if(this.LEFT_BLOCKED == false) this.x -= SPEED * Gdx.graphics.getDeltaTime();
            move = 1;
            stateTime += delta / SPEED_ANIMATION;
    }
    public void moveUp(){
        if(this.UP_BLOCKED == false) this.y += SPEED * Gdx.graphics.getDeltaTime();
            move = 3;
            stateTime += delta / SPEED_ANIMATION;
    }
    public void moveDown(){
        if(this.DOWN_BLOCKED == false) this.y -= SPEED * Gdx.graphics.getDeltaTime();
            move = 0;
            stateTime += delta / SPEED_ANIMATION;
    }

    public void render(SpriteBatch batch) {
        batch.draw((TextureRegion) moves[move].getKeyFrame(stateTime,true),x,y,WIDTH,HEIGHT);
    }
    public void dropBomb(ArrayList<Bomb> bomby){
        if(bomb_timer>=BOMB_WAIT_TIME) {
            bomb_timer = 0;
            bomby.add(new Bomb(this.x, this.y));
        }
    }
    private void dead() {

    }

    public void update(float delta) {
        bomb_timer += delta;
        hit_timer += delta;
        this.rect.move(this.x, this.y);
        RIGHT_BLOCKED = false;
        LEFT_BLOCKED = false;
        UP_BLOCKED = false;
        DOWN_BLOCKED = false;
    }

    public CollisionRect getCollisionRect(){
        return rect;
    }

    public void getHit(int moc) {
        if(hit_timer>=HIT_WAIT_TIME){
            this.HIT_POINTS -= moc;
            hit_timer=0;
        }
    }
}
