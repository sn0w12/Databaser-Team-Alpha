package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;

/**
 * Simple menu, gets the job done
 */
public class Menu {
  private GameController gameController;
  private TeamController teamController;
  private PlayerController playerController;
  private StaffController staffController;
  private MatchController matchController;

  public Menu(GameController gameController) {
    this.gameController = gameController;
    teamController = new TeamController();
    playerController = new PlayerController();
    staffController = new StaffController();
    matchController = new MatchController();
  }

  // ----------------------------------------

  // Main menu
  public void showMainMenu() {
    System.out.println("Main menu");
    System.out.println("1. STAFF (login)");
    System.out.println("2. Visitor");
    System.out.println("9. Exit program");
//    createStaff();
    handleMainMenu();
  }

  public void handleMainMenu() {
    Scanner mainScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = mainScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        staffController.getAll(true);
        System.out.print("Input id to log in as staff 💬: ");
        Optional<Staff> fetchedStaff = Optional.ofNullable(staffController.getStaffById(new Scanner(System.in).nextInt()));
        if (fetchedStaff.isPresent()) {
          String flagIcon = "";
          if (fetchedStaff.get().getCountry().equals("Australia")) {
            flagIcon = "🇦🇺";
          } else if (fetchedStaff.get().getCountry().equals("Belgium")) {
            flagIcon = "🇧🇪";
          } else if (fetchedStaff.get().getCountry().equals("Brazil")) {
            flagIcon = "🇧🇷";
          } else if (fetchedStaff.get().getCountry().equals("Bulgaria")) {
            flagIcon = "🇧🇬";
          } else if (fetchedStaff.get().getCountry().equals("Canada")) {
            flagIcon = "🇨🇦";
          } else if (fetchedStaff.get().getCountry().equals("Czechia")) {
            flagIcon = "🇨🇿";
          } else if (fetchedStaff.get().getCountry().equals("Denmark")) {
            flagIcon = "🇩🇰";
          } else if (fetchedStaff.get().getCountry().equals("England")) {
            flagIcon = "🏴󠁧󠁢󠁥󠁮󠁧󠁿";
          } else if (fetchedStaff.get().getCountry().equals("Finland")) {
            flagIcon = "🇫🇮";
          } else if (fetchedStaff.get().getCountry().equals("France")) {
            flagIcon = "🇫🇷";
          } else if (fetchedStaff.get().getCountry().equals("Germany")) {
            flagIcon = "🇩🇪";
          } else if (fetchedStaff.get().getCountry().equals("Greece")) {
            flagIcon = "🇬🇷";
          } else if (fetchedStaff.get().getCountry().equals("Hungary")) {
            flagIcon = "🇭🇺";
          } else if (fetchedStaff.get().getCountry().equals("India")) {
            flagIcon = "🇮🇳";
          } else if (fetchedStaff.get().getCountry().equals("Israel")) {
            flagIcon = "🇮🇱";
          } else if (fetchedStaff.get().getCountry().equals("Italy")) {
            flagIcon = "🇮🇹";
          } else if (fetchedStaff.get().getCountry().equals("Kazakhstan")) {
            flagIcon = "🇰🇿";
          } else if (fetchedStaff.get().getCountry().equals("Latvia")) {
            flagIcon = "🇱🇻";
          } else if (fetchedStaff.get().getCountry().equals("Lithuania")) {
            flagIcon = "🇱🇹";
          } else if (fetchedStaff.get().getCountry().equals("Montenegro")) {
            flagIcon = "🇲🇪";
          } else if (fetchedStaff.get().getCountry().equals("Netherlands")) {
            flagIcon = "🇳🇱";
          } else if (fetchedStaff.get().getCountry().equals("Norway")) {
            flagIcon = "🇳🇴";
          } else if (fetchedStaff.get().getCountry().equals("Poland")) {
            flagIcon = "🇵🇱";
          } else if (fetchedStaff.get().getCountry().equals("Romania")) {
            flagIcon = "🇷🇴";
          } else if (fetchedStaff.get().getCountry().equals("Russia")) {
            flagIcon = "🇷🇺";
          } else if (fetchedStaff.get().getCountry().equals("Slovenia")) {
            flagIcon = "🇸🇮";
          } else if (fetchedStaff.get().getCountry().equals("South Africa")) {
            flagIcon = "🇿🇦";
          } else if (fetchedStaff.get().getCountry().equals("South Korea")) {
            flagIcon = "🇰🇷";
          } else if (fetchedStaff.get().getCountry().equals("Spain")) {
            flagIcon = "🇪🇸";
          } else if (fetchedStaff.get().getCountry().equals("Sweden")) {
            flagIcon = "🇸🇪";
          } else if (fetchedStaff.get().getCountry().equals("Ukraine")) {
            flagIcon = "🇺🇦";
          } else if (fetchedStaff.get().getCountry().equals("USA")) {
            flagIcon = "🇺🇸";
          }
          System.out.println("✅ Logged in as: " + flagIcon + " " + fetchedStaff.get().getFirstName() + " " + fetchedStaff.get().getLastName());
          System.out.println(""); // Radbryt
          showCrudMenu();
        } else {
          System.out.println("❌ Could not find staff");
          System.out.println(""); // Radbryt
          showMainMenu();
        }
        break;
      case "2":
        System.out.println("⚠️ UNDER CONSTRUCTION ⚠️");
        break;
      case "9":
        System.out.println("Exiting program...");
        System.exit(0);
        break;
      default:
        break;
    }
  }

  // new menu CRUD
  public void showCrudMenu() {
    System.out.println("CRUD menu");
    System.out.println("0. Auto exec games, teams and players");
    System.out.println("1. Create");
    System.out.println("2. Read (List, Add to Game,Team etc / Remove from Game, Team etc)");
    System.out.println("3. Update");
    System.out.println("4. Delete");
    System.out.println("9. Log out - back to main menu");
    handleCrudMenu();
  }

  public void handleCrudMenu() {
    Scanner crudScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = crudScanner.nextLine();
    space();
    switch (userChoice) {
      case "0":
        createPlayersAndTeamsDataForStaff();
        showCrudMenu();
        break;
      case "1":
        showCreateMenu();
        break;
      case "2":
        showReadMenu();
        break;
      case "3":
        showUpdateMenu();
        break;
      case "4":
        showDeleteMenu();
        break;
      case "9":
        showMainMenu();
        break;
      default:
        showCrudMenu();
    }
  }

  // CREATE
  public void showCreateMenu() {
    System.out.println("Create Menu");
    System.out.println("1. Create new Game");
    System.out.println("2. Create new Team");
    System.out.println("3. Create new Player");
    System.out.println("4. Create new Match");
    System.out.println("5. Create new Tournament");
    System.out.println("6. Create new Staff");
    System.out.println("9. Back to CRUD Menu");
    handleCreateMenu();
  }

  public void handleCreateMenu() {
    Scanner createScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = createScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // create new game
        System.out.print("Game title 💬: ");
        String gameNameGame = new Scanner(System.in).nextLine();
        if (gameController.save(new Game(gameNameGame))) {
          System.out.println("✅ " + gameNameGame + " added");
        } else {
          System.out.println("❌ Failed to add Game");
        }
        showCrudMenu();
        break;
      case "2":
        // create new team
        System.out.print("Input team name 💬: ");
        if (teamController.save(new Team(new Scanner(System.in).nextLine()))) {
          System.out.println("✅ Team added");
        } else {
          System.out.println("❌ Could not save Team");
        }
        showCrudMenu();
        break;
      case "3":
        // create new player
        System.out.println("Add Player");
        System.out.print("Input first name 💬: ");
        String firstNamePlayer = createScanner.nextLine();
        System.out.print("Input last name 💬: ");
        String lastNamePlayer = createScanner.nextLine();
        System.out.print("Input nickname 💬: ");
        String nickNamePlayer = createScanner.nextLine();

        if (playerController.save(new Player(firstNamePlayer, lastNamePlayer, nickNamePlayer))) {
          System.out.println("✅ Player added");
        } else {
          System.out.println("❌ Could not save Player");
        }
        showCrudMenu();
        break;
      case "4":
        // create new match
        System.out.println("⚠️ MATCH IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "5":
        // create new tournament
        System.out.println("⚠️ TOURNAMENT IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "6":
        // create new staff
        System.out.println("Add Staff member");
        System.out.print("Input first name 💬: ");
        String firstNameStaff = createScanner.nextLine();
        System.out.print("Input last name 💬: ");
        String lastNameStaff = createScanner.nextLine();
        System.out.print("Input nickname 💬: ");
        String nickNameStaff = createScanner.nextLine();
        System.out.print("Input address 💬: ");
        String addressStaff = createScanner.nextLine();
        System.out.print("Input zip code 💬: ");
        String zipCodeStaff = createScanner.nextLine();
        System.out.print("Input postal address 💬: ");
        String postalAddressStaff = createScanner.nextLine();
        System.out.print("Input country 💬: ");
        String countryStaff = createScanner.nextLine();
        System.out.print("Input email 💬: ");
        String eMailStaff = createScanner.nextLine();

        if (staffController.save(new Staff(firstNameStaff, lastNameStaff, nickNameStaff, addressStaff, zipCodeStaff, postalAddressStaff, countryStaff, eMailStaff))) {
          System.out.println("✅ Staff added");
        } else {
          System.out.println("❌ Could not save Staff");
        }
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  // READ
  public void showReadMenu() {
    System.out.println("Read Menu (List)");
    System.out.println("1. Read Games Menu");
    System.out.println("2. Read Team Menu");
    System.out.println("3. Read Player Menu");
    System.out.println("4. ⚠️ Read Match Menu ⚠️");
    System.out.println("5. ⚠️ Read Tournament Menu ⚠️");
    System.out.println("6. Read Staff Menu");
    System.out.println("9. Back to CRUD Menu");
    handleReadMenu();
  }

  public void handleReadMenu() {
    Scanner readScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = readScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // read game
        showReadGameMenu();
        break;
      case "2":
        // read team
        showReadTeamMenu();
        break;
      case "3":
        // read player
        showReadPlayerMenu();
        break;
      case "4":
        // read match
        showReadMatchMenu();
        showCrudMenu();
        break;
      case "5":
        // read touranment
        showReadTournamentMenu();
        showCrudMenu();
        break;
      case "6":
        // read staff
        showReadStaffMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  public void showReadGameMenu() {
    System.out.println("Read Games (List)");
    System.out.println("1. List all Games");
    System.out.println("2. List specific Game by ID");
    System.out.println("9. Back to CRUD Menu");
    handleReadGameMenu();
  }

  public void handleReadGameMenu() {
    Scanner readGameScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = readGameScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // Print all games
        System.out.println("List of Games");
        gameController.getAll(true);
        showCrudMenu();
        break;
      case "2":
        // List Game by specific ID
        Scanner fetchGameScanner = new Scanner(System.in);
        // Test to fetch a specific post from database
        gameController.getAll(true);
        System.out.print("Input id of game to fetch 💬: ");
        int inputtedGameId = fetchGameScanner.nextInt();
        Optional<Game> fetchedGame = Optional.ofNullable(gameController.getGameById(inputtedGameId));
        if (fetchedGame.isPresent()) {
          System.out.println("✅ Game " + fetchedGame.get().getName() + " fetched successfully");
          System.out.println(""); // Radbryt

          System.out.println("🪪 ID: " + fetchedGame.get().getId());
          System.out.println("🎮 Game: " + fetchedGame.get().getName());
          System.out.println("🎮 Number of Teams: " + fetchedGame.get().getOwnedTeams().size());

          System.out.println(""); // Radbryt

          // If there are teams added to game, print out list
          if (fetchedGame.get().getOwnedTeams() != null && !fetchedGame.get().getOwnedTeams().isEmpty()) {
            System.out.println("Teams list: ");
            for (Team team : fetchedGame.get().getOwnedTeams()) {
              System.out.println("📝 Signed Team: " + team.getName());
            }
          }

          System.out.println(""); // radbrytning

          // if there are individual players added to game, print out list
          if (fetchedGame.get().getIndividualPlayers() != null && !fetchedGame.get().getIndividualPlayers().isEmpty()) {
            System.out.println("Individual players list:");
            for (Player player : fetchedGame.get().getIndividualPlayers()) {
              if (player.getGame() != null) {
                System.out.println("📝 Player: " + player.getNickName());
              }
            }
          }

          System.out.println(""); // radbrytning

        } else {
          System.out.println("❌ Could not fetch Game");
        }
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  public void showReadTeamMenu() {
    System.out.println("Read Teams (List, Add to Game/Remove from Game)");
    System.out.println("1. List all Teams");
    System.out.println("2. List specific Team by ID");
    System.out.println("3. Add Team to Game");
    System.out.println("4. Remove Team from Game");
    System.out.println("9. Back to CRUD Menu");
    handleReadTeamMenu();
  }

  public void handleReadTeamMenu() {
    Scanner readTeamScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = readTeamScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // List all teams
        System.out.println("List of Teams");
        gameController.getAll(true);
        showCrudMenu();
        break;
      case "2":
        // List specific team by id
        teamController.getAll(true);
        System.out.print("Input id of team to fetch 💬: ");

        System.out.println(""); // radbrytning

        Optional<Team> fetchedTeam = Optional.ofNullable(teamController.getTeamById(new Scanner(System.in).nextInt()));
        if (fetchedTeam.isPresent()) {
          System.out.println("✅ Team " + fetchedTeam.get().getName() + " fetched successfully");
          System.out.println(""); // radbryt
          System.out.println("🪪 ID: " + fetchedTeam.get().getId());
          System.out.println("👾 Team: " + fetchedTeam.get().getName());

          // If team is assigned to game print game
          if (fetchedTeam.get().getGame() != null) {
            System.out.println("🎮 Game: " + fetchedTeam.get().getGame().getName());
          }

          System.out.println("🎮 Player status: " + fetchedTeam.get().getOwnedPlayers().size());
          for (Player players : fetchedTeam.get().getOwnedPlayers()) {
            System.out.println("📝 Signed player: " + players.getNickName());
          }

          System.out.println(""); // radbryt
        } else {
          System.out.println("❌ Could not fetch team");
          System.out.println(""); // radbryt
        }
        showCrudMenu();
        break;
      case "3":
        // add team to game
        gameController.getAll(true);
        System.out.print("Pick Game ID 💬: ");
        int gameId = new Scanner(System.in).nextInt();
        teamController.getAll(true);
        System.out.println("Pick Team " + gameController.getAll(false).get(gameId - 1).getName());
        int teamId = new Scanner(System.in).nextInt();
        if (gameController.addTeamToGame(teamId, gameId)) {
          System.out.println("✅ Team added to Game");
        } else {
          System.out.println("❌ Team failed to add to Game");
        }
        showCrudMenu();
        break;
      case "4":
        // Remove Team from Game
        gameController.getAll(true);
        System.out.print("Pick Game ID 💬: ");
        int gamesId = new Scanner(System.in).nextInt();
        teamController.getAll(true);
        System.out.println("Pick Team " + gameController.getAll(false).get(gamesId - 1).getName());
        int teamsId = new Scanner(System.in).nextInt();
        if (gameController.removeTeamFromGame(teamsId, gamesId)) {
          System.out.println("✅ Team removed from Game");
        } else {
          System.out.println("❌ Team failed to be removed from Game");
        }
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  public void showReadPlayerMenu() {
    System.out.println("Read Players (List, Add to Game/Team/Remove from Game/Team)");
    System.out.println("1. List all Players");
    System.out.println("2. List specific Player by ID");
    System.out.println("3. Add Player to Team");
    System.out.println("4. Remove Player from Team");
    System.out.println("5. Add Player to Game");
    System.out.println("6. Remove Player from Game");
    System.out.println("9. Back to CRUD Menu");
    handleReadPlayerMenu();
  }

  public void handleReadPlayerMenu() {
    Scanner readPlayerScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = readPlayerScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // List all players
        System.out.println("Player list");
        teamController.getAll(true);
        System.out.println(""); // radbryt
        System.out.println("Individual players (teamless)");
        playerController.getAll(true);
        System.out.println(""); // radbryt
        showCrudMenu();
        break;
      case "2":
        // List specific player by id
        playerController.getAll(true);
        System.out.print("Input ID of Player to fetch 💬: ");
        Optional<Player> fetchedPlayer = Optional.ofNullable(playerController.getPlayerById(new Scanner(System.in).nextInt()));
        if (fetchedPlayer.isPresent()) {
          String flagIcon = "";
          if (fetchedPlayer.get().getCountry() != null) {
            if (fetchedPlayer.get().getCountry().equals("Australia")) {
              flagIcon = "🇦🇺";
            } else if (fetchedPlayer.get().getCountry().equals("Belgium")) {
              flagIcon = "🇧🇪";
            } else if (fetchedPlayer.get().getCountry().equals("Brazil")) {
              flagIcon = "🇧🇷";
            } else if (fetchedPlayer.get().getCountry().equals("Bulgaria")) {
              flagIcon = "🇧🇬";
            } else if (fetchedPlayer.get().getCountry().equals("Canada")) {
              flagIcon = "🇨🇦";
            } else if (fetchedPlayer.get().getCountry().equals("Czechia")) {
              flagIcon = "🇨🇿";
            } else if (fetchedPlayer.get().getCountry().equals("Denmark")) {
              flagIcon = "🇩🇰";
            } else if (fetchedPlayer.get().getCountry().equals("England")) {
              flagIcon = "🏴󠁧󠁢󠁥󠁮󠁧󠁿";
            } else if (fetchedPlayer.get().getCountry().equals("Finland")) {
              flagIcon = "🇫🇮";
            } else if (fetchedPlayer.get().getCountry().equals("France")) {
              flagIcon = "🇫🇷";
            } else if (fetchedPlayer.get().getCountry().equals("Germany")) {
              flagIcon = "🇩🇪";
            } else if (fetchedPlayer.get().getCountry().equals("Greece")) {
              flagIcon = "🇬🇷";
            } else if (fetchedPlayer.get().getCountry().equals("Hungary")) {
              flagIcon = "🇭🇺";
            } else if (fetchedPlayer.get().getCountry().equals("Israel")) {
              flagIcon = "🇮🇱";
            } else if (fetchedPlayer.get().getCountry().equals("Italy")) {
              flagIcon = "🇮🇹";
            } else if (fetchedPlayer.get().getCountry().equals("Kazakhstan")) {
              flagIcon = "🇰🇿";
            } else if (fetchedPlayer.get().getCountry().equals("Latvia")) {
              flagIcon = "🇱🇻";
            } else if (fetchedPlayer.get().getCountry().equals("Lithuania")) {
              flagIcon = "🇱🇹";
            } else if (fetchedPlayer.get().getCountry().equals("Montenegro")) {
              flagIcon = "🇲🇪";
            } else if (fetchedPlayer.get().getCountry().equals("Netherlands")) {
              flagIcon = "🇳🇱";
            } else if (fetchedPlayer.get().getCountry().equals("Norway")) {
              flagIcon = "🇳🇴";
            } else if (fetchedPlayer.get().getCountry().equals("Poland")) {
              flagIcon = "🇵🇱";
            } else if (fetchedPlayer.get().getCountry().equals("Romania")) {
              flagIcon = "🇷🇴";
            } else if (fetchedPlayer.get().getCountry().equals("Russia")) {
              flagIcon = "🇷🇺";
            } else if (fetchedPlayer.get().getCountry().equals("Slovenia")) {
              flagIcon = "🇸🇮";
            } else if (fetchedPlayer.get().getCountry().equals("South Africa")) {
              flagIcon = "🇿🇦";
            } else if (fetchedPlayer.get().getCountry().equals("South Korea")) {
              flagIcon = "🇰🇷";
            } else if (fetchedPlayer.get().getCountry().equals("Spain")) {
              flagIcon = "🇪🇸";
            } else if (fetchedPlayer.get().getCountry().equals("Sweden")) {
              flagIcon = "🇸🇪";
            } else if (fetchedPlayer.get().getCountry().equals("Ukraine")) {
              flagIcon = "🇺🇦";
            } else if (fetchedPlayer.get().getCountry().equals("USA")) {
              flagIcon = "🇺🇸";
            }
          }

          System.out.println("✅ Player " + fetchedPlayer.get().getNickName() + " fetched successfully");

          System.out.println(""); // radbrytning

          System.out.println("🪪 ID: " + fetchedPlayer.get().getId());
          System.out.println("ℹ️ Name: " + fetchedPlayer.get().getFirstName() + " '" + fetchedPlayer.get().getNickName() + "' " + fetchedPlayer.get().getLastName());
          if (fetchedPlayer.get().getAddress() != null) {
            System.out.println("📍 Address: " + fetchedPlayer.get().getAddress() + ", " + fetchedPlayer.get().getZipCode() + " " + fetchedPlayer.get().getPostalAddress());
          }
          if (fetchedPlayer.get().getCountry() != null) {
            System.out.println(flagIcon + " Country: " + fetchedPlayer.get().getCountry());
          }
          if (fetchedPlayer.get().geteMail() != null) {
            System.out.println("✉️ E-mail: " + fetchedPlayer.get().geteMail());
          }

          // if player does not have team, post the game if person is connected to game
          if (fetchedPlayer.get().getTeam() == null) {
            if (fetchedPlayer.get().getGame() != null) {
              System.out.println("🎮 Game: " + fetchedPlayer.get().getGame().getName());
            }

            System.out.println("🟢 Team status: Not signed");
            System.out.println("Currently a Free Agent");
            System.out.println(""); // radbrytning
          } // else if player has team, print team game
          else {
            if (fetchedPlayer.get().getTeam().getGame() != null) {
              System.out.println("🎮 Game: " + fetchedPlayer.get().getTeam().getGame().getName());
            }

            System.out.println("🔴 Team status: Signed");
            System.out.println("Representing: " + fetchedPlayer.get().getTeam().getName());
            System.out.println(""); // radbrytning
          }

        } else {
          System.out.println("❌ Could not fetch Player");

          System.out.println(""); // radbrytning
        }
        showCrudMenu();
        break;
      case "3":
        // add player to team
        teamController.getAll(true);
        System.out.print("Pick Team ID 💬: ");
        int teamId = new Scanner(System.in).nextInt();
        playerController.getAll(true);
        System.out.println("Pick Player " + teamController.getAll(false).get(teamId - 1).getName());
        int playerId = new Scanner(System.in).nextInt();
        if (teamController.addPlayerToTeam(playerId, teamId)) {
          System.out.println("✅ Player added to Team");
        } else {
          System.out.println("❌ Player failed to add to Team");
        }
        showCrudMenu();
        break;
      case "4":
        // Remove Player from Team
        teamController.getAll(true);
        System.out.print("Pick Team ID 💬: ");
        int teamsId = new Scanner(System.in).nextInt();
        playerController.getAll(true);
        System.out.println("Pick player " + teamController.getAll(false).get(teamsId - 1).getName());
        int playersId = new Scanner(System.in).nextInt();
        if (teamController.removePlayerFromTeam(playersId, teamsId)) {
          System.out.println("✅ Player removed from Team");
        } else {
          System.out.println("❌ Player failed to be removed from Team");
        }
        showCrudMenu();
        break;
      case "5":
        // add player to game
        gameController.getAll(true);
        System.out.print("Pick Game ID 💬: ");
        int gameId = new Scanner(System.in).nextInt();
        playerController.getAll(true);
        System.out.println("Pick Player " + gameController.getAll(false).get(gameId - 1).getName());
        int playerId_to_game = new Scanner(System.in).nextInt();
        if (gameController.addPlayerToGame(playerId_to_game, gameId)) {
          System.out.println("✅ Player added to Game");
        } else {
          System.out.println("❌ Player failed to be added to Game");
        }
        showCrudMenu();
        break;
      case "6":
        // Remove Player from Game
        gameController.getAll(true);
        System.out.print("Pick Game ID 💬: ");
        int gamesId = new Scanner(System.in).nextInt();
        playerController.getAll(true);
        System.out.println("Pick Player " + gameController.getAll(false).get(gamesId - 1).getName());
        int playerId_remov_game = new Scanner(System.in).nextInt();
        if (gameController.removePlayerFromGame(playerId_remov_game, gamesId)) {
          System.out.println("✅ Player removed from Game");
        } else {
          System.out.println("❌ Player failed to be removed from Game");
        }
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  public void showReadMatchMenu() {
    System.out.println("⚠️ Read Match Menu (List, Add to Match/Remove from Match ⚠️");
    System.out.println("1. ⚠️ List all Matches ⚠️");
    System.out.println("2. ⚠️ List specific Match by ID ⚠️");
    System.out.println("3. ⚠️ Add Team to Match ⚠️");
    System.out.println("4. ⚠️ Remove Team from Match ⚠️");
    System.out.println("5. ⚠️ Add Player from Match ⚠️");
    System.out.println("6. ⚠️ Remove Player from Match ⚠️");
    System.out.println("9. Back to CRUD Menu");
    handleReadMatchMenu();
  }

  public void handleReadMatchMenu() {
    Scanner readMatchScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = readMatchScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // List all Matches
        System.out.println("⚠️ List all Matches IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "2":
        // List specific Match by ID
        System.out.println("⚠️ List specific Match by ID IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "3":
        // Add Team to Match
        System.out.println("⚠️ Add Team to Match IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "4":
        // Remove Team from Match
        System.out.println("⚠️ Remove Team from Match IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "5":
        // Add player to match
        System.out.println("⚠️ Add Player to Match IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "6":
        // Remove player from match
        System.out.println("⚠️ Remove Player from Match IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  public void showReadTournamentMenu() {
    System.out.println("⚠️ Read Tournament Menu (List, Add to Tournament/Remove from Tournament ⚠️");
    System.out.println("1. ⚠️ List all Tournaments ⚠️");
    System.out.println("2. ⚠️ List specific Tournament by ID ⚠️");
    System.out.println("3. ⚠️ Add Team to Tournament ⚠️");
    System.out.println("4. ⚠️ Remove Team from Tournament ⚠️");
    System.out.println("5. ⚠️ Add Player from Tournament ⚠️");
    System.out.println("6. ⚠️ Remove Player from Tournament ⚠️");
    System.out.println("9. Back to CRUD Menu");
    handleReadTournamentMenu();
  }

  public void handleReadTournamentMenu() {
    Scanner readTournamentScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = readTournamentScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // List all Tournaments
        System.out.println("⚠️ List all Tournaments IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "2":
        // List specific Tournament by ID
        System.out.println("⚠️ List specific Tournament by ID IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "3":
        // Add Team to Tournament
        System.out.println("⚠️ Add Team to Tournament IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "4":
        // Remove Team from Tournament
        System.out.println("⚠️ Remove Team from Tournament IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "5":
        // Add player to Tournament
        System.out.println("⚠️ Add Player to Tournaent IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "6":
        // Remove player from Tournament
        System.out.println("⚠️ Remove Player from Tournament IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  public void showReadStaffMenu() {
    System.out.println("Read Staff Menu (List)");
    System.out.println("1. List all Staff");
    System.out.println("2. List specific Staff by ID");
    System.out.println("9. Back to CRUD Menu");
    handleReadStaffMenu();
  }

  public void handleReadStaffMenu() {
    Scanner readStaffScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = readStaffScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // List all staff
        System.out.println("Staff list");
        staffController.getAll(true);
        showCrudMenu();
        break;
      case "2":
        // List staff by id
        staffController.getAll(true);
        System.out.print("Input id of staff to fetch 💬: ");
        Optional<Staff> fetchedStaff = Optional.ofNullable(staffController.getStaffById(new Scanner(System.in).nextInt()));
        if (fetchedStaff.isPresent()) {
          String flagIcon = "";
          if (fetchedStaff.get().getCountry().equals("Australia")) {
            flagIcon = "🇦🇺";
          } else if (fetchedStaff.get().getCountry().equals("Belgium")) {
            flagIcon = "🇧🇪";
          } else if (fetchedStaff.get().getCountry().equals("Brazil")) {
            flagIcon = "🇧🇷";
          } else if (fetchedStaff.get().getCountry().equals("Bulgaria")) {
            flagIcon = "🇧🇬";
          } else if (fetchedStaff.get().getCountry().equals("Canada")) {
            flagIcon = "🇨🇦";
          } else if (fetchedStaff.get().getCountry().equals("Czechia")) {
            flagIcon = "🇨🇿";
          } else if (fetchedStaff.get().getCountry().equals("Denmark")) {
            flagIcon = "🇩🇰";
          } else if (fetchedStaff.get().getCountry().equals("England")) {
            flagIcon = "🏴󠁧󠁢󠁥󠁮󠁧󠁿";
          } else if (fetchedStaff.get().getCountry().equals("Finland")) {
            flagIcon = "🇫🇮";
          } else if (fetchedStaff.get().getCountry().equals("France")) {
            flagIcon = "🇫🇷";
          } else if (fetchedStaff.get().getCountry().equals("Germany")) {
            flagIcon = "🇩🇪";
          } else if (fetchedStaff.get().getCountry().equals("Greece")) {
            flagIcon = "🇬🇷";
          } else if (fetchedStaff.get().getCountry().equals("Hungary")) {
            flagIcon = "🇭🇺";
          } else if (fetchedStaff.get().getCountry().equals("India")) {
            flagIcon = "🇮🇳";
          } else if (fetchedStaff.get().getCountry().equals("Israel")) {
            flagIcon = "🇮🇱";
          } else if (fetchedStaff.get().getCountry().equals("Italy")) {
            flagIcon = "🇮🇹";
          } else if (fetchedStaff.get().getCountry().equals("Kazakhstan")) {
            flagIcon = "🇰🇿";
          } else if (fetchedStaff.get().getCountry().equals("Latvia")) {
            flagIcon = "🇱🇻";
          } else if (fetchedStaff.get().getCountry().equals("Lithuania")) {
            flagIcon = "🇱🇹";
          } else if (fetchedStaff.get().getCountry().equals("Montenegro")) {
            flagIcon = "🇲🇪";
          } else if (fetchedStaff.get().getCountry().equals("Netherlands")) {
            flagIcon = "🇳🇱";
          } else if (fetchedStaff.get().getCountry().equals("Norway")) {
            flagIcon = "🇳🇴";
          } else if (fetchedStaff.get().getCountry().equals("Poland")) {
            flagIcon = "🇵🇱";
          } else if (fetchedStaff.get().getCountry().equals("Romania")) {
            flagIcon = "🇷🇴";
          } else if (fetchedStaff.get().getCountry().equals("Russia")) {
            flagIcon = "🇷🇺";
          } else if (fetchedStaff.get().getCountry().equals("Slovenia")) {
            flagIcon = "🇸🇮";
          } else if (fetchedStaff.get().getCountry().equals("South Africa")) {
            flagIcon = "🇿🇦";
          } else if (fetchedStaff.get().getCountry().equals("South Korea")) {
            flagIcon = "🇰🇷";
          } else if (fetchedStaff.get().getCountry().equals("Spain")) {
            flagIcon = "🇪🇸";
          } else if (fetchedStaff.get().getCountry().equals("Sweden")) {
            flagIcon = "🇸🇪";
          } else if (fetchedStaff.get().getCountry().equals("Ukraine")) {
            flagIcon = "🇺🇦";
          } else if (fetchedStaff.get().getCountry().equals("USA")) {
            flagIcon = "🇺🇸";
          }
          System.out.println("✅ Staff " + fetchedStaff.get().getNickName() + " fetched successfully");

          System.out.println(""); // radbrytning

          System.out.println("🪪 ID: " + fetchedStaff.get().getId());
          System.out.println("ℹ️ Name: " + fetchedStaff.get().getFirstName() + " '" + fetchedStaff.get().getNickName() + "' " + fetchedStaff.get().getLastName());
          System.out.println("📍 Adress: " + fetchedStaff.get().getAddress() + ", " + fetchedStaff.get().getId() + " " + fetchedStaff.get().getPostalAddress());
          System.out.println(flagIcon + " Country: " + fetchedStaff.get().getCountry());
          System.out.println("✉️ E-mail: " + fetchedStaff.get().geteMail());

          System.out.println(""); // Radbryt
        } else {
          System.out.println("❌ Could not find staff");
          System.out.println(""); // Radbryt
        }
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  // UPDATE
  public void showUpdateMenu() {
    System.out.println("Update Menu");
    System.out.println("1. Update Game");
    System.out.println("2. Update Team");
    System.out.println("3. Update Player");
    System.out.println("4. Update Match");
    System.out.println("5. Update Tournament");
    System.out.println("6. Update Staff");
    System.out.println("9. Back to CRUD Menu");
    handleUpdateMenu();
  }

  public void handleUpdateMenu() {
    Scanner updateScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = updateScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // update game
        gameController.getAll(true);
        System.out.print("Input id 💬: ");
        Game gameToUpdate = gameController.getGameById(new Scanner(System.in).nextInt());
        System.out.print("Change name from " + gameToUpdate.getName() + " to 💬: ");
        gameToUpdate.setName(new Scanner(System.in).nextLine());
        if (gameController.updateGame(gameToUpdate)) {
          System.out.println("✅ Game updated");
        } else {
          System.out.println("❌ Game update failed");
        }
        showCrudMenu();
        break;
      case "2":
        // update team
        teamController.getAll(true);
        System.out.print("Input id 💬: ");
        Team teamToUpdate = teamController.getTeamById(new Scanner(System.in).nextInt());
        System.out.print("Change name from " + teamToUpdate.getName() + " to 💬: ");
        teamToUpdate.setName(new Scanner(System.in).nextLine());
        if (teamController.updateTeam(teamToUpdate)) {
          System.out.println("✅ Team updated");
        } else {
          System.out.println("❌ Team update failed");
        }
        showCrudMenu();
        break;
      case "3":
        // update player
        showPlayerUpdateMenu();
        break;
      case "4":
        // update match
        System.out.println("⚠️ MATCH IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "5":
        // update tournament
        System.out.println("⚠️ TOURNAMENT IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "6":
        // update staff
        showStaffUpdateMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  public void showPlayerUpdateMenu() {
    System.out.println("Player Update Menu");
    System.out.println("0. Update Everything");
    System.out.println("1. Update first name");
    System.out.println("2. Update last name");
    System.out.println("3. Update nickname");
    System.out.println("4. Update address");
    System.out.println("5. Update zip code");
    System.out.println("6. Update postal address");
    System.out.println("7. Update country");
    System.out.println("8. Update email");
    System.out.println("9. Back to Staff Menu");
    handlePlayerUpdateMenu();
  }

  public void handlePlayerUpdateMenu() {
    Scanner updateScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = updateScanner.nextLine();
    space();
    switch (userChoice) {
      case "0":
        // update everything
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        int playerIdToUpdate = new Scanner(System.in).nextInt();
        Player playerToUpdate = playerController.getPlayerById(playerIdToUpdate);

        System.out.println("Change player details:");

        System.out.print("First name from " + playerToUpdate.getFirstName() + " to 💬: ");
        playerToUpdate.setFirstName(new Scanner(System.in).nextLine());

        System.out.print("Last name from " + playerToUpdate.getLastName() + " to 💬: ");
        playerToUpdate.setLastName(new Scanner(System.in).nextLine());

        System.out.print("Nickname from " + playerToUpdate.getNickName() + " to 💬: ");
        playerToUpdate.setNickName(new Scanner(System.in).nextLine());

        System.out.print("Address from " + playerToUpdate.getAddress() + " to 💬: ");
        playerToUpdate.setAddress(new Scanner(System.in).nextLine());

        System.out.print("Zip code from " + playerToUpdate.getZipCode() + " to 💬: ");
        playerToUpdate.setZipCode(new Scanner(System.in).nextLine());

        System.out.print("Postal address from " + playerToUpdate.getPostalAddress() + " to 💬: ");
        playerToUpdate.setPostalAddress(new Scanner(System.in).nextLine());

        System.out.print("Country from " + playerToUpdate.getCountry() + " to 💬: ");
        playerToUpdate.setCountry(new Scanner(System.in).nextLine());

        System.out.print("Email from " + playerToUpdate.geteMail() + " to 💬: ");
        playerToUpdate.seteMail(new Scanner(System.in).nextLine());

        if (playerController.updatePlayer(playerToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");
        }
        showCrudMenu();
        break;
      case "1":
        // update firstname
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        Player firstNameToUpdate = playerController.getPlayerById(new Scanner(System.in).nextInt());
        System.out.print("Change first name from " + firstNameToUpdate.getFirstName() + " to 💬: ");
        firstNameToUpdate.setFirstName(new Scanner(System.in).nextLine());
        if (playerController.updatePlayer(firstNameToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");
        }
        showCrudMenu();
        break;
      case "2":
        // update last name
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        Player lastNameToUpdate = playerController.getPlayerById(new Scanner(System.in).nextInt());
        System.out.print("Change last name from " + lastNameToUpdate.getLastName() + " to 💬: ");
        lastNameToUpdate.setLastName(new Scanner(System.in).nextLine());
        if (playerController.updatePlayer(lastNameToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");
        }
        showCrudMenu();
        break;
      case "3":
        // update nickname
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        Player nickNameToUpdate = playerController.getPlayerById(new Scanner(System.in).nextInt());
        System.out.print("Change nickname from " + nickNameToUpdate.getNickName() + " to 💬: ");
        nickNameToUpdate.setNickName(new Scanner(System.in).nextLine());
        if (playerController.updatePlayer(nickNameToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");
        }
        showCrudMenu();
        break;
      case "4":
        // update address
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        Player addressToUpdate = playerController.getPlayerById(new Scanner(System.in).nextInt());
        System.out.print("Change address from " + addressToUpdate.getAddress() + " to 💬: ");
        addressToUpdate.setAddress(new Scanner(System.in).nextLine());
        if (playerController.updatePlayer(addressToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");
        }
        showCrudMenu();
        break;
      case "5":
        // update zip code
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        Player zipCodeToUpdate = playerController.getPlayerById(new Scanner(System.in).nextInt());
        System.out.print("Change zip code from " + zipCodeToUpdate.getZipCode() + " to 💬: ");
        zipCodeToUpdate.setZipCode(new Scanner(System.in).nextLine());
        if (playerController.updatePlayer(zipCodeToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");
        }
        showCrudMenu();
        break;
      case "6":
        // update postal address
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        Player postalAddrToUpdate = playerController.getPlayerById(new Scanner(System.in).nextInt());
        System.out.print("Change postal address from " + postalAddrToUpdate.getPostalAddress() + " to 💬: ");
        postalAddrToUpdate.setPostalAddress(new Scanner(System.in).nextLine());
        if (playerController.updatePlayer(postalAddrToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");
        }
        showCrudMenu();
        break;
      case "7":
        // update country
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        Player countryToUpdate = playerController.getPlayerById(new Scanner(System.in).nextInt());
        System.out.print("Change country from " + countryToUpdate.getCountry() + " to 💬: ");
        countryToUpdate.setCountry(new Scanner(System.in).nextLine());
        if (playerController.updatePlayer(countryToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");
        }
        showCrudMenu();
        break;
      case "8":
        // update email
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        Player eMailToUpdate = playerController.getPlayerById(new Scanner(System.in).nextInt());
        System.out.print("Change email from " + eMailToUpdate.geteMail() + " to 💬: ");
        eMailToUpdate.seteMail(new Scanner(System.in).nextLine());
        if (playerController.updatePlayer(eMailToUpdate)) {
          System.out.println("✅ Player updated");
        } else {
          System.out.println("❌ Player update failed");

        }
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  public void showStaffUpdateMenu() {
    System.out.println("Staff Update Menu");
    System.out.println("0. Update Everything");
    System.out.println("1. Update first name");
    System.out.println("2. Update last name");
    System.out.println("3. Update nickname");
    System.out.println("4. Update address");
    System.out.println("5. Update zip code");
    System.out.println("6. Update postal address");
    System.out.println("7. Update country");
    System.out.println("8. Update email");
    System.out.println("9. Back to Staff Menu");
    handleStaffUpdateMenu();
  }

  public void handleStaffUpdateMenu() {
    Scanner updateScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = updateScanner.nextLine();
    space();
    switch (userChoice) {
      case "0":
        // update everything
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        int staffIdToUpdate = new Scanner(System.in).nextInt();
        Staff staffToUpdate = staffController.getStaffById(staffIdToUpdate);

        System.out.println("Change player details:");

        System.out.print("First name from " + staffToUpdate.getFirstName() + " to 💬: ");
        staffToUpdate.setFirstName(new Scanner(System.in).nextLine());

        System.out.print("Last name from " + staffToUpdate.getLastName() + " to 💬: ");
        staffToUpdate.setLastName(new Scanner(System.in).nextLine());

        System.out.print("Nickname from " + staffToUpdate.getNickName() + " to 💬: ");
        staffToUpdate.setNickName(new Scanner(System.in).nextLine());

        System.out.print("Address from " + staffToUpdate.getAddress() + " to 💬: ");
        staffToUpdate.setAddress(new Scanner(System.in).nextLine());

        System.out.print("Zip code from " + staffToUpdate.getZipCode() + " to 💬: ");
        staffToUpdate.setZipCode(new Scanner(System.in).nextLine());

        System.out.print("Postal address from " + staffToUpdate.getPostalAddress() + " to 💬: ");
        staffToUpdate.setPostalAddress(new Scanner(System.in).nextLine());

        System.out.print("Country from " + staffToUpdate.getCountry() + " to 💬: ");
        staffToUpdate.setCountry(new Scanner(System.in).nextLine());

        System.out.print("Email from " + staffToUpdate.geteMail() + " to 💬: ");
        staffToUpdate.seteMail(new Scanner(System.in).nextLine());

        if (staffController.updateStaff(staffToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "1":
        // update firstname
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        Staff firstNameToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change first name from " + firstNameToUpdate.getFirstName() + " to 💬: ");
        firstNameToUpdate.setFirstName(new Scanner(System.in).nextLine());
        if (staffController.updateStaff(firstNameToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "2":
        // update last name
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        Staff lastNameToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change last name from " + lastNameToUpdate.getLastName() + " to 💬: ");
        lastNameToUpdate.setLastName(new Scanner(System.in).nextLine());
        if (staffController.updateStaff(lastNameToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "3":
        // update nickname
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        Staff nickNameToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change nickname from " + nickNameToUpdate.getNickName() + " to 💬: ");
        nickNameToUpdate.setNickName(new Scanner(System.in).nextLine());
        if (staffController.updateStaff(nickNameToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "4":
        // update address
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        Staff addressToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change address from " + addressToUpdate.getAddress() + " to 💬: ");
        addressToUpdate.setAddress(new Scanner(System.in).nextLine());
        if (staffController.updateStaff(addressToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "5":
        // update zip code
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        Staff zipCodeToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change zip code from " + zipCodeToUpdate.getZipCode() + " to 💬: ");
        zipCodeToUpdate.setZipCode(new Scanner(System.in).nextLine());
        if (staffController.updateStaff(zipCodeToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "6":
        // update postal address
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        Staff postalAddrToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change postal address from " + postalAddrToUpdate.getPostalAddress() + " to 💬: ");
        postalAddrToUpdate.setPostalAddress(new Scanner(System.in).nextLine());
        if (staffController.updateStaff(postalAddrToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "7":
        // update country
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        Staff countryToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change country from " + countryToUpdate.getCountry() + " to 💬: ");
        countryToUpdate.setCountry(new Scanner(System.in).nextLine());
        if (staffController.updateStaff(countryToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "8":
        // update email
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        Staff eMailToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change email from " + eMailToUpdate.geteMail() + " to 💬: ");
        eMailToUpdate.seteMail(new Scanner(System.in).nextLine());
        if (staffController.updateStaff(eMailToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");

        }
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  // DELETE
  public void showDeleteMenu() {
    System.out.println("Delete Menu");
    System.out.println("1. Delete Game");
    System.out.println("2. Delete Team");
    System.out.println("3. Delete Player");
    System.out.println("4. Delete Match");
    System.out.println("5. Delete Tournament");
    System.out.println("6. Delete Staff");
    System.out.println("9. Back to CRUD Menu");
    handleDeleteMenu();
  }

  public void handleDeleteMenu() {
    Scanner deleteScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = deleteScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        // Delete game
        gameController.getAll(true);
        System.out.print("Input id 💬: ");
        if (gameController.deleteGameById(new Scanner(System.in).nextInt())) {
          System.out.println("✅ Game deleted");
        } else {
          System.out.println("❌ Failed to delete Game");
        }
        showCrudMenu();
        break;
      case "2":
        // Delete Team
        teamController.getAll(true);
        System.out.print("Input id 💬: ");
        if (teamController.deleteTeamById(new Scanner(System.in).nextInt())) {
          System.out.println("✅ Team deleted");
        } else {
          System.out.println("❌ Failed to delete Team");
        }
        showCrudMenu();
        break;
      case "3":
        // Delete Player
        playerController.getAll(true);
        System.out.print("Input id 💬: ");
        if (playerController.deletePlayerById(new Scanner(System.in).nextInt())) {
          System.out.println("✅ Player deleted");
        } else {
          System.out.println("❌ Failed to delete Player");
        }
        showCrudMenu();
        break;
      case "4":
        // Delete Match
        System.out.println("⚠️ MATCH IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "5":
        // Delete Tournament
        System.out.println("⚠️ TOURNAMENT IS UNDER CONSTRUCTION ⚠️");
        showCrudMenu();
        break;
      case "6":
        // Delete Staff
        staffController.getAll(true);
        System.out.print("Input id 💬: ");
        if (staffController.deleteStaffById(new Scanner(System.in).nextInt())) {
          System.out.println("✅ Staff deleted");
        } else {
          System.out.println("❌ Failed to delete Staff");
        }
        showCrudMenu();
        break;
      default:
        showCrudMenu();
        break;
    }
  }

  // Create assets
  public void createStaff() {
    // if there are no staff
    if (staffController.getAll(false).isEmpty()) {
      // Creating staff
      staffController.save(new Staff("Richard", "Hendricks", "R_Hendricks", "5230 Newell Rd", "NONE", "Palo Alto", "USA", "r_hendricks@piedpiper.com"));
      staffController.save(new Staff("Bertram", "Gilfoyle", "B_Gilfoyle", "5230 Newell Rd", "NONE", "Palo Alto", "USA", "b_gilfoyle@piedpiper.com"));
      staffController.save(new Staff("Dinesh", "Chugtai", "D_Chugtai", "5230 Newell Rd", "NONE", "Palo Alto", "India", "d_chugtai@piedpiper.com"));
    }
  }

  private void createPlayersAndTeamsDataForStaff() {
    List<Game> gamesToAdd = new ArrayList<>();
    List<Team> teamsToAdd = new ArrayList<>();
    List<Player> playersToAdd = new ArrayList<>();


    gamesToAdd.addAll(List.of(
        // Games
        new Game("Counter-Strike 2"),
        new Game("EA Sports FC 24"),
        new Game("League of Legends")));

    teamsToAdd.addAll(List.of(
        // Teams
        // Counter-Strike 2
        new Team("NaVi"), new Team("ENCE"),
        new Team("Cloud9"), new Team("FaZe"),
        new Team("Heroic"), new Team("Complexity"),
        new Team("Vitality"), new Team("MOUZ"),
        // EA Sports FC 24
        new Team("AFC Ajax Brazil"), new Team("Team Gullit"),
        new Team("DUX America"), new Team("RBLZ Gaming"),
        new Team("Team FUTWIZ"), new Team("Atlanta United FC"),
        new Team("TG NIP"), new Team("Team Exeed"),
        // League of Legends
        new Team("NRG eSports"), new Team("G2 eSports"),
        new Team("Fnatic"), new Team("MAD Lions"),
        new Team("Cloud9LOL"), new Team("LOUD"),
        new Team("Team Liquid"), new Team("Team BDS")));
    playersToAdd.addAll(List.of(
        // Players
        // Counter-Strike 2
        // NaVi
        new Player("Valerii", "Vakhovskyi", "b1t", "Kosmonavtov", "NONE", "DNEPROPETROVSK", "Ukraine", "b1t@navi.com"),
        new Player("Justinas", "Lekavicius", "jL", "52 Luknojų", "06295", "VILNIUS", "Lithuania", "jL@navi.com"),
        new Player("Aleksi", "Virolainen", "Aleksib", "Kluuvikatu 5", "01380", "UUSIMAA", "Finland", "aleksib@navi.com"),
        new Player("Mihai", "Ivan", "iM", "Nr.179B, Creaca", "NONE", "SALAJ", "Romania", "iM@navi.com"),
        new Player("Ihor", "Zhdanov", "w0nderful", "Mineralnaya 18", "NONE", "IRPEN", "Ukraine", "w0nderful@navi.com"),
        // ENCE
        new Player("Lukas", "Rossander", "gla1ve", "Lumbyholmvej 39", "3390", "SJAELLAND", "Denmark", "gla1ve@ence.gg"),
        new Player("Pawel", "Dycha", "dycha", "Chlodna 25", "40-311", "KATOWICE", "Poland", "dycha@ence.gg"),
        new Player("Pavle", "Boskovic", "maden", "60, 19. Decembra", "81110", "PODGORICA", "Montenegro", "dycha@ence.gg"),
        new Player("Alvaro", "Garcia", "SunPayus", "Puerta Nueva 62", "36380", "PONTEVEDRA", "Spain", "sunpayus@ence.gg"),
        new Player("Guy", "Iluz", "NertZ", "3 Odem", "NONE", "PETAH TIKVA", "Israel", "nertz@ence.gg"),
        // Cloud 9
        new Player("Abay", "Khassenov", "HObbit", "11 Mikrorayon", "NONE", "AKTOBE", "Kazakhstan", "HObbit@cloud9.gg"),
        new Player("Ilia", "Zalutskii", "Perfecto", "CHapaeva 4", "NONE", "CHEBOKSARY", "Russia", "perfecto@cloud9.gg"),
        new Player("Kirill", "Mikhaylov", "Boombl4", "Gvozdkova 16", "NONE", "VOLGOGRAD", "Russia", "boombl4@cloud9.gg"),
        new Player("Denis", "Sharipov", "electroNic", "61 Kvartal", "NONE", "ANGARSK", "Russia", "electroNic@cloud9.gg"),
        new Player("Dmitrii", "Sokolov", "sh1ro", "Mira 196", "NONE", "STAVROPOL", "Russia", "sh1ro@cloud9.gg"),
        // FaZe
        new Player("Helvijs", "Saukants", "Broky", "Ludzas 52", "NONE", "RIGA", "Latvia", "broky@fazeclan.com"),
        new Player("Robin", "Kool", "ropz", "Köie 13", "11621", "TALLINN", "Estonia", "ropz@fazeclan.com"),
        new Player("Finn", "Andersen", "KARRIGAN", "Kongshöjvej 13", "1214", "SJAELLAND", "Denmark", "karrigan@fazeclan.com"),
        new Player("Olof", "Kajbjer", "OLOFMEISTER", "Violvägen 22", "267 00", "BJUV", "Sweden", "olofmeister@fazeclan.com"),
        new Player("Håvard", "Nygaard", "RAIN", "Duestien 144", "3717", "SKIEN", "Norway", "rain@fazeclan.com"),
        // Heroic
        new Player("René", "Madsen", "TeSeS", "Bakkelunden 83", "3740", "HOVEDSTADEN", "Denmark", "teses@heroic.gg"),
        new Player("Rasmus", "Beck", "sjuush", "Stude Strand 85", "1125", "SJAELLAND", "Denmark", "sjuush@heroic.gg"),
        new Player("Peter", "Rasmussen", "dupreeh", "Skolegade 39", "1656", "KÖBENHAVN K", "Denmark", "dupreeh@heroic.gg"),
        new Player("Rasmus", "Nordfoss", "Zyphon", "Degnehöjvej 25", "9870", "SINDAL", "Denmark", "zyphon@heroic.gg"),
        new Player("Casper", "Möller", "cadiaN", "Lille Vibybej 66", "8340", "MALLING", "Denmark", "cadian@heroic.gg"),
        // Complexity
        new Player("Jonathan", "Jablondwski", "EliGE", "1946 Vine Street", "99149", "MALDEN", "USA", "elige@complexity.gg"),
        new Player("Håkon", "Fjärli", "hallzerk", "Bekkasinveien 94", "4318", "SANDNES", "Norway", "hallzerk@complexity.gg"),
        new Player("Ricky", "Kemery", "Grim", "1945 Hardman Road", "01115", "SPRINGFIELD", "USA", "grim@complexity.gg"),
        new Player("Michael", "Wince", "floppy", "1100 Stonepot Road", "07102", "NEWARK", "USA", "floppy@complexity.gg"),
        new Player("Johnny", "Theodosiou", "JT", "1964 Barlow Street", "0638", "LIMPOPO", "South Africa", "jt@complexity.gg"),
        // Vitality
        new Player("William", "Merriman", "mezii", "13 Telford Street", "S43 6LU", "BARLBOROUGH", "England", "mezii@vitality.gg"),
        new Player("Shahar", "Shushan", "flameZ", "6 Saadia Garon", "NONE", "REHOVOT", "Israel", "flamez@vitality.gg"),
        new Player("Lotan", "Giladi", "Spinx", "34 Itzhak Sade", "NONE", "TEL AVIV", "Israel", "spinx@vitality.gg"),
        new Player("Mathieu", "Herbaut", "ZywOo", "82 Rue Roussy", "93130", "NOISY-LE-SEC", "France", "zywoo@vitality.gg"),
        new Player("Dan", "Madesclaire", "apEX", "17 rue Porte dOrange", "84300", "CAVAILLON", "France", "apex@vitality.gg"),
        // MOUZ
        new Player("Adam", "Torzsas", "torzsi", "Izabella 87", "8444", "SZENTGÁL", "Hungary", "torzsi@mousesports.com"),
        new Player("Jimi", "Salo", "Jimpphat", "Pietarinkuja 26", "61807", "KAUHAJOKI", "Finland", "jimpphat@mousesports.com"),
        new Player("Kamil", "Szkaradek", "siuhy", "Armii Ludowej 7", "44-251", "RYBNIK", "Poland", "siuhy@mousesports.com"),
        new Player("Dorian", "Berman", "xertioN", "2 Itaron", "NONE", "JERUSALEM", "Israel", "xertion@mousesports.com"),
        new Player("Dennis", "Nielsen", "sycrone", "Brogade 99", "6715", "Esbjerg N", "Denmark", "sycrone@mousesports.com"),
        // EA Sports FC 24
        new Player("Paulo", "Ferreira", "PHzin", "Rua Copaiba 1474", "79065-410", "CAMPO GRANDE", "Brazil", "PHzin@ajax.com"), // AFC Ajax Brazil
        new Player("Manuel", "Bachoore", "Manu", "De Laan 76", "9981 GR", "UITHUIZEN", "Netherlands", "manu@teamgullit.com"), // Team Gullit
        new Player("Ander", "Tobal", "Neat", "Herreria 38", "18197", "GRANADA", "Spain", "neat@duxamerica.com"), // DUX America
        new Player("Umut", "Gultekin", "Umut", "Reinickendorf 46", "87515", "SONTHOFEN", "Germany", "umut@rblzgaming.com"), // RBLZ Gaming
        new Player("Mark", "Zakhary", "Mark11", "47 McGregor Street", "2879", "MENINDEE", "Australia", "mark11@futwiz.com"), // Team FUTWIZ
        new Player("Paulo", "Neto", "PauloNeto999", "Estrada da Familia 1835", "04895-060", "SÃO PAULO", "Brazil", "pauloneto999@atlunitedfc.com"), // Atlanta United FC
        new Player("Olle", "Arbin", "Ollelito", "Västra Långgatan 15", "590 40", "KISA", "Sweden", "ollelito@nip.com"), // TG NIP
        new Player("Francesco", "Pio Tagliafierro", "Obrun2002", "Via Torino 3", "29100", "PIACENZA", "Italy", "obrun2002@exeed.com"), // Team Exeed
        // League of Legends
        // NRG eSports
        new Player("Niship", "Doshi", "Dhokla", "3329 Crestview Terrace", "78130", "NEW BRAUNFELS", "USA", "dhokla@NRG.com"),
        new Player("Juan", "Arturo Garcia", "Contractz", "230 Tennessee Avenue", "06708", "WATERBURY", "USA", "contractz@NRG.com"),
        new Player("Cristian", "Palafox", "Palafox", "1590 Willis Avenue", "32037", "PALM COAST", "USA", "@NRG.com"),
        new Player("Lee", "Dong-geun", "IgNar", "Moga-myeon 131", "NONE", "GYEONGGI-DO", "South Korea", "ignar@NRG.com"),
        new Player("Victor", "Huang", "FBI", "85 Bass Street", "2480", "LILLIAN ROCK", "Australia", "fbi@NRG.com"),
        // G2 eSports
        new Player("Sergen", "Celik", "BrokenBlade", "Kirchenallee 17", "94508", "SCHÖLLNACH", "Germany", "brokenblade@G2.com"),
        new Player("Martin", "Sundelin", "Yike", "Långgatan 36", "566 00", "HABO", "Sweden", "yike@G2.com"),
        new Player("Rasmus", "Borregaard Winther", "Caps", "Brogade 71", "4593", "ESKEBJERG", "Denmark", "caps@G2.com"),
        new Player("Steven", "Liv", "Hans Sama", "94 rue du Gue Jacquet", "78400", "Chatou", "France", "hanssama@G2.com"),
        new Player("Mihael", "Mehle", "Mikyx", "Tavcarjeva 55", "2214", "Sladki Vrh", "Slovenia", "mikyx@G2.com"),
        // Fnatic
        new Player("Óscar", "Muñoz Jiménez", "Oscarinin", "Estrela 57", "39509", "CANTABRIA", "Spain", "oscarinin@fnatic.com"),
        new Player("Iván", "Martín Díaz", "Razork", "Ventanilla de Beas 21", "27720", "LUGO", "Spain", "razork@fnatic.com"),
        new Player("Marek", "Braázda", "Humanoid", "Absolonova 1148", "675 71", "KRAJ VYSOCINA", "Czechia", "humanoid@fnatic.com"),
        new Player("Oh", "Hyeon-taek", "Noah", "Hyeondaeapateu 12", "NONE", "SEOUL", "South Korea", "noah@fnatic.com"),
        new Player("Yoon", "Se-jun", "Jun", "Dongseo 1", "NONE", "DAEGU", "South Korea", "jun@fnatic.com"),
        // MAD Lions
        new Player("Javier", "Prades", "Elyoya", "Constitucion 20", "37130", "VILLAMAYOR", "Spain", "elyoya@madlions.com"),
        new Player("Yasin", "Dincer", "Nisqy", "Ramselsesteenweg 381", "9570", "East Flanders", "Belgium", "nisqy@madlions.com"),
        new Player("Zdravets", "Iliev Galabov", "Hylissang", "Pod Floriánem 1190", "393 01", "Pelhrimov", "Bulgaria", "hylissang@madlions.com"),
        new Player("Matyás", "Orság", "Carzzy", "Masarykova 55", "789 01", "ZÁBREH", "Czechia", "carzzy@madlions.com"),
        new Player("Kim", "Dong-hyeon", "Chasy", "Duhojugong 3", "NONE", "GYEONGSANGBUK-DO", "South Korea", "chasy@madlions.com"),
        // Cloud9
        new Player("Ibrahim", "Allami", "Fudge", "91 English Street", "5252", "HAY VALLEY", "Australia", "fudge@cloud9.com"),
        new Player("Robert", "Huang", "Blaber", "3217 Farland Street", "50046", "IOWA", "USA", "blaber@cloud9.com"),
        new Player("Joseph", "Pyun", "Jojopyun", "2917 Pine Street", "T0C 2L2", "ALBERTA", "Canada", "jojopyun@cloud9.com"),
        new Player("Kim", "Min-cheol", "Berserker", "Janghyeonri 15", "NONE", "GYEONGGI-DO", "South Korea", "berserker@cloud9.com"),
        new Player("Philippe", "Laflamme", "Vulcan", "778 Tycos Dr", "M5T 1T4", "ONTARIO", "Canada", "vulcan@cloud9.com"),
        // LOUD
        new Player("Leonardo", "Souza", "Robo", "Rua Doze 1319", "34170-051", "MINAS GERAIS", "Brazil", "robo@LOUD.com"),
        new Player("Park", "Jong-hoon", "Croc", "Sinjeong 254", "NONE", "SEOUL", "South Korea", "croc@LOUD.com"),
        new Player("Thiago", "Sartori", "tinowns", "Rua C 36 1509", "74265-260", "GOIÁS", "Brazil", "tinowns@LOUD.com"),
        new Player("Moon", "Geom-su", "Route", "Duam 868", "NONE", "GWANGJU", "South Korea", "route@LOUD.com"),
        new Player("Denilson", "Oliveira Goncalves", "Ceos", "Avenida Europa 1341", "11533-060", "SÃO PAULO", "Brazil", "ceos@LOUD.com"),
        // Team Liquid
        new Player("Jeong", "Eon-young", "Impact", "Garwol-dong 164", "NONE", "SEOUL", "South Korea", "impact@teamliquid.org"),
        new Player("Eom", "Seong-hyeon", "UmTi", "Seonamri 17", "753 55", "JEOLLANAM-DO", "South Korea", "umti@teamliquid.org"),
        new Player("Eain", "Stearns", "APA", "2502 Bicetown Road", "10013", "NEW YORK", "USA", "apa@teamliquid.org"),
        new Player("Sean", "Sung", "Yeon", "4521 Whaley Lane", "53188", "WISCONSIN", "USA", "yeon@teamliquid.org"),
        new Player("Jo", "Yong-in", "Corejj", "Jongam-dong 4", "NONE", "SEOUL", "South Korea", "corejj@teamliquid.org"),
        // Team BDS
        new Player("Adam", "Maanane", "Adam", "54 avenue du Marechal Juin", "97436", "GUYANE", "France", "Adam@bds.com"),
        new Player("Théo", "Borile", "Sheo", "34 rue des Soeurs", "78170", "ÎLE-DE-FRANCE", "France", "sheo@bds.com"),
        new Player("Ilias", "Bizriken", "nuc", "11 rue Saint Germain", "92230", "ÎLE-DE-FRANCE", "France", "nuc@bds.com"),
        new Player("Jus", "Marusic", "Crownshot", "Kolodvorska 56", "6001", "KOPER", "Slovenia", "crownshot@bds.com"),
        new Player("Labros", "Papoutsakis", "Labrov", "Pomerio 7", "51000", "RIJEKA", "Greece", "labrov@bds.com")));


    for (Game game : gamesToAdd) {
      if (gameController.save(game)) {
        System.out.println(game.getName() + " added");
      }
    }
    // Add teams to games
    for (Team team : teamsToAdd) {
      if (teamController.save(team)) {
        System.out.println(team.getName() + " added");

        // Check if the team belongs to specific games
        if (team.getName().equals("NaVi") || team.getName().equals("ENCE") || team.getName().equals("Cloud9")
            || team.getName().equals("FaZe") || team.getName().equals("Heroic") || team.getName().equals("Complexity")
            || team.getName().equals("Vitality") || team.getName().equals("MOUZ")) {
          Game cs2Game = gameController.getGameByName("Counter-Strike 2");
          cs2Game.addTeam(team);
          gameController.updateGame(cs2Game);
          System.out.println(team.getName() + " added to " + cs2Game.getName());
        } else if (team.getName().equals("AFC Ajax Brazil") || team.getName().equals("Team Gullit")
            || team.getName().equals("DUX America") || team.getName().equals("RBLZ Gaming")
            || team.getName().equals("Team FUTWIZ") || team.getName().equals("Atlanta United FC")
            || team.getName().equals("TG NIP") || team.getName().equals("Team Exeed")) {
          Game eaGame = gameController.getGameByName("EA Sports FC 24");
          eaGame.addTeam(team);
          gameController.updateGame(eaGame);
          System.out.println(team.getName() + " added to " + eaGame.getName());
        } else if (team.getName().equals("NRG eSports") || team.getName().equals("G2 eSports")
            || team.getName().equals("Fnatic") || team.getName().equals("MAD Lions")
            || team.getName().equals("Cloud9LOL") || team.getName().equals("LOUD")
            || team.getName().equals("Team Liquid") || team.getName().equals("Team BDS")) {
          Game lolGame = gameController.getGameByName("League of Legends");
          lolGame.addTeam(team);
          gameController.updateGame(lolGame);
          System.out.println(team.getName() + " added to " + lolGame.getName());

        }
      }
    }
    // Add players to teams (continued)
    for (Player player : playersToAdd) {
      if (playerController.save(player)) {
        System.out.println(player.getNickName() + " added");

        // Check if the player belongs to specific teams
        if (player.getNickName().equals("b1t") || player.getNickName().equals("jL")
            || player.getNickName().equals("Aleksib") || player.getNickName().equals("iM")
            || player.getNickName().equals("w0nderful")) {
          Team naviTeam = teamController.getTeamByName("NaVi");
          naviTeam.addPlayer(player);
          teamController.updateTeam(naviTeam);
          System.out.println(player.getNickName() + " added to " + naviTeam.getName());
        } else if (player.getNickName().equals("gla1ve") || player.getNickName().equals("dycha")
            || player.getNickName().equals("maden") || player.getNickName().equals("SunPayus")
            || player.getNickName().equals("NertZ")) {
          Team enceTeam = teamController.getTeamByName("ENCE");
          enceTeam.addPlayer(player);
          teamController.updateTeam(enceTeam);
          System.out.println(player.getNickName() + " added to " + enceTeam.getName());
        } else if (player.getNickName().equals("HObbit") || player.getNickName().equals("Perfecto")
            || player.getNickName().equals("Boombl4") || player.getNickName().equals("electroNic")
            || player.getNickName().equals("sh1ro")) {
          Team cloud9Team = teamController.getTeamByName("Cloud9");
          cloud9Team.addPlayer(player);
          teamController.updateTeam(cloud9Team);
          System.out.println(player.getNickName() + " added to " + cloud9Team.getName());
        } else if (player.getNickName().equals("Broky") || player.getNickName().equals("ropz")
            || player.getNickName().equals("KARRIGAN") || player.getNickName().equals("OLOFMEISTER")
            || player.getNickName().equals("RAIN")) {
          Team fazeTeam = teamController.getTeamByName("FaZe");
          fazeTeam.addPlayer(player);
          teamController.updateTeam(fazeTeam);
          System.out.println(player.getNickName() + " added to " + fazeTeam.getName());
        } else if (player.getNickName().equals("TeSeS") || player.getNickName().equals("sjuush")
            || player.getNickName().equals("dupreeh") || player.getNickName().equals("Zyphon")
            || player.getNickName().equals("cadiaN")) {
          Team heroicTeam = teamController.getTeamByName("Heroic");
          heroicTeam.addPlayer(player);
          teamController.updateTeam(heroicTeam);
          System.out.println(player.getNickName() + " added to " + heroicTeam.getName());
        } else if (player.getNickName().equals("EliGE") || player.getNickName().equals("hallzerk")
            || player.getNickName().equals("Grim") || player.getNickName().equals("floppy")
            || player.getNickName().equals("JT")) {
          Team complexityTeam = teamController.getTeamByName("Complexity");
          complexityTeam.addPlayer(player);
          teamController.updateTeam(complexityTeam);
          System.out.println(player.getNickName() + " added to " + complexityTeam.getName());
        } else if (player.getNickName().equals("mezii") || player.getNickName().equals("flameZ")
            || player.getNickName().equals("Spinx") || player.getNickName().equals("ZywOo")
            || player.getNickName().equals("apEX")) {
          Team vitalityTeam = teamController.getTeamByName("Vitality");
          vitalityTeam.addPlayer(player);
          teamController.updateTeam(vitalityTeam);
          System.out.println(player.getNickName() + " added to " + vitalityTeam.getName());
        } else if (player.getNickName().equals("torzsi") || player.getNickName().equals("Jimpphat")
            || player.getNickName().equals("siuhy") || player.getNickName().equals("xertioN")
            || player.getNickName().equals("sycrone")) {
          Team mouzTeam = teamController.getTeamByName("MOUZ");
          mouzTeam.addPlayer(player);
          teamController.updateTeam(mouzTeam);
          System.out.println(player.getNickName() + " added to " + mouzTeam.getName());
        } else if (player.getNickName().equals("Dhokla") || player.getNickName().equals("Contractz")
            || player.getNickName().equals("Palafox") || player.getNickName().equals("IgNar")
            || player.getNickName().equals("FBI")) {
          Team nrgTeam = teamController.getTeamByName("NRG eSports");
          nrgTeam.addPlayer(player);
          teamController.updateTeam(nrgTeam);
          System.out.println(player.getNickName() + " added to " + nrgTeam.getName());
        } else if (player.getNickName().equals("BrokenBlade") || player.getNickName().equals("Yike")
            || player.getNickName().equals("Caps") || player.getNickName().equals("Hans Sama")
            || player.getNickName().equals("Mikyx")) {
          Team g2Team = teamController.getTeamByName("G2 eSports");
          g2Team.addPlayer(player);
          teamController.updateTeam(g2Team);
          System.out.println(player.getNickName() + " added to " + g2Team.getName());
        } else if (player.getNickName().equals("Oscarinin") || player.getNickName().equals("Razork")
            || player.getNickName().equals("Humanoid") || player.getNickName().equals("Noah")
            || player.getNickName().equals("Jun")) {
          Team fnaticTeam = teamController.getTeamByName("Fnatic");
          fnaticTeam.addPlayer(player);
          teamController.updateTeam(fnaticTeam);
          System.out.println(player.getNickName() + " added to " + fnaticTeam.getName());
        } else if (player.getNickName().equals("Elyoya") || player.getNickName().equals("Nisqy")
            || player.getNickName().equals("Hylissang") || player.getNickName().equals("Carzzy")
            || player.getNickName().equals("Chasy")) {
          Team madTeam = teamController.getTeamByName("MAD Lions");
          madTeam.addPlayer(player);
          teamController.updateTeam(madTeam);
          System.out.println(player.getNickName() + " added to " + madTeam.getName());
        } else if (player.getNickName().equals("Fudge") || player.getNickName().equals("Blaber")
            || player.getNickName().equals("Jojopyun") || player.getNickName().equals("Berserker")
            || player.getNickName().equals("Vulcan")) {
          Team c9Team = teamController.getTeamByName("Cloud9LOL");
          c9Team.addPlayer(player);
          teamController.updateTeam(c9Team);
          System.out.println(player.getNickName() + " added to " + c9Team.getName());
        } else if (player.getNickName().equals("Robo") || player.getNickName().equals("Croc")
            || player.getNickName().equals("tinowns") || player.getNickName().equals("Route")
            || player.getNickName().equals("Ceos")) {
          Team loudTeam = teamController.getTeamByName("LOUD");
          loudTeam.addPlayer(player);
          teamController.updateTeam(loudTeam);
          System.out.println(player.getNickName() + " added to " + loudTeam.getName());
        } else if (player.getNickName().equals("Impact") || player.getNickName().equals("UmTi")
            || player.getNickName().equals("APA") || player.getNickName().equals("Yeon")
            || player.getNickName().equals("Corejj")) {
          Team liquidTeam = teamController.getTeamByName("Team Liquid");
          liquidTeam.addPlayer(player);
          teamController.updateTeam(liquidTeam);
          System.out.println(player.getNickName() + " added to " + liquidTeam.getName());
        } else if (player.getNickName().equals("Adam") || player.getNickName().equals("Sheo")
            || player.getNickName().equals("nuc") || player.getNickName().equals("Crownshot")
            || player.getNickName().equals("Labrov")) {
          Team bdsTeam = teamController.getTeamByName("Team BDS");
          bdsTeam.addPlayer(player);
          teamController.updateTeam(bdsTeam);
          System.out.println(player.getNickName() + " added to " + bdsTeam.getName());
        } else if (player.getNickName().equals("PHzin")) {
          Team ajaxTeam = teamController.getTeamByName("AFC Ajax Brazil");
          ajaxTeam.addPlayer(player);
          teamController.updateTeam(ajaxTeam);
          System.out.println(player.getNickName() + " added to " + ajaxTeam.getName());
        } else if (player.getNickName().equals("Manu")) {
          Team gullitTeam = teamController.getTeamByName("Team Gullit");
          gullitTeam.addPlayer(player);
          teamController.updateTeam(gullitTeam);
          System.out.println(player.getNickName() + " added to " + gullitTeam.getName());
        } else if (player.getNickName().equals("Neat")) {
          Team duxTeam = teamController.getTeamByName("DUX America");
          duxTeam.addPlayer(player);
          teamController.updateTeam(duxTeam);
          System.out.println(player.getNickName() + " added to " + duxTeam.getName());
        } else if (player.getNickName().equals("Umut")) {
          Team rblzTeam = teamController.getTeamByName("RBLZ Gaming");
          rblzTeam.addPlayer(player);
          teamController.updateTeam(rblzTeam);
          System.out.println(player.getNickName() + " added to " + rblzTeam.getName());
        } else if (player.getNickName().equals("Mark11")) {
          Team futTeam = teamController.getTeamByName("Team FUTWIZ");
          futTeam.addPlayer(player);
          teamController.updateTeam(futTeam);
          System.out.println(player.getNickName() + " added to " + futTeam.getName());
        } else if (player.getNickName().equals("PauloNeto999")) {
          Team atlTeam = teamController.getTeamByName("Atlanta United FC");
          atlTeam.addPlayer(player);
          teamController.updateTeam(atlTeam);
          System.out.println(player.getNickName() + " added to " + atlTeam.getName());
        } else if (player.getNickName().equals("Ollelito")) {
          Team nipTeam = teamController.getTeamByName("TG NIP");
          nipTeam.addPlayer(player);
          teamController.updateTeam(nipTeam);
          System.out.println(player.getNickName() + " added to " + nipTeam.getName());
        } else if (player.getNickName().equals("Obrun2002")) {
          Team exeedTeam = teamController.getTeamByName("Team Exeed");
          exeedTeam.addPlayer(player);
          teamController.updateTeam(exeedTeam);
          System.out.println(player.getNickName() + " added to " + exeedTeam.getName());
        }
      }

    }
    Team cloud9LoLTeam = teamController.getTeamByName("Cloud9LOL");
    if (cloud9LoLTeam != null) {
      cloud9LoLTeam.setName("Cloud9");
      teamController.updateTeam(cloud9LoLTeam);
      System.out.println("Team renamed to Cloud9");
    }
  }

  // misc
  private void space() {
    for (int i = 0; i < 10; i++) {
      System.out.println();
    }
  }
}


