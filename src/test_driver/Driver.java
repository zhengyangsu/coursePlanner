package test_driver;

import domain_logic.CourseLibrary;
import domain_logic.OfferLister;

import java.io.File;

/**
 * Created by Leon on 2014-04-04.
 */
public class Driver {

    final static String DIRECTORY_PATH = new File("").getAbsolutePath();
    final static String PATH = DIRECTORY_PATH + "/doc/course_data_2014.csv";

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        CourseLibrary library = new CourseLibrary(
                new OfferLister(PATH).getOffers());
        library.printDetails();

    }
}
