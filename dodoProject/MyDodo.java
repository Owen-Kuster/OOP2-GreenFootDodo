import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;

    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
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
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }

    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
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
            System.out.println ("moved " + nrStepsTaken); //prints "moved" plus steps taken
        }
    }

    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates(){
        while(!borderAhead() && canMove()){
            move();
            //System.out.println("Your coordinates are: " + "(X" + getX() + ",Y" + getY() + ")");
        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if( onEgg() ){
            return false;
        }else{
            return true;
        }
    }  

    /**
     * Test if dodo can turn 180 degrees
     *          (Left and right)
     * 
     * <p> Initial: Dodo is facing EAST
     * <p> Initial: Dodo is facing WEST
     * 
     * <p> If facing either directions. Make it turn 180 degrees
     */

    public void turn180() {
        if(getDirection() == EAST){
            setDirection(WEST);
        } else {
            setDirection(EAST);
        }
    }

    /**
     * Test climbOverFence
     *     Dodo has to climb over a fence
     *         Has to look towards the EAST after it.
     * 
     * <p> Initial: Dodo is facing a fence
     * 
     * <p> If facing fence. Climb over it
     */

    public void climbOverFence() {
        if(fenceAhead() && getDirection() == EAST){
            setDirection(NORTH);
            move();
            setDirection(EAST);
            move();
            move();
            setDirection(SOUTH);
            move();
            setDirection(EAST);
        } else if (fenceAhead() && getDirection() == WEST){
            setDirection(NORTH);
            move();
            setDirection(WEST);
            move();
            move();
            setDirection(SOUTH);
            move();
            setDirection(WEST);
        }
    }

    /**
     * Test grainAhead
     *     Dodo has to check if there is a grain in front of him
     *         Has to look in front
     *         Then jumps back
     *             Looks to the right
     *             
     * <p> Initial: No requirements
     * <p> If noticed a grain. Print it.
     * <p> If not noticed a grain. Print it.
     */

    public boolean grainAhead(){
        move();
        if(onGrain() == true && getDirection() == EAST){
            System.out.println("There is a grain.");
            stepOneCellBackwards();
            return true;
        }else{
            System.out.println("There is not a grain here.");
            stepOneCellBackwards();
            return false;
        }
    }

    /**
     * Test goToEgg
     *     Egg lays x cells ahead of dodo
     *     The cells in between the dodo and egg are empty
     *     
     *     <p> Has to face the right direction
     *     <p> There are no obstacbles in between the dodo and the egg
     *     <p> If onEgg is found the egg
     *     <p> Use onEgg method
     *     
     *     Final situation: 
     *         <p> Dodo is standing on the egg
     *         <p> Dodo is facing the original direction.
     */

    public void goToEgg(){
        while(!onEgg() && canMove()){
            move();
            if (!canMove()){
                System.out.println("You have hit an obstacle");
            }
        }
    }

    /**
     * Test goBackToStartOfRowAndFaceBack
     * 
     * <p> Use other methods you have already written
     * <p> Dodo has to face to the original state
     * <p> Start of row.
     * 
     * <p> Stop at obstacles?
     * 
     *     Final situation:
     *         <p> Dodo is at the start of the row
     *         <p> Dodo is facing the original way
     */

    public void goBackToStartOfRowAndFaceBack(){
        setDirection(WEST);
        walkToWorldEdgePrintingCoordinates();
        turn180();
    }

    /**
     * Test walkToWorldEdgeClimbOverFences
     * 
     * <p> Dodo is walking towards the edge of the world
     * <p> Dodo climbs over fences that are in the way
     * 
     *     Final Situation:
     *         <p> Dodo walkToWorldEdge
     *         <p> Dodo climbOverFence
     */

    public void walkToWorldEdgeClimbOverFences(){
        while(!borderAhead()){
            walkToWorldEdgePrintingCoordinates();
            climbOverFence();
        }
    }

    /**
     * Test pickUpGrainsAndPrintCoordinates
     * 
     * <p> Dodo walks towards edge of the world
     * <p> Dodo picks up grains on the way
     * <p> System prints out the grain coordinates (getX, getY)
     * 
     *     Final situation:
     *         <p> Dodo walkToWorldEdge
     *         <p> Dodo picks up grains
     *         <p> System prints out grain coordinates
     */

    public void pickUpGrainsAndPrintCoordinates(){
        while(!borderAhead()){
            move();
            if(onGrain() == true){
                pickUpGrain();
                System.out.println("Grain on: " + "(" + getX() + "," + getY() + ")");
            }
        }
    }

    /**
     * Test stepOneCellBackwards
     * 
     * <p> Dodo has to turn 180
     * <p> Dodo has to move 1 cell
     * <p> Dodo has to turn 180 (facing original direction)
     *     
     *     Final situation:
     *         <p> Dodo has turned 180 and walked 1 cell backwards.
     *         <p> Faces the original direction again
     */

    public void stepOneCellBackwards(){
        turn180();
        move();
        turn180();
    }

    /**
     * Walks to edge of the world Laying an egg in an empty nest.
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     * <p>          Dodo lays eggs in empty nests on the way.
     */

    public void walkToWorldEdgeFillNests(){
        while(!borderAhead() && canMove()){
            move();
            if (onNest() && !onEgg()){
                layEgg();
            }
        }
    }

    /**
     * Test walkToWorldEdgeClimbOverFencesLayEggsInNests
     * 
     * <p> Dodo is walking towards the edge of the world
     * <p> Dodo climbs over fences that are in the way
     * <p> Dodo lays egg in empty nests
     * 
     *     Final situation:
     *         <p> Dodo walkToWorldEdge
     *         <p> Dodo climbOverFence
     *         <p> Dodo lays eggs in empty nests
     */

    public void walkToWorldEdgeClimbOverFencesLayEggsInNests(){
        while(!borderAhead()){
            walkToWorldEdgePrintingCoordinates();
            climbOverFence();
            if (onNest() && !onEgg()){
                layEgg();
            }
        }
    }

    /**
     * Test checkForFences
     * 
     * <p> Testing which direction has a fence
     */

    public boolean checkForFences(){
        turnRight();
        if(fenceAhead()){
            turnLeft();
            return true;
        } else {
            turnLeft();
            return false;
        }
    }

    /**
     * Test walkAroundFencedArea
     * 
     * <p> Dodo walks around fenced area
     * <p> Dodo stops at egg (onEgg())
     * <p> Dodo has to stop on egg
     * 
     *     Final situation:
     *         <p> Dodo walks around fenced area without bugs
     *         <p> Dodo stops on egg.
     */

    public void walkAroundFencedArea(){
        while(!onEgg()){
            if(fenceAhead()){
                turnLeft();
            }
            if(checkForFences()){
                move();
            } else{
                turnRight();
                move();
            }
        }
    }

    /**
     * Test checkForEggs
     */

    public boolean checkForEggs(){
        turnRight();
        if(eggAhead()){
            turnLeft();
            return true;
        } else {
            turnLeft();
            return false;
        }
    }

    /**
     * Test checkForNest
     */

    public boolean checkForNests(){
        turnRight();
        if(nestAhead()){
            turnLeft();
            return true;
        } else {
            turnLeft();
            return false;
        }
    }

    /**
     * Test eggTrailToNest
     * 
     * <p> Dodo is walking on a trail
     * <p> Dodo picks up every egg
     * <p> Stops on the nest
     *     
     *     Final situation:
     *         <p> Dodo walks and collects the eggs on the trail
     *         <p> Dodo stops on nest.
     */

    public void eggTrailToNest() {
        while (!onNest()) {
            if (eggAhead() || nestAhead()){
                move();
            } else if (checkForEggs() || checkForNests()){
                turnRight();
                move();
            } else{
                turnLeft();
                move();
            }

            if (onEgg()){
                pickUpEgg();
            }
        }
    }

    /**
     * Test walkThroughMazeToNest
     * 
     * <p> Dodo is in a maze and needs to escape.
     * <p> It needs to end on the nest at the end of the maze
     * 
     *  Final situation:
     *      <p> Dodo walks through the maze
     *      <p> Dodo stops on nest.
     */

    public void walkThroughMaze(){
        while(!onNest()){
            turnLeft();
            if(canMove()){
                move();
            } else {
                turnRight();
                if(canMove()){
                    move();
                } else {
                    turnRight();
                    if(canMove()){
                        move();
                    } else {
                        turnRight();
                        move();
                    }
                }
            }
            if(onNest()){
                System.out.println("You have found the nest!");
            }
        }
    }

    /**
     * Test faceEast
     * 
     * <p> dodo is not facing EAST
     * <p> Dodo needs to turn to EAST
     * <p> Use While Loop.
     * 
     *      Final Situation:
     *          <p> Dodo is facing east
     */

    public void faceEast(){
        while(getDirection() != EAST){
            setDirection(EAST);
        }
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
            } else if (getX() > coordX){
                setDirection(WEST);
                move();
            } else if (getY() < coordY){
                setDirection(SOUTH);
                move();
            } else if (getY() > coordY){
                setDirection(NORTH);
                move();
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
     * Test countEgssInRow
     * 
     * <p> Initial: Dodo is somewhere in the world facing East.
     * 
     *      Final situation:
     *          <p> Dodo is back at the start position facing East.
     * 
     * <p> return number of eggs in the row
     */
    public int countEggsInRow(){
        int eggCount = 0;

        if (onEgg()){
            eggCount++;
        }
        while (canMove()){
            move();
            if (onEgg()){
                eggCount++;
            }
        }
        goBackToStartOfRowAndFaceBack();

        showCompliment("Er zijn " + eggCount + " eieren in deze rij!");
        return eggCount;
    }

    /**
     * Test layTrailOfEggs 
     * 
     * <p> asks for distance
     * <p> lays eggs in current direction till cant move
     * 
     *      Final situation:
     *          <p> Lays trail of eggs in current direction
     *          
     */
    public void layTrailOfEggs(int n){
        int eggsLayed = 0;
        while ( eggsLayed < n && canMove()){
            move();
            layEgg();
            eggsLayed++;
        }
        System.out.println ("Layed " + eggsLayed +" eggs.");
    }

    /**
     * Test countEggsInWorld
     * 
     * <p> walks to the end of the row
     * <p> counts the eggs
     * <p> goes to an other row
     * <p> repeats till end of the world
     * <p> shows how many eggs
     * 
     *      Final situation:
     *          <p> goes through every row
     *          <p> counts every egg
     *          <p> gives the amount of eggs counted
     *          
     */
    public void countEggsInWorld(){
        int eggCount = 0;
        int y, x;
        y = getY();
        x = getX();
        while(getY() <= getWorld().getHeight() - 1){ //loopt door alle rijen
            if (onEgg()) {
                eggCount++;
            }
            while (canMove()) { //beweegt naar einde van de rij
                move();
                if (onEgg()) {
                    eggCount++;
                }
            }
            if (getY() < getWorld().getHeight() - 1){
                goBackToStartOfRowAndFaceBack();
                setDirection(SOUTH);
                move();
                setDirection(EAST);
            } else {
                break; //stopt anders blijft hij doorgaan wanneer het niet kan
            }
        }
        showCompliment("Totaal: " + eggCount + " eieren!");
        goToLocation(x, y); //gaat terug naar y,x van start
        setDirection(EAST);
    }

    /** 
     * Test findRowWithMostEggs
     * 
     * <p> Dodo goes through every row
     * <p> Dodo counts eggs
     * <p> System prints out the row with most eggs
     * <p> Dodo goes back to start position
     * 
     *      Final situation:
     *          <p> Dodo goes through every row of the world, counts the eggs
     *          <p> System prints out the row with most eggs
     *          <p> Dodo goes to the start position
     */

    public void findRowWithMostEggs(){
        int x = getX();
        int y = getY();
        int maxEggs = 0;
        int bestRow = 0;
        goToLocation(0, 0); //start pos left top corner
        setDirection(EAST);
        for (int row = 0; row < getWorld().getHeight(); row++){ //for every row thats lower than height
            int eggsInRow = countEggsInRow();
            if (eggsInRow > maxEggs) {
                maxEggs = eggsInRow;
                bestRow = row;
            }
            if (row < getWorld().getHeight() - 1){
                setDirection(SOUTH);
                move();
                setDirection(EAST);
            }
        }
        System.out.println("Rij met de meeste eieren: " + bestRow + " (" + maxEggs + " eieren)");
        goToLocation(x, y); //back to start position
        setDirection(EAST);
    }

    /**
     * Test buildMonumentOfEggs
     * 
     * <p> Dodo lays 1 more egg(s) every row
     * <p> Dodo lays the eggs from the position it is in
     * 
     *      Final Situation:
     *          <p> Dodo lays a monument of eggs in every row + 1
     *          <p> Dodo starts from starting position
     */
    public void buildMonumentOfEggs(){
        int startX = getX() -1;
        int startY = getY();
        int eggsToLay = 1;
        for (int row = startY; row < getWorld().getHeight(); row++){ //for every row and row is smaller than world height
            goToLocation(startX, row);
            setDirection(EAST);
            layTrailOfEggs(eggsToLay); //fills in the parameter automatically
            eggsToLay++; //adds 1 egg to lay
            goBackToStartOfRowAndFaceBack();
            if (row < getWorld().getHeight() - 1){
                setDirection(SOUTH);
                move();
                setDirection(EAST);
            }
        }
        goToLocation(startX +1, startY); //goes back to start
        setDirection(EAST);
    }

    /**
     * Test heavyMonumentOfEggs
     * 
     * <p> Dodo lays eggs twice as much eggs as the last row
     * <p> Dodo lays eggs from the position it is in
     * 
     *      Final situation:
     *          <p> Dodo lays a heavy monument
     *          <p> Dodo lays twice as many eggs as the last row
     */
    public void heavyMonumentOfEggs(){
        int startX = getX() -1;
        int startY = getY();
        int eggsToLay = 1;
        for (int row = startY; row < getWorld().getHeight(); row++){ //for every row and row is smaller than world height
            goToLocation(startX, row);
            setDirection(EAST);
            if (canLayEgg()){
                layEgg();
                layTrailOfEggs(eggsToLay - 1); //already layed egg so -1 //parameter already filled
                eggsToLay *= 2; //doubles the amount of eggs for the next row
                goBackToStartOfRowAndFaceBack();
                if (row < getWorld().getHeight() - 1){
                    setDirection(SOUTH);
                    move();
                    setDirection(EAST);
                }
            }
            goToLocation(startX, startY);
            setDirection(EAST);
        }
    }

    /**
     * Test buildPyramidOfEggs
     * 
     * <p> Dodo builds a pyramid of eggs
     * <p> Dodo does this from the place he is at (startPos)
     * 
     *      Final sitation:
     *          <p> Dodo lays eggs from the place he is at
     *          <p> Eggs form a pyramid
     */
    public void buildPyramidOfEggs(){
        int startX = getX();
        int startY = getY();
        int eggsToLay = 1;
        for (int row = startY; row < getWorld().getHeight(); row++){ //for every row and row is smaller than world height
            int offset = row - startY; //how many to move left
            goToLocation(startX - offset, row);
            setDirection(EAST);
            if (canLayEgg()){
                layEgg();
                layTrailOfEggs(eggsToLay - 1); //already layed egg so -1 //parameter already filled
                eggsToLay += 2; //adds 2 eggs
                goBackToStartOfRowAndFaceBack();
                if (row < getWorld().getHeight() - 1){
                    setDirection(SOUTH);
                    move();
                    setDirection(EAST);
                }
            }
            goToLocation(startX, startY);
            setDirection(EAST);
        }
    }
}
