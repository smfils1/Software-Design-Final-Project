package csc221.alg.view.menu;

//import csc.youtube.MenuController.MenuEventHandler;
import csc221.alg.controller.MenuEventHandler;
import javafx.scene.control.Button;

import javafx.scene.text.Font;

//Custom Button
public class ALGButton extends Button {
    MenuEventHandler handler;

    //Note: Like html background images repeat by default
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; " +
            " -fx-background-repeat: no-repeat; " +
            "-fx-background-image: url('resources/images/blue_button_pressed.png')";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; " +
            " -fx-background-repeat: no-repeat;" +
            "-fx-background-image: url('resources/images/blue_button.png')";

    public void setHandler(MenuEventHandler handler) {
        this.handler = handler;
    }

    public ALGButton(String text){//Initialize button w/ Style
        setText(text);
        setButtonFont();
        //Default style
        setPrefWidth(195);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont(){

        setFont(Font.font("Verdena",23));
    }

    private void initializeButtonListeners(){
        setOnMousePressed(event -> {
            if(handler!=null) {
                handler.buttonPressEvent(event,this,BUTTON_PRESSED_STYLE);
            }
        });
        setOnMouseReleased(event -> {
            if(handler!=null) {
                handler.buttonReleaseEvent(event,this,BUTTON_FREE_STYLE);
            }
        });
        setOnMouseEntered(event -> {
            if(handler!=null) {
                handler.buttonMouseEnterEvent(event,this);

            }
        });
        setOnMouseExited(event -> {
            if(handler!=null) {
                handler.buttonMouseExitEvent(event,this);
            }
        });
    }
}

