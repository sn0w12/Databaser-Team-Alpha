package com.teamalpha.teamalphapipergames.model;

import javax.persistence.*;

@Entity
@Table(name = "tournaments")
public class Tournament {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "player_id")
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

  @Override
  public String toString() {
    return getName();
  }
}
