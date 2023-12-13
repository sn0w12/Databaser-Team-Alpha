package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.TeamController;
import com.teamalpha.teamalphapipergames.model.Team;
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

import java.util.List;
import java.util.Optional;

public class TeamGraphics {

    private TeamController teamController;

    public TeamGraphics() {
        teamController = new TeamController();
    }

    public void initializeTeams() {
        System.out.println("Adding Teams");

        // Counter-Strike: Global Offensive teams (gameId = 1)
        teamController.createTeam(1, "NaVi");
        teamController.createTeam(1, "ENCE");
        teamController.createTeam(1, "Cloud9");
        teamController.createTeam(1, "FaZe");
        teamController.createTeam(1, "Heroic");
        teamController.createTeam(1, "Complexity");
        teamController.createTeam(1, "Vitality");
        teamController.createTeam(1, "MOUZ");

        // EA Sports FC 24 teams (gameId = 2)
        teamController.createTeam(2, "AFC Ajax Brazil");
        teamController.createTeam(2, "Team Gullit");
        teamController.createTeam(2, "DUX America");
        teamController.createTeam(2, "RBLZ Gaming");
        teamController.createTeam(2, "Team FUTWIZ");
        teamController.createTeam(2, "Atlanta United FC");
        teamController.createTeam(2, "TG NIP");
        teamController.createTeam(2, "Team Exeed");

        // League of Legends teams (gameId = 3)
        teamController.createTeam(3, "NRG eSports");
        teamController.createTeam(3, "G2 eSports");
        teamController.createTeam(3, "Fnatic");
        teamController.createTeam(3, "MAD Lions");
        teamController.createTeam(3, "Cloud9");
        teamController.createTeam(3, "LOUD");
        teamController.createTeam(3, "Team Liquid");
        teamController.createTeam(3, "Team BDS");
    }

    public void displayTeamUI() {
        Stage primaryStage = new Stage();

        if(teamController.getAllTeams().isEmpty())
            initializeTeams();

        ObservableList<Team> teamList = FXCollections.observableArrayList(teamController.getAllTeams());
        FilteredList<Team> filteredData = new FilteredList<>(teamList, p -> true);

        // TableView for displaying teams
        TableView<Team> teamTableView = new TableView<>();

        // Define columns
        TableColumn<Team, Integer> teamIdCol = new TableColumn<>("Team ID");
        teamIdCol.setCellValueFactory(new PropertyValueFactory<>("teamId"));
        teamIdCol.setMinWidth(65);
        teamIdCol.setMaxWidth(100);

        TableColumn<Team, Integer> gameIdCol = new TableColumn<>("Game ID");
        gameIdCol.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        gameIdCol.setMinWidth(65);
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


        TextField searchField = new TextField();
        searchField.setPromptText("Search for teams...");

        // Disabled initially until a team is selected
        editTeamButton.setDisable(true);
        removeTeamButton.setDisable(true);

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(addTeamButton, editTeamButton, removeTeamButton, backButton, searchField);

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
            selectedTeamLabel.setText("Selected team name: " + newSelection.getName() + ", team id: " + newSelection.getTeamId() + ", game id: " + newSelection.getGameId());
        });

        addTeamButton.setOnAction(event -> {
            Dialog<Team> dialog = new Dialog<>();
            dialog.setTitle("Add New Team");

            // Set up the dialog components
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

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
                        return new Team(0, gameId, name);
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
                Team createdTeam = teamController.createTeam(newTeam.getGameId(), newTeam.getName());
                if (createdTeam != null) {
                    teamList.add(createdTeam);
                } else {
                    // Show an error message
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Creation Failed");
                    errorAlert.setContentText("The team could not be created.");
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

                TextField gameIdField = new TextField(String.valueOf(selectedTeam.getGameId()));
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
                            int gameId = gameIdField.getText().trim().isEmpty() ? selectedTeam.getGameId() : Integer.parseInt(gameIdField.getText().trim());
                            String name = nameField.getText().trim().isEmpty() ? selectedTeam.getName() : nameField.getText().trim();
                            return new Team(selectedTeam.getTeamId(), gameId, name);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    }
                    return null;
                });

                Optional<Team> result = dialog.showAndWait();
                result.ifPresent(editedTeam -> {
                    boolean success = teamController.updateTeam(editedTeam.getTeamId(), editedTeam.getGameId(), editedTeam.getName());
                    if (success) {
                        int selectedIndex = teamTableView.getSelectionModel().getSelectedIndex();
                        if (selectedIndex >= 0) {
                            teamList.set(selectedIndex, editedTeam);
                        }
                    } else {
                        // Show an error message
                    }
                });
            }
        });

        // Remove Team button action
        removeTeamButton.setOnAction(event -> {
            Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
            if (selectedTeam != null) {
                // Remove team logic, assuming a method in teamController
                boolean success = teamController.deleteTeam(selectedTeam.getTeamId());
                if (success) {
                    teamList.remove(selectedTeam);
                } else {
                    // Show an error message if removal fails
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Removal Failed");
                    errorAlert.setContentText("The team could not be removed.");
                    errorAlert.showAndWait();
                }
            } else {
                // No team selected
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("No team selected");
                alert.setContentText("Please select a team to remove.");
                alert.showAndWait();
            }
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
                } else if (String.valueOf(team.getGameId()).contains(lowerCaseFilter)) {
                    return true; // Filter matches game ID
                }
                return false; // Does not match
            });
        });

        // Wrap the FilteredList in a SortedList
        SortedList<Team> sortedData = new SortedList<>(filteredData);

        // Bind the SortedList comparator to the TableView comparator
        sortedData.comparatorProperty().bind(teamTableView.comparatorProperty());

        // Add data to the table
        teamTableView.setItems(sortedData);

        backButton.setOnAction(event -> primaryStage.close());

        VBox vbox = new VBox(parentLayout, selectedTeamLabel, teamTableView);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        Scene scene = new Scene(vbox, 600, 400);

        primaryStage.setTitle("Team Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}