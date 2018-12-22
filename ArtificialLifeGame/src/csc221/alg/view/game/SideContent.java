package csc221.alg.view.game;

import csc221.alg.model.Backpack;
import javafx.geometry.Insets;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class SideContent extends SubScene {
    private static final int WIDTH = 225;
    private static final int HEIGHT = 600;
    private Text health;
    private Text time;
    private Text rescueTime;
    private Text backpack;

    public SideContent(){
        super(new AnchorPane(),WIDTH,HEIGHT);
        prefHeight(WIDTH);
        prefWidth(HEIGHT);
        AnchorPane root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,CornerRadii.EMPTY,Insets.EMPTY)));
        setLayoutX(600);
        setLayoutY(0);
        time = new Text("Countdown: 0 s");
        rescueTime = new Text("Rescue Time: ");
        health = new Text("Health: 100 hp");
        backpack = new Text("Inventory: ");
        time.setLayoutX(10);
        time.setLayoutY(20);
        rescueTime.setLayoutX(10);
        rescueTime.setLayoutY(50);
        health.setLayoutX(10);
        health.setLayoutY(80);
        backpack.setLayoutX(10);
        backpack.setLayoutY(110);
        health.setFont(Font.font("Verdena",23));
        time.setFont(Font.font("Verdena",23));
        rescueTime.setFont(Font.font("Verdena",23));
        backpack.setFont(Font.font("Verdena",23));
        Line line = new Line(0, 0, 0, HEIGHT);
        Line line2 = new Line(0, HEIGHT/7, WIDTH, HEIGHT/7);
        root.getChildren().addAll(line,line2,time,rescueTime, health, backpack);
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
    public void setBackpack(Backpack bp) {
        backpack.setText("Inventory: \n" +
                              "Wood: " + bp.getWoodAmount() +
                           "\nBerry: " + bp.getBerryAmount() +
                           "\nSteak: " + bp.getSteakAmount() +
                           "\nStone: " + bp.getstoneAmount() +
                           "\nSpear: " + bp.getSpearAmount() +
                           "\nTorch: " + bp.getFlashlightAmount() +
                      "\nSpace Left: " + bp.getSpace());
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }


}
