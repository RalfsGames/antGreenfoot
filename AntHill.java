import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A hill full of ants.
 *
 * @author Michael Kölling
 * @version 1.1
 */
public class AntHill extends Actor
{
    public static final int DEFAULTNUMBER_ANTS = 40;
    public static final int DEFAULT_FOODVALUE = 20;

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

    /**
     * = true if not created;
     * = false if initization is completed; => foodcounter is created & default number of ants is spawned;
     */
    private boolean notCreated = true;

    /**
     * Constructor for ant hill with default number of ants (40).
     */
    public AntHill() {
        this(AntHill.DEFAULTNUMBER_ANTS, AntHill.DEFAULT_FOODVALUE);
    }

    /**
     * Constructor with default value of food = 20;
     *
     * @param numberOfAnts
     */
    public AntHill(int numberOfAnts) {
        this(numberOfAnts, AntHill.DEFAULT_FOODVALUE);
    }

    /**
     * Construct an ant hill with a given number of ants.
     */
    public AntHill(int numberOfAnts, int food) {
        // Erzeuge alle Ameisen
        ants = numberOfAnts;
        this.food = food;
    }

    /**
     * Initialize all ANTS && FOODCOUNTER
     */
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

        if (Queen.PRICE <= foodCounter.getValue()) {
            if (Greenfoot.getRandomNumber(50) < 10) {
                getWorld().addObject(new Ant(this), getX(), getY());
                foodCounter.decrement(Queen.PRICE);
            }
        }

        if (Ant.PRICE <= foodCounter.getValue()) {
            if (Greenfoot.getRandomNumber(1000) < 10 + (this.foodCounter.getValue() * 0.25)) {
                getWorld().addObject(new Ant(this), getX(), getY());
                ants++;
                foodCounter.decrement(Ant.PRICE);
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

    protected void newAnt() {
        antWorld.AntsLivingCounter.increment();
    }
}