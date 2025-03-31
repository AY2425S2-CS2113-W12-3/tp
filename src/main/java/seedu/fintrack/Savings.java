package seedu.fintrack;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Savings {
    private static int income; // presumed to be on a monthly basis
    private static int savingsGoal;
    private int totalSavings;
    private static String currentMonth;

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
    public static int getIncome() {
        return income;
    }

    public static int getSavingsGoal() {
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

    public static int calculateMonthlyExpenses(ExpenseList expenseList) {
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
