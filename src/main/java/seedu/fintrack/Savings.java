package seedu.fintrack;

import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Savings {
    int income; // presumed to be on a monthly basis
    int savingsGoal;
    String currentMonth;

    Savings(){
        income = 0;
        savingsGoal = 0;
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM"); // Format for month (e.g., 01, 02, ...)
        Date now = new Date(); // Get the current date and time
        this.currentMonth = monthFormat.format(now);
    }


    //getters
    public int getIncome() {
        return income;
    }

    public int getSavingsGoal() {
        return savingsGoal;
    }

    //setters
    public void updateIncome(int monthlyIncome) {
        this.income = monthlyIncome;
    }
s
    public void updateSavingsGoal(int monthlySavingsGoal) {
        this.savingsGoal = monthlySavingsGoal;
    }

    int calculateMonthlyExpesnes(ExpenseList expenseList) {
        int monthlyExpenses = 0;


        for(Expense expense : expenseList.getExpenseList()) {
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            if (monthFormat.format(expense.getDate()).equals(currentMonth)) {
                monthlyExpenses += expense.getAmount();
            }
        }

        return monthlyExpenses;
    }
}
