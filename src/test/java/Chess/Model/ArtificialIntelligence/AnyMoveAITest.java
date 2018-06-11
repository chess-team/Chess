package Chess.Model.ArtificialIntelligence;

import org.junit.jupiter.api.Test;

class AnyMoveAITest {

    //general AI test can be used to test other AI's
    @Test
    void testMove(){
        new AITest().testAI(new AnyMoveAI());
    }
}