package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.command.ExportCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExportCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private ExportCommand exportCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();
        exportCommand = new ExportCommand();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        // Add test expenses to the list
        expenseList.addExpense(new Expense(1000, "Food", "Lunch", new Date()));
        expenseList.addExpense(new Expense(2000, "Transport", "Taxi", new Date()));
    }

    @Test
    public void execute_createsCsvFile() throws FinTrackException {
        // Execute the command
        exportCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the output contains success message
        String output = outputStream.toString();
        assertTrue(output.contains("Expenses exported to"));
        
        // Verify exports directory exists
        File exportsDir = new File("exports");
        assertTrue(exportsDir.exists());
        assertTrue(exportsDir.isDirectory());
        
        // Verify at least one CSV file exists in exports directory
        File[] files = exportsDir.listFiles((dir, name) -> name.endsWith(".csv"));
        assertTrue(files != null && files.length > 0);
    }
}

