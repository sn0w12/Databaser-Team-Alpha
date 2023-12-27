package com.teamalpha.teamalphapipergames.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity, we want this class to have persistence in the database
@Entity
// @Table, we can rename this to suit our needs, or else Hibernate takes charge.
@Table(name = "teams")
public class Team {
    // We declare where the primary key is
    @Id
    // The id will be generetad by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;
    // @Column(length = 8) is equivalent to VARCHAR(8) in database terms
    @Column(name = "team_name", length = 40)
    private String name;
    // @ManyToOne - many teams like this one can be owned by a single game

    @ManyToOne
    @JoinColumn(name = "game_id")  // This is the owning side of the relation
    private Game game;

    // Matches
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL/* , mappedBy = "teams"*/)
    private List<Match> teamMatches=new ArrayList<>();

    // to bind players to team
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "team")
    private List<Player> ownedPlayers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public Team(int id, String name, Game game) {
        this.id = id;
        this.name = name;
        this.game = game;
    }

    public void addPlayer(Player player){
        player.setTeam(this);
        ownedPlayers.add(player);
    }

    public void addMatch (Match match){
        teamMatches.add(match);
    }

    public List<Match> getTeamMatches() {
        return teamMatches;
    }

    public void setTeamMatches(List<Match> teamMatches) {
        this.teamMatches = teamMatches;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Player> getOwnedPlayers() {
        return ownedPlayers;
    }

    public void setOwnedPlayers(List<Player> ownedPlayers) {
        this.ownedPlayers = ownedPlayers;
    }

    public List<Match> getMatches() {
        return teamMatches;
    }

    public void setMatches(List<Match> matches) {
        this.teamMatches = matches;
    }

    @Override
    public String toString() {
        return getName();
    }
}