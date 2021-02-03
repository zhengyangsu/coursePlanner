package graphicalUI;

import domain_logic.Module;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel {

    protected final int WIDTH;
    protected final int HEIGHT;
    protected Module module;
    private List<ChangeListener> listeners = new ArrayList<>();

    public AbstractPanel(int width, int height, Module module) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.module = module;
    }

    // Observer Functions
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    protected void addContent(Component content) {
        add(content);
    }

    protected abstract ChangeListener getChangeListener();

    protected void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }

    protected abstract void refreshContent();

    protected void setupPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private List<Module> getSubModules(Module module) {
        List<Module> subModules = new ArrayList<>();
        for (Module subModule : module.getSubModules()) {
            subModules.add(subModule);
        }
        return subModules;
    }

    protected List<Module> getfilteredSubModules(Module module) {
        return getSubModules(module);
    }

    protected String[] getSubModuleList(Module module) {
        List<Module> subModules = getfilteredSubModules(module);
        String[] list = new String[subModules.size()];
        for (int index = 0; index < subModules.size(); index++) {
            list[index] = subModules.get(index).getModuleName();
        }
        return list;
    }

}
