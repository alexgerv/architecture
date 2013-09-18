package ca.ulaval.glo4003.model;

import java.util.Date;

public class MatchBuilder {
	private Match match = new Match();
	
	public Match build() {
		return match;
	}
	
	public MatchBuilder setSport(String sport) {
		match.setSport(sport);
		return this;
	}
	
	public MatchBuilder setDate(Date date) {
		match.setDate(date);
		return this;
	}
	
	public MatchBuilder setLocation(String location) {
		match.setLocation(location);
		return this;
	}
	
	public MatchBuilder setHomeTeam(String homeTeam) {
		match.setHomeTeam(homeTeam);
		return this;
	}
	
	public MatchBuilder setVisitorTeam(String visitorTeam) {
		match.setVisitorTeam(visitorTeam);
		return this;
	}
}
