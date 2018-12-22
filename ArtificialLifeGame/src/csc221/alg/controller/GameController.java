package csc221.alg.controller;

import csc221.alg.model.*;
import csc221.alg.view.game.GameView;
import csc221.alg.view.game.Tile;
import csc221.alg.view.menu.Menu;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.TimerTask;

import csc221.alg.model.*;
import csc221.alg.view.game.GameView;
import csc221.alg.view.menu.Character;
import csc221.alg.view.game.Tile;
import csc221.alg.view.menu.Menu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

//TODO: Implement pause and play button eventListeners

public class GameController implements GameEventHandler {
    private GameView gameView;
    private Menu menuView;
    private Timeline gameLoop;
    private int FPS = 8;

    public GameController(Menu menu, GameView gameView) {
        gameLoop = new Timeline();
        this.gameView = gameView;
        this.menuView = menu;
        gameView.setGameHandler(this);
        menuView.setGameHandler(this);
        gameView.getSideContent().setGameHandler(this);

    }

    @Override
    public void exitEvent() {
        gameView.setGameHandler(null);

        gameLoop.stop();
        System.exit(1);
    }

    @Override
    //Creates tiles and initialize the Game view
    public void startGameEvent ()  {
        if(menuView.choosenCHARACTER != null) {
            gameView.createNewGame(menuView.choosenCHARACTER); //TODO: Come back to this when we use character images
            ArrayList<ArrayList<Region>> world = World.getWorld();
            int y = World.getWorld().size();
            int x = World.getWorld().get(0).size();
            Tile tileGrid[][] = new Tile[x][y];
            for (int j = 0; j < y; j++) {
                for (int i = 0; i < x; i++) {
                    Entity entity = world.get(j).get(i).getEntity();
                    String entityUrl = World.getInstance().entityToUrl(entity);
                    //char entityUrl = World.getInstance().entityToChar(entity);
                    Paint terrainColor = gameView.strColorToPaint(World.getInstance().terrainColor(world.get(j).get(i)));
                    Tile tile;
                    if((entity instanceof Agent)) {
                        tile = new Tile(i, j, 15, menuView.choosenCHARACTER.getUrl(), terrainColor);
                        tileGrid[i][j] = tile;

                    }else{
                        tile = new Tile(i, j, 15, entityUrl, terrainColor);
                        //tile = new Tile(i, j, 15, entityUrl, terrainColor);
                        tileGrid[i][j] = tile;
                    }
                    gameView.getGamePane().getChildren().add(0,tile);
                    if(entity instanceof Movable){
                        gameView.getTileWaitingQueue().add(tile);
                    }
                }

            }
            gameView.setTileGrid(tileGrid);



            startArtificialMovement();
            World.getInstance().getTime().startTime();

            gameView.getSideContent().setRescueTime(World.getInstance().getTime().getEndTime()); //Sets Resume Time View
            startGameLoop();
        }
    }

    // TODO: Update inventory after each agent action

    @Override
    public void attackEvent() {
        Agent agent = World.getInstance().getMainCharacter();
        agent.attack();
        gameView.getSideContent().setBackpack(agent.getBackpack());
    }

    @Override
    public void buildBaseEvent() {
        Agent agent = World.getInstance().getMainCharacter();
        agent.buildBase();
        gameView.getSideContent().setBackpack(agent.getBackpack());
    }

    @Override
    public void collectResourceEvent() {
        Agent agent = World.getInstance().getMainCharacter();
        agent.collectResource();
        gameView.getSideContent().setBackpack(agent.getBackpack());
    }

    @Override
    public void eatEvent() {
        Agent agent = World.getInstance().getMainCharacter();
        agent.eat();
        gameView.getSideContent().setBackpack(agent.getBackpack());
    }

    @Override
    public void hideEvent() {
        Agent agent = World.getInstance().getMainCharacter();
        agent.hide();
    }

    @Override
    public void restEvent() {
        Agent agent = World.getInstance().getMainCharacter();
        agent.rest();
    }

    @Override
    public void moveLeftEvent() {
        process(Direction.LEFT);
    }

    @Override
    public void moveRightEvent() {
        process(Direction.RIGHT);
    }

    @Override
    public void moveUpEvent() {
        process(Direction.UP);
    }

    @Override
    public void moveDownEvent() {
        process(Direction.DOWN);
    }


    private void process(Direction command) {
        if (command.equals(Direction.DOWN)) {
            moveAgent(Direction.DOWN);
        } else if (command.equals(Direction.LEFT)) {
            moveAgent(Direction.LEFT);
        } else if (command.equals(Direction.RIGHT)) {
            moveAgent(Direction.RIGHT);
        } else if (command.equals(Direction.UP)) {
            moveAgent(Direction.UP);
        }
    }

    private void moveAgent(Direction direction){
        Agent agent = World.getInstance().getMainCharacter();
        int oldX = agent.getXPosition();
        int oldY = agent.getYPosition();
        if(direction.equals(Direction.DOWN)){
            agent.moveDown();
        }
        if(direction.equals(Direction.LEFT)){
            agent.moveLeft();
        }
        if(direction.equals(Direction.RIGHT)){
            agent.moveRight();
        }
        if(direction.equals(Direction.UP)){
            agent.moveUp();
        }
        updateCreaturePosition(oldX,oldY);
        gameView.getSideContent().setHealth(agent.getHealth()); //Updates Health View
    }

    private void startArtificialMovement(){
        ArrayList<Movable> movableEntities = World.getInstance().getMovableEntities();
        Timeline movementLoop = new Timeline();
        movementLoop.setCycleCount( Timeline.INDEFINITE );
        KeyFrame kf = new KeyFrame(
                Duration.seconds(1d/5),
                e -> {
                    if(!(gameLoop.getStatus() == Animation.Status.STOPPED || gameLoop.getStatus() == Animation.Status.PAUSED) )
                        for(Movable entity :  movableEntities) {
                            int oldX = ((Creature) entity).getXPosition();
                            int oldY = ((Creature) entity).getYPosition();
                            if (!(entity instanceof Agent)) {
                                Direction dir = EntityRandomFactory.randomDirection();
                                if (dir.equals(Direction.DOWN)) {
                                    entity.moveDown();
                                }
                                if (dir.equals(Direction.LEFT)) {
                                    entity.moveLeft();
                                }
                                if (dir.equals(Direction.RIGHT)) {
                                    entity.moveRight();
                                }
                                if (dir.equals(Direction.UP)) {
                                    entity.moveUp();
                                }
                            }
                            updateCreaturePosition(oldX, oldY);
                        }
                });
        movementLoop.getKeyFrames().add( kf );
        movementLoop.play();
    }


    private void startGameLoop(){

        gameLoop.setCycleCount( Timeline.INDEFINITE );
        KeyFrame kf = new KeyFrame(
                Duration.seconds(1d / FPS),
                new EventHandler<ActionEvent>() {
                    boolean ranOnce = false;
                    @Override
                    public void handle(ActionEvent event) {
                        updateView(World.getInstance().getMovableEntities());
                        gameView.getSideContent().setTime(World.getInstance().getTime().getCurrentTime());


                        //Day & Night
                        if (World.getInstance().getTime().isNight()) {
                            updateNightVision();

                        } else if (World.getInstance().getTime().isDay() && !ranOnce) {
                            ranOnce = !ranOnce;
                            updateDayVision();
                        }
                        //Loser & Winner
                        if (World.getInstance().getMainCharacter().isDead() ) {
                            endBackgroundProcesses();
                            gameView.getGameOverSubscene().moveSubSceneLeft(900);

                        } else if (World.getInstance().getMainCharacter().isAlive() && World.getInstance().getTime().hasEnd()) {
                            endBackgroundProcesses();
                            gameView.getWinnerSubscene().moveSubSceneLeft(900);
                        }
                    }
                } );
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
    }


    @Override
    public void pauseEvent(){
        pauseBackgroundProcesses();
    }

    @Override
    public void resumeEvent(){
        resumeBackgroundProcesses();
    }

    private void pauseBackgroundProcesses(){
        gameLoop.pause();
        World.getInstance().getTime().pause();
    }
    private void resumeBackgroundProcesses(){
        gameLoop.play();
        World.getInstance().getTime().resume();
    }
    private void endBackgroundProcesses(){
        gameLoop.stop();
        World.getInstance().getTime().stop();
    }

     private void updateView(ArrayList<Movable> movableEntities) {
        for(Movable entity :  movableEntities) {
            gameView.getTileWaitingQueue().remove().setImage(null);
            int x = ((Creature)entity).getXPosition();
            int y = ((Creature)entity).getYPosition();
            String entityUrl = World.getInstance().entityToUrl(((Entity)entity));
            gameView.getTileGrid()[x][y].setImage(entityUrl);
            gameView.getTileWaitingQueue().add(gameView.getTileGrid()[x][y]);
        }
    }

    //TODO: Implement night vision a different way. Current version loops thru all tiles.
    public void updateNightVision(){
        int WorldYSize = World.getWorld().size();
        int WorldXSize = World.getWorld().get(0).size();
        Agent agent = World.getInstance().getMainCharacter();
        int visionRadius = agent.getVisionRadius();
        for(int y = 0; y < WorldYSize; y++) {
            for (int x = 0; x <WorldXSize; x++) {
                Tile tile =gameView.getTileGrid()[x][y];
                tile.resetColor();
                boolean xBoundary = x <= agent.getXPosition() - visionRadius || x >= agent.getXPosition() + visionRadius;
                boolean yBoundary = y <= agent.getYPosition() - visionRadius || y >= agent.getYPosition() + visionRadius;
                if (xBoundary || yBoundary ) {
                    gameView.getTileGrid()[x][y].setColor(Color.BLACK);
                }
                if(tile.getCharacter().equals(EntityObject.ROCK.getImgUrl()) ||
                    tile.getCharacter().equals(Lifeform.TREE.getImgUrl()) ||
                    tile.getCharacter().equals(EntityObject.BASE.getImgUrl()) ||
                    tile.getCharacter().equals(Lifeform.BUSH.getImgUrl()) ||
                    tile.getCharacter().equals(Lifeform.TREE.getImgUrl())){
                    tile.getEntity().setVisible(false);

                }
            }
        }
    }

    //TODO: Implement day vision a different way. Current version loops thru all tiles to reset color.
    public void updateDayVision(){
        int WorldYSize = World.getWorld().size();
        int WorldXSize = World.getWorld().get(0).size();
        for(int y = 0; y < WorldYSize; y++) {
            for (int x = 0; x <WorldXSize; x++) {
                Tile tile =gameView.getTileGrid()[x][y];
                tile.resetColor();
                if(tile.getCharacter().equals(EntityObject.ROCK.getImgUrl()) ||
                        tile.getCharacter().equals(Lifeform.TREE.getImgUrl()) ||
                        tile.getCharacter().equals(EntityObject.BASE.getImgUrl()) ||
                        tile.getCharacter().equals(Lifeform.BUSH.getImgUrl()) ||
                        tile.getCharacter().equals(Lifeform.TREE.getImgUrl())){
                    tile.getEntity().setVisible(true);
                }
            }
        }
    }

    public void updateCreaturePosition(int oldX, int oldY) {
        Entity entity = World.getInstance().getWorld().get(oldY).get(oldX).getEntity();
        int newX = entity.getXPosition();
        int newY = entity.getYPosition();
        World.getInstance().getWorld().get(oldY).get(oldX).setEntity(null);
        World.getInstance().getWorld().get(newY).get(newX).setEntity(entity);
    }

}

