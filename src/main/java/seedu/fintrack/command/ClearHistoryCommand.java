package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class ClearHistoryCommand implements Command {
    private final Parser parser;

    public ClearHistoryCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try {
            // Show warning and ask for confirmation
            ui.showMessage(Ui.red + "WARNING: This will delete ALL your expense history!" + Ui.reset);
            ui.showMessage("This action cannot be undone.");
            String confirmation = parser.promptInput("Are you sure you want to proceed? (yes/no)");
            
            if (confirmation.equalsIgnoreCase("yes")) {
                // Clear the expense list
                expenseList.getExpenseList().clear();
                // Save the empty list to file
                storage.saveExpensesToFile(expenseList);
                ui.showMessage("All expense history has been cleared.");
            } else {
                ui.showMessage("Clear history operation cancelled.");
            }
            ui.printBorder();
        } catch (Exception e) {
            ui.showError("Error clearing history: " + e.getMessage());
            ui.printBorder();
        }
    }
}
