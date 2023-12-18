package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;
import com.teamalpha.teamalphapipergames.model.Game;
import javax.persistence.*;
import java.util.List;
import java.util.function.Function;

public class TeamController {

  private EntityManagerFactory entityManagerFactory;

  // Constructor initializes the EntityManagerFactory
  public TeamController() {
    this.entityManagerFactory = Persistence.createEntityManagerFactory("hibernate");
  }

  // Utility method to perform database operations
  // Accepts a lambda expression (operation) that uses EntityManager
  private <T> T performDbOperation(Function<EntityManager, T> operation) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      // Executes the provided database operation
      return operation.apply(entityManager);
    } catch (Exception e) {
      e.printStackTrace(); // Log exception, replace with better logging
      throw new RuntimeException(e); // Rethrow exception after logging
    } finally {
      // Ensure EntityManager is closed after operation
      if (entityManager != null && entityManager.isOpen()) {
        entityManager.close();
      }
    }
  }

  // Save a team to the database
  public boolean save(Team team) {
    // Using the utility method to handle the EntityManager
    return performDbOperation(entityManager -> {
      EntityTransaction transaction = entityManager.getTransaction();
      try {
        transaction.begin();
        Game game = team.getGame();
        // Check if the team is associated with a game
        if (game != null) {
          // Fetch the game from the database to ensure it's managed by the current EntityManager
          game = entityManager.find(Game.class, game.getId());
          if (game != null) {
            // Add the team to the game's team list
            game.addTeam(team);
            team.setGame(game);
            entityManager.persist(game);
          } else {
          }
        }
        // Persist the team
        entityManager.persist(team);
        transaction.commit();
        return true;
      } catch (Exception e) {
        // Rollback transaction in case of failure
        if (transaction != null && transaction.isActive()) {
          transaction.rollback();
        }
        throw e;
      }
    });
  }

  public List<Team> getAll(boolean printOut) {
    return performDbOperation(entityManager -> {
      List<Team> teams = entityManager.createQuery("FROM Team", Team.class).getResultList();
      if (printOut) {
        teams.forEach(team -> {
          System.out.println(team.getId() + ". 👾 " + team.getName());
          team.getOwnedPlayers().forEach(player ->
                  System.out.println("\t - 🪪: " + player.getId() + ", " + player.getNickName())
          );
        });
      }
      return teams;
    });
  }

  public Team getTeamById(int id) {
    return performDbOperation(entityManager -> entityManager.find(Team.class, id));
  }

  public boolean updateTeam(Team team) {
    return performDbOperation(entityManager -> {
      EntityTransaction transaction = entityManager.getTransaction();
      try {
        transaction.begin();
        entityManager.merge(team);
        transaction.commit();
        return true;
      } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
          transaction.rollback();
        }
        throw e;
      }
    });
  }

  public boolean deleteTeamById(int id) {
    return performDbOperation(entityManager -> {
      EntityTransaction transaction = entityManager.getTransaction();
      try {
        transaction.begin();
        Team teamToDelete = entityManager.find(Team.class, id);
        if (teamToDelete != null) {
          // If the team is associated with a game, handle the association
          if (teamToDelete.getGame() != null) {
            // Remove the team from the game's association
            Game game = teamToDelete.getGame();
            game.removeTeam(teamToDelete);
            entityManager.persist(game);
          }
          entityManager.remove(teamToDelete);
          transaction.commit();
          return true;
        } else {
          System.out.println("Team not found.");
          return false;
        }
      } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
          transaction.rollback();
        }
        throw e;
      }
    });
  }

  public boolean addPlayerToTeam(int playerId, int teamId) {
    return performDbOperation(entityManager -> {
      EntityTransaction transaction = entityManager.getTransaction();
      try {
        transaction.begin();
        Player player = entityManager.find(Player.class, playerId);
        Team team = entityManager.find(Team.class, teamId);
        if (player != null && team != null) {
          team.addPlayer(player);
          transaction.commit();
          return true;
        } else {
          System.out.println("Player or Team not found.");
          return false;
        }
      } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
          transaction.rollback();
        }
        throw e;
      }
    });
  }

  public boolean removePlayerFromTeam(int playerId, int teamId) {
    return performDbOperation(entityManager -> {
      EntityTransaction transaction = entityManager.getTransaction();
      try {
        transaction.begin();
        Player player = entityManager.find(Player.class, playerId);
        Team team = entityManager.find(Team.class, teamId);
        if (player != null && team != null) {
          team.getOwnedPlayers().remove(player);
          player.setTeam(null);
          transaction.commit();
          return true;
        } else {
          System.out.println("Player or Team not found.");
          return false;
        }
      } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
          transaction.rollback();
        }
        throw e;
      }
    });
  }

  public Team getTeamByName(String teamName) {
    return performDbOperation(entityManager -> {
      TypedQuery<Team> query = entityManager.createQuery("FROM Team t WHERE t.name = :name", Team.class);
      query.setParameter("name", teamName);
      try {
        return query.getSingleResult();
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public void resetTeamIdCount() {
    performDbOperation(entityManager -> {
      EntityTransaction transaction = entityManager.getTransaction();
      try {
        transaction.begin();

        entityManager.createNativeQuery("ALTER TABLE teams AUTO_INCREMENT = 1").executeUpdate();

        transaction.commit();
        return null; // As we're not returning anything specific
      } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
          transaction.rollback();
        }
        throw e; // Rethrowing the exception to be consistent with performDbOperation
      }
    });
  }
}