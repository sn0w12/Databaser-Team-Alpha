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
        MainWindow mainWindow = new MainWindow();
        mainWindow.start(new Stage());
    }

    public static void main(String[] args) {

        launch();
    }
}



