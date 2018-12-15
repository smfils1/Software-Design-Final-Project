package csc221.alg.view.game;


import javafx.scene.CacheHint;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


//TODO add set color so we can night vision by making tiles outside n-block radius black
//TODO: we might need a default color field so we can turn color back to normal
public class Tile extends StackPane {
    private int x;
    private int y;
    private int tileSize;
    private Rectangle border;
    private Text text;
    private Paint color;

    public Tile(int x, int y, int size, char character, Paint color) {//Tile got region = terrain, posiition, entity
        this.x = x;
        this.y = y;
        this.tileSize = size;
        this.color =color;
        text = new Text(character + "");
        border = new Rectangle( tileSize, tileSize );
        border.setFill(color);
        border.setStroke(Color.TRANSPARENT);
        text.setFont(Font.font(12));
        getChildren().addAll(border, text);
        setTranslateX(x * tileSize);
        setTranslateY(y * tileSize);
    }

    public void setColor(Paint color) {
        border.setFill(color);
    }
    public void resetColor() {
        border.setFill(color);
    }
    public void setText(char text) {
        this.text.setText(text+"");
    }
    public char getText() {
        return text.getText().charAt(0);
    }
}
