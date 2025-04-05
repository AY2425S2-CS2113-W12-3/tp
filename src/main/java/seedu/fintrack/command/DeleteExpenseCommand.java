package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class DeleteExpenseCommand implements Command {
    private final Parser parser;

    public DeleteExpenseCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try {
            ViewHistoryCommand viewHistory = new ViewHistoryCommand();
            viewHistory.execute(expenseList, ui, storage, categories);
            int index = parser.readInt("Enter the index of the expense to delete:");
            if (index <= 0 || index > expenseList.size()) {
                ui.showError("Invalid index. Please type 'delete' and then choose a valid index " +
                        "from your spending history.");
                ui.printBorder();
                return;
            }
            Expense expense = expenseList.getExpense(index - 1);
            int sizeBefore = expenseList.size();
            expenseList.getExpenseList().remove(expense);
            if (expenseList.size() != sizeBefore - 1) {
                throw new FinTrackException("Expense list did not decrement as expected");
            }
            ui.showMessage("Expense deleted.");
            storage.saveExpensesToFile(expenseList);
            ui.printBorder();
        } catch (FinTrackException e) {
            ui.showError("Error deleting expense: " + e.getMessage() + "\nPlease type 'delete'" +
                    "and choose a valid index from your spending history.");
            ui.printBorder();
        }
    }
}
