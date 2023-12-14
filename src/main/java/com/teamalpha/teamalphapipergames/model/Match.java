
package com.teamalpha.teamalphapipergames.model;


import com.teamalpha.teamalphapipergames.controller.MatchController;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name = "matches")
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    int matchId;

    @Column(name = "game_id")
    private int game_id;

    @Column(name = "team1_id")
    private int team1_id;

    @Column(name = "team2_id")
    private int team2_id;

//    @Column(name = "player1_id")
//    private int player1_id;

//    @Column(name = "player2_id")
//    private int player2_id;


    @Column(name = "finished")
    private boolean finished = false;

    @Column(name = "match_results")
    private String results = null;


    @Column(name = " team_game")
    private boolean teamGame;
    //har lagt in den här själv, finns inte i vårt schema

//    @Column(name="player1")
//    private int player_id;


    @Column(name = "matchDate")
    private String match_date;
    //joins


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "player_id",
//            joinColumns = @JoinColumn(name = "match_id")
//    )
    private List<Player> players = new ArrayList<>();


    @Column(name = "player1")
    private String player1;//= String.valueOf(getPlayers().get(0));
    @Column(name = "player2")
    private String player2;//= String.valueOf(getPlayers().get(1));

    //constructors
    public Match() {
    }

    public Match(int id) {
        this.matchId = id;
    }

//    public Match(boolean teamGame, int gameId, int teamOrPlayer1_id, int teamOrPlayer2_id, boolean finished, String matchDate) {
//        MatchController matchController = new MatchController();
//        this.teamGame = teamGame;
//        this.finished = finished;
//        this.game_id = gameId;
//        this.match_date = matchDate;
//        if (teamGame) {
//            //this.team1_id = player1_id.getId();
//            this.team2_id = teamOrPlayer2_id;
//        } else {
//            this.player1=matchController.addPlayerToMatch(teamOrPlayer1_id, matchId);
//
//           // matchController.addPlayerToMatch(teamOrPlayer1_id, matchId);
//
//            this.player2_id = teamOrPlayer2_id;
//        }
//    }


    public Match(int gameId, boolean teamGame, Player teamOrPlayer1_id, Player teamOrPlayer2_id, String matchDate) {
        this.teamGame = teamGame;
        this.game_id = gameId;
        this.match_date = matchDate;
        if (teamGame) {
//            this.team1_id = teamOrPlayer1_id.getId();
//            this.team2_id = teamOrPlayer2_id;
        } else {
            //this.player1_id = teamOrPlayer1_id;
            this.players.add(teamOrPlayer1_id);
            this.players.add(teamOrPlayer2_id);
//            this.player1 = teamOrPlayer1_id;
//            this.player2 = teamOrPlayer2_id;
        }
    }


    //getters and setters
    public int getMatchId() {
        return matchId;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getTeam1_id() {
        return team1_id;
    }

    public void setTeam1_id(int team1_id) {
        this.team1_id = team1_id;
    }

    public int getTeam2_id() {
        return team2_id;
    }

    public void setTeam2_id(int team2_id) {
        this.team2_id = team2_id;
    }


    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public boolean getTeamGame() {
        return teamGame;
    }

    public void setTeamGame(boolean teamGame) {
        this.teamGame = teamGame;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setPlayersByIndexInPlayersList(int index, Player player) {
//            this.players.get(index) = player;
        this.players.set(index, player);
    }

//    public String getPlayer1() {
//        return player1;
//    }
//
//    public void setPlayer1(String player1) {
//        this.player1 = player1;
//    }

    public String getPlayer1() {
        return players.get(0).getFirstName();
    }


    //var här det krånglade när jag inte fick in namnet rätt efter jag ändrat spelare i en match
    public void setPlayer1(String player1) {
        this.players.get(0).setFirstName(player1);
        this.player1 = player1;
    }

    public String getPlayer2() {
        return  players.get(1).getFirstName();
    }

    public void setPlayer2(String player2) {
        this.players.get(1).setFirstName(player2);
        this.player2 = player2;
    }

//    public void removePlayerFromMatch(Player player1, Player player2){
//        this.players.remove(player1);
//        this.players.remove(player2);
//        player1.getMatches().remove(this);
//        player2.getMatches().remove(this);
//    }
//
//    public List <Player> getPlayerFromMatchId(int matchiiId){
//        this.matchId=matchiiId;
//        return this.players;
//    }
}

