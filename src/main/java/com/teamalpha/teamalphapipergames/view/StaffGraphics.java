
package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Staff;
import com.teamalpha.teamalphapipergames.model.Tournament;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.sql.Delete;

import java.util.List;
import java.util.Objects;


public class StaffGraphics extends Application {
    Stage stage;
    private final GameController gameController;
    private final TeamController teamController;
    private final PlayerController playerController;
    private final MatchController matchController;
    private StaffController staffController;
      private TournamentController tournamentController;
    private Stage editStaffStage;
    Stage staffStage = new Stage();
    private FilteredList<Staff> filteredStaff;


    public StaffGraphics(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController, TournamentController tournamentController) {
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
                showTabsForStaff(primaryStage);
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


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


        Label selectedStaffLabel = new Label("Selected staff:");
        Button continueButton = new Button("Continue");
        Button backButton = new Button("Back to main");

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
                alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
                alert.setTitle("Warning");
                alert.setHeaderText("No staff selected");
                alert.setContentText("Please select an staff before continuing.");
                alert.showAndWait();
            }
        });

        backButton.setOnAction(event -> {
            Stage staffMainMenuStage = new Stage();
            StaffMainMenu staffMainMenu = new StaffMainMenu(gameController, teamController, playerController, matchController, staffController, tournamentController);

            // Skapa en instans av StaffMainMenu och öppna den
            try {
                staffMainMenu.start(staffMainMenuStage);
            } catch (Exception e) {
                e.printStackTrace(); // Hantera eventuella undantag
            }

            primaryStage.close();
        });
        // Skapa en HBox för att placera knapparna och texten ovanför listan
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(selectedStaffLabel, continueButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

// Skapa en VBox för att placera komponenterna
        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonBox, staffListView);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.getStyleClass().add("vbox-background"); // Lägger till stilklassen för VBox

        // Ladda in stilfilen för JavaFX-scenen
        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        // Sätt stilar för knapparna genom att tilldela stilklassen
        continueButton.getStyleClass().add("button-common");
        backButton.getStyleClass().add("button-common");

        primaryStage.setTitle("Staff Management");
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
        staffTableView.setMaxWidth(788);

        primaryStage.close();

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            primaryStage.show();
            staffStage.close(); // Stäng fönstret för flikarna
        });
        Button addButton = new Button("Add Staff");
        addButton.setOnAction(event -> createAddStaffWindow());

        Button editButton = new Button("Edit Staff");
        editButton.setOnAction(event -> createEditStaffWindow().show());

        Button deleteButton = new Button("Delete Staff");
        deleteButton.setOnAction(event -> createDeleteStaffWindow());

        TextField searchField = new TextField();
        searchField.setPromptText("Search for staff...");


        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(addButton, editButton, deleteButton, backButton);

        HBox searchLayout = new HBox(10);
        searchLayout.getChildren().addAll(searchField);

        // Spacer to push the search box to the right
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

        // Parent HBox that contains both the buttonLayout and searchLayout
        HBox parentLayout = new HBox();
        parentLayout.getChildren().addAll(buttonLayout, spacer, searchLayout);

        // Sätt stilar för knapparna
        Button[] buttonList = {backButton, addButton, editButton, deleteButton};
        for (Button button : buttonList) {
            button.getStyleClass().add("my-button-style");
            // Skapar en temporär Text för att beräkna bredden baserat på texten i knappen
            Text text = new Text(button.getText());
            text.snapshot(null, null); // för att säkerställa att texten är beräknad

            // Ställer in bredden på knappen baserat på textens bredd och lägg till en marginal
            button.setPrefWidth(text.getLayoutBounds().getWidth() + 20);


            button.setMaxWidth(100);
        }

        VBox staffLayout = new VBox();
        staffLayout.getStyleClass().add("vbox-background");
        staffLayout.getChildren().addAll(parentLayout, staffTableView); // Lägg till parentLayout för knappar och sökfält här

        VBox.setVgrow(staffTableView, Priority.ALWAYS); // Så att staffTableView tar upp all tillgänglig plats vertikalt
        HBox.setHgrow(parentLayout, Priority.ALWAYS); // Så att parentLayout (med knappar och sökfält) expanderar horisontellt

        staffTab.setContent(staffLayout);


        Scene staffScene = new Scene(tabPane, 800, 600);
        staffScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        staffStage.setTitle("Staff Management");
        staffStage.setScene(staffScene);

        staffStage.show();

        FilteredList<Staff> filteredStaff = new FilteredList<>(FXCollections.observableArrayList(staffList));
        staffController.searchStaff(staffTableView, searchField, filteredStaff);
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
        layout.getStyleClass().add("vbox-background");
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

        // Lägg till stilklassen från style.css
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        addStaffStage.setScene(scene);

        // Visa fönstret för att lägga till personal
        addStaffStage.show();
    }


    // Metoden för att redigera befintlig personal
    private Stage createEditStaffWindow() {
        staffController = new StaffController();
        editStaffStage = new Stage();
        editStaffStage.setTitle("Edit Staff");

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
        layout.getStyleClass().add("vbox-background");
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

        // Lägg till stilklassen från style.css
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        editStaffStage.setScene(scene);

        // Visa fönstret för att redigera personal
        editStaffStage.show();

        return editStaffStage;
    }

    // Metoden för att ta bort personal
    private void createDeleteStaffWindow() {
        Stage deleteStaffStage = new Stage();
        deleteStaffStage.setTitle("Delete Staff");

        // Textfält för att mata in ID på den personal som ska raderas
        TextField staffIdField = new TextField();

        // Knapp för att bekräfta och ta bort personal
        Button confirmDeleteButton = new Button("Confirm Delete");
        confirmDeleteButton.setOnAction(e -> {
            int staffId = Integer.parseInt(staffIdField.getText());

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Confirmation");
            confirmationAlert.setContentText("Are you sure you want to delete this staff member?");

            ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

            confirmationAlert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == confirmButtonType) {
                    boolean deleteSuccessful = staffController.deleteStaff(staffId);
                    if (deleteSuccessful) {
                        // Stäng fönstret när en person har tagits bort
                        deleteStaffStage.close();
                        staffStage.close();

                        // Uppdatera listan av personal efter att en person har tagits bort
                        StaffGraphics staffGraphics = new StaffGraphics(gameController, teamController, playerController, matchController, staffController, tournamentController);
                        staffGraphics.displayStaffUI();
                    } else {
                        // Visa ett meddelande om något gick fel med borttagningen
                        Alert failureAlert = new Alert(Alert.AlertType.ERROR);
                        failureAlert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
                        failureAlert.setTitle("Deletion Failure");
                        failureAlert.setHeaderText("Deletion Failure");
                        failureAlert.setContentText("Failed to delete staff member. Please try again!");
                        failureAlert.showAndWait();
                    }
                }
            });
        });

        // Skapa layout för fönstret med textfält och bekräftelseknapp
        VBox layout = new VBox(10);
        layout.getStyleClass().add("vbox-background");
        layout.getChildren().addAll(
                new Label("Staff ID to Delete:"), staffIdField,
                confirmDeleteButton
        );
        layout.setPadding(new Insets(10));

        // Skapa scen och lägg till layouten
        Scene scene = new Scene(layout, 300, 200);

        // Lägg till stilklassen från style.css
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        deleteStaffStage.setScene(scene);

        // Visa fönstret för att ta bort personal
        deleteStaffStage.show();
    }
}