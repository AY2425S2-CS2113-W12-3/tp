package seedu.fintrack.commands;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

public class DeleteExpenseCommand implements Command {
    public final ExpenseList expenseList;
    public final Parser parser;

    public DeleteExpenseCommand(ExpenseList expenseList, Parser parser) {
        this.expenseList = expenseList;
        this.parser = parser;
    }

    @Override
    public void execute() throws FinTrackException {
        new ViewHistoryCommand(expenseList).execute();
        int index = parser.readInt("Enter the index of the expense to delete:");
        if (index <= 0 || index > expenseList.size()) {
            Ui.showError("Invalid index. Please type 'delete' and then choose a valid index " +
                    "from your spending history.");
            Ui.printBorder();
            return;
        }
        Expense expense = expenseList.getExpense(index - 1);
        int sizeBefore = expenseList.size();
        expenseList.deleteExpense(expense);
        if (expenseList.size() != sizeBefore - 1) {
            throw new FinTrackException("Expense list did not decrement as expected");
        }
        Ui.showMessage("Expense deleted.");
        Storage.saveExpensesToFile(expenseList);
        Ui.printBorder();
    }
}
