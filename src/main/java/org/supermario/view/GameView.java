package org.supermario.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.supermario.model.elements.Block;
import org.supermario.model.elements.GameElement;
import org.supermario.model.elements.Koopa;
import org.supermario.model.elements.Player;
import org.supermario.view.elementViews.BlockView;
import org.supermario.view.elementViews.GameElementView;
import org.supermario.view.elementViews.KoopaView;
import org.supermario.view.elementViews.PlayerView;



public class GameView extends Canvas implements Observer, ChangeListener<Number> {
    private KeyboardInputListener inputListener;
    private Map<KeyCode, Key> keysMap = new TreeMap<KeyCode, Key>();
	private List<GameElementView> innerViews;
	
	public GameView() {
		keysMap = new TreeMap<KeyCode, Key>();
		keysMap.put(KeyCode.LEFT, Key.LEFT);
		keysMap.put(KeyCode.RIGHT, Key.RIGHT);
		keysMap.put(KeyCode.UP, Key.UP);
		keysMap.put(KeyCode.DOWN, Key.DOWN);
		
		this.innerViews = new ArrayList<GameElementView>();
		widthProperty().addListener(this);
		heightProperty().addListener(this);
	}

	@Override
	public void changed(ObservableValue<? extends Number> arg0,
			Number arg1, Number arg2) {
		this.scaleInnerViews();
		this.draw();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.draw();
	}

	private void draw() {
		Platform.runLater(new Runnable(){
			public void run() {
				GraphicsContext gc = getGraphicsContext2D();
				gc.clearRect(0, 0, getWidth(), getHeight());
			}	
		});
		
		this.drawInnerViews();
		
	}

	private void scaleInnerViews() {
		for (GameElementView innerView : innerViews) {
			innerView.scaleTo(getWidth(), getHeight());
		}
	}

	private void drawInnerViews() {
		for (GameElementView innerView : innerViews) {
			innerView.draw();
		}
	}

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public double prefWidth(double height) {
		return getWidth();
	}

	@Override
	public double prefHeight(double width) {
		return getHeight();
	}
	
	public void bindTo(KeyboardInputListener listener) {
		this.inputListener = listener;
	}

	public void registerView(GameElement element, GameElementView view) {
		element.addObserver(view);
		this.innerViews.add(view);
	}

	public void show(Stage primaryStage) {
	     VBox root = new VBox();
	     root.getChildren().add(this);
	     Scene scene = new Scene(root, 300, 200);
	     this.registerEvents(scene, root);
	     primaryStage.setTitle("Super Mario");
	     primaryStage.setScene(scene);
	     primaryStage.show();
	}
	
	private void registerEvents(Scene scene, Region root) {
		widthProperty().bind(root.widthProperty());
		heightProperty().bind(root.heightProperty());
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
		        @Override
		        public void handle(KeyEvent t) {
		        	Key key = mapKey(t.getCode());
		        	inputListener.keyReleased(key);
		        }

		    });
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        @Override
		        public void handle(KeyEvent t) {
		        	Key key = mapKey(t.getCode());
		        	inputListener.keyPressed(key);
		        }
		    });
	}
	private Key mapKey(KeyCode code) {
		if (keysMap.containsKey(code))
			return keysMap.get(code);
		else
			return null;
	}

}

