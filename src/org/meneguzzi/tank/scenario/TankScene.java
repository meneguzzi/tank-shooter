package org.meneguzzi.tank.scenario;

import java.awt.event.KeyEvent;

import org.meneguzzi.tank.vehicles.PlayerTank;
import org.nerdcorpgames.platform.elements.PlatformEvent;
import org.nerdcorpgames.platform.elements.PlatformObject;
import org.nerdcorpgames.platform.engine.PlatformEngine;
import org.nerdcorpgames.platform.engine.PlatformScene;

import com.nerdcorpgames.gamelib.engines.GameEngine;
import com.nerdcorpgames.gamelib.engines.simple.input.KeyHandler;
import com.nerdcorpgames.gamelib.engines.simple.input.KeyState;
import com.nerdcorpgames.gamelib.engines.simple.input.SimpleKeyMapper;
import com.nerdcorpgames.gamelib.fsm.AbstractState;
import com.nerdcorpgames.gamelib.fsm.FiniteStateMachine;
import com.nerdcorpgames.gamelib.fsm.State;

public class TankScene extends PlatformScene implements FiniteStateMachine {
	
	protected int iCurrentState = 0;
	protected State states[] = null;

	public TankScene(PlatformEngine eng) {
		super(eng);
	}
	
	public boolean initialize() {
		if(this.player == null)
			return false;
		super.focusObject(this.player);
		
		PlayerTank playerTank = (PlayerTank) player;
		
		SimpleKeyMapper keyMapper = new SimpleKeyMapper();
		keyMapper.addKeyHandler(KeyEvent.VK_LEFT, playerTank.new MoveLeftKeyHandler());
		keyMapper.addKeyHandler(KeyEvent.VK_RIGHT, playerTank.new MoveRightKeyHandler());
		keyMapper.addKeyHandler(KeyEvent.VK_UP, playerTank.new MoveUpKeyHandler());
		keyMapper.addKeyHandler(KeyEvent.VK_DOWN, playerTank.new MoveDownKeyHandler());
		keyMapper.addKeyHandler(KeyEvent.VK_SPACE, playerTank.new ShootKeyHandler());
		
		KeyHandler kHandler = new KeyHandler()
		{
			public void handleKey(KeyState keyState)
			{
				if (keyState.isPressed())
				{
					GameEngine.getInstance().halt();
				}
			}
		};
		
		keyMapper.addKeyHandler(KeyEvent.VK_ESCAPE, kHandler);
		this.setCurrentKeyMapping(keyMapper);
		
		this.states = new State[1];
		this.states[0] = new OnGameState();
		
		return true;
	}


	@Override
	protected void executeChildScene() {
		this.states[iCurrentState].onState();
	}

	public int getCurrentState() {
		return this.iCurrentState;
	}

	public int setCurrentState(int iNewState) {
		int iOldState = this.iCurrentState;
		this.iCurrentState = iNewState;
		this.states[iOldState].onExit();
		this.states[iCurrentState].onEnter();

		return iOldState;
	}
	
	protected class OnGameState extends AbstractState
	{
		public void onState()
		{
			// Update and execution of the player
			player.update();
			player.execute();

			//Character handling loop
			for (int i = 0; i < characterList.size(); i++)
			{
				characterList.get(i).update();
				characterList.get(i).execute();

				// If collision happens, handle it
				if (player.collides(characterList.get(i).getCollidingBounds()))
				{
					PlatformEvent event = new PlatformEvent(PlatformEvent.EVENT_COLLIDE);

					// Send the collision event to the player
					event.attributes.put("object", characterList.get(i));
					player.handleEvent(event);

					// Send the collision event to the collided object
					event = new PlatformEvent(PlatformEvent.EVENT_COLLIDE);
					event.attributes.put("object", player);
					characterList.get(i).handleEvent(event);
				}
			}

			// Do the scroll	
			focusObject(player);
		}
	}
}
