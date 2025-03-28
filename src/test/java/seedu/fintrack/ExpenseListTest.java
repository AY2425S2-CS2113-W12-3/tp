package seedu.fintrack;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpenseListTest {
    private ExpenseList expenseList = new ExpenseList();

    @Test
    void addExpense_storesExpenseCorrectly() {
        Expense expense = new Expense(2000, "Food", "Dinner", new Date());
        expenseList.addExpense(expense);
        assertEquals(expense, expenseList.getExpense(0));
    }

    @Test
    void deleteExpense_removesExpenseFromList() {
        Expense expense = new Expense(1500, "Transport", "Taxi", new Date());
        expenseList.addExpense(expense);
        expenseList.deleteExpense(expense);
        assertThrows(IndexOutOfBoundsException.class, () -> expenseList.getExpense(0));
    }
}
