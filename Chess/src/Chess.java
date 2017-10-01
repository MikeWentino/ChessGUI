import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;

public class Chess implements ActionListener 
{
	//declare GUI components
	private JFrame frame;
	private JPanel contentPane;
	private JButton[][] GUIBoard;
	private String[][] colors;
	
	//declare Model
	private Board board;
	
	public Chess()
	{
		//initialize and instantiate model components
		board = new Board(); 
		
		//prompt the user to choose sides
		Scanner reader = new Scanner(System.in);
		String userInput = "";

		System.out.println("Let's play chess!");
		System.out.println("Please choose a side");
		do {
			System.out.print("\"white\" or \"black\"? ");
			userInput = reader.nextLine().trim();
			if(userInput.equals("white"))
				board.side = true;
			else if(userInput.equals("black"))
				board.side = false;
		} while(!userInput.equalsIgnoreCase("white")&&!userInput.equalsIgnoreCase("black"));
		reader.close();
		
		//run the chessEngine if the user is black
		if(!board.side)
			board.chessEngine();
		
		//TODO:: flip board if user is black
		
		//instantiate GUI
		GUIBoard = new JButton[8][8];
		colors = new String[8][8];
		
		frame=new JFrame ("Chess!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);		
		
		contentPane=new JPanel();
		contentPane.setLayout(new GridLayout(8, 8, 0, 0));

		//initialize the GUI Board
		createInitialGUIBoard(board);
	}
		
	//creates and populates the GUI Board
	private void createInitialGUIBoard(Board b) {
		//convert board to a GUI
		boolean green = true;
		//loop through the board and create the appropriate picture file name for the image
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				String icon = "";
				String command = i+""+j;
				
				//creates String for the file name
				if(b.getPiece(new Coord(i,j))==null) { //updates empty spots
					if(green) {
						icon = "G.png";
						colors[i][j] = "G";
					} else {
						icon = "B.png";
						colors[i][j] = "B";
					}
				} else { //updates occupied
					if(b.getPiece(new Coord(i,j)).team)
						icon+="W";
					else
						icon+="B";
					
					icon += b.getPiece(new Coord(i,j)).toString();
					
					if(green) {
						icon+="G";
						colors[i][j] = "G";
					} else {
						icon+="B";
						colors[i][j] = "B";
					}
					
					icon+=".png";
				}
				
				//add the image to the GUIBoard and set the ActionCommand
				GUIBoard[i][j] = new JButton (new ImageIcon("src/"+icon));
				GUIBoard[i][j].setActionCommand(command);
				GUIBoard[i][j].addActionListener(this);
				GUIBoard[i][j].setPreferredSize(new Dimension(100,100));
				contentPane.add(GUIBoard[i][j]);
				
				green = !green;
			}
			green = !green;
		}
		
		frame.setContentPane(contentPane);		 
		frame.pack();
		frame.setVisible(true);
	}
	
	//synchronizes the board to the JFrame button grid
	public void updateGUI(Piece[][] g)
	{
		boolean green = true;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				String icon = "";
				
				//creates String for the file name
				if(board.getPiece(new Coord(i,j))==null) { //updates empty spots
					if(green) {
						icon = "G.png";
						colors[i][j] = "G";
					} else {
						icon = "B.png";
						colors[i][j] = "B";
					}
				} else { //updates occupied
					if(board.getPiece(new Coord(i,j)).team)
						icon+="W";
					else
						icon+="B";
					
					icon += board.getPiece(new Coord(i,j)).toString();
					
					if(green) {
						icon+="G";
						colors[i][j] = "G";
					} else {
						icon+="B";
						colors[i][j] = "B";
					}
					
					icon+=".png";
				}
				
				GUIBoard[i][j].setIcon(new ImageIcon("src/"+icon));								
				green = !green;
			}
			green = !green;
		}
	}
	
	//method for any button click event
	public void actionPerformed(ActionEvent event) {
		updateGUI(board.userInput(event.getActionCommand()).getGrid());
	}

	public static void runGUI() {
		Chess greeting = new Chess();
	}
	
	public static void main(String[]args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
	}
}
