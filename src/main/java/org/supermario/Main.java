package org.supermario;
import java.util.concurrent.ExecutorService;

import org.supermario.controller.GameController;
import org.supermario.model.Block;
import org.supermario.model.Game;
import org.supermario.model.GameConstants;
import org.supermario.model.GameElement;
import org.supermario.model.Koopa;
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
		 GameElement mario = new Player(OFFSET_X + GameConstants.BLOCK_SIDE_SIZE, OFFSET_Y - GameConstants.MARIO_HEIGHT/2);
		 game.addElement(mario);

		 for (int i = 0; i < 3; ++i) {
			game.addElement(new Block(OFFSET_X, OFFSET_Y - GameConstants.BLOCK_SIDE_SIZE /2 - i * GameConstants.BLOCK_SIDE_SIZE));
		 }
		 for (int i = 1; i < 20; ++i) {
			 game.addElement(new Block(OFFSET_X + GameConstants.BLOCK_SIDE_SIZE * i ,OFFSET_Y + GameConstants.BLOCK_SIDE_SIZE/2));
		 }
		 for (int i = 0; i < 3; ++i) {
			 game.addElement(new Block(OFFSET_X + 20 * GameConstants.BLOCK_SIDE_SIZE, OFFSET_Y - GameConstants.BLOCK_SIDE_SIZE /2 - i * GameConstants.BLOCK_SIDE_SIZE));
		 }
                 GameElement koopa = new Koopa(OFFSET_X + 19 * GameConstants.BLOCK_SIDE_SIZE, OFFSET_Y - GameConstants.KOOPA_HEIGHT/2);
                 game.addElement(koopa);
		 

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
