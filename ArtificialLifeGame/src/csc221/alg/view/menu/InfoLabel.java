package csc221.alg.view.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

//Custom Label
public class InfoLabel extends Label {
    private final String BACKGROUND_IMAGE= "resources/images/blue_small_panel.png";

    public InfoLabel(String text){
        setPrefWidth(300);
        setPrefHeight(49);
        setText(text);
        setWrapText(true);
        setFont(Font.font("Verdena",23));
        setAlignment(Pos.CENTER);
        Image image= new Image(BACKGROUND_IMAGE, 300 ,49,false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
    }
}
