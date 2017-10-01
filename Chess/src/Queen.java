import java.util.ArrayList;

public class Queen extends Piece {
	//default constructor
	public Queen(boolean a, Coord b) {
		team = a;
		c = b;
		moves = 0;
	}
	
	//copy constructor
	public Queen(Queen a) {
		team = a.team;
		c = new Coord(a.c);
		moves = a.moves;
	}
	
	//translates piece into image string representation
	public String toString() {
		return "Q";
	}
	
	//returns all possible moves of the piece based on the current board position
	public ArrayList<Pair<Coord,Coord>> returnMoves(Board b) {
		//copy Piece grid
		Piece[][] g = b.getGrid();
		
		//return array of all the possible moves
		ArrayList<Pair<Coord,Coord>> possibleMoves = new ArrayList<Pair<Coord,Coord>>();
		
		//booleans to prevent impossible moves from being added
		boolean one=true;
		boolean two=true;
		boolean three=true;
		boolean four=true;
		boolean five=true;
		boolean six=true;
		boolean seven=true;
		boolean eight=true;
		
		//increase the search distance for possible moves by 1
		for(int i=1; i<8; i++) {			
			//break if all directions are exhausted
			if(!one && !two && !three && !four && !five && !six && !seven && !eight)
				break;
			
			//search in direction one (southeast)
			if(c.x+i>=0 && c.x+i<=7 && c.y+i>=0 && c.y+i<=7 && one) {
				if(g[c.y+i][c.x+i] == null)	{
					possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+i,c.x+i),null));
				} else {
					//found a possible move to capture opponent's piece
					if(g[c.y+i][c.x+i].team!=team)
						possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+i,c.x+i),null));

					//done searching for moves in this direction
					one=false; 
				}
			}
			
			//search in direction two (northeast)
			if(c.x-i>=0 && c.x-i<=7 && c.y+i>=0 && c.y+i<=7 && two) {
				//add a possible move to the ArrayList if the space is empty
				if(g[c.y+i][c.x-i] == null) {
					possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+i,c.x-i),null));
				} else {
					//found a possible move to capture opponent's piece
					if(g[c.y+i][c.x-i].team!=team)
						possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+i,c.x-i),null));

					//done searching for moves in this direction
					two=false;
				}
			}
			
			//search in direction three (southwest)
			if(c.x+i>=0 && c.x+i<=7 && c.y-i>=0 && c.y-i<=7 && three) {
				//add a possible move to the ArrayList if the space is empty
				if(g[c.y-i][c.x+i] == null) {
					possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-i,c.x+i),null));
				} else {
					//found a possible move to capture opponent's piece
					if(g[c.y-i][c.x+i].team!=team)
						possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-i,c.x+i),null));
					
					//done searching for moves in this direction
					three=false;
				}
			}
			
			//search in direction four (northwest)
			if(c.x-i>=0 && c.x-i<=7 && c.y-i>=0 && c.y-i<=7 && four) {
				//add a possible move to the ArrayList if the space is empty
				if(g[c.y-i][c.x-i] == null) {
					possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-i,c.x-i),null));
				} else {
					//found a possible move to capture opponent's piece
					if(g[c.y-i][c.x-i].team!=team)
						possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-i,c.x-i),null));
					
					//done searching for moves in this direction
					four=false;
				}
			}
			
			//search in direction five (south)
			if(c.x+i>=0 && c.x+i<=7 && five) {
				//add a possible move to the ArrayList if the space is empty
				if(g[c.y][c.x+i] == null)	{
					possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y,c.x+i),null));
				} else {
					//found a possible move to capture opponent's piece
					if(g[c.y][c.x+i].team!=team)
						possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y,c.x+i),null));

					//done searching for moves in this direction
					five=false; 
				}
			}
			
			//search in direction six (north)
			if(c.x-i>=0 && c.x-i<=7 && six) {
				//add a possible move to the ArrayList if the space is empty
				if(g[c.y][c.x-i] == null) {
					possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y,c.x-i),null));
				} else {
					//found a possible move to capture opponent's piece
					if(g[c.y][c.x-i].team!=team)
						possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y,c.x-i),null));

					//done searching for moves in this direction
					six=false;
				}
			}
			
			//search in direction seven (east)
			if(c.y+i>=0 && c.y+i<=7 && seven) {
				//add a possible move to the ArrayList if the space is empty
				if(g[c.y+i][c.x] == null)	{
					possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+i,c.x),null));
				} else {
					//found a possible move to capture opponent's piece
					if(g[c.y+i][c.x].team!=team)
						possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y+i,c.x),null));

					//done searching for moves in this direction
					seven=false; 
				}
			}
			
			//search in direction eight (west)
			if(c.y-i>=0 && c.y-i<=7 && eight) {
				//add a possible move to the ArrayList if the space is empty
				if(g[c.y-i][c.x] == null) {
					possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-i,c.x),null));
				} else {
					//found a possible move to capture opponent's piece
					if(g[c.y-i][c.x].team!=team)
						possibleMoves.add(new Pair<Coord, Coord>(new Coord(c.y-i,c.x),null));
					
					//done searching for moves in this direction
					eight=false;
				}
			}
			
		}
		
		return possibleMoves;
	}
}
