package com.teamalpha.teamalphapipergames.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tournament_id")
  private int id;

  @Column(name = "title")
  private String name;

  @Column(name = "contestants")
  private int contestants;

  // Connection to Game
  @ManyToOne
  @JoinColumn(name = "game_id")  // This is the owning side of the relation
  private Game game;

  private boolean teamGame = false;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tournament")
  @Fetch(FetchMode.SUBSELECT)
  private List<Team> tournamentTeams = new ArrayList<>();

  // Constructors
  public Tournament() {
  }

  public Tournament(String name) {
    this.name = name;
  }

  public Tournament(String name, int contestants) {
    this.name = name;
    this.contestants = contestants;
  }

  public Tournament(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Tournament(int id, String name, int contestants) {
    this.id = id;
    this.name = name;
    this.contestants = contestants;
  }


  // Methods
  public void addTeam(Team team){
    team.setTournament(this);
    tournamentTeams.add(team);
  }


  // Getters and setters
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

  public boolean isTeamGame() {
    return teamGame;
  }

  public void setTeamGame(boolean teamGame) {
    this.teamGame = teamGame;
  }

  public int getContestants() {
    return contestants;
  }

  public void setContestants(int contestants) {
    this.contestants = contestants;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public List<Team> getTournamentTeams() {
    return tournamentTeams;
  }

  public void setTournamentTeams(List<Team> tournamentTeams) {
    this.tournamentTeams = tournamentTeams;
  }

  @Override
  public String toString() {
    return getName();
  }
}