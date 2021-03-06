package ca.ulaval.glo4003.domain.match;

public class Ticket {

    private int ID;
    private TicketAvailability availability;
    private float price;
    private AdmissionType admissionType;
    private MatchInformation matchInformation;

    public Ticket(int ID, TicketAvailability availability, MatchInformation matchInformation, float price,
            AdmissionType admissionType) {
        this.ID = ID;
        this.availability = availability;
        this.matchInformation = matchInformation;
        this.price = price;
        this.admissionType = admissionType;
    }

    public void buy() {
        assertTicketIsReserved();
        availability = TicketAvailability.SOLD;
    }

    private void assertTicketIsReserved() {
        if (!isReserved()) {
            throw new UnavailableTicketException("The ticket is not reserved.");
        }
    }

    public boolean isReserved() {
        return availability == TicketAvailability.RESERVE;
    }

    public boolean isAvailable() {
        return availability == TicketAvailability.AVAILABLE;
    }

    public void reserve() {
        availability = TicketAvailability.RESERVE;
    }

    public void free() {
        availability = TicketAvailability.AVAILABLE;
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

    public String getMatchIdentifier() {
        return matchInformation.getVenue() + "/" + matchInformation.getFormatedDate();
    }

    public boolean hasID(int ticketID) {
        return ID == ticketID;
    }

}
