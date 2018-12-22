package csc221.alg.view.menu;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

//Javafx stage can have only one scene but multiple subscenes
//Menu subscenes
public class ALGSubscene extends SubScene {
    private final static String BACKGROUND_IMAGE = "resources/images/blue_panel.png";
    private boolean isHidden;

    public ALGSubscene(){
        this(new AnchorPane());
    }
    public ALGSubscene(Pane layout){
        super(layout,500,300);
        prefHeight(300);
        prefWidth(500);
        Image newImage = new Image(BACKGROUND_IMAGE,500,300, false,true);
        BackgroundImage image = new BackgroundImage(newImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
        Pane root = (Pane) this.getRoot(); //Getting the Layout we set
        root.setBackground(new Background(image));//Adding Background to Layout
        isHidden = true;
        setLayoutX(1024);
        setLayoutY(130);
        if(layout instanceof StackPane){
            root = (StackPane) this.getRoot();
            ((StackPane) root).setAlignment(Pos.CENTER);
        }



    }


    public void moveSubScene(){//Subscene Transition aniamtion using TranslateTransition
        TranslateTransition transition = new TranslateTransition(); //Create TranslateTransition object
        //Settings of Transition
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);//This subscene should be transitioned
        if(isHidden){
            transition.setToX(-676);//X axis movement
            isHidden=!isHidden;
        }else{//Hide subscene
            transition.setToX(0);//X axis movement
            isHidden=!isHidden;
        }
        transition.play();//Start the transition
    }

    public void moveSubSceneLeft(int distance){
        TranslateTransition transition = new TranslateTransition();
        //Settings of Transition
        transition.setDuration(Duration.seconds(1));
        transition.setNode(this);

        transition.setToX(-1*distance);
        transition.play();
    }

    public Pane getPane(){
        return (Pane) this.getRoot();
    }


}
