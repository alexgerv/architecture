package ca.ulaval.glo4003.web.viewmodels;

public class CreditCardViewModel {

    private long number;
    private String type;

    public long getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = Long.valueOf(number);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
