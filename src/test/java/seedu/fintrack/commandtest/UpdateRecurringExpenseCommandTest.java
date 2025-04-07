package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Categories;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.command.UpdateRecurringExpenseCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateRecurringExpenseCommandTest {
    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private Parser parser;
    private UpdateRecurringExpenseCommand updateRecurringExpenseCommand;
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

        Categories.addCategory("Food");
        Categories.addCategory("Food");
        Categories.addCategory("Transport");

        expenseList.addRecurringExpense(new RecurringExpense(1000, "Food",  "monthly",
                "Lunch", new Date(), new Date()));
        expenseList.addRecurringExpense(new RecurringExpense(2000, "Transport",
                "monthly","Taxi", new Date(), new Date()));

        // Print initial state
        System.out.println("Initial expense list size: " + expenseList.getRecurringExpenses().size());
        System.out.println("Initial expense at index 0: " + expenseList.getRecurringExpenses().get(0).getAmount());
    }

    @Test
    public void execute_validUpdate_success() throws FinTrackException {
        // Setup initial expense list with one recurring expense
        RecurringExpense initialExpense = new RecurringExpense(1000, "Food", "monthly",
                "Lunch", new Date(), new Date());
        expenseList.addRecurringExpense(initialExpense);

        String input = "1\n30,0,1,Movie,monthly\n";  // Correct format
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        parser = new Parser(new Scanner(System.in));
        updateRecurringExpenseCommand = new UpdateRecurringExpenseCommand(parser);

        updateRecurringExpenseCommand.execute(expenseList, ui, storage, categories);

        RecurringExpense updatedExpense = expenseList.getRecurringExpenses().get(0);
        assertEquals(3000, updatedExpense.getAmount());
        assertEquals("Food", updatedExpense.getCategory());
        assertEquals("Movie", updatedExpense.getDescription());
        assertEquals("monthly", updatedExpense.getFrequency());
    }

    @Test
    public void execute_invalidIndex() throws FinTrackException {
        String input = "999\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        parser = new Parser(new Scanner(System.in));
        updateRecurringExpenseCommand = new UpdateRecurringExpenseCommand(parser);

        updateRecurringExpenseCommand.execute(expenseList, ui, storage, categories);

        String output = outputStream.toString();
        assertTrue(output.contains("Invalid index"));
    }
}
