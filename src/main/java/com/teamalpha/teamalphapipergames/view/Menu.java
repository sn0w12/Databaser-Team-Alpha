package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Simple menu, gets the job done
 */
public class Menu {
    private final GameController gameController;
    private final TeamController teamController;
    private final PlayerController playerController;
    private final StaffController staffController;
    private final MatchController matchController;
    Map<String, String> teamGameMap = new HashMap<>();
    Map<String, String> playerTeamMap = new HashMap<>();

    public Menu(GameController gameController) {
        this.gameController = gameController;
        teamController = new TeamController();
        playerController = new PlayerController();
        staffController = new StaffController();
        matchController = new MatchController();

        initializeMaps();
    }

    // ----------------------------------------
    public void initializeMaps() {
        // Mapping teams to their respective games
        teamGameMap.put("NaVi", "Counter-Strike 2");
        teamGameMap.put("ENCE", "Counter-Strike 2");
        teamGameMap.put("Cloud9", "Counter-Strike 2");
        teamGameMap.put("FaZe", "Counter-Strike 2");
        teamGameMap.put("Heroic", "Counter-Strike 2");
        teamGameMap.put("Complexity", "Counter-Strike 2");
        teamGameMap.put("Vitality", "Counter-Strike 2");
        teamGameMap.put("MOUZ", "Counter-Strike 2");

        teamGameMap.put("AFC Ajax Brazil", "EA Sports FC 24");
        teamGameMap.put("Team Gullit", "EA Sports FC 24");
        teamGameMap.put("DUX America", "EA Sports FC 24");
        teamGameMap.put("RBLZ Gaming", "EA Sports FC 24");
        teamGameMap.put("Team FUTWIZ", "EA Sports FC 24");
        teamGameMap.put("Atlanta United FC", "EA Sports FC 24");
        teamGameMap.put("TG NIP", "EA Sports FC 24");
        teamGameMap.put("Team Exeed", "EA Sports FC 24");

        teamGameMap.put("NRG eSports", "League of Legends");
        teamGameMap.put("G2 eSports", "League of Legends");
        teamGameMap.put("Fnatic", "League of Legends");
        teamGameMap.put("MAD Lions", "League of Legends");
        teamGameMap.put("Cloud9LOL", "League of Legends");
        teamGameMap.put("LOUD", "League of Legends");
        teamGameMap.put("Team Liquid", "League of Legends");
        teamGameMap.put("Team BDS", "League of Legends");

        playerTeamMap.put("b1t", "NaVi");
        playerTeamMap.put("jL", "NaVi");
        playerTeamMap.put("Aleksib", "NaVi");
        playerTeamMap.put("iM", "NaVi");
        playerTeamMap.put("w0nderful", "NaVi");

        playerTeamMap.put("gla1ve", "ENCE");
        playerTeamMap.put("dycha", "ENCE");
        playerTeamMap.put("maden", "ENCE");
        playerTeamMap.put("SunPayus", "ENCE");
        playerTeamMap.put("NertZ", "ENCE");

        playerTeamMap.put("HObbit", "Cloud9");
        playerTeamMap.put("Perfecto", "Cloud9");
        playerTeamMap.put("Boombl4", "Cloud9");
        playerTeamMap.put("electroNic", "Cloud9");
        playerTeamMap.put("sh1ro", "Cloud9");

        playerTeamMap.put("Broky", "FaZe");
        playerTeamMap.put("ropz", "FaZe");
        playerTeamMap.put("KARRIGAN", "FaZe");
        playerTeamMap.put("OLOFMEISTER", "FaZe");
        playerTeamMap.put("RAIN", "FaZe");

        playerTeamMap.put("TeSeS", "Heroic");
        playerTeamMap.put("sjuush", "Heroic");
        playerTeamMap.put("dupreeh", "Heroic");
        playerTeamMap.put("Zyphon", "Heroic");
        playerTeamMap.put("cadiaN", "Heroic");

        playerTeamMap.put("EliGE", "Complexity");
        playerTeamMap.put("hallzerk", "Complexity");
        playerTeamMap.put("Grim", "Complexity");
        playerTeamMap.put("floppy", "Complexity");
        playerTeamMap.put("JT", "Complexity");

        playerTeamMap.put("mezii", "Vitality");
        playerTeamMap.put("flameZ", "Vitality");
        playerTeamMap.put("Spinx", "Vitality");
        playerTeamMap.put("ZywOo", "Vitality");
        playerTeamMap.put("apEX", "Vitality");

        playerTeamMap.put("torzsi", "MOUZ");
        playerTeamMap.put("Jimpphat", "MOUZ");
        playerTeamMap.put("siuhy", "MOUZ");
        playerTeamMap.put("xertioN", "MOUZ");
        playerTeamMap.put("sycrone", "MOUZ");

        playerTeamMap.put("Dhokla", "NRG eSports");
        playerTeamMap.put("Contractz", "NRG eSports");
        playerTeamMap.put("Palafox", "NRG eSports");
        playerTeamMap.put("IgNar", "NRG eSports");
        playerTeamMap.put("FBI", "NRG eSports");

        playerTeamMap.put("BrokenBlade", "G2 eSports");
        playerTeamMap.put("Yike", "G2 eSports");
        playerTeamMap.put("Caps", "G2 eSports");
        playerTeamMap.put("Hans Sama", "G2 eSports");
        playerTeamMap.put("Mikyx", "G2 eSports");

        playerTeamMap.put("Oscarinin", "Fnatic");
        playerTeamMap.put("Razork", "Fnatic");
        playerTeamMap.put("Humanoid", "Fnatic");
        playerTeamMap.put("Noah", "Fnatic");
        playerTeamMap.put("Jun", "Fnatic");

        playerTeamMap.put("Elyoya", "MAD Lions");
        playerTeamMap.put("Nisqy", "MAD Lions");
        playerTeamMap.put("Hylissang", "MAD Lions");
        playerTeamMap.put("Carzzy", "MAD Lions");
        playerTeamMap.put("Chasy", "MAD Lions");

        playerTeamMap.put("Fudge", "Cloud9LOL");
        playerTeamMap.put("Blaber", "Cloud9LOL");
        playerTeamMap.put("Jojopyun", "Cloud9LOL");
        playerTeamMap.put("Berserker", "Cloud9LOL");
        playerTeamMap.put("Vulcan", "Cloud9LOL");

        playerTeamMap.put("Robo", "LOUD");
        playerTeamMap.put("Croc", "LOUD");
        playerTeamMap.put("tinowns", "LOUD");
        playerTeamMap.put("Route", "LOUD");
        playerTeamMap.put("Ceos", "LOUD");

        playerTeamMap.put("Impact", "Team Liquid");
        playerTeamMap.put("UmTi", "Team Liquid");
        playerTeamMap.put("APA", "Team Liquid");
        playerTeamMap.put("Yeon", "Team Liquid");
        playerTeamMap.put("Corejj", "Team Liquid");

        playerTeamMap.put("Adam", "Team BDS");
        playerTeamMap.put("Sheo", "Team BDS");
        playerTeamMap.put("nuc", "Team BDS");
        playerTeamMap.put("Crownshot", "Team BDS");
        playerTeamMap.put("Labrov", "Team BDS");

        playerTeamMap.put("PHzin", "AFC Ajax Brazil");

        playerTeamMap.put("Manu", "Team Gullit");

        playerTeamMap.put("Neat", "DUX America");

        playerTeamMap.put("Umut", "RBLZ Gaming");

        playerTeamMap.put("Mark11", "Team FUTWIZ");

        playerTeamMap.put("PauloNeto999", "Atlanta United FC");

        playerTeamMap.put("Ollelito", "TG NIP");

        playerTeamMap.put("Obrun2002", "Team Exeed");
    }

    // Main menu
    public void showMainMenu() {
        System.out.println("Main menu");
        System.out.println("1. STAFF (login)");
        System.out.println("2. Visitor");
        System.out.println("9. Exit program");
        createStaff();
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
        if (gameController.getAllGames(false).isEmpty()) {
            long startTime = System.currentTimeMillis();

            List<Game> gamesToAdd = new ArrayList<>(List.of(
                    // Games
                    new Game("Counter-Strike 2"),
                    new Game("EA Sports FC 24"),
                    new Game("League of Legends")));

            List<Team> teamsToAdd = new ArrayList<>(List.of(
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


            List<Player> playersToAdd = new ArrayList<>(List.of(
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
                if (gameController.createGame(game.getName())) {
                    System.out.println(game.getName() + " added");
                }
            }

            // Save all team and player objects to the database
            teamController.saveAll(teamsToAdd);
            playerController.saveAll(playersToAdd);

            // Initialize caches for games and teams to optimize database access
            Map<String, Game> cachedGames = new HashMap<>();
            Map<String, Team> cachedTeams = new HashMap<>();

            int teamCounter = 0;
            // Iterate through each team in the list to add
            for (Team team : teamsToAdd) {
                // Retrieve the game name associated with the team
                String gameName = teamGameMap.get(team.getName());
                if (gameName != null) {
                    // Get or create a game from the cache, and add the team to the game
                    Game game = cachedGames.computeIfAbsent(gameName, gameController::getGameByName);
                    game.addTeam(team);
                    teamCounter++;
                }
            }
            // Bulk update the games with their newly added teams
            gameController.updateAll(cachedGames.values());
            System.out.println(teamCounter + " teams added");

            int playerCounter = 0;
            // Iterate through each player in the list to add
            for (Player player : playersToAdd) {
                // Retrieve the team name associated with the player
                String teamName = playerTeamMap.get(player.getNickName());
                if (teamName != null) {
                    // Get or create a team from the cache, and add the player to the team
                    Team team = cachedTeams.computeIfAbsent(teamName, teamController::getTeamByName);
                    team.addPlayer(player);
                    playerCounter++;
                }
            }
            // Bulk update the teams with their newly added players
            teamController.updateAll(cachedTeams.values());
            System.out.println(playerCounter + " players added");

            // Rename a specific team (Cloud9LOL) if it exists
            Team cloud9LoLTeam = teamController.getTeamByName("Cloud9LOL");
            if (cloud9LoLTeam != null) {
                cloud9LoLTeam.setName("Cloud9");
                teamController.updateTeam(cloud9LoLTeam);
                System.out.println("Team renamed to Cloud9");
            }

            // Prepare a mapping of players by the game they are associated with
            Map<Integer, List<Integer>> playersByGame = new HashMap<>();
            for (Player player : playersToAdd) {
                int gameId = player.getTeam().getGame().getGame_id();
                playersByGame.computeIfAbsent(gameId, k -> new ArrayList<>()).add(player.getId());
            }

            // Add players to their respective games in bulk
            for (Map.Entry<Integer, List<Integer>> entry : playersByGame.entrySet()) {
                int gameId = entry.getKey();
                List<Integer> playerIds = entry.getValue();

                gameController.addAllPlayersToGame(playerIds, gameId);
                System.out.println(playerIds.size() + " players added to game ID: " + gameId);
            }

            long endTime = System.currentTimeMillis();
            long elapsedTimeMillis = endTime - startTime;
            System.out.println("Elapsed Time: " + (double)elapsedTimeMillis / 1000 + " seconds");
        }
    }

    //
    public void createMatches() {
        matchController.addNewMatch(1, true, 3, 4, LocalDate.of(2020, 5, 5));
        matchController.addNewMatch(1, true, 3, 4, LocalDate.of(2220, 5, 5));
        matchController.addNewMatch(1, true, 3, 4, LocalDate.of(2020, 5, 5));
        matchController.addNewMatch(1, true, 3, 4, LocalDate.of(2020, 5, 5));
        matchController.addNewMatch(1, false, 3, 4, LocalDate.of(2020, 5, 5));
        matchController.addNewMatch(1, false, 3, 4, LocalDate.of(2220, 5, 5));
        matchController.addNewMatch(1, false, 3, 4, LocalDate.of(2020, 5, 5));
        matchController.addNewMatch(1, false, 3, 4, LocalDate.of(2020, 5, 5));
        matchController.addNewMatch(1, false, 3, 4, LocalDate.of(2020, 5, 5));
        matchController.addNewMatch(1, true, 3, 4, LocalDate.of(2220, 5, 5));
        matchController.addNewMatch(1, true, 3, 4, LocalDate.of(2220, 5, 5));
        matchController.addNewMatch(1, true, 3, 4, LocalDate.of(2020, 5, 5));
        matchController.addNewMatch(1, true, 3, 4, LocalDate.of(2020, 5, 5));
    }

    // misc
    private void space() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}

