package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.command.ExitCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
//import java.security.Permission;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private ExitCommand exitCommand;
    private ByteArrayOutputStream outputStream;
    //private SecurityManager originalSecurityManager;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();
        exitCommand = new ExitCommand();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        // Add a test expense to the list
        expenseList.addExpense(new Expense(1000, "Food", "Lunch", new Date()));
        
        // Save original security manager
        //originalSecurityManager = System.getSecurityManager();
    }

    @Test
    public void execute_displaysExitMessage() throws FinTrackException {
        
        try {
            // Execute the command
            exitCommand.execute(expenseList, ui, storage, categories);
        } catch (SecurityException e) {
            // Expected exception when System.exit(0) is called
        } finally {
            // Restore original security manager
            //System.setSecurityManager(originalSecurityManager);
        }
        
        // Verify the output contains the exit message
        String output = outputStream.toString();
        assertTrue(output.contains("Make sure to have good saving habits yeah, cyaaaa."));
    }
}
