package graphicalUI;

import domain_logic.CourseLibrary;
import domain_logic.Subject;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SubjectList extends AbstractPanel {

    private final static int PANEL_WIDTH = 300;
    private final static int PANEL_HEIGHT = 150;
    private int subjectSelectedIndex;
    private boolean gradCourseSelected;
    private boolean undergradCourseSelected;

    public SubjectList(CourseLibrary library) {
        super(PANEL_WIDTH, PANEL_HEIGHT, library);
        gradCourseSelected = false;
        undergradCourseSelected = false;
        setupPanel();
    }


    @Override
    protected void setupPanel() {
        super.setupPanel();
        String[] departmentList = getSubModuleList(super.module);

        JPanel departmentSelection = setupDepartmentPanel(departmentList);
        JPanel gradCondition = setupGradCourseSelectionPanel();
        JPanel updatePanel = setupUpdatePanel();


        addContent(departmentSelection);
        addContent(gradCondition);
        addContent(updatePanel);

    }

    private JPanel setupUpdatePanel() {
        JPanel updatePanel = new JPanel();
        JButton updateBtn = new JButton("Update Course List");
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyListeners();
            }
        });
        updatePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        updatePanel.add(updateBtn);
        return updatePanel;
    }

    private JPanel setupGradCourseSelectionPanel() {
        JCheckBox underGradCheck;
        JCheckBox gradCheck;
        JPanel gradCondition = new JPanel();
        gradCondition.setLayout(new GridLayout(2, 1));
        gradCondition.setAlignmentY(JComponent.LEFT_ALIGNMENT);

        underGradCheck = new JCheckBox("Include undergrad courses");
        gradCheck = new JCheckBox("Include grad courses");

        underGradCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undergradCourseSelected = !undergradCourseSelected;
            }
        });

        gradCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gradCourseSelected = !gradCourseSelected;
            }
        });

        gradCondition.add(underGradCheck);
        gradCondition.add(gradCheck);
        return gradCondition;
    }

    private JPanel setupDepartmentPanel(String[] departmentList) {
        JPanel departmentSelection = new JPanel();
        final JComboBox departmentListBox = new JComboBox(departmentList);
        departmentSelection.setLayout(new BoxLayout(departmentSelection,
                BoxLayout.LINE_AXIS));
        departmentSelection.add(new JLabel("Department"));
        departmentSelection.add(Box.createHorizontalGlue());
        departmentSelection.add(departmentListBox);
        departmentListBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox subjects = (JComboBox) e.getSource();
                subjectSelectedIndex = subjects.getSelectedIndex();
            }
        });
        return departmentSelection;
    }

    @Override
    protected ChangeListener getChangeListener() {
        // TODO Auto-generated method stub
        return null;
    }

    public Subject getSelectedSubject() {
        return (Subject) this.module.getSubModules().get(subjectSelectedIndex);
    }

    @Override
    protected void refreshContent() {
        // TODO Auto-generated method stub
    }

    public boolean isGradCourseSelected() {
        return gradCourseSelected;
    }

    public boolean isUndergradCourseSelected() {
        return undergradCourseSelected;
    }
}
