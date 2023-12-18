
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
    private StaffController staffController;
    private Stage editStaffStage;
    Stage staffStage = new Stage();


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
            continueButton.setDisable(false); // Enable the button when a staff is selected
        });

        continueButton.setOnAction(event -> {
            String selectedStaff = staffListView.getSelectionModel().getSelectedItem();
            if (selectedStaff != null) {
                assert staffList != null;
                int selectedStaffId = getStaffIdFromName(selectedStaff, staffList);
                if (selectedStaffId != -1) {
                    showTabsForStaff(primaryStage);
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

    private void showTabsForStaff(Stage primaryStage) {


        TabPane tabPane = new TabPane();

        Tab staffTab = new Tab("Staff");

        
        tabPane.getTabs().addAll(staffTab);

        VBox staffLayout = new VBox(); // VBox to hold TableView and buttons
        StaffController staffController = new StaffController();
        List<Staff> staffList = staffController.getAllStaff();

        TableView<Staff> staffTableView = createStaffTableView(staffList);

        primaryStage.close();

        // Buttons for staff options
        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> {
            primaryStage.show(); // Show the main screen interface again
            staffStage.close(); // Close the window for the tabs
        });

        Button addButton = new Button("Add Staff");
        addButton.setOnAction(event -> {
            Stage addStaffStage = createAddStaffWindow();
            addStaffStage.show();

        });

        Button editButton = new Button("Edit Staff");
        editButton.setOnAction(event -> {
            Stage editStaffStage = createEditStaffWindow();
            editStaffStage.show();

        });


        Button deleteButton = new Button("Delete Staff");
        deleteButton.setOnAction(event -> {
            Stage deleteStaffStage = createDeleteStaffWindow();
            deleteStaffStage.show();

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

    private TableView<Staff> createStaffTableView(List<Staff> staffList) {
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

            staffTableView.getColumns().addAll(staffIdCol, firstNameCol, lastNameCol, nickNameCol, addressCol, zipCodeCol, postalAddressCol, countryCol, emailCol);

            staffTableView.setItems(data);
        } else {
            System.out.println("No staff to show.");
        }

        return staffTableView;
    }

    private Stage createAddStaffWindow() {

        staffController = new StaffController();
        Stage addStaffStage = new Stage();
        addStaffStage.setTitle("Add New Staff");


        // TextFields för att mata in ny personalinformation
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField nicknameField = new TextField();
        TextField addressField = new TextField();
        TextField zipCodeField = new TextField();
        TextField postalAddressField = new TextField();
        TextField countryField = new TextField();
        TextField emailField = new TextField();

        // Regex-mönster för olika typer av inmatning
        String namePattern = "[a-zA-Z]+"; // Endast bokstäver tillåtna för namn och land
        String alphanumericPattern = "[a-zA-Z0-9\\s]+"; // Bokstäver och siffror för nickname och adress
        String zipCodePattern = "\\d+"; // Endast siffror för postnummer



        // TextFormatter för att begränsa inmatning enligt specifika mönster
        TextFormatter<String> firstNameFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(namePattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> lastNameFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(namePattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> nicknameFormatter = new TextFormatter<>(change ->
                change.getControlNewText().isEmpty() || change.getControlNewText().matches(".*") ? change : null);

        TextFormatter<String> addressFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(alphanumericPattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> zipCodeFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(zipCodePattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> postalAddressFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(alphanumericPattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> countryFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(namePattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });


        // Tillämpa TextFormatter på respektive TextField
        firstNameField.setTextFormatter(firstNameFormatter);
        lastNameField.setTextFormatter(lastNameFormatter);
        nicknameField.setTextFormatter(nicknameFormatter);
        addressField.setTextFormatter(addressFormatter);
        zipCodeField.setTextFormatter(zipCodeFormatter);
        postalAddressField.setTextFormatter(postalAddressFormatter);
        countryField.setTextFormatter(countryFormatter);


        // Lägg till en lyssnare för att validera e-postfältet när användaren försöker lämna det
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String email = emailField.getText();
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                if (!email.matches(emailRegex)) {
                    // Visa ett felmeddelande om e-postadressen inte är giltig när användaren försöker lämna fältet
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid email address!");
                    alert.showAndWait();
                }
            }
        });


        // Knapp för att bekräfta och spara ny personal
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            // Hämta information från textfälten
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String nickname = nicknameField.getText();
            String address = addressField.getText();
            String zipCode = zipCodeField.getText();
            String postalAddress = postalAddressField.getText();
            String country = countryField.getText();
            String email = emailField.getText();

            // Kontrollera om något av de obligatoriska fälten är tomt
            if (firstName.isEmpty() || lastName.isEmpty() || nickname.isEmpty() || address.isEmpty() || zipCode.isEmpty() || postalAddress.isEmpty() || country.isEmpty() || email.isEmpty()) {
                // Visa ett felmeddelande för användaren om obligatoriska fält saknas
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all required fields (All fields need to be filled)!");
                alert.showAndWait();
            } else {
                // Alla obligatoriska fält är ifyllda, skapa Staff-objekt och spara
                Staff newStaff = new Staff(0, firstName, lastName, nickname, address, zipCode, postalAddress, country, email);
                staffController.save(newStaff);

                staffController.save(newStaff);
                // Stäng fönstret när du är klar med att lägga till personal
                addStaffStage.close();
                staffStage.close();


                // För att uppdatera listan av personal efter att en ny har lagts till
                displayAddConfirmation();
                StaffGraphics staffGraphics = new StaffGraphics();
                staffGraphics.displayStaffUI();
            }
            });

        // Skapa en layout för fönstret med alla textfält och bekräftelseknapp
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

        // Skapa en scen och lägg till layouten
        Scene scene = new Scene(layout, 400, 600);
        addStaffStage.setScene(scene);

        // Visa fönstret för att lägga till personal
        addStaffStage.show();

        return addStaffStage;
    }
    private void displayAddConfirmation() {
        // Visa en bekräftelsedialog om att personalen har lagts till
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Add Staff Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("New staff has been successfully added!");
        confirmationAlert.showAndWait();
    }

    private Stage createEditStaffWindow() {
        editStaffStage = new Stage();
        staffController = new StaffController();

        // TextFields för att visa befintlig personalinformation
        TextField staffIdField = new TextField();
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField nicknameField = new TextField();
        TextField addressField = new TextField();
        TextField zipCodeField = new TextField();
        TextField postalAddressField = new TextField();
        TextField countryField = new TextField();
        TextField emailField = new TextField();

        // Regex-mönster för olika typer av inmatning
        String namePattern = "[a-zA-Z]+"; // Endast bokstäver tillåtna för namn och land
        String alphanumericPattern = "[a-zA-Z0-9\\s]+"; // Bokstäver och siffror för nickname och adress
        String zipCodePattern = "\\d+"; // Endast siffror för postnummer


        // TextFormatter för att begränsa inmatning enligt specifika mönster
        TextFormatter<String> firstNameFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(namePattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> lastNameFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(namePattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> nicknameFormatter = new TextFormatter<>(change ->
                change.getControlNewText().isEmpty() || change.getControlNewText().matches(".*") ? change : null);

        TextFormatter<String> addressFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(alphanumericPattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> zipCodeFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(zipCodePattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> postalAddressFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(alphanumericPattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });

        TextFormatter<String> countryFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(namePattern) || newText.isEmpty()) {
                return change;
            }
            return null;
        });



        // Tillämpa TextFormatter på respektive TextField
        firstNameField.setTextFormatter(firstNameFormatter);
        lastNameField.setTextFormatter(lastNameFormatter);
        nicknameField.setTextFormatter(nicknameFormatter);
        addressField.setTextFormatter(addressFormatter);
        zipCodeField.setTextFormatter(zipCodeFormatter);
        postalAddressField.setTextFormatter(postalAddressFormatter);
        countryField.setTextFormatter(countryFormatter);


        // Lägg till en lyssnare för att validera e-postfältet när användaren försöker lämna det
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String email = emailField.getText();
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                if (!email.matches(emailRegex)) {
                    // Visa ett felmeddelande om e-postadressen inte är giltig när användaren försöker lämna fältet
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid email address!");
                    alert.showAndWait();
                }
            }
        });

        // Knapp för att bekräfta och uppdatera personal
        Button confirmEditButton = new Button("Confirm Edit");
        confirmEditButton.setOnAction(e -> {
            int staffId = Integer.parseInt(staffIdField.getText());
            Staff existingStaff = staffController.getStaffById(staffId);

            if (existingStaff != null) {
                // Uppdatera endast de fält där det finns ny information
                String newFirstName = firstNameField.getText();
                if (!newFirstName.isEmpty()) {
                    existingStaff.setFirstName(newFirstName);
                }

                String newLastName = lastNameField.getText();
                if (!newLastName.isEmpty()) {
                    existingStaff.setLastName(newLastName);
                }

                String newNickname = nicknameField.getText();
                if (!newNickname.isEmpty()) {
                    existingStaff.setNickname(newNickname);
                }

                String newAddress = addressField.getText();
                if (!newAddress.isEmpty()) {
                    existingStaff.setAddress(newAddress);
                }

                String newZipCode = zipCodeField.getText();
                if (!newZipCode.isEmpty()) {
                    existingStaff.setZipCode(newZipCode);
                }

                String newPostalAddress = postalAddressField.getText();
                if (!newPostalAddress.isEmpty()) {
                    existingStaff.setPostalAddress(newPostalAddress);
                }

                String newCountry = countryField.getText();
                if (!newCountry.isEmpty()) {
                    existingStaff.setCountry(newCountry);
                }

                String newEmail = emailField.getText();
                if (!newEmail.isEmpty()) {
                    existingStaff.setEmail(newEmail);
                }

                boolean updated = staffController.update(existingStaff);

                if (updated) {
                    editStaffStage.close();
                    staffStage.close();
                    displayEditConfirmation();
                    StaffGraphics staffGraphics = new StaffGraphics();
                    staffGraphics.displayStaffUI();
                } else {
                    // Visa meddelande om misslyckad uppdatering
                    displayUpdateFailure();
                }
            } else {
                // Visa meddelande om att personalen inte hittades
                System.out.println("Personalen hittades inte");
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
    private void displayEditConfirmation() {
        // Visa en bekräftelsedialog om att informationen har ändrats
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Update Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Staff information has been successfully updated!");
        confirmationAlert.showAndWait();
    }

    private void displayUpdateFailure() {
        // Visa meddelande om misslyckad uppdatering
        Alert failureAlert = new Alert(Alert.AlertType.ERROR);
        failureAlert.setTitle("Update Failure");
        failureAlert.setHeaderText(null);
        failureAlert.setContentText("Failed to update staff information. Please try again!");
        failureAlert.showAndWait();
    }

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
                displayDeleteConfirmation();
            } else {
                // Visa ett meddelande om något gick fel med borttagningen
                displayDeleteFailure();
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

    private void displayDeleteConfirmation() {
        // Visa en bekräftelse för användaren efter att en person har tagits bort
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Deletion Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Staff member has been successfully deleted!");
        confirmationAlert.showAndWait();
    }

    private void displayDeleteFailure() {
        // Visa ett meddelande om borttagning misslyckades
        Alert failureAlert = new Alert(Alert.AlertType.ERROR);
        failureAlert.setTitle("Deletion Failure");
        failureAlert.setHeaderText(null);
        failureAlert.setContentText("Failed to delete staff member. Please try again!");
        failureAlert.showAndWait();
    }
}