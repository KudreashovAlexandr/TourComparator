package TourParser;

import java.awt.*;

public class Tour {
    private Destination destination;
    private Integer price;
    private Integer periodInDays;
    private String summary;
    private String directLink;
    private Image image;

    public Tour(){};

    public Tour(Destination destination, Integer price, Integer periodInDays, String summary, String directLink, Image image) {
        this.destination = destination;
        this.price = price;
        this.periodInDays = periodInDays;
        this.summary = summary;
        this.directLink = directLink;
        this.image = image;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPeriodInDays() {
        return periodInDays;
    }

    public void setPeriodInDays(Integer periodInDays) {
        this.periodInDays = periodInDays;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDirectLink() {
        return directLink;
    }

    public void setDirectLink(String directLink) {
        this.directLink = directLink;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}