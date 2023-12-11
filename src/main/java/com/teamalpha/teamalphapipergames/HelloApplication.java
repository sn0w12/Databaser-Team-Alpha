package com.teamalpha.teamalphapipergames;

import com.teamalpha.teamalphapipergames.controller.MatchController;
import com.teamalpha.teamalphapipergames.model.Match;
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
        MatchGraphics matchGraphics = new MatchGraphics();
        matchGraphics.start(new Stage());
    }

    public static void main(String[] args) {


        MatchController matchController = new MatchController();
        matchController.saveMatch(new Match(true, 1, 1, 2, false, "2023/12/24"));
        matchController.saveMatch(new Match(true, 1, 2, 4, true, "3455"));
        matchController.saveMatch(new Match(false, 1, 2, 4, true, "3455"));
        matchController.saveMatch(new Match(false, 1, 2, 4, true, "3455"));
        //matchController.saveMatch(new Match(false, 1, 2, 4, true, "3455"));

        //  matchController.getAllMatches(true);

        System.out.println("***********************");
        matchController.getPlayedOrUpcomingMatches(true, true);
        System.out.println("***********************");
        matchController.getPlayedOrUpcomingMatches(true, false);

        System.out.println("test igen");

        launch();
    }
}



