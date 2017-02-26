import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The world where ants live.
 *
 * @author Michael Kölling
 * @version 1.1
 */
public class AntWorld extends World
{
    public static final int SIZE = 2000;

    protected Counter AntsLivingCounter;

    protected Counter AntsDeadCounter;

    private Counter TimeCounter;

    private Counter Date;

    /**
     * Create a new world. It will be initialised with a few ant hills
     * and food sources
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, Pheromone.class, AntHill.class, Food.class);
        setup4();
    }

    /**
     * Create world contents: one ant hill and food.
     */
    private void setup1()
    {
        removeObjects(getObjects(null));  // remove all existing objects
        addObject(new AntHill(70), SIZE / 2, SIZE / 2);
        addObject(new Food(), SIZE / 2, SIZE / 2 - 260);
        addObject(new Food(), SIZE / 2 + 215, SIZE / 2 - 100);
        addObject(new Food(), SIZE / 2 + 215, SIZE / 2 + 100);
        addObject(new Food(), SIZE / 2, SIZE / 2 + 260);
        addObject(new Food(), SIZE / 2 - 215, SIZE / 2 + 100);
        addObject(new Food(), SIZE / 2 - 215, SIZE / 2 - 100);
    }

    /**
     * Create world contents: two ant hills and food.
     */
    private void setup2()
    {
        removeObjects(getObjects(null));  // remove all existing objects
        addObject(new AntHill(40), 506, 356);
        addObject(new AntHill(40), 95, 267);

        addObject(new Food(), 80, 71);
        addObject(new Food(), 291, 56);
        addObject(new Food(), 516, 212);
        addObject(new Food(), 311, 269);
        addObject(new Food(), 318, 299);
        addObject(new Food(), 315, 331);
        addObject(new Food(), 141, 425);
        addObject(new Food(), 378, 547);
        addObject(new Food(), 566, 529);
    }

    /**
     * Create world contents: two ant hills and food.
     */
    private void setup3()
    {
        removeObjects(getObjects(null));  // remove all existing objects
        addObject(new AntHill(40), 576, 134);
        addObject(new AntHill(40), 59, 512);

        addObject(new Food(), 182, 84);
        addObject(new Food(), 39, 308);
        addObject(new Food(), 249, 251);
        addObject(new Food(), 270, 272);
        addObject(new Food(), 291, 253);
        addObject(new Food(), 339, 342);
        addObject(new Food(), 593, 340);
        addObject(new Food(), 487, 565);
    }

    private void setup4() {
        removeObjects(getObjects(null));

        addObject(new AntHill(40), (SIZE / 3), (SIZE / 3));

        for (int i = 0; i < (Greenfoot.getRandomNumber(SIZE / 20) + SIZE / 100); i++) {
            addObject(new Food(), Greenfoot.getRandomNumber(SIZE), Greenfoot.getRandomNumber(SIZE));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:s");
        Date = new Counter("Date: " + sdf.format(new Date()));
        addObject(Date, SIZE - 105, 30);

        TimeCounter = new CounterTime("Time:");
        addObject(TimeCounter, SIZE - 40, 50);

        AntsLivingCounter = new Counter("Living Ants: ");
        addObject(AntsLivingCounter, SIZE - 140, 70);

        AntsDeadCounter = new Counter("Dead Ants: ");
        addObject(AntsDeadCounter, SIZE - 150, 90);
    }

    public void act(){
        spawnFood();
        this.TimeCounter.increment();
    }

    private void spawnFood(){

        //TODO EVALUATE following expression
        // Greenfoot.getRandomNumber((int) Math.pow(SIZE, 2)) < (int)(SIZE / 100)
        if ((Greenfoot.getRandomNumber(1000) < 20)) {
            addObject(new Food(), Greenfoot.getRandomNumber(SIZE) + 1 , Greenfoot.getRandomNumber(SIZE) + 1);
        }
    }

}
