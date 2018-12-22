package csc221.alg.model;

//TODO: Add images
public enum Lifeform {
    RABBIT('r',"/resources/images/rabbit.png"),
    WOLF('W',"/resources/images/wolf.png"),
    LION('L',"/resources/images/lion.png"),
    DEER('m',"/resources/images/deer.png"),
    AGENT('A',"/resources/images/character1.gif"),
    BUSH('b',"/resources/images/bush.png"),
    TREE('T',"/resources/images/tree.png"),
    CARNIVORE('C'),HERBIVORE('H');

    String imgUrl;
    char lifeform;

    Lifeform(char lifeform){
        this.lifeform = lifeform;
    }

    Lifeform(char lifeform, String imgUrl){
        this.lifeform = lifeform;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public char getValue() {
        return lifeform;
    }


}
