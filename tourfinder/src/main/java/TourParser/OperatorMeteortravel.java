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

public class OperatorMeteortravel extends OperatorElement{

	public OperatorMeteortravel() {
		super("Meteor Travel", "http://meteortravel.md/");
	}

	private final Map<Destination, String> destinationLinks = new HashMap<Destination, String>() {{
		put(BULGARIA, "http://meteortravel.md/tour-category/bulgaria/");
		put(MONTENEGRO, "http://meteortravel.md/tour-category/montenegro/");
		put(TURKEY, "http://meteortravel.md/tour-category/turkey/");
		put(ROMANIA, "http://meteortravel.md/tour-category/romania/");
		put(EGYPT, "http://meteortravel.md/tour-category/egipet/");
		put(GREECE, "http://meteortravel.md/tour-category/greece/");
	}};

//	private final List<String> allToursLinks = new ArrayList<>(destinationLinks.values());

	private final Map<ContactDetails, String> contacts = new HashMap<ContactDetails, String>() {{
		put(ADDRESS, "БЕЛЬЦЫ, КИЕВСКАЯ 128");
		put(PHONE, "+373 799 71 990");
		put(MAIL, "INFO@METEORTRAVEL.MD");
	}};

	public Map<ContactDetails, String> getContacts() {
		return contacts;
	}

	public List<Tour> parseWithParameters(List<Destination> destinations, Integer minPrice, Integer maxPrice) {
		List<Tour> matchedTours = new ArrayList<Tour>();

		Document doc;

		for (Destination dest : destinations) {
			if (!destinationLinks.containsKey(dest)) continue;

			try {
				doc = Jsoup.connect(destinationLinks.get(dest)).userAgent("Mozilla").get();
			} catch (IOException e) {
				continue;
			}

			Elements toursList = doc.select("div[class*=\"tourmaster-tour-medium-inner\"]");

			for (Element tourElement : toursList) {
				Tour tour = new Tour();

				Integer elementPrice = Integer.parseInt(tourElement.select("div[class*=\"tourmaster-tour-price\"]  span[class=\"tourmaster-tail\"]").text().replaceAll("\\D", ""));
				if (elementPrice < minPrice || elementPrice > maxPrice) continue; // continue because tour is not in price diapason
				tour.setPrice(elementPrice);
				tour.setDestination(dest);
				tour.setPeriodInDays(null); // not exists
				tour.setSummary(tourElement.select("[class*=\"tourmaster-tour-title\"] a").text());
				tour.setDirectLink(tourElement.select("[class*=\"tourmaster-tour-title\"] a").attr("href"));

				matchedTours.add(tour);
			}
		}

		return matchedTours;
	}
}
