package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.*;

import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple menu, gets the job done
 */
public class Menu {
  private GameController gameController;
  private TeamController teamController;
  private PlayerController playerController;
  private StaffController staffController;
  private MatchController matchController;
  Map<String, String> countryFlags = new HashMap<>();
  Map<String, String> teamToGameMap = new HashMap<>();
  Map<String, String> playerToTeamMap = new HashMap<>();

  public Menu(GameController gameController) {
    this.gameController = gameController;
    teamController = new TeamController();
    playerController = new PlayerController();
    staffController = new StaffController();
    matchController = new MatchController();

    populateHashMaps();
  }

  private void populateHashMaps() {
    countryFlags.put("Australia", "🇦🇺");
    countryFlags.put("Belgium", "🇧🇪");
    countryFlags.put("Brazil", "🇧🇷");
    countryFlags.put("Bulgaria", "🇧🇬");
    countryFlags.put("Canada", "🇨🇦");
    countryFlags.put("Czechia", "🇨🇿");
    countryFlags.put("Denmark", "🇩🇰");
    countryFlags.put("England", "🏴󠁧󠁢󠁥󠁮󠁧󠁿");
    countryFlags.put("Finland", "🇫🇮");
    countryFlags.put("France", "🇫🇷");
    countryFlags.put("Germany", "🇩🇪");
    countryFlags.put("Greece", "🇬🇷");
    countryFlags.put("Hungary", "🇭🇺");
    countryFlags.put("India", "🇮🇳");
    countryFlags.put("Israel", "🇮🇱");
    countryFlags.put("Italy", "🇮🇹");
    countryFlags.put("Kazakhstan", "🇰🇿");
    countryFlags.put("Latvia", "🇱🇻");
    countryFlags.put("Lithuania", "🇱🇹");
    countryFlags.put("Montenegro", "🇲🇪");
    countryFlags.put("Netherlands", "🇳🇱");
    countryFlags.put("Norway", "🇳🇴");
    countryFlags.put("Poland", "🇵🇱");
    countryFlags.put("Romania", "🇷🇴");
    countryFlags.put("Russia", "🇷🇺");
    countryFlags.put("Slovenia", "🇸🇮");
    countryFlags.put("South Africa", "🇿🇦");
    countryFlags.put("South Korea", "🇰🇷");
    countryFlags.put("Spain", "🇪🇸");
    countryFlags.put("Sweden", "🇸🇪");
    countryFlags.put("Ukraine", "🇺🇦");
    countryFlags.put("USA", "🇺🇸");

    teamToGameMap.put("NaVi", "Counter-Strike 2");
    teamToGameMap.put("ENCE", "Counter-Strike 2");
    teamToGameMap.put("Cloud9", "Counter-Strike 2");
    teamToGameMap.put("FaZe", "Counter-Strike 2");
    teamToGameMap.put("Heroic", "Counter-Strike 2");
    teamToGameMap.put("Complexity", "Counter-Strike 2");
    teamToGameMap.put("Vitality", "Counter-Strike 2");
    teamToGameMap.put("MOUZ", "Counter-Strike 2");
    teamToGameMap.put("AFC Ajax Brazil", "EA Sports FC 24");
    teamToGameMap.put("Team Gullit", "EA Sports FC 24");
    teamToGameMap.put("DUX America", "EA Sports FC 24");
    teamToGameMap.put("RBLZ Gaming", "EA Sports FC 24");
    teamToGameMap.put("Team FUTWIZ", "EA Sports FC 24");
    teamToGameMap.put("Atlanta United FC", "EA Sports FC 24");
    teamToGameMap.put("TG NIP", "EA Sports FC 24");
    teamToGameMap.put("Team Exeed", "EA Sports FC 24");
    teamToGameMap.put("NRG eSports", "League of Legends");
    teamToGameMap.put("G2 eSports", "League of Legends");
    teamToGameMap.put("Fnatic", "League of Legends");
    teamToGameMap.put("MAD Lions", "League of Legends");
    teamToGameMap.put("Cloud9LOL", "League of Legends");
    teamToGameMap.put("LOUD", "League of Legends");
    teamToGameMap.put("Team Liquid", "League of Legends");
    teamToGameMap.put("Team BDS", "League of Legends");

    playerToTeamMap.put("b1t", "NaVi");
    playerToTeamMap.put("jL", "NaVi");
    playerToTeamMap.put("Aleksib", "ENCE");
    playerToTeamMap.put("iM", "ENCE");
    playerToTeamMap.put("w0nderful", "Cloud9");
    playerToTeamMap.put("gla1ve", "Cloud9");
    playerToTeamMap.put("dycha", "Cloud9");
    playerToTeamMap.put("maden", "Cloud9");
    playerToTeamMap.put("SunPayus", "Cloud9");
    playerToTeamMap.put("NertZ", "ENCE");
    playerToTeamMap.put("HObbit", "Cloud9");
    playerToTeamMap.put("Perfecto", "Cloud9");
    playerToTeamMap.put("Boombl4", "Cloud9");
    playerToTeamMap.put("electroNic", "Cloud9");
    playerToTeamMap.put("sh1ro", "Cloud9");
    playerToTeamMap.put("Broky", "FaZe");
    playerToTeamMap.put("ropz", "FaZe");
    playerToTeamMap.put("KARRIGAN", "FaZe");
    playerToTeamMap.put("OLOFMEISTER", "FaZe");
    playerToTeamMap.put("RAIN", "FaZe");
    playerToTeamMap.put("TeSeS", "Heroic");
    playerToTeamMap.put("sjuush", "Heroic");
    playerToTeamMap.put("dupreeh", "Heroic");
    playerToTeamMap.put("Zyphon", "Heroic");
    playerToTeamMap.put("cadiaN", "Heroic");
    playerToTeamMap.put("EliGE", "Complexity");
    playerToTeamMap.put("hallzerk", "Complexity");
    playerToTeamMap.put("Grim", "Complexity");
    playerToTeamMap.put("floppy", "Complexity");
    playerToTeamMap.put("JT", "Complexity");
    playerToTeamMap.put("mezii", "Vitality");
    playerToTeamMap.put("flameZ", "Vitality");
    playerToTeamMap.put("Spinx", "Vitality");
    playerToTeamMap.put("ZywOo", "Vitality");
    playerToTeamMap.put("apEX", "Vitality");
    playerToTeamMap.put("torzsi", "MOUZ");
    playerToTeamMap.put("Jimpphat", "MOUZ");
    playerToTeamMap.put("siuhy", "MOUZ");
    playerToTeamMap.put("xertioN", "MOUZ");
    playerToTeamMap.put("sycrone", "MOUZ");
    playerToTeamMap.put("Dhokla", "NRG eSports");
    playerToTeamMap.put("Contractz", "NRG eSports");
    playerToTeamMap.put("Palafox", "NRG eSports");
    playerToTeamMap.put("IgNar", "NRG eSports");
    playerToTeamMap.put("FBI", "NRG eSports");
    playerToTeamMap.put("BrokenBlade", "G2 eSports");
    playerToTeamMap.put("Yike", "G2 eSports");
    playerToTeamMap.put("Caps", "G2 eSports");
    playerToTeamMap.put("Hans Sama", "G2 eSports");
    playerToTeamMap.put("Mikyx", "G2 eSports");
    playerToTeamMap.put("Oscarinin", "Fnatic");
    playerToTeamMap.put("Razork", "Fnatic");
    playerToTeamMap.put("Humanoid", "Fnatic");
    playerToTeamMap.put("Noah", "Fnatic");
    playerToTeamMap.put("Jun", "Fnatic");
    playerToTeamMap.put("Elyoya", "MAD Lions");
    playerToTeamMap.put("Nisqy", "MAD Lions");
    playerToTeamMap.put("Hylissang", "MAD Lions");
    playerToTeamMap.put("Carzzy", "MAD Lions");
    playerToTeamMap.put("Chasy", "MAD Lions");
    playerToTeamMap.put("Fudge", "Cloud9LOL");
    playerToTeamMap.put("Blaber", "Cloud9LOL");
    playerToTeamMap.put("Jojopyun", "Cloud9LOL");
    playerToTeamMap.put("Berserker", "Cloud9LOL");
    playerToTeamMap.put("Vulcan", "Cloud9LOL");
    playerToTeamMap.put("Robo", "LOUD");
    playerToTeamMap.put("Croc", "LOUD");
    playerToTeamMap.put("tinowns", "LOUD");
    playerToTeamMap.put("Route", "LOUD");
    playerToTeamMap.put("Ceos", "LOUD");
    playerToTeamMap.put("Impact", "Team Liquid");
    playerToTeamMap.put("UmTi", "Team Liquid");
    playerToTeamMap.put("APA", "Team Liquid");
    playerToTeamMap.put("Yeon", "Team Liquid");
    playerToTeamMap.put("Corejj", "Team Liquid");
    playerToTeamMap.put("Adam", "Team BDS");
    playerToTeamMap.put("Sheo", "Team BDS");
    playerToTeamMap.put("nuc", "Team BDS");
    playerToTeamMap.put("Crownshot", "Team BDS");
    playerToTeamMap.put("Labrov", "Team BDS");
    playerToTeamMap.put("PHzin", "AFC Ajax Brazil");
    playerToTeamMap.put("Manu", "Team Gullit");
    playerToTeamMap.put("Neat", "DUX America");
    playerToTeamMap.put("Umut", "RBLZ Gaming");
    playerToTeamMap.put("Mark11", "Team FUTWIZ");
    playerToTeamMap.put("PauloNeto999", "Atlanta United FC");
    playerToTeamMap.put("Ollelito", "TG NIP");
    playerToTeamMap.put("Obrun2002", "Team Exeed");
  }

  // ----------------------------------------

  // Main menu
  public void showMainMenu() {
    System.out.println("Main menu");
    System.out.println("1. STAFF (login)");
    System.out.println("2. Visitor");
    System.out.println("9. Exit program");
    createStaff();
    handleMainMenu();
  }

  public void handleMainMenu() {
    Scanner mainScanner = new Scanner(System.in);
    System.out.print("Input choice 💬: ");
    String userChoice = mainScanner.nextLine();
    space();
    switch (userChoice) {
      case "1":
        staffController.getAllStaff();
        System.out.print("Input id to log in as staff 💬: ");
        Optional<Staff> fetchedStaff = Optional.ofNullable(staffController.getStaffById(new Scanner(System.in).nextInt()));
        String flagIcon = "";
        if (fetchedStaff.isPresent()) {
          if (fetchedStaff.get().getCountry() != null) {
            String country = fetchedStaff.get().getCountry();
            flagIcon = countryFlags.getOrDefault(country, "");
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
            String country = fetchedPlayer.get().getCountry();
            flagIcon = countryFlags.getOrDefault(country, "");
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
        staffController.getAllStaff();
        showCrudMenu();
        break;
      case "2":
        // List staff by id
        staffController.getAllStaff();
        System.out.print("Input id of staff to fetch 💬: ");
        Optional<Staff> fetchedStaff = Optional.ofNullable(staffController.getStaffById(new Scanner(System.in).nextInt()));
        if (fetchedStaff.isPresent()) {
          String flagIcon = "";
          String country = fetchedStaff.get().getCountry();
          flagIcon = countryFlags.getOrDefault(country, "");

          System.out.println("✅ Staff " + fetchedStaff.get().getNickname() + " fetched successfully");

          System.out.println(""); // radbrytning

          System.out.println("🪪 ID: " + fetchedStaff.get().getStaffId());
          System.out.println("ℹ️ Name: " + fetchedStaff.get().getFirstName() + " '" + fetchedStaff.get().getNickname() + "' " + fetchedStaff.get().getLastName());
          System.out.println("📍 Adress: " + fetchedStaff.get().getAddress() + ", " + fetchedStaff.get().getStaffId() + " " + fetchedStaff.get().getPostalAddress());
          System.out.println(flagIcon + " Country: " + fetchedStaff.get().getCountry());
          System.out.println("✉️ E-mail: " + fetchedStaff.get().getEmail());

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
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        int staffIdToUpdate = new Scanner(System.in).nextInt();
        Staff staffToUpdate = staffController.getStaffById(staffIdToUpdate);

        System.out.println("Change player details:");

        System.out.print("First name from " + staffToUpdate.getFirstName() + " to 💬: ");
        staffToUpdate.setFirstName(new Scanner(System.in).nextLine());

        System.out.print("Last name from " + staffToUpdate.getLastName() + " to 💬: ");
        staffToUpdate.setLastName(new Scanner(System.in).nextLine());

        System.out.print("Nickname from " + staffToUpdate.getNickname() + " to 💬: ");
        staffToUpdate.setNickname(new Scanner(System.in).nextLine());

        System.out.print("Address from " + staffToUpdate.getAddress() + " to 💬: ");
        staffToUpdate.setAddress(new Scanner(System.in).nextLine());

        System.out.print("Zip code from " + staffToUpdate.getZipCode() + " to 💬: ");
        staffToUpdate.setZipCode(new Scanner(System.in).nextLine());

        System.out.print("Postal address from " + staffToUpdate.getPostalAddress() + " to 💬: ");
        staffToUpdate.setPostalAddress(new Scanner(System.in).nextLine());

        System.out.print("Country from " + staffToUpdate.getCountry() + " to 💬: ");
        staffToUpdate.setCountry(new Scanner(System.in).nextLine());

        System.out.print("Email from " + staffToUpdate.getEmail() + " to 💬: ");
        staffToUpdate.setEmail(new Scanner(System.in).nextLine());

        if (staffController.update(staffToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "1":
        // update firstname
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        Staff firstNameToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change first name from " + firstNameToUpdate.getFirstName() + " to 💬: ");
        firstNameToUpdate.setFirstName(new Scanner(System.in).nextLine());
        if (staffController.update(firstNameToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "2":
        // update last name
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        Staff lastNameToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change last name from " + lastNameToUpdate.getLastName() + " to 💬: ");
        lastNameToUpdate.setLastName(new Scanner(System.in).nextLine());
        if (staffController.update(lastNameToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "3":
        // update nickname
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        Staff nickNameToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change nickname from " + nickNameToUpdate.getNickname() + " to 💬: ");
        nickNameToUpdate.setNickname(new Scanner(System.in).nextLine());
        if (staffController.update(nickNameToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "4":
        // update address
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        Staff addressToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change address from " + addressToUpdate.getAddress() + " to 💬: ");
        addressToUpdate.setAddress(new Scanner(System.in).nextLine());
        if (staffController.update(addressToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "5":
        // update zip code
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        Staff zipCodeToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change zip code from " + zipCodeToUpdate.getZipCode() + " to 💬: ");
        zipCodeToUpdate.setZipCode(new Scanner(System.in).nextLine());
        if (staffController.update(zipCodeToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "6":
        // update postal address
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        Staff postalAddrToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change postal address from " + postalAddrToUpdate.getPostalAddress() + " to 💬: ");
        postalAddrToUpdate.setPostalAddress(new Scanner(System.in).nextLine());
        if (staffController.update(postalAddrToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "7":
        // update country
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        Staff countryToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change country from " + countryToUpdate.getCountry() + " to 💬: ");
        countryToUpdate.setCountry(new Scanner(System.in).nextLine());
        if (staffController.update(countryToUpdate)) {
          System.out.println("✅ Staff updated");
        } else {
          System.out.println("❌ Staff update failed");
        }
        showCrudMenu();
        break;
      case "8":
        // update email
        staffController.getAllStaff();
        System.out.print("Input id 💬: ");
        Staff eMailToUpdate = staffController.getStaffById(new Scanner(System.in).nextInt());
        System.out.print("Change email from " + eMailToUpdate.getEmail() + " to 💬: ");
        eMailToUpdate.setEmail(new Scanner(System.in).nextLine());
        if (staffController.update(eMailToUpdate)) {
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
        staffController.getAllStaff();
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
    if (staffController.getAllStaff().isEmpty()) {
      // Creating staff
      staffController.save(new Staff("Richard", "Hendricks", "R_Hendricks", "5230 Newell Rd", "NONE", "Palo Alto", "USA", "r_hendricks@piedpiper.com"));
      staffController.save(new Staff("Bertram", "Gilfoyle", "B_Gilfoyle", "5230 Newell Rd", "NONE", "Palo Alto", "USA", "b_gilfoyle@piedpiper.com"));
      staffController.save(new Staff("Dinesh", "Chugtai", "D_Chugtai", "5230 Newell Rd", "NONE", "Palo Alto", "India", "d_chugtai@piedpiper.com"));
    }
  }

  public void createPlayersAndTeamsDataForStaff() {
    if (gameController.getAll(false).isEmpty()) {
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
      // Add teams to games in parallel
      teamsToAdd.parallelStream().forEach(team -> {
        if (teamController.save(team)) {
          String teamName = team.getName();
          String gameName = teamToGameMap.get(teamName);
          if (gameName != null) {
            Game game = gameController.getGameByName(gameName);
            game.addTeam(team);
            gameController.updateGame(game);
          }
        }
      });
      System.out.println(teamsToAdd.size() + " teams added");

      // Add players to teams in parallel
      playersToAdd.parallelStream().forEach(player -> {
        if (playerController.save(player)) {
          String playerName = player.getNickName();
          String teamName = playerToTeamMap.get(playerName);
          if (teamName != null) {
            Team team = teamController.getTeamByName(teamName);
            team.addPlayer(player);
            teamController.updateTeam(team);
          }
        }
      });
      System.out.println(playersToAdd.size() + " players added");

      // Rename Cloud9LOL team
      Team cloud9LoLTeam = teamController.getTeamByName("Cloud9LOL");
      if (cloud9LoLTeam != null) {
        cloud9LoLTeam.setName("Cloud9");
        teamController.updateTeam(cloud9LoLTeam);
        System.out.println("Team renamed to Cloud9");
      }
    }
  }

  // misc
  private void space() {
    for (int i = 0; i < 10; i++) {
      System.out.println();
    }
  }
}

