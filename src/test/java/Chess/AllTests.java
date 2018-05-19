package Chess;

import Chess.Model.ArtificialIntelligence.AITest;
import Chess.Model.ArtificialIntelligence.anyMoveAI;
import Chess.Model.ChessPieces.*;
import Chess.Model.GameVariants.*;
import org.junit.jupiter.api.Test;

class AllTests {
    @Test
    void allTests(){
        new AITest().testAI(new anyMoveAI());
        new BishopTest().testBishopMovement();
        new Chess960Test().test();
        new ClassicChessTest().testInitializeState();

        new DunsanysChessTest().testInitializeState();
        new KingTest().testKingProtection();
        new KnightTest().testKnightMovement();
        new MonsterChessTest().testInitializeState();
        new PawnTest().testPawnMovement();
        new PawnTest().testEnPassant();
        new PawnTest().testPromotion();
        new QueenTest().testQueenMovement2();
        new RookTest().testRookMovement();
        new UpsideDownChessTest().testInitializeState();

        new ClassicChessTest().testCastling();
        new ClassicChessTest().testDraw();
        new ClassicChessTest().testWin();
    }
}
