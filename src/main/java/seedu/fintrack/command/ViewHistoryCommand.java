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
            // Check if expenseList is null
            if (expenseList == null) {
                throw new FinTrackException("Expense list is null");
            }
            
            // Check if the expense list is empty
            if (expenseList.size() == 0) {
                ui.showMessage("No expenses found in your history.");
                ui.printBorder();
                return;
            }
            
            ui.showMessage("Spending history:");
            for (int i = 0; i < expenseList.size(); i++) {
                Expense expense = expenseList.getExpense(i);
                // Add null check for expense
                if (expense == null) {
                    ui.showError("Warning: Found null expense at index " + (i + 1));
                    continue;
                }
                
                // Add null checks for expense properties
                double amountInDollars = expense.getAmount() / 100.0;
                String formattedAmount = String.format("$%.2f", amountInDollars);
                String description = expense.getDescription() != null ? expense.getDescription() : "No description";
                String date = expense.getDate() != null ? expense.getDate().toString() : "No date";
                String category = expense.getCategory() != null ? expense.getCategory() : "Uncategorized";
                
                ui.showMessage((i + 1) + ": " + description +
                        " - " + formattedAmount + " on " + date + " (" +
                        category + ")");
            }
            ui.printBorder();
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Error accessing expense list: " + e.getMessage());
            ui.printBorder();
        } catch (Exception e) {
            ui.showError("An error occurred while displaying history: " + e.getMessage());
            ui.printBorder();
        }
    }
}
