package seedu.fintrack.commands;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Ui;

public class ViewHistoryCommand implements Command {
    private final ExpenseList expenseList;

    public ViewHistoryCommand(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public void execute() throws FinTrackException {
        Ui.showMessage("Spending history:");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.getExpense(i);
            Ui.showMessage((i + 1) + ": " + expense.getDescription() +
                    " - " + expense.getAmount() + " cents on " + expense.getDate() + " (" +
                    expense.getCategory() + ")");
        }
        Ui.printBorder();
    }
}
