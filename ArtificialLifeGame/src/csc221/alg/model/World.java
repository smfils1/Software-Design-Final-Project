package csc221.alg.model;

import java.util.ArrayList;

//TODO: randomUtilityClass
//TODO:Singleton
//TODO: World has String image paths to images of entities w/ getters & setters

public class World {
    private static World ourInstance = new World();
    public static World getInstance() {
        //System.out.println(ourInstance == null);
        return ourInstance;
    }

    static private ArrayList<ArrayList<Region>> world;
    private ArrayList<Movable> movableEntities;
    private Agent mainCharacter;

    private World() {
        world = new ArrayList<>();
        movableEntities = new ArrayList<>();
        loadTerrain(Loader.map("terrain.txt"));
        loadEntities(Loader.map("entities.txt"));

    }

    public  char entityToChar(Region region) {
        if(region.getEntity() instanceof Agent){
            return 'A';
        } else if(region.getEntity() instanceof Carnivore){
            return carnivoreText(region.getEntity());
        } else if(region.getEntity() instanceof Herbivore){
            return herbivoreText(region.getEntity());
        } else if(region.getEntity() instanceof Rock){
            return 'R';
        } else if(region.getEntity() instanceof Base){
            return 'B';
        } else if(region.getEntity() instanceof Bush){
            return 'b';
        } else if(region.getEntity() instanceof Tree){
            return 'T';
        } else{
            return region.getTerrainType();
        }
    }

    private static char herbivoreText(Entity entity) {
        if(entity instanceof Rabbit){return 'r';}
        else{return 'm';}
    }

    private static char carnivoreText(Entity entity) {
        if(entity instanceof Lion){return 'L';}
        else{return 'W';}
    }

    public Entity charToEntity(char entity, int x, int y ) {
        if(entity == 'A'){
            return new Agent(x,y);
        } else if(entity == 'C'){
            return RandomGenerator.randomCarnivore(x,y);
        } else if(entity == 'H'){
            return RandomGenerator.randomHerbivore(x,y);
        } else if(entity == 'R'){
            return RandomGenerator.randomRock(x,y);
        } else if(entity == 'S'){
            return new Base(x,y);
        } else if(entity == 'B'){
            return new Bush(x,y);
        } else if(entity == 'T'){
            return new Tree(x,y);
        } else{
            return null;
        }
    }

    private void loadTerrain(ArrayList<ArrayList<Character>> terrainMap) {//Works
        for (int y = 0; y < terrainMap.size(); y++) {
            ArrayList<Region> row = new ArrayList<>();
            for (int x = 0; x < terrainMap.get(0).size(); x++) {
                ArrayList<Character> rowArray1 = terrainMap.get(y);
                Region region = new Region(rowArray1.get(x) ,null);
                row.add(region);
            }
            world.add(row);
        }
    }

    private void loadEntities(ArrayList<ArrayList<Character>> entityMap) {//Works
        for (int y = 0; y < entityMap.size(); y++) {
            for (int x = 0; x < entityMap.get(0).size(); x++) {
                ArrayList<Character> rowArray1 = entityMap.get(y);
                world.get(y).get(x).setEntity(charToEntity(rowArray1.get(x),x,y));

                if(world.get(y).get(x).getEntity() instanceof Movable){

                    movableEntities.add((Movable) world.get(y).get(x).getEntity());
                }

                if(rowArray1.get(x) == 'A'){
                    mainCharacter = (Agent) world.get(y).get(x).getEntity();
                }
            }
        }
    }
    public Agent getMainCharacter() {
        return mainCharacter;
    }

    //This function is static so we dont access uninitialized data when the constructor executes
    static public ArrayList<ArrayList<Region>> getWorld() {
        return world;
    }

    public ArrayList<Movable> getMovableEntities() {
        return movableEntities;
    }

}



