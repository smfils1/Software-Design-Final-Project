package csc221.alg.view.menu;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class CharacterPicker extends VBox {
    private ImageView circleImage;
    private ImageView characterImage;
    private String circleNotChoosen = "resources/images/grey_circle.png";
    private String circleIsChoosen = "resources/images/circle_choosen.png";
    private CHARACTER CHARACTER;
    private boolean isCircleChosen;

    public CharacterPicker(CHARACTER CHARACTER){
        circleImage = new ImageView(circleNotChoosen);
        characterImage = new ImageView(CHARACTER.getUrl());
        characterImage.setFitHeight(100);
        characterImage.setFitWidth(100);
        this.CHARACTER = CHARACTER;
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(characterImage);

    }

    public CHARACTER getCHARACTER(){
        return CHARACTER;
    }

    public boolean getIsCircleChoosen(){
        return isCircleChosen;
    }

    public void setIsCircleChoosen(boolean isCircleChoosen){
        this.isCircleChosen = isCircleChoosen;
        String imageToSet= this.isCircleChosen ? circleIsChoosen : circleNotChoosen;
        circleImage.setImage(new Image(imageToSet));
    }

}
