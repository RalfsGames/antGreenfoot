import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A hill full of ants.
 *
 * @author Michael Kölling
 * @version 1.1
 */
public class AntHill extends Actor
{
    /** Number of ants that have come out so far. */
    private int ants = 0;

    /** */
    private int food;

    /** Counter to show how much food have been collected so far. */
    private Counter foodCounter;

    /**
     * Constructor for ant hill with default number of ants (40).
     */
    public AntHill() {
        this(40, 20);
    }

    /**
     * Constructor with default value of food = 20;
     *
     * @param numberOfAnts
     */
    public AntHill(int numberOfAnts) {
        this(numberOfAnts, 20);
    }

    /**
     * Construct an ant hill with a given number of ants.
     */
    public AntHill(int numberOfAnts, int food) {
        // Erzeuge alle Ameisen
        ants = numberOfAnts;
        this.food = food;
    }

    public void create(){

        for (int i = 0; i <= ants; i++) {
            getWorld().addObject(new Ant(this), getX(), getY());
        }
    }

    /**
     * Act:
     */
    public void act()
    {
        handleCounter();
        spawnAnt();
    }

    private void handleCounter() {
        if(foodCounter == null)
        {
            foodCounter = new Counter("Food: ");
            int x = getX();
            int y = getY() + getImage().getWidth()/2 + 8;

            foodCounter.setValue(this.food);

            getWorld().addObject(foodCounter, x, y);
        }
    }

    /**
     * possibly spawn an ant
     */
    private void spawnAnt() {
        if(Ant.ANT_PRICE <= foodCounter.getValue())
        {
            if(Greenfoot.getRandomNumber(1000) < 10)
            {
                getWorld().addObject(new Ant(this), getX(), getY());
                ants++;
                foodCounter.decrement(Ant.ANT_PRICE);
            }
        }
    }

    /**
     * Record that we have collected another bit of food.
     */
    public void countFood()
    {
        foodCounter.increment();
    }

    public void antIsDieing(){
        ants--;
    }
}