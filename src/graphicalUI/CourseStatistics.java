package graphicalUI;

import domain_logic.Course;
import domain_logic.CourseLibrary;
import histogram.Histogram;
import histogram.HistogramIcon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CourseStatistics extends AbstractPanel {

    private final static int PANEL_WIDTH = 300;
    private final static int PANEL_HEIGHT = 800;
    private final int HISTOGRAM_HEIGHT = 200;
    private final int HISTOGRAM_WIDTH = 300;

    private Histogram semesterHistogram;
    private Histogram campusHistogram;

    private Course course;
    private String name;


    public CourseStatistics(CourseLibrary library) {
        super(PANEL_WIDTH, PANEL_HEIGHT, library);
        semesterHistogram = new Histogram(new int[]{}, 0);
        campusHistogram = new Histogram(new int[]{}, 0);
        name = "Selected Course: ";
        setupPanel();
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();

        JLabel title = new JLabel(name);
        JPanel semesterStatPanel = setupHistogramPanel("Semester offerings", "(0 = Spring, 1 = Summer , 2 = Fall)", semesterHistogram);
        JPanel campusStatPanel = setupHistogramPanel("Campus offerings", "(0 = Bby, 1 = Sry , 2 = Van, 3 = other)", campusHistogram);

        add(title);
        add(semesterStatPanel);
        add(campusStatPanel);

    }

    private JPanel setupHistogramPanel(String title, String additionalLable, Histogram histogram) {
        JPanel panel = new JPanel();
        JLabel histTitle = new JLabel(title);
        JLabel histLabel = new JLabel(additionalLable);
        HistogramIcon histogramIcon = new HistogramIcon(histogram, HISTOGRAM_WIDTH, HISTOGRAM_HEIGHT);
        JLabel histogramLabel = new JLabel(histogramIcon);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(histTitle);
        panel.add(histogramLabel);
        panel.add(histLabel);
        return panel;
    }

    @Override
    protected ChangeListener getChangeListener() {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof CourseList) {
                    CourseList courseList = ((CourseList) e.getSource());
                    course = courseList.getSelectedCourse();
                    name = "Selected Course: " + course.getModuleName();
                }
                refreshContent();
            }
        };
    }

    private void refreshHistogramIcons() {
        semesterHistogram = course.getSemesterOfferingHistogram();
        campusHistogram = course.getCampusOfferingHistogram();
    }


    @Override
    protected void refreshContent() {
        refreshHistogramIcons();
        removeAll();
        setupPanel();
        revalidate();
        repaint();
    }

}
