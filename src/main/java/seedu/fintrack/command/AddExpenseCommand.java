package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;
import java.text.SimpleDateFormat;

public class AddExpenseCommand implements Command {
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Parser parser;

    public AddExpenseCommand(Parser parser) {
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
            
            // Read expense details with validation
            Expense expense = parser.readExpenseDetails();
            
            // Validate expense object
            if (expense == null) {
                throw new FinTrackException("Failed to create expense object");
            }
            
            // Validate expense amount
            if (expense.getAmount() <= 0) {
                throw new FinTrackException("Expense amount must be greater than zero");
            }
            
            // Validate expense category
            if (expense.getCategory() == null || expense.getCategory().trim().isEmpty()) {
                throw new FinTrackException("Expense category cannot be empty");
            }
            
            // Validate expense date
            if (expense.getDate() == null) {
                throw new FinTrackException("Expense date cannot be null");
            }
            
            // Record size before adding
            int sizeBefore = expenseList.size();
            
            // Add expense to list
            expenseList.addExpense(expense);
            
            // Verify expense was added correctly
            if (expenseList.size() != sizeBefore + 1) {
                throw new FinTrackException("Expense list did not increment as expected");
            }
            
            // Show budget information if applicable
            if (expenseList.getMonthlyBudget() > 0) {
                ui.showMessage("Your remaining budget for the month is: " + expenseList.getRemainingBudget());
            }
            
            // Format and display success message
            String description = expense.getDescription() != null ? expense.getDescription() : "No description";
            String category = expense.getCategory() != null ? expense.getCategory() : "Uncategorized";
            String dateStr = expense.getDate() != null ? DATE_TIME_FORMAT.format(expense.getDate()) : "No date";
            
            ui.showMessage("Expense added for " + category + ": " + description + " on " + dateStr);
            
            // Save to storage
            storage.saveExpensesToFile(expenseList);
            ui.printBorder();
        } catch (FinTrackException e) {
            ui.showError(e.getMessage());
            ui.printBorder();
        } catch (Exception e) {
            ui.showError("An unexpected error occurred: " + e.getMessage());
            ui.printBorder();
        }
    }
}
