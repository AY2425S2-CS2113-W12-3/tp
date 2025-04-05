package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.command.UpdateExpenseCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.Categories;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateExpenseCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private Parser parser;
    private UpdateExpenseCommand updateCommand;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        // Initialize categories
        Categories.addCategory("Food");
        Categories.addCategory("Food");
        Categories.addCategory("Transport");
        
        // Add test expenses to the list
        expenseList.addExpense(new Expense(1000, "Food", "Lunch", new Date()));
        expenseList.addExpense(new Expense(2000, "Transport", "Taxi", new Date()));
        
        // Print initial state
        System.out.println("Initial expense list size: " + expenseList.size());
        System.out.println("Initial expense at index 0: " + expenseList.getExpenseList().get(0).getAmount());
    }

    @Test
    public void execute_validUpdate_success() throws FinTrackException {
        // Set up input for the parser
        // First input is for index prompt, second input is expense details
        String input = "1\n30,00,1,Movie,2023-12-31\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        parser = new Parser(new Scanner(System.in));
        updateCommand = new UpdateExpenseCommand(parser);
        
        // Execute the command
        updateCommand.execute(expenseList, ui, storage, categories);
        
        // Print state after update
        System.out.println("Expense list size after update: " + expenseList.size());
        System.out.println("Expense at index 0 after update: " + expenseList.getExpenseList().get(0).getAmount());
        System.out.println("Expense at index 1 after update: " + expenseList.getExpenseList().get(1).getAmount());
        
        // Verify the expense was updated at index 0 (since index 1 was provided)
        Expense updatedExpense = expenseList.getExpenseList().get(0);
        assertEquals(3000, updatedExpense.getAmount());
        assertEquals("Food", updatedExpense.getCategory());
        assertEquals("Movie", updatedExpense.getDescription());
        
        // Verify the output contains the update message
        String output = outputStream.toString();
        System.out.println("Test output: " + output);
        assertTrue(output.contains("Expense updated"));
    }

    @Test
    public void execute_invalidIndex_handlesException() throws FinTrackException {
        // Set up input for the parser with an invalid index
        String input = "999\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        parser = new Parser(new Scanner(System.in));
        updateCommand = new UpdateExpenseCommand(parser);
        
        // Execute the command
        updateCommand.execute(expenseList, ui, storage, categories);
        
        // Verify the output contains the error message
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid index"));
    }
}
