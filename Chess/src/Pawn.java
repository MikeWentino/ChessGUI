import java.util.ArrayList;

public class Pawn extends Piece {
	
	//default constructor
	public Pawn(boolean a, Coord b)	{
		team = a;
		c = b;
		moves=0;
	}
	
	//copy constructor
	public Pawn(Pawn a)	{
		team = a.team;
		c = new Coord(a.c);
		moves = a.moves;
	}
	
	//translates piece into image string representation
	public String toString() {
		return "P";
	}
	
	//returns all possible moves of the piece based on the current board position
	public ArrayList<Pair<Coord,Coord>> returnMoves(Board b) {
		//copy Piece grid
		Piece[][] g = b.getGrid();
		
		//return array of all the possible moves
		ArrayList<Pair<Coord,Coord>> possibleMoves = new ArrayList<Pair<Coord,Coord>>();

		//System.out.println(team + ": (" + c.y + ", " + c.x + ")");
		//if the team is moving north (white team)
		if(team) {
			//first move, two square pawn advance
			if(moves==0 && g[c.y-1][c.x] == null && g[c.y-2][c.x] == null)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-2,c.x),null));
			
			//single square pawn advance
			if(g[c.y-1][c.x] == null)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x),null));
			
			//capture diagonally west
			if(c.x-1>=0 && c.x-1<=7 && g[c.y-1][c.x-1]!=null && (!g[c.y-1][c.x-1].team))
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x-1),null));
			
			//capture diagonally east
			if(c.x+1>=0 && c.x+1<=7 && g[c.y-1][c.x+1]!=null && (!g[c.y-1][c.x+1].team))
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x+1),null));
			
			//en passant west
			if(c.x-1>=0 && c.y==3 && g[c.y-1][c.x-1]==null && g[c.y][c.x-1]!=null && !g[c.y][c.x-1].team 
					&& g[c.y][c.x-1].toString().equals("P") && g[c.y][c.x-1].moves==1)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x-1),new Coord(c.y, c.x-1)));
			
			//en passant east
			if(c.x+1<=7 && c.y==3 && g[c.y-1][c.x+1]==null && g[c.y][c.x+1]!=null && !g[c.y][c.x+1].team
					&& g[c.y][c.x+1].toString().equals("P") && g[c.y][c.x+1].moves==1)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-1,c.x+1),new Coord(c.y, c.x+1)));
			
		//if the team is moving south (black team)
		} else {
			//first move, two square pawn advance
			if(moves==0 && g[c.y+1][c.x] == null && g[c.y+2][c.x] == null)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+2,c.x),null));

			//single square pawn advance
			if(g[c.y+1][c.x] == null)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x),null));
			
			//capture diagonally west
			if(c.x-1>=0 && c.x-1<=7 && g[c.y+1][c.x-1]!=null && (g[c.y+1][c.x-1].team))
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x-1),null));
			
			//capture diagonally east
			if(c.x+1>=0 && c.x+1<=7 && g[c.y+1][c.x+1]!=null && (g[c.y+1][c.x+1].team))
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x+1),null));
			
			//en passant west
			if(c.x-1>=0 && c.y==4 && g[c.y+1][c.x-1]==null && g[c.y][c.x-1]!=null && g[c.y][c.x-1].team 
					&& g[c.y][c.x-1].toString().equals("P") && g[c.y][c.x-1].moves==1)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x-1),new Coord(c.y, c.x-1)));
			
			//en passant east
			if(c.x+1<=7 && c.y==4 && g[c.y-1][c.x+1]==null && g[c.y][c.x+1]!=null && g[c.y][c.x+1].team 
					&& g[c.y][c.x+1].toString().equals("P") && g[c.y][c.x+1].moves==1)
				possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+1,c.x+1),new Coord(c.y, c.x+1)));
			
		}		

		return possibleMoves;
	}
}
