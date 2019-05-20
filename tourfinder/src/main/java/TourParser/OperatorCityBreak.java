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

public class OperatorCityBreak extends OperatorElement {

	private final Map<Destination, String> destinationLinks = new HashMap<Destination, String>() {{
		put(AUSTRIA, "https://citybreak.md/packages/?destination=53&type=-1&duration=-1&price=-1");
		put(BELGIUM, "https://citybreak.md/packages/?destination=64&type=-1&duration=-1&price=-1");
		put(GERMANY, "https://citybreak.md/packages/?destination=108&type=-1&duration=-1&price=-1");
		put(FRANCE, "https://citybreak.md/packages/?destination=56&type=-1&duration=-1&price=-1");
		put(ITALY, "https://citybreak.md/packages/?destination=19&type=-1&duration=-1&price=-1");
		put(RUSSIA, "https://citybreak.md/packages/?destination=60&type=-1&duration=-1&price=-1");
		put(SPAIN, "https://citybreak.md/packages/?destination=55&type=-1&duration=-1&price=-1");
		put(TURKEY, "https://citybreak.md/packages/?destination=49&type=-1&duration=-1&price=-1");
	}};

//	private final List<String> allToursLinks = new ArrayList<>(destinationLinks.values());

	private final Map<ContactDetails, String> contacts = new HashMap<ContactDetails, String>() {{
		put(ADDRESS, "Strada Columna 103");
		put(WORKTIME, "Luni – Vineri : 09:00 – 18:00");
		put(PHONE, " + (373) 22 99 44 44, + (373) 60 89 44 48");
		put(MAIL, "catalina.gitu@citybreak.md");
	}};

	public OperatorCityBreak() {
		super("City Break", "https://citybreak.md/");
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
                doc = Jsoup.connect(destinationLinks.get(dest)).userAgent("Mozilla").get();
            } catch (IOException e) {
                continue;
            }

            Elements toursList = doc.select("article[id*=\"item\"]");
            for (Element tourElement : toursList) {
                Tour tour = new Tour();
                Integer elementPrice = Integer.parseInt(tourElement.getElementsByClass("item-price").text().replaceAll("\\D", ""));
                if (elementPrice < minPrice || elementPrice > maxPrice) continue; // continue because tour is not in price diapason
                tour.setPrice(elementPrice);
                tour.setDestination(dest);
                tour.setPeriodInDays(Integer.parseInt(tourElement.getElementsByClass("item-aside").text().replaceAll("\\D", "")));
                tour.setSummary(tourElement.select(".item-excerpt p ").text().replace("[…]", "..."));
                tour.setDirectLink(tourElement.getElementsByTag("a").attr("href"));

                matchedTours.add(tour);
            }
        }
		return matchedTours;
	}
}
