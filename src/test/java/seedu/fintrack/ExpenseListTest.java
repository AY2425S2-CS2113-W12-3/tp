package seedu.fintrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseListTest {
    private ExpenseList expenseList;

    @BeforeEach
    void setUp() {
        expenseList = new ExpenseList();
        expenseList.setMonthlyBudget(1000); // Set an initial budget
    }

    @Test
    void testAddExpense() {
        Expense expense = new Expense(100, "Food", "Lunch", new Date());
        expenseList.addExpense(expense);

        assertEquals(1, expenseList.size());
        assertEquals(900, expenseList.getRemainingBudget());
    }

    @Test
    void testDeleteExpense() {
        Expense expense = new Expense(50, "Transport", "Bus fare", new Date());
        expenseList.addExpense(expense);
        expenseList.deleteExpense(expense);

        assertEquals(0, expenseList.size());
    }

    @Test
    void testSetMonthlyBudget() {
        expenseList.setMonthlyBudget(2000);
        assertEquals(2000, expenseList.getMonthlyBudget());
        assertEquals(2000, expenseList.getRemainingBudget());
    }

    @Test
    void testAddRecurringExpenseWeekly() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.WEEK_OF_YEAR, -6);
        Date newDate = calendar.getTime();
        RecurringExpense recurringExpense = new RecurringExpense(50, "Food", "weekly", "McDonalds",
                newDate, newDate);

        expenseList.addRecurringExpense(recurringExpense);
        expenseList.addAllRecurringExpenses();


        List<RecurringExpense> recurringList = expenseList.getRecurringExpenses();
        assertEquals(1, recurringList.size());
        assertTrue(expenseList.getExpenseList().size() >= 5); // At least 5 weeks' expenses
    }

    @Test
    void testAddRecurringExpenseMonthly() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, -3);
        Date newDate = calendar.getTime();

        RecurringExpense recurringExpense = new RecurringExpense(50, "Food", "monthly", "McDonalds",
                newDate, newDate);

        expenseList.addRecurringExpense(recurringExpense);
        expenseList.addAllRecurringExpenses();

        assertEquals(1, expenseList.getRecurringExpenses().size());
        assertEquals(4,expenseList.getExpenseList().size()); // At least 3 months' expenses
    }

    @Test
    void testAddRecurringExpenseYearly() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.YEAR, -2);
        Date newDate = calendar.getTime();

        RecurringExpense recurringExpense = new RecurringExpense(50, "Food", "yearly", "McDonalds",
                newDate, newDate);

        expenseList.addRecurringExpense(recurringExpense);
        expenseList.addAllRecurringExpenses();
        assertEquals(1, expenseList.getRecurringExpenses().size());
        assertTrue(expenseList.getExpenseList().size() >= 2); // At least 2 years' expenses
    }

    @Test
    void testUpdateRecurringExpense() {
        RecurringExpense recurringExpense = new RecurringExpense(
                100, "Food", "daily","Lunch", new Date(), new Date()
        );
        expenseList.addRecurringExpense(recurringExpense);

        RecurringExpense updatedExpense = new RecurringExpense(
                150, "Transport", "weekly","Bus", new Date(), new Date()
        );
        Date currentDate = new Date();
        updatedExpense.setLastProcessedDate(currentDate);
        expenseList.updateRecurringExpense(1, updatedExpense);

        Expense result = expenseList.getRecurringExpenses().get(0);
        assertEquals(150, result.getAmount());
        assertEquals("Transport", result.getCategory());
        assertEquals("Bus", result.getDescription());
    }

    @Test
    void testAddAllRecurringExpenses() {
        ExpenseList recurringAddAllTest = new ExpenseList();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -5);
        Date fiveMonthsAgo = calendar.getTime();
        RecurringExpense recurring1 = new RecurringExpense(
                150, "Food", "monthly","bubble",
                fiveMonthsAgo, fiveMonthsAgo);
        RecurringExpense recurring2 = new RecurringExpense(
                150, "Food", "monthly","tea",
                fiveMonthsAgo, fiveMonthsAgo);
        recurringAddAllTest.addRecurringExpense(recurring1);
        recurringAddAllTest.addRecurringExpense(recurring2);
        recurringAddAllTest.addAllRecurringExpenses();
        assertEquals(12, recurringAddAllTest.size());
    }

    @Test
    void testAddRecurringExpenses() {
        ExpenseList recurringAddTest = new ExpenseList();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -5);
        Date fiveMonthsAgo = calendar.getTime();
        RecurringExpense recurring = new RecurringExpense(
                150, "Food", "monthly","Bus",
                fiveMonthsAgo, fiveMonthsAgo);
        recurringAddTest.addRecurringExpenses(recurring);
        assertEquals(6,recurringAddTest.size());
    }

    @Test
    void testRemainingBudgetAfterMultipleExpenses() {
        expenseList.addExpense(new Expense(200, "Food", "Dinner", new Date()));
        expenseList.addExpense(new Expense(300, "Shopping", "Clothes", new Date()));
        expenseList.addExpense(new Expense(100, "Transport", "Taxi", new Date()));

        assertEquals(400, expenseList.getRemainingBudget());
    }
}
