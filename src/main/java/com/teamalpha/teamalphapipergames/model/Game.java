package com.teamalpha.teamalphapipergames.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int game_id;

    @Column(name = "game_title")
    private String name;

    // Constructors, getters, setters

    public Game(String name) {
        this.name = name;
    }

    public Game() {

        // Default constructor
    }


    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}



