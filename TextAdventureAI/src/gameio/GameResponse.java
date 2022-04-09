/**
 * 
 */
package gameio;

import java.util.ArrayList;

import aiio.Predicate;

/**
 * Indicates the game response in predicate format.
 * This would be the input into the AI manager, which
 * would determine our Knowledge Base State.
 * @author sriyamadapusivasudevan
 */
public class GameResponse {

	/** List of predicates that the game responded with */ 
	ArrayList<Predicate> listOfPredicates;
	
	/** to keep track if the player command was successful or not */
	boolean isSuccess;
	
	/**
	 * A simple constructor to initialize the fields
	 */
	private void init() {
		listOfPredicates = new ArrayList<Predicate>();
		isSuccess = false;
	}
	
	/**
	 * A parameterized constructor that sets the fields to
	 * the passed in parameters
	 * @param list - list of predicates the game responded with
	 * @param isSuccess - was the command issued a success or not
	 */
	public GameResponse(ArrayList<Predicate> list, boolean isSuccess) {
		init();
		for(int i = 0; i < list.size(); i++) {
			listOfPredicates.add(list.get(i));
		}
		
		this.isSuccess = isSuccess;
	}

	/**
	 * Returns the list of predicates that the game responded
	 * with.
	 * @return the listOfPredicates
	 */
	public ArrayList<Predicate> getListOfPredicates() {
		return listOfPredicates;
	}

	/**
	 * Returns true if the command the player issued was true.
	 * @return the isSuccess
	 */
	public boolean getSuccessStatus() {
		return isSuccess;
	}

	/**
	 * Sets the response: success or failure
	 * @param isSuccess the success or failure of the response
	 */
	public void setSucessState(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * Returns a string representation of the list
	 * of predicates.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i;
		for(i = 0; i < listOfPredicates.size() - 1; i++) {
			sb.append(listOfPredicates.get(i).toString());
			sb.append("\n");
		}
		sb.append(listOfPredicates.get(i).toString());
		return sb.toString();
	}
	
	/**
	 * String representation and the status of the game response
	 * @return string
	 */
	public String statusToString() {
		if(isSuccess == true) {
			return "SUCCESS!";
		} else {
			return "FAILURE!";
		}
	}
}
