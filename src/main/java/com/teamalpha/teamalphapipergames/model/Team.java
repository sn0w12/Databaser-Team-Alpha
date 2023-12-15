package com.teamalpha.teamalphapipergames.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "game_id")
    private int gameId;

    @Column(name = "name")
    private String name;


    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL/* , mappedBy = "teams"*/)
    private List<Match> teamMatches=new ArrayList<>();

    // Default constructor for JPA
    public Team() {}

    // Constructor
    public Team(int teamId, int gameId, String name) {
        this.teamId = teamId;
        this.gameId = gameId;
        this.name = name;
    }


    public void addMatch (Match match){
        teamMatches.add(match);
    }
    // Getters
    public int getTeamId() {
        return teamId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Match> getMatches() {
        return teamMatches;
    }

    public void setMatches(List<Match> matches) {
        this.teamMatches = matches;
    }
}