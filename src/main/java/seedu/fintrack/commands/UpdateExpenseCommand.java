package seedu.fintrack.commands;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

public class UpdateExpenseCommand implements Command {
    private final ExpenseList expenseList;
    private final Parser parser;

    public UpdateExpenseCommand(ExpenseList expenseList, Parser parser) {
        this.expenseList = expenseList;
        this.parser = parser;
    }

    @Override
    public void execute() throws FinTrackException {
        new ViewHistoryCommand(expenseList).execute();
        int index = parser.readInt("Enter the index of the expense to update:");
        if (index <= 0 || index > expenseList.size()) {
            Ui.showError("Invalid index. Please type 'update' and then follow the instructions: " +
                    "enter a valid index from your spending history.");
            Ui.printBorder();
            return;
        }
        Expense updatedExpense = parser.readExpenseDetails();
        if (expenseList.getMonthlyBudget() > 0) {
            Ui.showMessage("Your remaining budget for the month is: " + expenseList.getRemainingBudget());
        }
        expenseList.updateExpense(index, updatedExpense);
        Ui.showMessage("Expense updated.");
        Storage.saveExpensesToFile(expenseList);
        Ui.printBorder();
    }
}
