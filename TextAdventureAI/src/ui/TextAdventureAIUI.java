/**
 * 
 */
package ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
//import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import aimanager.AIManager;
import aimanager.EndToEnd;
import fileio.FileReaderIO;
import gameio.GameResponse;

/**
 * @author sriyamadapusivasudevan
 *
 */
@SuppressWarnings("serial")
public class TextAdventureAIUI  extends JFrame implements ActionListener {

	/** Button for displaying KB */
	private JButton showKB;
	
	private JButton showGameResponse;
	
	private JButton showKBForAResponse;
	
	/** Label for game responses */
	private JLabel gameResponsesLabel;
	
	/** Label for knowledge base */
	private JLabel kBLabel;
	
	/** Panel for game responses */
	private JPanel gameResponsesPanel;
	
	/** Panel for knowledge base */
	private JPanel kBPanel;
	
	/** Stevie - AI */
	private EndToEnd stevie;
	
	/** list of game responses */
	private String[] stringListOfGameResponses;
	
	/** list of game responses */
	private String[] stringListOfKB = {"Empty"};
	
	/** FileReaderIO instance */
	private FileReaderIO reader;
	
	/** AI Manager instance */
	private AIManager stevieJr;
	
	/** Pane for gameResponse */
	@SuppressWarnings("rawtypes")
	private JList gameResponsePane;
	
	/** Pane for  knowledge base */
	@SuppressWarnings("rawtypes")
	private JList kBPane;
	
	/** Scrolling list for game responses */
	private JScrollPane scrollGameResponse;

	/** Scrolling pane for KB */
	private JScrollPane scrollKB;
	
	/** Responses to build the knowledge base */
	ArrayList<GameResponse> responses = new ArrayList<GameResponse>();
	
	/** Responses used to show the knowledge base*/
	ArrayList<GameResponse> responses2 = new ArrayList<GameResponse>();
	
	/** Responses used to show the knowledge base per response */
	ArrayList<GameResponse> responses3 = new ArrayList<GameResponse>();
	
	/**
	 * Simple constructor
	 */
	@SuppressWarnings("unchecked")
	public TextAdventureAIUI() {
		
		JFileChooser fileChooser = new JFileChooser("");
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.showOpenDialog(this);
		File[] f = fileChooser.getSelectedFiles();
		String filename = f[0].getAbsolutePath();
		String filename2 = f[1].getAbsolutePath();
		stevie = new EndToEnd();
		reader = new FileReaderIO();
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(0, 2));
		
		ArrayList<GameResponse> responses = reader.readFile(filename);
		
		for(int i = 0; i < responses.size(); i++) {
			responses2.add(responses.get(i));
			responses3.add(responses.get(i));
		}
		
		stringListOfGameResponses = reader.printTextFile(filename2).split("\n");
		
		stevie.takeListOfGameResponses(responses);
		//stringListOfKB = stevie.printEntireKB().split("\n");
		
		showGameResponse = new JButton("Show the game response");
		showKB = new JButton("Show end knowledge base");
		showKBForAResponse = new JButton("Show current knowledge base"); 
		
		gameResponsesLabel = new JLabel("Responses from the game");
		kBLabel = new JLabel("Knowledge Base of AI");
		
		gameResponsesPanel = new JPanel();
		kBPanel = new JPanel();
		
		gameResponsePane = new JList<String>(stringListOfGameResponses);
		kBPane = new JList<String>(stringListOfKB);
		scrollGameResponse = new JScrollPane(gameResponsePane);
		scrollKB = new JScrollPane(kBPane);
		
		gameResponsePane.setFont(new Font("monospaced", Font.PLAIN, 12));
		kBPane.setFont(new Font("monospaced", Font.PLAIN, 12));
		
		container.add(gameResponsesPanel);
		container.add(kBPanel);
		gameResponsesPanel.setLayout(null);
		kBPanel.setLayout(null);
		
		//Left
		gameResponsesLabel.setBounds(200, 0, 220, 30);
		gameResponsesPanel.add(gameResponsesLabel);
		
		gameResponsePane.setListData(stringListOfGameResponses);
		
		scrollGameResponse.setBounds(20, 200, 570, 400);
		gameResponsesPanel.add(scrollGameResponse);
		
		//right side
		kBLabel.setBounds(200, 0, 220, 30);
		kBPanel.add(kBLabel);
		
		showGameResponse.setBounds(220, 50, 220, 30);
		gameResponsesPanel.add(showGameResponse);
				
		showGameResponse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = gameResponsePane.getSelectedIndex();
				if(index != -1) {
					String gR = responses2.get(index).toString();
					String status = responses2.get(index).statusToString();
					JOptionPane.showMessageDialog(container, gR, status, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(container, "Select a player response first", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		showKB.setBounds(200, 50, 220, 30);
		kBPanel.add(showKB);
		
		showKB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				stringListOfKB = stevie.printEntireKB().split("\n");
				kBPane.setListData(stringListOfKB);
				
				if(stevie != null && stevie.obtainedEndPredicate()) {
					  JOptionPane.showMessageDialog(container, "Stevie solved the game!", "YAY!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		showKBForAResponse.setBounds(200, 100, 220, 30);
		kBPanel.add(showKBForAResponse);
		
		showKBForAResponse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = gameResponsePane.getSelectedIndex();
				if(index != -1) {
					stevieJr = new AIManager();
					for(int i = index; i >= 0; i--) {
						stevieJr.addToKB(responses3.get(i));
					}
					
					stringListOfKB = stevieJr.printKB().split("\n");
					kBPane.setListData(stringListOfKB);
					
					if(stevieJr != null && stevieJr.obtainedEndPredicate()) {
						  JOptionPane.showMessageDialog(container, "Stevie solved the game!", "YAY!", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} else {
					JOptionPane.showMessageDialog(container, "Select a player response first", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		kBPane.setListData(stringListOfKB);
		
		scrollKB.setBounds(20, 200, 570, 400);
		kBPanel.add(scrollKB);
		
		setVisible(true);
		setSize(1200, 700);
		setLocation(50, 50);
		setTitle("STEVIE - Text Adventure Solver");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TextAdventureAIUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}
