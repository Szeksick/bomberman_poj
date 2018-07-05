package pl.kgdev.bomberman.elementy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.kgdev.bomberman.KolizjeiEventy.CollisionRect;

public class Potion {
    private static Texture texture;
    public float x,y;
    public int width, height, type;
    public static final int POTION_WIDTH = 20;
    public static final int POTION_HEIGHT = 20;
    CollisionRect rect;



    public boolean remove = false;


    public Potion(float y, float x,int type){
        this.x = x;
        this.y = y;
        this.type=type;
        this.rect = new CollisionRect(this.x,this.y,POTION_WIDTH, POTION_HEIGHT);
        if(type == 1){
            texture = new Texture("health.png");
        }else if(type != 1){
            texture = new Texture("potion.png");}
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, this.x, this.y, POTION_WIDTH, POTION_HEIGHT);
    }


    public void update(float delta) {
    }
    public CollisionRect getCollisionRect(){
        return rect;
    }

    public void update() {
    }
}

