package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.text.SimpleDateFormat;

public class UpdateExpenseCommand implements Command {
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Parser parser;

    public UpdateExpenseCommand(Parser parser) {
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
                throw new FinTrackException("No expenses to update");
            }

            // Get index to update
            int index = parser.readInt("Enter the index of the expense to update: ");

            // Validate index
            if (index <= 0 || index > expenseList.size()) {
                throw new FinTrackException("Invalid index. Please enter a number between 1 and " + expenseList.size());
            }

            // Get expense to update
            Expense oldExpense = expenseList.getExpense(index - 1);

            // Validate old expense
            if (oldExpense == null) {
                throw new FinTrackException("Expense at index " + index + " is null");
            }

            // Read new expense details
            Expense newExpense = parser.readExpenseDetails();

            // Validate new expense
            if (newExpense == null) {
                throw new FinTrackException("Failed to create new expense object");
            }

            // Validate new expense amount
            if (newExpense.getAmount() <= 0) {
                throw new FinTrackException("Expense amount must be greater than zero");
            }

            // Validate new expense category
            if (newExpense.getCategory() == null || newExpense.getCategory().trim().isEmpty()) {
                throw new FinTrackException("Expense category cannot be empty");
            }

            // Validate new expense date
            if (newExpense.getDate() == null) {
                throw new FinTrackException("Expense date cannot be null");
            }

            // Update expense
            expenseList.updateExpense(index - 1, newExpense);

            // Format and display success message
            String oldDescription = oldExpense.getDescription() != null ? oldExpense.getDescription()
                    : "No description";
            String oldCategory = oldExpense.getCategory() != null ? oldExpense.getCategory()
                    : "Uncategorized";
            String oldDateStr = oldExpense.getDate() != null ? DATE_TIME_FORMAT.format(oldExpense.getDate())
                    : "No date";

            String newDescription = newExpense.getDescription() != null ? newExpense.getDescription()
                    : "No description";
            String newCategory = newExpense.getCategory() != null ? newExpense.getCategory()
                    : "Uncategorized";
            String newDateStr = newExpense.getDate() != null ? DATE_TIME_FORMAT.format(newExpense.getDate())
                    : "No date";

            ui.showMessage("Expense updated from " + oldCategory + ": " + oldDescription + " on " + oldDateStr +
                    " to " + newCategory + ": " + newDescription + " on " + newDateStr);

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
