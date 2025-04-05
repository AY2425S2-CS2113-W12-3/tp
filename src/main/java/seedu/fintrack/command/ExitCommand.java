package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class ExitCommand implements Command {
    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        storage.saveExpensesToFile(expenseList);
        ui.showMessage("Make sure to have good saving habits yeah, cyaaaa.");
        System.exit(0);
    }
}
