
package com.teamalpha.teamalphapipergames.model;

import javax.persistence.*;
import java.sql.Date;

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
    private int id;

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

    @Column(name = "match_date")
    private Date match_date;

    @Column(name = "finished")
    private boolean finished;

    @Column(name = "match_results")
    private String results;



    public Match() {
    }

    public Match(int game_id, int teamPlayer1_id, int teamPlayer2_id, Date match_date) {
        this.game_id = game_id;
        this.team1_id = teamPlayer1_id;
        this.team2_id = teamPlayer2_id;
        this.player1_id = teamPlayer1_id;
        this.player2_id = teamPlayer2_id;
        this.match_date = match_date;
    }


    public int getId() {
        return id;
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

    public Date getMatch_date() {
        return match_date;
    }

    public void setMatch_date(Date match_date) {
        this.match_date = match_date;
    }

    public boolean isFinished() {
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
}