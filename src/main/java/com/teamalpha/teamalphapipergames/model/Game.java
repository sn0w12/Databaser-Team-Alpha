package com.teamalpha.teamalphapipergames.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// @Entity, we want this class to have persistence in the database
@Entity
// @Table, we can rename this to suit our needs, or else Hibernate takes charge.
@Table(name = "games")
public class Game {
  // We declare where the primary key is
  @Id
  // The id will be generetad by the database
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "game_id")
  private int id;
  // @Column(name = "game_title", length = 30) is equivalent to VARCHAR(30) in database terms
  @Column(name = "game_title", length = 30)
  private String name;
  // @OneToMany Defines the relation to the other entity (One game can own several teams)
  // FetchType.EAGER Always query for entities and all of their children FetchType.LAZY (must ask for children)
  // CascadeType.ALL saves entity and its children in one go.
  // mappedBy actually prevents this side from creating an extra table for relational mapping
  // mappedBy tells hibernate that the opposite side of this relation has control, no need to create extra tables
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
  @Fetch(FetchMode.SUBSELECT)
  private List<Team> ownedTeams = new ArrayList<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
  @Fetch(FetchMode.SUBSELECT)
  private List<Player> individualPlayers = new ArrayList<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
  @Fetch(FetchMode.SUBSELECT)
  private List<Tournament> tournamentGameList = new ArrayList<>();

  public Game() {
  }
  public Game(String name) {
    this.name = name;
  }
  public Game(int id, String name) {
    this.id = id;
    this.name = name;
  }
  public void addTeam(Team team){
    team.setGame(this);
    ownedTeams.add(team);
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

  public void addTournament(Tournament tournament) {
    tournament.setGame(this);
    tournamentGameList.add(tournament);
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