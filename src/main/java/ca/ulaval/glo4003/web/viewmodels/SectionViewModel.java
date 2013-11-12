package ca.ulaval.glo4003.web.viewmodels;

import ca.ulaval.glo4003.domain.match.AdmissionType;
import ca.ulaval.glo4003.domain.match.Sex;

public class SectionViewModel {

    private String sport;
    private String venue;
    private String section;
    private String date;
    private String homeTeam;
    private String visitorTeam;
    private Sex sex;
    private String name;
    private int availableTickets;
    private float price;
    private AdmissionType admissionType;

    public String getSport() {
        return sport;
    }
    
    public void setSport(String sport) {
        this.sport = sport;
    }
    
    public String getVenue(){
        return venue;
    }
    
    public void setVenue(String venue){
        this.venue = venue;
    }

    public AdmissionType getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(AdmissionType admissionType) {
        this.admissionType = admissionType;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

}
