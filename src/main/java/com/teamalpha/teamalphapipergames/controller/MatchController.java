package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Game;
import com.teamalpha.teamalphapipergames.model.Match;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class MatchController {
    Match match = new Match();
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    //Create
//    public void addNewMatch(int gameId, boolean teamGame, int contestant1Id, int contestant2Id, LocalDate date) {
//        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction transaction = null;
//        Player player1;
//        Player player2;
//        Team team1;
//        Team team2;
//        Match match;
//        //tänker att jag får göra liknande med gameid och team sen när de är ihopkopplade och kolla om
//        //det är possibly game/team och lägga till det.
//
//
//        //lägger till team/player
//        try {
//            transaction = entityManager.getTransaction();
//            transaction.begin();
//
//            if (teamGame) {
//
//                Optional<Team> possiblyATeam1 = Optional.ofNullable(entityManager.find(Team.class, contestant1Id));
//                Optional<Team> possiblyATeam2 = Optional.ofNullable(entityManager.find(Team.class, contestant2Id));
//                if (possiblyATeam1.isPresent() && possiblyATeam2.isPresent()) {
//
//                    team1 = possiblyATeam1.get();
//                    team2 = possiblyATeam2.get();
//                    match = new Match();
//
//                    //lägger till team i match och match till team
//                    match.addTeam(team1);
//                    match.addTeam(team2);
//                    match.getPlayers().add(null);
//                    match.getPlayers().add(null);
//
//                    team1.addMatch(match);
//                    team2.addMatch(match);
//
//                    match.setGame(gameId);
//                    match.setTeamGame(teamGame);
//                    match.setMatchDate(date);
//
//                    entityManager.persist(match);
//                    entityManager.merge(team1);
//                    entityManager.merge(team2);
//                    System.out.println("Datum för nylagd match: " + match.getMatchDate());
//                } else {
//                    System.out.println("fel lag");
//                }
//
//            } else {
//                Optional<Player> possiblyAPlayer1 = Optional.ofNullable(entityManager.find(Player.class, contestant1Id));
//                Optional<Player> possiblyAPlayer2 = Optional.ofNullable(entityManager.find(Player.class, contestant2Id));
//
//                if (possiblyAPlayer1.isPresent() && possiblyAPlayer2.isPresent()) {
//                    player1 = possiblyAPlayer1.get();
//                    player2 = possiblyAPlayer2.get();
//                    match = new Match();
//
//                    match.addPlayer(player1);
//                    match.addPlayer(player2);
//                    match.getTeams().add(null);
//                    match.getTeams().add(null);
//
//                    player1.addMatch(match);
//                    player2.addMatch(match);
//
//                    match.setGame_id(gameId);
//                    match.setTeamGame(teamGame);
//                    match.setMatchDate(date);
//                    entityManager.persist(match);
//                    entityManager.merge(player1);
//                    entityManager.merge(player2);
//                    System.out.println("Datum för nylagd match: " + match.getMatchDate());
//                } else {
//                    System.out.println("fel spelare");
//                }
//            }
//            transaction.commit();
//
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//    }
    //TODO Create played match where date is passed and you need to set at score to the match when created

    public void addNewMatch(int gameId, boolean teamGame, int contestant1Id, int contestant2Id, LocalDate date) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Player player1;
        Player player2;
        Team team1;
        Team team2;
        Match match;
        //tänker att jag får göra liknande med gameid och team sen när de är ihopkopplade och kolla om
        //det är possibly game/team och lägga till det.


        //lägger till team/player
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Game game = entityManager.find(Game.class, gameId);
            if (teamGame) {

                Optional<Team> possiblyATeam1 = Optional.ofNullable(entityManager.find(Team.class, contestant1Id));
                Optional<Team> possiblyATeam2 = Optional.ofNullable(entityManager.find(Team.class, contestant2Id));

                if (possiblyATeam1.isPresent() && possiblyATeam2.isPresent()) {

                    team1 = possiblyATeam1.get();
                    team2 = possiblyATeam2.get();
                    match = new Match();

                    //lägger till team i match och match till team
                    match.addTeam(team1);
                    match.addTeam(team2);
//                   match.getPlayers().add(null);
//                   match.getPlayers().add(null);
//                    match.getPlayers().add(entityManager.find(Player.class, 1));
//                    match.getPlayers().add(entityManager.find(Player.class, 2));


                    team1.addMatch(match);
                    team2.addMatch(match);

                    //  match.setGame_id(gameId);
                    match.setGame(game);
                    match.setTeamGame(teamGame);
                    match.setMatchDate(date);

                    entityManager.persist(match);
                    entityManager.merge(team1);
                    entityManager.merge(team2);
                    entityManager.merge(game);
                    System.out.println("Datum för nylagd match: " + match.getMatchDate());
                } else {
                    System.out.println("fel lag");
                }

            } else {
                Optional<Player> possiblyAPlayer1 = Optional.ofNullable(entityManager.find(Player.class, contestant1Id));
                Optional<Player> possiblyAPlayer2 = Optional.ofNullable(entityManager.find(Player.class, contestant2Id));

                if (possiblyAPlayer1.isPresent() && possiblyAPlayer2.isPresent()) {
                    player1 = possiblyAPlayer1.get();
                    player2 = possiblyAPlayer2.get();
                    match = new Match();

                    match.addPlayer(player1);
                    match.addPlayer(player2);
//                    match.getTeams().add(null);
//                    match.getTeams().add(null);
//                    match.addTeam(entityManager.find(Team.class, 1));
//                    match.addTeam(entityManager.find(Team.class, 2));


                    player1.addMatch(match);
                    player2.addMatch(match);

                    // match.setGame_id(gameId);
                    match.setGame(game);
                    match.setTeamGame(teamGame);
                    match.setMatchDate(date);

                    entityManager.persist(match);
                    entityManager.merge(player1);
                    entityManager.merge(player2);
//                    entityManager.merge(team1);
//                    entityManager.merge(team2);
                    entityManager.merge(game);

                    System.out.println("Datum för nylagd match: " + match.getMatchDate());
                } else {
                    System.out.println("fel spelare");
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
    //TODO Create played match where date is passed and you need to set at score to the match when created

    //Read
//    public List<Match> getAllMatchesAndPrint(boolean printMatches) {
//        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction transaction = null;
//        List<Match> matchesToReturn;
//        try {
//            transaction = entityManager.getTransaction();
//            transaction.begin();
//
//            matchesToReturn = entityManager.createQuery("FROM Match", Match.class).getResultList();
//            transaction.commit();
//
//            if (printMatches) {
//                for (Match match : matchesToReturn) {
//                    System.out.println("Game id: " + match.getMatchId());
//                    if (match.getTeamGame()) {
//                        System.out.println("Team " + match.getTeam1() + " vs Team " + match.getTeam2());
//                    } else {
//                        System.out.println("Player " + match.getPlayers().get(0) + " vs Player " + match.getPlayers().get(1));
//                    }
//                    if (match.getMatchPlayed()) {
//                        System.out.println("Match finished, resuls: " + match.getResults());
//                    } else {
//                        System.out.println("Match not played, no results available");
//                    }
//                    System.out.println("");
//                }
//            }
//            return matchesToReturn;
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//        return null;
//    }
    public List<Match> getAllMatches() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Match> matchesToReturn;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            matchesToReturn = entityManager.createQuery("FROM Match", Match.class).getResultList();
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

    public Match getMatchById(int id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Match matchToReturn = entityManager.find(Match.class, id);
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


    //Update
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


    public boolean removePlayerOrTeamFromMatch(int matchId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Find the Player and match entities
            Match match = entityManager.find(Match.class, matchId);


            match.setGame(null);
            //om ett lag så tar vi bort lagen ifrån matchen och vi tar bort matchen ifrån lagen
            if (match.getTeamGame()) {
                Team team1 = entityManager.find(Team.class, match.getTeams().get(0).getId());
                Team team2 = entityManager.find(Team.class, match.getTeams().get(1).getId());
                if (team1 != null && team2 != null) {


                    team1.getMatches().remove(match);
                    team2.getMatches().remove(match);

                    match.getTeams().remove(team1);
                    match.getTeams().remove(team2);


                    entityManager.merge(match);
                    entityManager.merge(team1);
                    entityManager.merge(team2);
                }

            } //om spelare, ta bort spelare ifrån matchen och ta bort matchen ifrån spelarna
            else {
                Player player1 = entityManager.find(Player.class, match.getPlayers().get(0).getId());
                Player player2 = entityManager.find(Player.class, match.getPlayers().get(1).getId());
                if (player1 != null && player2 != null) {


                    //tar bort matchen ifrån spelarens matchlista och tar bort spelare ifrån matchens spelar lista
                    player1.getMatches().remove(match);
                    player2.getMatches().remove(match);
                    match.getPlayers().remove(player1);
                    match.getPlayers().remove(player2);


                    entityManager.merge(match);
                    entityManager.merge(player1);
                    entityManager.merge(player2);
                }
            }

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

    public boolean replaceOnePlayerOrTeamFromMatch(int matchId, int playerOrTeamToRemoveIndex, int playerOrTeamIdToChangeTo) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Find the match
            Match match = entityManager.find(Match.class, matchId);

            if (match.getTeamGame()) {
                //Find the teams
                Team teamToRemove = entityManager.find(Team.class, match.getTeams().get(playerOrTeamToRemoveIndex).getId());
                Team teamToChangeTo = entityManager.find(Team.class, playerOrTeamIdToChangeTo);

                if (teamToRemove != null && teamToChangeTo != null) {
                    //Remove old team from the match list and add the new team. Change in the match from old team to new team
                    teamToChangeTo.getMatches().add(match);
                    teamToRemove.getMatches().remove(match);
                    match.setTeamsByIndexInTeamsList(playerOrTeamToRemoveIndex, teamToChangeTo);
                    entityManager.merge(match);
                    entityManager.merge(teamToRemove);
                    entityManager.merge(teamToChangeTo);
                }
            } else {
                //Find the players
                Player playerToRemove = entityManager.find(Player.class, match.getPlayers().get(playerOrTeamToRemoveIndex).getId());
                Player playerToChangeTo = entityManager.find(Player.class, playerOrTeamIdToChangeTo);
                if (playerToRemove != null && playerToChangeTo != null) {
                    // //Remove old player from the match list and add the new player. Change in the match from old player to new player
                    playerToChangeTo.getMatches().add(match);
                    playerToRemove.getMatches().remove(match);
                    match.setPlayersByIndexInPlayersList(playerOrTeamToRemoveIndex, playerToChangeTo);

                    entityManager.merge(playerToRemove);
                    entityManager.merge(playerToChangeTo);
                    entityManager.merge(match);
                }
            }

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



