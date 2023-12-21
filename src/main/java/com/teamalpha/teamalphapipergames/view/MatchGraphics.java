package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Game;
import com.teamalpha.teamalphapipergames.model.Match;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


//TODO Få det att funka med att ta bort en match
public class MatchGraphics extends Application {

    Stage stage;
    private GameController gameController;
    private TeamController teamController;
    private PlayerController playerController;
    private MatchController matchController;
    private StaffController staffController;

    public MatchGraphics(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController) {
        this.gameController = gameController;
        this.teamController = teamController;
        this.playerController = playerController;
        this.matchController = matchController;
        this.staffController = staffController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            this.stage = primaryStage;
            Platform.runLater(() -> {
                mainWindow(stage);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void mainWindow(Stage matchStage) {
        matchStage.setTitle("Matches");
        matchStage.setMinHeight(800);
        matchStage.setMinWidth(750);
//        matchStage.setWidth(800);
//        matchStage.setHeight(600);


        //TODO sätta vit text på alla labels
        // centrera/få saker snyggare placerade
        //TODO fixa så när man skapar en ny match med teams ifrån olika games ska lable ändras
        //fixa så storlek på fönstret så den håller sig samma även om man backar och går tillbaka

        AtomicBoolean showAllMatches = new AtomicBoolean(true);
        AtomicBoolean showPlayedMatches = new AtomicBoolean(false);

        //skapar en vbox för tabellen och sätter att jag vill visa alla matcher när man startar upp
        VBox vBoxMatchesShown = new VBox();
        vBoxMatchesShown.getChildren().add(showMatches(true, false));

        //Skapar knappar för att välja vilka matcher man vill visa
        Button buttonShowAllMatches = new Button("Show all matches");
        buttonShowAllMatches.setOnAction(event -> {
            showAllMatches.set(true);
            vBoxMatchesShown.getChildren().set(0, showMatches(true, true));
        });

        Button buttonShowPlayedMatches = new Button("Show played matches");
        buttonShowPlayedMatches.setOnAction(event -> {
            showAllMatches.set(false);
            showPlayedMatches.set(true);
            vBoxMatchesShown.getChildren().set(0, showMatches(false, true));
        });

        Button buttonShowUnplayedMatches = new Button("Show unplayed matches");
        buttonShowUnplayedMatches.setOnAction(event -> {
            showAllMatches.set(false);
            showPlayedMatches.set(false);
            vBoxMatchesShown.getChildren().set(0, showMatches(false, false));
        });

        //Lägger in knapparna för att välja matcher att visa i en vBox som läggs högst upp i fönstret
        HBox hBoxChooseMatchesToShow = new HBox();
        hBoxChooseMatchesToShow.getChildren().addAll(buttonShowAllMatches, buttonShowPlayedMatches, buttonShowUnplayedMatches);


        //En vBox för att lägga in alla knappar där man väljer vad man vill göra
        VBox vBoxButtons = new VBox();

        //Skapar en vbox där det visas vad man vill göra och man kan fylla i vad man vill göra
        VBox vBoxChange = new VBox();
        Label empty = new Label(); //lägger till en tom lable för att det ska finnas ett index att byta till
        vBoxChange.getChildren().add(0, empty);


        //knappar för varje funktion skapa, visa,ändra, ta bort mm
        Button buttonAddMatch = new Button("Add new Match");
        buttonAddMatch.setOnAction(event -> {
            vBoxChange.getChildren().set(0, addMatch(showAllMatches, showPlayedMatches, vBoxMatchesShown));
        });

        Button buttonAddResults = new Button("Add result to played game");
        buttonAddResults.setOnAction(event -> {
            vBoxChange.getChildren().set(0, addResult(showAllMatches, showPlayedMatches, vBoxMatchesShown));
        });

        Button buttonAlterMatch = new Button("Alter match");
        buttonAlterMatch.setOnAction(event -> {
            vBoxChange.getChildren().set(0, alterMatchTestPlayerAndTeam(showAllMatches, showPlayedMatches, vBoxMatchesShown));
        });

        Button buttonRemoveMatch = new Button("Remove match");
        buttonRemoveMatch.setOnAction(event -> {
            vBoxChange.getChildren().set(0, removeMatch(showAllMatches, showPlayedMatches, vBoxMatchesShown));
        });

        Button buttonBack = new Button("Back");
        buttonBack.setOnAction(event -> {
            StaffMainMenu staffMainMenu = new StaffMainMenu(gameController, teamController, playerController, matchController, staffController);
            try {
                staffMainMenu.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            matchStage.close();
        });

//        Button buttonUpdateTable = new Button("Update table");
//        buttonUpdateTable.setOnAction(event -> {
//            // Fetch data
//            ObservableList<Match> updatedMatchData = FXCollections.observableArrayList(matchController.getAllMatches());
//            if (showAllMatches.get()) {
//                vBoxMatchesShown.getChildren().set(0, showMatches(true, false));
//                //vBoxMatchesShown.getChildren().addAll(showMatches(true, false, updatedMatchData));
//            } else {
//                if (showPlayedMatches.get()) {
//                    //vBoxMatchesShown.getChildren().addAll(showMatches(false, true, updatedMatchData));
//                    vBoxMatchesShown.getChildren().set(0, showMatches(false, true));
//                } else {
//                    vBoxMatchesShown.getChildren().set(0, showMatches(false, false));
//                }
//            }
//        });


        // setVBoxBackGround(vBoxButtons);
        vBoxButtons.getChildren().addAll(buttonAddMatch, buttonAddResults, buttonAlterMatch, buttonRemoveMatch, buttonBack);
        vBoxButtons.setAlignment(Pos.CENTER);
        vBoxButtons.setSpacing(10);

        //skapar en Hbox där jag lägger in vboxButtons och vboxChange (läggs längst ner i fönstret)
        HBox hboxChoises = new HBox();
        hboxChoises.setMinHeight(280);  //TODO se till att textfielt får plats att visas efter man ex lagt till en match
        hboxChoises.getChildren().addAll(vBoxButtons, vBoxChange);
        hboxChoises.setSpacing(100);

        //För design, skapar en lista med alla knappar och sätter design
        Button[] buttonList = {buttonAddMatch, buttonShowAllMatches, buttonShowPlayedMatches, buttonShowUnplayedMatches, buttonAddResults, buttonAlterMatch, buttonRemoveMatch, buttonBack};
        for (Button button : buttonList) {
            button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
            button.setOnMouseEntered(event -> button.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
            button.setOnMouseExited(event -> button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
            button.setPrefWidth(190);
            button.setAlignment(Pos.BASELINE_LEFT);
        }


        VBox vBoxAll = new VBox();
        vBoxAll.setStyle("-fx-background-color: #174b54;");
        vBoxAll.getChildren().addAll(hBoxChooseMatchesToShow, vBoxMatchesShown, hboxChoises);
        Scene sceneAlternatives = new Scene(vBoxAll, 800, 750);
        matchStage.setScene(sceneAlternatives);
        matchStage.show();
    }


    public VBox showMatches(boolean showAllmatches, boolean showPlayedMatches) {
        TableView<Match> allMatchesTable = new TableView<>();

//skapar kolumner
        TableColumn<Match, Integer> matchId = new TableColumn<>("Match id");
        TableColumn<Match, String> game = new TableColumn<>("Game");
        TableColumn<Match, String> team1 = new TableColumn<>("Team1");
        TableColumn<Match, String> team2 = new TableColumn<>("Team2");
        TableColumn<Match, String> player1 = new TableColumn<>("Player1");
        TableColumn<Match, String> player2 = new TableColumn<>("Player2");
        TableColumn<Match, Boolean> matchPlayed = new TableColumn<>("Match Played");
        TableColumn<Match, LocalDate> matchDate = new TableColumn<>("Match date");
        TableColumn<Match, String> results = new TableColumn<>("Results");
        TableColumn<Match, String> winner = new TableColumn<>("Winner");


        //kopplar ihop data med kolumnerna
        matchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        game.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGame().getName()));

        team1.setCellValueFactory(cellData -> {
            Match match = cellData.getValue();
            if (match.getTeams().isEmpty()) {
                return new SimpleStringProperty("-");
            } else {
                return new ReadOnlyStringWrapper(cellData.getValue().getTeams().get(0).getName());
            }
        });

        team2.setCellValueFactory(cellData -> {
            Match match = cellData.getValue();
            if (match.getTeams().isEmpty()) {
                return new SimpleStringProperty("-");
            } else {
                return new SimpleStringProperty(cellData.getValue().getTeams().get(1).getName());
            }
        });

        player1.setCellValueFactory(cellData -> {
            Match match = cellData.getValue();
            if (match.getPlayers().isEmpty()) {
                return new SimpleStringProperty("-");
            } else return new SimpleStringProperty(cellData.getValue().getPlayers().get(0).getFirstName());
        });

        player2.setCellValueFactory(cellData -> {
            Match match = cellData.getValue();
            if (match.getPlayers().isEmpty()) {
                return new SimpleStringProperty("-");
            } else return new SimpleStringProperty(cellData.getValue().getPlayers().get(1).getFirstName());
        });
        matchPlayed.setCellValueFactory(new PropertyValueFactory<>("matchPlayed"));
        matchDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        results.setCellValueFactory(new PropertyValueFactory<>("results"));
        winner.setCellValueFactory(new PropertyValueFactory<>("winner"));


        //skapar listor med alla/spelade/ospelade matcher
        List<Match> allMatches = matchController.getAllMatches();
        List<Match> playedMatches = new ArrayList<>();
        List<Match> unplayedMatches = new ArrayList<>();
        for (Match match : allMatches) {
            if (match.getMatchPlayed()) {
                playedMatches.add(match);
            } else {
                unplayedMatches.add(match);
            }
        }


//skapar en observable list och lägger in listorna som skapades ovanför beroende på vad som ska visas alla/spelade/ospelade matcher
        ObservableList<Match> matchData;
        if (showAllmatches) {
            matchData = FXCollections.observableArrayList(allMatches);
            allMatchesTable.getColumns().addAll(matchId, game, team1, team2, player1, player2, matchPlayed, matchDate, results, winner);
        } else {
            if (showPlayedMatches) {
                matchData = FXCollections.observableArrayList(playedMatches);
                allMatchesTable.getColumns().addAll(matchId, game, team1, team2, player1, player2, matchPlayed, matchDate, results, winner);
            } else {
                matchData = FXCollections.observableArrayList(unplayedMatches);
                allMatchesTable.getColumns().addAll(matchId, game, team1, team2, player1, player2, matchDate);
            }
        }

        //lägger in olika listor i tabellen beroende på vilka matcher som ska visas
        allMatchesTable.setItems(matchData);
        allMatchesTable.setPrefSize(700, 700);

        //skapar en vBox där jag lägger in tabellen och returnerar
        VBox vbox = new VBox();
        vbox.setPrefSize(700, 700);
        vbox.getChildren().add(allMatchesTable);
        vbox.setStyle("-fx-background-color: #174b54;");
        return vbox;
    }

    public VBox addMatch(AtomicBoolean showAllMatches, AtomicBoolean showPlayedMatches, VBox vBoxMatchesShown) {

//Label/textfield/button
        Label gameIdLabel = new Label("Enter game id");
        gameIdLabel.setTextFill(Color.WHITE);
        TextField gameIdTextField = new TextField();
        gameIdTextField.setMaxWidth(50);

        Label teamGameLabel = new Label("Team or single player game");
        teamGameLabel.setTextFill(Color.WHITE);
        CheckBox teamGameCheckBox = new CheckBox("Team game");
        teamGameCheckBox.setTextFill(Color.WHITE);

        Label player1IdLabel = new Label("Enter id for team/player 1");
        player1IdLabel.setTextFill(Color.WHITE);
        TextField player1IdTextFiled = new TextField();
        player1IdTextFiled.setMaxWidth(50);

        Label player2IdLabel = new Label("Enter id for team/player 2");
        player2IdLabel.setTextFill(Color.WHITE);
        TextField player2IdTextFiled = new TextField();
        player2IdTextFiled.setMaxWidth(50);

        Label dateLabel = new Label("Enter date of game \"yyyy-mm-dd\"");
        dateLabel.setTextFill(Color.WHITE);
        TextField dateTextField = new TextField();
        dateTextField.setMaxWidth(100);

        Button addMatchbutton = new Button("Add match");
        setButtonStyle(addMatchbutton);
        Label messageLabel = new Label();

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
                String[] dateAsList = date.split("-");

                int year = Integer.parseInt(dateAsList[0]);
                int monthInteger = Integer.parseInt(dateAsList[1]);
                int dayInteger = Integer.parseInt(dateAsList[2]);

                if (matchController.addNewMatch(gameId, teamGame, contestant1_id, contestant2_id, LocalDate.of(year, monthInteger, dayInteger))) {  //heter addNewMatch istället för save för jag testade att skriva om save och gjorde det med ett annat namn då
                    updateTable(showAllMatches, showPlayedMatches, vBoxMatchesShown);
                    messageLabel.setText("Match added, add new match or close window");
                } else {
                    messageLabel.setText("failed to add match");
                }

            } catch (Exception e) {
                System.out.println("kunde inte lägga till");
                messageLabel.setText("Failed to add new match");
            }
        });


        //lägger till allt i en vbox och returnerar
        VBox vBoxAddMatch = new VBox();
        vBoxAddMatch.getChildren().addAll(gameIdLabel, gameIdTextField, teamGameLabel, teamGameCheckBox, player1IdLabel, player1IdTextFiled, player2IdLabel, player2IdTextFiled, dateLabel, dateTextField, addMatchbutton, messageLabel);
        vBoxAddMatch.setStyle("-fx-background-color: #174b54;");
        return vBoxAddMatch;
    }

    public VBox addResult(AtomicBoolean showAllMatches, AtomicBoolean showPlayedMatches, VBox vBoxMatchesShown) {


        //Label för att veta vad man ska skriva textfield att skriva i plus knapp för att lägga till
        Label matchIdLabel = new Label("Enter match id");
        matchIdLabel.setTextFill(Color.WHITE);
        Label resultLabel = new Label("Enter results, \"xx-xx\"");
        resultLabel.setTextFill(Color.WHITE);
        TextField matchIdTextField = new TextField();
        matchIdTextField.setMaxWidth(100);
        TextField resultTextField = new TextField();
        resultTextField.setMaxWidth(100);
        Button addResults = new Button("Add");
        setButtonStyle(addResults);
        addResults.setAlignment(Pos.BASELINE_LEFT);
        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.WHITE);


        addResults.setOnAction(event -> {
            try {
                int matchId = Integer.parseInt(matchIdTextField.getText()); //Sparar texten från fälten till int och string
                Match matchToAddResult = matchController.getMatchById(matchId);

                if (matchToAddResult.getMatchPlayed()) {
                    String results = resultTextField.getText();

                    if (results.contains("-")) {                         //Jag satte ett krav att resultatet måste innehålla "-"
                        matchToAddResult.setResults(results);            //anger resultatet
                        setWinner(matchToAddResult, results);
                        if (matchController.updateMatch(matchToAddResult)) {
                            messageLabel.setText("Result added");
                            updateTable(showAllMatches, showPlayedMatches, vBoxMatchesShown);
                        }
                    } else {
                        messageLabel.setText("enter results correctly \"xx-xx\"");
                    }
                } else {
                    messageLabel.setText("Match not played");
                }
            } catch (Exception e) {
                messageLabel.setText("Failed to add results");
            }
        });


        VBox vBoxResults = new VBox();
        vBoxResults.getChildren().addAll(matchIdLabel, matchIdTextField, resultLabel, resultTextField, addResults, messageLabel);
        vBoxResults.setStyle("-fx-background-color: #174b54;");
        return vBoxResults;
    }

    public void setWinner(Match match, String results) {
        // Splittar upp resultat strängen och skapar två int ifrån den
        String[] resultsSplit = results.split("-");
        int result1 = Integer.parseInt(resultsSplit[0]);
        int result2 = Integer.parseInt(resultsSplit[1]);

        //beroende på vilken result som är högst sätts vinnaren. Om de är samma sätts tie
        if (result1 > result2) {
            if (match.getTeamGame()) {
                match.setWinner(match.getTeams().get(0).getName());
            } else {
                match.setWinner(match.getPlayers().get(0).getFirstName());
            }
        } else if (result1 < result2) {
            if (match.getTeamGame()) {
                match.setWinner(match.getTeams().get(1).getName());
            } else {
                match.setWinner(match.getPlayers().get(1).getFirstName());
            }
        } else {
            match.setWinner("Tie");
        }
    }

//    public VBox alterMatchTestPlayerAndTeam(AtomicBoolean showAllMatches, AtomicBoolean showPlayedMatches, VBox vBoxMatchesShown) {
//
//        Label matchIdLabel = new Label("Enter id for match to change");
//        matchIdLabel.setTextFill(Color.WHITE);
//        TextField matchIdTextField = new TextField();
//        Label gameIdLabel = new Label("Enter id for new game");
//        gameIdLabel.setTextFill(Color.WHITE);
//        TextField gameIdTextField = new TextField();
//        Label playerOrTeam1Label = new Label("Enter new id for player/team 1");
//        playerOrTeam1Label.setTextFill(Color.WHITE);
//        TextField playerOrTeam1TextField = new TextField();
//        Label playerOrTeam2Label = new Label("Enter new id for player/team 2");
//        playerOrTeam2Label.setTextFill(Color.WHITE);
//        TextField playerOrTeam2TextField = new TextField();
//        Label dateLabel = new Label("Enter new date for match");
//        dateLabel.setTextFill(Color.WHITE);
//        TextField dateTextField = new TextField();
//        Button alterMatchButton = new Button("Change match");
//        setButtonStyle(alterMatchButton);
//
//
//        alterMatchButton.setOnAction(event -> {
//            int matchId;
//            int idOfNewplayerOrTeam;
//
//
//            matchId = Integer.parseInt(matchIdTextField.getText());
//            Match matchToUpdate = matchController.getMatchById(matchId);
//            if (matchToUpdate.getTeamGame()) {
//
//                //ändra team1
//                if (!playerOrTeam1TextField.getText().isEmpty()) {
//                    try {
//                        idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam1TextField.getText());
//
//                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 0, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
//                            System.out.println("lyckats ändra hoppas jag ");
//                        } else {
//                            System.out.println("nähä inte nu heller");
//                        }
//                    } catch (Exception e) {
//                        System.out.println(e + "fel för jag försöker inte ändra player 2");
//                    }
//                }
//                if (!playerOrTeam2TextField.getText().isEmpty()) {
//                    //ändra team2
//                    try {
//                        matchId = Integer.parseInt(matchIdTextField.getText());
//
//                        idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam2TextField.getText());
//                        System.out.println("changing team1 to id: " + idOfNewplayerOrTeam);
//
//                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 1, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
//                            System.out.println("lyckats ändra hoppas jag ");
//                        } else {
//                            System.out.println("nähä inte nu heller");
//                        }
//                    } catch (Exception e) {
//                        System.out.println(e + "fel för jag försöker inte ändra player 2");
//                    }
//                }
//            } else {
//
//
//
//
//                if (playerOrTeam1TextField.getText() != null) {       //  ändra spelare 1
//                    try {
//                        matchId = Integer.parseInt(matchIdTextField.getText());
//                        idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam1TextField.getText());
//                        System.out.println("changing player1 to id: " + idOfNewplayerOrTeam);
//
//                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 0, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
//                            System.out.println("lyckats ändra hoppas jag ");
//                        } else {
//                            System.out.println("nähä inte nu heller");
//                        }
//                    } catch (Exception e) {
//                        System.out.println(e);
//                    }
//                }
//
//                if (playerOrTeam2TextField.getText() != null) {     //ändra spelare 2
//                    try {
//                        matchId = Integer.parseInt(matchIdTextField.getText());
//                        idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam2TextField.getText());
//                        System.out.println("changing player2 to id: " + idOfNewplayerOrTeam);
//
//                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 1, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
//                            System.out.println("lyckats ändra hoppas jag ");
//                        } else {
//                            System.out.println("nähä inte nu heller");
//                        }
//                    } catch (Exception e) {
//                        System.out.println(e + "fel för jag försöker inte ändra player 2");
//                    }
//                }
//            }
//
//            //ändra datum
//            String[] dateList = dateTextField.getText().split("-");
//            if (dateList != null) {
//
//                try {
//                    int year = Integer.parseInt(dateList[0]);
//                    int month = Integer.parseInt(dateList[1]);
//                    int day = Integer.parseInt(dateList[2]);
//
//                    matchToUpdate.setMatchDate(LocalDate.of(year, month, day));
//
//                    if (matchController.updateMatch(matchToUpdate)) {
//                        System.out.println("Date updated");
//                    }
//                } catch (Exception e) {
//                }
//            }
//
//            //TODO lägga till funktion för att ändra spel
//
//
//            updateTable(showAllMatches, showPlayedMatches, vBoxMatchesShown);
//        });
//
//
//
//        VBox vBoxAlterMatch = new VBox();
//        vBoxAlterMatch.getChildren().addAll(matchIdLabel, matchIdTextField, gameIdLabel, gameIdTextField, playerOrTeam1Label, playerOrTeam1TextField, playerOrTeam2Label, playerOrTeam2TextField,
//                dateLabel, dateTextField, alterMatchButton);
//        vBoxAlterMatch.setStyle("-fx-background-color: #174b54;");
//        return vBoxAlterMatch;
//    }

//    public VBox alterMatchTestPlayerAndTeam(AtomicBoolean showAllMatches, AtomicBoolean showPlayedMatches, VBox vBoxMatchesShown) {
//
//        Label matchIdLabel = new Label("Enter id for match to change");
//        matchIdLabel.setTextFill(Color.WHITE);
//        TextField matchIdTextField = new TextField();
//        Label gameIdLabel = new Label("Enter id for new game");
//        gameIdLabel.setTextFill(Color.WHITE);
//        TextField gameIdTextField = new TextField();
//        Label playerOrTeam1Label = new Label("Enter new id for player/team 1");
//        playerOrTeam1Label.setTextFill(Color.WHITE);
//        TextField playerOrTeam1TextField = new TextField();
//        Label playerOrTeam2Label = new Label("Enter new id for player/team 2");
//        playerOrTeam2Label.setTextFill(Color.WHITE);
//        TextField playerOrTeam2TextField = new TextField();
//        Label dateLabel = new Label("Enter new date for match");
//        dateLabel.setTextFill(Color.WHITE);
//        TextField dateTextField = new TextField();
//        Button alterMatchButton = new Button("Change match");
//        setButtonStyle(alterMatchButton);
//        Label messageLabel = new Label();
//        messageLabel.setTextFill(Color.WHITE);
//
//        alterMatchButton.setOnAction(event -> {
//            int idOfNewplayerOrTeam1;
//            int idOfNewplayerOrTeam2;
//
//            try {
//                int matchId = Integer.parseInt(matchIdTextField.getText());
//                Match matchToUpdate = matchController.getMatchById(matchId);
//
//                //ändra team
//                if (matchToUpdate.getTeamGame()) {
//
//                    //ändra team1
//                    if (!playerOrTeam1TextField.getText().isEmpty()) {
//                        idOfNewplayerOrTeam1 = Integer.parseInt(playerOrTeam1TextField.getText());
//                        if (idOfNewplayerOrTeam1 > 0 && idOfNewplayerOrTeam1 < (teamController.getAll(false).size() + 1)) {
//                            if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 0, idOfNewplayerOrTeam1)) {  //står noll där för det är spelare ett jag vill ändra
//                                System.out.println("lyckats ändra hoppas jag ");
//                            } else {
//                                System.out.println("nähä inte nu heller");
//                            }
//                        } else {
//                            messageLabel.setText("Team1 id out of bounce");
//                        }
//                    }
//                    if (!playerOrTeam2TextField.getText().isEmpty()) {
//                        //ändra team2
//                        idOfNewplayerOrTeam2 = Integer.parseInt(playerOrTeam2TextField.getText());
//                        if (idOfNewplayerOrTeam2 > 0 && idOfNewplayerOrTeam2 < (teamController.getAll(false).size() + 1)) {
//                            System.out.println("changing team1 to id: " + idOfNewplayerOrTeam2);
//
//                            if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 1, idOfNewplayerOrTeam2)) {  //står noll där för det är spelare ett jag vill ändra
//                                System.out.println("lyckats ändra hoppas jag ");
//                            } else {
//                                System.out.println("nähä inte nu heller");
//                            }
//                        } else {
//                            messageLabel.setText("Team1 id out of bounce");
//                        }
//                    }
//
//                } else { //ändra player
//                    //  ändra spelare 1
//                    if (!playerOrTeam1TextField.getText().isEmpty()) {
//                        idOfNewplayerOrTeam1 = Integer.parseInt(playerOrTeam1TextField.getText());
//                        if (idOfNewplayerOrTeam1 > 0 && idOfNewplayerOrTeam1 < (playerController.getAll(false).size() + 1)) {
//                            if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 0, idOfNewplayerOrTeam1)) {  //står noll där för det är spelare ett jag vill ändra
//                                System.out.println("lyckats ändra hoppas jag ");
//                            } else {
//                                System.out.println("nähä inte nu heller");
//                            }
//                        } else {
//                            messageLabel.setText("Team1 id out of bounce");
//                        }
//                    }
//                    //ändra spelare 2
//                    if (!playerOrTeam2TextField.getText().isEmpty()) {
//                        idOfNewplayerOrTeam2 = Integer.parseInt(playerOrTeam2TextField.getText());
//                        if (idOfNewplayerOrTeam2 > 0 && idOfNewplayerOrTeam2 < (playerController.getAll(false).size() + 1)) {
//                            System.out.println("changing player2 to id: " + idOfNewplayerOrTeam2);
//
//                            if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 1, idOfNewplayerOrTeam2)) {  //står noll där för det är spelare ett jag vill ändra
//                                System.out.println("lyckats ändra hoppas jag ");
//                            } else {
//                                System.out.println("nähä inte nu heller");
//                            }
//                        } else {
//                            messageLabel.setText("Player2 id out of bounce");
//                        }
//                    }
//
//                    //ändra datum
//                    if (!dateTextField.getText().isEmpty()) {
//                        String[] dateList = dateTextField.getText().split("-");
//                        int year = Integer.parseInt(dateList[0]);
//                        int month = Integer.parseInt(dateList[1]);
//                        int day = Integer.parseInt(dateList[2]);
//
//                        matchToUpdate.setMatchDate(LocalDate.of(year, month, day));
//
//                        if (matchController.updateMatch(matchToUpdate)) {
//                            System.out.println("Date updated");
//                        }
//                    }
//
//                    messageLabel.setText("match updated");
//
//                    //TODO lägga till funktion för att ändra spel
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//                messageLabel.setText("Failed to update match");
//            }
//            updateTable(showAllMatches, showPlayedMatches, vBoxMatchesShown);
//        });
//
//
//        VBox vBoxAlterMatch = new VBox();
//        vBoxAlterMatch.getChildren().addAll(matchIdLabel, matchIdTextField, gameIdLabel, gameIdTextField, playerOrTeam1Label, playerOrTeam1TextField, playerOrTeam2Label, playerOrTeam2TextField,
//                dateLabel, dateTextField, alterMatchButton, messageLabel);
//        vBoxAlterMatch.setStyle("-fx-background-color: #174b54;");
//        return vBoxAlterMatch;
//    }

    public VBox alterMatchTestPlayerAndTeam(AtomicBoolean showAllMatches, AtomicBoolean showPlayedMatches, VBox vBoxMatchesShown) {

        Label matchIdLabel = new Label("Enter id for match to change");
        matchIdLabel.setTextFill(Color.WHITE);
        TextField matchIdTextField = new TextField();
        Label gameIdLabel = new Label("Enter id for new game");
        gameIdLabel.setTextFill(Color.WHITE);
        TextField gameIdTextField = new TextField();
        Label playerOrTeam1Label = new Label("Enter new id for player/team 1");
        playerOrTeam1Label.setTextFill(Color.WHITE);
        TextField playerOrTeam1TextField = new TextField();
        Label playerOrTeam2Label = new Label("Enter new id for player/team 2");
        playerOrTeam2Label.setTextFill(Color.WHITE);
        TextField playerOrTeam2TextField = new TextField();
        Label dateLabel = new Label("Enter new date for match");
        dateLabel.setTextFill(Color.WHITE);
        TextField dateTextField = new TextField();
        Button alterMatchButton = new Button("Change match");
        setButtonStyle(alterMatchButton);
        Label messageLabel = new Label("");
        messageLabel.setTextFill(Color.WHITE);


        alterMatchButton.setOnAction(event -> {
                    int matchId;
                    int idOfNewplayerOrTeam;
                    messageLabel.setText(" ");
                    try {
                        matchId = Integer.parseInt(matchIdTextField.getText());
                        Match matchToUpdate = matchController.getMatchById(matchId);


                        //ändra game- kan bara ändra game på matcher som är player
                        if (!gameIdTextField.getText().isEmpty()) {

                            try {
                                int gameId = Integer.parseInt(gameIdTextField.getText());

                                if (!matchToUpdate.getTeamGame()) {
                                    matchToUpdate.setGame(gameController.getGameByIdUniversal(gameId));
                                    if (matchController.updateMatch(matchToUpdate)) {
                                        messageLabel.setText("Updated");
                                        System.out.println("Game updated");
                                    } else {
                                        messageLabel.setText("Failed to update Game");
                                    }
                                } else {
                                    messageLabel.setText("Failed, Can only change game for player matches");
                                }
                            } catch (Exception e) {
                                messageLabel.setText("Failed to update game");
                            }
                        }


                        //ändra team
                        if (matchToUpdate.getTeamGame()) {
                            //ändra team1
                            if (!playerOrTeam1TextField.getText().isEmpty()) {
                                try {
                                    idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam1TextField.getText());
                                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 0, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra

                                            if (!messageLabel.getText().contains("Failed")) {
                                                messageLabel.setText("Updated");
                                                System.out.println("lyckats ändra hoppas jag ");
                                            }
                                        } else {
                                            if (!messageLabel.getText().contains("Failed")) {
                                                messageLabel.setText("Failed to update team1");
                                                System.out.println("nähä inte nu heller");
                                            }
                                        }

                                } catch (Exception e) {
                                    System.out.println(e + "fel för jag försöker inte ändra player 2");
                                    if (!messageLabel.getText().contains("Failed")) {
                                        messageLabel.setText("Failed to update team1");
                                    }
                                }
                            }


                            //ändra team2
                            if (!playerOrTeam2TextField.getText().isEmpty()) {
                                try {
                                    idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam2TextField.getText());
                                        matchId = Integer.parseInt(matchIdTextField.getText());
                                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 1, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
                                            System.out.println("lyckats ändra hoppas jag ");
                                            if (!messageLabel.getText().contains("Failed")) {
                                                messageLabel.setText("Updated");
                                            }
                                        } else {
                                            if (!messageLabel.getText().contains("Failed")) {
                                                messageLabel.setText("Failed to update team2");
                                            }
                                        }

                                } catch (Exception e) {
                                    if (!messageLabel.getText().contains("Failed")) {
                                        messageLabel.setText("Failed to update team2");
                                    }
                                }
                            }
                        //ändra  player
                        } else { //  ändra player1
                            if (!playerOrTeam1TextField.getText().isEmpty()) {
                                try {
                                    idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam1TextField.getText());
                                        matchId = Integer.parseInt(matchIdTextField.getText());


                                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 0, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
                                            System.out.println("lyckats ändra hoppas jag ");
                                            if (!messageLabel.getText().contains("Failed")) {
                                                messageLabel.setText("Updated");
                                            }
                                        } else {
                                            System.out.println("nähä inte nu heller");
                                            if (!messageLabel.getText().contains("Failed")) {
                                                messageLabel.setText("Failed to update player1");
                                            }
                                        }
                                } catch (Exception e) {
                                    System.out.println(e);
                                    if (!messageLabel.getText().contains("Failed")) {
                                        messageLabel.setText("Failed to update player1");
                                    }
                                }
                            }


                            //ändra player2
                            if (!playerOrTeam2TextField.getText().isEmpty()) {
                                try {
                                    idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam2TextField.getText());

                                        matchId = Integer.parseInt(matchIdTextField.getText());
                                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 1, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
                                            System.out.println("lyckats ändra hoppas jag ");
                                            if (!messageLabel.getText().contains("Failed")) {
                                                messageLabel.setText("Updated");
                                            }
                                        } else {
                                            System.out.println("nähä inte nu heller");
                                            if (!messageLabel.getText().contains("Failed")) {
                                                messageLabel.setText("Failed to update player2");
                                            }
                                        }

                                } catch (Exception e) {
                                    System.out.println(e + "fel för jag försöker inte ändra player 2");
                                    if (!messageLabel.getText().contains("Failed")) {
                                        messageLabel.setText("Failed to update player2");
                                    }
                                }
                            }
                        }

                        //ändra datum
                        if (!dateTextField.getText().isEmpty()) {
                            try {
                                String[] dateList = dateTextField.getText().split("-");
                                int year = Integer.parseInt(dateList[0]);
                                int month = Integer.parseInt(dateList[1]);
                                int day = Integer.parseInt(dateList[2]);

                                matchToUpdate.setMatchDate(LocalDate.of(year, month, day));

                                if (matchController.updateMatch(matchToUpdate)) {
                                    if (!messageLabel.getText().contains("Failed")) {
                                        messageLabel.setText("Updated");
                                    }
                                    System.out.println("Date updated");
                                } else {
                                    if (!messageLabel.getText().contains("Failed")) {
                                        messageLabel.setText("Failed to update date");
                                    }
                                }
                            } catch (Exception e) {
                                if (!messageLabel.getText().contains("Failed")) {
                                    messageLabel.setText("Failed to update date");
                                }
                            }
                        }

                        if (!messageLabel.getText().contains("Failed")) {
                            updateTable(showAllMatches, showPlayedMatches, vBoxMatchesShown);
                        }

                    } catch (Exception e) {
                        messageLabel.setText("Failed to update match");
                    }
                }
        );


        VBox vBoxAlterMatch = new VBox();
        vBoxAlterMatch.getChildren().addAll(matchIdLabel, matchIdTextField, gameIdLabel, gameIdTextField, playerOrTeam1Label, playerOrTeam1TextField, playerOrTeam2Label, playerOrTeam2TextField,
                dateLabel, dateTextField, alterMatchButton, messageLabel);
        vBoxAlterMatch.setStyle("-fx-background-color: #174b54;");
        return vBoxAlterMatch;
    }

    public VBox removeMatch(AtomicBoolean showAllMatches, AtomicBoolean showPlayedMatches, VBox vBoxMatchesShown) {

        VBox vBoxRemoveMatch = new VBox();
        Label removeMatchLabel = new Label("Enter id of match to delete");
        removeMatchLabel.setTextFill(Color.WHITE);
        TextField removeMatchIdTextField = new TextField();
        removeMatchIdTextField.setMaxWidth(50);
        Button removeMatchButton = new Button("Remove match");
        setButtonStyle(removeMatchButton);
        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.WHITE);

        removeMatchButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(removeMatchIdTextField.getText());
                    if (matchController.removePlayerOrTeamFromMatch(id)) {
                        if (matchController.deleteMatch(id)) {
                            messageLabel.setText("Match removed");
                            updateTable(showAllMatches, showPlayedMatches, vBoxMatchesShown);
                        }
                    } else {
                        messageLabel.setText("Failed to delete match");
                    }
            } catch (Exception e) {
                messageLabel.setText("Failed to remove match");
            }

        });

        vBoxRemoveMatch.getChildren().addAll(removeMatchLabel, removeMatchIdTextField, removeMatchButton, messageLabel);
        vBoxRemoveMatch.setStyle("-fx-background-color: #174b54;");
        return vBoxRemoveMatch;
    }

    public void updateTable(AtomicBoolean showAllMatches, AtomicBoolean showPlayedMatches, VBox vBoxMatchesShown) {
        if (showAllMatches.get()) {
            vBoxMatchesShown.getChildren().set(0, showMatches(true, false));
        } else {
            if (showPlayedMatches.get()) {
                vBoxMatchesShown.getChildren().set(0, showMatches(false, true));
            } else {
                vBoxMatchesShown.getChildren().set(0, showMatches(false, false));
            }
        }
    }

    public void setButtonStyle(Button button) {
        button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
        button.setOnMouseEntered(event -> button.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
        button.setOnMouseExited(event -> button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
        button.setAlignment(Pos.BASELINE_LEFT);
        button.setPrefWidth(190);
    }
}