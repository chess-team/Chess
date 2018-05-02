import org.junit.jupiter.api.Test;
import Chess.*;
//TODO refactor this using some framework
class AllTests {
    @Test
    void allTests(){
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
