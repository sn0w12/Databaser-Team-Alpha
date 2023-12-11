
package com.teamalpha.teamalphapipergames.model;


import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//match_id INT PRIMARY KEY AUTO_INCREMENT,
//        game_id INT,
//        team_id1 INT,
//        team_id2 INT,
//        player_id1 INT,
//        player_id2 INT,
//        match_date DATE NOT NULL,
//        finished BOOLEAN NOT NULL,
//        match_results TEXT,
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

    @Column(name = "player1_id")
    private int player1_id;

    @Column(name = "player2_id")
    private int player2_id;


    @Column(name = "finished")
    private boolean finished= false;

    @Column(name = "match_results")
    private String results=null;


    @Column (name =" team_game")
    private boolean teamGame;


    //har lagt in den här själv, finns inte i vårt schema
//    @Column(name = "team_game")
//    private boolean teamGame;
//
    //ändrar från date till String men har inte ändrat det i schema
//    @Column(name = "match_date")
//    private String match_date;
//
    @Column(name="matchDate")
    private String match_date;
    //joins
/*
vilken sida är ägar sidan?
spelar det någon roll


player-manyToOne   en spelare kan ha flera matcher
                    en match kan bara ha en spelare
                    --join med player_id

team- manyToOne
                    --join med team_id

game- manyToOne       en match kan bara ha ett spel
                        ett spel kan ha flera matcher
                       -- join med game_id
* */

//    @ManyToOne
//    @JoinColumn(name ="player1_id")
//    private Player player1;
//
//    @ManyToOne
//    @JoinColumn(name ="player2_id")
//    private Player player2;
//
//
//    @ManyToOne
//    @JoinColumn(name="team1_id")
//    private Team team1;
//
//    @ManyToOne
//    @JoinColumn(name="team2_id")
//    private Team team2;
//
//
//    @ManyToOne
//    @JoinColumn(name="game_id")
//    private Game game;


    //constructors
    public Match() {
    }

    public Match(int id) {
        this.matchId = id;
    }

    public Match(boolean teamGame, int gameId, int teamOrPlayer1_id, int teamOrPlayer2_id, boolean finished, String matchDate) {
        this.teamGame=teamGame;
        this.finished=finished;
        this.game_id=gameId;
        this.match_date=matchDate;
        if (teamGame) {
            this.team1_id = teamOrPlayer1_id;
            this.team2_id = teamOrPlayer2_id;
        } else {
            this.player1_id = teamOrPlayer1_id;
            this.player2_id = teamOrPlayer2_id;
        }


//        this.match_date=matchDate;
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate date = LocalDate.parse(match_date, formatter);


        // SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
      //  Date date=sdf.parse(match_date);
//        Date date=sdf.parse(match_date);


    }


    //getters and setters
    public int getId() {
        return matchId;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

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

    public int getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(int player1_id) {
        this.player1_id = player1_id;
    }

    public int getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(int player2_id) {
        this.player2_id = player2_id;
    }

//    public String getMatch_date() {
//        return match_date;
//    }
//
//    public void setMatch_date(String match_date) {
//        this.match_date = match_date;
//    }

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
}