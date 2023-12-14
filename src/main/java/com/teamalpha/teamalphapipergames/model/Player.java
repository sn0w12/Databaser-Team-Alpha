package com.teamalpha.teamalphapipergames.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    // We declare where the primary key is
    @Id
    // The id will be generetad by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int id;
    // @Column(length = 8) is equivalent to VARCHAR(8) in database terms
    @Column(name = "first_name", length = 20)
    private String firstName;
    @Column(name = "last_name", length = 40)
    private String lastName;
    @Column(name = "nick_name", length = 20)
    private String nickName;
    @Column(name = "address", length = 30)
    private String address;
    @Column(name = "zip_code", length = 10)
    private String zipCode;
    @Column(name = "postal_adr", length = 20)
    private String postalAddress;
    @Column(name = "country", length = 20)
    private String country;
    @Column(name = "email", length = 30)
    private String eMail;
    // @ManyToOne - many players like this one can be owned by a single team
//    @ManyToOne
//    @JoinColumn(name = "team_id")  // This is the owning side of the relation
//    private Team team;

    // Matches
//    @ManyToOne
//    @JoinColumn(name = "match_id")
//    private Match match;

//    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "player1")
//    private List<Match> matches=new ArrayList<>();
//
//    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "match")
//    private List<Match> matches=new ArrayList<>();



    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List <Match> matches=new ArrayList<>();




    // Empty constructor
    public Player() {
    }

    // for registering with only first name, last name and nickname
    public Player(String firstName, String lastName, String nickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    // without id and team
    public Player(String firstName, String lastName, String nickName, String address, String zipCode, String postalAddress, String country, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.address = address;
        this.zipCode = zipCode;
        this.postalAddress = postalAddress;
        this.country = country;
        this.eMail = eMail;
    }

    // full no id
//    public Player(String firstName, String lastName, String nickName, String address, String zipCode, String postalAddress, String country, String eMail,) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.nickName = nickName;
//        this.address = address;
//        this.zipCode = zipCode;
//        this.postalAddress = postalAddress;
//        this.country = country;
//        this.eMail = eMail;
//      //  this.team = team;
//    }

    // full
    public Player(int id, String firstName, String lastName, String nickName, String address, String zipCode, String postalAddress, String country, String eMail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.address = address;
        this.zipCode = zipCode;
        this.postalAddress = postalAddress;
        this.country = country;
        this.eMail = eMail;
      //  this.team = team;
    }


    public void addMatch(Match match){
       matches.add(match);
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAdress) {
        this.postalAddress = postalAdress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }



//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List <Match> match) {

        this.matches = match;
    }}




