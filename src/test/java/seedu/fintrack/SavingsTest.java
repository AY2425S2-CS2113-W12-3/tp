package seedu.fintrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SavingsTest {

    @BeforeEach
    void setUp() {
        // Reset static fields before each test
        Savings.updateIncome(0);
        Savings.updateSavingsGoal(0);
    }

    @Test
    void testGetIncome() {
        Savings.updateIncome(5000);
        assertEquals(5000, Savings.getIncome(), "Expected income to be 5000");
    }

    @Test
    void testGetSavingsGoal() {
        Savings.updateSavingsGoal(2000);
        assertEquals(2000, Savings.getSavingsGoal(), "Expected savings goal to be 2000");
    }

    @Test
    void testUpdateIncome() {
        Savings.updateIncome(6000);
        assertEquals(6000, Savings.getIncome(), "Expected updated income to be 6000");
    }

    @Test
    void testUpdateSavingsGoal() {
        Savings.updateSavingsGoal(2500);
        assertEquals(2500, Savings.getSavingsGoal(), "Expected updated savings goal to be 2500");
    }

    @Test
    void testAddToSavings() {
        Savings savings = new Savings(5000, 2000, 1000);
        savings.addToSavings(500);
        assertEquals(1500, Savings.getCurrentSavings(), "Expected total savings to be 1500 after adding 500");
    }
}
