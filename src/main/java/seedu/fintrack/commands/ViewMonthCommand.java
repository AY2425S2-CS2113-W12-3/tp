package seedu.fintrack.commands;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.Savings;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Ui;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewMonthCommand implements Command {
    private final ExpenseList expenseList;

    public ViewMonthCommand(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public void execute() throws FinTrackException {
        Date now = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM-yyyy");
        String currentMonth = monthFormat.format(now);
        Ui.showMessage("Month: " + currentMonth);
        Ui.showMessage("Your current income: " + Savings.getIncome());
        int monthlyExpenses = Savings.calculateMonthlyExpenses(expenseList);

        if(monthlyExpenses < Savings.getSavingsGoal()) {
            Ui.showMessage("You have yet to hit your savings goal You can still spend "
                    + (Savings.getSavingsGoal() - monthlyExpenses));
        } else {
            Ui.showMessage("You have exceeded your monthly savings goal by "
                    + (monthlyExpenses - Savings.getSavingsGoal()));
        }

        Ui.showMessage("Here is your expenses for this month: ");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.getExpense(i);
            if (monthFormat.format(expense.getDate()).equals(currentMonth)) {
                Ui.showMessage(i+1 + ": " + expense.getDescription() +
                        " - " + expense.getAmount() + " cents");
            }
        }
        Ui.printBorder();
    }
}
