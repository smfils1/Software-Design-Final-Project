package csc221.alg.view.game;

import csc221.alg.controller.GameEventHandler;
import csc221.alg.model.Backpack;
import csc221.alg.view.menu.ALGButton;
import javafx.geometry.Insets;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class SideContent extends SubScene {
    private static final int WIDTH = 225;
    private static final int HEIGHT = 600;
    private Text health;
    private Text time;
    private Text rescueTime;
    private Text backpack;
    private static final int SC_BUTTONS_START_X = 100;
    private static final int SC_BUTTONS_START_Y = 300;
    public List<ALGButton> scButtons;
    private VBox root;
    private GameEventHandler gameHandler;

    public SideContent(){
        super(new VBox(),WIDTH,HEIGHT);
        prefHeight(WIDTH);
        prefWidth(HEIGHT);
        this.root = (VBox) this.getRoot();
        this. scButtons = new ArrayList<>();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,CornerRadii.EMPTY,Insets.EMPTY)));
        root.setPadding(new Insets(10,10,10,10));
        root.setSpacing(10);
        time = new Text("Countdown: 0 s");
        rescueTime = new Text("Rescue Time: ");
        health = new Text("Health: 100 hp");
        backpack = new Text("Inventory: ");

        health.setFont(Font.font("Verdena",23));
        time.setFont(Font.font("Verdena",23));
        rescueTime.setFont(Font.font("Verdena",23));
        backpack.setFont(Font.font("Verdena",23));
       root.getChildren().addAll(time,rescueTime, health, backpack);
        createButtons();
    }


    public void setGameHandler(GameEventHandler handler) {
        this.gameHandler = handler;
    }

    private void createButtons(){
        createPauseButton();
        createResumeButton();
    }
   public void setBackpack(Backpack bp) {
        backpack.setText("Inventory: \n" +
                              "Wood: " + bp.getWoodAmount() +
                          "\n Berry: " + bp.getBerryAmount() +
                           "\nSteak: " + bp.getSteakAmount() +
                           "\nStone: " + bp.getstoneAmount() +
                           "\nSpear: " + bp.getSpearAmount() +
                           "\nTorch: " + bp.getFlashlightAmount() +
                      "\nSpace Left: " + bp.getSpace());
    }

    private void createPauseButton() {
        ALGButton pauseButton = new ALGButton("PAUSE");
        root.getChildren().add(pauseButton);
        pauseButton.setOnAction(event -> gameHandler.pauseEvent());
    }

    private void createResumeButton() {
        ALGButton resumeButton = new ALGButton("RESUME");
        root.getChildren().add(resumeButton);
        resumeButton.setOnAction(event -> gameHandler.resumeEvent());
    }

    public void setRescueTime(int seconds) {
        rescueTime.setText(rescueTime.getText() + seconds + " s");
    }
    public void setHealth(double hp) {
        health.setText("Health: " + hp + " hp");
    }
    public void setTime(int seconds) {
        time.setText("Time: " + seconds + " s");
    }

    public VBox getPane(){
        return (VBox) this.getRoot();
    }



}
