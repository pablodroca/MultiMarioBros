package org.supermario.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.supermario.model.Game;
import org.supermario.model.GameElementVisitor;
import org.supermario.model.elements.Player;
import org.supermario.view.GameView;

public class GameController {
	private Game game;
	private Player player;
	private GameView view;
	private ExecutorService executor;
	private KeyboardController keyboardController;
	
	public GameController(Game game, GameView view){
		this.game = game;
		this.view = view;
		this.player = this.game.getPlayer();
		this.keyboardController = new KeyboardController(this.player);
		
		GameElementVisitor viewsBuilder = new ViewsBuilderVisitor(this.view);
		this.game.accept(viewsBuilder);
		this.game.addObserver(this.view);
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
