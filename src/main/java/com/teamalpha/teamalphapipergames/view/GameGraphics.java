package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class GameGraphics extends Application {

    Stage stage;
    private final GameController gameController;
    private final TeamController teamController;
    private final PlayerController playerController;
    private final MatchController matchController;
    private final StaffController staffController;
    private final TournamentController tournamentController;


    public GameGraphics(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController, TournamentController tournamentController) {
        this.gameController = gameController;
        this.teamController = teamController;
        this.playerController = playerController;
        this.matchController = matchController;
        this.staffController = staffController;
        this.tournamentController = tournamentController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            this.stage = primaryStage;
            Platform.runLater(() -> {
                mainWindow(stage);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainWindow (Stage gameStage) {
        gameStage.setTitle("Games");
        gameStage.setWidth(600);
        gameStage.setHeight(400);

        VBox vBoxAlternatives = new VBox();
        //Button buttonAddGame = new Button("Add new Game");
        Button buttonShowAllGames = new Button("Show all games");
        //Button buttonAlterGame = new Button("Alter game");
        Button buttonRemoveGame = new Button("Remove game");

        Button buttonAddGame = new Button("Add new Game");
        buttonAddGame.setOnAction(event -> {
            addGame(gameStage);  // Pass the Stage instance to addGame
        });
        Button buttonAlterGame = new Button("Alter Game");
        buttonAlterGame.setOnAction(event -> {
            alterGame();  // Pass the Stage instance to addGame
        });
        buttonShowAllGames.setOnAction(event -> showAllGames());
        //buttonAlterGame.setOnAction(event -> alterGame());
        buttonRemoveGame.setOnAction(event -> buttonRemoveGame());

        vBoxAlternatives.getChildren().addAll(buttonAddGame, buttonShowAllGames, buttonAlterGame, buttonRemoveGame);

        Scene sceneAlternatives = new Scene(vBoxAlternatives);
        gameStage.setScene(sceneAlternatives);
        gameStage.show();
    }

    //@Override
    /*public void start(Stage gameStage) throws Exception {
        gameStage.setTitle("Games");
        gameStage.setWidth(600);
        gameStage.setHeight(400);

        VBox vBoxAlternatives = new VBox();
        //Button buttonAddGame = new Button("Add new Game");
        Button buttonShowAllGames = new Button("Show all games");
        //Button buttonAlterGame = new Button("Alter game");
        Button buttonRemoveGame = new Button("Remove game");

        Button buttonAddGame = new Button("Add new Game");
        buttonAddGame.setOnAction(event -> {
            addGame(gameStage);  // Pass the Stage instance to addGame
        });
        Button buttonAlterGame = new Button("Alter Game");
        buttonAlterGame.setOnAction(event -> {
            alterGame();  // Pass the Stage instance to addGame
        });
        buttonShowAllGames.setOnAction(event -> showAllGames());
        //buttonAlterGame.setOnAction(event -> alterGame());
        buttonRemoveGame.setOnAction(event -> buttonRemoveGame());

        vBoxAlternatives.getChildren().addAll(buttonAddGame, buttonShowAllGames, buttonAlterGame, buttonRemoveGame);

        Scene sceneAlternatives = new Scene(vBoxAlternatives);
        gameStage.setScene(sceneAlternatives);
        gameStage.show();
    }*/

    //create
    private void addGame(Stage gameStage) {
        gameStage.setTitle("Add Games");
        gameStage.setWidth(600);
        gameStage.setHeight(400);
        // Create a VBox to hold the input fields and buttons
        VBox vBoxAddGame = new VBox();


        // Input field for the game name
        TextField nameField = new TextField();
        nameField.setPromptText("Game Name");

        // Button to add the game
        Button addButton = new Button("Add Game");
        addButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                // Create a new game and save it
                Game newGame = new Game(name);
                if (gameController.createGame(newGame.getName())) {
                    System.out.println("Game added successfully!");
                } else {
                    System.out.println("Failed to add the game.");
                }

                // Call start again to refresh the UI
                try {
                    start(gameStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Add input field and button to the VBox
        vBoxAddGame.getChildren().addAll(nameField, addButton);

        // Set up the scene and show the stage
        Scene sceneAddGame = new Scene(vBoxAddGame);
        gameStage.setScene(sceneAddGame);
        gameStage.show();
    }


    //read
    private void showAllGames() {
        // Retrieve the list of all games
        List<Game> allGames = gameController.getAllGames(true);

        // Create a TableView to display all games
        TableView<Game> gameTable = new TableView<>();
        TableColumn<Game, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Game, String> nameColumn = new TableColumn<>("Game Name");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        gameTable.getColumns().addAll(idColumn, nameColumn);
        gameTable.getItems().addAll(allGames);

        // Show the TableView in a new window
        Stage tableStage = new Stage();
        tableStage.setTitle("All Games");
        tableStage.setScene(new Scene(new VBox(gameTable), 300, 400));
        tableStage.show();
    }



    //Update game
    private void alterGame() {
        // Retrieve the list of all games
        List<Game> allGames = gameController.getAllGames(false);

        // Create a ChoiceDialog for game selection
        ChoiceDialog<Game> dialog = new ChoiceDialog<>(null, allGames);
        dialog.setTitle("Select Game to Alter");
        dialog.setHeaderText("Choose a game to alter:");
        dialog.setContentText("Game:");

        // Set the content of the dialog to include the ComboBox for game selection
        ComboBox<Game> gameComboBox = new ComboBox<>(FXCollections.observableArrayList(allGames));
        gameComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Game item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        dialog.getDialogPane().setContent(gameComboBox);
        // Wait for the user to choose a game or cancel
        dialog.showAndWait().ifPresent(selectedGame -> {
            // Create a Stage for altering the game
            Stage alterGameStage = new Stage();
            alterGameStage.setTitle("Alter Game");
            alterGameStage.setWidth(300);
            alterGameStage.setHeight(200);

            // Create UI components for altering the game
            Label nameLabel = new Label("New Name:");
            TextField nameField = new TextField(selectedGame.getName());
            Button alterButton = new Button("Alter Game");

            // Handle alter button event
            alterButton.setOnAction(event -> {
                String newName = nameField.getText().trim();
                if (!newName.isEmpty()) {
                    // Update the game details in the database
                    selectedGame.setName(newName);
                    if (gameController.updateGame(selectedGame)) {
                        System.out.println("Game altered successfully!");
                        alterGameStage.close(); // Close the stage after successful alteration
                    } else {
                        System.out.println("Failed to alter the game.");
                    }
                }
            });

            // Create layout and set scene for the alterGameStage
            VBox alterVBox = new VBox(10);
            alterVBox.getChildren().addAll(nameLabel, nameField, alterButton);
            //alterVBox.setPadding(new Insets(10));

            Scene alterScene = new Scene(alterVBox);
            alterGameStage.setScene(alterScene);
            alterGameStage.show();
        });
    }



    //delete game

    private void buttonRemoveGame() {
        // Retrieve the list of all games
        List<Game> allGames = gameController.getAllGames(false);

        // Create a ChoiceDialog for game selection
        ChoiceDialog<Game> dialog = new ChoiceDialog<>(null, allGames);
        dialog.setTitle("Select Game to Remove");
        dialog.setHeaderText("Choose a game to remove:");
        dialog.setContentText("Game:");

        // Set the content of the dialog to include the ComboBox for game selection
        ComboBox<Game> gameComboBox = new ComboBox<>(FXCollections.observableArrayList(allGames));
        gameComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Game item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        dialog.getDialogPane().setContent(gameComboBox);

        // Wait for the user to choose a game or cancel
        dialog.showAndWait().ifPresent(selectedGame -> {
            // Confirm with the user before removing the game
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm Removal");
            confirmation.setHeaderText("Are you sure you want to remove the selected game?");
            confirmation.setContentText("Game: " + selectedGame.getName());

            // Wait for the user to confirm or cancel
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Remove the selected game from the database
                    if (gameController.deleteGame(selectedGame.getGame_id())) {
                        System.out.println("Game removed successfully!");
                    } else {
                        System.out.println("Failed to remove the game.");
                    }
                }
            });
        });
    }

}