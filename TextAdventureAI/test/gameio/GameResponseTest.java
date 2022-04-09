package gameio;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import aiio.Predicate;
/**
 * This class tests GameResponse class
 * @author sriyamadapusivasudevan
 */
public class GameResponseTest {

	/** The predicates objects for testing */
	Predicate p1, p2, p3;
	
	/** List of predicates for testing */
	ArrayList<Predicate> list;
	
	/** A game repsonse object */
	GameResponse r;
	
	String expected = "(contains PLAYER STICK)\nnot(contains ROOM STICK)\n(portable STICK)";
	
	/**
	 * Tests the simple constructor
	 */
	@Test
	public void testGameResponse() {
		list = new ArrayList<Predicate>();
		
		p1 = new Predicate("contains", "PLAYER", "STICK", true);
		p2 = new Predicate("contains", "ROOM", "STICK", false);
		p3 = new Predicate("portable", "STICK", "", true);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		r = new GameResponse(list, true);
		
		assertTrue(r.getSuccessStatus());
		
		for(int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i), r.getListOfPredicates().get(i));
		}
	}

	/**
	 * Tests the getListOfPredicates() method
	 */
	@Test
	public void testGetListOfPredicates() {
		list = new ArrayList<Predicate>();
		
		p1 = new Predicate("contains", "PLAYER", "STICK", true);
		p2 = new Predicate("contains", "ROOM", "STICK", false);
		p3 = new Predicate("portable", "STICK", "", true);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		r = new GameResponse(list, true);
		
		for(int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i), r.getListOfPredicates().get(i));
		}
	}
	
	/**
	 * Tests the getSuccessState() method
	 */
	@Test
	public void testGetSuccessState() {
		list = new ArrayList<Predicate>();
		
		p1 = new Predicate("contains", "PLAYER", "STICK", true);
		p2 = new Predicate("contains", "ROOM", "STICK", false);
		p3 = new Predicate("portable", "STICK", "", true);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		r = new GameResponse(list, true);
		
		assertTrue(r.getSuccessStatus());
	}
	
	/**
	 * Tests toString() method
	 */
	@Test
	public void testToString() {
		list = new ArrayList<Predicate>();
		
		p1 = new Predicate("contains", "PLAYER", "STICK", true);
		p2 = new Predicate("contains", "ROOM", "STICK", false);
		p3 = new Predicate("portable", "STICK", "", true);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		r = new GameResponse(list, true);
		assertEquals(expected, r.toString());
	}
	
	/**
	 * Tests setSuccessState()
	 */
	@Test
	public void testSetSuccessState() {
		list = new ArrayList<Predicate>();
		p1 = new Predicate("contains", "PLAYER", "STICK", true);
		list.add(p1);
		r = new GameResponse(list, false);
		assertFalse(r.getSuccessStatus());
		r.setSucessState(true);
		assertTrue(r.getSuccessStatus());
	}
	
	/**
	 * Tests statusToString() method
	 */
	@Test
	public void testStatusToString() {
		list = new ArrayList<Predicate>();
		p1 = new Predicate("contains", "PLAYER", "STICK", true);
		list.add(p1);
		r = new GameResponse(list, false);
		assertEquals("FAILURE!", r.statusToString());
		
		r = new GameResponse(list, true);
		assertEquals("SUCCESS!", r.statusToString());
	}
}
