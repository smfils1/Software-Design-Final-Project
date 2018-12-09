package csc221.alg.controller;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import csc221.alg.view.game.GameView;
import csc221.alg.view.menu.CharacterPicker;
import csc221.alg.view.menu.Menu;

public class MenuController implements MenuEventHandler {
    public Menu view;
    public GameView gameView;

    public MenuController(Menu view, GameView gameView) {
        this.view = view;
        this.gameView = gameView;
        buildView();
    }

    private void buildView(){
        view.setMenuHandler(this);
        view.menuButtons.forEach(b -> b.setHandler(this));
        view.getMainStage().show();
    }

    @Override
    public void hideEvent() {
        view.getMainStage().hide();
    }

    @Override
    public void helpEvent() {
        view.showSubScene(view.getHelpSubscene());
    }
    @Override
    public void characterChooserEvent() {
        view.showSubScene(view.getCharacterChooserScene());
    }

    @Override
    public void characterSelectEvent(CharacterPicker characterToPick) {
        for(CharacterPicker character : view.characterList){
            character.setIsCircleChoosen(false);
        }
        characterToPick.setIsCircleChoosen(true);
        view.choosenCHARACTER = characterToPick.getCHARACTER();
    }

    @Override
    public void exitEvent() {
        view.getMainStage().close();
    }

    @Override
    public void creditsEvent() {
        view.showSubScene(view.getCreditsSubscene());

    }

    @Override
    public void buttonPressEvent(MouseEvent event, Button b, String style) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            b.setStyle(style);
            b.setPrefHeight(49);
            b.setLayoutY(b.getLayoutY() + 4);
        }
    }

    @Override
    public void buttonReleaseEvent(MouseEvent event, Button b, String style) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            b.setStyle(style);
            b.setPrefHeight(49);
            b.setLayoutY(b.getLayoutY() - 4);
        }
    }

    @Override
    public void buttonMouseEnterEvent(MouseEvent event, Button b) {
        b.setEffect(new DropShadow());
    }

    @Override
    public void buttonMouseExitEvent(MouseEvent event, Button b) {
        b. setEffect(null);
    }

}