package ca.ulaval.glo4003.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ca.ulaval.glo4003.model.Match;

public aspect TransactionLoggerAspect {

    protected pointcut ticketPurchase() :
        execution(* *..Match.buy*(..));
    
    after() returning : ticketPurchase() {
        Match match = (Match)thisJoinPoint.getThis();
        String section = (String)thisJoinPoint.getArgs()[0];
        int quantity = (int)thisJoinPoint.getArgs()[1];
        
        Logger logger = LogManager.getLogger("transactionLogger");
        logger.info("Sold " + quantity + " tickets in section " + section + " for match: " + match.toString());
    }
	
}
