package org.supermario;
import java.util.concurrent.ExecutorService;

import org.supermario.controller.GameController;
import org.supermario.model.Block;
import org.supermario.model.Game;
import org.supermario.model.GameConstants;
import org.supermario.model.GameElement;
import org.supermario.model.Player;
import org.supermario.view.GameView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	 public static void main(String[] args) {
	     launch(args);
	 }

	 @Override
	 public void start(Stage primaryStage) {
		 Game game = new Game();
		 GameElement mario = new Player(100,100);
		 game.addElement(mario);
		 
		 game.addElement(new Block(100 - GameConstants.BLOCK_SIDE_SIZE, 100));
		 for (int i = 0; i < 10; ++i) {
			Block block = new Block(100 + GameConstants.BLOCK_SIDE_SIZE * i ,100 + GameConstants.MARIO_HEIGHT);
//			game.addElement(block);
		 }
		 for (int i = 0; i < 10; ++i) {
			Block block = new Block(100 + GameConstants.BLOCK_SIDE_SIZE * i ,100 + GameConstants.MARIO_HEIGHT/2);
	//		game.addElement(block);
		 }
		 for (int i = 0; i < 10; ++i) {
			Block block = new Block(100 + GameConstants.BLOCK_SIDE_SIZE * i ,100 + 3*(GameConstants.BLOCK_SIDE_SIZE/2));
			game.addElement(block);
		 }

		 
		 game.addElement(new Block(100 + 10 * GameConstants.BLOCK_SIDE_SIZE, 100));

		 GameView view = new GameView();
	     final GameController controller = new GameController(game, view);
	     controller.start();
	     view.show(primaryStage);
	     primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  //after the view is closed, we must dispose the game controllers
	        	  controller.stop();
	          }
	     });
	 }
}
