import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.1 -- 29-07-2017
 */
public class MyDodo extends Dodo
{
    List<SurpriseEgg> listOfEggs;

    public MyDodo() {
        super( EAST );
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, 
     * i.e. there are no obstructions or end of world in the cell in front of her.
     * 
     * <p> Initial:   Dodo is somewhere in the world
     * <p> Final:     Same as initial situation
     * 
     * @return  boolean true if Dodo can move (thus, no obstructions ahead)
     *                  false if Dodo can't move
     *                      there is an obstruction or end of world ahead
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
        }
    }

    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    public List<Integer> createListOfNumbers() {
        return new ArrayList<> (Arrays.asList( 2, 43, 7, -5, 12, 7 ));
    }

    /**
     * Method for praciticing with lists.
     */
    public void practiceWithLists( ){
        List<Integer> listOfNumbers = createListOfNumbers();

        //the following is incorrect and is to be fixed in challenge 6.1c
        System.out.println("First element: " + listOfNumbers.get(1) ); 
    }

    public void practiceWithListsOfSurpriseEggs( ){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
    }

    /**
     *  Test makeListOfSurpriseEggs
     *  
     *  <p> Call a list of 10 surprise eggs from the method: generateListOfSurpriseEggs
     *  <p> Method is in surpriseEgg
     *  <p> You can call it with getWorld()
     *  
     */

    public void makeListOfSurpriseEggs(){
        listOfEggs = SurpriseEgg.generateListOfSurpriseEggs( 10, getWorld() );
    }

    /**
     * Test printCoordinatesOfEgg(Egg egg)
     * 
     * <p> Print coordinates of an egg
     * 
     */

    public void printCoordinatesOfEgg(Egg egg){
        int eggX = egg.getX();
        int eggY = egg.getY();
        System.out.println("( X = " + eggX + " , Y = " + eggY + ")");
    }

    /**
     * Test makeListOfSurpriseEggsAndPrintCoordinates
     * 
     * <p> Use a foreach to print every coordinate from a surprise egg
     * 
     */

    public void makeListOfSurpriseEggsAndPrintCoordinates(){
        makeListOfSurpriseEggs();
        for(Egg egg:listOfEggs){
            printCoordinatesOfEgg(egg);
        }
    }

    /**
     * Test getMostValuableEgg
     * 
     * <p> Initial: listOfEggs contains SurpriseEgg objects
     * <p> Final: returns the egg with the highest value
     * 
     * <p> return SurpriseEgg with the highest value
     * 
     */
    public SurpriseEgg getMostValuableEgg(){ //SurpriseEgg from the list
        SurpriseEgg mostValuable = listOfEggs.get(0); //start with first egg
        for (SurpriseEgg egg : listOfEggs){ //foreach of a for loop as the list above
            if (egg.getValue() > mostValuable.getValue()){
                mostValuable = egg;
            }
        }
        return mostValuable; //return
    }

    /**
     * Test printCoordinatesAndValueOfEgg
     * 
     * <p> Egg the egg to print info about
     * 
     */
    public void printCoordinatesAndValueOfEgg(Egg egg){
        int eggX = egg.getX();
        int eggY = egg.getY();
        int eggValue = egg.getValue();
        System.out.println("( X = " + eggX + " , Y = " + eggY + " , Value = " + eggValue + " )");
    }

    /**
     * Test testMostValuableEgg
     * 
     */
    public void testMostValuableEgg(){
        makeListOfSurpriseEggs(); //place 10 eggs in the world
        System.out.println("All eggs:");
        for (SurpriseEgg egg : listOfEggs){ //foreach of a for loop
            printCoordinatesAndValueOfEgg(egg); //prints coordinates and value of the eggs
        }
        SurpriseEgg mostValuable = getMostValuableEgg(); //saving the most valuable egg in mostValuable van het type SurpriseEgg
        System.out.println("Most valuable egg:");
        printCoordinatesAndValueOfEgg(mostValuable);
    }

    /**
     * Test printAverageValueOfEggs
     * 
     * <p> Initial: listOfEggs contains SurpriseEgg objects
     * <p> Final: prints the average value of all eggs
     */
    public void printAverageValueOfEggs() {
        makeListOfSurpriseEggs();
        int total = 0;
        for (SurpriseEgg egg : listOfEggs) { //foreach of a for loop
            total = total + egg.getValue(); //total gets + egg value
        }
        double average = (double) total / listOfEggs.size(); //casting to double //divided by list size
        System.out.println("Average Value: " + average); //print average
    }
}
