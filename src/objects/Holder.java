/*
 *  Rodrigo Onate
 *  Zork Game
 *  Professor William McCarthy
 *  California State University, Fullerton
 *  Extension of the class Thing. List of objects that you have
 *
 */
package objects;

public class Holder extends Thing {
    private ListThings objects = new ListThings();

    public Holder (String lName, String lDescription, ListThings lObjects){
        super(lName, lDescription);
        objects = lObjects;
    }

    public ListThings getThings(){
        return objects;
    }

    public void setThings(ListThings objects){
        this.objects = objects;
    }
}
