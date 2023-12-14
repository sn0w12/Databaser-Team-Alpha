package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Optional;

public class PlayerGraphics extends Application {

  Stage stage;
  private GameController gameController;
  private TeamController teamController;
  private PlayerController playerController;
  private MatchController matchController;
  private StaffController staffController;

  public PlayerGraphics(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController) {
    this.gameController = gameController;
    this.teamController = teamController;
    this.playerController = playerController;
    this.matchController = matchController;
    this.staffController = staffController;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      this.stage = primaryStage; // initialize
      Platform.runLater(() -> {
      stage.setTitle("Players Menu");

      // Create new player
      Button createPlayer = createCreatePlayerButton();
      createPlayer.setOnAction(event -> handleCreatePlayerButton());

      // List all Players
      Button listPlayers = createListPlayersButton();
      listPlayers.setOnAction(event -> handleCreateListPlayersButton());

      // List specific Player by ID
      Button listSpecificPlayer = createListSpecificPlayerButton();
      listSpecificPlayer.setOnAction(event -> handleCreateListSpecificPlayerButton());

      // Add Player to Team
      Button addPlayerToTeam = createAddPlayerToTeamButton();
      addPlayerToTeam.setOnAction(event -> handleCreateAddPlayerToTeamButton());

      // Remove Player from Team
      Button removePlayerFromTeam = createRemovePlayerFromTeamButton();
      removePlayerFromTeam.setOnAction(event -> handleCreateRemovePlayerFromTeamButton());

      // Update player
      Button updatePlayer = createUpdatePlayerButton();
      updatePlayer.setOnAction(event -> handleCreateUpdatePlayerButton());

      // Add BACK button
      Button backButton = createBackButton();
      backButton.setOnAction(event -> {
        try {
          handleBackButton();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });

      // Set the preferred width for both buttons
      double preferredWidth = 190.0; // Adjust the value as needed
      createPlayer.setPrefWidth(preferredWidth);
      listPlayers.setPrefWidth(preferredWidth);
      listSpecificPlayer.setPrefWidth(preferredWidth);
      addPlayerToTeam.setPrefWidth(preferredWidth);
      removePlayerFromTeam.setPrefWidth(preferredWidth);
      updatePlayer.setPrefWidth(preferredWidth);
      backButton.setPrefWidth(preferredWidth);

      // Create a vertical layout for buttons
      VBox buttonLayout = new VBox(10); // 10 is the spacing between buttons
      buttonLayout.getChildren().addAll(createPlayer, listPlayers, listSpecificPlayer, addPlayerToTeam, removePlayerFromTeam, updatePlayer, backButton);
      buttonLayout.setAlignment(Pos.CENTER);

      // Create a new layout to contain button layout
      VBox layout = new VBox();
      layout.getChildren().add(buttonLayout);
      layout.setAlignment(Pos.CENTER);

      // Load BG image for background
      Image backgroundImage = new Image("file:BG.jpg");

      // Create a background with the image
      BackgroundImage background = new BackgroundImage(
          backgroundImage,
          BackgroundRepeat.NO_REPEAT,
          BackgroundRepeat.NO_REPEAT,
          BackgroundPosition.CENTER,
          new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
      );

      // apply background to layout
      layout.setBackground(new Background(background));

      Scene scene = new Scene(layout, 800, 600);
      stage.setScene(scene);
      stage.show();

      });

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private Button createCreatePlayerButton() {
    Button createPlayer = new Button("CREATE PLAYER");
    createPlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    createPlayer.setOnMouseEntered(event -> createPlayer.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    createPlayer.setOnMouseExited(event -> createPlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    createPlayer.setOnAction(event -> handleCreatePlayerButton());
    return createPlayer;
  }

  private void handleCreatePlayerButton() {
    System.out.println("Launch CREATE PLAYER WINDOW");
  }

  private Button createListPlayersButton() {
    Button createListPlayers = new Button("LIST PLAYERS");
    createListPlayers.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    createListPlayers.setOnMouseEntered(event -> createListPlayers.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    createListPlayers.setOnMouseExited(event -> createListPlayers.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    createListPlayers.setOnAction(event -> handleCreateListPlayersButton());
    return createListPlayers;
  }

  private void handleCreateListPlayersButton() {
    System.out.println("LIST ALL PLAYERS");
  }

  private Button createListSpecificPlayerButton() {
    Button createListSpecificPlayer = new Button("LIST SPECIFIC PLAYER BY ID");
    createListSpecificPlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    createListSpecificPlayer.setOnMouseEntered(event -> createListSpecificPlayer.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    createListSpecificPlayer.setOnMouseExited(event -> createListSpecificPlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    createListSpecificPlayer.setOnAction(event -> handleCreateListSpecificPlayerButton());
    return createListSpecificPlayer;
  }

  private void handleCreateListSpecificPlayerButton() {
    System.out.println("OPEN NEW WINDOW - SEARCH BY ID");
  }

  private Button createAddPlayerToTeamButton() {
    Button createAddPlayerToTeam = new Button("ADD PLAYER TO TEAM");
    createAddPlayerToTeam.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    createAddPlayerToTeam.setOnMouseEntered(event -> createAddPlayerToTeam.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    createAddPlayerToTeam.setOnMouseExited(event -> createAddPlayerToTeam.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    createAddPlayerToTeam.setOnAction(event -> handleCreateAddPlayerToTeamButton());
    return createAddPlayerToTeam;
  }

  private void handleCreateAddPlayerToTeamButton() {
    System.out.println("OPEN WINDOW TO CHOOSE WHAT TEAM TO ADD TO BY DORP DOWN MENU");
  }

  private Button createRemovePlayerFromTeamButton() {
    Button createRemovePlayerFromTeam = new Button("REMOVE PLAYER FROM TEAM");
    createRemovePlayerFromTeam.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    createRemovePlayerFromTeam.setOnMouseEntered(event -> createRemovePlayerFromTeam.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    createRemovePlayerFromTeam.setOnMouseExited(event -> createRemovePlayerFromTeam.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    createRemovePlayerFromTeam.setOnAction(event -> handleCreateRemovePlayerFromTeamButton());
    return createRemovePlayerFromTeam;
  }

  private void handleCreateRemovePlayerFromTeamButton() {
    System.out.println("open new window and choose player from drop down menu");
  }

  private Button createUpdatePlayerButton() {
    Button createUpdatePlayer = new Button("RUPDATE PLAYERM");
    createUpdatePlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    createUpdatePlayer.setOnMouseEntered(event -> createUpdatePlayer.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    createUpdatePlayer.setOnMouseExited(event -> createUpdatePlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    createUpdatePlayer.setOnAction(event -> handleCreateUpdatePlayerButton());
    return createUpdatePlayer;
  }

  public void handleCreateUpdatePlayerButton() {
    System.out.println("New window - choose what player to update");
  }

  private Button createBackButton() {
    Button backButton = new Button("BACK");
    backButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    backButton.setOnMouseEntered(event -> backButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    backButton.setOnMouseExited(event -> backButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    backButton.setOnAction(event -> {
      try {
        handleBackButton();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    return backButton;
  }

  private void handleBackButton() throws Exception {
    System.out.println("Back to Staff Main Menu");

    StaffMainMenu staffMainMenu = new StaffMainMenu(gameController, teamController, playerController, matchController, staffController);

    // start stage
    staffMainMenu.start(stage);

    stage.close();


  }
}
