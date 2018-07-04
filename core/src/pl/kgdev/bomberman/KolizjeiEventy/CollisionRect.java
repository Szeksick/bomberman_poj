package pl.kgdev.bomberman.KolizjeiEventy;

import com.badlogic.gdx.math.Rectangle;

public class CollisionRect extends Rectangle{
    public float x,y;
    public int width, height;



    public CollisionRect(float x, float y, int width, int height){
        this.x = x+2;
        this.y = y+2;
        this.width = width-5;
        this.height = height-5;

    }
    public void move(float x, float y){
        this.x = x;
        this.y = y;


    }

    public boolean collidesWith (CollisionRect rect) {

        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }

}
