package com.ny.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ny.game.Flappy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=(int)Flappy.WIDTH;
		config.height=(int)Flappy.HEIGHT;
		config.title=Flappy.TITLE;
		new LwjglApplication(new Flappy(), config);
	}
}
