package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class ViewHistoryCommand implements Command {
    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try {
            ui.showMessage("Spending history:");
            for (int i = 0; i < expenseList.size(); i++) {
                Expense expense = expenseList.getExpense(i);
                ui.showMessage((i + 1) + ": " + expense.getDescription() +
                        " - " + expense.getAmount() + " cents on " + expense.getDate() + " (" +
                        expense.getCategory() + ")");
            }
            ui.printBorder();
        } catch (Exception e) {
            ui.showError("An error occurred while displaying history: " + e.getMessage());
            ui.printBorder();
        }
    }
}
