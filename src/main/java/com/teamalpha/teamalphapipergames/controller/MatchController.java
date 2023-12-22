package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Game;
import com.teamalpha.teamalphapipergames.model.Match;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class MatchController {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    public boolean addNewMatch(int gameId, boolean teamGame, int contestant1Id, int contestant2Id, LocalDate date) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Game game;
        Player player1;
        Player player2;
        Team team1;
        Team team2;
        Match match;


        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            //kollar om spelet finns
            Optional<Game> possiblyAGame = Optional.ofNullable(entityManager.find(Game.class, gameId));
            if (possiblyAGame.isPresent()) {
                game = possiblyAGame.get();

                //Skapar team match
                if (teamGame) {
                    Optional<Team> possiblyATeam1 = Optional.ofNullable(entityManager.find(Team.class, contestant1Id));
                    Optional<Team> possiblyATeam2 = Optional.ofNullable(entityManager.find(Team.class, contestant2Id));

                    if (possiblyATeam1.isPresent() && possiblyATeam2.isPresent()) {

                        team1 = possiblyATeam1.get();
                        team2 = possiblyATeam2.get();

                        if (team1.getGame().equals(team2.getGame()) && !team1.equals(team2)) {
                            match = new Match();

                            //lägger till team i match och match till team
                            match.addTeam(team1);
                            match.addTeam(team2);

                            team1.addMatch(match);
                            team2.addMatch(match);

                            match.setGame(game);
                            match.setTeamGame(teamGame);
                            match.setMatchDate(date);

                            entityManager.persist(match);
                            entityManager.merge(team1);
                            entityManager.merge(team2);
                            entityManager.merge(game);
                        } else {
                            return false;
                        }
                    } else {
                        System.out.println("fel lag");
                        return false;
                    }

                    //skapar player match
                } else {
                    Optional<Player> possiblyAPlayer1 = Optional.ofNullable(entityManager.find(Player.class, contestant1Id));
                    Optional<Player> possiblyAPlayer2 = Optional.ofNullable(entityManager.find(Player.class, contestant2Id));

                    if (possiblyAPlayer1.isPresent() && possiblyAPlayer2.isPresent()) {
                        player1 = possiblyAPlayer1.get();
                        player2 = possiblyAPlayer2.get();
                        if (!player1.equals(player2)) {

                            match = new Match();


                            match.addPlayer(player1);
                            match.addPlayer(player2);
=======
//                     match.addPlayer(player1);
//                     match.addPlayer(player2);


                            player1.addMatch(match);
                            player2.addMatch(match);

                            match.setGame(game);
                            match.setTeamGame(teamGame);
                            match.setMatchDate(date);

                            entityManager.persist(match);
                            entityManager.merge(player1);
                            entityManager.merge(player2);
                            entityManager.merge(game);

                        } else {
                            System.out.println("fel spelare");
                            return false;
                        }
                    } else {
                        return false;
                    }
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

    //Read
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

    public boolean updateGameInMatch(Match match, int gameId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Match macthToUpdate = match;
            Optional<Game> possiblyAGame = Optional.ofNullable(entityManager.find(Game.class, gameId));

            if (possiblyAGame.isPresent()) {
                Game newGame = possiblyAGame.get();
                macthToUpdate.setGame(newGame);
            } else {
                return false;
            }

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
                    if (teamToChangeTo.getGame().equals(teamToRemove.getGame())) {
                        //Remove old team from the match list and add the new team. Change in the match from old team to new team
                        teamToChangeTo.getMatches().add(match);
                        teamToRemove.getMatches().remove(match);
                        match.setTeamsByIndexInTeamsList(playerOrTeamToRemoveIndex, teamToChangeTo);
                        entityManager.merge(match);
                        entityManager.merge(teamToRemove);
                        entityManager.merge(teamToChangeTo);
                    } else {
                        return false;
                    }
                } else {
                    return false;
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
                } else {
                    return false;
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

    public boolean alterMatch(int matchId, int playerOrTeam1IdToChangeTo, int playerOrTeam2IdToChangeTo, int newGameId, String date) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Find the match
            Match matchToUpdate = entityManager.find(Match.class, matchId);

            //ändra game
            if (newGameId != 0) {
                Optional<Game> possiblyAGame = Optional.ofNullable(entityManager.find(Game.class, newGameId));

                if (!matchToUpdate.getTeamGame()) {
                    if (possiblyAGame.isPresent()) {
                        Game newGame = possiblyAGame.get();
                        matchToUpdate.setGame(newGame);
                        entityManager.merge(matchToUpdate);
                    } else return false;
                } else return false;
            }


            //ändra date
            if (date != null) {
                try {
                    String[] dateList = date.split("-");
                    int year = Integer.parseInt(dateList[0]);
                    int month = Integer.parseInt(dateList[1]);
                    int day = Integer.parseInt(dateList[2]);

                    matchToUpdate.setMatchDate(LocalDate.of(year, month, day));
                    entityManager.merge(matchToUpdate);
                } catch (Exception e) {
                    return false;
                }
            }


            //ändra team
            if (matchToUpdate.getTeamGame()) {

                //uppdatera team1
                if (playerOrTeam1IdToChangeTo != 0) {

                    Optional<Team> possiblyTeam1ToRemove = Optional.ofNullable(entityManager.find(Team.class, matchToUpdate.getTeams().get(0).getId()));
                    Optional<Team> possiblyTeam1ToChangeTo = Optional.ofNullable(entityManager.find(Team.class, playerOrTeam1IdToChangeTo));

                    if (possiblyTeam1ToRemove.isPresent() && possiblyTeam1ToChangeTo.isPresent()) {
                        Team team1ToRemove = possiblyTeam1ToRemove.get();
                        Team team1ToChangeTo = possiblyTeam1ToChangeTo.get();

                        if (team1ToChangeTo.getGame().equals(team1ToRemove.getGame())) {
                            //Remove old team from the match list and add the new team. Change in the match from old team to new team
                            team1ToChangeTo.getMatches().add(matchToUpdate);
                            team1ToRemove.getMatches().remove(matchToUpdate);
                            matchToUpdate.setTeamsByIndexInTeamsList(0, team1ToChangeTo);
                            entityManager.merge(matchToUpdate);
                            entityManager.merge(team1ToRemove);
                            entityManager.merge(team1ToChangeTo);
                        } else return false;
                    } else return false;

                }

                //uppdatera team2
                if (playerOrTeam2IdToChangeTo != 0) {

                    Optional<Team> possiblyTeam2ToRemove = Optional.ofNullable(entityManager.find(Team.class, matchToUpdate.getTeams().get(1).getId()));
                    Optional<Team> possiblyTeam2ToChangeTo = Optional.ofNullable(entityManager.find(Team.class, playerOrTeam2IdToChangeTo));

                    if (possiblyTeam2ToRemove.isPresent() && possiblyTeam2ToChangeTo.isPresent()) {
                        Team team2ToRemove = possiblyTeam2ToRemove.get();
                        Team team2ToChangeTo = possiblyTeam2ToChangeTo.get();

                        if (team2ToChangeTo.getGame().equals(team2ToRemove.getGame())) {
                            //Remove old team from the match list and add the new team. Change in the match from old team to new team
                            team2ToChangeTo.getMatches().add(matchToUpdate);
                            team2ToRemove.getMatches().remove(matchToUpdate);
                            matchToUpdate.setTeamsByIndexInTeamsList(1, team2ToChangeTo);
                            entityManager.merge(matchToUpdate);
                            entityManager.merge(team2ToRemove);
                            entityManager.merge(team2ToChangeTo);
                        } else return false;
                    } else return false;

                }

                Team team1 = matchToUpdate.getTeams().get(0);
                Team team2 = matchToUpdate.getTeams().get(1);
                if (team1.equals(team2)) {
                    return false;
                }

                //ändra player
            } else {
                //uppdatera player1
                if (playerOrTeam1IdToChangeTo != 0) {

                    Optional<Player> possiblyplayer1ToRemove = Optional.ofNullable(entityManager.find(Player.class, matchToUpdate.getPlayers().get(0).getId()));
                    Optional<Player> possiblyplayer1ToChangeTo = Optional.ofNullable(entityManager.find(Player.class, playerOrTeam1IdToChangeTo));

                    if (possiblyplayer1ToRemove.isPresent() && possiblyplayer1ToChangeTo.isPresent()) {
                        Player player1ToRemove = possiblyplayer1ToRemove.get();
                        Player player1ToChangeTo = possiblyplayer1ToChangeTo.get();

                        player1ToChangeTo.getMatches().add(matchToUpdate);
                        player1ToRemove.getMatches().remove(matchToUpdate);
                        matchToUpdate.setPlayersByIndexInPlayersList(0, player1ToChangeTo);
                        entityManager.merge(matchToUpdate);
                        entityManager.merge(player1ToRemove);
                        entityManager.merge(player1ToChangeTo);

                    } else return false;
                }

                //uppdatera player2
                if (playerOrTeam2IdToChangeTo != 0) {

                    Optional<Player> possiblyplayer2ToRemove = Optional.ofNullable(entityManager.find(Player.class, matchToUpdate.getPlayers().get(1).getId()));
                    Optional<Player> possiblyplayer2ToChangeTo = Optional.ofNullable(entityManager.find(Player.class, playerOrTeam2IdToChangeTo));
                    if (possiblyplayer2ToRemove.isPresent() && possiblyplayer2ToChangeTo.isPresent()) {

                        Player player2ToRemove = possiblyplayer2ToRemove.get();
                        Player player2ToChangeTo = possiblyplayer2ToChangeTo.get();

                            player2ToChangeTo.getMatches().add(matchToUpdate);
                            player2ToRemove.getMatches().remove(matchToUpdate);
                            matchToUpdate.setPlayersByIndexInPlayersList(1, player2ToChangeTo);
                            entityManager.merge(matchToUpdate);
                            entityManager.merge(player2ToRemove);
                            entityManager.merge(player2ToChangeTo);

                    } else return false;
                }

                Player player1 = matchToUpdate.getPlayers().get(0);
                Player player2 = matchToUpdate.getPlayers().get(1);
                if (player1.equals(player2)) {
                    return false;
                }
            }

            transaction.commit();
            return true;
        } catch (
                Exception e) {
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



