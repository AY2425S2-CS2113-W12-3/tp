package seedu.fintrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinTrackTest {
    private Commands commands;
    private ExpenseList expenseList;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        expenseList = new ExpenseList();
        // Create a default scanner with an empty input; individual tests will override this if needed.
        Scanner scanner = new Scanner(new ByteArrayInputStream("".getBytes()));
        Parser parser = new Parser(scanner);
        commands = new Commands(expenseList, parser);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Redirect output for testing
    }

    /*
    @Test
    void fetchCommand_validCommand_addsExpense() throws FinTrackException {
        // Simulated input for addExpense in one shot.
        // Expected format: "dollars, cents, category index, description, date (yyyy-MM-dd)"
        String simulatedInput = "10, 50, 1, Lunch, 2025-03-18\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        Parser parser = new Parser(scanner);
        commands = new Commands(expenseList, parser);

        // Using the new descriptive command key "add"
        commands.fetchCommand("add");

        // Check that the expense was added and proper output was shown.
        assertTrue(outputStream.toString().contains("Expense added."));
    }
     */


    /*** ExpenseList Tests ***/

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

    /*** Ui Tests ***/

    @Test
    void printGreeting_displaysGreetingMessage() {
        Ui.printGreeting();
        assertTrue(outputStream.toString().contains("Hello, I am Fin!"));
    }
}
