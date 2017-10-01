public class Coord implements Comparable<Coord> {
	public final int y, x;//a is cols, b is rows
	public Coord(int a, int b) {
		y = a;
		x = b;
	}
	
	public Coord(Coord a) {
		y = a.y;
		x = a.x;
	}
	
	public boolean equals(Coord a) {
		return a.y==y && a.x==x;
	}
	
	public int compareTo(Coord c) {
		if(c.y==y && c.x==x) {
			return 0;
		} else if (y<c.y || y==c.y && x<c.x) {
			return -1;
		} else {
			return 1;
		}
	}
}
