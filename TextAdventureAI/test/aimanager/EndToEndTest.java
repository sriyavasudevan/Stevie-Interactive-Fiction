package aimanager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import aiio.Predicate;
import fileio.FileReaderIO;
import gameio.GameResponse;

/**
 * Tests the class EndToEnd
 * @author sriyamadapusivasudevan
 *
 */
public class EndToEndTest {

	/** Various predicates used for testing */
	ArrayList<Predicate> listOfResponse0Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse1Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse2Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse3Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse4Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse5Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse6Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse7Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse8Predicates = new ArrayList<Predicate>();
	ArrayList<Predicate> listOfResponse9Predicates = new ArrayList<Predicate>();

	/** List of game responses */
	ArrayList<GameResponse> listOfGameResponses = new ArrayList<GameResponse>();

	/** For the initial knowledge base */
	Predicate p = new Predicate("contains", "LAB", "PLAYER", true);
	
	//The list of predicates (p1, p2, p3, p4) - look
	Predicate p1 = new Predicate("contains", "LAB", "CHAIR", true);
	Predicate p2 = new Predicate("contains", "LAB", "STICK", true);
	Predicate p3 = new Predicate("contains", "LAB", "HOOK", true);
	Predicate p4 = new Predicate("contains", "HOOK", "BANANA", true);
	
	//Predicate response for the command - take banana
	Predicate p5 = new Predicate("contains", "LAB", "BANANA", false);
	
	//Predicate responses for the command - take stick
	Predicate p6 = new Predicate("contains", "PLAYER", "STICK", true);
	Predicate p7 = new Predicate("contains", "LAB", "STICK", false);
	Predicate p8 = new Predicate("portable", "STICK", "", true);

	//Predicate response for the command - wave stick
	Predicate p9 = new Predicate("waves", "PLAYER", "STICK", true);
	Predicate p10 = new Predicate("waveable", "STICK", "", true);
	
	//Predicate response for the command - get on chair
	Predicate p11 = new Predicate("contains", "CHAIR", "PLAYER", true);
	Predicate p12 = new Predicate("contains", "LAB", "PLAYER", false);
	Predicate p13 = new Predicate("climbable", "CHAIR", "", true);
	
	//Predicate response for the command - wave stick
	Predicate p14 = new Predicate("contains", "LAB", "BANANA", true);
	Predicate p15 = new Predicate("contains", "HOOK", "BANANA", false);
	
	//Predicate response for the command - get off chair	
	Predicate p16 = new Predicate("contains", "LAB", "PLAYER", true);
	Predicate p17 = new Predicate("contains", "CHAIR", "PLAYER", false);

	//Predicate response for the command - take banana
	Predicate p18 = new Predicate("contains", "PLAYER", "BANANA", true);
	Predicate p19 = new Predicate("contains", "LAB", "BANANA", false);
	//Predicate p20 = new Predicate("edible", "BANANA", "", true);
	Predicate p21 = new Predicate("portable", "BANANA", "", true);
	
	//Predicate response for the command - eat banana
	Predicate p22 = new Predicate("eats", "PLAYER", "BANANA", true);
	
	//Various game responses used for testing
	GameResponse r, r1, r2, r3, r4, r5, r6, r7, r8, r9;

	/** The initial knowledge base that will build */
	EndToEnd kB = new EndToEnd();
	
	//Entire KB at the end
	String expected = "(contains LAB PLAYER)\n"
			+ "(contains LAB CHAIR)\n"
			+ "not(contains LAB STICK)\n"
			+ "(contains LAB HOOK)\n"
			+ "not(contains HOOK BANANA)\n"
			+ "not(contains LAB BANANA)\n" + 
			"(contains PLAYER STICK)\n" +
			"(portable STICK)\n" +
			"(waves PLAYER STICK)\n" +
			"(waveable STICK)\n" +
			"not(contains CHAIR PLAYER)\n" +
			"(climbable CHAIR)\n" +
			"(contains PLAYER BANANA)\n" +
			"(portable BANANA)\n" +
			"(eats PLAYER BANANA)";
	
	/** 
	 * Tests takeListOfGameResponses() methof
	 */
	@Test
	public void testTakeListOfGameResponses() {
		listOfResponse0Predicates.add(p);
		
		listOfResponse1Predicates.add(p1);
		listOfResponse1Predicates.add(p2);
		listOfResponse1Predicates.add(p3);
		listOfResponse1Predicates.add(p4);
		
		listOfResponse2Predicates.add(p5);
		
		listOfResponse3Predicates.add(p6);
		listOfResponse3Predicates.add(p7);
		listOfResponse3Predicates.add(p8);
		
		listOfResponse4Predicates.add(p9);
		listOfResponse4Predicates.add(p10);
		
		listOfResponse5Predicates.add(p11);
		listOfResponse5Predicates.add(p12);
		listOfResponse5Predicates.add(p13);
		
		//p5.setStatus(true);
		listOfResponse6Predicates.add(p14);
		listOfResponse6Predicates.add(p15);
		
		listOfResponse7Predicates.add(p16);
		listOfResponse7Predicates.add(p17);

		
		listOfResponse8Predicates.add(p18);
		listOfResponse8Predicates.add(p19);
		//listOfResponse7Predicates.add(p20);
		listOfResponse8Predicates.add(p21);

		listOfResponse9Predicates.add(p22);

		
		r = new GameResponse(listOfResponse0Predicates, true);
		r1 = new GameResponse(listOfResponse1Predicates, true);
		r2 = new GameResponse(listOfResponse2Predicates, false);
		r3 = new GameResponse(listOfResponse3Predicates, true);
		r4 = new GameResponse(listOfResponse4Predicates, true);
		r5 = new GameResponse(listOfResponse5Predicates, true);
		r6 = new GameResponse(listOfResponse6Predicates, true);
		r7 = new GameResponse(listOfResponse7Predicates, true);
		r8 = new GameResponse(listOfResponse8Predicates, true);
		r9 = new GameResponse(listOfResponse9Predicates, true);

		listOfGameResponses.add(r);
		listOfGameResponses.add(r1);
		listOfGameResponses.add(r2);
		listOfGameResponses.add(r3);
		listOfGameResponses.add(r4);
		listOfGameResponses.add(r5);
		listOfGameResponses.add(r6);
		listOfGameResponses.add(r7);
		listOfGameResponses.add(r8);
		listOfGameResponses.add(r9);
		
		kB.takeListOfGameResponses(listOfGameResponses);
		String KB = kB.printEntireKB();
		assertEquals(expected, KB);
	}
	
	/**
	 * Takes the input as file and checks if it can print the 
	 * same KB
	 */
	@Test
	public void testUseFileAsInput() {
		FileReaderIO fr = new FileReaderIO();
		ArrayList<GameResponse> listOfGameResp = fr.readFile("test-files/successGameResponse");
		kB.takeListOfGameResponses(listOfGameResp);
		String knowledgeBase = kB.printEntireKB();
		
		assertEquals(expected, knowledgeBase);
	}

}
