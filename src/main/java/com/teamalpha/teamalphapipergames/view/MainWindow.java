package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.GameController;
import com.teamalpha.teamalphapipergames.controller.StaffController;
import com.teamalpha.teamalphapipergames.model.Staff;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class MainWindow extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  // Start page
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Login Window");

    // Add Login button
    Button loginButton = createLoginButton();
    loginButton.setOnAction(event -> handleLoginButton(primaryStage));

    StackPane layout = new StackPane();
    layout.getChildren().add(loginButton);
    layout.setAlignment(Pos.CENTER);

    // load BG image for background
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
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private Button createLoginButton() {
    Button loginButton = new Button("START");
    loginButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    return loginButton;
  }

  private void handleLoginButton(Stage primaryStage) {
    GameController gameController = new GameController();
    StaffController staffController = new StaffController();
    ChooseEmployee chooseEmployee = new ChooseEmployee(gameController);

    Menu menu = new Menu(gameController);
    menu.createStaff();

    List<Staff> staffList = staffController.getAll(true);
    chooseEmployee.createStaff(staffList);
    chooseEmployee.showEmployeeMenu(staffList);

    primaryStage.close();
  }
}

class ChooseEmployee {

  private final GameController controller;

  public ChooseEmployee(GameController controller) {
    this.controller = controller;
  }

  public void createStaff(List<Staff> staffList) {
    // Code to create staff, if needed
  }

  // Second stage - choosing employee
  public void showEmployeeMenu(List<Staff> staffList) {
    Stage stage = new Stage();
    stage.setTitle("Employee Menu");

    ComboBox<String> employeeComboBox = new ComboBox<>();
    employeeComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
    employeeComboBox.setItems(FXCollections.observableArrayList(getStaffNames(staffList)));
    employeeComboBox.setOnAction(e -> handleComboBoxSelection(employeeComboBox));


    // Label on box
    Label label = new Label("Select Profile");
    label.setStyle("-fx-text-fill: white;");


    // Add Login button
    Button loginButton = createLoginButton();

    VBox layout = new VBox(10);
    layout.getChildren().addAll(label, employeeComboBox, loginButton);
    layout.setAlignment(Pos.CENTER);

    // load BG image for background
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
  }

  private Button createLoginButton() {
    Button loginButton = new Button("Login");
    loginButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
    loginButton.setOnAction(e -> handleLoginButton());
    return loginButton;
  }

  private List<String> getStaffNames(List<Staff> staffList) {
    return staffList.stream()
        .map(staff -> staff.getFirstName() + " " + staff.getLastName())
        .collect(Collectors.toList());
  }

  private void handleComboBoxSelection(ComboBox<String> employeeComboBox) {
    String selectedEmployee = employeeComboBox.getSelectionModel().getSelectedItem();
    if (selectedEmployee != null) {
      System.out.println("Selected Employee: " + selectedEmployee);
    }
  }

  private void handleLoginButton() {
    System.out.println("Logged in");
  }
}

