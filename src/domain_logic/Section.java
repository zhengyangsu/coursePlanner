package domain_logic;

/**
 * Created by MikeKornitsky on 4/5/14.
 */
public class Section extends Module {

    private String componentType;
    private int enrollment;
    private int capacity;

    public Section() {
        super();
        enrollment = 0;
        capacity = 0;
    }

    private void calculateTotalEnrollmentAndCapacity() {
        for (Offer offer : getOffers()) {
            enrollment += offer.getEnrl_tot();
            capacity += offer.getEnrl_cap();
        }
    }

    @Override
    protected String gatherTags(Offer offer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Module makeNewSubModule() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void obtainModuleInformation() {
        componentType = getSampleOffer().getSsr_component();
    }

    @Override
    protected void performPostOperations() {
        calculateTotalEnrollmentAndCapacity();
    }

    @Override
    protected void printModuleDetails() {
        System.out.print("\t\t\t" + "Type=" + componentType);
        System.out.println(" Enrollment=" + enrollment + "/" + capacity);
    }

    @Override
    public String getModuleName() {
        return null;
    }

}
