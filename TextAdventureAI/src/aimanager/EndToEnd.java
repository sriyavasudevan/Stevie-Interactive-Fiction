package aimanager;

import java.util.ArrayList;

import aiio.Predicate;
import gameio.GameResponse;

/**
 * Takes an input of list of game responses and
 * gives output as the final knowledge base
 * @author sriyamadapusivasudevan
 */
public class EndToEnd {

	/** */
	ArrayList<Predicate> kB;
	
	/**
	 * Simple constructor initializes the fields
	 */
	public EndToEnd() {
		kB = new ArrayList<Predicate>();
	}
	
	/**
	 * Takes input as list of game responses and 
	 * gives output as the final Knowledge Base
	 * @param listOfGameResponses - list of game responses
	 * @return string representation of KB
	 */
	public void takeListOfGameResponses(ArrayList<GameResponse> listOfGameResponses) {
		
		int flag = 0;
		ArrayList<Predicate> r = new ArrayList<Predicate>();
		for(int k = 0; k < listOfGameResponses.size(); k++) {
			r = listOfGameResponses.get(k).getListOfPredicates();
			flag = 0;
			//int pred = 0;
			int track = 0;
			
			if(kB.size() == 0) {
				for(int i = 0; i < r.size(); i++) {
					Predicate pred = new Predicate(r.get(i).getName(), r.get(i).getParameter1(), r.get(i).getParameter2(), r.get(i).getStatus());
					kB.add(pred);
				}
				//return;
			} else {
				for(int i = 0; i < r.size(); i++) {
					flag = 0;
					track = 0;
					for(int j = 0; j < kB.size(); j++) {
						if(kB.get(j).equals(r.get(i))) {
							continue;
						} else {
							if(kB.get(j).nameAndParEquals(r.get(i)) && 
									(kB.get(j).getStatus() != r.get(i).getStatus())) { 
								boolean stat = r.get(i).getStatus();
								kB.get(j).setStatus(stat);
								track = 1;
								flag++;
							} else { 
								flag++;
							}
						}
					}
					if(flag == kB.size() && track == 0) {
						Predicate pred = new Predicate(r.get(i).getName(), r.get(i).getParameter1(), r.get(i).getParameter2(), r.get(i).getStatus());
						kB.add(pred);
					}
				}
			}
		}
		flag = 2;
	}
	
	/**
	 * Prints out the current knowledge base.
	 * @return string representation
	 */
	public String printEntireKB() {
		StringBuffer sb = new StringBuffer();
		int i;
		for(i = 0; i < kB.size() - 1; i++) {
			sb.append(kB.get(i).toString());
			sb.append("\n");
		}
		sb.append(kB.get(i).toString());
		return sb.toString();
	}
	
	/**
	 *  ** SPECIFIC to the GAME **
	 * This is to indicate that the KB has enough knowledge
	 * to eat the banana
	 * @return true if the goal has been reached.
	 */
	public boolean obtainedEndPredicate() {
		int i = 0;
		while( i< kB.size()) {
			if(kB.get(i).equals(new Predicate("contains", "PLAYER", "BANANA", true))) {
				return true;
			}
			i++;
		}
		return false;
	}
}
