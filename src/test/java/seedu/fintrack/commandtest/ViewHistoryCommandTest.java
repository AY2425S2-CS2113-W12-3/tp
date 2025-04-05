package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.command.ViewHistoryCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;



public class ViewHistoryCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private Parser parser;
    private ViewHistoryCommand viewCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();
        
        // Initialize parser with a Scanner that has empty input
        parser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes())));
        viewCommand = new ViewHistoryCommand();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_withExpenses_success() throws FinTrackException {
        // Add test expenses to the list
        Categories.addCategory("Food");
        Categories.addCategory("Transport");
        expenseList.addExpense(new Expense(1000, "Food", "Lunch", new Date()));
        expenseList.addExpense(new Expense(2000, "Transport", "Taxi", new Date()));
        
        // Execute the command
        viewCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the output contains expense details
        String output = outputStream.toString();
        assertTrue(output.contains("Spending history:"));
        assertTrue(output.contains("Lunch"));
        assertTrue(output.contains("Taxi"));
        assertTrue(output.contains("$10.00"));
        assertTrue(output.contains("$20.00"));
    }

    @Test
    public void execute_emptyExpenseList_success() throws FinTrackException {
        // Execute the command with empty expense list
        viewCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the output shows no expenses message
        String output = outputStream.toString();
        assertTrue(output.contains("No expenses found"));
    }
}
