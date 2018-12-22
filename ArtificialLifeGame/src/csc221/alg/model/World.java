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
        time = new Time(30,1000);
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
            return Lifeform.AGENT.getValue();
        } else if(entity instanceof Carnivore){
            return carnivoreText(entity);
        } else if(entity instanceof Herbivore){
            return herbivoreText(entity);
        } else if(entity instanceof Rock){
            return EntityObject.ROCK.getValue();
        } else if(entity instanceof Base){
            return EntityObject.BASE.getValue();
        } else if(entity instanceof Bush){
            return Lifeform.BUSH.getValue();
        } else if(entity instanceof Tree){
            return Lifeform.BUSH.getValue();
        } else{
            return ' ';
        }
    }

    public  String entityToUrl(Entity entity) {
        if(entity instanceof Agent){
            return Lifeform.AGENT.getImgUrl();
        } else if(entity instanceof Carnivore){
            return carnivoreImgUrl(entity);
        } else if(entity instanceof Herbivore){
            return herbivoreImgUrl(entity);
        } else if(entity instanceof Rock){
            return EntityObject.ROCK.getImgUrl();
        } else if(entity instanceof Base){
            return EntityObject.BASE.getImgUrl();
        } else if(entity instanceof Bush){
            return Lifeform.BUSH.getImgUrl();
        } else if(entity instanceof Tree){
            return Lifeform.BUSH.getImgUrl();
        } else{
            return "";
        }
    }

    public  String terrainColor(Region region) {
        if(region.getTerrainType() == Terrain.DESSERT.getValue()){
            return Terrain.DESSERT.getColor();
        } else if(region.getTerrainType() == Terrain.WATER.getValue()){
            return Terrain.WATER.getColor();
        }else{
            return Terrain.GRASS.getColor();
        }
    }

    private static char herbivoreText(Entity entity) {
        if(entity instanceof Rabbit){return Lifeform.RABBIT.getValue();}
        else{return Lifeform.DEER.getValue();}
    }

    private static char carnivoreText(Entity entity) {
        if(entity instanceof Lion){return Lifeform.LION.getValue();}
        else{return Lifeform.WOLF.getValue();}
    }

    private static String herbivoreImgUrl(Entity entity) {
        if(entity instanceof Rabbit){return Lifeform.RABBIT.getImgUrl();}
        else{return Lifeform.DEER.getImgUrl();}
    }

    private static String carnivoreImgUrl(Entity entity) {
        if(entity instanceof Lion){return Lifeform.LION.getImgUrl();}
        else{return Lifeform.WOLF.getImgUrl();}
    }

    private Entity charToEntity(char entity, int x, int y ) {
        if(entity == Lifeform.AGENT.getValue()){
            return new Agent(x,y);
        } else if(entity == Lifeform.CARNIVORE.getValue()){
            return EntityRandomFactory.randomCarnivore(x,y);
        } else if(entity == Lifeform.HERBIVORE.getValue()){
            return EntityRandomFactory.randomHerbivore(x,y);
        } else if(entity == EntityObject.ROCK.getValue()){
            return EntityRandomFactory.randomRock(x,y);
        } else if(entity == EntityObject.BASE.getValue()){
            return new Base(x,y);
        } else if(entity == Lifeform.BUSH.getValue()){
            return new Bush(x,y);
        } else if(entity == Lifeform.TREE.getValue()){
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



