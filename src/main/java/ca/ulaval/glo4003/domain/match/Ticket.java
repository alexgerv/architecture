package ca.ulaval.glo4003.domain.match;

public class Ticket {

    private int ID;
    private boolean available;
    private float price;
    private AdmissionType admissionType;

    private MatchInformation matchInformation;

    public Ticket(int ID, boolean available, MatchInformation matchInformation, float price, AdmissionType admissionType) {
        this.ID = ID;
        this.available = available;
        this.matchInformation = matchInformation;
        this.price = price;
        this.admissionType = admissionType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void buy() {
        if (!isAvailable()) {
            throw new UnavailableTicketException("The ticket is unavailable.");
        }
        available = false;
    }

    public int getID() {
        return ID;
    }

    public String getSport() {
        return matchInformation.getSport();
    }

    public String getDate() {
        return matchInformation.getFormatedDate();
    }

    public String getHomeTeam() {
        return matchInformation.getHomeTeam();
    }

    public String getVisitorTeam() {
        return matchInformation.getVisitorTeam();
    }

    public Sex getSex() {
        return matchInformation.getSex();
    }

    public float getPrice() {
        return price;
    }

    public String getVenue() {
        return matchInformation.getVenue();
    }

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

}
