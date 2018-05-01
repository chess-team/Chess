class Castling extends SpecialMove{
    private final Position towerPosition;
    Castling(Position positionOfTower){
        towerPosition = positionOfTower;
    }

    public Position getTowerPosition() {
        return towerPosition;
    }
}
