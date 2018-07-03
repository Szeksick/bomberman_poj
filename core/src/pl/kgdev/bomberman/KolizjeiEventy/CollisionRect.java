package pl.kgdev.bomberman.KolizjeiEventy;

import com.badlogic.gdx.math.Rectangle;
import pl.kgdev.bomberman.Bomberman;

public class CollisionRect extends Rectangle{
    public int x,y, width, height, left, right, top, bottom;



    public CollisionRect(float x, float y, int width, int height){
        this.x = Math.round(x);
        this.y = Math.round(y);
        this.width = width;
        this.height = height;
        this.left = Math.round(x);
        this.bottom  = Math.round(y);
        this.right = Bomberman.WIDTH-(this.left+this.width);
        this.top = Bomberman.HEIGHT-(this.bottom+this.height);
    }
    public void move(float x, float y){
        this.x = Math.round(x);
        this.y = Math.round(y);
        this.left = Math.round(x);
        this.bottom  = Math.round(y);
        this.right = Bomberman.WIDTH-(this.left+this.width);
        this.top = Bomberman.HEIGHT-(this.bottom+this.height);

    }

    public boolean collidesWith (CollisionRect rect) {

        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }

}
