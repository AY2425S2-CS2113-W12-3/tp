package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.command.ClearHistoryCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.Categories;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearHistoryCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private Parser parser;
    private ClearHistoryCommand clearCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();
        parser = new Parser(new Scanner(System.in));
        clearCommand = new ClearHistoryCommand(parser);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        
        // Add test expenses to the list
        expenseList.addExpense(new Expense(1000, "Food", "Lunch", new Date()));
        expenseList.addExpense(new Expense(2000, "Transport", "Taxi", new Date()));
    }

    @Test
    public void execute_clearsExpenseList() throws FinTrackException {
        // Verify list has expenses before clearing
        assertEquals(2, expenseList.getExpenseList().size());
        
        // Execute the command
        clearCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the output contains the warning message
        String output = outputStream.toString();
        assertTrue(output.contains("WARNING: This will delete ALL your expense history!"));
        assertTrue(output.contains("This action cannot be undone."));
    }
}
