package domain_logic;

import histogram.Histogram;

/**
 * Created by Leon on 2014-04-04.
 */
public class Course extends Module {

    private String courseName;
    private String catalog_nbr;
    private boolean undergrad;
    private Histogram semesterOffering;
    private Histogram campusOffering;
    private int startYear;
    private int endYear;

    public Course() {
        super();
    }

    private void determineCourseType() {
        final int gradCourseNbr = 499;
        int catalogNbr = Integer
                .parseInt(catalog_nbr.replaceAll("[^\\d.]", ""));
        undergrad = (catalogNbr > gradCourseNbr) ? false : true;
    }

    @Override
    protected String gatherTags(Offer offer) {
        return offer.getStrm() + offer.getLocation();
    }

    private void generateHistograms() {
        // Intervals
        final int SEMESTER = 3;
        final int CAMPUS = 4;
        int numOfUnits = getSubModules().size();

        int[] semesters = new int[numOfUnits];
        int[] campuses = new int[numOfUnits];

        for (int i = 0; i < numOfUnits; i++) {
            semesters[i] = ((Unit) getSubModules().get(i)).getSemester();
            campuses[i] = ((Unit) getSubModules().get(i)).getCampus();
        }
        semesterOffering = new Histogram(semesters, SEMESTER);
        campusOffering = new Histogram(campuses, CAMPUS);
    }

    /**
     * @return Campus Offering Histogram
     */
    public Histogram getCampusOfferingHistogram() {
        return campusOffering;
    }

    /**
     * @return Semester Offering Histogram
     */
    public Histogram getSemesterOfferingHistogram() {
        return semesterOffering;
    }

    public boolean isUndergrad() {
        return undergrad;
    }

    @Override
    protected Module makeNewSubModule() {
        // TODO Auto-generated method stub
        return new Unit();
    }

    @Override
    protected void obtainModuleInformation() {
        courseName = getSampleOffer().getSubject();
        catalog_nbr = getSampleOffer().getCatalog_nbr();
    }

    @Override
    protected void performPostOperations() {
        generateHistograms();
        determineCourseType();
        calculateYearRange();
    }

    @Override
    protected void printModuleDetails() {
        System.out.println(getModuleName());
    }

    /**
     * @return course name
     */
    @Override
    public String getModuleName() {
        return courseName + " " + catalog_nbr;
    }

    private void calculateYearRange() {
        int min = getSampleOffer().getYear();
        int max = getSampleOffer().getYear();
        for (Offer offer : getOffers()) {
            min = (offer.getYear() < min) ? offer.getYear() : min;
            max = (offer.getYear() > max) ? offer.getYear() : max;
        }
        startYear = min;
        endYear = max;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getCatalognbr() {

        int catalogNbr = Integer.parseInt(catalog_nbr.replaceAll("[a-zA-Z]", ""));

        return catalogNbr;
    }

}
