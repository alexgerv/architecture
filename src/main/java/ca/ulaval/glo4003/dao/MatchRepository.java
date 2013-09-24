package ca.ulaval.glo4003.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.model.Match;

@Repository
@Singleton
public class MatchRepository {
	private AtomicInteger nextId = new AtomicInteger(0);
	private Map<Integer, Match> entries = new HashMap<Integer, Match>();
	
	public Map<Integer, Match> getAll() {
		return entries;
	}

	public Match getById(int id) {
		if(entries.containsKey(id)) {
			return entries.get(id);
		}
		throw new RuntimeException(); //TODO Create custom Exception
	}

	public int add(Match entry) {
		int id = nextId.incrementAndGet();
		entries.put(id, entry);
		return id;
	}
	
	public void update(int id, Match entry) {
		entries.put(id, entry);
	}
	
	public void delete(int id) {
		entries.remove(id);
	}
}
