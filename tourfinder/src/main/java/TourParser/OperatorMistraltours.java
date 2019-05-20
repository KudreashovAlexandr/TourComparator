package TourParser;

import java.util.*;

import static TourParser.ContactDetails.*;
import static TourParser.ContactDetails.MAIL;

public class OperatorMistraltours extends OperatorElement {

    private final String name = "Favorit Tur";

    private final String mainUrl = "https://www.favorit-tur.md/";

    private final Map<Destination, String> destinationLinks = new HashMap<Destination, String>() {{}};

    private final List<String> allToursLinks = Arrays.asList(
            "http://mistraltours.md/tour-category/mare/",
            "http://mistraltours.md/tour-category/munte/"
    );

    private final Map<ContactDetails, String> contacts = new HashMap<ContactDetails, String>() {{
        put(ADDRESS, "Кишинёв, улица Ion Creanga 6/3");
        put(WORKTIME, "Понедельник – Суббота");
        put(PHONE, "022 90 90 90");
        put(MAIL, "Неизвестно");
    }};

    public OperatorMistraltours() {}


    public String getMainUrl() {
        return mainUrl;
    }

    public String getName() {
        return name;
    }

    public Map<ContactDetails, String> getContacts() {
        return contacts;
    }

    public List<Tour> parseWithParameters(List<Destination> destinations, Integer minPrice, Integer maxPrice) {
        return null;
    }
}
