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

public class OperatorAlpimarin extends OperatorElement {

	private final List<String> allToursLinks = Arrays.asList(
			"http://alpimarin.md/category/recomenduem/",
			"http://alpimarin.md/category/sea/",
			"http://alpimarin.md/category/bus-tours/",
			"http://alpimarin.md/category/eurasia/",
			"http://alpimarin.md/category/usa/",
			"http://alpimarin.md/category/spa/",
			"http://alpimarin.md/category/moldova/",
			"http://alpimarin.md/category/kids/"
	);

	private final Map<ContactDetails, String> contacts = new HashMap<ContactDetails, String>() {{
		put(ADDRESS, "Республика Молдова, г. Кишинэу, площадь К. Негруцци 2, офис 412");
		put(WORKTIME, "Пн-Пт 10.00-18.00, Сб 10.00 - 14.00 ");
		put(PHONE, "079109420, 079585565, 079589420");
		put(MAIL, "alpimarin@gmail.com");
	}};


	public OperatorAlpimarin() {
		super("Alpimarin-Tur", "http://alpimarin.md/");
	}

	public Map<ContactDetails, String> getContacts() {
		return contacts;
	}

	public List<Tour> parseWithParameters(List<Destination> destinations, Integer minPrice, Integer maxPrice) {
		List<Tour> matchedTours = new ArrayList<Tour>();

		for (String dest : allToursLinks) {
			Document doc;

			try {
				doc = Jsoup.connect(dest).userAgent("Mozilla").get();
			} catch (IOException e) {
				continue; //todo: handle and work with
			}

			Elements toursList = doc.select("div[class*=\"one_third\"]"); //todo: check if correct

			for (Element tourElement : toursList) {
				if (tourElement.getElementsByClass("tour_country").isEmpty()) continue;
				Tour tour = new Tour();
				Integer elementPrice = Integer.parseInt(tourElement.getElementsByClass("tour_price").text().replaceAll("\\D", ""));
				if (elementPrice < minPrice || elementPrice > maxPrice) continue; // continue because tour is not in price diapason
				String destinationElement = tourElement.getElementsByClass("tour_country").text();

				Optional<Destination> destination = destinations
						.stream()
						.filter(destinationEnumElement -> destinationEnumElement.getRuDescription().contains(destinationElement))
						.findAny();

				if (!destination.isPresent()) continue; // continue destination didn't match those from transfered parameters

				tour.setPrice(elementPrice);
				tour.setDestination(destination.get());
				String periodElement = tourElement.getElementsByClass("tour_days").text();
				Pattern p = Pattern.compile("\\d.д[не][ен][йь]");
				Matcher m = p.matcher(periodElement);
				if (m.find()) {
					String test = m.group(0).replaceAll("\\D", "");
					tour.setPeriodInDays(Integer.parseInt(test));
				} else tour.setPeriodInDays(-1); //todo: add user friendly description


				tour.setSummary(tourElement.getElementsByClass("thumb_title").get(0).getElementsByTag("h3").text());
				tour.setDirectLink(tourElement.getElementsByTag("a").attr("href"));

				if (matchedTours.stream().noneMatch(t -> t.getDirectLink().equals(tour.getDirectLink())))
					matchedTours.add(tour);
			}
		}

		return matchedTours;
	}
}
