package pl.kgdev.bomberman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.kgdev.bomberman.Screens.GameScreen;
import pl.kgdev.bomberman.Screens.MenuScreen;


public class Bomberman extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public static final int WIDTH = 750;
	public static final int HEIGHT = 550;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
