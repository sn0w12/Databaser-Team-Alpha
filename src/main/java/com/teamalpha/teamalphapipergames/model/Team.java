package com.teamalpha.teamalphapipergames.model;

import javax.persistence.*;

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

    // Default constructor for JPA
    public Team() {}

    // Constructor
    public Team(int teamId, int gameId, String name) {
        this.teamId = teamId;
        this.gameId = gameId;
        this.name = name;
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
}
