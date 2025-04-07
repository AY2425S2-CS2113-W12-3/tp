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
        
        // Check for all command sections
        assertTrue(output.contains("1. Add Expense"));
        assertTrue(output.contains("2. View Month's Expenses"));
        assertTrue(output.contains("3. View Spending History"));
        assertTrue(output.contains("4. Update Expense"));
        assertTrue(output.contains("5. Delete Expense"));
        assertTrue(output.contains("6. Set Monthly Budget"));
        assertTrue(output.contains("7. Add Recurring Expense"));
        assertTrue(output.contains("8. Update Recurring Expense"));
        assertTrue(output.contains("9. View Recurring Expenses"));
        assertTrue(output.contains("10. Delete Recurring Expense"));
        assertTrue(output.contains("11. Create Category"));
        assertTrue(output.contains("12. Delete Category"));
        assertTrue(output.contains("13. Update Income"));
        assertTrue(output.contains("14. Update Savings Goal"));
        assertTrue(output.contains("15. Clear History"));
        assertTrue(output.contains("16. Export Expenses"));
        assertTrue(output.contains("17. Exit Program"));
        
        // Check for common elements in the help output
        assertTrue(output.contains("Step 1:"));
        assertTrue(output.contains("Step 2:"));
        assertTrue(output.contains("Example:"));
    }
}
