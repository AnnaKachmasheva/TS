package lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add_2add3_5() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void subtract_5subtract2_3() {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.subtract(5, 2));
    }

    @Test
    void multiply_2multiply3_6() {
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void divide_6divide2_3() throws Exception {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.divide(6, 2));
    }

    @Test
    void divide_6divide0_newException() {
        Calculator calculator = new Calculator();
        assertThrows(Exception.class, () -> calculator.divide(6, 0));
    }
}