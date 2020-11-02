/*
 *  Rodrigo Onate
 *  Zork Game
 *  Professor William McCarthy
 *  California State University, Fullerton
 *
 *  File that takes everything from the other files, and then it passes to UserInput for the game to run
 *
 */

package gameMap;

import globalDirections.Directions;
import objects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class GameMap {
    private ArrayList<Rooms>map; // ArrayList for the rooms
    private Performer player;

    List<String> commands = new ArrayList<>(Arrays.asList("take","drop","look","inventory",
            "use","n","s","w","e"));
    List<String> objects = new ArrayList<>(Arrays.asList(
            "candy","knife","revolver"));

    public GameMap(){
        this.map = new ArrayList<Rooms>(); // List of rooms for the map
        // Objects that you get when the game starts.
        ListThings frontDoorList = new ListThings();
        frontDoorList.add(new Treasure("Candy", "A delicious candy that can heal you!",1));
        frontDoorList.add(new Treasure("knife", "A knife that will help you kill a specific kind of monster!",2));
        //Object that you get after defeating the monster in the Entrance room.
        ListThings entranceList = new ListThings();
        entranceList.add(new Treasure("Revolver","A gun that will help you kill a specific kind of monster!", 3));
        ListThings playerList = new ListThings();
                      // (Name of the room,     description of the room,   North Coordinate, South Coordinate, West Coordinate, East Coordinate)
        map.add(new Rooms("Front Door","the door is half way open!",1, Directions.NOEXIT,
                Directions.NOEXIT,2, frontDoorList));
        map.add(new Rooms("Entrance","You are inside the house, it smells like a dead body!",5,0,
                9,3, entranceList));
        /*map.add(new Rooms("Front Garage Door", "You are in front of the garage, you can't open it from outside",
                Directions.NOEXIT, Directions.NOEXIT, 0, Directions.NOEXIT));
        map.add(new Rooms("Dining Room","The light in this room is flashing at a weird rhythm!",4,
                Directions.NOEXIT,1,11));*/


        player = new Performer("Player", "an amazing player has come", playerList, map.get(0));
    }
    public void showIntro(){
        System.out.println("This is a HAUNTED HOUSE!\nif you got the guts, go in and try to find the treasure " +
                "that will get you out!\nJust be aware that you may never leave..\n(Use n(north), s(south)," +
                "w(west), e(east), to move around the place. If you find any items use the keywords take, drop, use, to make something happen\n" +
                "Good Luck!\nYou are at the front door. The door is half way open. What would you like to do?\n");
    }
    ArrayList getMap(){
        return map;
    }
    void setMap(ArrayList lMap){
        map = lMap;
    }
    public Performer getPlayer(){ // Player
        return player;
    }
    public void setPlayer(Performer lPlayer){
        player = lPlayer;
    }
    private void transObj(Thing t, ListThings fromList, ListThings toList){
        fromList.remove(t);
        toList.add(t);
    }

    public String takeOb(String obName){
        String rStr = "";
        Thing aT = player.getRoom().getThings().esteObj(obName);
        if(obName.equals("")){
            obName = "Object not real";
        }
        if(aT == null){
            rStr = "There is no " + obName + "with that name here";
        }else{
            transObj(aT,player.getRoom().getThings(),player.getThings());
            rStr = obName + " taken";
        }
        return rStr;
    }

    public String dropOb(String obName){
        String rStr = "";
        Thing aT = player.getThings().esteObj(obName);
        if(obName.equals("")){
            rStr = "Please type the object you want to drop";
        }else if(aT == null){
            rStr = "You don't have this object in your possession";
        }else{
            transObj(aT,player.getThings(),player.getRoom().getThings());
            rStr = obName + " dropped!";
        }
        return rStr;
    }
    void movePerformerTo(Performer p, Rooms lRoom){ // move the player to a room
        p.setRoom(lRoom);
    }
    int moveTo(Performer lPerformer, Directions dir){  // returns an int that represents the
        Rooms r = lPerformer.getRoom();                // room number or NOEXIT if it can't go there
        int exit;

        switch(dir){
            case NORTH:
                exit = r.getN();
                break;
            case SOUTH:
                exit = r.getS();
                break;
            case WEST:
                exit = r.getW();
                break;
            case EAST:
                exit = r.getE();
                break;
            default:
                exit = Directions.NOEXIT;
                break;
        }
        if(exit != Directions.NOEXIT){
            movePerformerTo(lPerformer, map.get(exit));
        }
        return exit;
    }
    public int movePlayerTo(Directions dir){
        return moveTo(player, dir);
    }
    private void goN(){
        updateOp(movePlayerTo(Directions.NORTH));
    }
    private void goS(){
        updateOp(movePlayerTo(Directions.SOUTH));
    }
    private void goW(){
        updateOp(movePlayerTo(Directions.WEST));
    }
    private void goE(){
        updateOp(movePlayerTo(Directions.EAST));
    }
    private void look(){
        showStr("You are in the "+ getPlayer().getRoom().describe());
    }
    private void showStr(String s){
        System.out.println(s);
    }
    private void showRoomDescription(int roomNumb){
        String aS;
        if(roomNumb == Directions.NOEXIT){
            aS = "You can't go on that direction!";
        }else{
            Rooms r = getPlayer().getRoom();
            aS = "You are in the " + r.describe();
        }
        showStr(aS);
    }
    private void showInventory(){
        showStr("You have " + getPlayer().getThings().descThings());
    }
    void updateOp(int roomNumber){
        String location;

        if(roomNumber == Directions.NOEXIT){
            location = "You can't go on that direction!";
        } else {
            Rooms room = getPlayer().getRoom();
            location = "You are in the " + room.getName() + ". " + room.getDescription();
        }
        System.out.println(location);
    }

    public String AnalyseVerb(List<String> wList){
        String Verb;
        String msg = "";

        Verb = wList.get(0);
        if(!commands.contains(Verb)){
            msg = Verb + " is not a known verb!";
        } else {
            switch (Verb){
                case "n":
                    goN();
                    break;
                case "s":
                    goS();
                    break;
                case "w":
                    goW();
                    break;
                case "e":
                    goE();
                    break;
                case "look":
                    look();
                    break;
                case "inventory":
                    showInventory();
                    break;
                default:
                    msg = Verb + "(verb isn't implemented yet)";
                    break;
            }
        }
        return msg;
    }
    public String AnalyseVerbNoun(List<String> wList){
        String Verb;
        String Noun;
        String msg = "";
        boolean error = false;
        Verb = wList.get(0);
        Noun = wList.get(1);

        if(!commands.contains(Verb)){
            msg = Verb + " is not a known verb!";
            error = true;
        }
        if(!objects.contains(Noun)){
            msg += (Noun + " is not a known noun!");
            error = true;
        }
        if(!error){
            switch(Verb){
                case "take":
                    msg = takeOb(Noun);
                    break;
                case "drop":
                    msg = dropOb(Noun);
                    break;
                default:
                    msg += "(verb isn't implemented yet)";
                    break;
            }
        }
        return msg;
    }

    public String ParseCommand(List<String> wList){
        String msg;

        if(wList.size() == 1){
            msg = AnalyseVerb(wList);
        } else if (wList.size() == 2){
            msg = AnalyseVerbNoun(wList);
        } else {
            msg = "Only 2 word commands are allowed!";
        }
        return msg;
    }

    public List<String> wordList(String input){
        String splits = " \t,.:;?!\"'";
        List<String> strList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, splits);
        String t;

        while(tokenizer.hasMoreTokens()){
            t = tokenizer.nextToken();
            strList.add(t);
        }
        return strList;
    }
    public String RunCommand (String inputStr){
        List<String> wList;
        String result = "What would you like to do?";
        String lString = inputStr.trim().toLowerCase();
        if(lString.equals("")){
            result = "Please enter a valid command";
        } else {
            wList = wordList(lString);
            wList.forEach((lstr) -> System.out.println(lstr));
            ParseCommand(wList);
        }
        return result;
    }
}