package domain_logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MikeKornitsky on 4/5/14.
 */
public class Unit extends Module {

    private String strm;
    private String location;
    private int semester;
    private int campus;
    private List<String> instructors;

    public Unit() {
        super();
        instructors = new ArrayList<>();
    }

    private void gatherInstructors() {
        for (Offer offer : getOffers()) {
            for (String instructor : offer.getInstructors()) {
                if (!instructors.contains(instructor)) {
                    instructors.add(instructor.replace("\"", ""));
                }
            }
        }
    }

    @Override
    protected String gatherTags(Offer offer) {
        return offer.getSsr_component();
    }

    public int getCampus() {
        return campus;
    }

    public int getSemester() {

        switch (semester) {
            case 1:
                return 0;
            case 4:
                return 1;
            case 7:
                return 2;
        }
        return 0;
    }

    @Override
    protected Module makeNewSubModule() {
        // TODO Auto-generated method stub
        return new Section();
    }

    @Override
    protected void obtainModuleInformation() {
        strm = getSampleOffer().getStrm();
        location = getSampleOffer().getLocation();
        semester = Integer.parseInt(getSampleOffer().getStrm()) % 10;

        switch (location) {
            case "BURNABY":
                campus = 0;
                break;
            case "SURREY":
                campus = 1;
                break;
            case "VAN":
                campus = 2;
                break;
            default:
                campus = 3;
                break;
        }
    }

    @Override
    protected void performPostOperations() {
        // TODO Auto-generated method stub
        gatherInstructors();

    }

    @Override
    protected void printModuleDetails() {
        System.out.print("\t\t" + strm + " in " + location + " by");
        for (String instructor : instructors) {
            System.out.print(" " + instructor);
        }
        System.out.println();
    }

    @Override
    public String getModuleName() {
        return null;
    }

    public int getYear() {
        return Integer.parseInt(strm) / 10 + 1900;
    }

}
