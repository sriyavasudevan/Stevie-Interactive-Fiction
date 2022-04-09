package aiio;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * This class tests the Predicate Class
 * @author sriyamadapusivasudevan
 *
 */
public class PredicateTest {

	/** A test predicate object */
	Predicate p, testP, testP2;
	
	/**
	 * Tests the constructor of Predicate
	 */
	@Test
	public void testPredicate() {
		p = new Predicate("contains", "ROOM", "PLAYER", true);
		assertEquals("contains", p.getName());
		assertEquals("ROOM", p.getParameter1());
		assertEquals("PLAYER", p.getParameter2());
		assertTrue(p.getStatus());
	}
	
	/**
	 * Tests the getName() method
	 */
	@Test
	public void testGetName() {
		p = new Predicate("contains", "ROOM", "PLAYER", true);
		assertEquals("contains", p.getName());
	}

	/**
	 * Tests the getParameter1() method
	 */
	@Test
	public void testGetParameter1() {
		p = new Predicate("contains", "ROOM", "PLAYER", true);
		assertEquals("ROOM", p.getParameter1());
	}
	
	/**
	 * Tests the getParameter2() method
	 */
	@Test
	public void testGetParameter2() {
		p = new Predicate("contains", "ROOM", "PLAYER", true);
		assertEquals("PLAYER", p.getParameter2());
	}
	
	/**
	 * Tests getStatus() method
	 */
	@Test
	public void testGetStatus() {
		p = new Predicate("contains", "ROOM", "PLAYER", true);
		assertTrue(p.getStatus());
	}
	
	/**
	 * Tests the equals method
	 */
	@Test
	public void testEquals() {
		p = new Predicate("contains", "ROOM", "PLAYER", true);
		testP = new Predicate("contains", "ROOM", "PLAYER", true);
		testP2 = new Predicate("contains", "ROOM", "PLAYER", false);
		assertTrue(testP.equals(p));
		assertFalse(testP2.equals(p));
	}
	
	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		p = new Predicate("contains", "ROOM", "PLAYER", true);
		assertEquals("(contains ROOM PLAYER)", p.toString());
		
		testP = new Predicate("portable", "STICK", "", false);
		assertEquals("not(portable STICK)", testP.toString());
	}
}