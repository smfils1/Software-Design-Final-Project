package csc221.alg.model;

//Each region of the game has an terrain & maybe an entity
public class Region {
    final private  Character terrain;
    private Entity entity;

    public Region(char terrain, Entity entity) {
        this.terrain = terrain;
        this.entity = entity;
    }

    public char getTerrainType() {
        return terrain;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
