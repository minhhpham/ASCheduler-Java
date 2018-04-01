import javax.swing.*;
import java.awt.*;

public class Main {

    private static JFrame frame;
    private Component prev;
    private Component next;

    private static DataSingleton singleton = DataSingleton.getInstance();


    public Main(JFrame frame, Component prev, Component next) {
        this.frame = frame;
        this.prev = prev;
        this.next = next;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void changeFrame(JFrame frame, Component cur, Component toChange) {
        frame.setVisible(false);
        frame.remove(cur);
        frame.add(toChange);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create and set up window
        frame = new JFrame("ASCheduler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NumberFieldsPanel numberFields = new NumberFieldsPanel(singleton);
        singleton.setNumberFields(numberFields);

        // Add contents to the window
        frame.add(numberFields);

        // Display the window
        frame.setSize(800, 800);
        frame.pack();
        frame.setVisible(true);

        // Display frame in center of screen
        Dimension center = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(center.width/2 - frame.getSize().width/2, center.height/2 - frame.getSize().height/2 );
        frame.setLocationRelativeTo(null);
    }
}
