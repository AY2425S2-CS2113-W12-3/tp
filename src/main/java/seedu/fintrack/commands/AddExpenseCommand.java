package seedu.fintrack.commands;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

public class AddExpenseCommand implements Command {
    private final ExpenseList expenseList;
    private final Parser parser;

    public AddExpenseCommand(ExpenseList expenseList, Parser parser) {
        this.expenseList = expenseList;
        this.parser = parser;
    }

    @Override
    public void execute() throws FinTrackException {
        Expense expense = parser.readExpenseDetails();
        if (expense.getAmount() < 0) {
            Ui.showError("Expense amount cannot be negative.");
            Ui.printBorder();
            return;
        }
        int sizeBefore = expenseList.size();
        expenseList.addExpense(expense);
        if (expenseList.getMonthlyBudget() > 0) {
            Ui.showMessage("Your remaining budget for the month is: " + expenseList.getRemainingBudget());
        }
        if (expenseList.size() != sizeBefore + 1) {
            throw new FinTrackException("Expense list did not increment as expected");
        }
        Ui.showMessage("Expense added.");
        Storage.saveExpensesToFile(expenseList);
        Ui.printBorder();
    }
}
