package pl.kgdev.bomberman.elementy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Bomb {
    public static final int SPEED = 300;
    private static Texture texture;

    int x,y;
    private static int BOMB_WIDTH = 40;
    private static int BOMB_HEIGHT = 40;


    public boolean inmove = false;
    public boolean remove = false;
    public int state;//1 odliczanie, 2 ruch, 3 eksplozja

    public Bomb(float x, float y){
        this.x = 50*Math.round(x/50);
        this.y = 50*Math.round(y/50);
        if(texture == null){
            texture = new Texture("bomb.png");
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, this.x, this.y, BOMB_WIDTH, BOMB_HEIGHT);
    }

    private void explode() {


    }

    public void update(float delta) {
        if(this.state == 3) {
            explode();
        }
    }
}
