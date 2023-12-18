package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Match;
import com.teamalpha.teamalphapipergames.model.Player;
import com.teamalpha.teamalphapipergames.model.Team;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
import javax.persistence.*;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


public class MatchController {
    Match match = new Match();
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
    public List<Match> getAllMatches(boolean printMatches) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Match> matchesToReturn;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            matchesToReturn = entityManager.createQuery("FROM Match", Match.class).getResultList();
            transaction.commit();

//            if (printMatches) {
//                for (Match match : matchesToReturn) {
//                    System.out.println("Game id: " + match.getMatchId());
//                    if (match.getTeamGame()) {
//                        System.out.println("Team " + match.getTeam1() + " vs Team " + match.getTeam2());
//                    } else {
//                        System.out.println("Player " + match.getPlayers().get(0) + " vs Player " + match.getPlayers().get(1));
//                    }
//                    if (match.getFinished()) {
//                        System.out.println("Match finished, resuls: " + match.getResults());
//                    } else {
//                        System.out.println("Match not played, no results available");
//                    }
//                    System.out.println("");
//                }
//            }
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


    public List<Match> getAllMatchesNoPrint() {
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


    public List<Match> getPlayedOrUpcomingMatches(boolean printMatches, boolean matchPlayed) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Match> matchesToReturn;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (matchPlayed) {
                matchesToReturn = entityManager.createQuery("FROM Match WHERE matchPlayed=true ", Match.class).getResultList();
            } else {
                matchesToReturn = entityManager.createQuery("FROM Match WHERE matchPlayed=false ", Match.class).getResultList();
            }

            transaction.commit();

//
//            if (printMatches) {
//                for (Match match : matchesToReturn) {
//                    if (match.getTeamGame()) {
//                        System.out.println("Team " + match.getTeam1() + " vs Team " + match.getTeam2());
//                    } else {
//                        System.out.println("Player " + match.getPlayers().get(0) + " vs Player " + match.getPlayers().get(1));
//                    }
//                    if (match.getFinished()) {
//                        System.out.println("Match finished, resuls: " + match.getResults());
//                    } else {
//                        System.out.println("Match not played, no results available");
//                    }
//                    System.out.println("");
//                }
//            }
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

    public boolean updateMatchAndPlayer(Match match, Player playerOld, Player playerNew) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(match);
            entityManager.merge(playerOld);
            entityManager.merge(playerNew);
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
            //matchToDelete.removePlayerFromMatch();
            //removePlayerFromMatch(match_id);
            // entityManager.remove(matchToDelete);
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


    // Remove Player/team from match and vice versa
    public boolean removePlayerOrTeamFromMatch(int matchId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Find the Player and match entities
            Match match = entityManager.find(Match.class, matchId);

            //om ett lag så tar vi bort lagen ifrån matchen och vi tar bort matchen ifrån lagen
            if (match.getTeamGame()) {
                Team team1 = entityManager.find(Team.class, match.getTeams().get(0).getTeamId());
                Team team2 = entityManager.find(Team.class, match.getTeams().get(1).getTeamId());

                if (team1 != null && team2 != null) {
                    match.getTeams().remove(team1);
                    match.getTeams().remove(team2);
                    team1.getMatches().remove(match);
                    team2.getMatches().remove(match);

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
                    match.getPlayers().remove(player1);
                    match.getPlayers().remove(player2);
                    player1.getMatches().remove(match);
                    player2.getMatches().remove(match);

                    entityManager.merge(match);
                    entityManager.merge(player1);
                    entityManager.merge(player2);
                }
            }

            transaction.commit();
            return true;

//            } else {
//                System.out.println("Player or Team not found.");
//            }
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

            // Find the Player and match entities
            Match match = entityManager.find(Match.class, matchId);

            //om ett lag så tar vi bort lagen ifrån matchen och vi tar bort matchen ifrån lagen
            if (match.getTeamGame()) {
                Team teamToRemove = entityManager.find(Team.class, match.getTeams().get(playerOrTeamToRemoveIndex).getTeamId());
                Team teamToChangeTo = entityManager.find(Team.class, playerOrTeamIdToChangeTo);

                if (teamToRemove != null && teamToChangeTo != null) {
                    //TODO har testat massa olika saker fram och tillbaka, tycker att det ska räcka med en set(). Men då blir det för skjutet

//                    teamToChangeTo.getMatches().add(match);
//                    teamToRemove.getMatches().remove(match);
//                    match.getTeams().set(playerOrTeamToRemoveIndex, teamToChangeTo);
//
                   // Team team2 = entityManager.find(Team.class, match.getTeams().get(1).getTeamId());
                    // Team teamTest = entityManager.find(Team.class, 5);
                    //teamToChangeTo.getMatches().add(match);
                    //teamToRemove.getMatches().remove(match);


                    //om vi ändrar första teamet
                    if (playerOrTeamToRemoveIndex == 0) {
                        //ska inte ändras
                        System.out.println("ändra första");

                        // match.getTeams().clear(); // tömmer listan
                       // match.getTeams().remove(0);
                        //match.getTeams().remove(0);

                         match.getTeams().set(0, teamToChangeTo);
                        teamToRemove.getMatches().remove(match);
//                        match.getTeams().add(0, teamToChangeTo);
//                        match.getTeams().add(1, team2);

                        // match.getTeams().add(teamTest);

// om jag tömmer hela listan och sen lägger till teamToChangeTo och sen teamTest. Hamnar de i rätt ordning
                        //men om jag tömmer hela listan och lägger till teamToChangeTo och sen team2 hamnar de i fel ordning

                      //  entityManager.merge(team2);
                        //   entityManager.merge(teamTest);
                    } else {
                        System.out.println("ändra andra");
//                        match.getTeams().remove(playerOrTeamToRemoveIndex);
//                        match.getTeams().add(teamToChangeTo);
                        match.getTeams().set(1, teamToChangeTo);
                        teamToRemove.getMatches().remove(match);

                    }

                    entityManager.merge(match);
                    entityManager.merge(teamToRemove);
                    entityManager.merge(teamToChangeTo);

                    // entityManager.merge(team2);


                }


            } //om spelare, ta bort spelare ifrån matchen och ta bort matchen ifrån spelarna
            else {

                Player playerToRemove = entityManager.find(Player.class, match.getPlayers().get(playerOrTeamToRemoveIndex).getId());
                Player playerToChangeTo = entityManager.find(Player.class, playerOrTeamIdToChangeTo);

                if (playerToRemove != null && playerToChangeTo != null) {

                    //lägga till saker för nya spelaren
                    //lägg till den nya spelaren i matchens lista
                    playerToChangeTo.getMatches().add(match);    //lagg till matchen i den nya spelarens lista

                    //ta bort saker för gamla spelaren
                    // match.getPlayers().remove(playerToRemove);  //ta bort spelaren ifrån matchens lista
                    playerToRemove.getMatches().remove(match);  //tar bort matchen ifrån spelarens lista

                    match.setPlayersByIndexInPlayersList(playerOrTeamToRemoveIndex, playerToChangeTo);   //byter ut spelare på matchens lista med spelare
//                    //tar bort matchen ifrån spelarens matchlista och tar bort spelare ifrån matchens spelar lista
//                    match.getPlayers().remove(playerToRemove);
//                    // match.getPlayers().remove(player2);
//                    playerToRemove.getMatches().remove(match);
//                    player2.getMatches().remove(match);

                    entityManager.merge(playerToRemove);
                    entityManager.merge(playerToChangeTo);
                    entityManager.merge(match);
                }
            }

            transaction.commit();
            return true;

//            } else {
//                System.out.println("Player or Team not found.");
//            }
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

    public boolean replaceOnePlayerOrTeamFromMatchMicke(int matchId, int playerOrTeamToRemoveIndex, int playerOrTeamIdToChangeTo) {


        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Fetch the match to alter
            Match match = entityManager.find(Match.class, matchId);

            // Check if there are teams we are dealing with
            if (match.getTeamGame()) {
                Team currentTeam = entityManager.find(Team.class, match.getTeams().get(playerOrTeamToRemoveIndex).getTeamId());
                Team updatedTeam = entityManager.find(Team.class, playerOrTeamIdToChangeTo);
//************************************************************************
//                if (currentTeam != null && updatedTeam != null) {
//                    System.out.println("Båda lagen finns:");
//                    //om vi ändrar första teamet
//                    if (playerOrTeamToRemoveIndex == 0) {
//                        match.getTeams().set(0, updatedTeam);
//                        currentTeam.getMatches().remove(match);
//
//                    } else {
//                        System.out.println("ändra andra");
//
//                        match.getTeams().set(1, updatedTeam);
//                        currentTeam.getMatches().remove(match);
//                    }
//                    entityManager.merge(match);
//                    entityManager.merge(currentTeam);
//                    entityManager.merge(updatedTeam);
//                }
                //************************************************************************
                if (currentTeam != null && updatedTeam != null) {
                    updatedTeam.getMatches().add(match);
                    currentTeam.getMatches().remove(match);
                    match.setTeamsByIndexInTeamsList(playerOrTeamToRemoveIndex,updatedTeam);
                    entityManager.merge(currentTeam);
                    entityManager.merge(updatedTeam);
                    entityManager.merge(match);
                }







            } //om spelare, ta bort spelare ifrån matchen och ta bort matchen ifrån spelarna
            else {
                Player playerToRemove = entityManager.find(Player.class, match.getPlayers().get(playerOrTeamToRemoveIndex).getId());
                Player playerToChangeTo = entityManager.find(Player.class, playerOrTeamIdToChangeTo);
                if (playerToRemove != null && playerToChangeTo != null) {
                    playerToChangeTo.getMatches().add(match);    //lagg till matchen i den nya spelarens lista
                    playerToRemove.getMatches().remove(match);  //tar bort matchen ifrån spelarens lista
                    match.setPlayersByIndexInPlayersList(playerOrTeamToRemoveIndex, playerToChangeTo);   //byter ut spelare på matchens lista med spelare
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


    public void addNewMatch(int gameId, boolean teamGame, int player1Id, int player2Id, String date) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Player player1;
        Player player2;
        //tänker att jag får göra liknande med gameid och team sen när de är ihopkopplade och kolla om
        //det är possibly game/team och lägga till det. Ska även göra en if satts för team/player
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Optional<Player> possiblyAPlayer1 = Optional.ofNullable(entityManager.find(Player.class, player1Id));
            Optional<Player> possiblyAPlayer2 = Optional.ofNullable(entityManager.find(Player.class, player2Id));

            if (possiblyAPlayer1.isPresent() && possiblyAPlayer2.isPresent()) {
                System.out.println("Båda finns");


                Match match = new Match();

                player1 = possiblyAPlayer1.get();
                player2 = possiblyAPlayer2.get();


//TODO vill vi ha att det ex är hela namnet eller nickname som visas i tabellen så ändrar
// vi här    match.setPlayer1(player1.getFirstName()); och lägger till typ getLastName    match.setPlayer1(player1.getFirstName()+lastname);
//                match.getPlayers().add(player1);
//                match.getPlayers().add(player2);

                match.addPlayer(player1);
                match.addPlayer(player2);
                //eftersom match och player har en koppling behöver man bara lägga till "saker i den ena sidans lista"
                // så kan den andra sidan komma åt det via kopplingen
                player1.addMatch(match);
                player2.addMatch(match);

                match.setGame_id(gameId);
                match.setTeamGame(teamGame);
                entityManager.persist(match);
                entityManager.merge(player1);
                entityManager.merge(player2);
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


        // dummy code: för att se att spelaren har en lista med matcher utan att vi lägger till det
        entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Player playerToTest = entityManager.find(Player.class, 1);
            System.out.println("Dessa matcher spelar personen i: ");
            for (Match matches :
                    playerToTest.getMatches()) {
                System.out.println(matches.getMatchId());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }

    }


    public void addNewMatchTeamOrPlayer(int gameId, boolean teamGame, int contestant1Id, int contestant2Id, String date) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Player player1;
        Player player2;
        Team team1;
        Team team2;
        Match match;
        //tänker att jag får göra liknande med gameid och team sen när de är ihopkopplade och kolla om
        //det är possibly game/team och lägga till det. Ska även göra en if satts för team/player

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();


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
                    match.getPlayers().add(null);
                    match.getPlayers().add(null);


                    team1.addMatch(match);
                    team2.addMatch(match);

                    match.setGame_id(gameId);
                    match.setTeamGame(teamGame);
                    entityManager.persist(match);
                    entityManager.merge(team1);
                    entityManager.merge(team2);
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
                    match.getTeams().add(null);
                    match.getTeams().add(null);
                    //eftersom match och player har en koppling behöver man bara lägga till "saker i den ena sidans lista"
                    // så kan den andra sidan komma åt det via kopplingen
                    //nehe tog bort group by för att kunna ha flera kopplingar i en klass så får lägga till de här
                    player1.addMatch(match);
                    player2.addMatch(match);

                    match.setGame_id(gameId);
                    match.setTeamGame(teamGame);
                    entityManager.persist(match);
                    entityManager.merge(player1);
                    entityManager.merge(player2);
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


//        // dummy code: för att se att spelaren har en lista med matcher utan att vi lägger till det
//        entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        transaction = entityManager.getTransaction();
//        transaction.begin();
//        try {
//            Player playerToTest = entityManager.find(Player.class, 1);
//            System.out.println("Dessa matcher spelar personen i: ");
//            for (Match matches :
//                    playerToTest.getMatches()) {
//                System.out.println(matches.getMatchId());
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        } finally {
//            entityManager.close();
//        }
//
    }

    public void addNewMatchWithDate(int gameId, boolean teamGame, int contestant1Id, int contestant2Id, LocalDate date) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Player player1;
        Player player2;
        Team team1;
        Team team2;
        Match match;
        //tänker att jag får göra liknande med gameid och team sen när de är ihopkopplade och kolla om
        //det är possibly game/team och lägga till det. Ska även göra en if satts för team/player


        //lägger till team/player
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

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
                    match.getPlayers().add(null);
                    match.getPlayers().add(null);

                    team1.addMatch(match);
                    team2.addMatch(match);


                    match.setGame_id(gameId);
                    match.setTeamGame(teamGame);
                    match.setMatchDate(date);

                    entityManager.persist(match);
                    entityManager.merge(team1);
                    entityManager.merge(team2);
                    System.out.println("Datum för nylagd match: "+match.getMatchDate());
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
                    match.getTeams().add(null);
                    match.getTeams().add(null);
                    //eftersom match och player har en koppling behöver man bara lägga till "saker i den ena sidans lista"
                    // så kan den andra sidan komma åt det via kopplingen
                    //nehe tog bort group by för att kunna ha flera kopplingar i en klass så får lägga till de här
                    player1.addMatch(match);
                    player2.addMatch(match);

                    match.setGame_id(gameId);
                    match.setTeamGame(teamGame);
                    match.setMatchDate(date);
                    entityManager.persist(match);
                    entityManager.merge(player1);
                    entityManager.merge(player2);
                    System.out.println("Datum för nylagd match: "+match.getMatchDate());
                } else {
                    System.out.println("fel spelare");
                }
            }

            //läger till datum


            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {

            entityManager.close();
        }


//        // dummy code: för att se att spelaren har en lista med matcher utan att vi lägger till det
//        entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        transaction = entityManager.getTransaction();
//        transaction.begin();
//        try {
//            Player playerToTest = entityManager.find(Player.class, 1);
//            System.out.println("Dessa matcher spelar personen i: ");
//            for (Match matches :
//                    playerToTest.getMatches()) {
//                System.out.println(matches.getMatchId());
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        } finally {
//            entityManager.close();
//        }
//
    }

}



