package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Staff;
import com.teamalpha.teamalphapipergames.model.Team;
import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TeamController {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    // Method to create a new team
    public Team createTeam(int gameId, String name) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team newTeam = new Team();  // Assuming you have a default constructor
            newTeam.setGameId(gameId);
            newTeam.setName(name);

            entityManager.persist(newTeam);  // Persist the new team to the database
            transaction.commit();  // Commit the transaction

            return newTeam;  // The newTeam object should now have an auto-generated ID
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback in case of an exception
            }
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();  // Always close the EntityManager
        }
    }

    // Method to update a team
    public boolean updateTeam(int teamId, int newGameId, String newName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Team team = entityManager.find(Team.class, teamId);
            if (team != null) {
                if (newGameId != 0) { // Assuming 0 as no change
                    team.setGameId(newGameId);
                }
                if (newName != null && !newName.isEmpty()) {
                    team.setName(newName);
                }
                entityManager.merge(team);
                transaction.commit();
                return true;
            }
            return false;
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

    // Method to find a team by ID
    public Team findTeamById(int teamId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return entityManager.find(Team.class, teamId);
        } finally {
            entityManager.close();
        }
    }

    // Method to find teams by game ID
    public List<Team> findTeamsByGameId(int gameId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return entityManager.createQuery("SELECT t FROM Team t WHERE t.gameId = :gameId", Team.class)
                    .setParameter("gameId", gameId)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    // Method to delete a team
    public boolean deleteTeam(int teamId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Team teamToDelete = entityManager.find(Team.class, teamId);
            if (teamToDelete != null) {
                entityManager.remove(teamToDelete);
                transaction.commit();
                return true;
            }
            return false;
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

    public List<Team> getAllTeams() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Team> teamList = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            teamList = entityManager.createQuery("FROM Team", Team.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return teamList;
    }

    // Close EntityManagerFactory when the application ends
    public void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }
}