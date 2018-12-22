package csc221.alg.view.game;
//TODO: Game UI
import csc221.alg.controller.GameEventHandler;
import csc221.alg.view.menu.ALGSubscene;
import csc221.alg.view.menu.Character;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;


//TODO: Implement pause and play button


public class GameView {
    private GameEventHandler gameHandler;
    private static final int WIDTH = 825;
    private static final int HEIGHT = 600;
    private Tile tileGrid[][];
    private SideContent sideContent;



    private ALGSubscene gameOverSubscene;
    private ALGSubscene winnerSubscene;
    public AnchorPane getGamePane() {
        return gamePane;
    }
    public SideContent getSideContent() {
        return sideContent;
    }
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private ImageView character;
    Queue<Tile> tileWaitingUpdate = new LinkedList<>();



    public GameView(){
        initializeStage();
        createKeyListeners();
    }

    public void setGameHandler(GameEventHandler handler) {
        this.gameHandler = handler;
    }


    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,WIDTH,HEIGHT);
        gameStage = new Stage();
        //gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        createSideContent();
        createSubscenes();
    }


    public void createSideContent() {
        sideContent = new SideContent();
        sideContent.setLayoutX(600);
        gamePane.getChildren().add(sideContent);
    }

    public ALGSubscene getGameOverSubscene() {
        return gameOverSubscene;
    }

    public ALGSubscene getWinnerSubscene() {
        return winnerSubscene;
    }

    private void createSubscenes() {
        createGameOverSubscene();
        createWinnerSubscene();
    }

    private void createGameOverSubscene() {
        gameOverSubscene = new ALGSubscene(new StackPane());
        gamePane.getChildren().add(gameOverSubscene);
        Label gameOverText = new Label("GAME OVER!!!");
        gameOverSubscene.getPane().getChildren().add(gameOverText);
        gameOverText.setFont(Font.font("Verdena",55));
    }
    private void createWinnerSubscene() {
        winnerSubscene = new ALGSubscene(new StackPane());
        Label winnerText = new Label("YOU SURVIVED!");
        gamePane.getChildren().add(winnerSubscene);
        winnerSubscene.getPane().getChildren().add(winnerText);
        winnerText.setFont(Font.font("Verdena",55));

    }


    public Queue<Tile> getTileWaitingQueue() {
        return tileWaitingUpdate;
    }

    public void setTileGrid(Tile[][] tileGrid) {
        this.tileGrid = tileGrid;
    }

    public Tile[][] getTileGrid() {
        return tileGrid;
    }

    public Paint strColorToPaint(String colorStr) {
        String color = colorStr.toUpperCase();
        if(color.equals("TAN")){
            return Color.TAN;
        } else if(color.equals("BLUE")){

            return Color.LIGHTBLUE;
        }else{
            return Color.LIGHTGREEN;
        }
    }

    private void createKeyListeners() {
        gameStage.setOnCloseRequest(event -> gameHandler.exitEvent());
        gameScene.setOnKeyPressed(event -> {
            if(gameHandler!= null) {
                if (event.getCode() == KeyCode.LEFT) {
                    gameHandler.moveLeftEvent();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    gameHandler.moveRightEvent();
                } else if (event.getCode() == KeyCode.UP) {
                    gameHandler.moveUpEvent();
                } else if (event.getCode() == KeyCode.DOWN) {
                    gameHandler.moveDownEvent();
                } else if (event.getCode() == KeyCode.A) {
                    gameHandler.attackEvent();
                } else if (event.getCode() == KeyCode.B) {
                    gameHandler.buildBaseEvent();
                } else if (event.getCode() == KeyCode.C) {
                    gameHandler.collectResourceEvent();
                } else if (event.getCode() == KeyCode.E) {
                    gameHandler.eatEvent();
                } else if (event.getCode() == KeyCode.H) {
                    gameHandler.hideEvent();
                } else if (event.getCode() == KeyCode.R) {
                    gameHandler.restEvent();
                }
            }
        });
    }

    //TODO: Use character image LATER
    private void createCharacter(Character choosenCHARACTER){
        character = new ImageView(choosenCHARACTER.getUrl());

    }

    public void createNewGame(Character choosenCHARACTER){//Game Window
        createCharacter(choosenCHARACTER);
        gameStage.show();
    }

}
