package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Game;
import com.teamalpha.teamalphapipergames.model.Match;
import com.teamalpha.teamalphapipergames.model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


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

}

