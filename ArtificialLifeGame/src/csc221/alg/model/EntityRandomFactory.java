package csc221.alg.model;

import java.util.Random;

//Utility Class the provides functions for the Games randomness
public class EntityRandomFactory {
    public static Carnivore randomCarnivore(int x, int y){
        Carnivore[] randomCarnivore = {new Lion(x,y),new Wolf(x,y)};
        return randomCarnivore[randomNumberBetween(0,1)];
    }
    public static Herbivore randomHerbivore(int x, int y){
        Herbivore[] randomHerbivore = {new Rabbit(x,y),new Deer(x,y)};
        return randomHerbivore[randomNumberBetween(0,1)];
    }
    public static Rock randomRock(int x, int y){
        char[] randomSize = {'S','L'};
        return new Rock(x,y,randomSize[randomNumberBetween(0,1)]);
    }
    public static Direction randomDirection(){
        Direction[] randomDirection = {Direction.LEFT,Direction.RIGHT,Direction.DOWN,Direction.UP};
        return randomDirection[randomNumberBetween(0,3)];
    }
    private static int randomNumberBetween(int min,int max){
        return new Random().nextInt((max - min) + 1) + min;
    }
}
