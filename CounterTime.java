import greenfoot.GreenfootImage;

/**
 * Created by Vincent Modrow on 26/02/2017.
 * PACKAGE_NAME
 */
public class CounterTime extends Counter {

    private double value = 0;
    private String text;

    public CounterTime() {
        this("");
    }

    public CounterTime(String prefix) {
        text = prefix;
        int imageWidth = (text.length() + 2) * 50;
        setImage(new GreenfootImage(imageWidth, 16));
        updateImage();
    }

    /**
     * Show the current text and count on this actor's image.
     */
    private void updateImage() {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + Double.toString(value), 1, 12);

    }

    @Override
    public void increment() {
        value += 1;
        updateImage();
    }
}
