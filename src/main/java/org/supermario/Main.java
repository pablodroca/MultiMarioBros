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
		 final int OFFSET_X = 300;
		 final int OFFSET_Y = 2000;
		 Game game = new Game();
		 GameElement mario = new Player(OFFSET_X + GameConstants.BLOCK_SIDE_SIZE, OFFSET_Y);
		 game.addElement(mario);
		 
		 for (int i = 0; i < 2; ++i) {
			game.addElement(new Block(OFFSET_X, OFFSET_Y - i * GameConstants.BLOCK_SIDE_SIZE * i));
		 }
		 for (int i = 0; i < 2; ++i) {
			 game.addElement(new Block(OFFSET_X + 20 * GameConstants.BLOCK_SIDE_SIZE, OFFSET_Y - i * GameConstants.BLOCK_SIDE_SIZE));
		 }
		 for (int i = 1; i < 20; ++i) {
			game.addElement(new Block(OFFSET_X + GameConstants.BLOCK_SIDE_SIZE * i ,OFFSET_Y + 3*(GameConstants.BLOCK_SIDE_SIZE/2)));
		 }

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
