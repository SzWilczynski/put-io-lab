package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailureOrErrorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp()
    {
        calculator = new Calculator();
    }

    @Test
    public void test1()
    {
        assert calculator.addPositiveNumbers(2, 2) == 5: "2 + 2";
    }

    @Test
    public void test2()
    {
        assert calculator.addPositiveNumbers(-2, 2) == 5: "-2 + 2";
    }

    @Test
    public void test3()
    {
        try {
            assert calculator.addPositiveNumbers(3, 3) == 9: "3 + 3";
        }catch (Throwable e)
        {
            e.printStackTrace();
        }
    }
}