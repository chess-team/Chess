
/*
Class that implements rules of Classic chess.
 */
public class ClassicChess implements VariantOfGame {

    ClassicChess(){
        initializeStateOfGame();
    }

    // return true if move is correct.
    public boolean validateMove(Move move) {
        if(move.to.x < 0 || move.to.x >= 8)return false;
        if(move.to.y < 0 || move.to.y >= 8)return false;
        if(StateOfGame.chessboard.getChessPieceOnPosition(move.from)
                instanceof EmptySquare)
                return false;
        if(!(StateOfGame.chessboard.getChessPieceOnPosition(move.from)
                instanceof Knight) && ChessUtil.isMovePassingThroughFigure(move)){
            return false;
        }
        return true;
    }

    public void initializeStateOfGame() {
        StateOfGame.chessboard = new ClassicChessboard();
        for(int i = 0; i < 8; ++i){
            Position place = new Position(i,1);
            StateOfGame.chessboard.setFigure(new Position(i,1),
                    new Pawn(Color.WHITE, new Position(i,1)));
        }
        for(int i = 0; i < 8; ++i){
            StateOfGame.chessboard.setFigure(new Position(i,6),
                    new Pawn(Color.BLACK, new Position(i,6)));
        }
        StateOfGame.chessboard.setFigure(new Position(0,7),
                new Rook(Color.BLACK, new Position(0,7)));

        StateOfGame.chessboard.setFigure(new Position(7,7),
                new Rook(Color.BLACK, new Position(7,7)));

        StateOfGame.chessboard.setFigure(new Position(0,0),
                new Rook(Color.WHITE, new Position(0,0)));

        StateOfGame.chessboard.setFigure(new Position(7,0),
                new Rook(Color.WHITE, new Position(7,0)));

        StateOfGame.chessboard.setFigure(new Position(1,7),
                new Knight(Color.BLACK, new Position(1,7)));

        StateOfGame.chessboard.setFigure(new Position(6,7),
                new Knight(Color.BLACK, new Position(6,7)));

        StateOfGame.chessboard.setFigure(new Position(1,0),
                new Knight(Color.WHITE, new Position(1,0)));

        StateOfGame.chessboard.setFigure(new Position(6,0),
                new Knight(Color.WHITE, new Position(6,0)));


        StateOfGame.chessboard.setFigure(new Position(2,7),
                new Bishop(Color.BLACK, new Position(2,7)));

        StateOfGame.chessboard.setFigure(new Position(5,7),
                new Bishop(Color.BLACK, new Position(5,7)));

        StateOfGame.chessboard.setFigure(new Position(2,0),
                new Bishop(Color.WHITE, new Position(2,0)));

        StateOfGame.chessboard.setFigure(new Position(5,0),
                new Bishop(Color.WHITE, new Position(5,0)));


        StateOfGame.chessboard.setFigure(new Position(4,7),
                new King(Color.BLACK, new Position(4,7)));

        StateOfGame.chessboard.setFigure(new Position(4,0),
                new King(Color.WHITE, new Position(4,0)));


        StateOfGame.chessboard.setFigure(new Position(3,7),
                new Queen(Color.BLACK, new Position(3,7)));

        StateOfGame.chessboard.setFigure(new Position(3,0),
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
    }
}
