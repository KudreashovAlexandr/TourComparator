package TourParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static TourParser.ContactDetails.*;
import static TourParser.Destination.*;

public class OperatorVolaRo extends OperatorElement {

    private final String name = "Favorit Tur";

    private final String mainUrl = "https://www.favorit-tur.md/";

    private final Map<Destination, String> destinationLinks = new HashMap<Destination, String>() {{
        put(BULGARIA, "https://www.favorit-tur.md/ru/country-category/bulgaria/");
        put(EGYPT, "https://www.favorit-tur.md/ru/country-category/egipt/");
        put(GREECE, "https://www.favorit-tur.md/ru/country-category/grecia/");
        put(ROMANIA, "https://www.favorit-tur.md/ru/country-category/romania-munte/");
        put(UKRAINE, "https://www.favorit-tur.md/ru/country-category/ukraina/");
        put(TURKEY, "https://www.favorit-tur.md/ru/country-category/turcia/");
    }};

    private final List<String> allToursLinks = new ArrayList<>(destinationLinks.values());

    private final Map<ContactDetails, String> contacts = new HashMap<ContactDetails, String>() {{
        put(ADDRESS, "Кишинёв, улица Ion Creanga 6/3");
        put(WORKTIME, "Понедельник – Суббота");
        put(PHONE, "022 90 90 90");
        put(MAIL, "Неизвестно");
    }};

    public OperatorVolaRo() {}

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

        List<Tour> matchedTours = new ArrayList<Tour>();

        for (Destination dest : destinations) {

            if (!destinationLinks.containsKey(dest)) continue;

            Document doc;

            try {
                doc = Jsoup.connect("https://www.vola.ro/city-break")
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            Elements toursList = doc.select("div[class*=\"grid-item\"]");
            System.out.println("TEST "+ doc.html() + " " + dest.getRuDescription()+ "  " + toursList.size());

            for (Element tourElement : toursList) {
                Tour tour = new Tour();
                Integer elementPrice = Integer.parseInt(tourElement.getElementsByClass("red-bg white-col").text().replaceAll("\\D", ""));
                if (elementPrice < minPrice || elementPrice > maxPrice) continue; // continue because tour is not in price diapason
                tour.setPrice(elementPrice);
                tour.setDestination(dest);
                tour.setPeriodInDays(Integer.parseInt(tourElement.getElementsByClass("div[class='left details'] span")
                        .text().replaceAll("^.*/","").replaceAll("\\D", "")));
                tour.setSummary(tourElement.select("div[class='text'] a").text());
                tour.setDirectLink(tourElement.select("div[class='image'] a").attr("href"));

                matchedTours.add(tour);
            }
        }
        return matchedTours;
    }
}
