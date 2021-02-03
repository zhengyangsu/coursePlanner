package graphicalUI;

import domain_logic.CourseLibrary;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class CourseDetails extends AbstractPanel {

    private final static int PANEL_WIDTH = 300;
    private final static int PANEL_HEIGHT = 300;


    public CourseDetails(CourseLibrary library) {
        super(PANEL_WIDTH, PANEL_HEIGHT, library);
        setupPanel();

    }

    @Override
    protected void setupPanel() {

        JTextField courseName = new JTextField();
        courseName.setColumns(10);
        courseName.setEnabled(false);
        add(courseName);

    }

    @Override
    protected ChangeListener getChangeListener() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void refreshContent() {
        // TODO Auto-generated method stub

    }
}
