package csc221.alg.view.game;


import csc221.alg.view.menu.Character;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


//TODO add set color so we can night vision by making tiles outside n-block radius black
//TODO: we might need a default color field so we can turn color back to normal
public class Tile extends StackPane {

    private int x;
    private int y;
    private int tileSize;
    private Rectangle border;
    private Text text;
    private Paint color;
    private ImageView entity;
    String character;


    public Tile(int x, int y, int size, String character, Paint color) {//Tile got region = terrain, posiition, entity
        this.x = x;
        this.y = y;
        this.tileSize = size;
        this.color =color;
        this.character = character;
        this.entity = new ImageView();
        this.entity.setImage(new WritableImage(size,size));

        if(!(character.isEmpty())) {
            entity.setImage(new Image(character));
        }

        entity.setFitWidth(15);
        entity.setFitHeight(15);

        border = new Rectangle( tileSize, tileSize );
        border.setFill(color);
        border.setStroke(Color.TRANSPARENT);

        getChildren().addAll(border, entity);
        setTranslateX(x * tileSize);
        setTranslateY(y * tileSize);
    }

    public ImageView getEntity() {
        return entity;
    }

    public String getCharacter() {
        return character;
    }

    public void setColor(Paint color) {
        border.setFill(color);
    }
    public void resetColor() {
        border.setFill(color);
    }
//    public void setText(char text) {
//        this.text.setText(text+"");
//    }
//
//    public char getText() {
//        return text.getText().charAt(0);
//    }

    public void setImage(String imgUrl) {
        if(imgUrl != null)
            entity.setImage(new Image(imgUrl));
        else
            entity.setImage(null);
    }
}


