package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Team;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.transformation.SortedList;

import java.util.Objects;
import java.util.Optional;

public class TeamGraphics {

  Stage stage;
  private GameController gameController;
  private TeamController teamController;
  private PlayerController playerController;
  private MatchController matchController;
  private StaffController staffController;
//  private TournamentController tournamentController;

  public TeamGraphics(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController) {
    this.gameController = gameController;
    this.teamController = teamController;
    this.playerController = playerController;
    this.matchController = matchController;
    this.staffController = staffController;
  }

  public void start(Stage primaryStage) throws Exception {
    try {
      this.stage = primaryStage;
      Platform.runLater(() -> {
        displayTeamUI();
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void displayTeamUI() {
    Stage primaryStage = new Stage();

    ObservableList<Team> teamList = FXCollections.observableArrayList(teamController.getAll(false));
    FilteredList<Team> filteredData = new FilteredList<>(teamList, p -> true);

    // TableView for displaying teams
    TableView<Team> teamTableView = new TableView<>();

    // Define columns
    TableColumn<Team, Integer> teamIdCol = new TableColumn<>("Team ID");
    teamIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    teamIdCol.setMinWidth(70);
    teamIdCol.setMaxWidth(100);

    TableColumn<Team, Integer> gameIdCol = new TableColumn<>("Game ID");
    gameIdCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getGame() != null ? cellData.getValue().getGame().getGame_id() : null));
    gameIdCol.setMinWidth(70);
    gameIdCol.setMaxWidth(100);

    TableColumn<Team, String> nameCol = new TableColumn<>("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    nameCol.setMinWidth(400);

    teamTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    teamTableView.setMinWidth(400);
    teamTableView.setMaxWidth(Double.MAX_VALUE);
    teamTableView.setMinHeight(300);
    teamTableView.setMaxHeight(Double.MAX_VALUE);

    teamTableView.getColumns().addAll(teamIdCol, gameIdCol, nameCol);
    Button addTeamButton = new Button("Add Team");
    Button removeTeamButton = new Button("Remove Team");
    Button editTeamButton = new Button("Edit Team");
    Button backButton = new Button("Back");
    Button truncateButton = new Button("Clear table");
    truncateButton.getStyleClass().add("delete-button");

    TextField searchField = new TextField();
    searchField.setPromptText("Search for teams...");

    // Disabled initially until a team is selected
    editTeamButton.setDisable(true);
    removeTeamButton.setDisable(true);

    HBox buttonLayout = new HBox(10);
    buttonLayout.getChildren().addAll(addTeamButton, editTeamButton, removeTeamButton, truncateButton, backButton);

    HBox searchLayout = new HBox(10);
    searchLayout.getChildren().addAll(searchField);

    // Spacer to push the search box to the right
    Pane spacer = new Pane();
    HBox.setHgrow(spacer, Priority.ALWAYS);
    spacer.setMinSize(10, 1);

    // Parent HBox that contains both the buttonLayout and searchLayout
    HBox parentLayout = new HBox();
    parentLayout.getChildren().addAll(buttonLayout, spacer, searchLayout);

    Label selectedTeamLabel = new Label("No team selected");

    teamTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      editTeamButton.setDisable(newSelection == null);
      removeTeamButton.setDisable(newSelection == null);
      if (newSelection != null) {
        selectedTeamLabel.setText("Selected team name: " + newSelection.getName() + ", team id: " + newSelection.getId() + ", game id: " + newSelection.getGame().getGame_id());
      } else {
        selectedTeamLabel.setText("No team selected");
      }
    });

    addTeamButton.setOnAction(event -> {
      Dialog<Team> dialog = new Dialog<>();
      dialog.setTitle("Add New Team");

      // Set up the dialog components
      DialogPane dialogPane = dialog.getDialogPane();
      dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
      dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

      TextField gameIdField = new TextField();
      gameIdField.setPromptText("Game ID");
      TextField nameField = new TextField();
      nameField.setPromptText("Team Name");

      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.add(new Label("Game ID:"), 0, 0);
      grid.add(gameIdField, 1, 0);
      grid.add(new Label("Team Name:"), 0, 1);
      grid.add(nameField, 1, 1);

      dialogPane.setContent(grid);

      // Disable the OK button initially
      Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
      okButton.setDisable(true);

      // Listener to enable OK button only if both fields are filled and gameId is an int
      javafx.beans.value.ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> {
        boolean isGameIdInt = false;
        try {
          Integer.parseInt(gameIdField.getText().trim());
          isGameIdInt = true;
        } catch (NumberFormatException ignored) {
        }

        okButton.setDisable(gameIdField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || !isGameIdInt);
      };

      gameIdField.textProperty().addListener(textChangeListener);
      nameField.textProperty().addListener(textChangeListener);

      // Convert the result to a team when OK button is clicked
      dialog.setResultConverter(dialogButton -> {
        if (dialogButton == ButtonType.OK) {
          try {
            int gameId = Integer.parseInt(gameIdField.getText().trim());
            String name = nameField.getText().trim();
            if (gameController.getGameById(gameId) != null)
              return new Team(name, gameController.getGameById(gameId));
            else {
              Alert errorAlert = new Alert(Alert.AlertType.ERROR);
              errorAlert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
              errorAlert.setHeaderText("Creation Failed");
              errorAlert.setContentText("There is no game with that id, please choose an existing game id");
              errorAlert.showAndWait();
            }
          } catch (NumberFormatException e) {
            // Handle invalid number input
            return null;
          }
        }
        return null;
      });

      // Handle the dialog result
      Optional<Team> result = dialog.showAndWait();
      result.ifPresent(newTeam -> {
        teamController.save(newTeam);
        try {
          teamList.add(newTeam);
        } catch(Exception e) {
          // Show an error message
          Alert errorAlert = new Alert(Alert.AlertType.ERROR);
          errorAlert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
          errorAlert.setHeaderText("Creation Failed");
          errorAlert.setContentText(e.getMessage());
          errorAlert.showAndWait();
        }
      });
    });

    editTeamButton.setOnAction(event -> {
      Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
      if (selectedTeam != null) {
        Dialog<Team> dialog = new Dialog<>();
        dialog.setTitle("Edit Team");

        // Set up the dialog components
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        TextField gameIdField = new TextField(String.valueOf(selectedTeam.getGame().getGame_id()));
        gameIdField.setPromptText("Game ID");
        TextField nameField = new TextField(selectedTeam.getName());
        nameField.setPromptText("Team Name");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Game ID:"), 0, 0);
        grid.add(gameIdField, 1, 0);
        grid.add(new Label("Team Name:"), 0, 1);
        grid.add(nameField, 1, 1);

        dialogPane.setContent(grid);

        // Disable the OK button initially
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        gameIdField.textProperty().addListener((observable, oldValue, newValue) -> {
          try {
            Integer.parseInt(newValue.trim());
            okButton.setDisable(false);
          } catch (NumberFormatException e) {
            okButton.setDisable(true);
          }
        });

        dialog.setResultConverter(dialogButton -> {
          if (dialogButton == ButtonType.OK) {
            try {
              int gameId = gameIdField.getText().trim().isEmpty() ? selectedTeam.getGame().getGame_id() : Integer.parseInt(gameIdField.getText().trim());
              String name = nameField.getText().trim().isEmpty() ? selectedTeam.getName() : nameField.getText().trim();
              // Pass the selected team's ID to the constructor
              return new Team(selectedTeam.getId(), name, gameController.getGameById(gameId));
            } catch (NumberFormatException e) {
              return null;
            }
          }
          return null;
        });

        Optional<Team> result = dialog.showAndWait();
        result.ifPresent(editedTeam -> {
          boolean success = teamController.updateTeam(editedTeam);
          if (success) {
            int selectedIndex = teamTableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
              teamList.set(selectedIndex, editedTeam);
            }
          } else {
            // Show an error message
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
            errorAlert.setHeaderText("Edit Failed");
            errorAlert.setContentText("The team could not be edited.");
            errorAlert.showAndWait();
          }
        });
      }
    });

    // Remove Team button action
    removeTeamButton.setOnAction(event -> {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
      alert.setTitle("Confirmation");
      alert.setHeaderText("Confirm Deletion");
      alert.setContentText("Are you sure you want to delete this team?");

      alert.showAndWait().ifPresent(response -> {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null && response == ButtonType.OK) {
          boolean success = teamController.deleteTeamById(selectedTeam.getId());
          if (success) {
            teamList.remove(selectedTeam);
          } else {
            // Show an error message if removal fails
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
            errorAlert.setHeaderText("Removal Failed");
            errorAlert.setContentText("The team could not be removed.");
            errorAlert.showAndWait();
          }
        } else {
          // No team selected
          Alert errorAlert = new Alert(Alert.AlertType.WARNING);
          errorAlert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
          errorAlert.setTitle("Warning");
          errorAlert.setHeaderText("No team selected");
          errorAlert.setContentText("Please select a team to remove.");
          errorAlert.showAndWait();
        }
      });
    });

    // Update the predicate whenever the filter changes
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredData.setPredicate(team -> {
        // If filter text is empty, display all teams
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        // Compare team name and ID with filter text
        String lowerCaseFilter = newValue.toLowerCase();
        if (team.getName().toLowerCase().contains(lowerCaseFilter)) {
          return true; // Filter matches team name
        } else return String.valueOf(team.getGame()).contains(lowerCaseFilter);
      });
    });

    truncateButton.setOnAction(event -> {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
      alert.setTitle("Confirmation");
      alert.setHeaderText("Confirm Clear");
      alert.setContentText("Are you sure you want to clear this table? You will remove every entry in the database.");

      // Display a confirmation dialog and wait for the user's response
      alert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.OK) {
          // If the user selects "OK", close the application
          for (Team team : teamList) {
            teamController.deleteTeamById(team.getId());
          }
          // Clear the list after deletion
          teamController.resetTeamIdCount();
          teamList.clear();
        }
      });
    });

    // Wrap the FilteredList in a SortedList
    SortedList<Team> sortedData = new SortedList<>(filteredData);

    // Bind the SortedList comparator to the TableView comparator
    sortedData.comparatorProperty().bind(teamTableView.comparatorProperty());

    // Add data to the table
    teamTableView.setItems(sortedData);

    backButton.setOnAction(event -> {
      System.out.println("Back to Staff Main Menu");

      StaffMainMenu staffMainMenu = new StaffMainMenu(gameController, teamController, playerController, matchController, staffController);

      // start stage
      try {
        staffMainMenu.start(stage);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

      primaryStage.close();
    });

    VBox vbox = new VBox(parentLayout, selectedTeamLabel, teamTableView);
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(10));
    vbox.getStyleClass().add("vbox-background");
    Scene scene = new Scene(vbox, 600, 400);
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

    primaryStage.setTitle("Team Management");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}