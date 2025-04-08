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

public class UpdateExpenseCommand implements Command {
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int MAX_EXPENSE_AMOUNT = 100000000; // 1 million dollars in cents
    private static final int MAX_CENTS = 99; // Maximum cents value (2 digits)
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

            // Inform user about limitations
            ui.showMessage("Note: The maximum amount that can be entered is $1,000,000.00 (one million dollars).");
            ui.showMessage("Note: Cents must be between 0 and 99 (2 digits only).");

            // Show current expenses and get index to update
            ViewHistoryCommand viewHistory = new ViewHistoryCommand();
            viewHistory.execute(expenseList, ui, storage, categories);
            ui.showMessage("Enter 0 to cancel the update operation.");
            int index = parser.readIntWithCancel("Enter the index of the expense to update:");

            // Check for cancellation
            if (index == -1) {
                ui.showMessage("Update operation cancelled.");
                ui.printBorder();
                return;
            }

            if (index <= 0 || index > expenseList.size()) {
                throw new FinTrackException("Invalid index. Please enter a valid index from your spending history.");
            }

            // Read expense details with validation
            Expense updatedExpense = parser.readExpenseDetails();

            // Validate expense object
            if (updatedExpense == null) {
                throw new FinTrackException("Failed to create expense object");
            }

            // Validate expense amount
            if (updatedExpense.getAmount() <= 0) {
                throw new FinTrackException("Expense amount must be greater than zero");
            }

            // Validate expense amount does not exceed 1 million dollars
            if (updatedExpense.getAmount() > MAX_EXPENSE_AMOUNT) {
                throw new FinTrackException("Expense amount cannot exceed $1,000,000.00 (one million dollars)");
            }

            // Validate that cents are limited to 2 digits
            int cents = updatedExpense.getAmount() % 100;
            if (cents > MAX_CENTS) {
                throw new FinTrackException("Cents must be between 0 and 99 (2 digits only)");
            }

            // Validate expense category
            if (updatedExpense.getCategory() == null || updatedExpense.getCategory().trim().isEmpty()) {
                throw new FinTrackException("Expense category cannot be empty");
            }

            // Validate expense date
            if (updatedExpense.getDate() == null) {
                throw new FinTrackException("Expense date cannot be null");
            }

            //check if new expense exceeds current monthly budget
            Expense oldExpense = expenseList.getExpense(index -1);
            if(Savings.getCurrentMonthlyBudget() - oldExpense.getAmount()  < updatedExpense.getAmount() ) {
                float excess = (float)(updatedExpense.getAmount() -
                        Savings.getCurrentMonthlyBudget() - oldExpense.getAmount()) / 100;
                throw new FinTrackException("New expense cannot exceeds current budget by $" + excess);
            }

            Savings.addToSavings(oldExpense.getAmount());
            Savings.updateTotalSavings(updatedExpense.getAmount());
            Storage.saveSavingsToFile(Savings.getIncome(), Savings.getMonthlyBudget(), Savings.getCurrentSavings());

            // Update expense in list
            expenseList.updateExpense(index, updatedExpense);


            // Format and display success message
            String description = updatedExpense.getDescription() != null ? updatedExpense.getDescription() :
                    "No description";
            String category = updatedExpense.getCategory() != null ? updatedExpense.getCategory() :
                    "Uncategorized";
            String dateStr = updatedExpense.getDate() != null ? DATE_TIME_FORMAT.format(updatedExpense.getDate()) :
                    "No date";

            ui.showMessage("Expense updated for " + category + ": " + description + " on " + dateStr);

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
