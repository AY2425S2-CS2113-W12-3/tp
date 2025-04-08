package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.Savings;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;
import seedu.fintrack.utils.Storage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewMonthCommand implements Command {
    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        Date now = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM-yyyy");
        String currentMonth = monthFormat.format(now);
        ui.showMessage("Month: " + currentMonth);
        ui.showMessage("Your current income: " + Savings.getIncome());
        int monthlyExpenses = Savings.calculateMonthlyExpenses(ExpenseList.getExpenseList());

        if (monthlyExpenses < Savings.getSavingsGoal()) {
            ui.showMessage("You have yet to hit your savings goal You can still spend "
                    + (Savings.getSavingsGoal() - monthlyExpenses));
        } else {
            ui.showMessage("You have exceeded your monthly savings goal by "
                    + (monthlyExpenses - Savings.getSavingsGoal()));
        }

        ui.showMessage("Here is your expenses for this month: ");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.getExpense(i);
            if (monthFormat.format(expense.getDate()).equals(currentMonth)) {
                ui.showMessage(i + 1 + ": " + expense.getDescription() +
                        " - " + expense.getAmount() + " cents");
            }
        }
        ui.printBorder();
    }
}
