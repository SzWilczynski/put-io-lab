package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {
    private Audiobook audiobook;
    private AudiobookPriceCalculator audiobookPriceCalculator;

    @BeforeEach
    public void setUp()
    {
        audiobook = new Audiobook("testowy audiobook", 100);
        audiobookPriceCalculator = new AudiobookPriceCalculator();
    }

    @Test
    public void testDefault()
    {
        Customer customer = new Customer("test", Customer.LoyaltyLevel.STANDARD, false);
        assert audiobookPriceCalculator.calculate(customer, audiobook) == 100.0;
    }

    @Test
    public void testSubscribed()
    {
        Customer customer = new Customer("test", Customer.LoyaltyLevel.STANDARD, true);
        assert audiobookPriceCalculator.calculate(customer, audiobook) == 0.0;
    }

    @Test
    public void testSilver()
    {
        Customer customer = new Customer("test", Customer.LoyaltyLevel.SILVER, false);
        assert audiobookPriceCalculator.calculate(customer, audiobook) == 90.0;
    }

    @Test
    public void testGold()
    {
        Customer customer = new Customer("test", Customer.LoyaltyLevel.GOLD, false);
        assert audiobookPriceCalculator.calculate(customer, audiobook) == 80.0;
    }
}