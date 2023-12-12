
package com.teamalpha.teamalphapipergames;

import com.teamalpha.teamalphapipergames.view.StaffGraphics;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StaffGraphics staffGraphics = new StaffGraphics();
        staffGraphics.displayStaffUI();
    }
}




