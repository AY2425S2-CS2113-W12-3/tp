package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class AddExpenseCommand implements Command {
    private final Parser parser;

    public AddExpenseCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        Expense expense = parser.readExpenseDetails();
        int sizeBefore = expenseList.size();
        expenseList.addExpense(expense);
        if (expenseList.getMonthlyBudget() > 0) {
            ui.showMessage("Your remaining budget for the month is: " + expenseList.getRemainingBudget());
        }
        if (expenseList.size() != sizeBefore + 1) {
            throw new FinTrackException("Expense list did not increment as expected");
        }
        ui.showMessage("Expense added.");
        storage.saveExpensesToFile(expenseList);
        ui.printBorder();
    }
}
