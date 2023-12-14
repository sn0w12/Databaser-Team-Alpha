package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;
import com.teamalpha.teamalphapipergames.model.Game;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  This class contains a lot of redundant code.
 *  The idea was not to write compact code, but to show the individual steps of CRUD operations
 *  CRUD - CREATE READ UPDATE DELETE
 */
public class TeamController {

  // The value "hibernate" at the end of the row is a pointer of which settings in persistence.xml to use.
  public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

  // CREATE
  public boolean save(Team team) {
    // "Boiler plate" of code, recurring code throughout the code
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(team);
      transaction.commit();
      return true;
    } catch (Exception e){
      if(transaction != null){
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return false;
  }
  // READ
  public List<Team> getAll(boolean printOut){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      List<Team> listToReturn = new ArrayList<>(entityManager.createQuery("FROM Team", Team.class).getResultList());
      transaction.commit();
      if(printOut){
        for (Team team :
            listToReturn) {
          System.out.println(team.getId() + ". 👾 " + team.getName());
          for (Player player :
              team.getOwnedPlayers()) {
            System.out.println("\t - 🪪: " + player.getId() + ", " + player.getNickName());
          }
        }
      }
      return listToReturn;
    } catch (Exception e){
      if(transaction != null){
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return null;
  }

  // READ 1 -- NEW
  public Team getTeamById(int id){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      Team teamToReturn = entityManager.find(Team.class, id);
      transaction.commit();
      return teamToReturn;
    } catch (Exception e){
      if(transaction != null){
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return null;
  }

  // UPDATE
  public boolean updateTeam(Team team){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.merge(team);
      transaction.commit();
      return true;
    } catch (Exception e){
      if(transaction != null){
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return false;
  }

  public boolean deleteTeamById(int id) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // Retrieve the Game entity using EntityManager.find
      Team teamToDelete = entityManager.find(Team.class, id);


      // Check if the entity is found
      if (teamToDelete != null) {
        if (teamToDelete.getGame() != null) {
          System.out.println("❌ Remove team from its game first.");
          return false;
        }

        entityManager.remove(teamToDelete);
        transaction.commit();
        return true;
      } else {
        System.out.println("Team not found with id: " + id);
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return false;
  }

  // Assign player to team
  public boolean addPlayerToTeam(int playerId, int teamId){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    Team team;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      Optional<Player> possiblyAPlayer = Optional.ofNullable(entityManager.find(Player.class,playerId));
      Optional<Team> possiblyATeam = Optional.ofNullable(entityManager.find(Team.class, teamId));
      if(possiblyATeam.isPresent() && possiblyAPlayer.isPresent()){
        System.out.println("Both exist");
        Player player = possiblyAPlayer.get();
        team = possiblyATeam.get();
        team.addPlayer(player);
      }
      transaction.commit();
      return true;
    } catch (Exception e){
      if(transaction != null){
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return false;
  }

  // new - add automatically
  public Team getTeamByName(String teamName) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    try {
      TypedQuery<Team> query = entityManager.createQuery("FROM Team t WHERE t.name = :name", Team.class);
      query.setParameter("name", teamName);
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null; // Team not found
    } finally {
      entityManager.close();
    }
  }

  // Remove Player from Team
  public boolean removePlayerFromTeam(int playerId, int teamId) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;

    try {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // Find the Player and Team entities
      Player player = entityManager.find(Player.class, playerId);
      Team team = entityManager.find(Team.class, teamId);

      if (player != null && team != null) {
        // Remove the player from the team
        team.getOwnedPlayers().remove(player);
        player.setTeam(null); // Remove the association from the player

        transaction.commit();
        return true;
      } else {
        System.out.println("Player or Team not found.");
      }
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }

    return false;
  }

}