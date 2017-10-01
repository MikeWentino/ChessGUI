import java.util.ArrayList;

public class Knight extends Piece {
	//default constructor
	public Knight(boolean a, Coord b) {
		team = a;
		c = b;
		moves = 0;
	}

	//copy constructor
	public Knight(Knight a)	{
		team = a.team;
		c = new Coord(a.c);
		moves = a.moves;
	}
	
	//translates piece into image string representation
	public String toString() {
		return "H";
	}
	
	//returns all possible moves of the piece based on the current board position
	public ArrayList<Pair<Coord,Coord>> returnMoves(Board b) {
		//copy Piece grid
		Piece[][] g = b.getGrid();
		
		//return array of all the possible moves
		ArrayList<Pair<Coord,Coord>> possibleMoves = new ArrayList<Pair<Coord,Coord>>();
			
		if(c.x-2>=0 && c.x-2<=7 && c.y-1>=0 && c.y-1<=7 && (g[c.y-1][c.x-2]==null || team != g[c.y-1][c.x-2].team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x-2),null));
		
		if(c.x-2>=0 && c.x-2<=7 && c.y+1>=0 && c.y+1<=7 && (g[c.y+1][c.x-2]==null || team != g[c.y+1][c.x-2].team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x-2),null));
		
		if(c.x+2>=0 && c.x+2<=7 && c.y-1>=0 && c.y-1<=7 && (g[c.y-1][c.x+2]==null || team != g[c.y-1][c.x+2].team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x+2),null));
		
		if(c.x+2>=0 && c.x+2<=7 && c.y+1>=0 && c.y+1<=7 && (g[c.y+1][c.x+2]==null || team != g[c.y+1][c.x+2].team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x+2),null));
		
		if(c.x-1>=0 && c.x-1<=7 && c.y-2>=0 && c.y-2<=7 && (g[c.y-2][c.x-1]==null || team != g[c.y-2][c.x-1].team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-2,c.x-1),null));
		
		if(c.x+1>=0 && c.x+1<=7 && c.y-2>=0 && c.y-2<=7 && (g[c.y-2][c.x+1]==null || team != g[c.y-2][c.x+1].team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-2,c.x+1),null));
		
		if(c.x-1>=0 && c.x-1<=7 && c.y+2>=0 && c.y+2<=7 && (g[c.y+2][c.x-1]==null || team != g[c.y+2][c.x-1].team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+2,c.x-1),null));
		
		if(c.x+1>=0 && c.x+1<=7 && c.y+2>=0 && c.y+2<=7 && (g[c.y+2][c.x+1]==null || team != g[c.y+2][c.x+1].team))
			possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+2,c.x+1),null));

		return possibleMoves;
	}
}
