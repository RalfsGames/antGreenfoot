import java.util.Random;

/**
 * Created by Vincent Modrow on 26/02/2017.
 * PACKAGE_NAME
 */
public class Queen extends Creature {

    public static int PRICE = 10;
    private static int maxANTSinHILL = 120;
    private int LP = 1000;
    private int randomLength;

    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public Queen() {
        randomLength = (new Random().nextInt(1010));
    }

    public void act() {

        if (!checkLiveStat()) {
            getWorld().removeObject(this);
        } else {
            if (randomLength <= 1) {
                getWorld().removeObject(this);
                spawnHill();
            } else {
                randomLength -= 1;
            }
        }
    }

    protected boolean checkLiveStat() {
        if ((this.LP) > 0) {
            // substract defined Value from LP
            LP -= Ant.CALORIESBYSTEP;
            randomWalk();
            return Ant.IS_ALIVE;
        } else {
            return Ant.IS_DEAD;
        }
    }

    private void spawnHill() {
        if ((LP / randomLength) < Queen.maxANTSinHILL) {
            //FIXME
            getWorld().addObject(new AntHill(Queen.maxANTSinHILL, AntHill.DEFAULT_FOODVALUE), getX(), getY());
            getWorld().removeObject(this);
        } else {
            //FIXME
            super.getWorld().addObject(new AntHill(AntHill.DEFAULTNUMBER_ANTS, LP / randomLength), getX(), getY());
            super.getWorld().removeObject(this);
        }
    }
}
