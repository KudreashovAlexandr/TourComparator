package TourParser;

public enum Destination {
//    SEA("Море"),
//    MOUNTAIN("Горы"),
    MOLDOVA("Молдова"),
    UKRAINE("Украина"),
    ROMANIA("Румыния"),
    RUSSIA("Россия"),
    BULGARIA("Болгария"),
    TURKEY("Турция"),
    CZECH("Чехия"),
    MONTENEGRO("Черногория"),
    SPAIN("Испания"),
    GREECE("Греция"),
    BELGIUM("Бельгия"),
    GERMANY("Германия"),
    ITALY("Италия"),
    FRANCE("Франция"),
    AUSTRIA("Австрия"),
    EGYPT("Египет"),
    USA("США");
//    EXCURSION("Экскурсии");

    private String ruDescription;

    Destination(final String ruDescription){
        this.ruDescription = ruDescription;
    }

    public String getRuDescription() {
        return ruDescription;
    }
}