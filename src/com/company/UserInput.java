/*
*  Rodrigo Onate
*  Zork Game
*  Professor William McCarthy
*  California State University, Fullerton
*
*  File that takes UserInput, saves the game, and loads the game.
* */


package com.company;

import gameMap.GameMap;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UserInput {
    static GameMap game;

    private static void saveGame(){
        try{
            FileOutputStream fos = new FileOutputStream("Zork.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            System.out.print("Your game has been saved\n");
        } catch (Exception e){
            System.out.print("Can't save data. Error!\n");
        }
    }

    private static void loadGame(){
        try{
            FileInputStream fis = new FileInputStream("Zork.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (GameMap) ois.readObject();
            ois.close();
            System.out.print("Your game has been loaded\n");
        } catch (Exception e){
            System.out.print("Can't load data. Error!\n");
            System.out.print(e.getClass() + ": " + e.getMessage() + "\n");
        }
    }
    public static void main(String[] args) throws IOException { // Main keeps the program running
        // until the user presses q to QUIT
        BufferedReader in;
        String input;
        String output = "";
        game = new GameMap();
        in = new BufferedReader(new InputStreamReader(System.in));
        game.showIntro();

        do{
            System.out.print("> ");
            input = in.readLine();
            switch(input){
                case "save":
                    saveGame();
                    break;
                case "load":
                    loadGame();
                    break;
                default:
                    output = game.RunCommand(input);
                    break;
            }
            System.out.println(output);
        } while (!"q".equals(input));
    }

}