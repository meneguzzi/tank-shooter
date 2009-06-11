package org.meneguzzi.tank;

import org.meneguzzi.tank.engine.PeleusTankEngine;
import org.meneguzzi.tank.engine.TankEngine;

import com.nerdcorpgames.gamelib.engines.GraphicEngine.VideoMode;
import com.nerdcorpgames.gamelib.engines.simple.SimpleGameEngine;
import com.nerdcorpgames.gamelib.engines.simple.SimpleGameLoader;

public class TankGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleGameEngine gameEngine = new SimpleGameEngine();
		gameEngine.initialize(new SimpleGameLoader());
		
		VideoMode vm = new VideoMode();
		vm.width = 800;
		vm.height = 600;
		
		gameEngine.graphicEngine.setVideoMode(false, vm);
		
//		TankEngine tankEngine = new TankEngine();
		PeleusTankEngine tankEngine = new PeleusTankEngine();
		
		tankEngine.startGame();
	}

}
