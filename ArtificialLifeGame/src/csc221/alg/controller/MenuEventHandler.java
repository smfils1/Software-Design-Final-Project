package csc221.alg.controller;

import csc221.alg.view.menu.CharacterPicker;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public interface MenuEventHandler {
    void hideEvent();
    void helpEvent();
    void characterChooserEvent();
    void characterSelectEvent(CharacterPicker ship);
    void exitEvent();
    void creditsEvent();
    void buttonPressEvent(MouseEvent event, Button b, String style);
    void buttonReleaseEvent(MouseEvent event, Button b, String style);
    void buttonMouseEnterEvent(MouseEvent event, Button b);
    void buttonMouseExitEvent(MouseEvent event, Button b);
}
