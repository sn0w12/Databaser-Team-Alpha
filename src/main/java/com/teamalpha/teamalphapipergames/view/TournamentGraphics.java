package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Game;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;
import com.teamalpha.teamalphapipergames.model.Tournament;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class TournamentGraphics extends Application {

  Stage stage;
  private GameController gameController;
  private TeamController teamController;
  private PlayerController playerController;
  private MatchController matchController;
  private StaffController staffController;
  private TournamentController tournamentController;

  public TournamentGraphics(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController, TournamentController tournamentController) {
    this.gameController = gameController;
    this.teamController = teamController;
    this.playerController = playerController;
    this.matchController = matchController;
    this.staffController = staffController;
    this.tournamentController = tournamentController;
  }

  @Override
  public void start(Stage tournamentStage) throws Exception {
    try {
      this.stage = tournamentStage;
      Platform.runLater(() -> {
        tournamentMainWindow();
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void tournamentMainWindow() {
    Stage mainMenuStage = new Stage();
    mainMenuStage.setTitle("Tournaments Menu");
    mainMenuStage.setWidth(322);
    mainMenuStage.setHeight(500);

    // Create the TableView
    TableView<Tournament> allTournamentsTable = new TableView<>();

    TableColumn<Tournament, Integer> tournamentIdColumn = new TableColumn<>("ID");
    tournamentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Tournament, String> tournamentTitleColumn = new TableColumn<>("Title");
    tournamentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<Tournament, Integer> tournamentContestants = new TableColumn<>("Contestants");
    tournamentContestants.setCellValueFactory(new PropertyValueFactory<>("contestants"));

    TableColumn<Tournament, String> tournamentGameColumn = new TableColumn<>("Game");
    tournamentGameColumn.setCellValueFactory(cellData -> {
      Tournament tournament = cellData.getValue();
      if (tournament.getGame() != null) {
        return new SimpleStringProperty(tournament.getGame().getName());
      }
      return new SimpleStringProperty("NONE");
    });

    allTournamentsTable.getColumns().addAll(tournamentIdColumn, tournamentTitleColumn, tournamentContestants, tournamentGameColumn);

    // Fetch data
    ObservableList<Tournament> tournamentData = FXCollections.observableArrayList(tournamentController.getAll(false));
    allTournamentsTable.setItems(tournamentData);

    // BUTTONS ++
    // button length
    double buttonWidth = 161;

    // Create the "UPDATE TABLE" button
    Button updateTableButton = new Button("UPDATE TABLE");
    updateTableButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    updateTableButton.setOnMouseEntered(event -> updateTableButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    updateTableButton.setOnMouseExited(event -> updateTableButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    updateTableButton.setPrefWidth(buttonWidth);
    updateTableButton.setAlignment(Pos.CENTER);

    // Add an event handler to the button to update the table
    updateTableButton.setOnAction(event -> {
      // Fetch data
      ObservableList<Tournament> updatedTournamentData = FXCollections.observableArrayList(tournamentController.getAll(false));
      allTournamentsTable.setItems(updatedTournamentData);
    });

    // Create tournament button
    Button createTournamentButton = new Button("CREATE");
    createTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createTournamentButton.setOnMouseEntered(event -> createTournamentButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createTournamentButton.setOnMouseExited(event -> createTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    createTournamentButton.setMinWidth(buttonWidth);
    createTournamentButton.setMaxWidth(buttonWidth);
    createTournamentButton.setAlignment(Pos.CENTER);

    createTournamentButton.setOnAction(event -> {
      createTournament();
    });

    // Add score to tournament button
    Button addScoreToTournamentButton = new Button("ADD SCORE");
    addScoreToTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    addScoreToTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    addScoreToTournamentButton.setOnMouseEntered(event -> addScoreToTournamentButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    addScoreToTournamentButton.setOnMouseExited(event -> addScoreToTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    addScoreToTournamentButton.setMinWidth(buttonWidth);
    addScoreToTournamentButton.setMaxWidth(buttonWidth);
    addScoreToTournamentButton.setAlignment(Pos.CENTER);

    addScoreToTournamentButton.setOnAction(event -> {
      System.out.println("ADD SCORE PRESSED");
      addScoreToTournament();
    });

    // Show Tournament
    Button showTournamentButton = new Button("SHOW TOURNAMENT");
    showTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    showTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    showTournamentButton.setOnMouseEntered(event -> showTournamentButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    showTournamentButton.setOnMouseExited(event -> showTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    showTournamentButton.setMinWidth(buttonWidth);
    showTournamentButton.setMaxWidth(buttonWidth);
    showTournamentButton.setAlignment(Pos.CENTER);

    showTournamentButton.setOnAction(event -> {
//      showTournament();
    });

    // Delete tournament button
    Button deleteTournamentButton = new Button("DELETE");
    deleteTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    deleteTournamentButton.setOnMouseEntered(event -> deleteTournamentButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    deleteTournamentButton.setOnMouseExited(event -> deleteTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    deleteTournamentButton.setMinWidth(buttonWidth);
    deleteTournamentButton.setMaxWidth(buttonWidth);
    deleteTournamentButton.setAlignment(Pos.CENTER);

    deleteTournamentButton.setOnAction(event -> {
//      deleteTournament();
    });

    // Update tournament button
    Button updateTournamentButton = new Button("EDIT");
    updateTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    updateTournamentButton.setOnMouseEntered(event -> updateTournamentButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    updateTournamentButton.setOnMouseExited(event -> updateTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    updateTournamentButton.setMinWidth(buttonWidth);
    updateTournamentButton.setMaxWidth(buttonWidth);
    updateTournamentButton.setAlignment(Pos.CENTER);

    updateTournamentButton.setOnAction(event -> {
//      updateTournament();
    });

    // Back button
    Button backButton = new Button("BACK");
    backButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    backButton.setOnMouseEntered(event -> backButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    backButton.setOnMouseExited(event -> backButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    backButton.setMinWidth(322);
    backButton.setMaxWidth(322);
    backButton.setAlignment(Pos.CENTER);

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
    listButton.setMinWidth(322);
    listButton.setMaxWidth(322);
    listButton.setAlignment(Pos.CENTER);

    listButton.setOnAction(event -> {
      // what should happen when button is pressed
      changeListView(allTournamentsTable);
    });
    // BUTTONS --

    // Create an HBox to hold the "UPDATE TABLE" and "CREATE PLAYER" buttons
    HBox buttonsBox1 = new HBox(createTournamentButton, updateTournamentButton);
    buttonsBox1.setAlignment(Pos.CENTER);

    HBox buttonsBox2 = new HBox(addScoreToTournamentButton, showTournamentButton);
    buttonsBox2.setAlignment(Pos.CENTER);

    HBox buttonsBox3 = new HBox(updateTableButton, deleteTournamentButton);
    buttonsBox2.setAlignment(Pos.CENTER);

    HBox buttonsBox4 = new HBox(backButton);
    buttonsBox4.setAlignment(Pos.CENTER);

    // Create a VBox to hold the table and the "UPDATE TABLE" and "CREATE PLAYER" buttons
    VBox vBox = new VBox(listButton, allTournamentsTable, buttonsBox1, buttonsBox2, buttonsBox3, buttonsBox4);
    VBox.setVgrow(allTournamentsTable, Priority.ALWAYS);
    vBox.setStyle("-fx-background-color: #174b54;");

    // Set the scene to the stage
    Scene listTournamentsScene = new Scene(vBox, 400, 400);
    // Load CSS file
    listTournamentsScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

    mainMenuStage.setScene(listTournamentsScene);
    mainMenuStage.show();
  }

  // Filter list method
  public void changeListView(TableView<Tournament> allTournamentsTable) {
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
    List<CheckBox> gameCheckBoxes = createCheckBoxesForGame();

    // Add checkboxes to the VBox
    vBox.getChildren().addAll(infoLabel, spaceLabel, viewGamesLabel);
    vBox.getChildren().addAll(gameCheckBoxes);

    Button applyButton = new Button("APPLY");
    applyButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    applyButton.setPrefWidth(130);
    applyButton.setOnMouseEntered(event -> applyButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    applyButton.setOnMouseExited(event -> applyButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    AtomicBoolean filteringSuccessful = new AtomicBoolean(false);

    // Add a listener to the applyButton to update checkboxes when it's clicked
    applyButton.setOnAction(event -> {
      // Get the selected game IDs from checkboxes
      List<String> selectedIds = gameCheckBoxes.stream()
          .filter(CheckBox::isSelected)
          .map(CheckBox::getUserData)
          .map(Object::toString)
          .collect(Collectors.toList());

      // Perform filtering based on selected game IDs
      List<Tournament> filteredTournaments;
      if (selectedIds.isEmpty()) {
        // If no games are selected, show players from all games
        filteredTournaments = tournamentController.getAll(false);
      } else {
        filteredTournaments = tournamentController.getAll(false).stream()
            .filter(tournament -> {
              // Check if the player is directly connected to a game
              boolean connectedToGameDirectly = tournament.getGame() != null && selectedIds.contains(String.valueOf(tournament.getGame().getGame_id()));

              // Include the player if connected to the game directly or through a team
              return connectedToGameDirectly;
            })
            .collect(Collectors.toList());
      }

      // Update the provided TableView with filtered player data
      ObservableList<Tournament> updatedTournamentData = FXCollections.observableArrayList(filteredTournaments);
      allTournamentsTable.setItems(updatedTournamentData);

      // Close the filter list window
      listViewStage.close();

      // Set filteringSuccessful to true
      filteringSuccessful.set(true);

      // Update checkboxes after filtering
      updateCheckBoxes(gameCheckBoxes);
    });

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(130);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    closeWindowButton.setOnAction(event -> {
      listViewStage.close();
      // Update checkboxes after closing the window
      updateCheckBoxes(gameCheckBoxes);
    });

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
  }

  private List<CheckBox> createCheckBoxesForGame() {
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

  private void updateCheckBoxes(List<CheckBox> checkBoxes) {
    List<CheckBox> updatedCheckBoxes = createCheckBoxesForGame();
    checkBoxes.clear();
    checkBoxes.addAll(updatedCheckBoxes);
  }

  // klar
  public void createTournament() {
    Stage createTournamentStage = new Stage();
    createTournamentStage.setTitle("Create new Tournament");
    createTournamentStage.setWidth(300);
    createTournamentStage.setHeight(350);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxCreatePlayer = new VBox();
    vBoxCreatePlayer.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("(*) are mandatory fields/boxes");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label();

    Label titleLabel = new Label("Input title(*)");
    titleLabel.setTextFill(Color.WHITE);
    TextField titleTextField = new TextField();
    titleTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    titleTextField.setMaxWidth(200);

    Label spaceLabel2 = new Label();

    Label contestantLabel = new Label("Amount of contestants(*)");
    contestantLabel.setTextFill(Color.WHITE);
    ObservableList<Integer> contestantOptions = FXCollections.observableArrayList(2, 4, 8);
    ComboBox<Integer> nrOfContestants = new ComboBox(contestantOptions); // 2, 4, or 8

    Label spaceLabel3 = new Label();

    Button createTournamentButton = new Button("NEXT");
    createTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createTournamentButton.setPrefWidth(95);
    createTournamentButton.setOnMouseEntered(event -> createTournamentButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createTournamentButton.setOnMouseExited(event -> createTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(95);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createTournamentButton, closeWindowButton);

    Label spaceLabel5 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);

    VBox dynamicComboBoxesBox = new VBox();
    dynamicComboBoxesBox.setAlignment(Pos.CENTER);

    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel,
        titleLabel, titleTextField,
        spaceLabel2, contestantLabel,
        nrOfContestants, spaceLabel3,
        buttonBox, spaceLabel5,
        messageLabel
    );


    stackPane.getChildren().addAll(vBoxCreatePlayer);

    Scene createTournamentScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createTournamentStage.setScene(createTournamentScene);
    createTournamentStage.show();

    closeWindowButton.setOnAction(event -> {
      createTournamentStage.close();
    });

    createTournamentButton.setOnAction(event -> {
      String title = titleTextField.getText();
      Integer selectedValue = nrOfContestants.getSelectionModel().getSelectedItem();

      if (selectedValue == null) {
        messageLabel.setText("Please select the number of contestants.");
        messageLabel.setTextFill(Color.RED);
        return;  // Exit the method early, as the user needs to correct the input.
      }

      int contestants = selectedValue.intValue();

      try {
        if (title.isEmpty()) {
          messageLabel.setText("You have to fill in the required textfield");
          messageLabel.setTextFill(Color.RED);
        } else {
          Tournament savedTournament = new Tournament(title, contestants);
          tournamentController.save(savedTournament, contestants);
          messageLabel.setText("Tournament: " + title + " with " + contestants + " contestants was created");
          messageLabel.setTextFill(Color.LIGHTGREEN);
          titleTextField.clear();
          nrOfContestants.getSelectionModel().clearSelection();

          // Close window
          createTournamentStage.close();

          // Open next
          addTournamentToGame(savedTournament);
        }
      } catch (IllegalArgumentException e) {
        // Handle specific exception related to invalid arguments
        messageLabel.setText("Invalid argument: " + e.getMessage());
        messageLabel.setTextFill(Color.RED);
      } catch (PersistenceException e) {
        // Handle specific exception related to persistence (database) issues
        messageLabel.setText("Error saving tournament to the database.");
        messageLabel.setTextFill(Color.RED);
      } catch (Exception e) {
        // Handle other exceptions
        System.out.println("Could not create Tournament");
        messageLabel.setText("Failed to create new Tournament");
      }
    });

  }

  public void addTournamentToGame(Tournament savedTournament) {
    Stage createAddTournamentToGameStage = new Stage();
    createAddTournamentToGameStage.setTitle("Add Tournament to Game");
    createAddTournamentToGameStage.setWidth(300);
    createAddTournamentToGameStage.setHeight(350);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxTournamentToGame = new VBox();
    vBoxTournamentToGame.setAlignment(Pos.CENTER);

    Label infoLabel = new Label();
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label();

    Label chooseTournament = new Label("Tournament: " + savedTournament.getName());
    chooseTournament.setTextFill(Color.WHITE);

    Label spaceLabel2 = new Label();

    Label chooseGame = new Label("Choose Game");
    chooseGame.setTextFill(Color.WHITE);

    ComboBox<Game> gameBox = new ComboBox<>();
    gameBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    List<Game> allGames = gameController.getAllGames(true);

    ObservableList<Game> gameList = FXCollections.observableArrayList(allGames);
    gameBox.setItems(gameList);

    Label spaceLabel3 = new Label();

    // Checkboxes
    Label gameTypeLabel = new Label("Tournament Type");
    gameTypeLabel.setTextFill(Color.WHITE);
    CheckBox teamBox = new CheckBox("Teams");
    teamBox.setTextFill(Color.WHITE);
    CheckBox playerBox = new CheckBox("Players");
    playerBox.setTextFill(Color.WHITE);

    // Checkboxes settings
    teamBox.setOnAction(event -> {
      if (teamBox.isSelected()) {
        playerBox.setSelected(false);
        playerBox.setDisable(true);
        savedTournament.setTeamGame(true);
      } else {
        playerBox.setDisable(false);
        savedTournament.setTeamGame(false);
      }
    });

    playerBox.setOnAction(event -> {
      if (playerBox.isSelected()) {
        teamBox.setSelected(false);
        teamBox.setDisable(true);
        savedTournament.setTeamGame(false);
      } else {
        teamBox.setDisable(false);
        savedTournament.setTeamGame(true);
      }
    });

    Label spaceLabel4 = new Label();

    Button createAddToGameButton = new Button("NEXT");
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
    vBoxTournamentToGame.getChildren().addAll(
        infoLabel, spaceLabel, chooseTournament, spaceLabel2,
        chooseGame, gameBox, spaceLabel3, gameTypeLabel, teamBox, playerBox, spaceLabel4, buttonBox, spaceLabel10, messageLabel
    );

    stackPane.getChildren().addAll(vBoxTournamentToGame);

    Scene createTournamentScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createAddTournamentToGameStage.setScene(createTournamentScene);
    createAddTournamentToGameStage.show();

    createAddToGameButton.setOnAction(event -> {
      Game selectedGame = gameBox.getSelectionModel().getSelectedItem();

      if (savedTournament != null && selectedGame != null) {
        int tournamentId = savedTournament.getId();
        int gameId = selectedGame.getGame_id();

        if (gameController.addTournamentToGame(tournamentId, gameId)) {
          messageLabel.setText(savedTournament.getName() + " added to " + selectedGame.getName());
          messageLabel.setTextFill(Color.LIGHTGREEN);
          System.out.println("✅ Tournament added to Game");

          gameBox.getSelectionModel().clearSelection();

          // Close window
          createAddTournamentToGameStage.close();

          // Open new
          addTeamsOrPlayersToTournament(savedTournament, gameId);

        } else {
          System.out.println("❌ Tournament failed to be added to Game");
        }
      } else if (savedTournament != null && selectedGame == null) {
        messageLabel.setText("Choose a Game");
        messageLabel.setTextFill(Color.RED);
      } else if (savedTournament == null && selectedGame != null) {
        messageLabel.setText("Choose a Tournament");
        messageLabel.setTextFill(Color.RED);
      }

    });

    closeWindowButton.setOnAction(event -> {
      createAddTournamentToGameStage.close();
    });
  }

  public void addTeamsOrPlayersToTournament(Tournament savedTournament, int gameId) {
    Stage addMatchesStage = new Stage();
    addMatchesStage.setTitle("Add Teams/Players");
    addMatchesStage.setWidth(322);
    addMatchesStage.setHeight(500);

    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxAddMatch = new VBox();
    vBoxAddMatch.setAlignment(Pos.CENTER);

    Label savedTournamentLabel = new Label("Tournament: " + savedTournament.getName());
    savedTournamentLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label();

    String typeOfTournament = "";
    if (savedTournament.isTeamGame()) {
      typeOfTournament = "Teams";
    } else {
      typeOfTournament = "Players";
    }
    Label tournamentTypeLabel = new Label("Tournament Type: " + typeOfTournament);
    tournamentTypeLabel.setTextFill(Color.WHITE);

    Label spaceLabel2 = new Label();

    Label spaceLabel3 = new Label();

    Button createAddToGameButton = new Button("DONE");
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

    // add to vBox
    vBoxAddMatch.getChildren().addAll(
        savedTournamentLabel, spaceLabel,
        tournamentTypeLabel, spaceLabel2
    );


    ComboBox<Team> teamComboBox = null;
    ComboBox<Team> teamComboBox2 = null;
    ComboBox<Team> teamComboBox3 = null;
    ComboBox<Team> teamComboBox4 = null;
    ComboBox<Team> teamComboBox5 = null;
    ComboBox<Team> teamComboBox6 = null;
    ComboBox<Team> teamComboBox7 = null;
    ComboBox<Team> teamComboBox8 = null;
    ComboBox<Player> playerComboBox = null;
    ComboBox<Player> playerComboBox2 = null;
    ComboBox<Player> playerComboBox3 = null;
    ComboBox<Player> playerComboBox4 = null;
    ComboBox<Player> playerComboBox5 = null;
    ComboBox<Player> playerComboBox6 = null;
    ComboBox<Player> playerComboBox7 = null;
    ComboBox<Player> playerComboBox8 = null;

    if (savedTournament.isTeamGame()) {
      if (savedTournament.getContestants() == 2) {
        // Box 1

        teamComboBox = new ComboBox<>();
        teamComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        List<Team> allTeams = teamController.getAll(true);
        List<Team> availableTeams = allTeams.stream().filter(team -> team.getGame() != null && team.getGame().getGame_id() == gameId).collect(Collectors.toList());

        ObservableList<Team> teamList = FXCollections.observableArrayList(availableTeams);
        teamComboBox.setItems(teamList);


        vBoxAddMatch.getChildren().add(teamComboBox);
        Label spaceLabelForComboBox = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox);

        // Box 2
        teamComboBox2 = new ComboBox<>();
        teamComboBox2.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox2.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox2);
        Label spaceLabelForComboBox2 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox2);

      } else if (savedTournament.getContestants() == 4) {
        // Box 1

        teamComboBox = new ComboBox<>();
        teamComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        List<Team> allTeams = teamController.getAll(true);
        List<Team> availableTeams = allTeams.stream().filter(team -> team.getGame() != null && team.getGame().getGame_id() == gameId).collect(Collectors.toList());

        ObservableList<Team> teamList = FXCollections.observableArrayList(availableTeams);
        teamComboBox.setItems(teamList);


        vBoxAddMatch.getChildren().add(teamComboBox);
        Label spaceLabelForComboBox = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox);

        // Box 2
        teamComboBox2 = new ComboBox<>();
        teamComboBox2.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox2.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox2);
        Label spaceLabelForComboBox2 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox2);

        // Box 3
        teamComboBox3 = new ComboBox<>();
        teamComboBox3.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox3.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox3);
        Label spaceLabelForComboBox3 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox3);

        // Box 4
        teamComboBox4 = new ComboBox<>();
        teamComboBox4.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox4.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox4);
        Label spaceLabelForComboBox4 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox4);
      } else if (savedTournament.getContestants() == 8) {
        teamComboBox = new ComboBox<>();
        teamComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        List<Team> allTeams = teamController.getAll(true);
        List<Team> availableTeams = allTeams.stream().filter(team -> team.getGame() != null && team.getGame().getGame_id() == gameId).collect(Collectors.toList());

        ObservableList<Team> teamList = FXCollections.observableArrayList(availableTeams);
        teamComboBox.setItems(teamList);


        vBoxAddMatch.getChildren().add(teamComboBox);
        Label spaceLabelForComboBox = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox);

        // Box 2
        teamComboBox2 = new ComboBox<>();
        teamComboBox2.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox2.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox2);
        Label spaceLabelForComboBox2 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox2);

        // Box 3
        teamComboBox3 = new ComboBox<>();
        teamComboBox3.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox3.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox3);
        Label spaceLabelForComboBox3 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox3);

        // Box 4
        teamComboBox4 = new ComboBox<>();
        teamComboBox4.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox4.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox4);
        Label spaceLabelForComboBox4 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox4);

        // Box 5
        teamComboBox5 = new ComboBox<>();
        teamComboBox5.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox5.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox5);
        Label spaceLabelForComboBox5 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox5);

        // Box 6
        teamComboBox6 = new ComboBox<>();
        teamComboBox6.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox6.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox6);
        Label spaceLabelForComboBox6 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox6);

        // Box 7
        teamComboBox7 = new ComboBox<>();
        teamComboBox7.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox7.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox7);
        Label spaceLabelForComboBox7 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox7);

        // Box 8
        teamComboBox8 = new ComboBox<>();
        teamComboBox8.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        teamComboBox8.setItems(teamList);

        vBoxAddMatch.getChildren().add(teamComboBox8);
        Label spaceLabelForComboBox8 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox8);
      }
    } else {
      if (savedTournament.getContestants() == 2) {
        // Box 1
        playerComboBox = new ComboBox<>();
        playerComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        List<Player> allPLayers = playerController.getAll(true);
        List<Player> availablePlayers = allPLayers.stream().filter(player -> player.getGame() != null && player.getGame().getGame_id() == gameId).collect(Collectors.toList());

        ObservableList<Player> playerList = FXCollections.observableArrayList(availablePlayers);
        playerComboBox.setItems(playerList);


        vBoxAddMatch.getChildren().add(playerComboBox);
        Label spaceLabelForComboBox = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox);

        // Box 2
        playerComboBox2 = new ComboBox<>();
        playerComboBox2.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox2.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox2);
        Label spaceLabelForComboBox2 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox2);
      } else if (savedTournament.getContestants() == 4) {
        // Box 1
        playerComboBox = new ComboBox<>();
        playerComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        List<Player> allPLayers = playerController.getAll(true);
        List<Player> availablePlayers = allPLayers.stream().filter(player -> player.getGame() != null && player.getGame().getGame_id() == gameId).collect(Collectors.toList());

        ObservableList<Player> playerList = FXCollections.observableArrayList(availablePlayers);
        playerComboBox.setItems(playerList);


        vBoxAddMatch.getChildren().add(playerComboBox);
        Label spaceLabelForComboBox = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox);

        // Box 2
        playerComboBox2 = new ComboBox<>();
        playerComboBox2.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox2.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox2);
        Label spaceLabelForComboBox2 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox2);

        // Box 3
        playerComboBox3 = new ComboBox<>();
        playerComboBox3.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox3.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox3);
        Label spaceLabelForComboBox3 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox3);

        // Box 4
        playerComboBox4 = new ComboBox<>();
        playerComboBox4.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox4.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox4);
        Label spaceLabelForComboBox4 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox4);
      } else if (savedTournament.getContestants() == 8) {
        // Box 1
        playerComboBox = new ComboBox<>();
        playerComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        List<Player> allPLayers = playerController.getAll(true);
        List<Player> availablePlayers = allPLayers.stream().filter(player -> player.getGame() != null && player.getGame().getGame_id() == gameId).collect(Collectors.toList());

        ObservableList<Player> playerList = FXCollections.observableArrayList(availablePlayers);
        playerComboBox.setItems(playerList);


        vBoxAddMatch.getChildren().add(playerComboBox);
        Label spaceLabelForComboBox = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox);

        // Box 2
        playerComboBox2 = new ComboBox<>();
        playerComboBox2.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox2.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox2);
        Label spaceLabelForComboBox2 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox2);

        // Box 3
        playerComboBox3 = new ComboBox<>();
        playerComboBox3.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox3.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox3);
        Label spaceLabelForComboBox3 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox3);

        // Box 4
        playerComboBox4 = new ComboBox<>();
        playerComboBox4.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox4.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox4);
        Label spaceLabelForComboBox4 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox4);

        // Box 5
        playerComboBox5 = new ComboBox<>();
        playerComboBox5.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox5.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox5);
        Label spaceLabelForComboBox5 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox5);

        // Box 6
        playerComboBox6 = new ComboBox<>();
        playerComboBox6.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox6.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox6);
        Label spaceLabelForComboBox6 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox6);

        // Box 7
        playerComboBox7 = new ComboBox<>();
        playerComboBox7.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox7.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox7);
        Label spaceLabelForComboBox7 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox7);

        // Box 8
        playerComboBox8 = new ComboBox<>();
        playerComboBox8.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
        playerComboBox8.setItems(playerList);

        vBoxAddMatch.getChildren().add(playerComboBox8);
        Label spaceLabelForComboBox8 = new Label();
        vBoxAddMatch.getChildren().add(spaceLabelForComboBox8);
      }

    }

    LocalDate todaysDate = LocalDate.now();


    vBoxAddMatch.getChildren().addAll(spaceLabel3, buttonBox);


    closeWindowButton.setOnAction(event -> {
      addMatchesStage.close();
    });

    ComboBox<Team> finalTeamComboBox = teamComboBox;
    ComboBox<Team> finalTeamComboBox2 = teamComboBox2;
    ComboBox<Team> finalTeamComboBox3 = teamComboBox3;
    ComboBox<Team> finalTeamComboBox4 = teamComboBox4;
    ComboBox<Team> finalTeamComboBox5 = teamComboBox5;
    ComboBox<Team> finalTeamComboBox6 = teamComboBox6;
    ComboBox<Team> finalTeamComboBox7 = teamComboBox7;
    ComboBox<Team> finalTeamComboBox8 = teamComboBox8;
    ComboBox<Player> finalPlayerComboBox = playerComboBox;
    ComboBox<Player> finalPlayerComboBox2 = playerComboBox2;
    ComboBox<Player> finalPlayerComboBox3 = playerComboBox3;
    ComboBox<Player> finalPlayerComboBox4 = playerComboBox4;
    ComboBox<Player> finalPlayerComboBox5 = playerComboBox5;
    ComboBox<Player> finalPlayerComboBox6 = playerComboBox6;
    ComboBox<Player> finalPlayerComboBox7 = playerComboBox7;
    ComboBox<Player> finalPlayerComboBox8 = playerComboBox8;
    createAddToGameButton.setOnAction(event -> {
      if (savedTournament.isTeamGame()) {
        if (savedTournament.getContestants() == 2) {
          Team finalTeam = finalTeamComboBox.getSelectionModel().getSelectedItem();
          Team finalTeam2 = finalTeamComboBox2.getSelectionModel().getSelectedItem();

          if (finalTeam != null && finalTeam2 != null) {
            tournamentController.addTeamToTournament(finalTeam.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam2.getId(), savedTournament.getId());

            matchController.addNewMatch(gameId, true, finalTeam.getGame().getGame_id(), finalTeam2.getGame().getGame_id(), todaysDate);

            // Close window
            addMatchesStage.close();
          } else {
            System.out.println("Please select both teams.");
          }
        } else if (savedTournament.getContestants() == 4) {
          Team finalTeam = finalTeamComboBox.getSelectionModel().getSelectedItem();
          Team finalTeam2 = finalTeamComboBox2.getSelectionModel().getSelectedItem();
          Team finalTeam3 = finalTeamComboBox3.getSelectionModel().getSelectedItem();
          Team finalTeam4 = finalTeamComboBox4.getSelectionModel().getSelectedItem();

          if (finalTeam != null && finalTeam2 != null && finalTeam3 != null && finalTeam4 != null) {
            tournamentController.addTeamToTournament(finalTeam.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam2.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam3.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam4.getId(), savedTournament.getId());

            matchController.addNewMatch(gameId, true, finalTeam.getGame().getGame_id(), finalTeam2.getGame().getGame_id(), todaysDate);
            matchController.addNewMatch(gameId, true, finalTeam3.getGame().getGame_id(), finalTeam4.getGame().getGame_id(), todaysDate);

            // Close window
            addMatchesStage.close();
          } else {
            System.out.println("Please select 4 teams");
          }
        } else if (savedTournament.getContestants() == 8) {
          Team finalTeam = finalTeamComboBox.getSelectionModel().getSelectedItem();
          Team finalTeam2 = finalTeamComboBox2.getSelectionModel().getSelectedItem();
          Team finalTeam3 = finalTeamComboBox3.getSelectionModel().getSelectedItem();
          Team finalTeam4 = finalTeamComboBox4.getSelectionModel().getSelectedItem();
          Team finalTeam5 = finalTeamComboBox.getSelectionModel().getSelectedItem();
          Team finalTeam6 = finalTeamComboBox2.getSelectionModel().getSelectedItem();
          Team finalTeam7 = finalTeamComboBox3.getSelectionModel().getSelectedItem();
          Team finalTeam8 = finalTeamComboBox4.getSelectionModel().getSelectedItem();

          if (finalTeam != null && finalTeam2 != null && finalTeam3 != null && finalTeam4 != null && finalTeam5 != null && finalTeam6 != null && finalTeam7 != null && finalTeam8 != null) {
            tournamentController.addTeamToTournament(finalTeam.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam2.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam3.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam4.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam5.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam6.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam7.getId(), savedTournament.getId());
            tournamentController.addTeamToTournament(finalTeam8.getId(), savedTournament.getId());

            matchController.addNewMatch(gameId, true, finalTeam.getGame().getGame_id(), finalTeam2.getGame().getGame_id(), todaysDate);
            matchController.addNewMatch(gameId, true, finalTeam3.getGame().getGame_id(), finalTeam4.getGame().getGame_id(), todaysDate);
            matchController.addNewMatch(gameId, true, finalTeam5.getGame().getGame_id(), finalTeam6.getGame().getGame_id(), todaysDate);
            matchController.addNewMatch(gameId, true, finalTeam7.getGame().getGame_id(), finalTeam8.getGame().getGame_id(), todaysDate);

            // Close window
            addMatchesStage.close();
          }
        }
      } else {
        if (savedTournament.getContestants() == 2) {
          Player finalPlayer = finalPlayerComboBox.getSelectionModel().getSelectedItem();
          Player finalPlayer2 = finalPlayerComboBox2.getSelectionModel().getSelectedItem();

          if (finalPlayer != null && finalPlayer2 != null) {
            matchController.addNewMatch(gameId, false, finalPlayer.getGame().getGame_id(), finalPlayer2.getGame().getGame_id(), todaysDate);

            // Close window
            addMatchesStage.close();
          } else {
            System.out.println("Please select both players.");
          }
        } else if (savedTournament.getContestants() == 4) {
          Player finalPlayer = finalPlayerComboBox.getSelectionModel().getSelectedItem();
          Player finalPlayer2 = finalPlayerComboBox2.getSelectionModel().getSelectedItem();
          Player finalPlayer3 = finalPlayerComboBox3.getSelectionModel().getSelectedItem();
          Player finalPlayer4 = finalPlayerComboBox4.getSelectionModel().getSelectedItem();

          if (finalPlayer != null && finalPlayer2 != null && finalPlayer3 != null && finalPlayer4 != null) {
            matchController.addNewMatch(gameId, false, finalPlayer.getGame().getGame_id(), finalPlayer2.getGame().getGame_id(), todaysDate);
            matchController.addNewMatch(gameId, false, finalPlayer3.getGame().getGame_id(), finalPlayer4.getGame().getGame_id(), todaysDate);

            // Close window
            addMatchesStage.close();
          } else {
            System.out.println("Please select 4 players.");
          }
        } else if (savedTournament.getContestants() == 8) {
          Player finalPlayer = finalPlayerComboBox.getSelectionModel().getSelectedItem();
          Player finalPlayer2 = finalPlayerComboBox2.getSelectionModel().getSelectedItem();
          Player finalPlayer3 = finalPlayerComboBox3.getSelectionModel().getSelectedItem();
          Player finalPlayer4 = finalPlayerComboBox4.getSelectionModel().getSelectedItem();
          Player finalPlayer5 = finalPlayerComboBox4.getSelectionModel().getSelectedItem();
          Player finalPlayer6 = finalPlayerComboBox4.getSelectionModel().getSelectedItem();
          Player finalPlayer7 = finalPlayerComboBox4.getSelectionModel().getSelectedItem();
          Player finalPlayer8 = finalPlayerComboBox4.getSelectionModel().getSelectedItem();

          if (finalPlayer != null && finalPlayer2 != null && finalPlayer3 != null && finalPlayer4 != null && finalPlayer5 != null && finalPlayer6 != null && finalPlayer7 != null && finalPlayer8 != null) {
            matchController.addNewMatch(gameId, false, finalPlayer.getGame().getGame_id(), finalPlayer2.getGame().getGame_id(), todaysDate);
            matchController.addNewMatch(gameId, false, finalPlayer3.getGame().getGame_id(), finalPlayer4.getGame().getGame_id(), todaysDate);
            matchController.addNewMatch(gameId, false, finalPlayer5.getGame().getGame_id(), finalPlayer6.getGame().getGame_id(), todaysDate);
            matchController.addNewMatch(gameId, false, finalPlayer7.getGame().getGame_id(), finalPlayer8.getGame().getGame_id(), todaysDate);

            // Close window
            addMatchesStage.close();
          } else {
            System.out.println("Please select 8 players.");
          }
        }
      }
    });


    stackPane.getChildren().addAll(vBoxAddMatch);

    Scene addMatchScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    addMatchesStage.setScene(addMatchScene);
    addMatchesStage.show();
  }

  public void addScoreToTournament() {
    Stage createScoreStage = new Stage();
    createScoreStage.setTitle("Add Scores");
    createScoreStage.setWidth(300);
    createScoreStage.setHeight(350);

    // Wrap VBox in StackPane to center it
    StackPane stackPane = new StackPane();

    // Existing VBox
    VBox vBoxAddScore = new VBox();
    vBoxAddScore.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("(*) are mandatory fields/boxes");
    infoLabel.setTextFill(Color.WHITE);

    Label spaceLabel = new Label();

    Label chooseTournament = new Label("Choose Tournament");
    chooseTournament.setTextFill(Color.WHITE);

    ComboBox<Tournament> tournamentComboBox = new ComboBox<>();
    List<Tournament> allTournaments = tournamentController.getAll(true);
    ObservableList<Tournament> tournamentsList = FXCollections.observableArrayList(allTournaments);
    tournamentComboBox.setItems(tournamentsList);

    tournamentComboBox.setOnAction(event -> {

      Tournament selectedTournament = tournamentComboBox.getSelectionModel().getSelectedItem();
      selectedTournament.getTournamentTeams();

      if (selectedTournament != null) {
        // Update labels based on the selected tournament
        if (selectedTournament.getContestants() == 2) {
          Label matchTeamsLabel = new Label(selectedTournament.getTournamentTeams().get(0).getName() + " vs. " + selectedTournament.getTournamentTeams().get(1).getName());
          matchTeamsLabel.setTextFill(Color.WHITE);
          vBoxAddScore.getChildren().addAll(matchTeamsLabel);
        } else if (selectedTournament.getContestants() == 4) {
          Label matchTeamsLabel1 = new Label(selectedTournament.getTournamentTeams().get(0).getName() + " vs. " + selectedTournament.getTournamentTeams().get(1).getName());
          matchTeamsLabel1.setTextFill(Color.WHITE);

          Label matchTeamsLabel2 = new Label(selectedTournament.getTournamentTeams().get(2).getName() + " vs. " + selectedTournament.getTournamentTeams().get(3).getName());
          matchTeamsLabel2.setTextFill(Color.WHITE);

          vBoxAddScore.getChildren().addAll(matchTeamsLabel1, matchTeamsLabel2);
        } else if (selectedTournament.getContestants() == 8) {

          Label matchTeamsLabel1 = new Label(selectedTournament.getTournamentTeams().get(0).getName() + " vs. " + selectedTournament.getTournamentTeams().get(1).getName());
          matchTeamsLabel1.setTextFill(Color.WHITE);

          Label matchTeamsLabel2 = new Label(selectedTournament.getTournamentTeams().get(2).getName() + " vs. " + selectedTournament.getTournamentTeams().get(3).getName());
          matchTeamsLabel2.setTextFill(Color.WHITE);

          Label matchTeamsLabel3 = new Label(selectedTournament.getTournamentTeams().get(0).getName() + " vs. " + selectedTournament.getTournamentTeams().get(1).getName());
          matchTeamsLabel3.setTextFill(Color.WHITE);

          Label matchTeamsLabel4 = new Label(selectedTournament.getTournamentTeams().get(2).getName() + " vs. " + selectedTournament.getTournamentTeams().get(3).getName());
          matchTeamsLabel4.setTextFill(Color.WHITE);

          vBoxAddScore.getChildren().addAll(matchTeamsLabel1, matchTeamsLabel2, matchTeamsLabel3, matchTeamsLabel4);

        }
      }
    });

    Label spaceLabel2 = new Label();

    Button createSetScoreButton = new Button("SET SCORE");
    createSetScoreButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    createSetScoreButton.setPrefWidth(95);
    createSetScoreButton.setOnMouseEntered(event -> createSetScoreButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    createSetScoreButton.setOnMouseExited(event -> createSetScoreButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    Button closeWindowButton = new Button("CLOSE");
    closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    closeWindowButton.setPrefWidth(95);
    closeWindowButton.setOnMouseEntered(event -> closeWindowButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    closeWindowButton.setOnMouseExited(event -> closeWindowButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    HBox buttonBox = new HBox(10); // 10 is spacing between buttons
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.getChildren().addAll(createSetScoreButton, closeWindowButton);

    Label spaceLabel5 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);

    // Add buttons, labels, and HBox to VBox
    vBoxAddScore.getChildren().addAll(
        infoLabel, spaceLabel,
        chooseTournament, tournamentComboBox,
        spaceLabel2,
        buttonBox, spaceLabel5,
        messageLabel
    );


    stackPane.getChildren().addAll(vBoxAddScore);

    Scene createScoreScene = new Scene(stackPane);
    stackPane.setStyle("-fx-background-color: #14373d;");

    createScoreStage.setScene(createScoreScene);
    createScoreStage.show();

    closeWindowButton.setOnAction(event -> {
      createScoreStage.close();
    });

    createSetScoreButton.setOnAction(event -> {
      // add what happens
    });

  }
}
