package pl.kgdev.bomberman.elementy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import pl.kgdev.bomberman.KolizjeiEventy.CollisionRect;

public class Bush{
    private static Texture texture;
    public float x,y;
    public int width, height;
    public static final int WALL_WIDTH = 50;
    public static final int WALL_HEIGHT = 50;
    CollisionRect rect;


    public boolean breakable = false;
    public boolean remove = false;


    public Bush(float y, float x){
        this.x = x;
        this.y = y;
        this.rect = new CollisionRect(this.x,this.y,WALL_WIDTH, WALL_HEIGHT);
        if(texture == null){
            texture = new Texture("bush.png");
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, this.x, this.y, WALL_WIDTH, WALL_HEIGHT);
    }

    private void explode() {

    }

    public void update(float delta) {
    }
    public CollisionRect getCollisionRect(){
        return rect;
    }

    public void update() {
    }
}
