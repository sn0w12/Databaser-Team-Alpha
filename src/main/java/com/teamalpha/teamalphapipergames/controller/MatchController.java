package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Match;
import javafx.collections.ObservableList;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
import javax.persistence.*;
import java.util.List;


public class MatchController {
    Match match=new Match();
    // public static final EntityManagerFactory ENTITY_MANAGER_FACTORY=Persistence.createEntityManagerFactory("hibernate");
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

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
    public ObservableList <Match> getAllMatches(boolean printMatches) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        ObservableList<Match> matchesToReturn;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();


            matchesToReturn = (ObservableList<Match>) entityManager.createQuery("FROM Match", Match.class).getResultList();
            transaction.commit();

            if (printMatches) {
                for (Match match : matchesToReturn) {
                    if (match.getTeamGame()){
                        System.out.println("Team " + match.getTeam1_id() + " vs Team " + match.getTeam2_id());
                    } else {
                        System.out.println("Player " + match.getPlayer1_id() + " vs Player " + match.getPlayer2_id());
                    }
                    if (match.getFinished()) {
                        System.out.println("Match finished, resuls: " + match.getResults());
                    } else {
                        System.out.println("Match not played, no results available");
                    }
                    System.out.println("");
                }
            }
            return matchesToReturn;
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


    public List <Match> getAllMatchesNoPrint() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Match> matchesToReturn;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();


            matchesToReturn = entityManager.createQuery("FROM Match",Match.class).getResultList();
            transaction.commit();


            return matchesToReturn;
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



    public List<Match> getPlayedOrUpcomingMatches(boolean printMatches, boolean matchPlayed) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Match> matchesToReturn;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (matchPlayed) {
                matchesToReturn = entityManager.createQuery("FROM Match WHERE finished=true ", Match.class).getResultList();
            } else {
                matchesToReturn = entityManager.createQuery("FROM Match WHERE finished=false ", Match.class).getResultList();
            }

            transaction.commit();


            if (printMatches) {
                for (Match match : matchesToReturn) {
                    if (match.getTeamGame()){
                        System.out.println("Team " + match.getTeam1_id() + " vs Team " + match.getTeam2_id());
                    } else {
                        System.out.println("Player " + match.getPlayer1_id() + " vs Player " + match.getPlayer2_id());
                    }
                    if (match.getFinished()) {
                        System.out.println("Match finished, resuls: " + match.getResults());
                    } else {
                        System.out.println("Match not played, no results available");
                    }
                    System.out.println("");
                }
            }
            return matchesToReturn;
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
            Match matchToDelete = entityManager.find(Match.class, match_id);
            entityManager.remove(entityManager.contains(matchToDelete) ? matchToDelete : entityManager.merge(matchToDelete));
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