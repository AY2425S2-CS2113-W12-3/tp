package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.Savings;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;
import java.text.SimpleDateFormat;

public class DeleteExpenseCommand implements Command {
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Parser parser;

    public DeleteExpenseCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try {
            // Check if expenseList is null
            if (expenseList == null) {
                throw new FinTrackException("Expense list is null");
            }
            
            // Check if parser is null
            if (parser == null) {
                throw new FinTrackException("Parser is null");
            }
            
            // Check if storage is null
            if (storage == null) {
                throw new FinTrackException("Storage is null");
            }
            
            // Check if categories is null
            if (categories == null) {
                throw new FinTrackException("Categories is null");
            }
            
            // Check if expenseList is empty
            if (expenseList.size() == 0) {
                throw new FinTrackException("No expenses to delete");
            }
            
            // Show current expenses and get index to delete
            ViewHistoryCommand viewHistory = new ViewHistoryCommand();
            viewHistory.execute(expenseList, ui, storage, categories);
            ui.showMessage("Enter 0 to cancel the delete operation.");
            int index = parser.readIntWithCancel("Enter the index of the expense to delete:");
            
            // Check for cancellation
            if (index == -1) {
                ui.showMessage("Delete operation cancelled.");
                ui.printBorder();
                return;
            }
            
            // Validate index
            if (index <= 0 || index > expenseList.size()) {
                throw new FinTrackException("Invalid index. Please enter a number between 1 and " + expenseList.size());
            }
            
            // Get expense to delete
            Expense expense = expenseList.getExpense(index - 1);
            
            // Validate expense
            if (expense == null) {
                throw new FinTrackException("Expense at index " + index + " is null");
            }
            
            // Record size before deletion
            int sizeBefore = expenseList.size();
            
            // Delete expense
            expenseList.deleteExpense(expense);
            
            // Verify expense was deleted correctly
            if (expenseList.size() != sizeBefore - 1) {
                throw new FinTrackException("Expense list did not decrement as expected");
            }
            
            // Format and display success message
            String description = expense.getDescription() != null ? expense.getDescription() : "No description";
            String category = expense.getCategory() != null ? expense.getCategory() : "Uncategorized";
            String dateStr = expense.getDate() != null ? DATE_TIME_FORMAT.format(expense.getDate()) : "No date";

            Savings.addToCurrentMonthlyBudget(expense);
            Savings.addToSavings(expense.getAmount());
            Storage.saveSavingsToFile(Savings.getIncome(), Savings.getMonthlyBudget(), Savings.getCurrentSavings());
            
            ui.showMessage("Expense deleted: " + category + ": " + description + " on " + dateStr);
            
            // Save to storage
            storage.saveExpensesToFile(expenseList);
            ui.printBorder();
        } catch (FinTrackException e) {
            ui.showError(e.getMessage());
            ui.printBorder();
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Invalid index. Please enter a number between 1 and " + expenseList.size());
            ui.printBorder();
        } catch (Exception e) {
            ui.showError("An unexpected error occurred: " + e.getMessage());
            ui.printBorder();
        }
    }
}
