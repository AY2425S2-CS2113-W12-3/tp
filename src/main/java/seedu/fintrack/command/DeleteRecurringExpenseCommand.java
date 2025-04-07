package seedu.fintrack.command;

import seedu.fintrack.Categories;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

public class DeleteRecurringExpenseCommand implements Command {
    private final Parser parser;

    public DeleteRecurringExpenseCommand(Parser parser) {
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
        int index = parser.readInt("Enter the index of the recurring expense to delete: ");

        int sizeBefore = expenseList.getRecurringExpenses().size();
        if (index <= 0 || index > sizeBefore) {
            throw new FinTrackException("Invalid index. Please enter a number between 1 and " + sizeBefore);
        }

        RecurringExpense expense = expenseList.getRecurringExpense(index - 1);
        // Format and display success message
        String description = expense.getDescription() != null ? expense.getDescription() : "No description";
        String category = expense.getCategory() != null ? expense.getCategory() : "Uncategorized";
        String frequency = expense.getDate() != null ? expense.getFrequency() : "No date";

        ui.showMessage("Recurring Expense deleted: " + category + ": " + description + ", " + frequency);

        expenseList.deleteRecurringExpense(index-1);

        if (expenseList.getRecurringExpenses().size() != sizeBefore - 1) {
            throw new FinTrackException("Recurring expense is not removed as expected");
        }
        assert expenseList.getRecurringExpenses().size() == sizeBefore - 1:
                "1 recurring expense removed from recurring expense list";
        storage.savRecurringExpensesToFile(expenseList);
        ui.printBorder();
    }
}
