package ca.ulaval.glo4003.service.transaction;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import ca.ulaval.glo4003.domain.match.Ticket;

@Aspect
public class TransactionLoggerAspect {

    @Pointcut("execution(* *..TransactionManager.processTransaction*(..))")
    private void ticketPurchase() {}

    @SuppressWarnings("unchecked")
    @AfterReturning(pointcut = "execution(* *..TransactionManager.processTransaction*(..))",
                    returning = "transactionID")
    public void logTransaction(JoinPoint joinpoint, long transactionID) {
        List<Ticket> tickets = (List<Ticket>) joinpoint.getArgs()[2];

        Logger logger = LogManager.getLogger("transactionLogger");
        logger.info("Transaction: " + transactionID + " - Sold tickets:" + ticketListToString(tickets));
    }

    private String ticketListToString(List<Ticket> tickets) {
        String str = "";
        for (Ticket ticket : tickets) {
            str = str + "ID: " + ticket.getID() + ",";
        }
        return str.substring(0, str.length() - 1);
    }
}
