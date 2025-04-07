package seedu.fintrack.commandtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.Categories;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.command.ViewRecurringExpensesCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewRecurringHistoryCommandTest {

    private ExpenseList expenseList;
    private Ui ui;
    private Storage storage;
    private Categories categories;
    private Parser parser;
    private ViewRecurringExpensesCommand viewCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage();
        categories = new Categories();

        parser = new Parser(new Scanner(new ByteArrayInputStream("".getBytes())));
        viewCommand = new ViewRecurringExpensesCommand();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void execute() throws FinTrackException {
        Categories.addCategory("Food");
        Categories.addCategory("Transport");
        expenseList.addRecurringExpense(new RecurringExpense(1000, "Food","monthly",
                "Lunch", new Date(),new Date()));
        expenseList.addRecurringExpense(new RecurringExpense(2000, "Transport",
                "monthly","Taxi", new Date(), new Date()));

        viewCommand.execute(expenseList, ui, storage, categories);

        String output = outputStream.toString();
        assertTrue(output.contains("These are your current recurring expenses:"));
        assertTrue(output.contains("Lunch"));
        assertTrue(output.contains("Taxi"));
        assertTrue(output.contains("$10.00"));
        assertTrue(output.contains("$20.00"));
        assertTrue(output.contains("monthly"));
    }

    @Test
    public void execute_emptyRecurringExpenseList() throws FinTrackException {
        ExpenseList emptyList = new ExpenseList();
        viewCommand.execute(emptyList, ui, storage, categories);

        String output = outputStream.toString();
        assertTrue(output.contains("There are currently no recurring expenses"));
    }
}
