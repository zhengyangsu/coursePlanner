package domain_logic;

/**
 * Created by Leon on 2014-04-04. Offer contains all details about the offering
 */
public class Offer {

    private String strm;
    private String subject;
    private String catalog_nbr;
    private String location;
    private int enrl_cap;
    private String ssr_component;
    private int enrl_tot;
    private String[] instructors;

    /**
     * default constructor
     *
     * @param offerDetails takes a String array of details and decode them
     */
    public Offer(String[] offerDetails) {

        offerDetailDecoder(offerDetails);

    }

    /**
     * @return subject catalog number
     */
    public String getCatalog_nbr() {
        return catalog_nbr;
    }

    /**
     * @return enrollment capacity
     */
    public int getEnrl_cap() {
        return enrl_cap;
    }

    /**
     * @return total enrollment
     */
    public int getEnrl_tot() {
        return enrl_tot;
    }

    /**
     * @return String array of instructors
     */
    public String[] getInstructors() {
        return instructors;
    }

    /**
     * @return returns offering location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return component type
     */
    public String getSsr_component() {
        return ssr_component;
    }

    /**
     * @return term and semester
     */
    public String getStrm() {
        return strm;
    }

    /**
     * @return subject name
     */
    public String getSubject() {
        return subject;
    }

    public int getYear() {
        final int CENTRY = 1900;
        return CENTRY + Integer.parseInt(getStrm()) / 10;
    }

    private void offerDetailDecoder(String[] offerDetails) {

        final int COMPLETE_DETAILS = 8;
        final int strm_index = 0;
        final int subject_index = 1;
        final int catalog_nbr_index = 2;
        final int location_index = 3;
        final int enrl_cap_index = 4;
        final int ssr_component_index = 5;
        final int enrl_tot_index = 6;
        final int instructors_index = 7;

        strm = offerDetails[strm_index];
        subject = offerDetails[subject_index];
        catalog_nbr = offerDetails[catalog_nbr_index];
        location = offerDetails[location_index];
        enrl_cap = Integer.parseInt(offerDetails[enrl_cap_index]);
        ssr_component = offerDetails[ssr_component_index];
        enrl_tot = Integer.parseInt(offerDetails[enrl_tot_index]);

        if (offerDetails.length == COMPLETE_DETAILS) {
            instructors = new String[]{offerDetails[instructors_index]};
        } else if (offerDetails.length > COMPLETE_DETAILS) {
            instructors = new String[(offerDetails.length - instructors_index)];
            for (int index = 0, detailIndex = instructors_index; detailIndex < offerDetails.length; index++, detailIndex++) {
                instructors[index] = offerDetails[detailIndex];
            }
        } else {
            instructors = new String[]{""};
        }

    }

    /**
     * displays all details at once
     */
    public void printOfferDetails() {

        StringBuilder builder = new StringBuilder();
        builder.append(strm);
        builder.append(subject);
        builder.append(catalog_nbr);
        builder.append(location);
        builder.append(enrl_cap);
        builder.append(ssr_component);
        builder.append(enrl_tot);
        for (String instructor : instructors) {
            builder.append(instructor);
        }

        System.out.println(builder.toString());
    }

}
