package pl.kgdev.bomberman.Kolizje;

import com.badlogic.gdx.math.Rectangle;
import pl.kgdev.bomberman.Bomberman;

public class CollisionRect extends Rectangle{
    int x,y, width, height, left, right, top, bottom;


    public CollisionRect(float x, float y, int width, int height){
        this.x = (int)x;
        this.y = (int)y;
        this.width = width;
        this.height = height;
        this.left = (int)x;
        this.top = (int)y;
        this.right = Bomberman.WIDTH-(this.x+this.width);
        this.bottom = Bomberman.HEIGHT-(this.y+this.height);
    }
    public void move(float x, float y){
        this.x = (int)x;
        this.y = (int)y;
        this.left = (int)x;
        this.top = (int)y;
        this.right = Bomberman.WIDTH-(this.x+this.width);
        this.bottom = Bomberman.HEIGHT-(this.y+this.height);

    }

    public boolean collidesWith (CollisionRect rect) {
    boolean itcolides = false;
        if(this.left+this.width>=rect.left && this.right<=rect.right && (this.top+this.width==rect.top || this.bottom==rect.bottom)) itcolides=true;
       /* if((x>=rect.x && x<=rect.x+rect.width)||(x+width>=rect.x && x+width<=rect.x+rect.width)){
            if((y>=rect.y && y<=rect.y+rect.height)||(y+height>=rect.y && y+height<=rect.y+rect.height))
                itcolides = true;
        }*/
        return itcolides;
    }
}
