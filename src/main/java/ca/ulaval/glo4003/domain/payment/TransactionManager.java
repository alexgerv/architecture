package ca.ulaval.glo4003.domain.payment;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.service.TransactionService;

public class TransactionManager {
    public long processTransaction(long creditCardNumber, String creditCardType, Match match, int quantity, String sectionName, TransactionService transactionService) throws InvalidCreditCardException {
        CreditCard creditCard = createCreditCard(creditCardNumber, creditCardType);
        match.buyTickets(sectionName, quantity);
        float purchaseTotal = quantity*match.getSectionByName(sectionName).getPrice();
        long transactionID = transactionService.processPayment(creditCard, purchaseTotal);
        return transactionID;
    }

    private CreditCard createCreditCard(long creditCardNumber, String creditCardType) throws InvalidCreditCardException {
        switch(creditCardType) {
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
