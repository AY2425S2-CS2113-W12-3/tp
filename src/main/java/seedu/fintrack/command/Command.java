package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

/**
 * Represents an executable command.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param expenseList The list of expenses.
     * @param ui The user interface handler.
     * @param storage The storage handler.
     * @param categories The categories' handler.
     * @throws FinTrackException If an error occurs during command execution.
     */
    void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories) throws FinTrackException;
}
