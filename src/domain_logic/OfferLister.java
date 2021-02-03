package domain_logic;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Leon on 2014-04-04. OfferLister lists all offerings from csv file
 */
public class OfferLister implements Iterable<Offer> {

    final private String PATH;
    private List<Offer> offers;

    /**
     * Default constructor
     *
     * @param PATH the path of the desire csv file
     */
    public OfferLister(String PATH) {
        this.PATH = PATH;
        this.offers = new ArrayList<>();
        gatherOffers();
    }

    private void gatherOffers() {

        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(PATH),
                    Charset.forName("UTF-8"));
            for (String line : lines) {
                if (line.length() > 0 && line.matches(".*\\d.*")) {
                    String[] details = line.split(",");
                    offers.add(new Offer(details));
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @return all offerings read from the csv file as a list of Offer object
     */
    public List<Offer> getOffers() {
        return offers;
    }

    @Override
    public Iterator<Offer> iterator() {
        return Collections.unmodifiableList(offers).iterator();
    }
}
