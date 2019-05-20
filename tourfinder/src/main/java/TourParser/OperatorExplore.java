package TourParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static TourParser.ContactDetails.*;
import static TourParser.Destination.*;
import static TourParser.Destination.TURKEY;

public class OperatorExplore extends OperatorElement {

	public OperatorExplore() {
		super("Explore", "http://www.explore.md/");
	}

	private final Map<Destination, String> destinationLinks = new HashMap<Destination, String>() {{
		put(BULGARIA, "http://www.explore.md/?tour-search&destination-rus=%25d0%25b1%25d0%25be%25d0%25bb%25d0%25b3%25d0%" +
				"25b0%25d1%2580%25d0%25b8%25d1%258f&duration-rus&min-price-rus&max-price-rus&page_id=4858");
		put(GREECE, "http://www.explore.md/?tour-search&destination-rus=%25d0%25b3%25d1%2580%25d0%25b5%25d1%2586%25d0%25" +
				"b8%25d1%258f&duration-rus&min-price-rus&max-price-rus&page_id=4858");
		put(ITALY, "http://www.explore.md/?tour-search&destination-rus=%25d0%2598%25d1%2582%25d0%25b0%25d0%25bb%25d0%25b" +
				"8%25d1%258f&duration-rus&min-price-rus&max-price-rus&page_id=4858");
		put(RUSSIA, "http://www.explore.md/?tour-search&destination-rus=%25d0%25a0%25d0%25be%25d1%2581%25d1%2581%25d0%25" +
				"b8%25d1%258f&duration-rus&min-price-rus&max-price-rus&page_id=4858");
		put(MONTENEGRO, "http://www.explore.md/?tour-search&destination-rus=%25d1%2587%25d0%25b5%25d1%2580%25d0%25bd%25d" +
				"0%25be%25d0%25b3%25d0%25be%25d1%2580%25d0%25b8%25d1%258f&duration-rus&min-price-rus&max-price-rus&page_id=4858");
		put(CZECH, "http://www.explore.md/?tour-search&destination-rus=%25d0%25a7%25d0%25b5%25d1%2585%25d0%25b8%25d1%258" +
				"f&duration-rus&min-price-rus&max-price-rus&page_id=4858");
		put(TURKEY, "http://www.explore.md/?tour-search&destination-rus=%25d1%2582%25d1%2583%25d1%2580%25d1%2586%25d0%25" +
				"b8%25d1%258f&duration-rus&min-price-rus&max-price-rus&page_id=4858");
	}};

//	private final List<String> allToursLinks = new ArrayList<>(destinationLinks.values());

	private final Map<ContactDetails, String> contacts = new HashMap<ContactDetails, String>() {{
		put(ADDRESS, "str. 31 August 1989, 64");
		put(WORKTIME, "Пн-Пт 09.00-18.00, Сб 09.00 - 15.00 ");
		put(PHONE, "+373 22 844 333, +373 78 844 333, +373 68 844 333");
		put(MAIL, "travel@explore.md");
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
