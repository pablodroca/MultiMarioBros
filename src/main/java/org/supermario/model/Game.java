package org.supermario.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import org.supermario.model.elements.GameElement;
import org.supermario.model.elements.Player;

public class Game extends Observable implements Runnable {
	private List<GameElement> elements = new ArrayList<GameElement>();
	private Player player;
		
	public void addElement(GameElement element) {
		this.elements.add(element);
		element.addToGame(this);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void run() {
		boolean runGame = true;
		while (runGame) {
			long loopStart = System.currentTimeMillis();
			this.moveElements();
			this.checkCollisions();
			this.setChanged();
			this.notifyObservers();
			
			long loopElapsed = System.currentTimeMillis() - loopStart;
			long delay = loopElapsed < GameConstants.GAME_LOOP_MS ? 
					GameConstants.GAME_LOOP_MS - loopElapsed : 1;
			try {
				TimeUnit.MILLISECONDS.sleep(delay);
			} catch (InterruptedException e) {
				runGame = false;
			}
		}
	}

	private void checkCollisions() {
		for (GameElement leftObj : this.elements) {
			for (GameElement rightObj : this.elements) {
				if (leftObj != rightObj && leftObj.inCollisionWith(rightObj)) {
					leftObj.resolveCollisionWith(rightObj);
					rightObj.resolveCollisionWith(leftObj);
				}
			}
		}
	}

	private void moveElements() {
		for (GameElement gameObject : this.elements) {
			gameObject.move();
		}
	}

	public Player getPlayer() {
		return this.player;
	}

	public Iterable<GameElement> getElements() {
		return this.elements;
	}

	public void accept(GameElementVisitor visitor) {
		for (GameElement element : this.elements) {
			element.accept(visitor);			
		}
	}

}
