package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Staff;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class MainWindow extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  // Start page class / stage
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Login Window");

    // Add STAFF button
    Button staffButton = createStaffButton();
    staffButton.setOnAction(event -> handleStaffButton(primaryStage));

    // Add VISITOR button
    Button visitorButton = createVisitorButton();
    visitorButton.setOnAction(event -> handleVisitorButton());

    // Add EXIT button
    Button exitButton = createExitButton();
    exitButton.setOnAction(event -> handleExitButton());

    // Set the preferred width for both buttons
    double preferredWidth = 100.0; // Adjust the value as needed
    staffButton.setPrefWidth(preferredWidth);
    visitorButton.setPrefWidth(preferredWidth);
    exitButton.setPrefWidth(preferredWidth);

    // Create a vertical layout for buttons
    VBox buttonLayout = new VBox(10); // 10 is the spacing between buttons
    buttonLayout.getChildren().addAll(staffButton, visitorButton, exitButton);
    buttonLayout.setAlignment(Pos.CENTER);

    // Create a layout to contain the buttons
    VBox layout = new VBox(20); // 20 is the spacing between the button layout and other elements
    layout.getChildren().addAll(buttonLayout);
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

    // Apply background to the layout
    layout.setBackground(new Background(background));

    Scene scene = new Scene(layout, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
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

  private void handleStaffButton(Stage primaryStage) {
    GameController gameController = new GameController();
    TeamController teamController = new TeamController();
    PlayerController playerController = new PlayerController();
    MatchController matchController = new MatchController();
    StaffController staffController = new StaffController();
    ChooseEmployee chooseEmployee = new ChooseEmployee(primaryStage, gameController, teamController, playerController, matchController, staffController);

    Menu menu = new Menu(gameController);
    menu.createStaff();

    List<Staff> staffList = staffController.getAll(true);

    chooseEmployee.showEmployeeMenu(staffList);

    primaryStage.close();
  }

  private Button createVisitorButton() {
    Button visitorButton = new Button("VISITOR");
    visitorButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    visitorButton.setOnMouseEntered(event -> visitorButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    visitorButton.setOnMouseExited(event -> visitorButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return visitorButton;
  }

  private void handleVisitorButton() {
    System.out.println("Opening visitor window");
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

// Second stage Choose Employee Login Class / Stage
class ChooseEmployee {

  private final GameController gameController;
  private final TeamController teamController;
  private final PlayerController playerController;
  private final MatchController matchController;
  private final StaffController staffController;
  private final Stage stage;

  // Constructor
  ChooseEmployee(Stage stage, GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController) {
    this.stage = stage;
    this.gameController = gameController;
    this.teamController = teamController;
    this.playerController = playerController;
    this.matchController = matchController;
    this.staffController = staffController;
  }

  void showEmployeeMenu(List<Staff> staffList) {
    Platform.runLater(() -> {
      stage.setTitle("Employee Menu");

      ComboBox<String> employeeComboBox = new ComboBox<>();
      employeeComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
      employeeComboBox.setItems(FXCollections.observableArrayList(getStaffNames(staffList)));
      employeeComboBox.setOnAction(e -> handleComboBoxSelection(employeeComboBox));


      // Label on box
      Label label = new Label("Select Profile");
      label.setStyle("-fx-text-fill: white;");


      // Add Login button
      Button loginButton = createLoginButton();

      VBox layout = new VBox(10);
      layout.getChildren().addAll(label, employeeComboBox, loginButton);
      layout.setAlignment(Pos.CENTER);

      // load BG image for background
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

  // Methods
  private Button createLoginButton() {
    Button loginButton = new Button("LOGIN");
    loginButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    loginButton.setOnMouseEntered(event -> loginButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    loginButton.setOnMouseExited(event -> loginButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    loginButton.setOnAction(e -> {
      try {
        handleLoginButton();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    });
    return loginButton;
  }

  private void handleLoginButton() throws Exception {
    System.out.println("Logged in");
    StaffMainMenu staffMainMenu = new StaffMainMenu(gameController, teamController, playerController, matchController, staffController);

    // Start next stage
    staffMainMenu.start(stage);

    stage.close();
  }

  private List<String> getStaffNames(List<Staff> staffList) {
    return staffList.stream()
        .map(staff -> staff.getFirstName() + " " + staff.getLastName())
        .collect(Collectors.toList());
  }

  private void handleComboBoxSelection(ComboBox<String> employeeComboBox) {
    String selectedEmployee = employeeComboBox.getSelectionModel().getSelectedItem();
    if (selectedEmployee != null) {
      System.out.println("Selected Employee: " + selectedEmployee);
    }
  }
}

// Third stage - Main Menu class / stage





// PLAYER
//class PlayerGraphicsTest {
//
//  private final GameController gameController;
//  private final TeamController teamController;
//  private final PlayerController playerController;
//  private final MatchController matchController;
//  private final StaffController staffController;
//  private final Stage stage;
//
//
//  public PlayerGraphicsTest(Stage stage, GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController) {
//    this.stage = stage;
//    this.gameController = gameController;
//    this.teamController = teamController;
//    this.playerController = playerController;
//    this.matchController = matchController;
//    this.staffController = staffController;
//  }
//
//
//  public void showPlayersMenu() throws Exception {
//    Platform.runLater(() -> {
//      stage.setTitle("Players Menu");
//
//      // Create new player
//      Button createPlayer = createCreatePlayerButton();
//      createPlayer.setOnAction(event -> handleCreatePlayerButton());
//
//      // List all Players
//      Button listPlayers = createListPlayersButton();
//      listPlayers.setOnAction(event -> handleCreateListPlayersButton());
//
//      // List specific Player by ID
//      Button listSpecificPlayer = createListSpecificPlayerButton();
//      listSpecificPlayer.setOnAction(event -> handleCreateListSpecificPlayerButton());
//
//      // Add Player to Team
//      Button addPlayerToTeam = createAddPlayerToTeamButton();
//      addPlayerToTeam.setOnAction(event -> handleCreateAddPlayerToTeamButton());
//
//      // Remove Player from Team
//      Button removePlayerFromTeam = createRemovePlayerFromTeamButton();
//      removePlayerFromTeam.setOnAction(event -> handleCreateRemovePlayerFromTeamButton());
//
//      // Update player
//      Button updatePlayer = createUpdatePlayerButton();
//      updatePlayer.setOnAction(event -> handleCreateUpdatePlayerButton());
//
//      // Add BACK button
//      Button backButton = createBackButton();
//      backButton.setOnAction(event -> handleBackButton());
//
//      // Set the preferred width for both buttons
//      double preferredWidth = 130.0; // Adjust the value as needed
//      createPlayer.setPrefWidth(preferredWidth);
//      listPlayers.setPrefWidth(preferredWidth);
//      listSpecificPlayer.setPrefWidth(preferredWidth);
//      addPlayerToTeam.setPrefWidth(preferredWidth);
//      removePlayerFromTeam.setPrefWidth(preferredWidth);
//      updatePlayer.setPrefWidth(preferredWidth);
//      backButton.setPrefWidth(preferredWidth);
//
//      // Create a vertical layout for buttons
//      VBox buttonLayout = new VBox(10); // 10 is the spacing between buttons
//      buttonLayout.getChildren().addAll(createPlayer, listPlayers, listSpecificPlayer, addPlayerToTeam, removePlayerFromTeam, updatePlayer, backButton);
//      buttonLayout.setAlignment(Pos.CENTER);
//
//      // Create a new layout to contain button layout
//      VBox layout = new VBox();
//      layout.getChildren().add(buttonLayout);
//      layout.setAlignment(Pos.CENTER);
//
//      // Load BG image for background
//      Image backgroundImage = new Image("file:BG.jpg");
//
//      // Create a background with the image
//      BackgroundImage background = new BackgroundImage(
//          backgroundImage,
//          BackgroundRepeat.NO_REPEAT,
//          BackgroundRepeat.NO_REPEAT,
//          BackgroundPosition.CENTER,
//          new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
//      );
//
//      // apply background to layout
//      layout.setBackground(new Background(background));
//
//      Scene scene = new Scene(layout, 800, 600);
//      stage.setScene(scene);
//      stage.show();
//    });
//  }
//
//  private Button createCreatePlayerButton() {
//    Button createPlayer = new Button("CREATE PLAYER");
//    createPlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
//
//    // hover effect
//    createPlayer.setOnMouseEntered(event -> createPlayer.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
//    // go back to regular color
//    createPlayer.setOnMouseExited(event -> createPlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
//
//    createPlayer.setOnAction(event -> handleCreatePlayerButton());
//    return createPlayer;
//  }
//
//  private void handleCreatePlayerButton() {
//    System.out.println("Launch CREATE PLAYER WINDOW");
//  }
//
//  private Button createListPlayersButton() {
//    Button createListPlayers = new Button("LIST PLAYERS");
//    createListPlayers.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
//
//    // hover effect
//    createListPlayers.setOnMouseEntered(event -> createListPlayers.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
//    // go back to regular color
//    createListPlayers.setOnMouseExited(event -> createListPlayers.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
//
//    createListPlayers.setOnAction(event -> handleCreateListPlayersButton());
//    return createListPlayers;
//  }
//
//  private void handleCreateListPlayersButton() {
//    System.out.println("LIST ALL PLAYERS");
//  }
//
//  private Button createListSpecificPlayerButton() {
//    Button createListSpecificPlayer = new Button("LIST SPECIFIC PLAYER BY ID");
//    createListSpecificPlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
//
//    // hover effect
//    createListSpecificPlayer.setOnMouseEntered(event -> createListSpecificPlayer.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
//    // go back to regular color
//    createListSpecificPlayer.setOnMouseExited(event -> createListSpecificPlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
//
//    createListSpecificPlayer.setOnAction(event -> handleCreateListSpecificPlayerButton());
//    return createListSpecificPlayer;
//  }
//
//  private void handleCreateListSpecificPlayerButton() {
//    System.out.println("OPEN NEW WINDOW - SEARCH BY ID");
//  }
//
//  private Button createAddPlayerToTeamButton() {
//    Button createAddPlayerToTeam = new Button("ADD PLAYER TO TEAM");
//    createAddPlayerToTeam.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
//
//    // hover effect
//    createAddPlayerToTeam.setOnMouseEntered(event -> createAddPlayerToTeam.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
//    // go back to regular color
//    createAddPlayerToTeam.setOnMouseExited(event -> createAddPlayerToTeam.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
//
//    createAddPlayerToTeam.setOnAction(event -> handleCreateAddPlayerToTeamButton());
//    return createAddPlayerToTeam;
//  }
//
//  private void handleCreateAddPlayerToTeamButton() {
//    System.out.println("OPEN WINDOW TO CHOOSE WHAT TEAM TO ADD TO BY DORP DOWN MENU");
//  }
//
//  private Button createRemovePlayerFromTeamButton() {
//    Button createRemovePlayerFromTeam = new Button("REMOVE PLAYER FROM TEAM");
//    createRemovePlayerFromTeam.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
//
//    // hover effect
//    createRemovePlayerFromTeam.setOnMouseEntered(event -> createRemovePlayerFromTeam.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
//    // go back to regular color
//    createRemovePlayerFromTeam.setOnMouseExited(event -> createRemovePlayerFromTeam.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
//
//    createRemovePlayerFromTeam.setOnAction(event -> handleCreateRemovePlayerFromTeamButton());
//    return createRemovePlayerFromTeam;
//  }
//
//  private void handleCreateRemovePlayerFromTeamButton() {
//    System.out.println("open new window and choose player from drop down menu");
//  }
//
//  private Button createUpdatePlayerButton() {
//    Button createUpdatePlayer = new Button("REMOVE PLAYER FROM TEAM");
//    createUpdatePlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
//
//    // hover effect
//    createUpdatePlayer.setOnMouseEntered(event -> createUpdatePlayer.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
//    // go back to regular color
//    createUpdatePlayer.setOnMouseExited(event -> createUpdatePlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
//
//    createUpdatePlayer.setOnAction(event -> handleCreateUpdatePlayerButton());
//    return createUpdatePlayer;
//  }
//
//  public void handleCreateUpdatePlayerButton() {
//    System.out.println("New window - choose what player to update");
//  }
//
//  private Button createBackButton() {
//    Button backButton = new Button("BACK");
//    backButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
//
//    // hover effect
//    backButton.setOnMouseEntered(event -> backButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
//    // go back to regular color
//    backButton.setOnMouseExited(event -> backButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
//
//    backButton.setOnAction(event -> handleBackButton());
//    return backButton;
//  }
//
//  private void handleBackButton() {
//    System.out.println("Back to Staff Main Menu");
//
//    // Close the current stage
//    stage.close();
//
//    StaffMainMenu staffMainMenu = new StaffMainMenu(stage, gameController, teamController, playerController, matchController, staffController);
//
//    // Create a new instance of MainWindow and show its stage
//
//    Platform.runLater(() -> {
//      staffMainMenu.showStaffMainMenu();
//    });
//
//
//  }
//
//}