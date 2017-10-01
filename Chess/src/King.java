import java.util.ArrayList;

public class King extends Piece {
	//default constructor
	public King(boolean a, Coord b) {
		team = a;
		c = b;
		moves = 0;
	}
	
	//copy constructor
	public King(King a) {
		team = a.team;
		c = new Coord(a.c);
		moves = a.moves;
	}
	
	//translates piece into image string representation
	public String toString() {
		return "K";
	}
	
	//returns all possible moves of the piece based on the current board position
	public ArrayList<Pair<Coord, Coord>> returnMoves(Board b) {
		//copy Piece grid
		Piece[][] g = b.getGrid();
		
		//return array of all the possible moves
		ArrayList<Pair<Coord,Coord>> possibleMoves = new ArrayList<Pair<Coord,Coord>>();
			
		//east square
		if((c.x>=0 && c.x<=7 && c.y+1>=0 && c.y+1<=7) 
				&& (g[c.y+1][c.x] == null || g[c.y+1][c.x].team!=team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x),null));

		//north square
		if((c.x-1>=0 && c.x-1<=7 && c.y>=0 && c.y<=7) 
				&& (g[c.y][c.x-1] == null || g[c.y][c.x-1].team!=team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y,c.x-1),null));

		//west square
		if((c.x>=0 && c.x<=7 && c.y-1>=0 && c.y-1<=7) 
				&& (g[c.y-1][c.x] == null || g[c.y-1][c.x].team!=team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x),null));

		//south square
		if((c.x+1>=0 && c.x+1<=7 && c.y>=0 && c.y<=7) 
				&& (g[c.y][c.x+1] == null || g[c.y][c.x+1].team!=team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y,c.x+1),null));
				
		//southeast square
		if((c.x+1>=0 && c.x+1<=7 && c.y+1>=0 && c.y+1<=7) 
				&& (g[c.y+1][c.x+1] == null || g[c.y+1][c.x+1].team!=team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x+1),null));

		//northeast square
		if((c.x-1>=0 && c.x-1<=7 && c.y+1>=0 && c.y+1<=7)
				&& (g[c.y+1][c.x-1] == null || g[c.y+1][c.x-1].team!=team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x-1),null));

		//southwest square
		if((c.x+1>=0 && c.x+1<=7 && c.y-1>=0 && c.y-1<=7) 
				&& (g[c.y-1][c.x+1] == null || g[c.y-1][c.x+1].team!=team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x+1),null));

		//northwest square
		if((c.x-1>=0 && c.x-1<=7 && c.y-1>=0 && c.y-1<=7) 
				&& (g[c.y-1][c.x-1] == null || g[c.y-1][c.x-1].team!=team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x-1),null));
		
		//TODO:: use loop and *-1 or *1 variable to prevent duplication
		//Castle east
		if(moves==0 && g[c.y][c.x+1]==null && g[c.y][c.x+2]==null && g[c.y][c.x+3]!=null 
				&& g[c.y][c.x+3].toString().equals("R") && g[c.y][c.x+3].moves==0 && !b.inCheck(team)) {
			//set test variables
			boolean isPossible = true;
			Board tempBoard;
			
			//check first position to the right
			tempBoard = new Board(b);
			tempBoard.move(new Coord(c.y,c.x), new Pair<Coord, Coord>(new Coord(c.y,c.x+1),null));
			//if position is being attacked do not add move
			if(tempBoard.inCheck())
				isPossible = false;
			
			//check second position to the right
			tempBoard = new Board(b);
			tempBoard.move(new Coord(c.y,c.x), new Pair<Coord, Coord>(new Coord(c.y,c.x+2),null));
			//if position is being attacked do not add move
			if(tempBoard.inCheck())
				isPossible = false;
			
			//check third position to the right
			tempBoard = new Board(b);
			tempBoard.move(new Coord(c.y,c.x), new Pair<Coord, Coord>(new Coord(c.y,c.x+3),null));
			//if position is being attacked do not add move
			if(tempBoard.inCheck())
				isPossible = false;
			
			if(isPossible)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y,c.x+2),new Coord(c.y,c.x+1)));
		}
			
		//Castle west
		if(moves==0 && g[c.y][c.x-1]==null && g[c.y][c.x-2]==null && g[c.y][c.x-3]==null 
				&& g[c.y][c.x-4]!=null && g[c.y][c.x-4].toString().equals("R") && g[c.y][c.x-4].moves==0 && !b.inCheck(team)) {
			//set test variables
			boolean isPossible = true;
			Board tempBoard;
			
			//check first position to the right
			tempBoard = new Board(b);
			tempBoard.move(new Coord(c.y,c.x), new Pair<Coord, Coord>(new Coord(c.y,c.x-1),null));
			//if position is being attacked do not add move
			if(tempBoard.inCheck())
				isPossible = false;
			
			//check second position to the right
			tempBoard = new Board(b);
			tempBoard.move(new Coord(c.y,c.x), new Pair<Coord, Coord>(new Coord(c.y,c.x-2),null));
			//if position is being attacked do not add move
			if(tempBoard.inCheck())
				isPossible = false;
			
			//check third position to the right
			tempBoard = new Board(b);
			tempBoard.move(new Coord(c.y,c.x), new Pair<Coord, Coord>(new Coord(c.y,c.x-3),null));
			//if position is being attacked do not add move
			if(tempBoard.inCheck())
				isPossible = false;
			
			//check fourth position to the right
			tempBoard = new Board(b);
			tempBoard.move(new Coord(c.y,c.x), new Pair<Coord, Coord>(new Coord(c.y,c.x-4),null));
			//if position is being attacked do not add move
			if(tempBoard.inCheck())
				isPossible = false;
			
			if(isPossible)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y,c.x-2),new Coord(c.y,c.x-1)));
		}
		
		return possibleMoves;
	}
}
