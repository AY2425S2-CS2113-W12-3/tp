package seedu.fintrack.command;

import seedu.fintrack.Categories;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

public class DeleteRecurringExpenseCommand implements Command {
    private final Parser parser;

    public DeleteRecurringExpenseCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        int index = parser.readInt("Enter the index of the recurring expense to delete: ");

        int sizeBefore = expenseList.getRecurringExpenses().size();
        if (index <= 0 || index > sizeBefore) {
            throw new FinTrackException("Invalid index. Please enter a number between 1 and " + sizeBefore);
        }
        expenseList.deleteRecurringExpense(index-1);
        if (expenseList.getRecurringExpenses().size() != sizeBefore - 1) {
            throw new FinTrackException("Recurring expense is not removed as expected");
        }
        storage.savRecurringExpensesToFile(expenseList);
        ui.printBorder();
    }
}
