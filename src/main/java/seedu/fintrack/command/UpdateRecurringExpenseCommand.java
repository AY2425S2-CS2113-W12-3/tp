package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.util.Date;

public class UpdateRecurringExpenseCommand implements Command {
    private static final int MAX_EXPENSE_AMOUNT = 100000000; // 1 million dollars in cents
    private static final int MAX_CENTS = 99; // Maximum cents value (2 digits)
    private final Parser parser;

    public UpdateRecurringExpenseCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        assert expenseList != null : "ExpenseList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert categories != null : "Categories should not be null";

        try {
            ViewRecurringExpensesCommand viewRecurringExpenses = new ViewRecurringExpensesCommand();
            viewRecurringExpenses.execute(expenseList, ui, storage, categories);
            ui.showMessage("Enter 0 to cancel the update operation.");

            int index = parser.readIntWithCancel("Enter the index of the recurring expense to update:");

            if (index == -1) {
                ui.showMessage("Update operation cancelled.");
                ui.printBorder();
                return;
            }
            if (index <= 0 || index > expenseList.getRecurringExpenses().size()) {
                ui.showError("Invalid index. Please type 'update recurring' and then follow the instructions: " +
                        "enter a valid index from the list of recurring expenses");
                ui.printBorder();
                return;
            }

            RecurringExpense updatedExpense = parser.readRecurringExpenseDetails();

            if (updatedExpense.getAmount() > MAX_EXPENSE_AMOUNT) {
                throw new FinTrackException("Expense amount cannot exceed $1,000,000.00 (one million dollars)");
            }

            // Validate that cents are limited to 2 digits
            int cents = updatedExpense.getAmount() % 100;
            if (cents > MAX_CENTS) {
                throw new FinTrackException("Cents must be between 0 and 99 (2 digits only)");
            }

            if (updatedExpense.getAmount() > MAX_EXPENSE_AMOUNT) {
                throw new FinTrackException("Expense amount cannot exceed $1,000,000.00 (one million dollars)");
            }


            if (updatedExpense.getCategory() == null || updatedExpense.getCategory().trim().isEmpty()) {
                throw new FinTrackException("Expense category cannot be empty");
            }
            Date currentDate = new Date();
            updatedExpense.setLastProcessedDate(currentDate);
            expenseList.updateRecurringExpense(index, updatedExpense);

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
