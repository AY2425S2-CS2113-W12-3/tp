package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.command.AddExpenseCommand;
import seedu.fintrack.command.AddRecurringExpenseCommand;
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

import static org.junit.jupiter.api.Assertions.*;


public class AddRecurringExpenseCommandTest {
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

        Categories.addCategory("Food");

        // Initialize parser with a Scanner that has empty input
        parser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes())));
        addCommand = new AddExpenseCommand(parser);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_invalidFrequency() {

        String invalidInput = "10,0,1,Lunch,quarterly\n";
        Parser parser = new Parser(new Scanner(new ByteArrayInputStream(invalidInput.getBytes())));
        AddRecurringExpenseCommand command = new AddRecurringExpenseCommand(parser);

        FinTrackException exception = assertThrows(FinTrackException.class,
                () -> command.execute(expenseList, ui, storage, categories),
                "Should throw FinTrackException for invalid frequency");

        assertEquals("Error parsing recurring expense details: Invalid frequency",
                exception.getMessage());
        assertEquals(0, expenseList.getRecurringExpenses().size(),
                "No expense should be added to the list");
    }

    @Test
    public void execute_validRecurringExpense() throws FinTrackException {
        final RecurringExpense validExpense = new RecurringExpense(1000, "Food",
                "monthly","Lunch", new Date(), new Date());

        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public RecurringExpense readRecurringExpenseDetails() throws FinTrackException {
                return validExpense;
            }
        };

        AddRecurringExpenseCommand mockCommand = new AddRecurringExpenseCommand(mockParser);
        mockCommand.execute(expenseList, ui, storage, categories);

        assertEquals(1, expenseList.getRecurringExpenses().size());
        RecurringExpense addedExpense = expenseList.getRecurringExpense(0);
        assertEquals(validExpense.getAmount(), addedExpense.getAmount());
        assertEquals(validExpense.getCategory(), addedExpense.getCategory());
        assertEquals(validExpense.getDescription(), addedExpense.getDescription());
        assertEquals(validExpense.getFrequency(), addedExpense.getFrequency());
    }


    @Test
    public void execute_invalidRecurringExpense_handlesException() throws FinTrackException {
        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public RecurringExpense readRecurringExpenseDetails() throws FinTrackException {
                throw new FinTrackException("Invalid recurring expense details");
            }
        };

        AddExpenseCommand mockCommand = new AddExpenseCommand(mockParser);
        mockCommand.execute(expenseList, ui, storage, categories);

        assertEquals(0, expenseList.getRecurringExpenses().size());

    }
}
