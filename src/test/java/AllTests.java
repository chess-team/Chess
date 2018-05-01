import org.junit.jupiter.api.Test;

class AllTests {
    @Test
    void allTests(){
        new ClassicChessTest().testInitializeState();
        new BishopTest().testBishopMovement();
        new KnightTest().testKnightMovement();
        new PawnTest().testPawnMovement();
        new QueenTest().testQueenMovement1();
        new QueenTest().testQueenMovement2();
        new RookTest().testRookMovement();
        new KingTest().testKingProtection();
        new DunsanysChessTest().testInitializeState();
        new UpsideDownChessTest().testInitializeState();
        new MonsterChessTest().testInitializeState();

    }
}
