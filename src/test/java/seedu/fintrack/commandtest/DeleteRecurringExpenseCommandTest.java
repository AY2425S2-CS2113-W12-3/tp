package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Categories;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.command.DeleteRecurringExpenseCommand;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteRecurringExpenseCommandTest {

    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private Parser parser;
    private DeleteRecurringExpenseCommand deleteCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();

        parser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes())));
        deleteCommand = new DeleteRecurringExpenseCommand(parser);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute_validIndex() throws FinTrackException {
        expenseList.addRecurringExpense(new RecurringExpense(1000, "Food",
                "yearly", "apples", new Date(), new Date()));

        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public int readInt(String prompt) {
                return 1; // Valid index
            }
        };

        DeleteRecurringExpenseCommand command = new DeleteRecurringExpenseCommand(mockParser);
        command.execute(expenseList, ui, storage, categories);

        assertEquals(0, expenseList.getRecurringExpenses().size());
    }


    @Test
    public void execute_invalidIndex_handlesException() throws FinTrackException {
        expenseList.addRecurringExpense(new RecurringExpense(1000, "Food",
                "yearly", "apples", new Date(), new Date()));

        Parser mockParser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes()))) {
            @Override
            public int readInt(String prompt) {
                return 999;
            }
        };

        DeleteRecurringExpenseCommand command = new DeleteRecurringExpenseCommand(mockParser);

        FinTrackException exception = assertThrows(FinTrackException.class,
                () -> command.execute(expenseList, ui, storage, categories));

        assertEquals("Invalid index. Please enter a number between 1 and 1",
                exception.getMessage());

    }
}

