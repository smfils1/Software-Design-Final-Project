package csc221.alg.model;

//TODO: Add images
public enum EntityObject {
    ROCK('R',"/resources/images/rock.png"),
    BASE('B',"/resources/images/base.png");

    char value;
    String imgUrl;

    EntityObject(char value, String imgUrl){
        this.value = value;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public char getValue() {
        return value;
    }
}

