package org.supermario.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.supermario.model.Block;
import org.supermario.model.Game;
import org.supermario.model.GameElement;
import org.supermario.model.Player;
import org.supermario.view.GameView;
import org.supermario.view.Key;
import org.supermario.view.KeyboardInputListener;

public class GameController implements KeyboardInputListener {
	private Game game;
	private Player mario;
	private GameView view;
	private ExecutorService executor;
	
	public GameController(Game game, GameView view){
		this.game = game;
		this.view = view;
		this.mario = game.getPlayer();
		
		for (GameElement element : game.getElements()) {
			if (element != mario) //TODO improve it
				this.view.register((Block)element);			
		}
		this.view.register(this.mario);
		this.view.bindTo(this);
	}

	@Override
	public void keyPressed(Key key) {
		if (key == Key.LEFT)
			mario.goLeft();
		else if (key == Key.RIGHT)
			mario.goRight();
		else if (key == Key.UP)
			mario.goUp();
		else if (key == Key.DOWN)
			mario.goDown();
	}

	@Override
	public void keyReleased(Key key) {
		if (key == Key.LEFT)
			mario.stop();
		else if (key == Key.RIGHT)
			mario.stop();
		else if (key == Key.UP)
			mario.stop();
		else if (key == Key.DOWN)
			mario.stop();
	}
	
	public void start() {
		this.executor = Executors.newSingleThreadExecutor();
	    this.executor.submit(game);
	}

	public void stop() {
		if (this.executor != null)
			this.executor.shutdownNow();
	}
}
