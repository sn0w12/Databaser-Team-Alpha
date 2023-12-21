package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Game;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;
import com.teamalpha.teamalphapipergames.model.Tournament;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TournamentController {

  public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

  public boolean save(Tournament tournament, int contestants) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();

      tournament.setContestants(contestants);

      entityManager.persist(tournament);
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

  public List<Tournament> getAll(boolean printOut) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      List<Tournament> listToReturn = new ArrayList<>(entityManager.createQuery("FROM Tournament", Tournament.class).getResultList());
      transaction.commit();
      if (printOut) {
        for (Tournament tournament :
            listToReturn) {

          System.out.println(tournament.getId() + ". " + tournament.getName());


        }
      }
      return listToReturn;
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

  public boolean updateTournament(Tournament tournament){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.merge(tournament);
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

  public boolean deleteTournament(int id) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      Tournament tounamentToDelete = entityManager.find(Tournament.class, id);
      entityManager.remove(entityManager.contains(tounamentToDelete) ? tounamentToDelete : entityManager.merge(tounamentToDelete));
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

  // Add team to Tournament
  public boolean addTeamToTournament(int teamId, int tournamentId){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    Tournament tournament;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      Optional<Team> possiblyATeam = Optional.ofNullable(entityManager.find(Team.class,teamId));
      Optional<Tournament> possiblyATournament = Optional.ofNullable(entityManager.find(Tournament.class, tournamentId));
      if(possiblyATournament.isPresent() && possiblyATeam.isPresent()){
        System.out.println("Both exist");
        Team team = possiblyATeam.get();
        tournament = possiblyATournament.get();
        tournament.addTeam(team);
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
}