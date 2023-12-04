package put.io.testing.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp()
    {
        calculator = new Calculator();
    }

    @Test
    public void testAdd()
    {
        assert calculator.add(1, 2) == 3: "1 + 2";
        assert calculator.add(-5, -3) == -8: "-5 + -3";
        assert calculator.add(12, -3) == 9: "12 + -3";
    }

    @Test
    public void testMultiply()
    {
        assert calculator.multiply(1, 1) == 1: "1 * 1";
        assert calculator.multiply(3, 4) == 12: "3 * 4";
    }

    @Test
    public void testAddPositiveNumbers()
    {
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.addPositiveNumbers(-1, 2);
        });

        Assertions.assertEquals("The arguments must be positive", thrown.getMessage());
    }
}