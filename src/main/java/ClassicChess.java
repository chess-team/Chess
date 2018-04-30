
/*
Class that implements rules of Classic chess.
 */
public class ClassicChess implements VariantOfGame {

    ClassicChess(){ }

    // checks if move is inside board.
    public boolean isInsideBoard(Move move){
        if(move.to.x < 0 || move.to.x >= 8){
            return false;
        }

        if(move.to.y < 0 || move.to.y >= 8){
            return false;
        }
        return true;
    }

    // checks if move doesn't violate any rules about moving figures
    // without constraints about King protection.
    protected boolean isMovePossibleWithoutKingProtection(Move move){

        if(StateOfGame.chessboard.getChessPieceOnPosition(
                move.from).getColor() ==
                StateOfGame.chessboard.
                        getChessPieceOnPosition(move.to).getColor()){
            return false;
        }

        // checks if there is figure moved.
        if(StateOfGame.chessboard.getChessPieceOnPosition(move.from)
                instanceof EmptySquare){
            return false;
        }

        // checks if move is not blocked by some chess piece
        if(!(StateOfGame.chessboard.getChessPieceOnPosition(move.from)
                instanceof Knight) && ChessUtil.isMovePassingThroughFigure(move)){
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
            return (Math.min(differenceOnXCoordinate,
                    differenceOnYCoordinate) == 1
                    && Math.max(differenceOnXCoordinate,
                    differenceOnYCoordinate) == 2);
        }
        else if(movedChessPiece instanceof Rook){
            return (differenceOnXCoordinate == 0 ||
                    differenceOnYCoordinate == 0);
        }
        else if(movedChessPiece instanceof Bishop){
            return (differenceOnXCoordinate ==
                    differenceOnYCoordinate);
        }
        else if(movedChessPiece instanceof Queen){
            return (differenceOnXCoordinate ==
                    differenceOnYCoordinate ||
                    differenceOnXCoordinate == 0 ||
                    differenceOnYCoordinate == 0);
        }
        else if(movedChessPiece instanceof Pawn){
            boolean whiteColor = false;
            if(differenceOnYCoordinate > 2){
                return false;
            }
            switch (movedChessPiece.getColor()){
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
                    return false;
                }
                else if(StateOfGame.chessboard.
                        getChessPieceOnPosition(move.to).getColor() ==
                        StateOfGame.chessboard.
                                getChessPieceOnPosition(move.from).getColor()){
                    return false;
                }
            }
            else if(!(StateOfGame.chessboard.getChessPieceOnPosition(move.to)
                    instanceof EmptySquare)){
                return false;
            }
            return (move.to.y != 7 && move.to.y != 0) ||
                    ((move.promoteTo instanceof Queen ||
                            move.promoteTo instanceof Knight ||
                            move.promoteTo instanceof Bishop ||
                            move.promoteTo instanceof Rook) &&
                            move.isPromotion);
        }
        else if(movedChessPiece instanceof King){
            return differenceOnXCoordinate <= 1 &&
                    differenceOnYCoordinate <= 1;
        }
        return true;
    }

    // check if King is under attack after move
    protected boolean isKingUnderAttackAfterMove(
            Color kingColor, Move v){
        Position positionOfKing = ChessUtil.getKingPosition(kingColor);
        return false;
    }

    // checks if enemy can attack some position
    // without constraint of protecting King
    protected boolean isPlaceUnderAttack(
            Position place, Color enemyColor) {

        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                Color colorOfChessPiece = StateOfGame.chessboard.
                        getChessPieceOnPosition(new Position(i,j)).getColor();
                if(colorOfChessPiece != enemyColor)continue;
                if(isMovePossibleWithoutKingProtection(
                        new Move(new Position(i,j), place))){
                    return true;
                }
            }
        }
        return false;
    }

    // check if King is under attack after move
    public boolean isKingUnderAttack(Color kingColor){
        Position positionOfKing = ChessUtil.getKingPosition(kingColor);
        return isPlaceUnderAttack(positionOfKing,
                ChessUtil.getOtherColor(kingColor));
    }

    // return true if move is correct.
    public boolean validateMove(Move move) {

        if(!isInsideBoard(move)) {
            return false;
        }

        if(!isMovePossibleWithoutKingProtection(move)) {
            return false;
        }

        Color colorOfPlayer = StateOfGame.chessboard.
                getChessPieceOnPosition(move.from).getColor();

        if(isKingUnderAttackAfterMove(colorOfPlayer, move)){
            return false;
        }

        return true;
    }

    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        for(int i = 0; i < 8; ++i){
            StateOfGame.chessboard.setFigure(
                    new Pawn(Color.WHITE, new Position(i,1)));
        }
        for(int i = 0; i < 8; ++i){
            StateOfGame.chessboard.setFigure(
                    new Pawn(Color.BLACK, new Position(i,6)));
        }
        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(0,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.BLACK, new Position(7,7)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(0,0)));

        StateOfGame.chessboard.setFigure(
                new Rook(Color.WHITE, new Position(7,0)));

        StateOfGame.chessboard.setFigure(
                new Knight(Color.BLACK, new Position(1,7)));

        StateOfGame.chessboard.setFigure(
                new Knight(Color.BLACK, new Position(6,7)));

        StateOfGame.chessboard.setFigure(
                new Knight(Color.WHITE, new Position(1,0)));

        StateOfGame.chessboard.setFigure(
                new Knight(Color.WHITE, new Position(6,0)));


        StateOfGame.chessboard.setFigure(
                new Bishop(Color.BLACK, new Position(2,7)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.BLACK, new Position(5,7)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.WHITE, new Position(2,0)));

        StateOfGame.chessboard.setFigure(
                new Bishop(Color.WHITE, new Position(5,0)));


        StateOfGame.chessboard.setFigure(
                new King(Color.BLACK, new Position(4,7)));

        StateOfGame.chessboard.setFigure(
                new King(Color.WHITE, new Position(4,0)));


        StateOfGame.chessboard.setFigure(
                new Queen(Color.BLACK, new Position(3,7)));

        StateOfGame.chessboard.setFigure(
                new Queen(Color.WHITE, new Position(3,0)));

    }

    public void changeState(Move change) {
        switch (StateOfGame.colorOfLastMovedPiece){
            case BLACK:
                StateOfGame.colorOfLastMovedPiece = Color.WHITE;
                break;
            case WHITE:
                StateOfGame.colorOfLastMovedPiece = Color.BLACK;
                break;
        }
        StateOfGame.chessboard.moveFigure(change);
        StateOfGame.historyOfMoves.addMove(change);
    }

    public void undoMove(){
        StateOfGame.historyOfMoves.undoMove();
    }
}
