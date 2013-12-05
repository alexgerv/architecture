package ca.ulaval.glo4003.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ca.ulaval.glo4003.domain.match.Ticket;

public aspect TransactionLoggerAspect {

    protected pointcut ticketPurchase() :
        execution(* *..TransactionManager.processTransaction*(..));
    
    after() returning(long transactionID) : ticketPurchase() {
        List<Ticket> tickets = (List<Ticket>)thisJoinPoint.getArgs()[2];
        
        Logger logger = LogManager.getLogger("transactionLogger");
        logger.info("Transaction: " + transactionID + " - Sold tickets:" + ticketListToString(tickets) );
    }
    
    private String ticketListToString(List<Ticket> tickets) {
        String str = "";
        for (Ticket ticket: tickets) {
            str = str + "ID: " + ticket.getID() + ","; 
        }
        return str.substring(0,str.length()-1);
    }
    
}
