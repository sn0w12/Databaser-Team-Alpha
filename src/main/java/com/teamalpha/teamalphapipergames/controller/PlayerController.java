package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PlayerController {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    // CREATE
    public boolean save(Player player) {
        // "Boiler plate" of code, recurring code throughout the code
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(player);
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
    public List<Player> getAll(boolean printOut){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            List<Player> listToReturn = new ArrayList<>(entityManager.createQuery("FROM Player", Player.class).getResultList());
            transaction.commit();
            if(printOut){
                for (Player player :
                        listToReturn) {
                    System.out.println(player.getId() + ". " + player.getNickName());
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
    public Player getPlayerById(int id){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Player playerToReturn = entityManager.find(Player.class, id);
            transaction.commit();
            return playerToReturn;
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
    public boolean updatePlayer(Player player){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(player);
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


    //Ska vara kvar bara jag som tagit bort för att inte få error pga getTeam
//    public boolean deletePlayerById(int id) {
//        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction transaction = null;
//        try {
//            transaction = entityManager.getTransaction();
//            transaction.begin();
//
//            // Retrieve the Game entity using EntityManager.find
//            Player playerToDelete = entityManager.find(Player.class, id);
//
//            // Check if the entity is found
//            if (playerToDelete != null) {
//                if (playerToDelete.getTeam() != null) {
//                    System.out.println("❌ Remove player from its team first.");
//                    return false;
//                }
//
//                entityManager.remove(playerToDelete);
//                transaction.commit();
//                return true;
//            } else {
//                System.out.println("Player not found with id: " + id);
//            }
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//        return false;
//    }
}