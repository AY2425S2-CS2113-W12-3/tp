package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class UpdateRecurringExpenseCommand implements Command {
    private final Parser parser;

    public UpdateRecurringExpenseCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try {
            int index = parser.readInt("Enter the index of the expense to update:");
            if (index <= 0 || index > expenseList.size()) {
                ui.showError("Invalid index. Please type 'update recurring' and then follow the instructions: " +
                        "enter a valid index from the list of recurring expenses");
                ui.printBorder();
                return;
            }
            Expense updatedExpense = parser.readRecurringExpenseDetails();
            if (expenseList.getMonthlyBudget() > 0) {
                ui.showMessage("Your remaining budget for the month is: " + expenseList.getRemainingBudget());
            }
            expenseList.updateExpense(index, updatedExpense);
            ui.showMessage("Recurring Expense updated.");
            storage.savRecurringExpensesToFile(expenseList);
            ui.printBorder();
        } catch (FinTrackException e) {
            ui.showError("Error updating recurring expense: " + e.getMessage() +
                    "\nPlease type 'update' and follow this format: \"<dollars>, <cents>, <category index>, " +
                    "<description>, <frequency");
            ui.printBorder();
        }
    }
}
