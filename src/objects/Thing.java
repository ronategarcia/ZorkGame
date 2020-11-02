/*
 *  Rodrigo Onate
 *  Zork Game
 *  Professor William McCarthy
 *  California State University, Fullerton
 *
 *  File with the class Thing. Thing is the base class that creates the names and description of the rooms
 *  in the game.
 *
 */
package objects;

public class Thing {
    private String name;
    private String description;

    public Thing(String lName, String lDescription){
        this.name = lName;
        this.description = lDescription;
    }

    public String getName(){
        return name;
    }

    public void setName(String lName){
        this.name = lName;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String lDescription){
        this.description = lDescription;
    }
}
