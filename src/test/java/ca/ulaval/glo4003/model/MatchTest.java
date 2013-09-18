package ca.ulaval.glo4003.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;

public class MatchTest {
	
	@Mock
	private Ticket aTicket;
	private Match aMatch = new Match();
	
	@Test
	public void canAddTicket() {
		aMatch.addTicket(aTicket);
		
		assertEquals(aMatch.totalTickets(), 1);
	}
	
	@Test
	public void canRemoveATicket() {
		aMatch.addTicket(aTicket);
		aMatch.removeTicket(aTicket);
		
		assertEquals(aMatch.totalTickets(), 0);
	}
	
	@Test
	public void afterAddingATicketItIsAvailable() {
		aMatch.addTicket(aTicket);
		
		assertTrue(aMatch.isTicketAvailable(aTicket));
	}
	
	@Test
	public void afterRemovingATicketItIsNotAvailable() {
		aMatch.addTicket(aTicket);
		aMatch.removeTicket(aTicket);
		
		assertFalse(aMatch.isTicketAvailable(aTicket));
	}
	
}
