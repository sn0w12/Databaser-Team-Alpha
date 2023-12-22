
package com.teamalpha.teamalphapipergames.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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

    @Column(name = " team_game")
    private boolean teamGame;

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

    public Match(boolean teamGame, LocalDate matchDate, boolean matchPlayed, Game game) {
        this.teamGame = teamGame;
        this.matchDate = matchDate;
        this.matchPlayed = matchPlayed;
        this.game = game;
    }

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

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean getTeamGame() {
        return teamGame;
    }

    public void setTeamGame(boolean teamGame) {
        this.teamGame = teamGame;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate match_date) {
        this.matchDate = match_date;
    }

    public boolean getMatchPlayed() {
        return matchDate.isBefore(LocalDate.now());
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

