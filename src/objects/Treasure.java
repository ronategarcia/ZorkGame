/*
 *  Rodrigo Onate
 *  Zork Game
 *  Professor William McCarthy
 *  California State University, Fullerton
 *
 *  File that creates treasure for the player to obtain during the game.
 *
 */
package objects;

public class Treasure extends Thing {
    public int value;

    public Treasure(String lName, String lDescription, int lValue){
        super(lName, lDescription);
        this.value = lValue;
    }

    public int getValue(){
        return value;
    }
}
