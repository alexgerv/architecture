package ca.ulaval.glo4003.domain.payment;

public class CreditCardFactory {

    public CreditCard create(String creditCardType, long creditCardNumber) throws InvalidCreditCardException {
        switch (creditCardType) {
        case "VASI":
            return new VasiCreditCard(creditCardNumber);
        case "MISTERCARD":
            return new MistercardCreditCard(creditCardNumber);
        case "AMERICANEXPRESSO":
            return new AmericanExpressoCreditCard(creditCardNumber);
        }

        throw new InvalidCreditCardException("The provided credit card type is invalid (Invalid provider)");
    }

}
