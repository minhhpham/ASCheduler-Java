import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private JFrame frame;
    private Component prev;
    private Component next;

    public Main(JFrame frame, Component prev, Component next) {
        this.frame = frame;
        this.prev = prev;
        this.next = next;
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
        JFrame frame = new JFrame("ASCheduler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DataSingleton singleton = DataSingleton.getInstance();
        NumberFieldsPanel numberFields = new NumberFieldsPanel(DataSingleton.getInstance());
        EmployeeInputPanel employeeInput = new EmployeeInputPanel(DataSingleton.getInstance());

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

       // Add button listeners
        NumberFieldsPanel.btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                changeFrame(frame, numberFields, new EmployeeInputPanel(DataSingleton.getInstance()));
            }
        });
        
        EmployeeInputPanel.btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                NumberFieldsPanel numberFields = new NumberFieldsPanel(DataSingleton.getInstance());
                changeFrame(frame, employeeInput, numberFields);
            }
        });

    }
}
