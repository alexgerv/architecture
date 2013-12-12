package ca.ulaval.glo4003.domain.payment;

public interface IMailSender {

    public void sendPurchaseConfirmation(long transactionID);
}
