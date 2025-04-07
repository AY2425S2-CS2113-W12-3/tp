package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.command.DeleteExpenseCommand;
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



public class DeleteExpenseCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private Parser parser;
    private DeleteExpenseCommand deleteCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();
        
        // Add a test expense to the list
        expenseList.addExpense(new Expense(1000, "Food", "Lunch", new Date()));
        
        // Initialize parser with a Scanner that has empty input
        parser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes())));
        deleteCommand = new DeleteExpenseCommand(parser);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_validIndex_success() throws FinTrackException {
        // Create a mock parser that returns index 1
        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public int readIntWithCancel(String prompt) throws FinTrackException {
                return 1;
            }
        };
        
        // Create a new command with the mock parser
        DeleteExpenseCommand mockCommand = new DeleteExpenseCommand(mockParser);
        
        // Execute the command
        mockCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the expense was deleted from the list
        assertEquals(0, expenseList.size());
        
        // Verify success message was displayed
        String output = outputStream.toString();
        assertTrue(output.contains("Expense deleted:"));
    }

    @Test
    public void execute_cancelledIndex_handlesCancellation() throws FinTrackException {
        // Create a mock parser that returns -1 (simulating cancellation)
        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public int readIntWithCancel(String prompt) throws FinTrackException {
                return -1;
            }
        };
        
        // Create a new command with the mock parser
        DeleteExpenseCommand mockCommand = new DeleteExpenseCommand(mockParser);
        
        // Execute the command
        mockCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the expense list remains unchanged
        assertEquals(1, expenseList.size());
        
        // Verify cancellation message was displayed
        String output = outputStream.toString();
        assertTrue(output.contains("Delete operation cancelled"));
    }

    @Test
    public void execute_invalidIndex_handlesException() throws FinTrackException {
        // Create a mock parser that returns an invalid index
        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public int readIntWithCancel(String prompt) throws FinTrackException {
                return 999; // Invalid index
            }
        };
        
        // Create a new command with the mock parser
        DeleteExpenseCommand mockCommand = new DeleteExpenseCommand(mockParser);
        
        // Execute the command
        mockCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the expense list remains unchanged
        assertEquals(1, expenseList.size());
        
        // Verify error message was displayed
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid index. Please enter a number between 1 and"));
    }
}

