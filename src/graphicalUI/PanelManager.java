package graphicalUI;

import domain_logic.CourseLibrary;
import domain_logic.OfferLister;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@SuppressWarnings("serial")
public class PanelManager {

    private final String DIRECTORY_PATH = new File("").getAbsolutePath();
    private final String PATH = DIRECTORY_PATH + "/doc/course_data_2014.csv";
    private CourseLibrary library = new CourseLibrary(new OfferLister(
            PATH).getOffers());

    private SubjectList CourseFilter;
    private CourseList courseList;
    private CourseStatistics courseStatistics;
    private CourseDetails courseDetails;
    private CourseOfferrings courseOffer;

    public PanelManager() {

        CourseFilter = new SubjectList(library);
        courseList = new CourseList(library);
        courseOffer = new CourseOfferrings(library);
        courseStatistics = new CourseStatistics(library);
        courseDetails = new CourseDetails(library);
        CourseFilter.addChangeListener(courseList.getChangeListener());
        courseList.addChangeListener(courseStatistics.getChangeListener());
        courseList.addChangeListener(courseOffer.getChangeListener());
        courseStatistics.addChangeListener(courseDetails.getChangeListener());
    }

    public JPanel leftPanel() {

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        leftPanel.add(getTitlePanel("Course List Filter"));
        leftPanel.add(CourseFilter);
        leftPanel.add(getTitlePanel("Course List"));
        leftPanel.add(courseList);
        return leftPanel;
    }

    public JPanel midPanel() {
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.PAGE_AXIS));
        midPanel.add(getTitlePanel("Course List Filter"));
        midPanel.add(courseOffer);
        return midPanel;
    }

    // Statics and courseDetails
    public JPanel rightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.add(getTitlePanel("Statistics"));
        courseStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(courseStatistics);
        rightPanel.add(getTitlePanel("Details of Course Offerings"));
        rightPanel.add(courseDetails);
        return rightPanel;
    }

    private JPanel getTitlePanel(String title) {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel(title);
        titlePanel.add(titleLabel);
        return titlePanel;
    }
}
