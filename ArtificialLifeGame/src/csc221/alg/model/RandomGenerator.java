package csc221.alg.model;

import java.util.Random;

//Utility Class the provides functions for the Games randomness
 public class RandomGenerator {
     //TODO: should carnivore has random size?
    public static Carnivore randomCarnivore(int x, int y){
        Carnivore[] randomCarnivore = {new Lion(x,y),new Wolf(x,y)};
        return randomCarnivore[randomNumberBetween(0,1)];
    }
    public static Herbivore randomHerbivore(int x, int y){
        Herbivore[] randomHerbivore = {new Rabbit(x,y),new Monkey(x,y)};
        return randomHerbivore[randomNumberBetween(0,1)];
    }
    //TODO: should i add Medium Size
    public static Rock randomRock(int x, int y){
        char[] randomSize = {'S','L'};
        return new Rock(x,y,randomSize[randomNumberBetween(0,1)]);
    }
    public static char randomDirection(){
        char[] randomDirection = {'L','R','U','D'};
        return randomDirection[randomNumberBetween(0,3)];
    }

    public static int randomNumberBetween(int min,int max){
        return new Random().nextInt((max - min) + 1) + min;
    }
}
