package csc221.alg.application;

import csc221.alg.controller.GameController;
import csc221.alg.controller.MenuController;
import csc221.alg.view.game.GameView;
import csc221.alg.view.menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;


public class Game extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Menu menu = new Menu();
        GameView gameView = new GameView();
        MenuController menuController = new MenuController(menu,gameView);
        GameController gameController = new GameController(menu,gameView);

    }

    public static void main(String[] args) {
        launch(args);
    }
}

