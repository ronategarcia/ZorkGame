/*
 *  Rodrigo Onate
 *  Zork Game
 *  Professor William McCarthy
 *  California State University, Fullerton
 *
 *  File extension of the class Holder. It has the class Rooms that moves the player around.
 *
 */
package objects;

public class Rooms extends Holder {
    private int n, s, w ,e;

    public Rooms(String lName, String lDescription, int cN,int cS, int cW, int cE, ListThings lObjects){
        super(lName, lDescription, lObjects);
        this.n = cN;
        this.s = cS;
        this.w = cW;
        this.n = cE;
    }

    // movement methods
    // North
    public int getN(){
        return n;
    }
    public void setN(int cN){
        this.n = cN;
    }
    // South
    public int getS(){
        return s;
    }
    public void setS(int cS){
        this.s = cS;
    }
    // West
    public int getW(){
        return w;
    }
    public void setW(int cW){
        this.w = cW;
    }
    // East
    public int getE(){
        return e;
    }
    public void setE(int cE){
        this.e = cE;
    }
    public String describe(){
        return String.format("%s. %s\n", getName(),getDescription()) +
                "Objects here: " + getThings().descThings();
    }
}
