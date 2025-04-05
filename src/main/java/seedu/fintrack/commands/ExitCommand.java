package seedu.fintrack.commands;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

public class ExitCommand implements Command {
    private final ExpenseList expenseList;

    public ExitCommand(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public void execute() throws FinTrackException {
        Storage.saveExpensesToFile(expenseList);
        Ui.showMessage("Make sure to have good saving habits yeah, cyaaaa.");
        System.exit(0);
    }
}
