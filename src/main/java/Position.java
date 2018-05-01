/*
Class holds information about position (coordinates) of figure on chessboard.
 */
public class Position {

    // Uses standard x,y coordinates with coordinate system in left, bottom
    // corner.
    public final int x;
    public final int y;

    Position(int a, int b){
        this.x = a;
        this.y = b;
    }

    // position after translation by a vector [a,b]
    public Position translateByVector(int a, int b){
        return new Position(x+a, y+b);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Position) &&
                (obj.toString().equals(this.toString()));
    }
}
