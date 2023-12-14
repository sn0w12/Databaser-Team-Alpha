package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.MatchController;
import com.teamalpha.teamalphapipergames.controller.PlayerController;
import com.teamalpha.teamalphapipergames.model.Match;
import com.teamalpha.teamalphapipergames.model.Player;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import java.util.List;
import java.util.Optional;


public class MatchGraphics extends Application {
    //  public void actionPerformed(ActionEvent){};

    MatchController matchController = new MatchController();
    PlayerController playerController = new PlayerController();

    @Override
    public void start(Stage matchStage) throws Exception {
        //Här skapar jag i princip bara en massa knappar som kallar på andra funktioner när man trycker på knappen


        matchStage.setTitle("Matches");
        matchStage.setWidth(500);
        matchStage.setHeight(500);

        //ruta för att lägga in knappar i
        VBox vBoxAlternatives = new VBox();

        //knappar för varje funktion skapa, visa,ändra, ta bort mm
        Button buttonAddMatch = new Button("Add new Match");
        buttonAddMatch.setOnAction(event -> {
            addMatch();
        });

        Button buttonShowAllMatches = new Button("Show all matches");
        buttonShowAllMatches.setOnAction(event -> {
            showAllMatchesTestWithJoinsOnPlayer();
        });

        Button buttonShowPlayedMatches = new Button("Show played matches");
        buttonShowPlayedMatches.setOnAction(event -> {
            showPlayedMatches();
        });

        Button buttonAddResults = new Button("Add result to played game");
        buttonAddResults.setOnAction(event -> {
            addResult();
        });

        Button buttonAlterMatch = new Button("Alter match");
        buttonAlterMatch.setOnAction(event -> {
            alterMatch();
        });

        Button buttonRemoveMatch = new Button("Remove match");
        buttonRemoveMatch.setOnAction(event -> {
            removeMatch();
        });

        //lägger till knapparna i rutan/fönstret
        vBoxAlternatives.getChildren().addAll(buttonAddMatch, buttonShowAllMatches, buttonShowPlayedMatches, buttonAddResults, buttonAlterMatch, buttonRemoveMatch);

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
        TableView<Match> allMatchesTable = new TableView<>();

        //kollat mest på de här två för att få till det. Den andra säger att det ska vara för FXML men är inte det
//        https://www.youtube.com/watch?v=97nHAyMktTE&list=PL2EKpjm0bX4IWJ1ErhQZgrLPVgyqeP3L5&index=2
//        https://www.youtube.com/watch?v=4RNhPZJ84P0&list=PL2EKpjm0bX4IWJ1ErhQZgrLPVgyqeP3L5&index=6
//skapar kolumner
        TableColumn<Match, Integer> matchId = new TableColumn<>("Match id");
        TableColumn<Match, Integer> gameId = new TableColumn<>("Game");
        TableColumn<Match, Integer> team1_id = new TableColumn<>("Team 1");
        TableColumn<Match, Integer> team2_id = new TableColumn<>("Team 2");
        TableColumn<Match, Integer> player1_id = new TableColumn<>("Player 1");
        TableColumn<Match, Integer> player2_id = new TableColumn<>("Player 2");
        TableColumn<Match, Boolean> matchPlayed = new TableColumn<>("Match Played");
        TableColumn<Match, String> results = new TableColumn<>("Results");
        allMatchesTable.getColumns().addAll(matchId, gameId, team1_id, team2_id, player1_id, player2_id, matchPlayed, results);


        //skapar en lista med matchdata och lägger in data
        final ObservableList<Match> matchData = FXCollections.observableArrayList();  //se kommentarer längre ner för observable
        //   for (Match match : matchController.getAllMatchesNoPrint()) {
        for (Match match : matchController.getAllMatches(false)) {
            matchData.add(match);
        }

        //kopplar ihop data med kolumnerna.   Det som är i "fritext" berättar vartifrån man tar det. Det ska vara samma
        // som namnet på ex int ifrån Match klassen. Alltså
//        @Column(name = "match_id")
//        int matchId;  --ska svara samma som den här

        matchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        gameId.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        team1_id.setCellValueFactory(new PropertyValueFactory<>("team1_id"));
        team2_id.setCellValueFactory(new PropertyValueFactory<>("team2_id"));
        player1_id.setCellValueFactory(new PropertyValueFactory<>("player1_id"));
        player2_id.setCellValueFactory(new PropertyValueFactory<>("player2_id"));
        matchPlayed.setCellValueFactory(new PropertyValueFactory<>("finished"));
        results.setCellValueFactory(new PropertyValueFactory<>("results"));

        // Läger in data i kolumnerna
        // allMatchesTable.setItems(matchController.getAllMatchesNoPrint());
        allMatchesTable.setItems(matchData);

        Scene sceneShowAllMatches = new Scene(matchGroup);
        ((Group) sceneShowAllMatches.getRoot()).getChildren().add(allMatchesTable);


        matchStage.setScene(sceneShowAllMatches);
        matchStage.show();
    }

    public void showAllMatchesTestWithJoinsOnPlayer() {
        Stage matchStage = new Stage();
        matchStage.setTitle("Show All Matches");
        matchStage.setWidth(500);
        matchStage.setHeight(500);

        //en tableView som är det enda som jag vill visa i den här sidan så behöver inte någon vbox eller liknande
        TableView<Match> allMatchesTable = new TableView<>();


//skapar kolumner
        TableColumn<Match, Integer> matchId = new TableColumn<>("Match id");
        TableColumn<Match, Integer> gameId = new TableColumn<>("Game");
        TableColumn<Match, Integer> team1_id = new TableColumn<>("Team 1");
        TableColumn<Match, Integer> team2_id = new TableColumn<>("Team 2");
        // TableColumn<Match, String> player1 = new TableColumn<>("Player1");
        //  TableColumn<Match, String> player2 = new TableColumn<>("Player2");
        TableColumn<Match, Player> player1 = new TableColumn<>("Player11");
        TableColumn<Match, Player> player2 = new TableColumn<>("Player22");


        TableColumn<Match, Boolean> matchPlayed = new TableColumn<>("Match Played");
        TableColumn<Match, String> results = new TableColumn<>("Results");
        allMatchesTable.getColumns().addAll(matchId, gameId, team1_id, team2_id, player1, player2, matchPlayed, results);


        //kopplar ihop data med kolumnerna.   Det som är i "fritext" berättar vartifrån man tar det. Det ska vara samma
        // som namnet på ex int ifrån Match klassen. Alltså
//        @Column(name = "match_id")
//        int matchId;  --ska svara samma som den här
        matchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        gameId.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        team1_id.setCellValueFactory(new PropertyValueFactory<>("team1_id"));
        team2_id.setCellValueFactory(new PropertyValueFactory<>("team2_id"));

//        player1.setCellValueFactory(new PropertyValueFactory<>("player1"));
//        player2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        player1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        player2.setCellValueFactory(new PropertyValueFactory<>("player2"));


        matchPlayed.setCellValueFactory(new PropertyValueFactory<>("finished"));
        results.setCellValueFactory(new PropertyValueFactory<>("results"));

        //skapar en lista matchdata och lägger in data
        List<Match> matchList = matchController.getAllMatchesNoPrint();
        final ObservableList<Match> matchData = FXCollections.observableArrayList(matchList);
        // Läger in data i kolumnerna


        //Lägger till all data i min tableview/fönster och bygger scenen med den
        allMatchesTable.setItems(matchData);

        Scene sceneShowAllMatches = new Scene(allMatchesTable);
        matchStage.setScene(sceneShowAllMatches);
        matchStage.show();
    }

    public void addMatch() {
        Stage addMatchStage = new Stage();
        addMatchStage.setTitle("Add new match");
        addMatchStage.setWidth(300);
        addMatchStage.setHeight(320);
        VBox vBoxAddMatch = new VBox();

//Label/text för att veta vad man ska skriva i textField under och en knapp addMatchButton som när man trycker
// på den så sker själva metoden att faktiskt spara matchen utifrån värdena man fyllt i i textFielden ovanför

        Label gameIdLabel = new Label("Enter game id");
        TextField gameIdTextField = new TextField();
        gameIdTextField.setMaxWidth(50);

        Label teamGameLabel = new Label("Team or single player game");
        CheckBox teamGameCheckBox = new CheckBox("Team game");


        Label player1IdLabel = new Label("Enter id for team/player 1");
        TextField player1IdTextFiled = new TextField();
        player1IdTextFiled.setMaxWidth(50);

        Label player2IdLabel = new Label("Enter id for team/player 2");
        TextField player2IdTextFiled = new TextField();
        player2IdTextFiled.setMaxWidth(50);

        Label dateLabel = new Label("Enter date of game");
        TextField dateTextField = new TextField();
        dateTextField.setMaxWidth(100);

        Button addMatchbutton = new Button("Add match");
        Label messageLabel = new Label();


        vBoxAddMatch.getChildren().addAll(gameIdLabel, gameIdTextField, teamGameLabel, teamGameCheckBox,
                player1IdLabel, player1IdTextFiled, player2IdLabel, player2IdTextFiled, dateLabel, dateTextField, addMatchbutton, messageLabel);


        addMatchbutton.setOnAction(event -> {
            int gameId;
            boolean teamGame = teamGameCheckBox.isSelected();
            int contestant1_id;
            int contestant2_id;
            String date;


            try {
                gameId = Integer.parseInt(gameIdTextField.getText());
                contestant1_id = Integer.parseInt(player1IdTextFiled.getText());
                contestant2_id = Integer.parseInt(player2IdTextFiled.getText());
                date = dateTextField.getText();

                matchController.addNewMatch(gameId, teamGame, contestant1_id, contestant2_id, date); //heter addNewMatch istället för save för jag testade att skriva om save och gjorde det med ett annat namn då
                messageLabel.setText("Match added, add new match or close window");

            } catch (Exception e) {
                System.out.println("kunde inte lägga till");
                messageLabel.setText("Failed to add new match");
            }
        });


        Scene createMatchScene = new Scene(vBoxAddMatch);
        addMatchStage.setScene(createMatchScene);
        addMatchStage.show();


//matchController.saveMatch();


    }

    public void showPlayedMatches() {
        Stage matchStage = new Stage();
        matchStage.setTitle("Played Matches");
        matchStage.setWidth(500);
        matchStage.setHeight(500);

        Group groupMatchPlayed = new Group();
        TableView<Match> allMatchesTable = new TableView<>();
//skapar kolumner
        TableColumn<Match, Integer> gameId = new TableColumn<>("Games");
        TableColumn<Match, Integer> team1_id = new TableColumn<>("Team 1");
        TableColumn<Match, Integer> team2_id = new TableColumn<>("Team 2");
        TableColumn<Match, Integer> player1_id = new TableColumn<>("Player 1");
        TableColumn<Match, Integer> player2_id = new TableColumn<>("Player 2");
        TableColumn<Match, Boolean> finished = new TableColumn<>("Match Played"); //ev ta bort sen men är med nu för att kunna se
        TableColumn<Match, String> results = new TableColumn<>("Results");
        allMatchesTable.getColumns().addAll(gameId, team1_id, team2_id, player1_id, player2_id, finished, results);


        //skapar en lista med matchdata och lägger in data
        final ObservableList<Match> matchData = FXCollections.observableArrayList();  //se kommentarer längre ner för observable
        for (Match match : matchController.getAllMatches(false)) {
            //  for (Match match : matchController.getAllMatchesNoPrint()) {
            matchData.add(match);
        }

        //kopplar ihop data med kolumnerna
        gameId.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        team1_id.setCellValueFactory(new PropertyValueFactory<>("team1_id"));
        team2_id.setCellValueFactory(new PropertyValueFactory<>("team2_id"));
        player1_id.setCellValueFactory(new PropertyValueFactory<>("player1_id"));
        player2_id.setCellValueFactory(new PropertyValueFactory<>("player2_id"));
        finished.setCellValueFactory(new PropertyValueFactory<>("Match played"));
        results.setCellValueFactory(new PropertyValueFactory<>("Results"));

        // Läger in data i kolumnerna
        allMatchesTable.setItems(matchData);

        Scene sceneShowAllMatches = new Scene(groupMatchPlayed);

        ((Group) sceneShowAllMatches.getRoot()).getChildren().add(allMatchesTable);


        matchStage.setScene(sceneShowAllMatches);
        matchStage.show();
    }

    public void addResult() {
        Stage resultStage = new Stage();
        resultStage.setTitle("Add result");
        resultStage.setWidth(250);
        resultStage.setHeight(180);

        //  HBox hBoxResults = new HBox();
        VBox vBoxResults = new VBox();
        //Label för att veta vad man ska skriva textfield att skriva i plus knapp för att lägga till
        Label matchIdLabel = new Label("Enter match id");
        Label resultLabel = new Label("Enter results");
        TextField matchIdTextField = new TextField();
        matchIdTextField.setMaxWidth(100);
        TextField resultTextField = new TextField();
        resultTextField.setMaxWidth(150);
        Button addResults = new Button("Add");
        Label messageLabel = new Label();

        //när man trycker på add så läggs data som man skrivit i textfielden till och resultatet uppdateras och man stänger fönstret
        addResults.setOnAction(event -> {
            try {
                int id = Integer.parseInt(matchIdTextField.getText()); //Sparar texten från fälten till int och string
                String results = resultTextField.getText();

                Match matchToAddResult = matchController.getMatchById(id); //anger id för match vi vill ge reultat till
                matchToAddResult.setResults(String.valueOf(results));      //anger resultatet

                if (results.contains("-")) {                         //Jag satte ett krav att resultatet måste innehålla "-"
                    if (matchController.updateMatch(matchToAddResult)) {
                        messageLabel.setText("Added result successfully. Add more results or close window");
                        System.out.println("Results added");
                    }
                }
            } catch (Exception e) {
                messageLabel.setText("Failed to add results");
                System.out.println("Failed to add results");
            }
        });

        vBoxResults.getChildren().

                addAll(matchIdLabel, matchIdTextField, resultLabel, resultTextField, addResults, messageLabel);

        Scene sceneAddResult = new Scene(vBoxResults);
        resultStage.setScene(sceneAddResult);
        resultStage.show();
    }


    public void alterMatch() {

        Stage alterMatchStage = new Stage();
        alterMatchStage.setTitle("Alter match");
        alterMatchStage.setWidth(300);
        alterMatchStage.setHeight(320);
        VBox vBoxAlterMatch = new VBox();

        Label matchIdLabel = new Label("Enter id for match to change");
        TextField matchIdTextField = new TextField();
        Label gameIdLabel = new Label("Enter id for new game");
        TextField gameIdTextField = new TextField();
        Label player1Label = new Label("Enter new id for player/team 1");
        TextField player1TextField = new TextField();
        Label player2Label = new Label("Enter new id for player/team 2");
        TextField player2TextField = new TextField();
        Label dateLabel = new Label("Enter new date for match");
        TextField dateTextField = new TextField();
        Button alterMatchButton = new Button("Change match");


        alterMatchButton.setOnAction(event -> {
            int matchId;
            int player1Id;
            int player2Id;
            String date;

            matchId = Integer.parseInt(matchIdTextField.getText());
            Match matchToUpdate = matchController.getMatchById(matchId);
            System.out.println("matchToUpdate.getTeamGame()" + matchToUpdate.getTeamGame());
            if (matchToUpdate.getTeamGame()) {
                //TODO ändra för team
            } else {
                //  ändra spelare 1

                try {
                    //  matchId = Integer.parseInt(matchIdTextField.getText());
                    player1Id = Integer.parseInt(player1TextField.getText());
                    System.out.println("changing player1 to id: " + player1Id);


                    //  Match matchToUpdate = matchController.getMatchById(matchId);
                    Player playerToChangeTo = playerController.getPlayerById(player1Id);

                    matchToUpdate.setPlayersByIndexInPlayersList(0, playerToChangeTo);
                    System.out.println("matchToUpdate.getPlayer1(): " + matchToUpdate.getPlayer1());
                    System.out.println("matchToUpdate.getPlayers().get(0).getFirstName(): " + matchToUpdate.getPlayers().get(0).getFirstName());

                    if (matchController.updateMatch(matchToUpdate)) {
                        System.out.println("lyckats ändra hoppas jag ");
                    } else {
                        System.out.println("nähä inte nu heller");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

                //ändra spelare 2
                try {
                    // matchId = Integer.parseInt(matchIdTextField.getText());
                    player2Id = Integer.parseInt(player2TextField.getText());
                    System.out.println("changing player2 to id: " + player2Id);

                    //  Match matchToUpdate = matchController.getMatchById(matchId);
                    Player playerToChangeTo = playerController.getPlayerById(player2Id);

                    matchToUpdate.setPlayersByIndexInPlayersList(1, playerToChangeTo);
                    System.out.println("matchToUpdate.getPlayer1(): " + matchToUpdate.getPlayer2());
                    System.out.println("matchToUpdate.getPlayers().get(0).getFirstName(): " + matchToUpdate.getPlayers().get(1).getFirstName());

                    if (matchController.updateMatch(matchToUpdate)) {
                        System.out.println("lyckats ändra hoppas jag ");
                    } else {
                        System.out.println("nähä inte nu heller");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

            }

            //TODO lägga till funktion för att ändra spel och datum

        });


        vBoxAlterMatch.getChildren().addAll(matchIdLabel, matchIdTextField, gameIdLabel, gameIdTextField, player1Label, player1TextField, player2Label, player2TextField,
                dateLabel, dateTextField, alterMatchButton);

        Scene scene = new Scene(vBoxAlterMatch);
        alterMatchStage.setScene(scene);
        alterMatchStage.show();


//TODO skapa hela metoden här ... men först en promenad
    }

    public void removeMatch() {
        Stage removeMatchStage = new Stage();
        removeMatchStage.setTitle("Remove match");
        removeMatchStage.setWidth(270);
        removeMatchStage.setHeight(120);

        VBox vBoxRemoveMatch = new VBox();
        Label removeMatchLabel = new Label("Enter id of match to delete");
        TextField removeMatchIdTextField = new TextField();
        Button removeMatchButton = new Button("Remove match");
        Label messageLabel = new Label();

        removeMatchButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(removeMatchIdTextField.getText());

                if (id > 0 && id < matchController.getAllMatchesNoPrint().size()+1) {
                    matchController.removePlayerFromMatch(id);  //tar bort spelarna ifrån matchens lista med spelare
                 //  playerController.removeMatchFromPlayer(id); //ta bort sen ev
                    if (matchController.deleteMatch(id)) {
                        messageLabel.setText("Match removed");
                    }
                } else {
                    messageLabel.setText("Match id out of bounds");
                }
            } catch (Exception e) {
                messageLabel.setText("Failed to remove match");
            }

        });

        vBoxRemoveMatch.getChildren().addAll(removeMatchLabel, removeMatchIdTextField, removeMatchButton, messageLabel);
        Scene sceneRemoveMatch = new Scene(vBoxRemoveMatch);
        removeMatchStage.setScene(sceneRemoveMatch);
        removeMatchStage.show();

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