package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.StaffController;
import com.teamalpha.teamalphapipergames.model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class StaffGraphics {

    public void displayStaffUI() {
        Stage primaryStage = new Stage();
        StaffController staffController = new StaffController();

        // Create initial staff if they don't exist already
        staffController.createInitialStaffIfNotExists();

        // Retrieve all records from the staff table
        List<Staff> staffList = staffController.getAllStaff();

        // Create a list to store staff names
        ObservableList<String> items = FXCollections.observableArrayList();

        if (staffList != null) {
            for (Staff staff : staffList) {
                items.add(staff.getFirstName() + " " + staff.getLastName());
            }
        } else {
            items.add("Failed to fetch data from the staff table.");
        }

        // Create a ListView to display staff information
        ListView<String> staffListView = new ListView<>(items);

        Label selectedStaffLabel = new Label("Selected staff: ");
        Button continueButton = new Button("Continue");
        Button exitButton = new Button("Exit");

        staffListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedStaffLabel.setText("Selected staff: " + newValue);
            continueButton.setDisable(false); // Enable the button when an staff is selected
        });

        continueButton.setOnAction(event -> {
            String selectedStaff = staffListView.getSelectionModel().getSelectedItem();
            if (selectedStaff != null) {
                int selectedStaffId = getStaffIdFromName(selectedStaff, staffList);
                if (selectedStaffId != -1) {
                    showTabsForStaff(selectedStaffId, primaryStage);
                }
            } else {
                // If no staff is selected, show an alert prompting the user to select one
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("No staff selected");
                alert.setContentText("Please select an staff before continuing.");
                alert.showAndWait();
            }
        });

        exitButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm Exit");
            alert.setContentText("Are you sure you want to exit?");

            // Display a confirmation dialog and wait for the user's response
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // If the user selects "OK", close the application
                    primaryStage.close();
                }
            });
        });



        VBox vbox = new VBox(staffListView, selectedStaffLabel, continueButton, exitButton);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        Scene scene = new Scene(vbox, 300, 250);

        primaryStage.setTitle("Welcome to Piper Games");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showTabsForStaff(int staffId, Stage primaryStage) {
        Stage staffStage = new Stage();

        TabPane tabPane = new TabPane();

        Tab staffTab = new Tab("Staff");

        // Detta kan man lägga i sin egen showTabsFor....
        /*Tab playerTab = new Tab("Player");
        Tab gamesTab = new Tab("Games");
        Tab teamsTab = new Tab("Teams");
        Tab matchesTab = new Tab("Matches");*/

        //Samma här då ta den som gäller ens egen och skriv om koden nedan.
        // Add tabs to the TabPane
        tabPane.getTabs().addAll(staffTab/*, playerTab, gamesTab, teamsTab, matchesTab*/);
        VBox staffLayout = new VBox(); // VBox to hold TableView and buttons


        StaffController staffController = new StaffController();
        List<Staff> staffList = staffController.getAllStaff();

        TableView<Staff> staffTableView = new TableView<>();

        if (staffList != null && !staffList.isEmpty()) {
            ObservableList<Staff> data = FXCollections.observableArrayList(staffList);

            TableColumn<Staff, Integer> staffIdCol = new TableColumn<>("Staff ID");
            staffIdCol.setCellValueFactory(new PropertyValueFactory<>("staffId"));

            TableColumn<Staff, String> firstNameCol = new TableColumn<>("First Name");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<Staff, String> lastNameCol = new TableColumn<>("Last Name");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<Staff, String> nickNameCol = new TableColumn<>("Nick Name");
            nickNameCol.setCellValueFactory(new PropertyValueFactory<>("nickname"));

            TableColumn<Staff, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

            TableColumn<Staff, String> zipCodeCol = new TableColumn<>("Zip Code");
            zipCodeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));

            TableColumn<Staff, String> postalAddressCol = new TableColumn<>("Postal Address");
            postalAddressCol.setCellValueFactory(new PropertyValueFactory<>("postalAddress"));

            TableColumn<Staff, String> countryCol = new TableColumn<>("Country");
            countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

            TableColumn<Staff, String> emailCol = new TableColumn<>("Email");
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

            staffTableView.getColumns().addAll(staffIdCol, firstNameCol, lastNameCol, nickNameCol, addressCol, zipCodeCol, postalAddressCol, countryCol, emailCol /* och andra kolumner här */);


            staffTableView.setItems(data);
        } else {
            System.out.println("Ingen personal att visa.");
        }

        // Buttons for staff options
        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> {
            staffStage.close(); // Close the window for the tabs
            primaryStage.show(); // Show the main screen interface again
        });

        Button addButton = new Button("Add Staff");
        addButton.setOnAction(event -> {
            // Logic for adding staff
        });

        Button editButton = new Button("Edit Staff");
        editButton.setOnAction(event -> {
            // Logic for editing staff
        });

        Button deleteButton = new Button("Delete Staff");
        deleteButton.setOnAction(event -> {
            // Logic for deleting staff
        });

        VBox buttonLayout = new VBox(addButton, editButton, deleteButton, backButton);
        staffLayout.getChildren().addAll(staffTableView, buttonLayout);

        staffTab.setContent(staffLayout);


        Scene staffScene = new Scene(tabPane, 800, 600);

        staffStage.setTitle("Staff Options");
        staffStage.setScene(staffScene);
        staffStage.show();
    }

    private int getStaffIdFromName(String selectedName, List<Staff> staffList) {
        for (Staff staff : staffList) {
            String fullName = staff.getFirstName() + " " + staff.getLastName();
            if (selectedName.equals(fullName)) {
                return staff.getStaffId();
            }
        }
        return -1;
    }
}

