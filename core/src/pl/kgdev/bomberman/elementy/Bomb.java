package pl.kgdev.bomberman.elementy;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;


public class Bomb {
    public static final int SPEED = 300;
    private static Texture texture;


    int x,y;
    private static int WIDTH = 50;
    private static int HEIGHT = 50;
    public static final float SPEED_ANIMATION = 0.5f;
    public static final float JEB_WAIT_TIMER = 2.8f;
//    czytamy dżeb_łajt_tajmer ;-)
    Animation[] ticks;

    public boolean inmove = false;
    public boolean remove = false;
    public int state;//1 odliczanie, 2 ruch, 3 eksplozja
    private float stateTime, jeb_timer;

    public Bomb(float x, float y){
        this.x = 50*Math.round(x/50);
        this.y = 50*Math.round(y/50);
        jeb_timer = 0;

        ticks = new Animation[1];
        TextureRegion[][] moveSpriteSheet = TextureRegion.split(new Texture("tnt.jpg"), 50, 50);
        ticks[0] = new Animation(SPEED_ANIMATION, moveSpriteSheet[0]);
        if(texture == null){
            texture = new Texture("tnt.jpg");
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw((TextureRegion) ticks[0].getKeyFrame(stateTime,true),x,y,this.WIDTH,this.HEIGHT);
    }


    public void update(float delta, ArrayList<Explozja> boomholder) {
            if(jeb_timer >= JEB_WAIT_TIMER){
                boomholder.add(new Explozja(x+this.WIDTH/2,y+this.HEIGHT/2));
                this.state = 3;
                this.remove = true;

                System.out.println("bomba dousuniecia");
            }
        jeb_timer += delta;
        stateTime += delta / SPEED_ANIMATION;
    }
}
