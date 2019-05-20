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

public class OperatorCocostur extends OperatorElement{

	public OperatorCocostur() {
		super("CocosTur", "http://www.cocos-tur.md");
	}

	private final Map<Destination, String> destinationLinks = new HashMap<Destination, String>() {{
		put(MOLDOVA, "http://www.cocos-tur.md/ru/country/moldova/tours");
		put(BULGARIA, "http://www.cocos-tur.md/ru/country/bolgariya/tours");
		put(AUSTRIA, "http://www.cocos-tur.md/ru/country/avstria/tours");
		put(ROMANIA, "http://www.cocos-tur.md/ru/country/rumynia/tours");
		put(EGYPT, "http://www.cocos-tur.md/ru/country/egipet/tours");
		put(GREECE, "http://www.cocos-tur.md/ru/country/grecia/tours");
		put(ITALY, "http://www.cocos-tur.md/ru/country/italia/tours");
		put(SPAIN, "http://www.cocos-tur.md/ru/country/ispania-0/tours");
		put(RUSSIA, "http://www.cocos-tur.md/ru/country/rossia/tours");
		put(USA, "http://www.cocos-tur.md/ru/country/ssa/tours");
		put(TURKEY, "http://www.cocos-tur.md/ru/country/turciya/tours");
		put(UKRAINE, "http://www.cocos-tur.md/ru/country/ukraina/tours");
		put(FRANCE, "http://www.cocos-tur.md/ru/country/francia/tours");
		put(MONTENEGRO, "http://www.cocos-tur.md/ru/country/cernogoria/tours");
		put(CZECH, "http://www.cocos-tur.md/ru/country/cehia/tours");
	}};

//	private final List<String> allToursLinks = new ArrayList<>(destinationLinks.values());

	private final Map<ContactDetails, String> contacts = new HashMap<ContactDetails, String>() {{
		put(ADDRESS, "2005 Молдова, г. Кишинэу, бл.Г.Виеру 24 of.4-А");
		put(WORKTIME, "Пн-Пт 10.00-18.00");
		put(PHONE, "+373 022 210501, +373 022 210934, +373 61015553");
		put(MAIL, "office@cocos-tur.md");
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

			Elements toursList = doc.select("article[role=\"article\"]");

			for (Element tourElement : toursList) {
				Tour tour = new Tour();
				Integer elementPrice;
				try {
					elementPrice = Integer.parseInt(tourElement.select("div[class*=\"tprice vc\"]").text().replaceAll("\\D", ""));
				}
				catch (Exception e) {continue;}
				if (elementPrice < minPrice || elementPrice > maxPrice) continue; // continue because tour is not in price diapason
				tour.setPrice(elementPrice);
				tour.setDestination(dest);
				tour.setPeriodInDays(null); // not exists
				tour.setSummary(tourElement.select("[class*=\"node-title\"] a").text());
				tour.setDirectLink(getMainUrl() + tourElement.select("[class*=\"node-title\"] a").attr("href"));

				matchedTours.add(tour);
			}
		}

		return matchedTours;
	}
}
