package com.teamalpha.teamalphapipergames;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        BorderPane borderPane= new BorderPane();

        Scene scene = new Scene(borderPane, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
       // launch();

        Connection connection = null;
        Statement statement =null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/piper_games", "malin","malinolsson");
            statement = connection.createStatement();


        boolean hasResultSet = statement.execute("SELECT player.player_name "+
                "FROM players"
                );
        if(hasResultSet){
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
        } else {
            System.out.println("Det var inte ett resultset");
        }

        } catch (SQLException err){
            System.out.println("Ett fel uppstod:\n" + err.getMessage());
        }
      finally {
        try {
            if(connection != null)
                connection.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }}}



