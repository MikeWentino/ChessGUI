import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Board {
	public Piece[][] grid; //2d Piece array to model board position
	public boolean turn; //boolean to keep track of turn
	public Coord pieceActive;
	public boolean side;

	
	//create a new board
	public Board() {
		//true is white
		//false is black
		//set white to start
		turn = true;
		grid = new Piece[8][8];
		pieceActive = null;
		side = true;
		//grid[0][0] is the top left corner
		
		//set the opponents pieces
		grid[0][0] = new Rook(false, new Coord(0,0));
		grid[0][1] = new Knight(false, new Coord(0,1));
		grid[0][2] = new Bishop(false, new Coord(0,2));
		grid[0][3] = new Queen(false, new Coord(0,3));
		grid[0][4] = new King(false, new Coord(0,4));
		grid[0][5] = new Bishop(false, new Coord(0,5));
		grid[0][6] = new Knight(false, new Coord(0,6));
		grid[0][7] = new Rook(false, new Coord(0,7));
		
		grid[1][0] = new Pawn(false, new Coord(1,0));
		grid[1][1] = new Pawn(false, new Coord(1,1));
		grid[1][2] = new Pawn(false, new Coord(1,2));
		grid[1][3] = new Pawn(false, new Coord(1,3));
		grid[1][4] = new Pawn(false, new Coord(1,4));
		grid[1][5] = new Pawn(false, new Coord(1,5));
		grid[1][6] = new Pawn(false, new Coord(1,6));
		grid[1][7] = new Pawn(false, new Coord(1,7));
		
		//fill in the blank space
		for(int i=2; i<6; i++) {
			for(int j=0; j<8; j++) {
				grid[i][j] = null;
			}
		}
		
		//set the players pieces
		grid[6][0] = new Pawn(true, new Coord(6,0));
		grid[6][1] = new Pawn(true, new Coord(6,1));
		grid[6][2] = new Pawn(true, new Coord(6,2));
		grid[6][3] = new Pawn(true, new Coord(6,3));
		grid[6][4] = new Pawn(true, new Coord(6,4));
		grid[6][5] = new Pawn(true, new Coord(6,5));
		grid[6][6] = new Pawn(true, new Coord(6,6));
		grid[6][7] = new Pawn(true, new Coord(6,7));
		
		grid[7][0] = new Rook(true, new Coord(7,0));
		grid[7][1] = new Knight(true, new Coord(7,1));
		grid[7][2] = new Bishop(true, new Coord(7,2));
		grid[7][3] = new Queen(true, new Coord(7,3));
		grid[7][4] = new King(true, new Coord(7,4));
		grid[7][5] = new Bishop(true, new Coord(7,5));
		grid[7][6] = new Knight(true, new Coord(7,6));
		grid[7][7] = new Rook(true, new Coord(7,7));			
	}
	
	//create a board based on another board (temporary board)
	public Board(Board a) {
		turn = a.turn;
		grid = new Piece[8][8];		
		side = a.side;
		if(a.pieceActive!=null)
			pieceActive = new Coord(a.pieceActive.y,a.pieceActive.x);
		
		//loop through the input board and copy all the pieces
		for(int i=0; i<8; i++) { //rows
			for(int j=0; j<8; j++) { //columns
				//copy the piece if it is not a blank space
				if(a.getPiece(new Coord(i,j)) != null) {
					grid[i][j] = a.getPiece(new Coord(i,j)).copyPiece();
				} else {
					grid[i][j] = null;
				}
			}
		}
	}
	
	//create a board based on another board (temporary board)
	public void updateBoard(Board a) {
		turn = a.turn;
		grid = new Piece[8][8];	
		side = a.side;
		if(a.pieceActive!=null)
			pieceActive = new Coord(a.pieceActive.y,a.pieceActive.x);

		//loop through the input board and copy all the pieces
		for(int i=0; i<8; i++) { //rows
			for(int j=0; j<8; j++) { //columns
				//copy the piece if it is not a blank space
				if(a.getPiece(new Coord(i,j)) != null) {
					grid[i][j] = a.getPiece(new Coord(i,j)).copyPiece();
				} else {
					grid[i][j] = null;
				}
			}
		}
	}
	
	//handles user input of a clicked square
	public Board userInput(String eventName) {
		//checks if it is the users turn
		if(turn == side) {
			//checks for checkmate
			if(inCheckMate()) {
				System.out.println("Check Mate. You Lost!!!");
				System.exit(0);
			} else if(inStaleMate()) {
				System.out.println("Stale Mate!!!");
				System.exit(0);
			}

			//see which Coord the user clicked
			Coord eventMove = new Coord(Integer.parseInt(eventName.charAt(0)+""),Integer.parseInt(eventName.charAt(1)+""));

			//executes if user is activating a piece
			if(getPiece(eventMove) != null && getPiece(eventMove).team == turn)	{
				pieceActive = eventMove;//sets pieceActive to the user chosen piece coordinate
			//executes if user already activated a piece and has clicked a square to move it to
			} else if(pieceActive != null) {
				Piece piece = getPiece(pieceActive);
				
				//tests to see if the user clicked move is a legal move for the active piece
				ArrayList<Pair<Coord,Coord>> possibleMoves = getLegalMoves(piece);				
				for(int i=0; i<possibleMoves.size(); i++) {
					if(possibleMoves.get(i).a.equals(eventMove)) {							
						//moves the piece in the board object
						move(pieceActive, new Pair<Coord, Coord>(possibleMoves.get(i).a, possibleMoves.get(i).b));	
						pieceActive=null;//deactivates the piece
						turn = !turn;//changes the turn
						
						//run the chess engine
						chessEngine();
					}
				}
			}
		}		
		
		return this;
	}
	
	//Opponents Move (Chess engine)
	public Board chessEngine() {
		//checks for checkmate
		if(inCheckMate()) {
			System.out.println("You Won!!!");
			System.exit(0);
		} else if(inStaleMate()) {
			System.out.println("Stale Mate!!!");
			System.exit(0);
		}
		
		//creates a treeMap of all the moves the current team can make
		TreeMap<Coord,ArrayList<Pair<Coord,Coord>>> teamMoves = new TreeMap<Coord,ArrayList<Pair<Coord,Coord>>>();
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(this.getPiece(new Coord(i,j)) != null && this.getPiece(new Coord(i,j)).team == turn && getLegalMoves(getPiece(new Coord(i,j))).size()>0)
					teamMoves.put(new Coord(i,j),getLegalMoves(getPiece(new Coord(i,j))));
		
		//choose a random piece
		int randPieceIndex = (int)(Math.random()*teamMoves.size());	
		int teamMovesCount = 0;
		for(Map.Entry<Coord,ArrayList<Pair<Coord,Coord>>> entry : teamMoves.entrySet()) {
			//choose a random move
			if(teamMovesCount==randPieceIndex) {
				Coord key = entry.getKey();
				ArrayList<Pair<Coord,Coord>> value = entry.getValue();
				int randMoveIndex = (int)(Math.random()*value.size());	
				move(key, new Pair<Coord, Coord>(value.get(randMoveIndex).a, value.get(randMoveIndex).b));
			}
			teamMovesCount++;
		}
		
		turn=!turn;
		
		return this;
	}	
	
	//return true if team t is in check
	public boolean inCheck() {	
		ArrayList<Pair<Coord,Coord>> opposingMoves = new ArrayList<Pair<Coord,Coord>>();		
		Coord kingCoord = null;

		//find the king Coord
		//find all the opposing pieces and return their moves
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				//if the piece is of the opposing team add their moves to opposingMoves
				if(this.grid[i][j] != null && grid[i][j].team != turn)
					opposingMoves.addAll(getAllMoves(grid[i][j]));
				
				//if the king is found
				if(this.grid[i][j] != null && this.grid[i][j].team == turn && this.grid[i][j].toString().equals("K"))
					kingCoord = new Coord(this.grid[i][j].c);
			}
		}
		if(kingCoord==null)
			System.out.println("kingCoord==null");
		
		//loop through the opposing moves and return true if the king is in check
		for(int i=0; i<opposingMoves.size(); i++)
			if(opposingMoves.get(i).a.equals(kingCoord))
				return true;
		//return false if the king is not in check
		return false;
	}
	
	public boolean inCheck(boolean t) {	
		ArrayList<Pair<Coord,Coord>> opposingMoves = new ArrayList<Pair<Coord,Coord>>();		
		Coord kingCoord = null;

		//find the king Coord
		//find all the opposing pieces and return their moves
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				//if the piece is of the opposing team add their moves to opposingMoves
				if(this.grid[i][j] != null && grid[i][j].team != t)
					opposingMoves.addAll(getAllMoves(grid[i][j]));
				
				//if the king is found
				if(this.grid[i][j] != null && this.grid[i][j].team == t && this.grid[i][j].toString().equals("K"))
					kingCoord = new Coord(this.grid[i][j].c);
			}
		}
		if(kingCoord==null)
			System.out.println("kingCoord==null");
		
		//loop through the opposing moves and return true if the king is in check
		for(int i=0; i<opposingMoves.size(); i++)
			if(opposingMoves.get(i).a.equals(kingCoord))
				return true;
		//return false if the king is not in check
		return false;
	}

	//returns true if the team whose turn it is is in check mate
	private boolean inCheckMate() {		
		//check if inCheck
		if(!this.inCheck())
			return false;

		//creates a treeMap of all the moves the current team can make
		TreeMap<Coord,ArrayList<Pair<Coord,Coord>>> teamMoves = new TreeMap<Coord,ArrayList<Pair<Coord,Coord>>>();
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(getPiece(new Coord(i,j)) != null && getPiece(new Coord(i,j)).team == turn)
					teamMoves.put(new Coord(i,j),getLegalMoves(getPiece(new Coord(i,j))));
		
		//iterate through teamMoves and see if there are any moves to get out of check
		for (Map.Entry<Coord,ArrayList<Pair<Coord,Coord>>> entry : teamMoves.entrySet())
	        if(entry.getValue().size()>0)
	        	return false;
		
		//return true if there are no moves to get out checkmate
		return true;
	}
	
	//checks to see if the team whose turn it is is in stalemate
	private boolean inStaleMate() {
		//creates a treeMap of all the moves the current team can make
		TreeMap<Coord,ArrayList<Pair<Coord,Coord>>> teamMoves = new TreeMap<Coord,ArrayList<Pair<Coord,Coord>>>();
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(getPiece(new Coord(i,j)) != null && getPiece(new Coord(i,j)).team == turn)
					teamMoves.put(new Coord(i,j),getLegalMoves(getPiece(new Coord(i,j))));
		
		//iterate through teamMoves and see if there are any moves that don't put you in check
		for (Map.Entry<Coord,ArrayList<Pair<Coord,Coord>>> entry : teamMoves.entrySet())
	        if(entry.getValue().size()>0)
	        	return false;
		
		//return true if there are no moves that don't put you in check
		return true;
	}	
	
	//returns all possible moves a piece can make including ones that put that team in check
	public ArrayList<Pair<Coord,Coord>> getAllMoves(Piece p) {
		ArrayList<Pair<Coord,Coord>> allMoves = p.returnMoves(this);
		return allMoves;
	}
	
	//returns all legal moves a piece can make excluding ones that put that team in check
	public ArrayList<Pair<Coord,Coord>> getLegalMoves(Piece p) {
		ArrayList<Pair<Coord,Coord>> possibleMoves = p.returnMoves(this);
		
        //check to see if any moves put the team in check
		ArrayList<Pair<Coord,Coord>> legalMoves = new ArrayList<Pair<Coord,Coord>>();
        for(int i=0; i<possibleMoves.size(); i++) {
        	Board tempBoard = new Board(this);
        	
        	//check if move is null
        	if(possibleMoves.get(i).b!=null)
        		tempBoard.move(p.c, new Pair<Coord, Coord>(new Coord(possibleMoves.get(i).a), new Coord(possibleMoves.get(i).b)));
        	else
        		tempBoard.move(p.c, new Pair<Coord, Coord>(new Coord(possibleMoves.get(i).a), null));
        	
        	//add the move if it does not put the team in check
	        if(!tempBoard.inCheck())
	        	legalMoves.add(possibleMoves.get(i));
        }
        
		return legalMoves;
	}
	
	
	//return the Piece at the given coordinate
	public Piece getPiece(Coord n) {
		return grid[n.y][n.x];
	}
	
	//return the board in a 2d array of pieces
	public Piece[][] getGrid() {
		return grid;
	}
	
	//move a Piece from Coord a to b
	public void move(Coord a, Pair<Coord, Coord> b) {
		//tests if the active piece is a King (for castling)
		if(getPiece(a).toString().equals("K") && b.b!=null) {
			if(b.a.x-a.x>0)
				move(new Coord(a.y, 7), new Pair<Coord, Coord>(new Coord(a.y, b.a.x-1), null));
			else
				move(new Coord(a.y, 0), new Pair<Coord, Coord>(new Coord(a.y, b.a.x+1), null));			
		//tests if the active piece is a Pawn (for en passant)
		} else if(getPiece(a).toString().equals("P") && b.b!=null) {
			remove(b.b);
		}
		
		//change the Piece's index in the grid array
		grid[b.a.y][b.a.x] = grid[a.y][a.x];
		grid[a.y][a.x] = null; 
		
		//change the coordinate value of the moved piece
		grid[b.a.y][b.a.x].c = new Coord(b.a.y,b.a.x);
		
		//automatically promote a pawn to a queen
		if(grid[b.a.y][b.a.x].toString().equals("P") && (b.a.y==0 || b.a.y==7))
			grid[b.a.y][b.a.x] = new Queen(grid[b.a.y][b.a.x].team, b.a);
		
		//increase the pieces moves count
		grid[b.a.y][b.a.x].moves++;
	}
	
	//sets the position on the board to empty
	public void remove(Coord a)	{
		grid[a.y][a.x] = null;
	}
}
