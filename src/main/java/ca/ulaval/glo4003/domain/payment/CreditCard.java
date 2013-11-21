package ca.ulaval.glo4003.domain.payment;


public abstract class CreditCard {
    private long cardNumber;
    
    public CreditCard(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getCardNumber() {
        return cardNumber;
    }
}
