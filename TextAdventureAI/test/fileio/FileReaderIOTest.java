/**
 * 
 */
package fileio;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the class FileReaderIO 
 * @author sriyamadapusivasudevan
 *
 */
public class FileReaderIOTest {
	
	/** Name of test file */
	String filename = "test-files/takeBananaGameResponse";
	
	/** Name of test file */
	String filename2 = "test-files/successGameResponse";
	
	/** Name of test file */
	String filename3 = "test-files/takeStickGameResponse";
	
	/** Name of test file */
	FileReaderIO reader = new FileReaderIO();
	
	/** String representation of list of game responses */
	String list = "(contains LAB PLAYER)\n"
			+ "(contains LAB CHAIR)\n"
			+ "(contains LAB STICK)\n"
			+ "(contains LAB HOOK)\n"
			+ "(contains HOOK BANANA)\n"
			+ "not(contains LAB BANANA)";
	
	/** String representation of list of game responses */
	String list2 = "(contains LAB PLAYER)\n"
			+ "(contains LAB CHAIR)\n"
			+ "(contains LAB STICK)\n"
			+ "(contains LAB HOOK)\n"
			+ "(contains HOOK BANANA)\n"
			+ "not(contains LAB BANANA)\n"
			+ "(contains PLAYER STICK)\n"
			+ "not(contains LAB STICK)\n"
			+ "(portable STICK)\n"
			+ "(waves PLAYER STICK)\n"
			+ "(waveable STICK)\n"
			+ "(contains CHAIR PLAYER)\n"
			+ "not(contains LAB PLAYER)\n"
			+ "(climbable CHAIR)\n"
			+ "(contains LAB BANANA)\n"
			+ "not(contains HOOK BANANA)\n"
			+ "(contains LAB PLAYER)\n"
			+ "not(contains CHAIR PLAYER)\n"
			+ "(contains PLAYER BANANA)\n"
			+ "not(contains LAB BANANA)\n"
			+ "(portable BANANA)\n"
			+ "(eats PLAYER BANANA)";
	
	/** String representation of list of game responses */
	String list3 = "(contains LAB PLAYER)\n"
			+ "(contains LAB CHAIR)\n"
			+ "(contains LAB STICK)\n"
			+ "(contains LAB HOOK)\n"
			+ "(contains HOOK BANANA)\n"
			+ "not(contains LAB BANANA)\n"
			+ "(contains PLAYER STICK)\n"
			+ "not(contains LAB STICK)\n"
			+ "(portable STICK)";
	
	/** String representation of file */ 
	String file = "Success\n"
			+ "LAB contains PLAYER\n"
			+ "-\n"
			+ "Success\n"
			+ "LAB contains CHAIR\n"
			+ "LAB contains STICK\n" 
			+ "LAB contains HOOK\n"
			+ "HOOK contains BANANA\n"
			+ "-\n"
			+ "Failure\n"
			+ "LAB does not contain BANANA\n"
			+ "-\n"
			+ "Success\n"
			+ "PLAYER contains STICK\n"
			+ "LAB does not contain STICK\n"
			+ "STICK is portable";
	
	/**
	 * tests readFile()
	 */
	@Test
	public void testReadFile() {
		reader.readFile(filename);
		assertEquals(list, reader.printGameResponses());
		
		reader = new FileReaderIO();
		reader.readFile(filename3);
		assertEquals(list3, reader.printGameResponses());
	}
	
	/**
	 * tests printGameResponses()
	 */
	@Test
	public void testPrintGameResponses() {
		reader = new FileReaderIO();
		reader.readFile(filename2);
		assertEquals(list2, reader.printGameResponses());
	}
	
	/**
	 * tests printTextFile()
	 */
	@Test
	public void testPrintTextFile() {
		reader = new FileReaderIO();
		String str = reader.printTextFile("test-files/takeStickGameResponse");
		assertEquals(file, str);
	}

}
