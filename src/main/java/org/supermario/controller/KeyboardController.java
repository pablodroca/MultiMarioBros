package org.supermario.controller;

import org.supermario.model.Player;
import org.supermario.view.Key;
import org.supermario.view.KeyboardInputListener;

public class KeyboardController implements KeyboardInputListener {
	private boolean upPressed;
	private boolean leftPressed;
	private boolean rightPressed;
	
	private Player player;
	
	public KeyboardController(Player player) {
		this.player = player;
	}
		
	@Override
	public void keyPressed(Key key) {
		if (key == Key.LEFT) {
			if (! this.leftPressed) {
				this.leftPressed = true;
				player.goLeft();
			}
		}
		else if (key == Key.RIGHT) {
			if (! this.rightPressed) {
				this.rightPressed = true;
				player.goRight();
			}
		}
		else if (key == Key.UP) {
			if (! this.upPressed) {
				this.upPressed = true;
				player.jump();
			}
		}
	}

	@Override
	public void keyReleased(Key key) {
		if (key == Key.LEFT) {
			if (this.leftPressed) {
				this.leftPressed = false;
				player.stopLeft();
			}
		}
		else if (key == Key.RIGHT) {
			if (this.rightPressed) {
				this.rightPressed = false;
				player.stopRight();
			}
		}
		else if (key == Key.UP) {
			if (this.upPressed) {
				this.upPressed = false;
			}
		}

	}

}
