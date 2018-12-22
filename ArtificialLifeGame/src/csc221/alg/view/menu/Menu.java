package csc221.alg.view.menu;

import csc221.alg.controller.GameEventHandler;
import csc221.alg.controller.MenuEventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

//Menu View
public class Menu {
    private MenuEventHandler menuHandler;
    private GameEventHandler gameHandler;
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    private static final int MENU_BUTTONS_START_X = 70;
    private static final int MENU_BUTTONS_START_Y = 110;
    private ALGSubscene creditsSubscene;
    private ALGSubscene helpSubscene;
    private ALGSubscene scoresSubscene;
    private ALGSubscene characterChooserScene;
    private ALGSubscene sceneToHide;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    public List<ALGButton> menuButtons;
    public List<CharacterPicker> characterList;
    public Character choosenCHARACTER;
    private ALGSubscene helpText;
    private ALGSubscene creditsText;

    public void setMenuHandler(MenuEventHandler handler) {
        this.menuHandler = handler;
    }
    public void setGameHandler(GameEventHandler handler) {
        this.gameHandler = handler;
    }

    public ALGSubscene getCreditsSubscene() {
        return creditsSubscene;
    }
    public ALGSubscene getHelpSubscene() {
        return helpSubscene;
    }
    public ALGSubscene getCharacterChooserScene() {
        return characterChooserScene;
    }

    public Menu() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        createSubscenes();
        createBackground();
        menuButtons = new ArrayList<>();
        createButtons();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createButtons() {
        createStartButton();
        createHelpsButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        ALGButton startButton = new ALGButton("PLAY");
        addMenuButton(startButton);
        startButton.setOnAction(event -> {
            if (menuHandler != null) {
                menuHandler.characterChooserEvent();
            }
        });
    }

    private void createHelpsButton() {
        ALGButton helpButton = new ALGButton("HELP");
        addMenuButton(helpButton);
        helpButton.setOnAction(event -> {
                    if (menuHandler != null) {
                        menuHandler.helpEvent();
                    }
                }
        );
    }

    private void createCreditsButton() {
        ALGButton creditsButton = new ALGButton("CREDITS");
        addMenuButton(creditsButton);
        creditsButton.setOnAction(event -> {
                    if (menuHandler != null) {
                        menuHandler.creditsEvent();
                    }
                }
        );
    }

    private void createExitButton() {
        ALGButton exitButton = new ALGButton("EXIT");
        addMenuButton(exitButton);
        exitButton.setOnAction(event -> {
                    if (menuHandler != null) {
                        menuHandler.exitEvent();
                    }
                }
        );
    }

    private void createBackground() {
        Image backgroundImage = new Image("/resources/images/menuBackground2.jpg", WIDTH, HEIGHT, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void addMenuButton(ALGButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);// This value is fixed
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);//Each time button add Y position increase
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createSubscenes() {
        creditsSubscene = new ALGSubscene();
        mainPane.getChildren().add(creditsSubscene);

        createCreditsText();

        helpSubscene = new ALGSubscene();
        mainPane.getChildren().add(helpSubscene);

        createHelpText();

        scoresSubscene = new ALGSubscene();
        mainPane.getChildren().add(scoresSubscene);

        characterChooserScene = new ALGSubscene();
        mainPane.getChildren().add(characterChooserScene);

        createCharacterChooserSubscene();
    }

    public void showSubScene(ALGSubscene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createHelpText() {
        helpText = new ALGSubscene();
        mainPane.getChildren().add(helpText);
        InfoLabel helpText = new InfoLabel("Controls");
        InfoLabel leftArrowKey = new InfoLabel("← : Move Left");
        InfoLabel upArrowKey = new InfoLabel("↑ : Move Up");
        InfoLabel rightArrowKey = new InfoLabel("→ : Move Right");
        InfoLabel downArrowKey = new InfoLabel("↓ : Move Down");
        helpText.setLayoutX(100);
        helpText.setLayoutY(25);
        leftArrowKey.setLayoutX(100);
        leftArrowKey.setLayoutY(75);
        upArrowKey.setLayoutX(100);
        upArrowKey.setLayoutY(125);
        rightArrowKey.setLayoutX(100);
        rightArrowKey.setLayoutY(175);
        downArrowKey.setLayoutX(100);
        downArrowKey.setLayoutY(225);
        helpSubscene.getPane().getChildren().add(helpText);
        helpSubscene.getPane().getChildren().add(leftArrowKey);
        helpSubscene.getPane().getChildren().add(upArrowKey);
        helpSubscene.getPane().getChildren().add(rightArrowKey);
        helpSubscene.getPane().getChildren().add(downArrowKey);
    }

    private void createCreditsText(){
        creditsText = new ALGSubscene();
        mainPane.getChildren().add(creditsText);
        InfoLabel creditsText = new InfoLabel("Created by:");
        InfoLabel creatorOne = new InfoLabel("Samuel Fils");
        InfoLabel creatorTwo = new InfoLabel("Jeffrey Lei");
        InfoLabel creatorThree = new InfoLabel("Brian Thibeault");
//        creditsText.setMinWidth(100);
//        creditsText.setMinHeight(100);
        creditsText.setLayoutX(100);
        creditsText.setLayoutY(25);
        creatorOne.setLayoutX(100);
        creatorOne.setLayoutY(85);
        creatorTwo.setLayoutX(100);
        creatorTwo.setLayoutY(145);
        creatorThree.setLayoutX(100);
        creatorThree.setLayoutY(205);
        creditsSubscene.getPane().getChildren().add(creditsText);
        creditsSubscene.getPane().getChildren().add(creatorOne);
        creditsSubscene.getPane().getChildren().add(creatorTwo);
        creditsSubscene.getPane().getChildren().add(creatorThree);
    }

    private void createCharacterChooserSubscene() {
        characterChooserScene = new ALGSubscene();
        mainPane.getChildren().add(characterChooserScene);

        InfoLabel choosenCharacterLabel = new InfoLabel("Choose Your Player");
        choosenCharacterLabel.setLayoutX(100);
        choosenCharacterLabel.setLayoutY(25);
        characterChooserScene.getPane().getChildren().add(choosenCharacterLabel);
        characterChooserScene.getPane().getChildren().add(createCharactersToChoose());
        characterChooserScene.getPane().getChildren().add(createButtonToStart());
    }

    private HBox createCharactersToChoose() {
        HBox box = new HBox();

        box.setSpacing(35);
        characterList = new ArrayList<>();
        for (Character CHARACTER : Character.values()) {
            CharacterPicker characterToPick = new CharacterPicker(CHARACTER);
            characterList.add(characterToPick);
            box.getChildren().add(characterToPick);
            characterToPick.setOnMouseClicked(event -> {
                if (menuHandler != null) {
                    menuHandler.characterSelectEvent(characterToPick);
                }
            });
        }
        box.setLayoutX(300 - (118 * 2));
        box.setLayoutY(100);
        return box;


    }


    private ALGButton createButtonToStart() {
        ALGButton startButton = new ALGButton("START");
        startButton.setLayoutX(285);
        startButton.setLayoutY(240);
        startButton.setOnAction(event -> {
            menuHandler.hideEvent();
            gameHandler.startGameEvent();
        });
        return startButton;
    }

}


