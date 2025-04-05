package seedu.fintrack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandsTest {

    // Dummy dependencies
    private DummyExpenseList expenseList;
    private DummyParser parser;
    private AllCommands commands;

    // Used to capture console output (e.g., messages printed by Ui)
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private PrintStream originalErr;


    @BeforeEach
    public void setUp() {
        expenseList = new DummyExpenseList();
        parser = new DummyParser();
        commands = new AllCommands(expenseList, parser);

        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Success test: when a valid expense is provided by the parser, the expense is added
     * and "Expense added." is printed.
     */
    @Test
    public void addExpense_validExpenseDetails_addsExpenseAndShowsSuccessMessage() throws FinTrackException {
        DummyExpense expense = new DummyExpense(1250, "Food", "Lunch", new Date());
        parser.setDummyExpense(expense);
        int sizeBefore = expenseList.size();
        commands.addExpense();

        assertEquals(sizeBefore + 1, expenseList.size(), "Expense list size should increment by 1.");
        String output = outputStream.toString();
        assertTrue(output.contains("Expense added."), "The success message should be printed.");
    }

    /**
     * Failure test: when the parser fails to read expense details (throws an exception),
     * no expense is added and an error message is printed.
     */
    @Test
    public void addExpense_invalidExpenseDetails_showsErrorMessageWithoutAddingExpense() {
        parser.setThrowExceptionOnReadExpense(true);
        int sizeBefore = expenseList.size();

        commands.addExpense();
        assertEquals(sizeBefore, expenseList.size(), "Expense list size should remain unchanged on failure.");
        String output = (outputStream.toString().toLowerCase());
        assertTrue(output.contains("type 'add' and enter"),
                "Error message should be printed on failure.");
    }

    // ===== viewHistory Tests =====

    @Test
    public void viewHistory_expenseListContainsOneExpense_showsExpenseDetails() {

        DummyExpense expense = new DummyExpense(800, "Food", "Dinner", new Date());
        expenseList.addExpense(expense);

        commands.viewHistory();

        String output = outputStream.toString();
        assertTrue(output.contains("Spending history:"), "Header should be printed.");
        assertTrue(output.contains("Dinner"), "Expense details should be printed.");
    }


    // ===== updateExpense Tests =====

    @Test
    public void updateExpense_validIndexAndDetails_updatesExpenseAndShowsSuccessMessage() {

        DummyExpense original = new DummyExpense(200, "Food", "Snack", new Date());
        expenseList.addExpense(original);

        parser.setIntInputs(new int[]{1});
        DummyExpense updatedExpense = new DummyExpense(250,"Food","Snack Updated",new Date());
        parser.setDummyExpense(updatedExpense);

        commands.updateExpense();

        DummyExpense result = (DummyExpense) expenseList.getExpense(0);
        assertEquals("Snack Updated", result.getDescription(),
                "Expense description should be updated.");
        String output = outputStream.toString();
        assertTrue(output.contains("Expense updated."), "Success message should be printed after update.");
    }

    @Test
    public void updateExpense_invalidIndex_maintainsOriginalExpenseAndShowsError() {

        DummyExpense expense = new DummyExpense(200, "Food", "Snack", new Date());
        expenseList.addExpense(expense);
        parser.setIntInputs(new int[]{0});

        commands.updateExpense();

        DummyExpense result = (DummyExpense) expenseList.getExpense(0);
        assertEquals("Snack", result.getDescription(),
                "Expense should remain unchanged on invalid index.");
        String output = outputStream.toString().toLowerCase();
        assertTrue(output.contains("invalid index"), "Error message for invalid index should be printed.");
    }

    // ===== deleteExpense Tests =====

    @Test
    public void deleteExpense_validIndex_removesExpenseAndShowsSuccessMessage() {

        DummyExpense expense = new DummyExpense(1200, "Entertainment", "Movie", new Date());
        expenseList.addExpense(expense);
        parser.setIntInputs(new int[]{1});
        int sizeBefore = expenseList.size();

        commands.deleteExpense();


        assertEquals(sizeBefore - 1, expenseList.size(), "Expense should be deleted successfully.");
        String output = outputStream.toString();
        assertTrue(output.contains("Expense deleted."), "Success message should be printed on deletion.");
    }

    @Test
    public void deleteExpense_invalidIndex_maintainsExpenseListAndShowsError() {

        DummyExpense expense = new DummyExpense(1200, "Entertainment", "Movie", new Date());
        expenseList.addExpense(expense);

        parser.setIntInputs(new int[]{5});
        int sizeBefore = expenseList.size();

        commands.deleteExpense();

        assertEquals(sizeBefore, expenseList.size(), "Expense list should remain unchanged on invalid index.");
        String output = outputStream.toString().toLowerCase();
        assertTrue(output.contains("invalid index"), "Error message for invalid deletion index " +
                "should be printed.");
    }

    // ===== addCategory Tests =====

    @Test
    public void addCategory_categoriesContainOneCategory_showsSuccessMessage() {
        int sizeBefore = Categories.size();
        parser.isValidCategory = true;
        commands.addCategory();

        assertEquals(sizeBefore + 1, Categories.size(), "Category list size should increment by 1.");
        String output = outputStream.toString();
        assertTrue(output.contains("Food has been added to the list of categories."),
                "The success message should be printed.");
    }

    @Test
    public void addCategory_categoriesContainOneCategory_showsErrorMessage() {
        parser.isValidCategory = false;
        commands.addCategory();

        String output = outputStream.toString();
        assertTrue(output.contains("Empty input. Please enter a category."),
                "The error message should be printed.");
    }

    @Test
    public void addCategory_categoriesAlreadyContainCategory_showsErrorMessage() {
        parser.isValidCategory = true;
        commands.addCategory();
        commands.addCategory();

        String output = outputStream.toString();
        assertTrue(output.contains("Category already exists."),
                "The success message should be printed.");
    }

    // ===== deleteCategory Tests =====

    @Test
    public void deleteCategory_categoriesContainOneCategory_showsSuccessMessage() {
        parser.isValidCategory = true;
        commands.addCategory();
        int sizeBefore = Categories.size();
        parser.setIntInputs(new int[]{1});
        commands.deleteCategory();

        assertEquals(sizeBefore - 1, Categories.size(), "Category list size should decrement by 1.");
        String output = outputStream.toString();
        assertTrue(output.contains("Food has been deleted."), "The success message should be printed.");
    }

    @Test
    public void deleteCategory_categoriesContainOneCategory_showsErrorMessage() {
        parser.isValidCategory = true;
        commands.addCategory();
        parser.setIntInputs(new int[]{-1});
        commands.deleteCategory();

        String output = outputStream.toString();
        assertTrue(output.contains("Invalid index."), "This error message should be printed.");
    }

    // ===== Dummy Implementations for Testing =====

    /**
     * DummyExpenseList simulates the ExpenseList dependency.
     * This version supports adding, updating, and deleting expenses.
     */
    private static class DummyExpenseList extends ExpenseList {
        private final List<DummyExpense> expenses = new ArrayList<>();

        @Override
        public int size() {
            return expenses.size();
        }

        @Override
        public void addExpense(Expense expense) {
            expenses.add((DummyExpense) expense);
        }

        @Override
        public Expense getExpense(int index) {
            return expenses.get(index);
        }

        @Override
        public void updateExpense(int index, Expense expense) {
            expenses.set(index - 1, (DummyExpense) expense);
        }

        @Override
        public void deleteExpense(Expense expense) {
            expenses.remove(expense);
        }

        @Override
        public void setMonthlyBudget(int budget) {
        }

        @Override
        public int getMonthlyBudget() {
            return 0;
        }

        @Override
        public int getRemainingBudget() {
            return 0;
        }

        @Override
        public void addRecurringExpense(RecurringExpense recurringExpense) {
        }

    }

    /**
     * DummyExpenseListFail simulates a failure in viewHistory by throwing an exception
     * when attempting to get an expense.
     */
    private static class DummyExpenseListFail extends DummyExpenseList {
        @Override
        public Expense getExpense(int index) {
            throw new RuntimeException("Simulated failure in viewHistory");
        }
    }

    /**
     * DummyExpense is a simple implementation of an Expense.
     */
    private static class DummyExpense extends Expense {
        private final int amount;
        private final String category;
        private final String description;
        private final Date date;

        public DummyExpense(int amount, String category, String description, Date date) {
            super(amount, category, description, date);
            this.amount = amount;
            this.category = category;
            this.description = description;
            this.date = date;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public int getAmount() {
            return amount;
        }

        @Override
        public Date getDate() {
            return date;
        }

        @Override
        public String getCategory() {
            return category;
        }

    }

    /**
     * DummyParser simulates the Parser dependency.
     */
    private static class DummyParser extends Parser {
        private DummyExpense dummyExpense;
        private boolean throwExceptionOnReadExpense = false;
        private boolean isValidCategory;
        private java.util.Queue<Integer> intInputs = new java.util.LinkedList<>();

        public DummyParser() {
            super(new Scanner("")); // Scanner input is not used.
        }

        public void setDummyExpense(DummyExpense expense) {
            this.dummyExpense = expense;
        }

        public void setThrowExceptionOnReadExpense(boolean flag) {
            this.throwExceptionOnReadExpense = flag;
        }

        public void setIntInputs(int[] inputs) {
            intInputs.clear();
            for (int i : inputs) {
                intInputs.add(i);
            }
        }

        @Override
        public Expense readExpenseDetails() throws FinTrackException {
            if (throwExceptionOnReadExpense) {
                throw new FinTrackException("Dummy expense read failure");
            }
            return dummyExpense;
        }

        @Override
        public int readInt(String prompt) {
            return intInputs.isEmpty() ? 0 : intInputs.poll();
        }

        @Override
        public Date readDate(String prompt) throws FinTrackException {
            return new Date();
        }

        @Override
        public String promptInput(String prompt) {
            return isValidCategory ? "Food" : "" ;
        }

    }


}
