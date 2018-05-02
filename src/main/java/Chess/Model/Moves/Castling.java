package Chess.Model.Moves;

import Chess.Model.Position;

public class Castling extends SpecialMove{
    private final Position towerPosition;
    public Castling(Position positionOfTower){
        towerPosition = positionOfTower;
    }

    public Position getTowerPosition() {
        return towerPosition;
    }
}
