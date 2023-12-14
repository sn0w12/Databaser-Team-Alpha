package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Match;
import com.teamalpha.teamalphapipergames.model.Player;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;


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

            if (printMatches) {
                for (Match match : matchesToReturn) {
                    System.out.println("Game id: " + match.getMatchId());
                    if (match.getTeamGame()) {
                        System.out.println("Team " + match.getTeam1_id() + " vs Team " + match.getTeam2_id());
                    } else {
                        System.out.println("Player " + match.getPlayers().get(0) + " vs Player " + match.getPlayers().get(1));
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
                matchesToReturn = entityManager.createQuery("FROM Match WHERE finished=true ", Match.class).getResultList();
            } else {
                matchesToReturn = entityManager.createQuery("FROM Match WHERE finished=false ", Match.class).getResultList();
            }

            transaction.commit();


            if (printMatches) {
                for (Match match : matchesToReturn) {
                    if (match.getTeamGame()) {
                        System.out.println("Team " + match.getTeam1_id() + " vs Team " + match.getTeam2_id());
                    } else {
                        System.out.println("Player " + match.getPlayers().get(0) + " vs Player " + match.getPlayers().get(1));
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

    // Remove Player from match and vice versa
    public boolean removePlayerFromMatch(int matchId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Find the Player and match entities
            Match match = entityManager.find(Match.class, matchId);
            Player player1 = entityManager.find(Player.class, match.getPlayers().get(0).getId());
            Player player2 = entityManager.find(Player.class, match.getPlayers().get(1).getId());

            if (player1 != null && player2 != null && match != null) {

                //tar bort matchen ifrån spelarens matchlista och tar bort spelare ifrån matchens spelar lista
                match.getPlayers().remove(player1);
                match.getPlayers().remove(player2);
                player1.getMatches().remove(match);
                player2.getMatches().remove(match);


                entityManager.merge(match);
                entityManager.merge(player1);
                entityManager.merge(player2);
                transaction.commit();
                return true;
            } else {
                System.out.println("Player or Team not found.");
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


    // add player to match
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
                match.setGame_id(gameId);
                match.setTeamGame(teamGame);
                entityManager.persist(match);
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

    public boolean updateMatchInFX(int matchId, int player1ToChangeToId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        Optional<Match> possiblyAMatchToChange = Optional.ofNullable(entityManager.find(Match.class, matchId));
        // Optional<Player> possiblyAPlayer1ToChange = Optional.ofNullable(entityManager.find(Player.class, possiblyAMatchToChange.get().getPlayer1()));
        Optional<Player> possiblyAPlayer1ToChangeFrom = Optional.ofNullable(entityManager.find(Player.class, possiblyAMatchToChange.get().getPlayers().get(0)));
        Optional<Player> possiblyAPlayer1ToChangeTo = Optional.ofNullable(entityManager.find(Player.class, possiblyAMatchToChange.get().getPlayers().get(player1ToChangeToId)));
        if (possiblyAMatchToChange.isPresent() && possiblyAPlayer1ToChangeFrom.isPresent() && possiblyAPlayer1ToChangeTo.isPresent()) {


            Player player1 = possiblyAPlayer1ToChangeTo.get();
            Match match = possiblyAMatchToChange.get();
            match.setPlayersByIndexInPlayersList(0, player1);


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
        }
        return false;
    }

//    public void addNewMatchTestWithBooleanAndTeam(int gameId, boolean teamGame, int player1Id, int player2Id, String date) {
//        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction transaction = null;
//
//        //tänker att jag får göra liknande med gameid och team sen när de är ihopkopplade och kolla om
//        //det är possibly game/team och lägga till det. Ska även göra en if satts för team/player
//
//
//        try {
//            transaction = entityManager.getTransaction();
//            transaction.begin();
//            if (teamGame) {
//                Team team1;
//                Team team2;
//                Optional<Team> possiblyATeam1 = Optional.ofNullable(entityManager.find( < Team >. class,team1Id));
//                Optional<Team> possiblyATeam2 = Optional.ofNullable(entityManager.find(Team.class, team2Id));
//
//
//                if ((possiblyATeam1.isPresent() && possiblyATeam2.isPresent())) {
//                    System.out.println("Båda finns");
//
//                    Match match = new Match();
//
//                    team1 = possiblyATeam1.get();
//                    team2 = possiblyATeam2.get();
////                player1.addMatch(match);
////                player2.addMatch(match);
//
//
//                    match.getPlayers().add(player1);
//                    match.getPlayers().add(player2);
//                    entityManager.persist(match);
//                }
//            } else {
//                Player player1;
//                Player player2;
//                Optional<Player> possiblyAPlayer1 = Optional.ofNullable(entityManager.find(Player.class, player1Id));
//                Optional<Player> possiblyAPlayer2 = Optional.ofNullable(entityManager.find(Player.class, player2Id));
//
//                if ((possiblyAPlayer1.isPresent() && possiblyAPlayer2.isPresent())) {
//                    System.out.println("Båda finns");
//
//                    Match match = new Match();
//
//                    player1 = possiblyAPlayer1.get();
//                    player2 = possiblyAPlayer2.get();
////                player1.addMatch(match);
////                player2.addMatch(match);
//
//                    match.getPlayers().add(player1);
//                    match.getPlayers().add(player2);
//                    entityManager.persist(match);
//                }
//
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
//
//    }
}



