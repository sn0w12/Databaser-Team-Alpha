package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.StaffController;
import com.teamalpha.teamalphapipergames.model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

import static com.teamalpha.teamalphapipergames.view.MatchGraphics.setVBoxBackGround;

public class StaffGraphics {
    private StaffController staffController;
    private Stage editStaffStage;
    Stage staffStage = new Stage();


    public void displayStaffUI() {
        Stage primaryStage = new Stage();
        StaffController staffController = new StaffController();

        // Skapa initial personal om de inte redan finns
        staffController.createInitialStaffIfNotExists();

        // Hämta alla poster från personaltabellen
        List<Staff> staffList = staffController.getAllStaff();

        // Skapa en lista för att lagra personalens namn
        ObservableList<String> items = FXCollections.observableArrayList();

        if (staffList != null) {
            for (Staff staff : staffList) {
                items.add(staff.getFirstName() + " " + staff.getLastName());
            }
        } else {
            items.add("Failed to fetch data from the staff table.");
        }

        // Skapa en ListView för att visa personalinformation
        ListView<String> staffListView = new ListView<>(items);


        Label selectedStaffLabel = new Label("Selected staff: ");
        Button continueButton = new Button("Continue");
        Button exitButton = new Button("Exit");

        staffListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedStaffLabel.setText("Selected staff: " + newValue);
            continueButton.setDisable(false); // Aktivera knappen när en personal är vald
        });

        continueButton.setOnAction(event -> {
            String selectedStaff = staffListView.getSelectionModel().getSelectedItem();
            if (selectedStaff != null) {
                assert staffList != null;
                int selectedStaffId = staffController.getStaffIdFromName(selectedStaff, staffList);
                if (selectedStaffId != -1) {
                    showTabsForStaff(primaryStage);
                }
            } else {
                // Om ingen personal är vald, visa en varning för att användaren ska välja en
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

            // Visa en bekräftelsedialog och vänta på användarens svar
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Om användaren väljer "OK", stäng applikationen
                    primaryStage.close();
                }
            });
        });

// Skapa en VBox för att placera komponenterna
        VBox vbox = new VBox(staffListView, selectedStaffLabel, continueButton, exitButton);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        setVBoxBackGround(vbox);
        Scene scene = new Scene(vbox, 800, 600);

        //Sätt stilar för knapparna
        Button[] buttonList = {continueButton, exitButton}; // Lägg till andra knappar här om det behövs
        for (Button button : buttonList) {
            button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
            button.setOnMouseEntered(event -> button.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
            button.setOnMouseExited(event -> button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
            button.setPrefWidth(190);
        }
// Sätt titel och visa scenen
        primaryStage.setTitle("Welcome to Piper Games");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void showTabsForStaff(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        Tab staffTab = new Tab("Staff");
        tabPane.getTabs().addAll(staffTab);

        StaffController staffController = new StaffController();
        List<Staff> staffList = staffController.getAllStaff();
        TableView<Staff> staffTableView = staffController.createStaffTableView(staffList);

        primaryStage.close();

        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> {
            primaryStage.show(); // Visa gränssnittet för huvudskärmen
            staffStage.close(); // Stäng fönstret för flikarna
        });
        Button addButton = new Button("Add Staff");
        addButton.setOnAction(event -> createAddStaffWindow());

        Button editButton = new Button("Edit Staff");
        editButton.setOnAction(event -> createEditStaffWindow().show());

        Button deleteButton = new Button("Delete Staff");
        deleteButton.setOnAction(event -> createDeleteStaffWindow().show());

        // Sätt stilar för knapparna
        Button[] buttonList = {backButton, addButton, editButton, deleteButton}; // Lägg till andra knappar här om det behövs
        for (Button button : buttonList) {
            button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
            button.setOnMouseEntered(event -> button.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
            button.setOnMouseExited(event -> button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
            button.setPrefWidth(190);
        }
        VBox buttonLayout = new VBox(addButton, editButton, deleteButton, backButton);
        buttonLayout.setSpacing(10);

        VBox staffLayout = new VBox(staffTableView, buttonLayout);
        staffTab.setContent(staffLayout);

        Scene staffScene = new Scene(tabPane, 1000, 800);

        staffStage.setTitle("Staff Options");
        staffStage.setScene(staffScene);

        // Sätt bakgrundsbild för staffLayout
        setVBoxBackGround(staffLayout);

        staffStage.show();
    }

    // Metoden för att lägga till ny personal
    private void createAddStaffWindow() {
        StaffController staffController = new StaffController();
        Stage addStaffStage = new Stage();
        addStaffStage.setTitle("Add New Staff");

        TextField firstNameField = staffController.createFormattedTextField("[a-zA-Z]+");
        TextField lastNameField = staffController.createFormattedTextField("[a-zA-Z]+");
        TextField nicknameField = staffController.createFormattedTextField("[a-zA-Z0-9\\s]+");
        TextField addressField = staffController.createFormattedTextField("[a-zA-Z0-9\\s]+");
        TextField zipCodeField = staffController.createFormattedTextField("\\d+");
        TextField postalAddressField = staffController.createFormattedTextField("[a-zA-Z0-9\\s]+");
        TextField countryField = staffController.createFormattedTextField("[a-zA-Z]+");
        TextField emailField = new TextField();

        StaffController.validateEmailField(emailField);

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String nickname = nicknameField.getText();
            String address = addressField.getText();
            String zipCode = zipCodeField.getText();
            String postalAddress = postalAddressField.getText();
            String country = countryField.getText();
            String email = emailField.getText();

            boolean staffSaved = staffController.saveStaff(firstName, lastName, nickname, address, zipCode, postalAddress, country, email);
            if (staffSaved) {
                addStaffStage.close(); // Stäng addStaffStage efter att personalen har lagts till
                staffStage.close();
                staffController.displayAddConfirmation();
                displayStaffUI();
            }
        });

        // Layout för att lägga till personal
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("First Name:"), firstNameField,
                new Label("Last Name:"), lastNameField,
                new Label("Nickname:"), nicknameField,
                new Label("Address:"), addressField,
                new Label("Zip Code:"), zipCodeField,
                new Label("Postal Address:"), postalAddressField,
                new Label("Country:"), countryField,
                new Label("Email:"), emailField,
                confirmButton
        );
        layout.setPadding(new Insets(10));

        // Skapa scen och lägg till layouten
        Scene scene = new Scene(layout, 400, 600);
        addStaffStage.setScene(scene);

        // Visa fönstret för att lägga till personal
        addStaffStage.show();
    }

    // Metoden för att redigera befintlig personal
    private Stage createEditStaffWindow() {
        staffController = new StaffController();
        editStaffStage = new Stage();

        // TextFields för att visa befintlig personalinformation
        TextField staffIdField = new TextField();
        TextField firstNameField = staffController.createFormattedTextField("[a-zA-Z]+");
        TextField lastNameField = staffController.createFormattedTextField("[a-zA-Z]+");
        TextField nicknameField = staffController.createFormattedTextField("[a-zA-Z0-9\\s]+");
        TextField addressField = staffController.createFormattedTextField("[a-zA-Z0-9\\s]+");
        TextField zipCodeField = staffController.createFormattedTextField("\\d+");
        TextField postalAddressField = staffController.createFormattedTextField("[a-zA-Z0-9\\s]+");
        TextField countryField = staffController.createFormattedTextField("[a-zA-Z]+");
        TextField emailField = new TextField();

        StaffController.validateEmailField(emailField);
        Button confirmEditButton = new Button("Confirm Edit");
        confirmEditButton.setOnAction(e -> {
            int staffId = Integer.parseInt(staffIdField.getText());
            String newFirstName = firstNameField.getText();
            String newLastName = lastNameField.getText();
            String newNickname = nicknameField.getText();
            String newAddress = addressField.getText();
            String newZipCode = zipCodeField.getText();
            String newPostalAddress = postalAddressField.getText();
            String newCountry = countryField.getText();
            String newEmail = emailField.getText();

            boolean updated = staffController.updateStaffInformation(staffId, newFirstName, newLastName,
                    newNickname, newAddress, newZipCode, newPostalAddress, newCountry, newEmail);

            if (updated) {
                editStaffStage.close();
                staffStage.close();
                staffController.displayEditConfirmation();
                displayStaffUI();
            } else {
                // Visa meddelande om misslyckad uppdatering
                staffController.displayUpdateFailure();
            }
        });

        // Layout för fönstret för att redigera personal
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Staff ID to Edit:"), staffIdField,
                new Label("First Name:"), firstNameField,
                new Label("Last Name:"), lastNameField,
                new Label("Nickname:"), nicknameField,
                new Label("Address:"), addressField,
                new Label("Zip Code:"), zipCodeField,
                new Label("Postal Address:"), postalAddressField,
                new Label("Country:"), countryField,
                new Label("Email:"), emailField,
                confirmEditButton
        );
        layout.setPadding(new Insets(10));

        // Skapa scen och lägg till layouten
        Scene scene = new Scene(layout, 400, 600);
        editStaffStage.setScene(scene);

        // Visa fönstret för att redigera personal
        editStaffStage.show();

        return editStaffStage;
    }

    // Metoden för att ta bort personal
    private Stage createDeleteStaffWindow() {
        staffController = new StaffController();
        Stage deleteStaffStage = new Stage();
        deleteStaffStage.setTitle("Delete Staff");

        // Textfält för att mata in ID på den personal som ska raderas
        TextField staffIdField = new TextField();

        // Knapp för att bekräfta och ta bort personal
        Button confirmDeleteButton = new Button("Confirm Delete");
        confirmDeleteButton.setOnAction(e -> {
            // Hämta ID från textfältet
            int staffId = Integer.parseInt(staffIdField.getText());

            // Anropa en metod för att ta bort personal med det angivna ID:t
            boolean deleteSuccessful = staffController.delete(staffId);

            if (deleteSuccessful) {
                // Stäng fönstret när en person har tagits bort
                deleteStaffStage.close();
                staffStage.close();


                // Uppdatera listan av personal efter att en person har tagits bort
                StaffGraphics staffGraphics = new StaffGraphics();
                staffGraphics.displayStaffUI();

                // Visa en bekräftelse till användaren
                StaffController.displayDeleteConfirmation();
            } else {
                // Visa ett meddelande om något gick fel med borttagningen
                StaffController.displayDeleteFailure();
            }
        });

        // Skapa layout för fönstret med textfält och bekräftelseknapp
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Staff ID to Delete:"), staffIdField,
                confirmDeleteButton
        );
        layout.setPadding(new Insets(10));

        // Skapa scen och lägg till layouten
        Scene scene = new Scene(layout, 300, 200);
        deleteStaffStage.setScene(scene);

        // Visa fönstret för att ta bort personal
        deleteStaffStage.show();

        return deleteStaffStage;
    }
}