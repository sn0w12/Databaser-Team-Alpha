package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class StaffMainMenu extends Application {
  Stage stage;
  private final GameController gameController;
  private final TeamController teamController;
  private final PlayerController playerController;
  private final MatchController matchController;
  private final StaffController staffController;


  // Constructor
  public StaffMainMenu(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController) {
    this.gameController = gameController;
    this.teamController = teamController;
    this.playerController = playerController;
    this.matchController = matchController;
    this.staffController = staffController;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.stage = primaryStage; // initialize stage

    Platform.runLater(() -> {
      stage.setTitle("Staff Main Menu");

      // Add GAMES button
      Button gamesButton = createGamesButton();
      gamesButton.setOnAction(event -> handleGamesButton());

      // Add TEAMS button
      Button teamsButton = createTeamsButton();
      teamsButton.setOnAction(event -> handleTeamsButton());

      // Add PLAYERS button
      Button playersButton = createPlayersButton();
      playersButton.setOnAction(event -> {
        try {
          handlePlayersButton();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });

      // Add MATCHES button
      Button matchesButton = createMatchesButton();
      matchesButton.setOnAction(event -> {
        try {
          handleMatchesButton();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });

      // Add TOURNAMENT button
      Button tournamentsButton = createTournamentsButton();
      tournamentsButton.setOnAction(event -> handleTournamentsButton());

      // Add STAFF button
      Button staffButton = createStaffButton();
      staffButton.setOnAction(event -> {
        try {
          handleStaffButton();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });

      // Add LOGOUT button
      Button logOutButton = createLogOutButton();
      logOutButton.setOnAction(event -> handleLogOutButton());

      // Add EXIT button
      Button exitButton = createExitButton();
      exitButton.setOnAction(event -> handleExitButton());

      // Set the preferred width for both buttons
      double preferredWidth = 130.0; // Adjust the value as needed
      gamesButton.setPrefWidth(preferredWidth);
      teamsButton.setPrefWidth(preferredWidth);
      playersButton.setPrefWidth(preferredWidth);
      matchesButton.setPrefWidth(preferredWidth);
      tournamentsButton.setPrefWidth(preferredWidth);
      staffButton.setPrefWidth(preferredWidth);
      logOutButton.setPrefWidth(preferredWidth);
      exitButton.setPrefWidth(preferredWidth);

      // Create a vertical layout for buttons
      VBox buttonLayout = new VBox(10); // 10 is the spacing between buttons
      buttonLayout.getChildren().addAll(gamesButton, teamsButton, playersButton, matchesButton, tournamentsButton, staffButton, logOutButton, exitButton);
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

  }

  private Button createGamesButton() {
    Button gamesButton = new Button("GAMES");
    gamesButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    gamesButton.setOnMouseEntered(event -> gamesButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    gamesButton.setOnMouseExited(event -> gamesButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return gamesButton;
  }

  private void handleGamesButton() {
    System.out.println("Launching GAMES");
  }

  private Button createTeamsButton() {
    Button teamsButton = new Button("TEAMS");
    teamsButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    teamsButton.setOnMouseEntered(event -> teamsButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    teamsButton.setOnMouseExited(event -> teamsButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return teamsButton;
  }

  private void handleTeamsButton() {
    System.out.println("Launching TEAMS");
  }

  private Button createPlayersButton() {
    Button playersButton = new Button("PLAYERS");
    playersButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    playersButton.setOnMouseEntered(event -> playersButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    playersButton.setOnMouseExited(event -> playersButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return playersButton;
  }

  public void handlePlayersButton() throws Exception {
    System.out.println("Launching PLAYERS");

    PlayerGraphics playerGraphics = new PlayerGraphics(gameController, teamController, playerController, matchController, staffController);

    // Start next stage
    playerGraphics.start(stage);


    stage.close();
  }

  private Button createMatchesButton() {
    Button matchesButton = new Button("MATCHES");
    matchesButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    matchesButton.setOnMouseEntered(event -> matchesButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    matchesButton.setOnMouseExited(event -> matchesButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return matchesButton;
  }

  public void handleMatchesButton() throws Exception {
    System.out.println("Launching MATCHES");

    MatchGraphics matchGraphics = new MatchGraphics();

    // start stage
    matchGraphics.start(stage);
  }

  private Button createTournamentsButton() {
    Button tournamentsButton = new Button("TOURNAMENTS");
    tournamentsButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    tournamentsButton.setOnMouseEntered(event -> tournamentsButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    tournamentsButton.setOnMouseExited(event -> tournamentsButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return tournamentsButton;
  }

  private void handleTournamentsButton() {
    System.out.println("Launching TOURNAMENTS");
  }

  private Button createStaffButton() {
    Button staffButton = new Button("STAFF");
    staffButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    staffButton.setOnMouseEntered(event -> staffButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    staffButton.setOnMouseExited(event -> staffButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return staffButton;
  }

  private void handleStaffButton() throws Exception {
    System.out.println("Launching STAFF");

    StaffGraphics staffGraphics = new StaffGraphics(gameController, teamController, playerController, matchController, staffController);

    // Start next stage
    staffGraphics.start(stage);


    stage.close();
  }

  private Button createLogOutButton() {
    Button logOutButton = new Button("LOGOUT");
    logOutButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    logOutButton.setOnMouseEntered(event -> logOutButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    logOutButton.setOnMouseExited(event -> logOutButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return logOutButton;
  }

  private void handleLogOutButton() {
    System.out.println("Logging out -> back to main menu");

    // Close the current stage
    stage.close();

    // Create a new instance of MainWindow and show its stage
    Platform.runLater(() -> {
      Stage primaryStage = new Stage();
      MainWindow mainWindow = new MainWindow();
      mainWindow.start(primaryStage);
    });
  }

  private Button createExitButton() {
    Button exitButton = new Button("EXIT");
    exitButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    exitButton.setOnMouseEntered(event -> exitButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    exitButton.setOnMouseExited(event -> exitButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    exitButton.setOnAction(event -> handleExitButton());
    return exitButton;
  }

  private void handleExitButton() {
    Platform.exit();
    System.exit(0);
  }

}