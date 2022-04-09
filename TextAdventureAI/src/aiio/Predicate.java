/**
 * 
 */
package aiio;

/**
 * This class represents a predicate which contains:
 * - name
 * - either one or two parameters
 *
 * @author sriyamadapusivasudevan
 */
public class Predicate {
	
	/** name of the predicate */
	String name;
	
	/** name of parameter 1 */
	String parameter1;
	
	/** name of parameter 2 */
	String parameter2;
	
	/** status of the predicate, whether its true or not */
	boolean status;

	/**
	 * Instantiates the predicate with the 2 parameters. If 
	 * one of the parameter is empty, "" is passed to it.
	 * @param name - name of the predicate
	 * @param parameter1 - first parameter of the predicate
	 * @param parameter2 - second parameter of the predicate
	 * @param status - false if the predicate is false
	 */
	public Predicate(String name, String parameter1, String parameter2, boolean status) {
		this.name = name;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
		this.status = status;
	}
	
	/**
	 * Returns the name of the predicate
	 * @return - name of the predicate
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the first parameter of predicate
	 * @return - first parameter of predicate
	 */
	public String getParameter1() {
		return parameter1;
	}
	
	/**
	 * Returns the second parameter of predicate
	 * @return - second parameter of predicate
	 */
	public String getParameter2() {
		return parameter2;
	}
	
	/**
	 * Returns the status of the predicate
	 * @return - status of predicate
	 */
	public boolean getStatus() {
		return status;
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * Checks the equality of two predicates
	 * @param p - predicate object
	 * @return returns true if equal, else, false.
	 */
	public boolean equals(Predicate p) {
		if(p.getName().equals(name) && p.getParameter1().equals(parameter1)
		    && p.getParameter2().equals(parameter2) && p.getStatus() == status) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public boolean nameAndParEquals(Predicate p) {
		if(p.getName().equals(name) && p.getParameter1().equals(parameter1)
			    && p.getParameter2().equals(parameter2)) {
				return true;
			} else {
				return false;
			}
	}
	
	/**
	 * To string method
	 * @return String representation
	 */
	public String toString() {
		if(status == true) {
			if(!parameter2.equals(""))
				return "(" + name + " " + parameter1 + " " + parameter2 + ")"; 
			else
				return "(" + name + " " + parameter1 + ")"; 
		} else {
			if(!parameter2.equals(""))
				return "not(" + name + " " + parameter1 + " " + parameter2 + ")"; 
			else
				return "not(" + name + " " + parameter1 + ")";
		}
	}
	
	
	
}
