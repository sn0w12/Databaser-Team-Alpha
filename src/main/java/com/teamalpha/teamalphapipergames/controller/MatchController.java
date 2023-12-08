package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Match;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
import javax.persistence.*;


public class MatchController {
   // public static final EntityManagerFactory ENTITY_MANAGER_FACTORY=Persistence.createEntityManagerFactory("hibernate");
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY=Persistence.createEntityManagerFactory("hibernate");
    //create
    public boolean saveMatch(Match match) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(match);
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

    //read
    //ska kunna se
    // vilka spelare/lag som tävlar
    // om matchen är kommande/avgjord
    // om den är avgjord ska resultatet visas (vem som vann bara)

    // lista samtliga matcher, avgjorda matcher och kommande matcher i tre olika reads
    public Match getAllMatches(int match_id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();


            Match matchToReturn = entityManager.find(Match.class, match_id);  //nu får jag ju med allt ifrån den...
            transaction.commit();

            return matchToReturn;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    private boolean matchPlayed;

    public Match getMatch(int match_id, boolean matchPlayed) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Match matchToReturn;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (matchPlayed) {
                matchToReturn = entityManager.find(Match.class, match_id);  //nu får jag ju med allt ifrån den...
            } else {
                matchToReturn = entityManager.find(Match.class, match_id);  //nu returnerar de precis samma oavsett
            }
            transaction.commit();

            return matchToReturn;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    //update
    public boolean updateMatch(Match match) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(match);
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

    //delete
    public boolean deleteMatch(int match_id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Match matchToDelete=entityManager.find(Match.class, match_id);
            entityManager.remove(entityManager.contains(matchToDelete)?matchToDelete: entityManager.merge(matchToDelete));
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

// getUpcomingMatches
//boolean getFinishedMatches

