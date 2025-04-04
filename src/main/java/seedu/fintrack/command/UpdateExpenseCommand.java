package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class UpdateExpenseCommand implements Command {
    private final Parser parser;

    public UpdateExpenseCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try {
            ViewHistoryCommand viewHistory = new ViewHistoryCommand();
            viewHistory.execute(expenseList, ui, storage, categories);
            int index = parser.readInt("Enter the index of the expense to update:");
            if (index <= 0 || index > expenseList.size()) {
                ui.showError("Invalid index. Please type 'update' and then follow the instructions: " +
                        "enter a valid index from your spending history.");
                ui.printBorder();
                return;
            }
            Expense updatedExpense = parser.readExpenseDetails();
            if (expenseList.getMonthlyBudget() > 0) {
                ui.showMessage("Your remaining budget for the month is: " + expenseList.getRemainingBudget());
            }
            expenseList.updateExpense(index, updatedExpense);
            ui.showMessage("Expense updated.");
            storage.saveExpensesToFile(expenseList);
            ui.printBorder();
        } catch (FinTrackException e) {
            ui.showError("Error updating expense: " + e.getMessage() +
                    "\nPlease type 'update' and follow this format: \"<dollars>, <cents>, <category index>, " +
                    "<description>, <date (yyyy-MM-dd)>\".");
            ui.printBorder();
        }
    }
}
