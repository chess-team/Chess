import org.junit.jupiter.api.Test;

class AllTests {
    @Test
    void allTests(){
        new BishopTest().testBishopMovement();
        new KnightTest().testKnightMovement();
        new PawnTest().testPawnMovement();
        new QueenTest().testQueenMovement1();
        new QueenTest().testQueenMovement2();
        new RookTest().testRookMovement();
    }
}
