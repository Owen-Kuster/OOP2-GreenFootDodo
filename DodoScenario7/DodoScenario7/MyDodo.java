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
    /* ATTRIBUTE DECLARATIONS: */
    private int myNrOfStepsTaken;

    public MyDodo() {
        super( EAST );
        /* INITIALISATION OF ATTRIBUTES: */
        myNrOfStepsTaken = 0;
    }

    /* METHODS OF THE CLASS: */

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
        int nrStepsTaken = 0;
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                       // take a step
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
        System.out.println("First element: " + listOfNumbers.get(1) ); 
    }

    public void practiceWithListsOfSurpriseEgss( ){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
    }

    /**
     * Test goToLocation
     * 
     * <p> dodo is on a certain x & y place.
     * <p> dodo needs to go to a certain x & y place.
     * <p> dodo needs to check if the location is reached
     * 
     *      Final situation:
     *          <p> dodo is on the certain x & y place.
     */

    public boolean locationReached(int x, int y){
        return getX() == x && getY() == y;
    }

    public void goToLocation(int coordX, int coordY){
        if (!validCoordinates(coordX, coordY)){
            return;
        }
        while (!locationReached(coordX, coordY)){
            if (getX() < coordX){
                setDirection(EAST);
                move();
                updateScores(Mauritius.MAXSTEPS - myNrOfStepsTaken, 0);
                myNrOfStepsTaken ++;
            } else if (getX() > coordX){
                setDirection(WEST);
                move();
                updateScores(Mauritius.MAXSTEPS - myNrOfStepsTaken, 0);
                myNrOfStepsTaken ++;
            } else if (getY() < coordY){
                setDirection(SOUTH);
                move();
                updateScores(Mauritius.MAXSTEPS - myNrOfStepsTaken, 0);
                myNrOfStepsTaken ++;
            } else if (getY() > coordY){
                setDirection(NORTH);
                move();
                updateScores(Mauritius.MAXSTEPS - myNrOfStepsTaken, 0);
                myNrOfStepsTaken ++;
            }
        }
    }

    /**
     * Test validCoordinates
     * 
     * <p> Dodo fills in the coordinates
     * <p> System recognizes that the coordinates do not exist.
     * <p> Systen returns true/false
     * 
     *      Final situation:
     *          <p> System returns true/false
     *          <p> System tells to move or not
     *          
     */

    public boolean validCoordinates(int x, int y){
        if (x < 0 || x >= getWorld().getWidth() || y < 0 || y >= getWorld().getHeight()){
            showError("Invalid coordinates");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Test moveRandomly
     * 
     * <p> Dodo will move randomly
     * <p> Dodo can not move more than 40 steps
     * <p> Dodo can not walk against a fence
     * <p> Dodo can not walk against the border
     * 
     *      Final situation:
     *          <p> Dodo moves 40 steps randomly
     *          <p> Dodo won't walk against a border nor fence
     *          
     */

    public void moveRandomly(){
        while(myNrOfStepsTaken <= Mauritius.MAXSTEPS){
            setDirection(randomDirection());
            System.out.println(randomDirection());
            while(fenceAhead() || borderAhead()){
                setDirection(randomDirection());
            }
            move();
            updateScores(Mauritius.MAXSTEPS - myNrOfStepsTaken, 0);
            myNrOfStepsTaken++;
        }
        if(Mauritius.MAXSTEPS == 40){
            showCompliment("You have done 40 steps!");
        }
    }

    /**
     * Test pickUpNearestEggInList
     * 
     * <p> No initial situation
     * <p> Get list of eggs
     * <p> Determine destination: nearest egg
     * <p> Walk to nearest egg
     * <p> Walk to destination
     * <p> Pick up egg
     * 
     *      Final situation:
     *          <p> Dodo walks to nearest egg and picks it up
     *          
     */

    public void pickUpNearestEggInList(){
        int shortestDistanceEgg = Integer.MAX_VALUE;
        int distanceToEggX = 0;
        int distanceToEggY = 0;
        int distanceToEgg = 0;

        List<Egg> Eggs = (List<Egg>) getWorld().getObjects(Egg.class);
        if (Eggs.isEmpty()){
            showError("No Eggs Found");
            return;
        }
        Egg nearestEgg = null;
        for(Egg egg: Eggs){
            distanceToEgg = Math.abs(getX() - egg.getX()) + Math.abs(getY() - egg.getY());
            if(distanceToEgg < shortestDistanceEgg){
                shortestDistanceEgg = distanceToEgg;
                nearestEgg = egg;
            }
        }
        goToLocation(nearestEgg.getX(), nearestEgg.getY());
        if(onEgg()){
            pickUpEgg();
        }
    }

    /**
     * Test dodoRace
     * 
     */

    public void dodoRace(){
        List<Egg> Eggs = (List<Egg>) getWorld().getObjects(Egg.class);
        while (myNrOfStepsTaken < Mauritius.MAXSTEPS || !Eggs.isEmpty()){
            pickUpNearestEggInList();
        }
    }
}
