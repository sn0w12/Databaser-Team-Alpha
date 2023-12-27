package com.teamalpha.teamalphapipergames.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int game_id;

    @Column(name = "game_title")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
    @Fetch(FetchMode.SUBSELECT)
    private List<Team> ownedTeams = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
    @Fetch(FetchMode.SUBSELECT)
    private List<Player> individualPlayers = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
    @Fetch(FetchMode.SUBSELECT)
    private List<Tournament> tournamentGameList = new ArrayList<>();

    // Constructors, getters, setters

    public Game(String name) {
        this.name = name;
    }

    public Game() {

        // Default constructor
    }

    public Game(int game_id, String name) {
        this.game_id = game_id;
        this.name = name;
    }

    public void addTeam(Team team){
        team.setGame(this);
        ownedTeams.add(team);
    }

    public void addTournament(Tournament tournament) {
        tournament.setGame(this);
        tournamentGameList.add(tournament);
    }

    public List<Tournament> getTournamentGameList() {
        return tournamentGameList;
    }

    public void setTournamentGameList(List<Tournament> tournamentGameList) {
        this.tournamentGameList = tournamentGameList;
    }

    public void removeTeam(Team team) {
        if (team != null && ownedTeams != null) {
            for (Iterator<Team> iterator = ownedTeams.iterator(); iterator.hasNext();) {
                Team currentTeam = iterator.next();
                if (currentTeam.equals(team)) {
                    iterator.remove();
                    team.setGame(null); // This is important to dissociate the team from the game
                    break;
                }
            }
        }
    }

    public void addPlayer(Player player) {
        player.setGame(this);
        individualPlayers.add(player);
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getOwnedTeams() {
        return ownedTeams;
    }

    public void setOwnedTeams(List<Team> ownedTeams) {
        this.ownedTeams = ownedTeams;
    }

    public List<Player> getIndividualPlayers() {
        return individualPlayers;
    }

    public void setIndividualPlayers(List<Player> individualPlayers) {
        this.individualPlayers = individualPlayers;
    }

    @Override
    public String toString() {
        return getName();
    }
}



