package domain_logic;

import java.util.List;

/**
 * Created by Leon on 2014-04-04. Course Library, contains all subjects
 */
public class CourseLibrary extends Module {
    // only the library has offers field
    // since it has to gather offers from OfferLister
    private List<Offer> offers;

    /**
     * default constructor
     *
     * @param offers takes a list of offerings
     */
    public CourseLibrary(List<Offer> offers) {
        super();
        this.offers = offers;
        fetchOffers();
        initializeSubModule();

    }

    private void fetchOffers() {
        for (Offer offer : offers) {
            super.populateOffering(offer);
        }
    }

    // generate Tag for Subject
    @Override
    protected String gatherTags(Offer offer) {
        return offer.getSubject();
    }

    @Override
    protected Module makeNewSubModule() {
        // TODO Auto-generated method stub
        return new Subject();
    }

    @Override
    protected void obtainModuleInformation() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void performPostOperations() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void printModuleDetails() {
        // TODO Auto-generated method stub
    }

    @Override
    public String getModuleName() {
        return null;
    }
}
