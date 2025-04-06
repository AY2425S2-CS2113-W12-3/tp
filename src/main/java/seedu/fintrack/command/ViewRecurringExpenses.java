package seedu.fintrack.command;

import seedu.fintrack.Categories;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import java.util.ArrayList;

public class ViewRecurringExpenses implements Command {
    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        int index = 1;
        ArrayList<RecurringExpense> recurringExpenses = expenseList.getRecurringExpenses();
        if (recurringExpenses.isEmpty()) {
            ui.showMessage("There are currently no recurring expenses");
        } else {
            ui.showMessage("These are your current recurring expenses:");
            for (RecurringExpense r : recurringExpenses) {
                ui.showMessage(index + ". " + r.getDescription() + ": " + r.getAmount() + ", "
                        + r.getFrequency());
                index++;
            }
        }
        ui.printBorder();
    }
}
