package com.teamalpha.teamalphapipergames;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Match;
import com.teamalpha.teamalphapipergames.view.MainWindow;
import com.teamalpha.teamalphapipergames.view.Menu;
import com.teamalpha.teamalphapipergames.view.MatchGraphics;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//kör bara matcher
//        GameController gameController = new GameController();
//        TeamController teamController = new TeamController();
//        PlayerController playerController = new PlayerController();
//        MatchController matchController = new MatchController();
//        StaffController staffController = new StaffController();
//        MatchGraphics matchGraphics = new MatchGraphics(gameController, teamController, playerController, matchController, staffController);
//        matchGraphics.start(stage);

//
//        //kör hela från början
        MainWindow mainWindow = new MainWindow();
        mainWindow.start(new Stage());



        //         start for menu to test in console while coding
//        GameController controller = new GameController();
//        Menu menu = new Menu(controller);
//        menu.showMainMenu();
    }

    public static void main(String[] args) {

        launch();
    }
}



