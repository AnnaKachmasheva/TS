package DU1.cz.cvut.fel.ts1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KachamnnTest {

    @Test
    void factorialTest() {
        Kachamnn kachamnn = new Kachamnn();
        long result = kachamnn.factorial(3);
        assertEquals(result, 6);
    }
}