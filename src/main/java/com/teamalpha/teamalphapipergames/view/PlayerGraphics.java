package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Game;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class PlayerGraphics extends Application {

  Stage stage;
  private final GameController gameController;
  private final TeamController teamController;
  private final PlayerController playerController;
  private final MatchController matchController;
  private final StaffController staffController;
  private TournamentController tournamentController;

  // list players table
  public PlayerGraphics(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController, TournamentController tournamentController) {
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
        mainMenu();
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  // SCENES
  // Main Menu
  public void mainMenu() {
    Stage mainMenuStage = new Stage();
    mainMenuStage.setTitle("Players Menu");
    mainMenuStage.setWidth(382);
    mainMenuStage.setHeight(500);

    // Create the TableView
    TableView<Player> allPlayersTable = new TableView<>();

    // Columns, new PropertValueFactory<>("NAME OF VARIABLE IN CLASS");
    TableColumn<Player, Integer> playerIdColumn = new TableColumn<>("Player ID");
    playerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Player, String> playerNicknameColumn = new TableColumn<>("Player Nickname");
    playerNicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickName"));

    TableColumn<Player, String> playerTeamNameColumn = new TableColumn<>("Team");
    playerTeamNameColumn.setCellValueFactory(cellData -> {
      Player player = cellData.getValue();
      if (player.getTeam() != null) {
        return new SimpleStringProperty(player.getTeam().getName());
      } else {
        return new SimpleStringProperty("NONE");
      }
    });

    TableColumn<Player, String> playerGameColumn = new TableColumn<>("Game");
    playerGameColumn.setCellValueFactory(cellData -> {
      Player player = cellData.getValue();
      if (player.getTeam() != null) {
        if (player.getTeam().getGame() != null) {
          return new SimpleStringProperty(player.getTeam().getGame().getName());
        } else {
          return new SimpleStringProperty("NONE");
        }
      } else if (player.getGame() != null) {
        return new SimpleStringProperty(player.getGame().getName());
      } else {
        return new SimpleStringProperty("NONE");
      }
    });

    // Tie columns to allPlayerTable
    allPlayersTable.getColumns().addAll(playerIdColumn, playerNicknameColumn, playerTeamNameColumn, playerGameColumn);

    // Fetch data
    ObservableList<Player> playerData = FXCollections.observableArrayList(playerController.getAll(false));
    allPlayersTable.setItems(playerData);

    // button length
    double buttonWidth = 191;
    // +++++++ update button
    // Create the "UPDATE TABLE" button
    Button updateTableButton = new Button("UPDATE TABLE");
    updateTableButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    updateTableButton.setOnMouseEntered(event -> updateTableButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    updateTableButton.setOnMouseExited(event -> updateTableButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    updateTableButton.setPrefWidth(buttonWidth);

    // Add an event handler to the button to update the table
    updateTableButton.setOnAction(event -> {
      // Fetch data
      ObservableList<Player> updatedPlayerData = FXCollections.observableArrayList(playerController.getAll(false));
      allPlayersTable.setItems(updatedPlayerData);
    });


    // Create player button
    Button createPlayerButton = new Button("CREATE PLAYER");
    createPlayerButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createPlayerButton.setOnMouseEntered(event -> createPlayerButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createPlayerButton.setOnMouseExited(event -> createPlayerButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    createPlayerButton.setMinWidth(buttonWidth);
    createPlayerButton.setMaxWidth(buttonWidth);

    createPlayerButton.setOnAction(event -> {
      createPlayer();
    });

    // Delete player button
    Button deletePlayerButton = new Button("DELETE PLAYER");
    deletePlayerButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    deletePlayerButton.setOnMouseEntered(event -> deletePlayerButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    deletePlayerButton.setOnMouseExited(event -> deletePlayerButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    deletePlayerButton.setMinWidth(buttonWidth);
    deletePlayerButton.setMaxWidth(buttonWidth);

    deletePlayerButton.setOnAction(event -> {
      deletePlayer();
    });

    // Update player button
    Button updatePlayerButton = new Button("EDIT PLAYER");
    updatePlayerButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    updatePlayerButton.setOnMouseEntered(event -> updatePlayerButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    updatePlayerButton.setOnMouseExited(event -> updatePlayerButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    updatePlayerButton.setMinWidth(buttonWidth);
    updatePlayerButton.setMaxWidth(buttonWidth);

    updatePlayerButton.setOnAction(event -> {
      updatePlayer();
    });

    // Add player To Team
    Button addPlayerToTeamButton = new Button("ADD TO TEAM");
    addPlayerToTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    addPlayerToTeamButton.setOnMouseEntered(event -> addPlayerToTeamButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    addPlayerToTeamButton.setOnMouseExited(event -> addPlayerToTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    addPlayerToTeamButton.setMinWidth(buttonWidth);
    addPlayerToTeamButton.setMaxWidth(buttonWidth);

    addPlayerToTeamButton.setOnAction(event -> {
      addPlayerToTeam();
    });

    // Remove player from team button
    Button removePlayerFromTeamButton = new Button("REMOVE FROM TEAM");
    removePlayerFromTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    removePlayerFromTeamButton.setOnMouseEntered(event -> removePlayerFromTeamButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    removePlayerFromTeamButton.setOnMouseExited(event -> removePlayerFromTeamButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    removePlayerFromTeamButton.setMinWidth(buttonWidth);
    removePlayerFromTeamButton.setMaxWidth(buttonWidth);

    removePlayerFromTeamButton.setOnAction(event -> {
      removePlayerFromTeam();
    });

    // Add player To Game
    Button addPlayerToGameButton = new Button("ADD TO GAME");
    addPlayerToGameButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    addPlayerToGameButton.setOnMouseEntered(event -> addPlayerToGameButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    addPlayerToGameButton.setOnMouseExited(event -> addPlayerToGameButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    addPlayerToGameButton.setMinWidth(buttonWidth);
    addPlayerToGameButton.setMaxWidth(buttonWidth);

    addPlayerToGameButton.setOnAction(event -> {
      addPlayerToGame();
    });

    // Remove player from game button
    Button removePlayerFromGameButton = new Button("REMOVE FROM GAME");
    removePlayerFromGameButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    removePlayerFromGameButton.setOnMouseEntered(event -> removePlayerFromGameButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    removePlayerFromGameButton.setOnMouseExited(event -> removePlayerFromGameButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    removePlayerFromGameButton.setMinWidth(buttonWidth);
    removePlayerFromGameButton.setMaxWidth(buttonWidth);

    removePlayerFromGameButton.setOnAction(event -> {
      removePlayerFromGame();
    });

    // Back button
    Button backButton = new Button("BACK");
    backButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    backButton.setOnMouseEntered(event -> backButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    backButton.setOnMouseExited(event -> backButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    backButton.setMinWidth(382);
    backButton.setMaxWidth(382);

    backButton.setOnAction(event -> {
      System.out.println("Back to Staff Main Menu");

      StaffMainMenu staffMainMenu = new StaffMainMenu(gameController, teamController, playerController, matchController, staffController, tournamentController);

      // start stage
      try {
        staffMainMenu.start(stage);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

      mainMenuStage.close();
    });

    // change listview button
    Button listButton = new Button("FILTER LIST");
    listButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    listButton.setOnMouseEntered(event -> listButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    listButton.setOnMouseExited(event -> listButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    listButton.setMinWidth(382);
    listButton.setMaxWidth(382);

    listButton.setOnAction(event -> {
      // what should happen when button is pressed
      changeListView(allPlayersTable);
    });


    // Create an HBox to hold the "UPDATE TABLE" and "CREATE PLAYER" buttons
    HBox buttonsBox1 = new HBox(createPlayerButton, updatePlayerButton);
    buttonsBox1.setAlignment(Pos.CENTER);

    HBox buttonsBox2 = new HBox(addPlayerToTeamButton, removePlayerFromTeamButton);
    buttonsBox2.setAlignment(Pos.CENTER);

    HBox buttonsBox3 = new HBox(addPlayerToGameButton, removePlayerFromGameButton);
    buttonsBox3.setAlignment(Pos.CENTER);

    HBox buttonsBox4 = new HBox(updateTableButton, deletePlayerButton);
    buttonsBox4.setAlignment(Pos.CENTER);

    HBox buttonsBox5 = new HBox(backButton);
    buttonsBox5.setAlignment(Pos.CENTER);

    // Create a VBox to hold the table and the "UPDATE TABLE" and "CREATE PLAYER" buttons
    VBox vBox = new VBox(listButton, allPlayersTable, buttonsBox1, buttonsBox2, buttonsBox3, buttonsBox4, buttonsBox5);
    VBox.setVgrow(allPlayersTable, Priority.ALWAYS);
    vBox.setStyle("-fx-background-color: #174b54;");
    // ----- update button


    // Set the scene to the stage
    Scene listPlayersScene = new Scene(vBox, 400, 400);
    // Load CSS file
    listPlayersScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

    mainMenuStage.setScene(listPlayersScene);
    mainMenuStage.show();
  }

  // Filter list method
  public void changeListView(TableView<Player> allPlayersTable) {
    Stage listViewStage = new Stage();
    listViewStage.setTitle("Filter List");
    listViewStage.setWidth(300);
    listViewStage.setHeight(350);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setSpacing(10);

    Label infoLabel = new Label("Filter list by options");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label viewGamesLabel = new Label("View Games");
    viewGamesLabel.setTextFill(Color.WHITE);

    // Create checkboxes dynamically
    List<CheckBox> checkBoxes = createCheckBoxes();

    // Add checkboxes to the VBox
    vBox.getChildren().addAll(infoLabel, spaceLabel, viewGamesLabel);
    vBox.getChildren().addAll(checkBoxes);

    Button applyButton = new Button("APPLY");
    applyButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    applyButton.setPrefWidth(130);

    AtomicBoolean filteringSuccessful = new AtomicBoolean(false);

    // Add a listener to the applyButton to update checkboxes when it's clicked
    applyButton.setOnAction(event -> {
      // Get the selected game IDs from checkboxes
      List<String> selectedIds = checkBoxes.stream()
          .filter(CheckBox::isSelected)
          .map(CheckBox::getUserData)
          .map(Object::toString)
          .collect(Collectors.toList());

      // Perform filtering based on selected game IDs
      List<Player> filteredPlayers;
      if (selectedIds.isEmpty()) {
        // If no games are selected, show players from all games
        filteredPlayers = playerController.getAll(false);
      } else {
        filteredPlayers = playerController.getAll(false).stream()
            .filter(player -> {
              // Check if the player is directly connected to a game
              boolean connectedToGameDirectly = player.getGame() != null && selectedIds.contains(String.valueOf(player.getGame().getGame_id()));

              // Check if the player is in a team connected to a game
              boolean connectedToGameThroughTeam =
                  player.getTeam() != null &&
                      player.getTeam().getGame() != null &&
                      selectedIds.contains(String.valueOf(player.getTeam().getGame().getGame_id()));

              // Include the player if connected to the game directly or through a team
              return connectedToGameDirectly || connectedToGameThroughTeam;
            })
            .collect(Collectors.toList());
      }

      // Update the provided TableView with filtered player data
      ObservableList<Player> updatedPlayerData = FXCollections.observableArrayList(filteredPlayers);
      allPlayersTable.setItems(updatedPlayerData);

      // Close the filter list window
      listViewStage.close();

      // Set filteringSuccessful to true
      filteringSuccessful.set(true);

      // Update checkboxes after filtering
      updateCheckBoxes(checkBoxes);
    });

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(130);

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(applyButton, closeWindowButton);

    vBox.getChildren().add(buttonBox);

    // add vBox to stackpane
    stackPane.getChildren().add(vBox);

    Scene listScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    listViewStage.setScene(listScene);
    listViewStage.show();

    closeWindowButton.setOnAction(event -> {
      listViewStage.close();
      // Update checkboxes after closing the window
      updateCheckBoxes(checkBoxes);
    });
  }

  // Method to create checkboxes based on the current list of games
  private List<CheckBox> createCheckBoxes() {
    List<Game> games = gameController.getAllGames(false);
    return games.stream()
        .map(game -> {
          CheckBox checkBox = new CheckBox(game.getName());
          checkBox.setUserData(String.valueOf(game.getGame_id()));
          checkBox.setTextFill(Color.WHITE);
          return checkBox;
        })
        .collect(Collectors.toList());
  }

  // Method to update checkboxes based on the current list of games
  private void updateCheckBoxes(List<CheckBox> checkBoxes) {
    List<CheckBox> updatedCheckBoxes = createCheckBoxes();
    checkBoxes.clear();
    checkBoxes.addAll(updatedCheckBoxes);
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
          messageLabel.setText("Player " + firstName + " '" + nickName + "' " + lastName + " was created");
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

  // ADD PLAYER TO GAME
  public void addPlayerToGame() {
    Stage createAddPlayerToGameStage = new Stage();
    createAddPlayerToGameStage.setTitle("Add Player to Game");
    createAddPlayerToGameStage.setWidth(300);
    createAddPlayerToGameStage.setHeight(350);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("Only players without a game");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label choosePlayer = new Label("Choose Player");
    choosePlayer.setTextFill(Color.WHITE);

    ComboBox<Player> playerBox = new ComboBox<>();
    playerBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Player> allPlayers = playerController.getAll(true);
    List<Player> availablePlayers = allPlayers.stream().filter(player -> player.getGame() == null).collect(Collectors.toList());

    ObservableList<Player> playerList = FXCollections.observableArrayList(availablePlayers);
    playerBox.setItems(playerList);

    Label spaceLabel2 = new Label("");

    Label chooseGame = new Label("Choose Game");
    chooseGame.setTextFill(Color.WHITE);

    ComboBox<Game> gameBox = new ComboBox<>();
    gameBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Game> allGames = gameController.getAllGames(true);

    ObservableList<Game> gameList = FXCollections.observableArrayList(allGames);
    gameBox.setItems(gameList);

    Label spaceLabel3 = new Label("");

    Button createAddToGameButton = new Button("ADD TO GAME");
    createAddToGameButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createAddToGameButton.setPrefWidth(130);
    createAddToGameButton.setOnMouseEntered(event -> createAddToGameButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createAddToGameButton.setOnMouseExited(event -> createAddToGameButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(130);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createAddToGameButton, closeWindowButton);

    Label spaceLabel10 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);

    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel, choosePlayer, playerBox, spaceLabel2,
        chooseGame, gameBox, spaceLabel3, buttonBox, spaceLabel10, messageLabel
    );

    stackPane.getChildren().addAll(vBoxCreatePlayer);

    Scene createPlayerScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createAddPlayerToGameStage.setScene(createPlayerScene);
    createAddPlayerToGameStage.show();

    createAddToGameButton.setOnAction(event -> {
      Player selectedPlayer = playerBox.getSelectionModel().getSelectedItem();
      Game selectedGame = gameBox.getSelectionModel().getSelectedItem();

      if (selectedPlayer != null && selectedGame != null) {
        int playerId = selectedPlayer.getId();
        int gameId = selectedGame.getGame_id();

        if (gameController.addPlayerToGame(playerId, gameId)) {
          messageLabel.setText(selectedPlayer.getNickName() + " added to " + selectedGame.getName());
          messageLabel.setTextFill(Color.LIGHTGREEN);
          System.out.println("✅ Player added to Game");

          playerBox.getSelectionModel().clearSelection();
          gameBox.getSelectionModel().clearSelection();

        } else {
          System.out.println("❌ Player failed to be added to Game");
        }
      } else if (selectedPlayer != null && selectedGame == null) {
        messageLabel.setText("Choose a Game");
        messageLabel.setTextFill(Color.RED);
      } else if (selectedPlayer == null && selectedGame != null) {
        messageLabel.setText("Choose a Player");
        messageLabel.setTextFill(Color.RED);
      }

    });

    closeWindowButton.setOnAction(event -> {
      createAddPlayerToGameStage.close();
    });
  }

  // REMOVE PLAYER FROM GAME
  public void removePlayerFromGame() {
    Stage createRemovePlayerFromGameStage = new Stage();
    createRemovePlayerFromGameStage.setTitle("Remove Player from Game");
    createRemovePlayerFromGameStage.setWidth(300);
    createRemovePlayerFromGameStage.setHeight(350);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("Only players who are tied to a game can be selected");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label choosePlayer = new Label("Choose Player");
    choosePlayer.setTextFill(Color.WHITE);

    ComboBox<Player> playerBox = new ComboBox<>();
    playerBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Player> allPlayers = playerController.getAll(true);
    List<Player> availablePlayers = allPlayers.stream().filter(player -> player.getGame() != null).collect(Collectors.toList());

    ObservableList<Player> playerList = FXCollections.observableArrayList(availablePlayers);
    playerBox.setItems(playerList);



    Label spaceLabel2 = new Label("");

    Label gameInformation = new Label();
    gameInformation.setTextFill(Color.WHITE);

    Label spaceLabel3 = new Label("");

    Button createRemovePlayerFromGameButton = new Button("REMOVE");
    createRemovePlayerFromGameButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createRemovePlayerFromGameButton.setPrefWidth(130);
    createRemovePlayerFromGameButton.setOnMouseEntered(event -> createRemovePlayerFromGameButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createRemovePlayerFromGameButton.setOnMouseExited(event -> createRemovePlayerFromGameButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(130);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createRemovePlayerFromGameButton, closeWindowButton);

    Label spaceLabel10 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);


    playerBox.setOnAction(event -> {
      Player selectedPlayerInBox = playerBox.getSelectionModel().getSelectedItem();
      if (selectedPlayerInBox != null) {
        gameInformation.setText(selectedPlayerInBox.getNickName() + " is currently in: " + selectedPlayerInBox.getGame().getName());
      } else {
        gameInformation.setText("Could not fetch game data");
        gameInformation.setTextFill(Color.RED);
      }
    });


    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel, choosePlayer, playerBox, spaceLabel2,
        gameInformation, spaceLabel3, buttonBox, spaceLabel10, messageLabel
    );

    stackPane.getChildren().addAll(vBoxCreatePlayer);

    Scene removePlayerFromGameScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createRemovePlayerFromGameStage.setScene(removePlayerFromGameScene);
    createRemovePlayerFromGameStage.show();

    createRemovePlayerFromGameButton.setOnAction(event -> {
      Player selectedPlayer = playerBox.getSelectionModel().getSelectedItem();

      if (selectedPlayer != null) {
        int playerId = selectedPlayer.getId();
        int gameId = selectedPlayer.getGame().getGame_id();
        String playerGame = selectedPlayer.getGame().getName();

        if (gameController.removePlayerFromGame(playerId, gameId)) {
          messageLabel.setText(selectedPlayer.getNickName() + " removed from " + playerGame);
          messageLabel.setTextFill(Color.LIGHTGREEN);
          System.out.println("✅ Player removed from Game");

          playerBox.getSelectionModel().clearSelection();

        } else {
          System.out.println("❌ Player failed to be removed from Game");
        }
      } else {
        messageLabel.setText("Choose a Player");
        messageLabel.setTextFill(Color.RED);
      }

    });

    closeWindowButton.setOnAction(event -> {
      createRemovePlayerFromGameStage.close();
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
    createAddPlayerToTeamStage.setWidth(330);
    createAddPlayerToTeamStage.setHeight(200);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("Make sure player is teamless & gameless in order to delete");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label("");

    Label choosePlayer = new Label("Choose Player");
    choosePlayer.setTextFill(Color.WHITE);

    ComboBox<Player> playerBox = new ComboBox<>();
    playerBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Player> allPlayers = playerController.getAll(true);
    List<Player> availablePlayers = allPlayers.stream().filter(player -> player.getTeam() == null && player.getGame() == null).collect(Collectors.toList());
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