import java.util.ArrayList;

abstract public class Piece {
	public boolean team;
	public int moves;
	public Coord c;
	
	//TODO:: all of the cardinal directions are off in the subclasses
	abstract public ArrayList<Pair<Coord,Coord>> returnMoves(Board b);
	
	public Piece copyPiece() {
		if(this.toString().equals("K")) //King
			return new King((King)this);
		
		if(this.toString().equals("Q")) //Queen
			return new Queen((Queen)this);

		if(this.toString().equals("P")) //Pawn
			return new Pawn((Pawn)this);
		
		if(this.toString().equals("B")) //Bishop
			return new Bishop((Bishop)this);
		
		if(this.toString().equals("H")) //Knight
			return new Knight((Knight)this);
		
		if(this.toString().equals("R")) //Rook
			return new Rook((Rook)this);
		
		//should never execute
		return null;
		//return this
	}
}
