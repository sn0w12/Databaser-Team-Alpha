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

    // Add to tournament button
    Button addToTournamentButton = new Button("ADD TO TOU");
    addToTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    addToTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    addToTournamentButton.setOnMouseEntered(event -> addToTournamentButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    addToTournamentButton.setOnMouseExited(event -> addToTournamentButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
    addToTournamentButton.setMinWidth(buttonWidth);
    addToTournamentButton.setMaxWidth(buttonWidth);
    addToTournamentButton.setAlignment(Pos.CENTER);

    addToTournamentButton.setOnAction(event -> {
//      addToTournament();
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
//      addScoreToTournament();
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

    HBox buttonsBox2 = new HBox(addToTournamentButton, addScoreToTournamentButton);
    buttonsBox2.setAlignment(Pos.CENTER);

    HBox buttonsBox3 = new HBox(updateTableButton, deleteTournamentButton);
    buttonsBox3.setAlignment(Pos.CENTER);

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
              boolean connectedToGameDirectly = tournament.getGame() != null && selectedIds.contains(String.valueOf(tournament.getGame().getId()));

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
    List<Game> games = gameController.getAll(false);
    return games.stream()
        .map(game -> {
          CheckBox checkBox = new CheckBox(game.getName());
          checkBox.setUserData(String.valueOf(game.getId()));
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

    Label spaceLabel = new Label("");

    Label titleLabel = new Label("Input title(*)");
    titleLabel.setTextFill(Color.WHITE);
    TextField titleTextField = new TextField();
    titleTextField.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    titleTextField.setMaxWidth(200);

    Label spaceLabel2 = new Label("");

    Label contestantLabel = new Label("Amount of contestants(*)");
    contestantLabel.setTextFill(Color.WHITE);
    ObservableList<Integer> contestantOptions = FXCollections.observableArrayList(2, 4, 8);
    ComboBox<Integer> nrOfContestants = new ComboBox(contestantOptions); // 2, 4, or 8

    Label spaceLabel3 = new Label("");

    Button createTournamentButton = new Button("CREATE");
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

    Label spaceLabel4 = new Label("");

    Label messageLabel = new Label();
    messageLabel.setTextFill(Color.WHITE);

    Label spaceLabel5 = new Label("");

    VBox dynamicComboBoxesBox = new VBox();
    dynamicComboBoxesBox.setAlignment(Pos.CENTER);

    // Add buttons, labels, and HBox to VBox
    vBoxCreatePlayer.getChildren().addAll(
        infoLabel, spaceLabel,
        titleLabel, titleTextField,
        spaceLabel2, contestantLabel,
        spaceLabel3, nrOfContestants,
        spaceLabel4, buttonBox,
        spaceLabel5, messageLabel
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
          tournamentController.save(new Tournament(title, contestants), contestants);
          messageLabel.setText("Tournament: " + title + " with " + contestants + " contestants was created");
          messageLabel.setTextFill(Color.LIGHTGREEN);
          titleTextField.clear();
          nrOfContestants.getSelectionModel().clearSelection();
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


}
