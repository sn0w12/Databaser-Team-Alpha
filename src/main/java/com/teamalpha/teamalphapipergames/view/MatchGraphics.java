package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.MatchController;
import com.teamalpha.teamalphapipergames.model.Match;
import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class MatchGraphics extends Application {
    Stage stage;

    MatchController matchController = new MatchController();
    private final TableView<Match> allMatchesTable = new TableView<>();

    @Override
    public void start(Stage matchStage) throws Exception {
        matchStage.setTitle("Matches");
        matchStage.setWidth(500);
        matchStage.setHeight(500);


        VBox vBoxAlternatives = new VBox();

        Button buttonShowAllMatches = new Button("Show all matches");
        buttonShowAllMatches.setOnAction(event -> {
                    showAllMatches();
                }
        );
        vBoxAlternatives.getChildren().add(buttonShowAllMatches);


        Scene sceneAlternatives = new Scene(vBoxAlternatives);
        matchStage.setScene(sceneAlternatives);
        matchStage.show();
    }


    public void showAllMatches() {
        Stage matchStage = new Stage();
        matchStage.setTitle("Show All Matches");
        matchStage.setWidth(500);
        matchStage.setHeight(500);

        Group matchGroup = new Group();


        //kollat mest på de här två för att få till det. Den andra säger att det ska vara för FXML men är inte det
//        https://www.youtube.com/watch?v=97nHAyMktTE&list=PL2EKpjm0bX4IWJ1ErhQZgrLPVgyqeP3L5&index=2
//        https://www.youtube.com/watch?v=4RNhPZJ84P0&list=PL2EKpjm0bX4IWJ1ErhQZgrLPVgyqeP3L5&index=6
//skapar kolumner
        TableColumn<Match, Integer> gameId = new TableColumn<>("Games");
        TableColumn<Match, Integer> team1_id = new TableColumn<>("Team 1");
        TableColumn<Match, Integer> team2_id = new TableColumn<>("Team 2");
        TableColumn<Match, Boolean> matchPlayed = new TableColumn<>("Match Played");
        TableColumn<Match, String> results = new TableColumn<>("Results");
        allMatchesTable.getColumns().addAll(gameId, team1_id, team2_id, matchPlayed, results);


        //skapar en lista med matchdata och lägger in data
        final ObservableList<Match> matchData = FXCollections.observableArrayList();  //se kommentarer längre ner för observable
        for (Match match : matchController.getAllMatchesNoPrint()) {
            matchData.add(match);
        }

        //kopplar ihop data med kolumnerna
        gameId.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        team1_id.setCellValueFactory(new PropertyValueFactory<>("team1_id"));
        team2_id.setCellValueFactory(new PropertyValueFactory<>("team2_id"));
        matchPlayed.setCellValueFactory(new PropertyValueFactory<>("Match played"));
        results.setCellValueFactory(new PropertyValueFactory<>("Results"));

        // Läger in data i kolumnerna
        // allMatchesTable.setItems(matchController.getAllMatchesNoPrint());
        allMatchesTable.setItems(matchData);

        Scene sceneShowAllMatches = new Scene(matchGroup);
        ((Group) sceneShowAllMatches.getRoot()).getChildren().add(allMatchesTable);


        matchStage.setScene(sceneShowAllMatches);
        matchStage.show();
    }
}


//observable, uppdateras eftersom om det ändras.... ex, testa kommentera bort och köra det här själv i main
//den "enkla/vanliga"
//        int a=10;
//        int b=10;
//        int sum=a+b;
//        System.out.println("vanlig "+sum); //ger 20
//        a=20;
//        System.out.println("vanlig "+sum); //ger fortfarande 20
//
//        //med observable. SimpleIntegerProperty är integers variant av observable
//        SimpleIntegerProperty obsA=new SimpleIntegerProperty(10);   //motsvarar int =10
//        SimpleIntegerProperty obsB=new SimpleIntegerProperty(10);   //motsvarar int =1
//
//        NumberBinding obsSum=obsA.add(obsB);                           // motsvarar a+b
//        System.out.println("med observable "+obsSum.getValue());
//        obsA.set(20);                                                   // motsvarar a=20; /att ändra värdet
//        System.out.println("med observable "+obsSum.getValue());