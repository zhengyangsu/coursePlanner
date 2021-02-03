package graphicalUI;

import javax.swing.*;
import java.awt.*;

public class GUIDisplayer {

    public static void main(String[] args) {

        PanelManager manager = new PanelManager();
        JPanel leftPanel = manager.leftPanel();
        JPanel rightPanel = manager.rightPanel();
        JPanel midPanel = manager.midPanel();

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FAS Course Planner");

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(midPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }

}
