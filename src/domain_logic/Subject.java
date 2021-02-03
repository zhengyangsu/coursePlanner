package domain_logic;

/**
 * Created by Leon on 2014-04-04.
 */
public class Subject extends Module {

    private String subjectName;

    public Subject() {
        super();
    }

    // gather Tag for Course
    @Override
    protected String gatherTags(Offer offer) {
        return offer.getSubject() + offer.getCatalog_nbr();
    }

    @Override
    protected Module makeNewSubModule() {
        return new Course();
    }

    @Override
    protected void obtainModuleInformation() {
        subjectName = getSampleOffer().getSubject();
    }

    @Override
    protected void performPostOperations() {
    }

    @Override
    protected void printModuleDetails() {
        System.out.println(subjectName);
    }

    /**
     * @return subject name
     */
    @Override
    public String getModuleName() {
        return subjectName;
    }

}
