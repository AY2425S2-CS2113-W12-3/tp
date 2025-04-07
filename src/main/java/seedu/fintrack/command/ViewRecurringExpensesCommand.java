package seedu.fintrack.command;

import seedu.fintrack.Categories;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import java.util.ArrayList;

public class ViewRecurringExpensesCommand implements Command {
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
                double amountInDollars = r.getAmount() / 100.0;
                String formattedAmount = String.format("$%.2f", amountInDollars);
                ui.showMessage(index + ". " + r.getDescription() + ": " + formattedAmount + ", "
                        + r.getFrequency());
                index++;
            }
            assert index == recurringExpenses.size() + 1: "There are " + recurringExpenses.size()
                    + "recurring expenses in the list";
        }
        ui.printBorder();
    }
}
