/*
 *  Rodrigo Onate
 *  Zork Game
 *  Professor William McCarthy
 *  California State University, Fullerton
 *
 *  File for the class ListThings. In this class in-game objects are processed and return to print them
 *  whenever they are called in a room.
 *
 */
package objects;

import java.util.ArrayList;

public class ListThings extends ArrayList<Thing> {
    public String descThings(){
        String s = "";
        if(this.size() == 0){
            s = "Nothing.\n";
        } else {
            for( Thing aT: this){
                s = s + aT.getName() + ": "+ aT.getDescription() + "\n";
            }
        }
        return s;
    }

    public Thing esteObj (String lName){
        Thing lThing = null;
        String tName = "";
        String lNameLowerCase = lName.trim().toLowerCase();

        for(Thing aT: this){
            tName = aT.getName().trim().toLowerCase();
            if(tName.equals(lNameLowerCase)){
                lThing = aT;
            }
        }
        return lThing;
    }
}
