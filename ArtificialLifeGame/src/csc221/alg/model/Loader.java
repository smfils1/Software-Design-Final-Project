package csc221.alg.model;
//TODO: Loader may need error handling

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Class to loader and convert file to an array of Characters
public class Loader {
    public static ArrayList<ArrayList<Character>> map(String filename){
        String resource = "/resources/maps/";
        Scanner input = new Scanner(Loader.class.getResourceAsStream(resource.concat(filename)));
        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        while (input.hasNext()){
            String strRow = input.nextLine();
             ArrayList<Character> rowList = new ArrayList<>();
            Character[] row = strRow.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
            Collections.addAll(rowList, row);
            grid.add(rowList);
        }
        input.close();
        return grid;
    }



}
