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
    public void execute_cancelledExpense_handlesCancellation() throws FinTrackException {
        // Create a mock parser that returns null (simulating cancellation)
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
        
        // Verify cancellation message was displayed
        String output = outputStream.toString();
        assertTrue(output.contains("Add expense operation cancelled"));
    }

    @Test
    public void execute_invalidExpense_handlesException() throws FinTrackException {
        // Create a mock parser that throws an exception
        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public Expense readExpenseDetails() throws FinTrackException {
                throw new FinTrackException("Invalid expense details");
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
        assertTrue(output.contains("Invalid expense details"));
    }
}
