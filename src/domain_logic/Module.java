package domain_logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Leon on 2014-04-04. Module base class for CourseLibrary, Subject,
 * Course, Unit, and Section
 */
public abstract class Module implements Iterable<Module> {

    private String tag;
    private List<Offer> offers;
    private List<Module> subModules;

    public Module() {
        this.offers = new ArrayList<>();
        this.subModules = new ArrayList<>();
    }

    private List<String> collectionOfTags() {

        List<String> subModuleTags = new ArrayList<>();

        for (Offer offer : offers) {
            String tag = gatherTags(offer);
            if (!subModuleTags.contains(tag)) {
                subModuleTags.add(tag);
            }
        }
        return subModuleTags;
    }

    protected void distributeOfferings() {

        for (Offer offer : offers) {
            for (Module module : subModules) {
                if (gatherTags(offer).equals(module.getTag())) {
                    module.populateOffering(offer);
                }
            }
        }
    }

    protected abstract String gatherTags(Offer offer);

    protected List<Offer> getOffers() {
        return offers;
    }

    protected Offer getSampleOffer() {
        final int DEFAULT_SAMPLE = 0;
        return offers.get(DEFAULT_SAMPLE);
    }

    /**
     * @return a list of sub-modules
     */
    public List<Module> getSubModules() {
        return subModules;
    }

    private String getTag() {
        return tag;
    }

    private void setTag(String tag) {
        this.tag = tag;
    }

    protected void initializeSubModule() {

        List<String> tags = collectionOfTags();

        for (String tag : tags) {
            Module module = makeNewSubModule();
            if (module != null) {
                module.setTag(tag);
                subModules.add(module);
            }
        }

        distributeOfferings();

        for (Module module : subModules) {
            module.initializeSubModule();
            module.obtainModuleInformation();
            module.performPostOperations();
        }

    }

    @Override
    public Iterator<Module> iterator() {
        return Collections.unmodifiableList(subModules).iterator();
    }

    protected abstract Module makeNewSubModule();

    protected abstract void obtainModuleInformation();

    protected abstract void performPostOperations();

    protected void populateOffering(Offer offer) {
        offers.add(offer);
    }

    /**
     * dump out model content
     */
    public void printDetails() {
        printModuleDetails();
        printSubModuleDetails();
    }

    protected abstract void printModuleDetails();

    private void printSubModuleDetails() {
        for (Module module : subModules) {
            module.printDetails();
        }
    }

    public abstract String getModuleName();

}