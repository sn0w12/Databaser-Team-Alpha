
package com.teamalpha.teamalphapipergames.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    int matchId;

//    @Column(name = "game_id")
//    private int game_id;

    @Column(name = " team_game")
    private boolean teamGame;
    //har lagt in den här själv, finns inte i vårt schema

//    @Column(name = "player_1")
//    private String player1;
//    @Column(name = "player_2")
//    private String player2;
//
//    @Column(name = "team_1")
//    private String team1;
//    @Column(name = "team_2")
//    private String team2;
   @Column(name = "match_date")
    private LocalDate matchDate;
    @Column(name = "match_played")
    private boolean matchPlayed;

    @Column(name = "match_results")
    private String results = null;

    @Column(name = "match_winner")
    private String winner;

    //joins
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderColumn
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL/*, mappedBy="playerMatches"*/)
    private List<Player> players = new ArrayList<>();


    @Fetch(value = FetchMode.SUBSELECT)
    @OrderColumn
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL/*,mappedBy="teamMatches"*/)
    private List<Team> teams = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;


    //constructors
    public Match() {
    }

    public Match(int id) {
        this.matchId = id;
    }

//    public Match(int game_id, boolean teamGame, Player teamOrPlayer1_id, Player teamOrPlayer2_id, Date matchDate) {
//        this.game_id = game_id;
//        this.teamGame = teamGame;
//    }


    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void setPlayersByIndexInPlayersList(int index, Player player) {
        this.players.set(index, player);
    }

    public void setTeamsByIndexInTeamsList(int index, Team team) {
        this.teams.set(index, team);
    }


    //getters and setters
    public int getMatchId() {
        return matchId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame( Game game) {
        this.game = game;
    }

    public boolean getTeamGame() {
        return teamGame;
    }

    public void setTeamGame(boolean teamGame) {
        this.teamGame = teamGame;
    }

//    public String getPlayer1() {
//        if (players.isEmpty()) {
//            return "-";
//        } else
//            return players.get(0).getFirstName();
//    }
//
//
//    public void setPlayer1(String player1) {
//        this.players.get(0).setFirstName(player1);
//        this.player1 = player1;
//    }
//
//    public String getPlayer2() {
//        if (players.isEmpty()) {
//            return "-";
//        } else
//            return players.get(1).getFirstName();
//    }
//
//    public void setPlayer2(String player2) {
//        this.players.get(1).setFirstName(player2);
//        this.player2 = player2;
//    }
//
//    public String getTeam1() {
//        if (teams.isEmpty()) {
//            return "-";
//        } else {
//            return teams.get(0).getName();
//        }
//    }
//
//    public void setTeam1(String team1) {
//        this.teams.get(0).setName(team1);
//        this.team1 = team1;
//    }
//
//    public String getTeam2() {
//        if (teams.isEmpty()) {
//            return "-";
//        } else {
//            return teams.get(1).getName();
//        }
//    }
//
//    public void setTeam2(String team2) {
//        this.teams.get(1).setName(team2);
//        this.team2 = team2;
//    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate match_date) {
        this.matchDate = match_date;
    }

    public boolean getMatchPlayed() {
        return matchDate.isBefore(LocalDate.now());
    }

    public void setMatchPlayed(boolean matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


}

