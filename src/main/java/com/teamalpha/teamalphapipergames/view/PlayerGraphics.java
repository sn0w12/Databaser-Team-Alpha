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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        // Delete player
        Button deletePlayer = createDeletePlayerButton();
        deletePlayer.setOnAction(event -> handleDeletePlayerButton());

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
        deletePlayer.setPrefWidth(preferredWidth);
        backButton.setPrefWidth(preferredWidth);

        // Create a vertical layout for buttons
        VBox buttonLayout = new VBox(10); // 10 is the spacing between buttons
        buttonLayout.getChildren().addAll(createPlayer, listPlayers, listSpecificPlayer, addPlayerToTeam, removePlayerFromTeam, updatePlayer, deletePlayer, backButton);
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
    createPlayer();
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
    System.out.println("Player Graphics > button");
    listPlayers();
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
    System.out.println("Player Graphics > button");
    addPlayerToTeam();
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
    System.out.println("Player Graphics > button");
    removePlayerFromTeam();
  }

  private Button createUpdatePlayerButton() {
    Button createUpdatePlayer = new Button("UPDATE PLAYER");
    createUpdatePlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    createUpdatePlayer.setOnMouseEntered(event -> createUpdatePlayer.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    createUpdatePlayer.setOnMouseExited(event -> createUpdatePlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    createUpdatePlayer.setOnAction(event -> handleCreateUpdatePlayerButton());
    return createUpdatePlayer;
  }

  public void handleCreateUpdatePlayerButton() {
    System.out.println("Player Graphics > button");
    updatePlayer();
  }

  private Button createDeletePlayerButton() {
    Button createDeletePlayer = new Button("DELETE PLAYER");
    createDeletePlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    createDeletePlayer.setOnMouseEntered(event -> createDeletePlayer.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    createDeletePlayer.setOnMouseExited(event -> createDeletePlayer.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    createDeletePlayer.setOnAction(event -> handleDeletePlayerButton());
    return createDeletePlayer;
  }

  public void handleDeletePlayerButton() {
    System.out.println("Player Graphics -> button");
    deletePlayer();
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

  // CREATE PLAYER
  public void createPlayer() {
    Stage createPlayerStage = new Stage();
    createPlayerStage.setTitle("Create new Player");
    createPlayerStage.setWidth(500);
    createPlayerStage.setHeight(800);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("(*) are mandatory textfields");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label firstNameLabel = new Label("Input first name(*)");
    firstNameLabel.setTextFill(Color.WHITE);
    TextField firstNameTextField = new TextField();
    firstNameTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    firstNameTextField.setMaxWidth(200);

    Label spaceLabel2 = new Label("");

    Label lastNameLabel = new Label("Input last name(*)");
    lastNameLabel.setTextFill(Color.WHITE);
    TextField lastNameTextField = new TextField();
    lastNameTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    lastNameTextField.setMaxWidth(200);

    Label spaceLabel3 = new Label("");

    Label nickNameLabel = new Label("Input nickname(*)");
    nickNameLabel.setTextFill(Color.WHITE);
    TextField nickNameTextField = new TextField();
    nickNameTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    nickNameTextField.setMaxWidth(200);

    Label spaceLabel4 = new Label("");

    Label addressLabel = new Label("Input address");
    addressLabel.setTextFill(Color.WHITE);
    TextField addressTextField = new TextField();
    addressTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    addressTextField.setMaxWidth(200);

    Label spaceLabel5 = new Label("");

    Label zipCopeLabel = new Label("Input zip code");
    zipCopeLabel.setTextFill(Color.WHITE);
    TextField zipCodeTextField = new TextField();
    zipCodeTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    zipCodeTextField.setMaxWidth(200);

    Label spaceLabel6 = new Label("");

    Label postalAddressLabel = new Label("Input postal address");
    postalAddressLabel.setTextFill(Color.WHITE);
    TextField postalAddressTextField = new TextField();
    postalAddressTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    postalAddressTextField.setMaxWidth(200);

    Label spaceLabel7 = new Label("");

    Label countryLabel = new Label("Input country");
    countryLabel.setTextFill(Color.WHITE);
    TextField countryTextField = new TextField();
    countryTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    countryTextField.setMaxWidth(200);

    Label spaceLabel8 = new Label("");

    Label eMailLabel = new Label("Input email");
    eMailLabel.setTextFill(Color.WHITE);
    TextField eMailTextField = new TextField();
    eMailTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    eMailTextField.setMaxWidth(200);

    Label spaceLabel9 = new Label("");

    Button createPlayerButton = new Button("CREATE");
    createPlayerButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createPlayerButton.setPrefWidth(95);
    createPlayerButton.setOnMouseEntered(event -> createPlayerButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createPlayerButton.setOnMouseExited(event -> createPlayerButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(95);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createPlayerButton, closeWindowButton);

    Label spaceLabel10 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);

    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel, firstNameLabel, firstNameTextField,
        spaceLabel2, lastNameLabel, lastNameTextField, spaceLabel3, nickNameLabel, nickNameTextField,
        spaceLabel4, addressLabel, addressTextField, spaceLabel5, zipCopeLabel, zipCodeTextField,
        spaceLabel6, postalAddressLabel, postalAddressTextField, spaceLabel7, countryLabel,
        countryTextField, spaceLabel8, eMailLabel, eMailTextField, spaceLabel9, buttonBox,
        spaceLabel10, messageLabel
    );

    stackPane.getChildren().addAll(vBoxCreatePlayer);

    Scene createPlayerScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createPlayerStage.setScene(createPlayerScene);
    createPlayerStage.show();

    createPlayerButton.setOnAction(event -> {
      String firstName;
      String lastName;
      String nickName;
      String address;
      String zipCode;
      String postalAddress;
      String country;
      String eMail;

      try {
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();
        nickName = nickNameTextField.getText();
        address = addressTextField.getText();
        zipCode = zipCodeTextField.getText();
        postalAddress = postalAddressTextField.getText();
        country = countryTextField.getText();
        eMail = eMailTextField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || nickName.isEmpty()) {
          messageLabel.setText("You have to fill in the required text fields");
          messageLabel.setTextFill(Color.RED);
        } else {
          playerController.save(new Player(firstName, lastName, nickName, address, zipCode, postalAddress, country, eMail));
          messageLabel.setText("Player " + firstName + " '" + nickName + "' " + lastName + " was created. Create a new player or close the window");
          messageLabel.setTextFill(Color.LIGHTGREEN);
          firstNameTextField.clear();
          lastNameTextField.clear();
          nickNameTextField.clear();
          addressTextField.clear();
          zipCodeTextField.clear();
          postalAddressTextField.clear();
          countryTextField.clear();
          eMailTextField.clear();
        }

      } catch (Exception e) {
        System.out.println("Could not create Player");
        messageLabel.setText("Failed to create new Player");
      }

    });

    closeWindowButton.setOnAction(events -> {
      createPlayerStage.close();
    });
  }

  // LIST ALL PLAYERS
  public void listPlayers() {

  }

  // List specific player by ID (more info)
  public void listSpecificPlayer() {

  }

  // ADD PLAYER TO TEAM
  public void addPlayerToTeam() {
    Stage createAddPlayerToTeamStage = new Stage();
    createAddPlayerToTeamStage.setTitle("Add Player to Team");
    createAddPlayerToTeamStage.setWidth(300);
    createAddPlayerToTeamStage.setHeight(350);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("Only players without a team are shown");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label choosePlayer = new Label("Choose Player");
    choosePlayer.setTextFill(Color.WHITE);

    ComboBox<Player> playerBox = new ComboBox<>();
    playerBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Player> allPlayers = playerController.getAll(true);
    List<Player> availablePlayers = allPlayers.stream().filter(player -> player.getTeam() == null).collect(Collectors.toList());

    ObservableList<Player> playerList = FXCollections.observableArrayList(availablePlayers);
    playerBox.setItems(playerList);

    Label spaceLabel2 = new Label("");

    Label chooseTeam = new Label("Choose Team");
    chooseTeam.setTextFill(Color.WHITE);

    ComboBox<Team> teamBox = new ComboBox<>();
    teamBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Team> allTeams = teamController.getAll(true);

    ObservableList<Team> teamList = FXCollections.observableArrayList(allTeams);
    teamBox.setItems(teamList);

    Label spaceLabel3 = new Label("");

    Button createAddToTeamButton = new Button("ADD TO TEAM");
    createAddToTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createAddToTeamButton.setPrefWidth(130);
    createAddToTeamButton.setOnMouseEntered(event -> createAddToTeamButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createAddToTeamButton.setOnMouseExited(event -> createAddToTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(130);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createAddToTeamButton, closeWindowButton);

    Label spaceLabel10 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);

    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel, choosePlayer, playerBox, spaceLabel2,
        chooseTeam, teamBox, spaceLabel3, buttonBox, spaceLabel10, messageLabel
    );

    stackPane.getChildren().addAll(vBoxCreatePlayer);

    Scene createPlayerScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createAddPlayerToTeamStage.setScene(createPlayerScene);
    createAddPlayerToTeamStage.show();

    createAddToTeamButton.setOnAction(event -> {
      Player selectedPlayer = playerBox.getSelectionModel().getSelectedItem();
      Team selectedTeam = teamBox.getSelectionModel().getSelectedItem();

      if (selectedPlayer != null && selectedTeam != null) {
        int playerId = selectedPlayer.getId();
        int teamId = selectedTeam.getId();

        if (teamController.addPlayerToTeam(playerId, teamId)) {
          messageLabel.setText(selectedPlayer.getNickName() + " added to " + selectedTeam.getName());
          messageLabel.setTextFill(Color.LIGHTGREEN);
          System.out.println("✅ Player added to Team");

          playerBox.getSelectionModel().clearSelection();
          teamBox.getSelectionModel().clearSelection();

        } else {
          System.out.println("❌ Player failed to add to Team");
        }
      } else if (selectedPlayer != null && selectedTeam == null) {
        messageLabel.setText("Choose a Team");
        messageLabel.setTextFill(Color.RED);
      } else if (selectedPlayer == null && selectedTeam != null) {
        messageLabel.setText("Choose a Player");
        messageLabel.setTextFill(Color.RED);
      }

    });

    closeWindowButton.setOnAction(event -> {
      createAddPlayerToTeamStage.close();
    });
  }

  // REMOVE PLAYER FROM TEAM
  public void removePlayerFromTeam() {
    Stage createAddPlayerToTeamStage = new Stage();
    createAddPlayerToTeamStage.setTitle("Remove Player from Team");
    createAddPlayerToTeamStage.setWidth(300);
    createAddPlayerToTeamStage.setHeight(350);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("Only players who are in a team can be selected");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label choosePlayer = new Label("Choose Player");
    choosePlayer.setTextFill(Color.WHITE);

    ComboBox<Player> playerBox = new ComboBox<>();
    playerBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Player> allPlayers = playerController.getAll(true);
    List<Player> availablePlayers = allPlayers.stream().filter(player -> player.getTeam() != null).collect(Collectors.toList());

    ObservableList<Player> playerList = FXCollections.observableArrayList(availablePlayers);
    playerBox.setItems(playerList);



    Label spaceLabel2 = new Label("");

    Label teamInformation = new Label();
    teamInformation.setTextFill(Color.WHITE);

    Label spaceLabel3 = new Label("");

    Button createAddToTeamButton = new Button("REMOVE");
    createAddToTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createAddToTeamButton.setPrefWidth(130);
    createAddToTeamButton.setOnMouseEntered(event -> createAddToTeamButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createAddToTeamButton.setOnMouseExited(event -> createAddToTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(130);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createAddToTeamButton, closeWindowButton);

    Label spaceLabel10 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);


    playerBox.setOnAction(event -> {
          Player selectedPlayerInBox = playerBox.getSelectionModel().getSelectedItem();
          if (selectedPlayerInBox != null) {
            teamInformation.setText(selectedPlayerInBox.getNickName() + " is currently in: " + selectedPlayerInBox.getTeam().getName());
          } else {
            teamInformation.setText("Could not fetch team data");
            teamInformation.setTextFill(Color.RED);
          }
        });


    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel, choosePlayer, playerBox, spaceLabel2,
        teamInformation, spaceLabel3, buttonBox, spaceLabel10, messageLabel
    );

    stackPane.getChildren().addAll(vBoxCreatePlayer);

    Scene createPlayerScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createAddPlayerToTeamStage.setScene(createPlayerScene);
    createAddPlayerToTeamStage.show();

    createAddToTeamButton.setOnAction(event -> {
      Player selectedPlayer = playerBox.getSelectionModel().getSelectedItem();

      if (selectedPlayer != null) {
        int playerId = selectedPlayer.getId();
        int teamId = selectedPlayer.getTeam().getId();
        String playerTeam = selectedPlayer.getTeam().getName();

        if (teamController.removePlayerFromTeam(playerId, teamId)) {
          messageLabel.setText(selectedPlayer.getNickName() + " removed from " + playerTeam);
          messageLabel.setTextFill(Color.LIGHTGREEN);
          System.out.println("✅ Player removed from Team");

          playerBox.getSelectionModel().clearSelection();

        } else {
          System.out.println("❌ Player failed to add to Team");
        }
      } else {
        messageLabel.setText("Choose a Player");
        messageLabel.setTextFill(Color.RED);
      }

    });

    closeWindowButton.setOnAction(event -> {
      createAddPlayerToTeamStage.close();
    });
  }

  // UPDATE PLAYER
  public void updatePlayer() {
    Stage createUpdatePlayerStage = new Stage();
    createUpdatePlayerStage.setTitle("Update Player Information");
    createUpdatePlayerStage.setWidth(500);
    createUpdatePlayerStage.setHeight(800);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("Choose Player to update information");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label choosePlayer = new Label("Choose Player");
    choosePlayer.setTextFill(Color.WHITE);

    ComboBox<Player> playerBox = new ComboBox<>();
    playerBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Player> allPlayers = playerController.getAll(true);

    ObservableList<Player> playerList = FXCollections.observableArrayList(allPlayers);
    playerBox.setItems(playerList);



    Label spaceLabel2 = new Label("");

    Label firstNameLabel = new Label("Update First Name");
    firstNameLabel.setTextFill(Color.WHITE);
    TextField firstNameTextField = new TextField();
    firstNameTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    firstNameTextField.setMaxWidth(200);

    Label spaceLabel3 = new Label("");

    Label lastNameLabel = new Label("Update Last Name");
    lastNameLabel.setTextFill(Color.WHITE);
    TextField lastNameTextField = new TextField();
    lastNameTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    lastNameTextField.setMaxWidth(200);

    Label spaceLabel4 = new Label("");

    Label nickNameLabel = new Label("Update Nick Name");
    nickNameLabel.setTextFill(Color.WHITE);
    TextField nickNameTextField = new TextField();
    nickNameTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    nickNameTextField.setMaxWidth(200);

    Label spaceLabel5 = new Label("");

    Label addressLabel = new Label("Update Address");
    addressLabel.setTextFill(Color.WHITE);
    TextField addressTextField = new TextField();
    addressTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    addressTextField.setMaxWidth(200);

    Label spaceLabel6 = new Label("");

    Label zipCodeLabel = new Label("Update Zip Code");
    zipCodeLabel.setTextFill(Color.WHITE);
    TextField zipCodeTextField = new TextField();
    zipCodeTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    zipCodeTextField.setMaxWidth(200);

    Label spaceLabel7 = new Label("");

    Label postalAddressLabel = new Label("Update Postal Address");
    postalAddressLabel.setTextFill(Color.WHITE);
    TextField postalAddressTextField = new TextField();
    postalAddressTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    postalAddressTextField.setMaxWidth(200);


    Label spaceLabel8 = new Label("");

    Label countryLabel = new Label("Update Country");
    countryLabel.setTextFill(Color.WHITE);
    TextField countryTextField = new TextField();
    countryTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    countryTextField.setMaxWidth(200);


    Label spaceLabel9 = new Label("");

    Label emailLabel = new Label("Update eMail");
    emailLabel.setTextFill(Color.WHITE);
    TextField emailTextField = new TextField();
    emailTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    emailTextField.setMaxWidth(200);

    Label spaceLabel10 = new Label("");

    Button createUpdateButton = new Button("UPDATE");
    createUpdateButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createUpdateButton.setPrefWidth(130);
    createUpdateButton.setOnMouseEntered(event -> createUpdateButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createUpdateButton.setOnMouseExited(event -> createUpdateButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(130);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createUpdateButton, closeWindowButton);

    Label spaceLabel11 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);

    playerBox.setOnAction(event -> {
      Player selectedPlayerInBox = playerBox.getSelectionModel().getSelectedItem();
      if (selectedPlayerInBox != null) {
        firstNameTextField.setText(selectedPlayerInBox.getFirstName());
        lastNameTextField.setText(selectedPlayerInBox.getLastName());
        nickNameTextField.setText(selectedPlayerInBox.getNickName());
        addressTextField.setText(selectedPlayerInBox.getAddress());
        zipCodeTextField.setText(selectedPlayerInBox.getZipCode());
        postalAddressTextField.setText(selectedPlayerInBox.getPostalAddress());
        countryTextField.setText(selectedPlayerInBox.getCountry());
        emailTextField.setText(selectedPlayerInBox.geteMail());
      } else {
        messageLabel.setText("Could not fetch data");
        messageLabel.setTextFill(Color.RED);
      }

    });

    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel, choosePlayer, playerBox, spaceLabel2,
        firstNameLabel, firstNameTextField, spaceLabel3,
        lastNameLabel, lastNameTextField, spaceLabel4,
        nickNameLabel, nickNameTextField, spaceLabel5,
        addressLabel, addressTextField, spaceLabel6,
        zipCodeLabel, zipCodeTextField, spaceLabel7,
        postalAddressLabel, postalAddressTextField, spaceLabel8,
        countryLabel, countryTextField, spaceLabel9,
        emailLabel, emailTextField, spaceLabel10,
        buttonBox, spaceLabel11, messageLabel
    );

    stackPane.getChildren().addAll(vBoxCreatePlayer);

    Scene createUpdatePlayerScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createUpdatePlayerStage.setScene(createUpdatePlayerScene);
    createUpdatePlayerStage.show();

    createUpdateButton.setOnAction(event -> {
      Player selectedPlayer = playerBox.getSelectionModel().getSelectedItem();

      if (selectedPlayer != null) {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String nickName = nickNameTextField.getText();
        String address = addressTextField.getText();
        String zipCode = zipCodeTextField.getText();
        String postalAddress = postalAddressTextField.getText();
        String country = countryTextField.getText();
        String eMail = emailTextField.getText();

        int playerIdToUpdate = playerBox.getSelectionModel().getSelectedItem().getId();
        System.out.println("playerIdToUpdate: " + playerIdToUpdate);
        Player managedPlayer = playerController.getPlayerById(playerIdToUpdate);

        if (managedPlayer != null) {
          managedPlayer.setFirstName(firstName);
          managedPlayer.setLastName(lastName);
          managedPlayer.setNickName(nickName);
          managedPlayer.setAddress(address);
          managedPlayer.setZipCode(zipCode);
          managedPlayer.setPostalAddress(postalAddress);
          managedPlayer.setCountry(country);
          managedPlayer.seteMail(eMail);

          if (playerController.updatePlayer(managedPlayer)) {
            messageLabel.setText("Player updated");
            messageLabel.setTextFill(Color.LIGHTGREEN);
            System.out.println("✅ Player updated");

            System.out.println("Nickname should have changed to: " + selectedPlayer.getNickName());

            firstNameTextField.clear();
            lastNameTextField.clear();
            nickNameTextField.clear();
            addressTextField.clear();
            zipCodeTextField.clear();
            postalAddressTextField.clear();
            countryTextField.clear();
            emailTextField.clear();
          } else {
            messageLabel.setText("Player failed to be updated");
            messageLabel.setTextFill(Color.RED);
            System.out.println("❌ Player update failed");
          }


        } else {
          System.out.println("player not found");
        }
      } else {
        messageLabel.setText("Choose a Player in order to update");
        messageLabel.setTextFill(Color.RED);
      }

    });

    closeWindowButton.setOnAction(event -> {
      createUpdatePlayerStage.close();
    });


  }

  // DELETE PLAYER
  public void deletePlayer() {
    Stage createAddPlayerToTeamStage = new Stage();
    createAddPlayerToTeamStage.setTitle("Delete Player");
    createAddPlayerToTeamStage.setWidth(300);
    createAddPlayerToTeamStage.setHeight(200);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("Make sure player is teamless in order to delete");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label choosePlayer = new Label("Choose Player");
    choosePlayer.setTextFill(Color.WHITE);

    ComboBox<Player> playerBox = new ComboBox<>();
    playerBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Player> allPlayers = playerController.getAll(true);
    List<Player> availablePlayers = allPlayers.stream().filter(player -> player.getTeam() == null).collect(Collectors.toList());
    ObservableList<Player> playerList = FXCollections.observableArrayList(availablePlayers);
    playerBox.setItems(playerList);

    Label spaceLabel2 = new Label("");

    Button createAddToTeamButton = new Button("DELETE");
    createAddToTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createAddToTeamButton.setPrefWidth(130);
    createAddToTeamButton.setOnMouseEntered(event -> createAddToTeamButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createAddToTeamButton.setOnMouseExited(event -> createAddToTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(130);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createAddToTeamButton, closeWindowButton);

    Label spaceLabel10 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);


    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel, choosePlayer, playerBox, spaceLabel2,
        buttonBox, spaceLabel10, messageLabel
    );

    stackPane.getChildren().addAll(vBoxCreatePlayer);

    Scene createPlayerScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createAddPlayerToTeamStage.setScene(createPlayerScene);
    createAddPlayerToTeamStage.show();

    createAddToTeamButton.setOnAction(event -> {
      Player selectedPlayer = playerBox.getSelectionModel().getSelectedItem();

      if (selectedPlayer != null) {
        int playerId = selectedPlayer.getId();

        if (playerController.deletePlayerById(playerId)) {
          messageLabel.setText(selectedPlayer.getNickName() + " deleted");
          messageLabel.setTextFill(Color.LIGHTGREEN);
          System.out.println("✅ Player deleted");

          playerBox.getSelectionModel().clearSelection();

        } else {
          messageLabel.setText("Failed to delete. Remove player from current team");
          messageLabel.setTextFill(Color.RED);
          System.out.println("❌ Player failed to be deleted");
        }
      } else {
        messageLabel.setText("Choose a Player");
        messageLabel.setTextFill(Color.RED);
      }

    });

    closeWindowButton.setOnAction(event -> {
      createAddPlayerToTeamStage.close();
    });
  }
}