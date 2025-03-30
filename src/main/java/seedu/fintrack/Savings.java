package seedu.fintrack;

import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Savings {
    private int income; // presumed to be on a monthly basis
    private int savingsGoal;
    private int totalSavings;
    private String currentMonth;

    public Savings(){
        this.income = 0;
        this.savingsGoal = 0;
        this.totalSavings = 0;
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM"); // Format for month (e.g., 01, 02, ...)
        Date now = new Date(); // Get the current date and time
        this.currentMonth = monthFormat.format(now);
    }

    public Savings(int income, int savingsGoal, int totalSavings){
        this.income = income;
        this.savingsGoal = savingsGoal;
        this.totalSavings = totalSavings;
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

    public int getCurrentSavings() {
        return totalSavings;
    }

    //setters
    public void updateIncome(int monthlyIncome) {
        this.income = monthlyIncome;
    }

    public void updateSavingsGoal(int monthlySavingsGoal) {
        this.savingsGoal = monthlySavingsGoal;
    }

    public int calculateMonthlyExpesnes(ExpenseList expenseList) {
        int monthlyExpenses = 0;


        for(Expense expense : expenseList.getExpenseList()) {
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            if (monthFormat.format(expense.getDate()).equals(currentMonth)) {
                monthlyExpenses += expense.getAmount();
            }
        }

        return monthlyExpenses;
    }

    public void addToSavings(int newSavings ) {
        totalSavings += newSavings;
    }

    public void addIncome() {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String currentMonthCheck = monthFormat.format(new Date());

        if (!currentMonthCheck.equals(currentMonth)) {
            this.currentMonth = currentMonthCheck;
            totalSavings += income;
        }
    }
}
