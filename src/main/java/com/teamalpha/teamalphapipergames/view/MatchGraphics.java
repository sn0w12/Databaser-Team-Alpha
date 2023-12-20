package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.Match;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchGraphics extends Application {
    Stage stage;
    private GameController gameController;
    private TeamController teamController;
    private PlayerController playerController;
    private MatchController matchController;
    private StaffController staffController;
    private TournamentController tournamentController;
    private final TableView<Match> allMatchesTable = new TableView<>();

    public MatchGraphics(GameController gameController, TeamController teamController, PlayerController playerController, MatchController matchController, StaffController staffController, TournamentController tournamentController) {
        this.gameController = gameController;
        this.teamController = teamController;
        this.playerController = playerController;
        this.matchController = matchController;
        this.staffController = staffController;
        this.tournamentController = tournamentController;
    }

    @Override
    public void start(Stage matchStage) throws Exception {
        try {
            this.stage = matchStage;
            Platform.runLater(() -> {
                mainWindow(stage);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainWindow(Stage matchStage) {
        matchStage.setTitle("Matches");
        matchStage.setWidth(500);
        matchStage.setHeight(500);


        //knappar för varje funktion skapa, visa,ändra, ta bort mm
        Button buttonAddMatch = new Button("Add new Match");
        buttonAddMatch.setOnAction(event -> {
            addMatch();
        });

        Button buttonShowAllMatches = new Button("Show all matches");
        buttonShowAllMatches.setOnAction(event -> {
            showMatches(true, false);
        });

        Button buttonShowPlayedMatches = new Button("Show played matches");
        buttonShowPlayedMatches.setOnAction(event -> {
            showMatches(false, true);
        });

        Button buttonShowUnplayedMatches = new Button("Show unplayed matches");
        buttonShowUnplayedMatches.setOnAction(event -> {
            showMatches(false, false);
        });

        Button buttonAddResults = new Button("Add result to played game");
        buttonAddResults.setOnAction(event -> {
            addResult();
        });

        Button buttonAlterMatch = new Button("Alter match");
        buttonAlterMatch.setOnAction(event -> {
            // alterMatch();
            alterMatchTestPlayerAndTeam();
        });

        Button buttonRemoveMatch = new Button("Remove match");
        buttonRemoveMatch.setOnAction(event -> {
            removeMatch();
        });


        //För design, skapar en lista med alla knappar och sätter design
        Button[] buttonList = {buttonAddMatch, buttonShowAllMatches, buttonShowPlayedMatches, buttonShowUnplayedMatches, buttonAddResults, buttonAlterMatch, buttonRemoveMatch};
        for (Button button : buttonList) {
            button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;");
            button.setOnMouseEntered(event -> button.setStyle("-fx-text-fill: #174b54; -fx-background-color: #75e8bd"));
            button.setOnMouseExited(event -> button.setStyle("-fx-text-fill: white; -fx-background-color: #174b54;"));
            button.setPrefWidth(190);
        }


        VBox vBoxAlternatives = new VBox();
        setVBoxBackGround(vBoxAlternatives);
        vBoxAlternatives.getChildren().addAll(buttonAddMatch, buttonShowAllMatches, buttonShowPlayedMatches, buttonShowUnplayedMatches, buttonAddResults, buttonAlterMatch, buttonRemoveMatch);

        //  Scene sceneAlternatives = new Scene(vBoxAlternatives, 800, 600);
        Scene sceneAlternatives = new Scene(vBoxAlternatives, 600, 450);
        // Scene sceneAlternatives = new Scene(vBoxAlternatives, 400, 300);
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
        for (Match match : matchController.getAllMatches()) {
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

    public void showMatches(boolean showAllmatches, boolean showPlayedMatches) {
        Stage matchStage = new Stage();
        matchStage.setTitle("Show All Matches");
        matchStage.setWidth(750);
        matchStage.setHeight(500);


        TableView<Match> allMatchesTable = new TableView<>();


//skapar kolumner
        TableColumn<Match, Integer> matchId = new TableColumn<>("Match id");
        TableColumn<Match, Integer> gameId = new TableColumn<>("Game");
        TableColumn<Match, Team> team1 = new TableColumn<>("Team11");
        TableColumn<Match, Team> team2 = new TableColumn<>("Team22");
        TableColumn<Match, Player> player1 = new TableColumn<>("Player11");
        TableColumn<Match, Player> player2 = new TableColumn<>("Player22");
        TableColumn<Match, Boolean> matchPlayed = new TableColumn<>("Match Played");
        TableColumn<Match, LocalDate> matchDate = new TableColumn<>("Match date");
        TableColumn<Match, String> results = new TableColumn<>("Results");
        TableColumn<Match, String> winner = new TableColumn<>("Winner");


        //kopplar ihop data med kolumnerna.   Det som är i "fritext" berättar vartifrån man tar det. Det ska vara samma
        // som namnet på ex int ifrån Match klassen. Alltså
//        @Column(name = "match_id")
//        int matchId;  --ska svara samma som den här
        matchId.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        gameId.setCellValueFactory(new PropertyValueFactory<>("game_id"));
        team1.setCellValueFactory(new PropertyValueFactory<>("team1"));
        team2.setCellValueFactory(new PropertyValueFactory<>("team2"));
        player1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        player2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        matchPlayed.setCellValueFactory(new PropertyValueFactory<>("matchPlayed"));
        matchDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        results.setCellValueFactory(new PropertyValueFactory<>("results"));
        winner.setCellValueFactory(new PropertyValueFactory<>("winner"));


//skapar listor med alla/spelade/ospelade matcher
        List<Match> alMatches = matchController.getAllMatches();
        List<Match> playedMatches = new ArrayList<>();
        List<Match> unplayedMatches = new ArrayList<>();
        for (Match match : matchController.getAllMatches()) {
            if (match.getMatchPlayed()) {
                playedMatches.add(match);
            } else {
                unplayedMatches.add(match);
            }
        }

        //skapar en observable list och lägger in listorna som skapades ovanför beroende på vad som ska visas alla/spelade/ospelade matcher
        ObservableList<Match> matchData;
        if (showAllmatches) {
            matchData = FXCollections.observableArrayList(alMatches);
            allMatchesTable.getColumns().addAll(matchId, gameId, team1, team2, player1, player2, matchPlayed, matchDate, results, winner);
        } else {
            if (showPlayedMatches) {
                matchData = FXCollections.observableArrayList(playedMatches);
                allMatchesTable.getColumns().addAll(matchId, gameId, team1, team2, player1, player2, matchPlayed, matchDate, results, winner);
            } else {
                matchData = FXCollections.observableArrayList(unplayedMatches);
                allMatchesTable.getColumns().addAll(matchId, gameId, team1, team2, player1, player2, matchDate);
            }
        }


        allMatchesTable.setItems(matchData);
        allMatchesTable.setPrefSize(700, 700);
        VBox vbox = new VBox();
        setVBoxBackGround(vbox);
        vbox.getChildren().add(allMatchesTable);

        Scene sceneShowAllMatches = new Scene(vbox);
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

        Label dateLabel = new Label("Enter date of game \"yyyy-mm-dd\"");
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
                String[] dateAsList = date.split("-");

                int year = Integer.parseInt(dateAsList[0]);
                int monthInteger = Integer.parseInt(dateAsList[1]);
                int dayInteger = Integer.parseInt(dateAsList[2]);


                matchController.addNewMatch(gameId, teamGame, contestant1_id, contestant2_id, LocalDate.of(year, monthInteger, dayInteger));  //heter addNewMatch istället för save för jag testade att skriva om save och gjorde det med ett annat namn då
                messageLabel.setText("Match added, add new match or close window");

            } catch (Exception e) {
                System.out.println("kunde inte lägga till");
                messageLabel.setText("Failed to add new match");
            }
        });


        Scene createMatchScene = new Scene(vBoxAddMatch);
        addMatchStage.setScene(createMatchScene);
        addMatchStage.show();
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
                int matchId = Integer.parseInt(matchIdTextField.getText()); //Sparar texten från fälten till int och string
                Match matchToAddResult = matchController.getMatchById(matchId);

                if (matchToAddResult.getMatchPlayed()) {
                    String results = resultTextField.getText();

                    if (results.contains("-")) {                         //Jag satte ett krav att resultatet måste innehålla "-"
                        matchToAddResult.setResults(results);            //anger resultatet
                        setWinner(matchToAddResult, results);
                        if (matchController.updateMatch(matchToAddResult)) {
                            messageLabel.setText("Added result successfully. Add more results or close window");
                            System.out.println("Results added");
                        }

                    } else {
                        messageLabel.setText("enter results correctly \"x-x\"");
                    }
                } else {
                    messageLabel.setText("Match not played");
                }
            } catch (Exception e) {
                messageLabel.setText("Failed to add results");
                System.out.println("Failed to add results");
            }
        });

        vBoxResults.getChildren().addAll(matchIdLabel, matchIdTextField, resultLabel, resultTextField, addResults, messageLabel);

        Scene sceneAddResult = new Scene(vBoxResults);
        resultStage.setScene(sceneAddResult);
        resultStage.show();
    }

    public void setWinner(Match match, String results) {
        // Splittar upp resultat strängen och skapar två int ifrån den
        String[] resultsSplit = results.split("-");
        int result1 = Integer.parseInt(resultsSplit[0]);
        int result2 = Integer.parseInt(resultsSplit[1]);

        //beroende på vilken result som är högst sätts vinnaren. Om de är samma sätts tie
        if (result1 > result2) {
            if (match.getTeamGame()) {
                match.setWinner(match.getTeam1());
            } else {
                match.setWinner(match.getPlayer1());
            }
        } else if (result1 < result2) {
            if (match.getTeamGame()) {
                match.setWinner(match.getTeam2());
            } else {
                match.setWinner(match.getPlayer2());
            }
        } else {
            match.setWinner("Tie");
        }
    }

    public void alterMatchTestPlayerAndTeam() {

        Stage alterMatchStage = new Stage();
        alterMatchStage.setTitle("Alter match");
        alterMatchStage.setWidth(300);
        alterMatchStage.setHeight(320);
        VBox vBoxAlterMatch = new VBox();

        Label matchIdLabel = new Label("Enter id for match to change");
        TextField matchIdTextField = new TextField();
        Label gameIdLabel = new Label("Enter id for new game");
        TextField gameIdTextField = new TextField();
        Label playerOrTeam1Label = new Label("Enter new id for player/team 1");
        TextField playerOrTeam1TextField = new TextField();
        Label playerOrTeam2Label = new Label("Enter new id for player/team 2");
        TextField playerOrTeam2TextField = new TextField();
        Label dateLabel = new Label("Enter new date for match");
        TextField dateTextField = new TextField();
        Button alterMatchButton = new Button("Change match");


        alterMatchButton.setOnAction(event -> {
            int matchId;
            int idOfNewplayerOrTeam;

            matchId = Integer.parseInt(matchIdTextField.getText());
            Match matchToUpdate = matchController.getMatchById(matchId);
            if (matchToUpdate.getTeamGame()) {


                if (!playerOrTeam1TextField.getText().isEmpty()) {
                    //ändra team1
                    try {
                        matchId = Integer.parseInt(matchIdTextField.getText());
                        idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam1TextField.getText());

                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 0, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
                            System.out.println("lyckats ändra hoppas jag ");
                        } else {
                            System.out.println("nähä inte nu heller");
                        }
                    } catch (Exception e) {
                        System.out.println(e + "fel för jag försöker inte ändra player 2");
                    }
                }

                if (!playerOrTeam2TextField.getText().isEmpty()) {
                    //ändra team2
                    try {
                        matchId = Integer.parseInt(matchIdTextField.getText());

                        idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam2TextField.getText());
                        System.out.println("changing team1 to id: " + idOfNewplayerOrTeam);

                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 1, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
                            System.out.println("lyckats ändra hoppas jag ");
                        } else {
                            System.out.println("nähä inte nu heller");
                        }

                    } catch (Exception e) {
                        System.out.println(e + "fel för jag försöker inte ändra player 2");
                    }
                }
            } else {

                if (playerOrTeam1TextField.getText() != null) {       //  ändra spelare 1
                    try {
                        matchId = Integer.parseInt(matchIdTextField.getText());
                        idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam1TextField.getText());
                        System.out.println("changing player1 to id: " + idOfNewplayerOrTeam);

                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 0, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
                            System.out.println("lyckats ändra hoppas jag ");
                        } else {
                            System.out.println("nähä inte nu heller");
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                if (playerOrTeam2TextField.getText() != null) {     //ändra spelare 2
                    try {
                        matchId = Integer.parseInt(matchIdTextField.getText());
                        idOfNewplayerOrTeam = Integer.parseInt(playerOrTeam2TextField.getText());
                        System.out.println("changing player2 to id: " + idOfNewplayerOrTeam);

                        if (matchController.replaceOnePlayerOrTeamFromMatch(matchId, 1, idOfNewplayerOrTeam)) {  //står noll där för det är spelare ett jag vill ändra
                            System.out.println("lyckats ändra hoppas jag ");
                        } else {
                            System.out.println("nähä inte nu heller");
                        }
                    } catch (Exception e) {
                        System.out.println(e + "fel för jag försöker inte ändra player 2");
                    }
                }
            }

            //ändra datum
            String[] dateList = dateTextField.getText().split("-");
            if (dateList != null) {

                try {
                    int year = Integer.parseInt(dateList[0]);
                    int month = Integer.parseInt(dateList[1]);
                    int day = Integer.parseInt(dateList[2]);

                    matchToUpdate.setMatchDate(LocalDate.of(year, month, day));

                    if (matchController.updateMatch(matchToUpdate)) {
                        System.out.println("Date updated");
                    }
                } catch (Exception e) {
                }
            }

            //TODO lägga till funktion för att ändra spel

        });


        vBoxAlterMatch.getChildren().addAll(matchIdLabel, matchIdTextField, gameIdLabel, gameIdTextField, playerOrTeam1Label, playerOrTeam1TextField, playerOrTeam2Label, playerOrTeam2TextField,
            dateLabel, dateTextField, alterMatchButton);

        Scene scene = new Scene(vBoxAlterMatch);
        alterMatchStage.setScene(scene);
        alterMatchStage.show();
    }

    public void removeMatch() {
        Stage removeMatchStage = new Stage();
        removeMatchStage.setTitle("Remove match");
//        removeMatchStage.setWidth(270);
//        removeMatchStage.setHeight(120);

        VBox vBoxRemoveMatch = new VBox();
        Label removeMatchLabel = new Label("Enter id of match to delete");
        TextField removeMatchIdTextField = new TextField();
        removeMatchIdTextField.setMaxWidth(50);
        Button removeMatchButton = new Button("Remove match");
        Label messageLabel = new Label();

        removeMatchButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(removeMatchIdTextField.getText());

                if (id > 0) {
                    if (matchController.removePlayerOrTeamFromMatch(id)) {
                        if (matchController.deleteMatch(id)) {
                            messageLabel.setText("Match removed");
                        }
                    } else {
                        messageLabel.setText("Failed to delete match");
                    }
                } else {
                    messageLabel.setText("Match id out of bounds");
                }
            } catch (Exception e) {
                messageLabel.setText("Failed to remove match");
            }

        });

        vBoxRemoveMatch.getChildren().addAll(removeMatchLabel, removeMatchIdTextField, removeMatchButton, messageLabel);
        setVBoxBackGround(vBoxRemoveMatch);
        Scene sceneRemoveMatch = new Scene(vBoxRemoveMatch, 300, 225);
        removeMatchStage.setScene(sceneRemoveMatch);
        removeMatchStage.show();

    }


    public void setVBoxBackGround(VBox vBox) {
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        // Create a background with the image
        Image backgroundImage = new Image("file:BG.jpg");
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        vBox.setBackground(new Background(background));

        //Lite olika storlekar som funkar med bakgrunden
        //  Scene scene = new Scene(vBox, 800, 600);
        // Scene scene = new Scene(vBox, 600, 450);
        // Scene scene = new Scene(vBox, 400, 300);
        // Scene scene = new Scene(vBox, 300, 225);

    }
}