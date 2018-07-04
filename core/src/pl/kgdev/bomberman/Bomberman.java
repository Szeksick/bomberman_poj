package pl.kgdev.bomberman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.kgdev.bomberman.Screens.GameScreen;
import pl.kgdev.bomberman.Screens.MenuScreen;


public class Bomberman extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	private Music muzyka;
	public static final int WIDTH = 750;
	public static final int HEIGHT = 550;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		muzyka = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
		muzyka.setLooping(true);
		muzyka.setVolume(0.6f);
		muzyka.play();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		muzyka.dispose();
	}
}
