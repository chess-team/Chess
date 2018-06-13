package Chess.Model.Moves;

import Chess.Model.*;

public class Castling extends SpecialMove {
    private Position towerPosition;

    static public boolean castlingDisabled = false;

    public Castling(Position positionOfTower) {
        towerPosition = positionOfTower;
    }

    public Position getTowerPosition() {
        if(towerPosition == null){
            towerPosition = from.translateByVector(ChessUtil.signum(from.x - to.x), 0);
        }
        return towerPosition;
    }

    @Override
    public String toString() {
        return "castling - tower position:" + towerPosition.toString();
    }

    @Override
    public StateOfGameplay getMoveColor() {
        switch (StateOfGame.chessboard.
                getChessPieceOnPosition(towerPosition).getChessColour()) {
            case BLACK:
                return StateOfGameplay.BLACK_MOVE;
            case WHITE:
                return StateOfGameplay.WHITE_MOVE;
        }
        return null;
    }

    @Override
    public boolean isBreakingMoveRules() {
        return castlingDisabled;
    }

    @Override
    public void changeState() {}

    @Override
    public boolean isBreakingRules() {
        return false;
    }
}
