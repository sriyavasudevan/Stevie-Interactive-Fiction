/**
 * 
 */
package fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import aiio.Predicate;
import gameio.GameResponse;

/**
 * @author sriyamadapusivasudevan
 *
 */
public class FileReaderIO {

	/** Predicate to be read in */
	Predicate p;

	/** Game response to be generated */
	GameResponse r;

	/** A list of predicates */
	ArrayList<Predicate> listOfPred; 

	/** A list of game responses */
	ArrayList<GameResponse> listOfGameResponses; 

	/**
	 * constructor 
	 */
	public FileReaderIO() {
		p = new Predicate("", "", "", false);
		listOfPred =new ArrayList<Predicate>();
		r = new GameResponse(listOfPred, false);
		
		listOfGameResponses = new ArrayList<GameResponse>();
	}
	
	/**
	 * Read files and makes a list of game responses
	 * @param filename name of the file
	 * @return the list of game responses
	 */
	public ArrayList<GameResponse> readFile(String filename) {

		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			//line = line.substring(1);
			String response = "";
			while(line != null) {
				response = "";
				while(line != null && !line.equals("-")) {
					response += line + "\n";
					line = br.readLine();
				}
				/*if(line == null) {
					break;
				}*/
				String[] arr = response.split("\n");
				String stringStatus = arr[0];
				boolean status = false;
				if(stringStatus.equals("Failure")) {
					status = false;
				} else if(stringStatus.equals("Success")) {
					status = true;
				} else {
					//invalid format
					return null;
				}
				
				for(int i = 1; i < arr.length; i++) {
					String[] res = arr[i].split(" ");
					if(res.length == 3) {
						
						if(res[1].equals("is")) {
							p = new Predicate(res[2], res[0], "", true);
						} else {
							p = new Predicate(res[1], res[0], res[2], true);
						}
					} else if(res.length == 5) {
						
						p = new Predicate(res[3] + "s", res[0], res[4], false);
					} else if(res.length == 4) {
						p = new Predicate(res[3], res[0], "", false);
					}
					
					listOfPred.add(p);
				}
				
				r = new GameResponse(listOfPred, status);
				
				listOfGameResponses.add(r);
				
				listOfPred = new ArrayList<Predicate>();
				
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			//File is not found
			return null;
		} catch (IOException e) {
			//IO
			return null;
		}
		
		return listOfGameResponses;
	}
	
	/**
	 * String representation of list of game responses
	 * @return game responses
	 */
	public String printGameResponses() {
		StringBuffer sb = new StringBuffer();
		int i;
		for(i = 0; i < listOfGameResponses.size() - 1; i++) {
			sb.append(listOfGameResponses.get(i).toString());
			sb.append("\n");
		}
		sb.append(listOfGameResponses.get(i).toString());
		return sb.toString();
	}
	
	/** 
	 * String representation of the file
	 * @param filename - name of the file
	 * @return rep 
	 */
	public String printTextFile(String filename) {
		StringBuffer sb = new StringBuffer();
		String rep = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while(line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}
			rep = sb.toString().trim();
			br.close();
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		return rep;
	}
}
