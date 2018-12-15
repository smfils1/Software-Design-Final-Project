package csc221.alg.model;

import java.util.ArrayList;

//TODO: Remove movable entities from the World when they are dead using isDead & update movableEntities

public class World {
    private static World ourInstance = new World();
    public static World getInstance() {
        return ourInstance;
    }

    static private ArrayList<ArrayList<Region>> world;
    private ArrayList<Movable> movableEntities;
    private Agent mainCharacter;
    private Time time;

    private World() {
        world = new ArrayList<>();
        time = new Time(20,100);
        movableEntities = new ArrayList<>();
        loadTerrain(Loader.map("terrain.txt"));
        loadEntities(Loader.map("entities.txt"));
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

    public  char entityToChar(Entity entity) {
        if(entity instanceof Agent){
            return 'A';
        } else if(entity instanceof Carnivore){
            return carnivoreText(entity);
        } else if(entity instanceof Herbivore){
            return herbivoreText(entity);
        } else if(entity instanceof Rock){
            return 'R';
        } else if(entity instanceof Base){
            return 'B';
        } else if(entity instanceof Bush){
            return 'b';
        } else if(entity instanceof Tree){
            return 'T';
        } else{
            return ' ';
        }
    }

    public  String terrainToColor(Region region) {
        if(region.getTerrainType() == '.'){
            return "tan";
        } else if(region.getTerrainType() == '~'){
            return "blue";
        }else{
            return "green";
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

    private Entity charToEntity(char entity, int x, int y ) {
        if(entity == 'A'){
            return new Agent(x,y);
        } else if(entity == 'C'){
            return RandomGenerator.randomCarnivore(x,y);
        } else if(entity == 'H'){
            return RandomGenerator.randomHerbivore(x,y);
        } else if(entity == 'R'){
            return RandomGenerator.randomRock(x,y);
        } else if(entity == 'B'){
            return new Base(x,y);
        } else if(entity == 'b'){
            return new Bush(x,y);
        } else if(entity == 'T'){
            return new Tree(x,y);
        } else{
            return null;
        }
    }

    public Agent getMainCharacter() {
        return mainCharacter;
    }

    public Time getTime() {
        return time;
    }

    public static ArrayList<ArrayList<Region>> getWorld() {
        return world;
    } //Must be static (fixes bug)

    public ArrayList<Movable> getMovableEntities() {
        return movableEntities;
    }

}



