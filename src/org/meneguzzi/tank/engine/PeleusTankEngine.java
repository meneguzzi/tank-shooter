package org.meneguzzi.tank.engine;

import org.meneguzzi.tank.scenario.TankScene;
import org.nerdcorpgames.platform.engine.PlatformEngine;

import com.nerdcorpgames.gamelib.engines.GameEngine;

public class PeleusTankEngine extends PlatformEngine {

	public PeleusTankEngine() {
		this.loadGame("res/tank.xml");
		this.loadLevel("level1", new TankScene(this));
	}
	
	@Override
	public void startGame() {
		GameEngine engine = GameEngine.getInstance();

		while (!engine.isFinished())
			engine.loop();

		System.exit(0);
	}

	@Override
	public void gameover() {
		// TODO Auto-generated method stub

	}

	@Override
	public void levelEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void levelStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}
}
