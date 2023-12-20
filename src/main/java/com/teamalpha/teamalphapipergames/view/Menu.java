package com.teamalpha.teamalphapipergames.view;

import com.teamalpha.teamalphapipergames.controller.*;
import com.teamalpha.teamalphapipergames.model.*;

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
            List<Game> gamesToAdd = new ArrayList<>();
            List<Team> teamsToAdd = new ArrayList<>();
            List<Player> playersToAdd = new ArrayList<>();


            gamesToAdd.addAll(List.of(
                    // Games
                    new Game("Counter-Strike 2"),
                    new Game("EA Sports FC 24"),
                    new Game("League of Legends")));


//            new Team("-");
//            new Team("-");
            teamsToAdd.addAll(List.of(
                    // Teams
                    // Counter-Strike 2
                    new Team("-"),
                    new Team("-"),
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

//            new Player("-", "-", "-");
//            new Player("-", "-", "-");
            playersToAdd.addAll(List.of(
                    // Players
                    // Counter-Strike 2
                    // NaVi
                    new Player("-", "-", "-"),
                    new Player("-", "-", "-"),
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

            // adding player to specific game as well
            for (Player player : playersToAdd) {
                if (player.getId() != 1 && player.getId() != 2) {  //added if, so dont add the "empty" players to the list
                    gameController.addPlayerToGame(player.getId(), player.getTeam().getGame().getGame_id());
                    System.out.println(player.getNickName() + " added to: " + player.getTeam().getGame().getName());
                }
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

