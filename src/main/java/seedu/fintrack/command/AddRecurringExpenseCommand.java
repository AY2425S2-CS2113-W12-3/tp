package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.text.SimpleDateFormat;

public class AddRecurringExpenseCommand implements Command {
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Parser parser;

    public AddRecurringExpenseCommand(Parser parser) {
        assert parser != null : "Parser cannot be null";
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        assert expenseList != null : "ExpenseList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        assert categories != null : "Categories cannot be null";

        RecurringExpense expense = parser.readRecurringExpenseDetails();
        if (expense == null) {
            throw new FinTrackException("Failed to create expense object");
        }

        if (expense.getAmount() <= 0) {
            throw new FinTrackException("Expense amount must be greater than zero");
        }

        if (expense.getCategory() == null || expense.getCategory().trim().isEmpty()) {
            throw new FinTrackException("Expense category cannot be empty");
        }

        expenseList.addRecurringExpense(expense);
        String description = expense.getDescription() != null ? expense.getDescription() : "No description";
        String category = expense.getCategory() != null ? expense.getCategory() : "Uncategorized";
        String dateStr = expense.getDate() != null ? DATE_TIME_FORMAT.format(expense.getDate()) : "No date";

        storage.savRecurringExpensesToFile(expenseList);
        ui.printBorder();
        ui.showMessage("Recurring expense added for " + category + ": " + description + " on " + dateStr);

    }
}
