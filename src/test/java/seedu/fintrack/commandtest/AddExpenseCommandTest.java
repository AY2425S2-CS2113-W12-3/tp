package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.command.AddExpenseCommand;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AddExpenseCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private Parser parser;
    private AddExpenseCommand addCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();
        
        // Add a category for testing
        Categories.addCategory("Food");
        
        // Initialize parser with a Scanner that has empty input
        parser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes())));
        addCommand = new AddExpenseCommand(parser);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_validExpense_success() throws FinTrackException {
        // Create a valid expense for testing
        final Expense validExpense = new Expense(1000, "Food", "Lunch", new Date());
        
        // Create a mock parser that returns the valid expense
        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public Expense readExpenseDetails() throws FinTrackException {
                return validExpense;
            }
        };
        
        // Create a new command with the mock parser
        AddExpenseCommand mockCommand = new AddExpenseCommand(mockParser);
        
        // Execute the command
        mockCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the expense was added to the list
        assertEquals(1, expenseList.size());
        Expense addedExpense = expenseList.getExpense(0);
        assertEquals(validExpense.getAmount(), addedExpense.getAmount());
        assertEquals(validExpense.getCategory(), addedExpense.getCategory());
        assertEquals(validExpense.getDescription(), addedExpense.getDescription());
    }

    @Test
    public void execute_nullExpense_handlesException() throws FinTrackException {
        // Create a mock parser that returns null
        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public Expense readExpenseDetails() throws FinTrackException {
                return null;
            }
        };
        
        // Create a new command with the mock parser
        AddExpenseCommand mockCommand = new AddExpenseCommand(mockParser);
        
        // Execute the command
        mockCommand.execute(expenseList, ui, storage, categories);
        
        // Verify no expense was added to the list
        assertEquals(0, expenseList.size());
        
        // Verify error message was displayed
        String output = outputStream.toString();
        assertTrue(output.contains("Failed to create expense object"));
    }
}
