package TourParser;

public enum ContactDetails {
    ADDRESS("Адресс:"),
    WORKTIME("График работы:"),
    PHONE("Телефон:"),
    MAIL("E-mail:");

    private String contactDetainField;

    ContactDetails(final String contactDetainField){
        this.contactDetainField = contactDetainField;
    }

    public String getContactDetainField() {
        return contactDetainField;
    }
}
