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

    /**
     * collected Food Value
     */
    private int food;

    /** Counter to show how much food have been collected so far. */
    private Counter foodCounter;

    /**
     * set WORLD for Interaction
     */
    private AntWorld antWorld;

    private boolean notCreated = true;

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

    public void create() {
        if (notCreated) {
            if (foodCounter == null) {
                foodCounter = new Counter("Food: ");
                int x = getX();
                int y = getY() + getImage().getWidth() / 2 + 8;

                foodCounter.setValue(this.food);

                getWorld().addObject(foodCounter, x, y);
            }

            antWorld = (AntWorld) getWorld();

            for (int i = 0; i < ants; i++) {
                getWorld().addObject(new Ant(this), getX(), getY());
                antWorld.AntsLivingCounter.increment();
            }
            notCreated = false;
        }
    }

    /**
     * Act:
     */
    public void act() {
        create();
        spawnAnt();
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
                antWorld.AntsLivingCounter.increment();
                ants++;
                foodCounter.decrement(Ant.ANT_PRICE);
            }
        }
    }

    /**
     * Record that we have collected another bit of food.
     */
    protected void countFood()
    {
        foodCounter.increment();
    }

    /**
     * Tell the Hill that one of her ants died;
     */
    protected void antIsDieing() {
        antWorld.AntsLivingCounter.decrement();
        antWorld.AntsDeadCounter.increment();
        ants--;
    }
}