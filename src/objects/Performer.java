/*
 *  Rodrigo Onate
 *  Zork Game
 *  Professor William McCarthy
 *  California State University, Fullerton
 *
 *  File for the class Performer extension of Holder. This class tells the player where he is right now and
 *  getting the room and setting it to where the player moved.
 *
 */
package objects;

public class Performer extends Holder {
    private Rooms room; // This is the location where the player is right now

    public Performer(String lName, String lDescription,ListThings lObjects, Rooms lRoom){
        super(lName, lDescription,lObjects);
        this.room = lRoom;
    }
    public Rooms getRoom(){
        return this.room;
    }
    public void setRoom(Rooms lRoom){
        this.room = lRoom;
    }

}

