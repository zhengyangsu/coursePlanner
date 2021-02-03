package graphicalUI;

import domain_logic.Course;
import domain_logic.CourseLibrary;
import domain_logic.Module;
import domain_logic.Subject;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CourseList extends AbstractPanel {

    private final static int PANEL_WIDTH = 300;
    private final static int PANEL_HEIGHT = 800;
    private JList courseList;
    private Subject subject;
    private boolean gradCourseSelected;
    private boolean undergradCourseSelected;

    public CourseList(CourseLibrary library) {
        super(PANEL_WIDTH, PANEL_HEIGHT, library);
        setupPanel();

    }

    @Override
    protected List<Module> getfilteredSubModules(Module module) {
        List<Module> filteredList = new ArrayList<>();

        if (!(undergradCourseSelected && gradCourseSelected)) {
            if (undergradCourseSelected) {
                for (Module subModule : super.getfilteredSubModules(module)) {
                    if (((Course) subModule).isUndergrad()) {
                        filteredList.add(subModule);
                    }
                }
            }
            if (gradCourseSelected) {
                for (Module subModule : super.getfilteredSubModules(module)) {
                    if (!((Course) subModule).isUndergrad()) {
                        filteredList.add(subModule);
                    }
                }
            }
        } else if (undergradCourseSelected && gradCourseSelected) {
            filteredList = super.getfilteredSubModules(module);
        }


        return filteredList;
    }


    @Override
    protected void setupPanel() {
        super.setupPanel();
        JScrollPane scroller;
        String[] list = getSubModuleList(subject);
        setupCourseList(list);
        scroller = new JScrollPane(courseList);
        addContent(scroller);
    }

    private void setupCourseList(String[] list) {
        courseList = new JList(list);
        courseList.setBorder(BorderFactory.createEmptyBorder());
        courseList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        courseList.setVisibleRowCount(-1);
        courseList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                notifyListeners();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        courseList.setFixedCellWidth(PANEL_WIDTH / 4);
    }

    @Override
    protected ChangeListener getChangeListener() {
        return new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof SubjectList) {
                    SubjectList filter = ((SubjectList) e.getSource());
                    subject = filter.getSelectedSubject();
                    //sort courses by catalog number
                    Collections.sort(subject.getSubModules(), new Comparator<Module>() {
                        @Override
                        public int compare(Module o1, Module o2) {
                            Course c1 = (Course) o1;
                            Course c2 = (Course) o2;
                            return c1.getCatalognbr() - c2.getCatalognbr();
                        }
                    });
                    gradCourseSelected = filter.isGradCourseSelected();
                    undergradCourseSelected = filter.isUndergradCourseSelected();
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

    public Course getSelectedCourse() {
        int selectedCourseIndex = courseList.getSelectedIndex();
        return (Course) subject.getSubModules().get(selectedCourseIndex);
    }

}
