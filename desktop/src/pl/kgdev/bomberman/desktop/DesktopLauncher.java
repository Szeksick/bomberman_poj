package pl.kgdev.bomberman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.kgdev.bomberman.Bomberman;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Bomberman.WIDTH;
		config.height = Bomberman.HEIGHT;
		config.resizable = false;
		config.title = "BOMBERMAN by Konrad Gugała, Karol Mikołajczyk, Paweł Langier, Tucznik";
		new LwjglApplication(new Bomberman(), config);
	}
}
