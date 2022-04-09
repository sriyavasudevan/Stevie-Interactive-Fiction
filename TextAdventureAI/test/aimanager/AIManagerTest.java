package aimanager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import aiio.Predicate;
import gameio.GameResponse;
/**
 * Tests the AIManager class
 * @author sriyamadapusivasudevan
 *
 */
public class AIManagerTest {

	ArrayList<Predicate> responsePredicates = new ArrayList<Predicate>();

	/** For the initial knowledge base */
	Predicate p = new Predicate("contains", "ROOM", "PLAYER", true);
	
	//The list of predicates (p1, p2, p3, p4) - look
	Predicate p1 = new Predicate("contains", "ROOM", "CHAIR", true);
	Predicate p2 = new Predicate("contains", "ROOM", "STICK", true);
	Predicate p3 = new Predicate("contains", "ROOM", "HOOK", true);
	Predicate p4 = new Predicate("contains", "HOOK", "BANANA", true);
	
	//Predicate response for the command - take banana
	Predicate p5 = new Predicate("contains", "ROOM", "BANANA", false);
	
	//Predicate responses for the command - take stick
	Predicate p6 = new Predicate("contains", "PLAYER", "STICK", true);
	Predicate p7 = new Predicate("contains", "ROOM", "STICK", false);
	Predicate p8 = new Predicate("portable", "STICK", "", true);

	//Predicate response for the command - wave stick
	Predicate p9 = new Predicate("waves", "PLAYER", "STICK", true);
	Predicate p10 = new Predicate("waveable", "STICK", "", true);
	
	//Predicate response for the command - get on chair
	Predicate p11 = new Predicate("contains", "CHAIR", "PLAYER", true);
	Predicate p12 = new Predicate("contains", "ROOM", "PLAYER", false);
	Predicate p13 = new Predicate("climbable", "CHAIR", "", true);
	
	//Various game responses used for testing
	GameResponse r, r1, r2, r3, r4, r5;

	/** The initial knowledge base that will build */
	AIManager knowledgeBase = new AIManager();
	
	//expected KB after command: look
	String expectedKB2 = "(contains ROOM PLAYER)\n(contains ROOM CHAIR)\n(contains ROOM STICK)\n(contains ROOM HOOK)"
			+ "\n(contains HOOK BANANA)";
	
	//Expected KB after command: take banana
	String expectedKB3 = expectedKB2 + "\nnot(contains ROOM BANANA)";
	
	//Expected KB after command: take stick
	String expectedKB4 = "(contains ROOM PLAYER)\n(contains ROOM CHAIR)\n" +
			"not(contains ROOM STICK)\n(contains ROOM HOOK)\n" + 
			"(contains HOOK BANANA)\nnot(contains ROOM BANANA)\n" +
			"(contains PLAYER STICK)\n(portable STICK)";
	
	//Expected KB after command: wave stick
	String expectedKB5 = expectedKB4 + "\n(waves PLAYER STICK)\n"
			+ "(waveable STICK)";
	
	//Expected KB after command: get on chair
	String expectedKB6 = "not(contains ROOM PLAYER)\n(contains ROOM CHAIR)\n" +
			"not(contains ROOM STICK)\n(contains ROOM HOOK)\n"
			+ "(contains HOOK BANANA)\nnot(contains ROOM BANANA)\n"
			+ "(contains PLAYER STICK)\n(portable STICK)\n(waves PLAYER STICK)\n"
			+ "(waveable STICK)\n(contains CHAIR PLAYER)\n(climbable CHAIR)";
	
	
	/**
	 * Tests the addToKB method
	 */
	@Test
	public void testAddToKB() {
		
		//Initial assumption, player is in the room
		responsePredicates.add(p);
		r = new GameResponse(responsePredicates, true);
		knowledgeBase.addToKB(r);
		
		assertEquals("(contains ROOM PLAYER)", knowledgeBase.printKB());
		
		//response to look
		responsePredicates.remove(0);
		
		//contains(ROOM, CHAIR)
		responsePredicates.add(p1);
		
		//contains(ROOM, STICK)
		responsePredicates.add(p2);
		
		//contains(ROOM, HOOK)
		responsePredicates.add(p3);
		
		//contains(HOOK, BANANA)
		responsePredicates.add(p4);
		
		//Game responded successfully with {p1, p2, p3, p4}
		r1 = new GameResponse(responsePredicates, true);
		
		//Adding response r1 to KB
		knowledgeBase.addToKB(r1);
		
		assertEquals(expectedKB2, knowledgeBase.printKB());
		
		//response to take banana
		
		//Flushing out the old list
		responsePredicates.remove(0);
		responsePredicates.remove(0);
		responsePredicates.remove(0);
		responsePredicates.remove(0);
		
		//not(contains(ROOM, BANANA))
		responsePredicates.add(p5);
		
		//Game responded with a failure {p5}
		r2 = new GameResponse(responsePredicates, false);
		
		//Adding response r2 to KB
		knowledgeBase.addToKB(r2);
		
		assertEquals(expectedKB3, knowledgeBase.printKB());
		
		//response to take stick
		
		//Flushing out the old list
		responsePredicates.remove(0);
		
		//contains(PLAYER, STICK)
		responsePredicates.add(p6);
		
		//not(contains(ROOM, STICK))
		responsePredicates.add(p7);
		
		//portable(STICK)
		responsePredicates.add(p8);
		
		//Game responded successfully with {p6, p7, p8}
		r3 = new GameResponse(responsePredicates, true);
		
		//Adding response r3 to KB
		knowledgeBase.addToKB(r3);
		
		assertEquals(expectedKB4, knowledgeBase.printKB());
		
		//response to wave stick
		
		//Flushing out the old list
		responsePredicates.remove(0);
		responsePredicates.remove(0);
		responsePredicates.remove(0);
		
		//waves(PLAYER, STICK)
		responsePredicates.add(p9);
		
		//waveable(STICK)
		responsePredicates.add(p10);
		
		//Game responded successfully with {p9, p10}
		r4 = new GameResponse(responsePredicates, true);
		
		//Adding response r4 to KB
		knowledgeBase.addToKB(r4);
		
		assertEquals(expectedKB5, knowledgeBase.printKB());
		
		//response to get on chair
		
		//Flushing out the old list
		responsePredicates.remove(0);
		responsePredicates.remove(0);
		
		//contains(CHAIR, PLAYER)
		responsePredicates.add(p11);
		
		//not(contains(ROOM, PLAYER))
		responsePredicates.add(p12);
		
		//climbable(CHAIR)
		responsePredicates.add(p13);
		
		//Game responded successfully with {p11, p12, p13}
		r5 = new GameResponse(responsePredicates, true);
		
		//Adding response r5 to KB
		knowledgeBase.addToKB(r5);
		
		assertEquals(expectedKB6, knowledgeBase.printKB());
		
	}

	/**
	 * Tests obtainedEndPredicate method
	 */
	@Test
	public void testObtainedEndPredicate() {
		//Initial assumption, player is in the room
		responsePredicates.add(p);
		r = new GameResponse(responsePredicates, true);
		knowledgeBase.addToKB(r);
		
		assertFalse(knowledgeBase.obtainedEndPredicate());
	}
}
