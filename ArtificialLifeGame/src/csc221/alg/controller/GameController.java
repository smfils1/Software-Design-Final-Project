package csc221.alg.controller;

import csc221.alg.model.*;
import csc221.alg.view.game.GameView;
import csc221.alg.view.game.Tile;
import csc221.alg.view.menu.Menu;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//TODO: Implement pause and play button eventListeners

public class GameController implements GameEventHandler {
    private GameView gameView;
    private Menu menuView;
    private boolean running;


    public GameController(Menu menu, GameView gameView) {
        running = true;
        this.gameView = gameView;
        this.menuView = menu;
        gameView.setGameHandler(this);
        menuView.setGameHandler(this);
    }


    @Override
    public void exitEvent() {
        gameView.setGameHandler(null);

        running = false;
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
                    char entityChar = World.getInstance().entityToChar(entity);
                    Paint terrainColor = gameView.strColorToPaint(World.getInstance().terrainToColor(world.get(j).get(i)));
                    Tile tile = new Tile(i,j, 15, entityChar, terrainColor);
                    tileGrid[i][j] = tile;
                    gameView.getGamePane().getChildren().add(tile);
                    if(entity instanceof Movable){
                        gameView.getTileWaitingQueue().add(tile);
                    }
                }
            }
            gameView.createSubscenes();
            gameView.setTileGrid(tileGrid);
            startArtificialMovement();
            World.getInstance().getTime().startTime();
            gameView.getSideContent().setRescueTime(World.getInstance().getTime().getEndTime()); //Sets Resume Time View
            startGameLoop();
        }
    }

    @Override
    public void moveLeftEvent() {
        process('L');
    }

    @Override
    public void moveRightEvent() {
        process('R');
    }

    @Override
    public void moveUpEvent() {
        process('U');
    }

    @Override
    public void moveDownEvent() {
        process('D');
    }


    private void process(char command) {
        if (command == 'D') {
            moveAgent('D');
        } else if (command == 'L') {
            moveAgent('L');
        } else if (command == 'R') {
            moveAgent('R');
        } else if (command == 'U') {
            moveAgent('U');
        }
    }

    private void moveAgent(char direction){
        Agent agent = World.getInstance().getMainCharacter();
        int oldX = agent.getXPosition();
        int oldY = agent.getYPosition();
        if(direction == 'D' ){
            agent.moveDown();
        }
        if(direction == 'L' ){
            agent.moveLeft();
        }
        if(direction == 'R' ){
            agent.moveRight();
        }
        if(direction == 'U'){
            agent.moveUp();
        }
        updateCreaturePosition(oldX,oldY);
        gameView.getSideContent().setHealth(agent.getHealth()); //Updates Health View
    }

    //TODO: We can add different schedules for different animal types speed. Implement LATER
    private void startArtificialMovement(){
        ArrayList<Movable> movableEntities = World.getInstance().getMovableEntities();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!running) {this.cancel();}
                for(Movable entity :  movableEntities) {
                    int oldX = ((Creature) entity).getXPosition();
                    int oldY = ((Creature) entity).getYPosition();
                    if (!(entity instanceof Agent)) {
                        char dir = RandomGenerator.randomDirection();
                        if (dir == 'D') {
                            entity.moveDown();
                        }
                        if (dir == 'L') {
                            entity.moveLeft();
                        }
                        if (dir == 'R') {
                            entity.moveRight();
                        }
                        if (dir == 'U') {
                            entity.moveUp();
                        }
                    }
                    updateCreaturePosition(oldX, oldY);
                }
            }},0,250);
    }

    private void startGameLoop(){
        AnimationTimer gameLoop = new AnimationTimer() {
            int runOnce = 1;
            @Override
            public void handle(long now) {
                updateView( World.getInstance().getMovableEntities());
                gameView.getSideContent().setTime(World.getInstance().getTime().getCurrentTime());
                if(!running){this.stop();}
                //Day & Night
                if(World.getInstance().getTime().isNight()){
                    updateNightVision();
                    runOnce = 1;
                }else if(World.getInstance().getTime().isDay() && runOnce == 1){
                    runOnce++;
                    updateDayVision();
                }
                //Loser & Winner
                if(World.getInstance().getMainCharacter().isDead()){
                    //gameView.setGameHandler(null);
                    running = false;
                    gameView.getGameOverSubscene().moveSubSceneLeft(900);

                }else if(World.getInstance().getMainCharacter().isAlive() && World.getInstance().getTime().hasEnd()){
                    //gameView.setGameHandler(null);
                    running = false;
                    gameView.getWinnerSubscene().moveSubSceneLeft(900);
                }
            }
        };
        gameLoop.start();
    }

    private void updateView(ArrayList<Movable> movableEntities) {
        for(Movable entity :  movableEntities) {
            gameView.getTileWaitingQueue().remove().setText(' ');
            int x = ((Creature)entity).getXPosition();
            int y = ((Creature)entity).getYPosition();
            char entityChar = World.getInstance().entityToChar(((Entity)entity));
            gameView.getTileGrid()[x][y].setText(entityChar);
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
                gameView.getTileGrid()[x][y].resetColor();
                boolean xBoundary = x <= agent.getXPosition() - visionRadius || x >= agent.getXPosition() + visionRadius;
                boolean yBoundary = y <= agent.getYPosition() - visionRadius || y >= agent.getYPosition() + visionRadius;
                if (xBoundary || yBoundary ) {
                    gameView.getTileGrid()[x][y].setColor(Color.BLACK);
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
                gameView.getTileGrid()[x][y].resetColor();
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

