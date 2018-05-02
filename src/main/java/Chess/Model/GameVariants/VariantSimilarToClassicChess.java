package Chess.Model.GameVariants;

import Chess.Model.*;
import Chess.Model.ChessPieces.*;
import Chess.Model.Moves.Move;
import Chess.Model.Moves.NoSpecialMoves;
import Chess.Model.Moves.SpecialMoves;

import java.util.ArrayList;

/*
Class with methods used in variant of Chess with movement of pieces the same
or similar to standard chess with different objectives, special moves,
    */
@SuppressWarnings({"RedundantIfStatement", "SimplifiableIfStatement"})
public abstract class VariantSimilarToClassicChess implements VariantOfGame {

    public SpecialMoves getSpecialMoves() {
        return new NoSpecialMoves();
    }

    // checks if move is inside board.
    @SuppressWarnings({"RedundantIfStatement", "BooleanMethodIsAlwaysInverted"})
    public boolean isInsideBoard(Move move){
        if(move.to.x < 0 || move.to.x >= 8){
            return false;
        }

        if(move.to.y < 0 || move.to.y >= 8){
            return false;
        }
        return true;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public boolean validateEnPassantMove(Move move){
        return false;
    }

    // checks if move doesn't violate any rules about moving figures
    // without constraints about Chess.Model.ChessPieces.King protection.
    public boolean isMovePossibleWithoutKingProtection(Move move){
        if(!isInsideBoard(move)){
            return false;
        }

        if(StateOfGame.chessboard.getChessPieceOnPosition(
                move.from).getChessColour() ==
                StateOfGame.chessboard.
                        getChessPieceOnPosition(move.to).getChessColour()){
            return false;
        }

        // checks if there is figure moved.
        if(StateOfGame.chessboard.getChessPieceOnPosition(move.from)
                instanceof EmptySquare){
            return false;
        }

        ChessPiece movedChessPiece = StateOfGame.chessboard.
                getChessPieceOnPosition(move.from);

        int differenceOnXCoordinate = Math.abs(move.from.x - move.to.x);
        int differenceOnYCoordinate = Math.abs(move.from.y - move.to.y);
        if(differenceOnXCoordinate + differenceOnYCoordinate == 0){
            return false;
        }
        if(movedChessPiece instanceof Knight){
            if (!(Math.min(differenceOnXCoordinate,
                    differenceOnYCoordinate) == 1
                    && Math.max(differenceOnXCoordinate,
                    differenceOnYCoordinate) == 2)){
                return false;
            }
        }
        else if(movedChessPiece instanceof Rook){
            if (!(differenceOnXCoordinate == 0 ||
                    differenceOnYCoordinate == 0)){
                return false;
            }
        }
        else if(movedChessPiece instanceof Bishop){
            if(!(differenceOnXCoordinate ==
                    differenceOnYCoordinate)){
                return false;
            }
        }
        else if(movedChessPiece instanceof Queen){
            if (!(differenceOnXCoordinate ==
                    differenceOnYCoordinate ||
                    differenceOnXCoordinate == 0 ||
                    differenceOnYCoordinate == 0)){
                return false;
            }
        }
        else if(movedChessPiece instanceof Pawn){
            boolean whiteColor = false;
            if(differenceOnYCoordinate > 2){
                return false;
            }
            switch (movedChessPiece.getChessColour()){
                case WHITE:
                    if(move.to.y - move.from.y < 0){
                        return false;
                    }
                    whiteColor = true;
                    break;
                case BLACK:
                    if(move.to.y - move.from.y > 0){
                        return false;
                    }
                    break;
            }
            if(differenceOnYCoordinate == 2){
                if(differenceOnXCoordinate != 0) {
                    return false;
                }
                if(whiteColor){
                    if(move.from.y != 1){
                        return false;
                    }
                }
                else if(move.from.y != 6){
                    return false;
                }
            }
            if(differenceOnXCoordinate != 0){
                if(StateOfGame.chessboard.getChessPieceOnPosition(move.to)
                        instanceof EmptySquare ){
                    if(!validateEnPassantMove(move)){
                        return false;
                    }
                }
                else if(StateOfGame.chessboard.
                        getChessPieceOnPosition(move.to).getChessColour() ==
                        StateOfGame.chessboard.
                                getChessPieceOnPosition(move.from).getChessColour()){
                    return false;
                }
            }
            else if(!(StateOfGame.chessboard.getChessPieceOnPosition(move.to)
                    instanceof EmptySquare)){
                return false;
            }

            if (!((move.to.y != 7 && move.to.y != 0) ||
                    ((move.promoteTo instanceof Queen ||
                            move.promoteTo instanceof Knight ||
                            move.promoteTo instanceof Bishop ||
                            move.promoteTo instanceof Rook) &&
                            move.isPromotion))){
                return false;
            }


        }
        else if(movedChessPiece instanceof King){
            if (!(differenceOnXCoordinate <= 1 &&
                    differenceOnYCoordinate <= 1)){
                return false;
            }
        }

        // checks if move is not blocked by some chess piece
        if(!(StateOfGame.chessboard.getChessPieceOnPosition(move.from)
                instanceof Knight) && ChessUtil.isMovePassingThroughFigure(move)){
            return false;
        }
        return true;
    }

    // check if Chess.Model.ChessPieces.King is under attack after move
    protected boolean isKingUnderAttack(ChessColour kingColor){
        Position positionOfKing = ChessUtil.getKingPosition(kingColor);
        if(positionOfKing == null) {
            return false;
        }
        return isPlaceUnderAttack(positionOfKing,
                ChessUtil.getOtherColor(kingColor));
    }

    // checks if enemy can attack some position
    // without constraint of protecting Chess.Model.ChessPieces.King
    protected boolean isPlaceUnderAttack(
            Position place, ChessColour enemyColor) {

        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                ChessColour colorOfChessPiece = StateOfGame.chessboard.
                        getChessPieceOnPosition(new Position(i,j)).getChessColour();
                if(colorOfChessPiece != enemyColor)continue;
                if(isMovePossibleWithoutKingProtection(
                        new Move(new Position(i,j), place))){
                    return true;
                }
            }
        }
        return false;
    }

    public void undoMove(){
        StateOfGame.historyOfMoves.undoMove();
    }

    // check if Chess.Model.ChessPieces.King is under attack after move
    public boolean isKingUnderAttackAfterMove(
            ChessColour kingColor, Move v){
        Position positionOfKing = ChessUtil.getKingPosition(kingColor);
        if(positionOfKing == null) {
            return false;
        }

        // function make move check if Chess.Model.ChessPieces.King is under attack and return figures
        // to previous state.
        ChessPiece tempTo = StateOfGame.chessboard.
                getChessPieceOnPosition(v.to);
        ChessPiece tempFrom = StateOfGame.chessboard.
                getChessPieceOnPosition(v.from);

        StateOfGame.chessboard.moveFigure(v);
        boolean ret = isKingUnderAttack(kingColor);
        tempFrom.setPosition(v.from);
        tempTo.setPosition(v.to);
        StateOfGame.chessboard.setFigure(tempFrom);
        StateOfGame.chessboard.setFigure(tempTo);
        return ret;
    }

    // set on chessboard line of pawns.
    public void setLineOfPawns(int YCoordinate, ChessColour pawnsColor){
        for(int i = 0; i < 8; ++i){
            StateOfGame.chessboard.setFigure(
                    new Pawn(pawnsColor, new Position(i,YCoordinate)));
        }
    }

    // initialize chessboard with line of figures
    // permutation 1,2,3,4,5,6,7,8 - gives position like in standard
    // standard chess
    public void setLineOfFigures(int YCoordinate, ChessColour figuresColor,
                          ArrayList<Integer> permutation){

        StateOfGame.chessboard.setFigure(
                new Rook(figuresColor, new Position(
                        permutation.get(0) - 1, YCoordinate)));

        StateOfGame.chessboard.setFigure(
                new Rook(figuresColor, new Position(
                        permutation.get(7) - 1, YCoordinate)));

        StateOfGame.chessboard.setFigure(
                new Knight(figuresColor, new Position(
                        permutation.get(1) - 1, YCoordinate)));

        StateOfGame.chessboard.setFigure(
                new Knight(figuresColor, new Position(
                        permutation.get(6) - 1, YCoordinate)));

        StateOfGame.chessboard.setFigure(
                new Bishop(figuresColor, new Position(
                        permutation.get(2) - 1,YCoordinate)));

        StateOfGame.chessboard.setFigure(
                new Bishop(figuresColor, new Position(
                        permutation.get(5) - 1,YCoordinate)));

        StateOfGame.chessboard.setFigure(
                new King(figuresColor, new Position(
                        permutation.get(4) - 1,YCoordinate)));

        StateOfGame.chessboard.setFigure(
                new Queen(figuresColor, new Position(
                        permutation.get(3) - 1,YCoordinate)));

    }

    public void setLineOfFigures(int YCoordinate, ChessColour figuresColor){
        ArrayList <Integer> temp = new ArrayList<Integer>();
        for(int i = 1; i <= 8; ++i){
            temp.add(i);
        }
        setLineOfFigures(YCoordinate, figuresColor, temp);
    }
}
