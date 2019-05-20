package TourParser;

import java.util.List;
import java.util.Map;

public abstract class OperatorElement {
    private String name;
    private String mainUrl;

    OperatorElement() {
        name = "unknown";
        mainUrl = "unknown";
    }

    OperatorElement(String name, String url) {
        this.name = name;
        this.mainUrl = url;
    }

    public String getName() {return  name;};

    public void setName(String name) {
        this.name = name;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public String getMainUrl() { return mainUrl; };

    public abstract Map<ContactDetails, String> getContacts();

    public abstract List<Tour> parseWithParameters(List<Destination> destinations, Integer minPrice, Integer maxPrice);
}