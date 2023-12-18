
package com.teamalpha.teamalphapipergames;


import com.teamalpha.teamalphapipergames.controller.MatchController;
import com.teamalpha.teamalphapipergames.controller.PlayerController;
import com.teamalpha.teamalphapipergames.controller.TeamController;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.view.MatchGraphics;

import com.teamalpha.teamalphapipergames.view.StaffGraphics;

import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class HelloApplication extends Application {

    public static void main(String[] args) {

        // lägger till några spelare och en match för att ha lite att testa med

        MatchController matchController = new MatchController();
        TeamController teamController=new TeamController();
        PlayerController playerController = new PlayerController();

        playerController.save(new Player("Valerii", "Vakhovskyi", "b1t", "Kosmonavtov", "NONE", "DNEPROPETROVSK", "Ukraine", "b1t@navi.com"));
        playerController.save(new Player("Justinas", "Lekavicius", "jL", "52 Luknojų", "06295", "VILNIUS", "Lithuania", "jL@navi.com"));
        playerController.save(new Player("Aleksi", "Virolainen", "Aleksib", "Kluuvikatu 5", "01380", "UUSIMAA", "Finland", "aleksib@navi.com"));
        playerController.save(new Player("Mihai", "Ivan", "iM", "Nr.179B, Creaca", "NONE", "SALAJ", "Romania", "iM@navi.com"));
        playerController.save(new Player("Ihor", "Zhdanov", "w0nderful", "Mineralnaya 18", "NONE", "IRPEN", "Ukraine", "w0nderful@navi.com"));
        playerController.save(new Player("Lukas", "Rossander", "gla1ve", "Lumbyholmvej 39", "3390", "SJAELLAND", "Denmark", "gla1ve@ence.gg"));

       teamController.createTeam(1, "badAnka");
       teamController.createTeam(2, "kalleAnka");
       teamController.createTeam(3, "KallesJul");
       teamController.createTeam(4, "Anki");
       teamController.createTeam(2, "Pytte");

      //  matchController.addNewMatchTeamOrPlayer(1, false, 1, 2, "sf");


        matchController.addNewMatchWithDate(1,true,1,2, LocalDate.of(2020,5,5));
        matchController.addNewMatchWithDate(1,true,1,2, LocalDate.of(2020,5,5));
        matchController.addNewMatchWithDate(1,true,1,2, LocalDate.of(2020,5,5));
        matchController.addNewMatchWithDate(1,false,1,2, LocalDate.of(2020,5,5));
        matchController.addNewMatchWithDate(1,false,1,2, LocalDate.of(2020,5,5));
        matchController.addNewMatchWithDate(1,false,1,2, LocalDate.of(2020,5,5));
        matchController.addNewMatchWithDate(1,true,1,2, LocalDate.of(2020,5,5));
        matchController.addNewMatchWithDate(1,true,1,2, LocalDate.of(2020,5,5));
        System.out.println(  matchController.getAllMatches(false).get(0).getMatchDate());
      //  matchController.addNewWithDate(1,true,1,2,22-06-2001);
      //  matchController.addNewWithDate(1,true,1,2,2222-06-21);
        System.out.println("idag är det den: "+LocalDate.now());


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //kommentera in för att få startsida och välja staff
//        StaffGraphics staffGraphics = new StaffGraphics();
//        staffGraphics.displayStaffUI();

        //kommentera in för att få matchsida och välja matcher
        MatchGraphics matchGraphics = new MatchGraphics();
        matchGraphics.start(new Stage());
    }
}




