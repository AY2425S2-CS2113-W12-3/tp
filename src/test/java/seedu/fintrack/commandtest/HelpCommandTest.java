package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.command.HelpCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private HelpCommand helpCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();
        helpCommand = new HelpCommand();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_displaysHelpInformation() throws FinTrackException {
        // Execute the command
        helpCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the output contains help information
        String output = outputStream.toString();
        
        // Check for key sections in the help output
        assertTrue(output.contains("Detailed Usage Instructions:"));
        assertTrue(output.contains("Add Expense"));
        assertTrue(output.contains("View Month's Expenses"));
        assertTrue(output.contains("View Spending History"));
        assertTrue(output.contains("Update Expense"));
        assertTrue(output.contains("Delete Expense"));
        assertTrue(output.contains("Set Monthly Budget"));
        assertTrue(output.contains("Add Recurring Expense"));
        assertTrue(output.contains("Category Management"));
        assertTrue(output.contains("Update Financial Goals"));
        assertTrue(output.contains("Clear History"));
        assertTrue(output.contains("Export Expenses"));
        assertTrue(output.contains("Exit Program"));
    }
}
