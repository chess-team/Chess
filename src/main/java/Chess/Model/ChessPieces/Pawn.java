package Chess.Model.ChessPieces;

import Chess.Model.ChessColour;
import Chess.Model.Moves.Move;
import Chess.Model.Position;
import Chess.Model.StateOfGame;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    {
        label = 'P';
    }

    public Pawn(ChessColour a, Position b) {
        super(a, b);
    }

    static public boolean enPassantDisabled = false;

    @Override
    public ArrayList<Move> listOfPossibleMoves() {
        ArrayList<Move> resultList = new ArrayList<>();

        int y;
        switch (this.getChessColour()) {
            case BLACK:
                y = -1;
                break;
            case WHITE:
                y = 1;
                break;
            default:
                throw new RuntimeException();
        }
        for (int i = -1; i <= 1; ++i) {
            Move v = new Move(getPosition(),
                    getPosition().translateByVector(i, y));

            if (v.to.y == 0 || v.to.y == StateOfGame.chessboard.getYWidth() - 1) {
                ArrayList<ChessPiece> temp = new ArrayList<>();
                temp.add(new Rook(getChessColour(), v.to));
                temp.add(new Knight(getChessColour(), v.to));
                temp.add(new Queen(getChessColour(), v.to));
                temp.add(new Bishop(getChessColour(), v.to));
                for (ChessPiece chessPiece : temp) {
                    Move u = new Move(v.from, v.to, chessPiece);
                    if (StateOfGame.variant.validateMove(u)) {
                        resultList.add(u);
                    }
                }
            } else {
                if (StateOfGame.variant.validateMove(v)) {
                    resultList.add(v);
                }
            }
        }
        Move v = new Move(getPosition(),
                getPosition().translateByVector(0, 2 * y));

        if (StateOfGame.variant.validateMove(v)) {
            resultList.add(v);
        }

        return resultList;
    }

    @Override
    public boolean isBreakingRules(Move move) {
        boolean whiteColor = false;
        int differenceOnYCoordinate = move.differenceOnYCoordinate();
        int differenceOnXCoordinate = move.differenceOnXCoordinate();

        if (differenceOnYCoordinate > 2 || differenceOnYCoordinate == 0) {
            return true;
        }

        if (differenceOnXCoordinate > 1) {
            return true;
        }

        switch (getChessColour()) {
            case WHITE:
                if (move.to.y - move.from.y < 0) {
                    return true;
                }
                whiteColor = true;
                break;
            case BLACK:
                if (move.to.y - move.from.y > 0) {
                    return true;
                }
                break;
            default:
                throw new RuntimeException();
        }
        if (differenceOnYCoordinate == 2) {
            if (differenceOnXCoordinate != 0) {
                return true;
            }
            if (whiteColor) {
                if (move.from.y != 1) {
                    return true;
                }
            } else if (move.from.y != 6) {
                return true;
            }
        }
        if (differenceOnXCoordinate != 0) {
            if (differenceOnYCoordinate != 1) {
                return true;
            }
            if (StateOfGame.chessboard.getChessPieceOnPosition(move.to)
                    instanceof EmptySquare) {
                if (!validateEnPassantMove(move)) {
                    return true;
                }
            } else if (StateOfGame.chessboard.
                    getChessPieceOnPosition(move.to).getChessColour() ==
                    StateOfGame.chessboard.
                            getChessPieceOnPosition(move.from).getChessColour()) {
                return true;
            }
        } else if (!(StateOfGame.chessboard.getChessPieceOnPosition(move.to)
                instanceof EmptySquare)) {
            return true;
        }

        boolean badPromotion;
        badPromotion = (!((move.to.y != StateOfGame.chessboard.getYWidth() - 1 &&
                move.to.y != 0) ||
                ((move.promoteTo instanceof Queen ||
                        move.promoteTo instanceof Knight ||
                        move.promoteTo instanceof Bishop ||
                        move.promoteTo instanceof Rook) &&
                        move.isPromotion)));

        return badPromotion ||
                move.to.y != StateOfGame.chessboard.getYWidth() - 1 &&
                        move.to.y != 0 && move.promoteTo != null;

    }

    @SuppressWarnings("SpellCheckingInspection")
    private boolean validateEnPassantMove(Move move) {
        if (enPassantDisabled) return false;
        int differenceOnXCoordinate = move.to.x - move.from.x;
        int differenceOnYCoordinate = move.to.y - move.from.y;
        Position wantedPreviousMoveTo = move.from.translateByVector(
                differenceOnXCoordinate, 0);
        if (!(StateOfGame.chessboard.
                getChessPieceOnPosition(wantedPreviousMoveTo)
                instanceof Pawn)) {
            return false;
        }
        Position wantedPreviousMoveFrom = move.from.translateByVector(
                differenceOnXCoordinate, 2 * differenceOnYCoordinate);

        Move wantedPreviousMove =
                new Move(wantedPreviousMoveFrom, wantedPreviousMoveTo);
        Move previousMove = StateOfGame.historyOfMoves.lastMove();
        return wantedPreviousMove.equals(previousMove);
    }

}
