package csc221.alg.controller;

import csc221.alg.view.game.GameView;
import csc221.alg.view.menu.Menu;


public class GameController implements GameEventHandler {
    public GameView gameView;
    public Menu menuView;

    public GameController(Menu menu, GameView gameView) {
        this.gameView = gameView;
        this.menuView = menu;
        gameView.setGameHandler(this);
        menuView.setGameHandler(this);
    }

    @Override
    public void startGameEvent() {
        gameView.createNewGame(menuView.choosenCHARACTER);

        //TODO: Remove code below after implement the GUI version
        //CODE BELOW IS TO ENABLE US TO RUN THE CONSOLE VIEW CODE WHILE RUNNING THE GUI
        Thread thread = new Thread(() -> {gameView.play();});
        thread.start();
    }
 }

