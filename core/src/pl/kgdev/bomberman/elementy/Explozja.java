package pl.kgdev.bomberman.elementy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.kgdev.bomberman.KolizjeiEventy.CollisionRect;


public class Explozja{
    private static Texture texture;

    float x,y;
    private static int WIDTH = 150;
    private static int HEIGHT = 150;
    public static final float SPEED_ANIMATION = 0.1f;
    public static final float JEB_WAIT_TIMER = 0.5f;
//    czytamy dżeb_łajt_tajmer ;-)

    Animation[] ticks;
    CollisionRect rect;

    public boolean remove = false;
    private float stateTime, jeb_timer;

    public Explozja(float x, float y){
        this.x = +x-this.WIDTH/2;
        this.y = +y-this.HEIGHT/2;
        this.rect = new CollisionRect(this.x,this.y,WIDTH, HEIGHT);
        jeb_timer = 0;
        ticks = new Animation[1];
        TextureRegion[][] moveSpriteSheet = TextureRegion.split(new Texture("tnt.jpg"), 50, 50);
        ticks[0] = new Animation(SPEED_ANIMATION, moveSpriteSheet[0]);

    }

    public void render(SpriteBatch batch) {
        batch.draw((TextureRegion) ticks[0].getKeyFrame(stateTime,true),this.x,this.y,this.WIDTH,this.HEIGHT);
    }

    public CollisionRect getCollisionRect(){
        return rect;
    }
    public void update(float delta) {
        if(jeb_timer >= JEB_WAIT_TIMER){
            remove=true;
            System.out.println("eksplozja dousuniecia");
        }
        jeb_timer += delta;
        this.rect.move(this.x, this.y);
        stateTime += delta / SPEED_ANIMATION;
    }
}
