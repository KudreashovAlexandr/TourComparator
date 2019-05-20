package TourParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static TourParser.ContactDetails.*;
import static TourParser.ContactDetails.MAIL;

public class OperatorVoiajMd extends OperatorElement {
    private final String name = "Voiaj";

    private final String mainUrl = "https://voiaj.md/";

    private final Map<Destination, String> destinationLinks = new HashMap<Destination, String>();

    private final List<String> allToursLinks = new ArrayList<>(destinationLinks.values());

    private final Map<ContactDetails, String> contacts = new HashMap<ContactDetails, String>() {{
        put(ADDRESS, "г. Кишинев Бул. Негруцци, 4/2, г. Бэлць, ул. Штефан сел Маре, 6/1");
        put(WORKTIME, "Неизвестно");
        put(PHONE, " +373 22 54-64-64, +373 231 63 633, 068160033");
        put(MAIL, "voiaj@voiaj.md");
    }};

    public OperatorVoiajMd() {}


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
