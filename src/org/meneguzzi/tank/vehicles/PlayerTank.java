package org.meneguzzi.tank.vehicles;

import java.awt.Dimension;

import org.nerdcorpgames.platform.elements.PlatformEvent;
import org.nerdcorpgames.platform.elements.QuadIsometricMovingObject;

import com.nerdcorpgames.gamelib.elements.SpriteLayer;
import com.nerdcorpgames.gamelib.engines.GameEngine;
import com.nerdcorpgames.gamelib.engines.simple.input.KeyHandler;
import com.nerdcorpgames.gamelib.engines.simple.input.KeyState;
import com.nerdcorpgames.gamelib.fsm.AbstractState;
import com.nerdcorpgames.gamelib.fsm.State;

public class PlayerTank extends QuadIsometricMovingObject {
	public final static int STOPPED = 0;
	public final static int MOVING = 1;
	public final static int DIEING = 2;
	public final static int SHOOTING = 3;
	
	public final static String STOPPED_ANI_UP     = "stopped_up";
	public final static String STOPPED_ANI_DOWN   = "stopped_down";
	public final static String STOPPED_ANI_LEFT   = "stopped_left";
	public final static String STOPPED_ANI_RIGHT  = "stopped_right";
	
	public final static String MOVING_ANI_UP      = "moving_up";
	public final static String MOVING_ANI_DOWN    = "moving_down";
	public final static String MOVING_ANI_LEFT    = "moving_left";
	public final static String MOVING_ANI_RIGHT   = "moving_right";
	
	public final static String SHOOTING_ANI_UP    = "shooting_up";
	public final static String SHOOTING_ANI_DOWN  = "shooting_down";
	public final static String SHOOTING_ANI_LEFT  = "shooting_left";
	public final static String SHOOTING_ANI_RIGHT = "shooting_right";
	
	
	protected Direction direction = Direction.UP;
	
	public PlayerTank() {
		super();
		this.states = new State[4];
		this.states[STOPPED] = new StoppedState();
		this.states[MOVING] = new MovingState();
		this.states[DIEING] = new DieingState();
		this.states[SHOOTING] = new ShootingState();
		
	}

	public boolean initialize() {
		this.iCurrentState = STOPPED;
		return true;
	}
	
	@Override
	public void attachToLayer(SpriteLayer layer) {
		//TODO Review the method for loading a character, probably move this to the config file
		this.sAvatar = layer.createSprite("res/tankbrigade.png", new Dimension(32, 32));
		int animation[];
		
		//Creates stopped state animations
		animation = new int[] {14};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(STOPPED_ANI_UP, 1, false, animation));
		animation = new int[] {129};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(STOPPED_ANI_DOWN, 1, false, animation));
		animation = new int[] {63};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(STOPPED_ANI_LEFT, 1, false, animation));
		animation = new int[] {88};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(STOPPED_ANI_RIGHT, 1, false, animation));
		
		//Creates moving state animations
		animation = new int[] {15, 16, 17, 18, 19, 20, 21, 14};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(MOVING_ANI_UP, 5, true, animation));
		animation = new int[] {38, 39, 40, 41, 42, 43, 44, 129};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(MOVING_ANI_DOWN, 5, true, animation));
		animation = new int[] {62, 85, 108, 131, 154, 177, 200, 63};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(MOVING_ANI_LEFT, 5, true, animation));
		animation = new int[] {61, 84, 107, 130, 153, 176, 199, 88};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(MOVING_ANI_RIGHT, 5, true, animation));
		
		animation = new int[] {14, 37, 60};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(SHOOTING_ANI_UP, 5, false, animation));
		
		animation = new int[] {129, 106, 83};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(SHOOTING_ANI_DOWN, 5, false, animation));
		
		animation = new int[] {63, 64, 65};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(SHOOTING_ANI_LEFT, 5, false, animation));
		
		animation = new int[] {88, 87, 86};
		sAvatar.addAnimation(GameEngine.getInstance().gameLoader.loadAnimation(SHOOTING_ANI_RIGHT, 5, false, animation));
	}

	/*
	 *  (non-Javadoc)
	 * @see org.nerdcorpgames.platform.elements.PlatformObject#update()
	 */
	public void update() {
		super.update();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.nerdcorpgames.platform.elements.PlatformObject#handleEvent(org.nerdcorpgames.platform.elements.PlatformEvent)
	 */
	public void handleEvent(PlatformEvent event) {
		// TODO Auto-generated method stub

	}
	
	public final class MoveUpKeyHandler implements KeyHandler {
		public void handleKey(KeyState keyState) {
			if (keyState.isPressed()) {
				fCurrentSpeedY = -200.0f;
				fCurrentSpeedX = 0;
				if (direction != Direction.UP || getCurrentState() == STOPPED) {
					direction = Direction.UP;
					setCurrentState(MOVING);
				}

				// bWalking = true;
			}
		}
	}

	public final class MoveDownKeyHandler implements KeyHandler {
		public void handleKey(KeyState keyState) {
			if (keyState.isPressed()) {
				fCurrentSpeedY = 200.0f;
				fCurrentSpeedX = 0;
				if (direction != Direction.DOWN || getCurrentState() == STOPPED) {
					direction = Direction.DOWN;
					setCurrentState(MOVING);
				}

				// bWalking = true;
			}
		}
	}

	public final class MoveRightKeyHandler implements KeyHandler {
		public void handleKey(KeyState keyState) {
			if (keyState.isPressed()) {
				fCurrentSpeedX = 200.0f;
				fCurrentSpeedY = 0;

				if (direction != Direction.RIGHT
						|| getCurrentState() == STOPPED) {
					direction = Direction.RIGHT;
					setCurrentState(MOVING);
				}

				// bWalking = true;
			}
		}
	}

	public final class MoveLeftKeyHandler implements KeyHandler {
		public void handleKey(KeyState keyState) {
			if (keyState.isPressed()) {
				fCurrentSpeedX = -200.0f;
				fCurrentSpeedY = 0;

				if (direction != Direction.LEFT || getCurrentState() == STOPPED) {
					direction = Direction.LEFT;
					setCurrentState(MOVING);
				}

				// bWalking = true;
			}
		}
	}
	
	public final class ShootKeyHandler implements KeyHandler {
		public void handleKey(KeyState keyState) {
			if (keyState.goneDown()) {
				fCurrentSpeedX = 0;
				fCurrentSpeedX = 0;
				
				setCurrentState(SHOOTING);
			}
		}
	}
	
	private final class StoppedState extends AbstractState {
		@Override
		public void onState() {
			switch (direction) {
			case DOWN:
				sAvatar.setCurrentAnimation(STOPPED_ANI_DOWN);
				break;
			case LEFT:
				sAvatar.setCurrentAnimation(STOPPED_ANI_LEFT);
				break;
			case RIGHT:
				sAvatar.setCurrentAnimation(STOPPED_ANI_RIGHT);
				break;
			case UP:
				sAvatar.setCurrentAnimation(STOPPED_ANI_UP);
				break;
			default:
				break;
			}
		}
	}

	private final class MovingState extends AbstractState {
		@Override
		public void onEnter() {
			switch (direction) {
			case DOWN:
				sAvatar.setCurrentAnimation(MOVING_ANI_DOWN);
				break;
			case LEFT:
				sAvatar.setCurrentAnimation(MOVING_ANI_LEFT);
				break;

			case RIGHT:
				sAvatar.setCurrentAnimation(MOVING_ANI_RIGHT);
				break;

			case UP:
				sAvatar.setCurrentAnimation(MOVING_ANI_UP);
				break;

			default:
				break;
			}
		}

		public void onState() {
			if (fCurrentSpeedX == 0.0f && fCurrentSpeedY == 0.0f)
				setCurrentState(STOPPED);
			else {
				fCurrentSpeedX = 0.0f;
				fCurrentSpeedY = 0.0f;
			}
		}
	}

	private final class DieingState extends AbstractState {

	}

	private final class ShootingState extends AbstractState {
		private int stopFrame = 0;

		@Override
		public void onEnter() {
			stopFrame = 50;
			switch (direction) {
			case DOWN:
				sAvatar.setCurrentAnimation(SHOOTING_ANI_DOWN);
				break;
			case LEFT:
				sAvatar.setCurrentAnimation(SHOOTING_ANI_LEFT);
				break;

			case RIGHT:
				sAvatar.setCurrentAnimation(SHOOTING_ANI_RIGHT);
				break;

			case UP:
				sAvatar.setCurrentAnimation(SHOOTING_ANI_UP);
				break;

			default:
				break;
			}
		}

		@Override
		public void onState() {
			stopFrame--;
			if (stopFrame == 0) {
				setCurrentState(STOPPED);
			}
		}
	}

}
