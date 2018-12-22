package csc221.alg.model;

//TODO: Add images
public enum Terrain {
    GRASS(',',"green"),WATER('~',"blue"),DESSERT('.',"tan");

    private char terrain;
    private String color;

    Terrain(char terrain, String color){
        this.terrain = terrain;
        this.color = color;
    }

    public char getValue() {
        return terrain;
    }

    public String getColor() {
        return color;
    }
}
