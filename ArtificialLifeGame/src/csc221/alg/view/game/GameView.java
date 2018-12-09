package csc221.alg.view.game;
//TODO: Game UI
import csc221.alg.controller.GameEventHandler;
import csc221.alg.view.menu.CHARACTER;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;//THIS IS FOR THE CONSOLE
import java.util.Scanner;//THIS IS FOR THE CONSOLE
import csc221.alg.model.*;//THIS IS FOR THE CONSOLE

//======================================================================================================================
//THIS IS THE GAME GUI.
//THE CONSOLE CODE IS BELOW
//======================================================================================================================

//TODO: Use GridPane instead of AnchorPane
public class GameView {
    private GameEventHandler gameHandler;
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private ImageView character;


    public GameView(){
        initializeStage();
        createKeyListeners();

        this.worldModel = World.getInstance();//THIS IS FOR THE CONSOLE CODE
        this.agent = worldModel.getMainCharacter();//THIS IS FOR THE CONSOLE CODE
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(event -> {
           //TODO:Implement
        });
        gameScene.setOnKeyReleased(event -> {
            //TODO: Implement
        });
    }

    public void setGameHandler(GameEventHandler handler) {
        this.gameHandler = handler;
    }


    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,WIDTH,HEIGHT);
        gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
    }

    public void createNewGame(CHARACTER choosenCHARACTER){//Game Window
        createCharacter(choosenCHARACTER);
        gameStage.show();
    }

    private void createCharacter(CHARACTER choosenCHARACTER){
        character = new ImageView(choosenCHARACTER.getUrl());
        character.setFitWidth(50);
        character.setFitHeight(50);
        //TODO: Put character in correct position
        character.setLayoutX(0);
        character.setLayoutY(0);
        gamePane.getChildren().add(character);
    }

//======================================================================================================================
//THIS IS THE CONSOLE View & Controllers
// TODO: Remove CONSOLE CODE AFTER Implementing the GUI version
//======================================================================================================================


    public World worldModel;
    public Agent agent;
    private Scanner input;

    //Process User Input
    private void process(String command) {
        if (command.charAt(0) == 's') {
            moveMovables('s');
        } else if (command.charAt(0) == 'a') {
            moveMovables('a');
        } else if (command.charAt(0) == 'd') {
            moveMovables('d');
        } else if (command.charAt(0) == 'w') {
            moveMovables('w');
        } else{
            play();
        }
    }

    //TODO: Move other entities move random
    //Moves the movable Entities
    private void moveMovables(char direction){
        ArrayList<Movable> movableEntities = worldModel.getMovableEntities();
        for(Movable entity :  movableEntities){
            int oldX = ((Creature) entity).getXPosition();
            int oldY= ((Creature) entity).getYPosition();
            if(entity instanceof Agent) {
                if(direction == 's' ){
                    entity.moveDown();
                }
                if(direction == 'a' ){
                    entity.moveLeft();
                }
                if(direction == 'd' ){
                    entity.moveRight();
                }
                if(direction == 'w'){
                    entity.moveUp();
                }
            }
            else{
                char dir = RandomGenerator.randomDirection();
                if( dir == 'D' ){
                    entity.moveDown();
                }
                if(dir == 'L'  ){
                    entity.moveLeft();
                }
                if(dir == 'R' ){
                    entity.moveRight();
                }
                if(dir == 'U'){
                    entity.moveUp();
                }
            }
            updateWorld(oldX,oldY);
        }
    }

    // Updates the world
    public void updateWorld(int x, int y) {
        for (int j = 0; j < worldModel.getWorld().size(); j++) {
            for (int i = 0; i < worldModel.getWorld().get(0).size(); i++) {
                if(worldModel.getWorld().get(j).get(i).getEntity() != null){
                    Entity entity = worldModel.getWorld().get(j).get(i).getEntity();
                    int nx = worldModel.getWorld().get(j).get(i).getEntity().getXPosition();
                    int ny = worldModel.getWorld().get(j).get(i).getEntity().getYPosition();
                    worldModel.getWorld().get(j).get(i).setEntity(null);
                    worldModel.getWorld().get(ny).get(nx).setEntity(entity);
                }
            }
        }
    }

    //Game Loop
    public void play(){
        input = new Scanner(System.in);
        printWorld(worldModel.getWorld());
        System.out.println("Vision: Agent");
        printVision(agent.getVision());
        while(true){
            System.out.print("Enter Direction: ");
            String direction = input.nextLine();
            if(direction.equals("")){
                System.out.println("Ending Game");
                break;
            }
            process(direction);
            System.out.println();
            printWorld(worldModel.getWorld());

            //The Agents Vision
            System.out.println("Vision: Agent");
            printVision(agent.getVision());

        }}

    public void printVision(ArrayList<ArrayList<Region>> m) {
        for (int y = 0; y < m.size(); y++) {
            for (int x = 0; x < m.get(0).size(); x++) {
                Region a= m.get(y).get(x);
                if(a == null){
                    System.out.print('x');
                }
                else {
                    System.out.print(worldModel.entityToChar(a));
                }
            }
            System.out.println();
        }
    }
    public void printWorld(ArrayList<ArrayList<Region>> m) {
        for (int y = 0; y < m.size(); y++) {
            for (int x = 0; x < m.get(0).size(); x++) {
                Entity entity = m.get(y).get(x).getEntity();
                if(entity == null){
                    System.out.print(m.get(y).get(x).getTerrainType());
                }
                else{
                    System.out.print(worldModel.entityToChar(m.get(y).get(x)));
                }
            }
            System.out.println();
        }
    }
}
