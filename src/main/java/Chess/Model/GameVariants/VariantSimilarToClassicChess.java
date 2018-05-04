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

public abstract class VariantSimilarToClassicChess implements VariantOfGame {

    static {StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;}

    public SpecialMoves getSpecialMoves() {
        return new NoSpecialMoves();
    }

    // checks if move is inside board
    boolean isOutsideBoard(Move move){
        if(move == null ||
                move.to == null ||
                move.from == null) {
            return true;
        }
        if(move.to.x < 0 || move.to.x >= 8){
            return true;
        }

        if(move.to.y < 0 || move.to.y >= 8){
            return true;
        }
        return false;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public boolean validateEnPassantMove(Move move){
        return false;
    }

    // checks if move doesn't violate any rules about moving figures
    // without constraints about Chess.Model.ChessPieces.King protection.
    boolean isMovePossibleWithoutKingProtection(Move move){
        return !(move.isBreakingRules());
    }

    // check if king is under attack
    boolean isKingUnderAttack(ChessColour kingColor) {
        Position positionOfKing = ChessUtil.getKingPosition(kingColor);
        return positionOfKing != null &&
                isPlaceUnderAttack(positionOfKing, ChessUtil.getOtherColor(kingColor));
    }

    // checks if enemy can attack some position
    // without constraint of protecting Chess.Model.ChessPieces.King
    boolean isPlaceUnderAttack(
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

    // check if Chess.Model.ChessPieces.King is under attack after move
    boolean isKingUnderAttackAfterMove(
            ChessColour kingColor, Move v){
        Position positionOfKing = ChessUtil.getKingPosition(kingColor);
        if(positionOfKing == null) {
            return false;
        }

        // function make move check if king is under attack and return figures
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
    void setLineOfPawns(int YCoordinate, ChessColour pawnsColor){
        for(int i = 0; i < 8; ++i){
            StateOfGame.chessboard.setFigure(
                    new Pawn(pawnsColor, new Position(i,YCoordinate)));
        }
    }

    // initialize chessboard with line of figures
    // permutation 1,2,3,4,5,6,7,8 - gives position like in standard
    // standard chess
    void setLineOfFigures(int YCoordinate, ChessColour figuresColor,
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

    void setLineOfFigures(int YCoordinate, ChessColour figuresColor){
        ArrayList <Integer> temp = new ArrayList<>();
        for(int i = 1; i <= 8; ++i){
            temp.add(i);
        }
        setLineOfFigures(YCoordinate, figuresColor, temp);
    }
    void setClassicState(){
        StateOfGame.stateOfGameplay = StateOfGameplay.WHITE_MOVE;
        StateOfGame.historyOfMoves.clear();
        StateOfGame.chessboard = new ClassicChessboard();
    }

}
