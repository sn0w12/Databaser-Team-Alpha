package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Staff;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class MainWindow extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  // Start page class / stage
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Login Window");

    // Add STAFF button
    Button staffButton = createStaffButton();
    staffButton.setOnAction(event -> handleStaffButton(primaryStage));

    // Add VISITOR button
    Button visitorButton = createVisitorButton();
    visitorButton.setOnAction(event -> handleVisitorButton());

    // Add EXIT button
    Button exitButton = createExitButton();
    exitButton.setOnAction(event -> handleExitButton());

    // Set the preferred width for both buttons
    double preferredWidth = 100.0; // Adjust the value as needed
    staffButton.setPrefWidth(preferredWidth);
    visitorButton.setPrefWidth(preferredWidth);
    exitButton.setPrefWidth(preferredWidth);

    // Create a vertical layout for buttons
    VBox buttonLayout = new VBox(10); // 10 is the spacing between buttons
    buttonLayout.getChildren().addAll(staffButton, visitorButton, exitButton);
    buttonLayout.setAlignment(Pos.CENTER);

    // Create a layout to contain the buttons
    VBox layout = new VBox(20); // 20 is the spacing between the button layout and other elements
    layout.getChildren().addAll(buttonLayout);
    layout.setAlignment(Pos.CENTER);


    // Load BG image for background
    Image backgroundImage = new Image("file:BG.jpg");

    // Create a background with the image
    BackgroundImage background = new BackgroundImage(
        backgroundImage,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
    );

    // Apply background to the layout
    layout.setBackground(new Background(background));

    Scene scene = new Scene(layout, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private Button createStaffButton() {
    Button staffButton = new Button("STAFF");
    staffButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    staffButton.setOnMouseEntered(event -> staffButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    staffButton.setOnMouseExited(event -> staffButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return staffButton;
  }

  private void handleStaffButton(Stage primaryStage) {
    GameController gameController = new GameController();
    TeamController teamController = new TeamController();
    PlayerController playerController = new PlayerController();
    MatchController matchController = new MatchController();
    StaffController staffController = new StaffController();
    TournamentController tournamentController = new TournamentController();
    ChooseEmployee chooseEmployee = new ChooseEmployee(primaryStage, gameController, teamController, playerController, matchController, staffController, tournamentController);

    Menu menu = new Menu(gameController);
    menu.createStaff();
    menu.createPlayersAndTeamsDataForStaff();

    List<Staff> staffList = staffController.getAllStaff();

    chooseEmployee.showEmployeeMenu(staffList);

    primaryStage.close();
  }

  private Button createVisitorButton() {
    Button visitorButton = new Button("VISITOR");
    visitorButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    visitorButton.setOnMouseEntered(event -> visitorButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    visitorButton.setOnMouseExited(event -> visitorButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    return visitorButton;
  }

  private void handleVisitorButton() {
    System.out.println("Opening visitor window");
  }

  private Button createExitButton() {
    Button exitButton = new Button("EXIT");
    exitButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");

    // hover effect
    exitButton.setOnMouseEntered(event -> exitButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
    // go back to regular color
    exitButton.setOnMouseExited(event -> exitButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

    exitButton.setOnAction(event -> handleExitButton());
    return exitButton;
  }

  private void handleExitButton() {
    Platform.exit();
    System.exit(0);
  }
}

// Second stage Choose Employee Login Class / Stage
class ChooseEmployee {

  private final GameController gameController;
  private final TeamController teamController;
  private final PlayerController playerController;
  private final MatchController matchController;
  private final StaffController staffController;
  private final TournamentController tournamentController;
  private final Stage stage;

  // Constructor


  public ChooseEmployee(Stage stage, GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController, TournamentController tournamentController) {
    this.stage = stage;
    this.gameController = gameController;
    this.teamController = teamController;
    this.playerController = playerController;
    this.matchController = matchController;
    this.staffController = staffController;
    this.tournamentController = tournamentController;
  }

  void showEmployeeMenu(List<Staff> staffList) {
    Platform.runLater(() -> {
      stage.setTitle("Employee Menu");

      // profile picture here
      ImageView profilePicture = new ImageView();
      profilePicture.setFitWidth(150);
      profilePicture.setFitHeight(150);

      // display picture
      VBox profileBox = new VBox(10);
      profileBox.getChildren().addAll(profilePicture);
      profileBox.setAlignment(Pos.CENTER);

      // Label on box
      Label label = new Label("Select Profile");
      label.setStyle("-fx-text-fill: white;");

      ComboBox<String> employeeComboBox = new ComboBox<>();
      employeeComboBox.setStyle("-fx-background-color: #206773; -fx-mark-highlight-text-fill: white;");
      employeeComboBox.setItems(FXCollections.observableArrayList(getStaffNames(staffList)));
      employeeComboBox.setOnAction(e ->
          handleComboBoxSelection(employeeComboBox, profilePicture));
      displayProfilePicture("", profilePicture);

      // Message if fails
      Label messageLabel = new Label();
      messageLabel.setTextFill(Color.WHITE);

      // hard coded login button
      Button loginButton = new Button("LOGIN");
      loginButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
      // hover effect
      loginButton.setOnMouseEntered(event -> loginButton.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
      // go back to regular color
      loginButton.setOnMouseExited(event -> loginButton.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));

      loginButton.setOnAction(e -> {
        try {
          String selectedEmployee = employeeComboBox.getSelectionModel().getSelectedItem();
          if (selectedEmployee != null && !selectedEmployee.isEmpty()) {
            System.out.println("Logged in");
            StaffMainMenu staffMainMenu = new StaffMainMenu(gameController, teamController, playerController, matchController, staffController, tournamentController);
            // Start next stage
            staffMainMenu.start(stage);
            stage.close();
          } else {
            messageLabel.setText("Choose an employee in order to continue");
            messageLabel.setTextFill(Color.RED);
          }
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }

      });


      VBox layout = new VBox(10);
      layout.getChildren().addAll(profileBox, label, employeeComboBox, loginButton, messageLabel);
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

    });

  }

  // Methods
  private List<String> getStaffNames(List<Staff> staffList) {
    return staffList.stream()
        .map(staff -> staff.getFirstName() + " " + staff.getLastName())
        .collect(Collectors.toList());
  }

  private void handleComboBoxSelection(ComboBox<String> employeeComboBox, ImageView profilePicture) {
    String selectedEmployee = employeeComboBox.getSelectionModel().getSelectedItem();
    if (selectedEmployee != null) {
      System.out.println("Selected Employee: " + selectedEmployee);
      if (selectedEmployee.contains("Hendricks") || selectedEmployee.contains("Gilfoyle") || selectedEmployee.contains("Chugtai")) {
        displayProfilePicture(selectedEmployee, profilePicture);
      }
    }
  }

  private void displayProfilePicture(String selectedEmployee, ImageView profilePicture) {

    Image defaultImage = new Image("file:icon_image.png");

    if (selectedEmployee != null) {

      Image image = null;
      try {
        if (selectedEmployee.contains("Hendricks")) {
          image = new Image("file:richard_hendricks.jpeg");
          System.out.println("display richard");
        } else if (selectedEmployee.contains("Gilfoyle")) {
          image = new Image("file:bertram_gilfoyle.jpeg");
        } else if (selectedEmployee.contains("Chugtai")) {
          image = new Image("file:dinesh_chugtai.jpeg");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (image != null) {
        profilePicture.setImage(image);
        makeCircular(profilePicture);
      } else {
        profilePicture.setImage(defaultImage);
      }
    } else {
      profilePicture.setImage(defaultImage);
    }
  }

  private void makeCircular(ImageView imageView) {

    Circle clip = new Circle(imageView.getFitWidth() / 2, imageView.getFitHeight() / 2, imageView.getFitWidth() / 2);
    imageView.setClip(clip);
  }
}