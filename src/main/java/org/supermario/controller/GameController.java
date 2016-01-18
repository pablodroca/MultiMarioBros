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

public class GameController {
	private Game game;
	private Player player;
	private GameView view;
	private ExecutorService executor;
	private KeyboardController keyboardController;
	
	public GameController(Game game, GameView view){
		this.game = game;
		this.view = view;
		this.player = game.getPlayer();
		this.keyboardController = new KeyboardController(this.player);
		
		for (GameElement element : game.getElements()) {
			if (element != player) //TODO improve it
				this.view.register((Block)element);			
		}
		this.view.register(this.player);
		this.view.bindTo(this.keyboardController);
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
