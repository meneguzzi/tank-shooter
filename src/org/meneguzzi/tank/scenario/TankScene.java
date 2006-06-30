package org.meneguzzi.tank.scenario;

import org.nerdcorpgames.platform.engine.PlatformEngine;
import org.nerdcorpgames.platform.engine.PlatformScene;

import com.nerdcorpgames.gamelib.fsm.FiniteStateMachine;

public class TankScene extends PlatformScene implements FiniteStateMachine {

	public TankScene(PlatformEngine eng) {
		super(eng);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void executeChildScene() {
		// TODO Auto-generated method stub

	}

	public int getCurrentState() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setCurrentState(int iNewState) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean initialize() {
		// TODO Auto-generated method stub
		return false;
	}

}
