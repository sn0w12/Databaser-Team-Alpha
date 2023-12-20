package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;
import com.teamalpha.teamalphapipergames.model.Game;
import com.teamalpha.teamalphapipergames.model.Tournament;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 *  This class contains a lot of redundant code.
 *  The idea was not to write compact code, but to show the individual steps of CRUD operations
 *  CRUD - CREATE READ UPDATE DELETE
 */
public class GameController {
  // The value "hibernate" at the end of the row is a pointer of which settings in persistence.xml to use.

  public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

  // CREATE
  public boolean save(Object game) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(game);
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
  public List<Game> getAll(boolean printOut){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    List<Game> gameListToReturn = new ArrayList<>();
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      TypedQuery<Game> resultList = entityManager.createQuery("FROM Game", Game.class);
      gameListToReturn.addAll(resultList.getResultList());
      transaction.commit();
      if(printOut){
        for (Game game :
            gameListToReturn) {
          System.out.println(game.getGame_id() + ". " + game.getName());
          for (Team team :
              game.getOwnedTeams()) {
            System.out.println("\t - " + team.getName());

            // individual players
            for (Player player :
                game.getIndividualPlayers()) {
              System.out.println("\t - " + player.getNickName());
            }
          }
        }
      }
      return gameListToReturn;
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
  // READ 1
  public Game getGameById(int id) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    Game gameToReturn = null;

    try {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // Use LEFT JOIN FETCH to eagerly fetch the ownedTeams collection
      gameToReturn = entityManager.createQuery(
              "SELECT g FROM Game g LEFT JOIN FETCH g.ownedTeams WHERE g.id = :id",
              Game.class
          ).setParameter("id", id)
          .getSingleResult();

      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }

    return gameToReturn;
  }


  // UPDATE
  public boolean updateGame(Game game){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.merge(game);
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
  // DELETE
  public boolean deleteGame(Game game){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      // If the entity is attached then remove game, else merge(attach/update) entity and then remove
      entityManager.remove(entityManager.contains(game) ? game :entityManager.merge(game));
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

  public boolean deleteGameById(int id) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // Retrieve the Game entity using EntityManager.find
      Game gameToDelete = entityManager.find(Game.class, id);

      // Check if the entity is found
      if (gameToDelete != null) {
        entityManager.remove(gameToDelete);
        transaction.commit();
        return true;
      } else {
        System.out.println("Game not found with id: " + id);
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
  // Assign team to game
  public boolean addTeamToGame(int teamId, int gameId){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    Game game;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      Optional<Team> possiblyATeam = Optional.ofNullable(entityManager.find(Team.class,teamId));
      Optional<Game> possiblyAGame = Optional.ofNullable(entityManager.find(Game.class, gameId));
      if(possiblyAGame.isPresent() && possiblyATeam.isPresent()){
        System.out.println("Both exist");
        Team team = possiblyATeam.get();
        game = possiblyAGame.get();
        game.addTeam(team);
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

  // Assign tournament to game
  public boolean addTournamentToGame(int tournamentId, int gameId){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    Game game;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      Optional<Tournament> possiblyATournament = Optional.ofNullable(entityManager.find(Tournament.class,tournamentId));
      Optional<Game> possiblyAGame = Optional.ofNullable(entityManager.find(Game.class, gameId));
      if(possiblyAGame.isPresent() && possiblyATournament.isPresent()){
        System.out.println("Both exist");
        Tournament tournament = possiblyATournament.get();
        game = possiblyAGame.get();
        game.addTournament(tournament);
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

  // Assign player to game
  public boolean addPlayerToGame(int playerId, int gameId){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    Game game;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      Optional<Player> possiblyAPlayer = Optional.ofNullable(entityManager.find(Player.class,playerId));
      Optional<Game> possiblyAGame = Optional.ofNullable(entityManager.find(Game.class, gameId));
      if(possiblyAGame.isPresent() && possiblyAPlayer.isPresent()){
        System.out.println("Both exist");
        Player player = possiblyAPlayer.get();
        game = possiblyAGame.get();
        game.addPlayer(player);
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

  // New for automatic add
  public Game getGameByName(String gameName) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    try {
      TypedQuery<Game> query = entityManager.createQuery("FROM Game g WHERE g.name = :name", Game.class);
      query.setParameter("name", gameName);
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null; // Game not found
    } finally {
      entityManager.close();
    }
  }

  // Remove Team from Game
  public boolean removeTeamFromGame(int teamId, int gameId) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;

    try {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // Find the Team and Game entities
      Team team = entityManager.find(Team.class, teamId);
      Game game = entityManager.find(Game.class, gameId);

      if (team != null && game != null) {
        // Remove the team from the game
        game.getOwnedTeams().remove(team);
        team.setGame(null); // Remove the association from the team

        transaction.commit();
        return true;
      } else {
        System.out.println("Team or Game not found.");
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

  // Remove Player from Game
  public boolean removePlayerFromGame(int playerId, int gameId) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;

    try {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // Find the Player and Game entities
      Player player = entityManager.find(Player.class, playerId);
      Game game = entityManager.find(Game.class, gameId);

      if (player != null && game != null) {
        // Remove the player from the game
        game.getIndividualPlayers().remove(player);
        player.setGame(null); // Remove the association from the player

        transaction.commit();
        return true;
      } else {
        System.out.println("Player or Game not found.");
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