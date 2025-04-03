package seedu.fintrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SavingsTest {

    @BeforeEach
    void setUp() {
        // Reset static fields before each test
        Savings.updateIncome(0);
        Savings.updateSavingsGoal(0);
        Savings.updateTotalSavings(0);
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

    @Test
    void testAddToSavingsWithNegativeValue() {
        Savings savings = new Savings(5000, 2000, 1000);
        savings.addToSavings(-500);
        assertEquals(500, Savings.getCurrentSavings(), "Expected total savings to be 500 after adding -500");
    }

    @Test
    void testAddIncomeNewMonth() {
        Savings savings = new Savings(5000, 2000, 1000);
        savings.addIncome();

        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String currentMonth = monthFormat.format(new Date());
        assertEquals(currentMonth, savings.currentMonth, "Expected current month to be updated");
    }


    @Test
    void testCalculateMonthlyExpensesWithDifferentMonths() {
        ExpenseList expenseList = new ExpenseList();

        expenseList.addExpense(new Expense(1500, "Food", "Dinner", new Date(1000L)));
        expenseList.addExpense(new Expense(1500, "Food", "Dinner", new Date(2000L)));

        assertEquals(0, Savings.calculateMonthlyExpenses(expenseList), "Expected total" +
                " monthly expenses to exclude past months");
    }
}
