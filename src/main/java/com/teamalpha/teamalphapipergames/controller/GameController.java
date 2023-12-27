package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class GameController {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");
    //skapa spel, lägg till spel
    //behövs gameID? autogenereras
    public boolean createGame(String name) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Game newGame = new Game(name);  // Assuming ID is auto-generated
            entityManager.persist(newGame);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

    // Skapa lista för att se alla spel
    public List<Game> getAllGames(boolean printOut) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Game> gameList = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            gameList = entityManager.createQuery("FROM Game", Game.class).getResultList();
            transaction.commit();
            if (printOut) {
                for (Game game :
                        gameList) {
                    System.out.println(game.getGame_id() + ". " + game.getName());
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return gameList;
    }

    ////update game
    public boolean updateGame(Game game) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(game);
            transaction.commit();
            return true;
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

    // //Delete game
    public boolean deleteGame(int game_id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Game gameToDelete = entityManager.find(Game.class, game_id);

            entityManager.remove(entityManager.contains(gameToDelete) ? gameToDelete : entityManager.merge(gameToDelete));
            transaction.commit();
            return true;
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

    // Fredrik
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


    //den här är bara mot team klassen??
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
    // -- Fredrik



    //Malin
    public Game getGameByIdUniversal(int gameId){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Game gameToReturn = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            gameToReturn = entityManager.find(Game.class,gameId);

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
    public void addAllPlayersToGame(List<Integer> playerIds, int gameId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Game game = entityManager.find(Game.class, gameId);
            if (game == null) {
                System.out.println("Game not found.");
            }

            for (int playerId : playerIds) {
                Player player = entityManager.find(Player.class, playerId);
                if (player != null) {
                    game.addPlayer(player);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    // Bulk update a list of games in the database
    public boolean updateAll(Collection<Game> games) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            for (Game game : games) {
                entityManager.merge(game);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}

