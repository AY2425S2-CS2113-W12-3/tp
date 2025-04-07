package seedu.fintrack;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RecurringExpenseTest {
    @Test
    void getAmount() {
        // Create an Expense with an amount of 1500 cents.
        RecurringExpense expense = new RecurringExpense(1500, "Food",
                "Monthly", "Dinner", new Date(1000L), new Date(1000L));

        // Use JUnit assertEquals to verify that getAmount returns 1500.
        assertEquals(1500, expense.getAmount(), "Expected amount to be 1500 cents");
    }

    @Test
    void getCategory() {
        // Create an Expense with the category "Food".
        RecurringExpense expense = new RecurringExpense(1500, "Food",
                "Monthly", "Dinner", new Date(1000L), new Date(1000L));
        assertEquals("Food", expense.getCategory(), "Expected category to be 'Food'");
    }

    @Test
    void getDescription() {
        // Create an Expense with the description "Dinner".
        RecurringExpense expense = new RecurringExpense(1500, "Food",
                "Monthly", "Dinner", new Date(1000L), new Date(1000L));
        assertEquals("Dinner", expense.getDescription(), "Expected description to be 'Dinner'");
    }

    @Test
    void getDate() {
        // Create a fixed Date for testing.
        Date testDate = new Date(1000L);
        RecurringExpense expense = new RecurringExpense(1500, "Food",
                "Monthly", "Dinner", new Date(1000L), new Date(1000L));
        assertEquals(testDate, expense.getDate(), "Expected date to match the provided date");
    }

    @Test
    void getStartDate() {
        Date testDate = new Date(1000L);
        RecurringExpense expense = new RecurringExpense(1500, "Food",
                "Monthly", "Dinner", new Date(1000L), new Date(1000L));
        assertEquals(testDate, expense.getStartDate(), "Expected date to match the provided date");
    }

    @Test
    void getFrequency() {
        RecurringExpense expense = new RecurringExpense(1500, "Food",
                "Monthly", "Dinner", new Date(1000L), new Date(1000L));
        assertEquals("Monthly", expense.getFrequency(), "Expected frequency to be 'Monthly'");
    }

    @Test
    void getLastProcessedDate() {
        RecurringExpense expense = new RecurringExpense(1500, "Food",
                "Monthly", "Dinner", new Date(1000L), new Date(1000L));
        assertEquals(new Date(1000L), expense.getLastProcessedDate(), "Expected last processed date to be initially null");
    }

    @Test
    void setLastProcessedDate() {
        RecurringExpense expense = new RecurringExpense(1500, "Food",
                "Monthly", "Dinner", new Date(1000L), new Date(1000L));
        Date newDate = new Date(2000L);
        expense.setLastProcessedDate(newDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        assertEquals(calendar.getTime(), expense.getLastProcessedDate(),
                "Expected last processed date to be updated to the new date");
    }
}
