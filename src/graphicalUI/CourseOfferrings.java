package graphicalUI;

import domain_logic.Course;
import domain_logic.CourseLibrary;
import domain_logic.Module;
import domain_logic.Unit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

public class CourseOfferrings extends AbstractPanel {
    private final static int PANEL_WIDTH = 800;
    private final static int PANEL_HEIGHT = 800;

    private final int NUM_OF_SEMESTERS = 3;
    private int rangeOfYears;
    private Course course;


    public CourseOfferrings(CourseLibrary library) {
        super(PANEL_WIDTH, PANEL_HEIGHT, library);
        setupPanel();
    }

    @Override
    protected void setupPanel() {

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));


        for (int i = 0; i < rangeOfYears; i++) {
            for (int j = 0; j < NUM_OF_SEMESTERS; j++) {
                GridBagConstraints c = new GridBagConstraints();

                c.gridx = j;
                c.gridy = i;
                c.weightx = 1.0;
                c.weighty = 1.0;

                c.fill = GridBagConstraints.BOTH;
                JPanel box = new JPanel();
                box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
                JButton btn = new JButton(i + "," + j);
                box.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                box.add(btn);
                add(box, c);
            }
        }


    }

    @Override
    protected ChangeListener getChangeListener() {

        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof CourseList) {
                    CourseList courseList = ((CourseList) e.getSource());
                    course = courseList.getSelectedCourse();
                    Collections.sort(course.getSubModules(), new Comparator<Module>() {
                        @Override
                        public int compare(Module o1, Module o2) {
                            Unit u1 = (Unit) o1;
                            Unit u2 = (Unit) o2;
                            return u1.getYear() - u2.getYear();
                        }
                    });
                    rangeOfYears = course.getEndYear() - course.getStartYear();
                }
                refreshContent();
            }
        };
    }

    @Override
    protected void refreshContent() {
        removeAll();
        setupPanel();
        revalidate();
        repaint();
    }

}
