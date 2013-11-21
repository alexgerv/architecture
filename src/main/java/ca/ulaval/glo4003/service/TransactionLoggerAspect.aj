package ca.ulaval.glo4003.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

import ca.ulaval.glo4003.domain.match.Match;

public aspect TransactionLoggerAspect {

    protected pointcut ticketPurchase() :
        execution(* *..TransactionManager.processTransaction*(..));
    
    after() returning(long transactionID) : ticketPurchase() {
        Match match = (Match)thisJoinPoint.getArgs()[2];
        String section = (String)thisJoinPoint.getArgs()[4];
        int quantity = (int)thisJoinPoint.getArgs()[3];
        
        Logger logger = LogManager.getLogger("transactionLogger");
        logger.info("Transaction: " + transactionID + " - Sold " + quantity + " tickets in section " + section + " for match: " + match.toString());
    }
	
}
