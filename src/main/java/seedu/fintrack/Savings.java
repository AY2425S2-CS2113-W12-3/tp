package seedu.fintrack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Savings {
    static String currentMonth;
    private static int income; // presumed to be on a monthly basis
    private static int savingsGoal;
    private static int totalSavings;
    private static int monthlyBudget;
    private static int currentMonthlyBudget;


    public Savings(){
        this.income = 0;
        this.savingsGoal = 0;
        this.totalSavings = 0;
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM"); // Format for month (e.g., 01, 02, ...)
        Date now = new Date(); // Get the current date and time
        this.currentMonth = monthFormat.format(now);
        this.monthlyBudget = 0;
    }

    public Savings(int income, int savingsGoal, int totalSavings){
        this.income = income;
        this.savingsGoal = savingsGoal;
        this.totalSavings = totalSavings;
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM"); // Format for month (e.g., 01, 02, ...)
        Date now = new Date(); // Get the current date and time
        this.currentMonth = monthFormat.format(now);
        this.monthlyBudget = 0;
        this.currentMonthlyBudget = 0;
    }


    //getters
    public static int getIncome() {
        return income;
    }

    public static int getSavingsGoal() {
        return savingsGoal;
    }

    public static int getCurrentSavings() {
        return totalSavings;
    }

    public static int getMonthlyBudget() {
        return monthlyBudget;
    }

    public static int getCurrentMonthlyBudget() {
        return currentMonthlyBudget;
    }

    //setters
    public static void updateIncome(int monthlyIncome) {
        income = monthlyIncome;
        if(totalSavings == 0){
            totalSavings = monthlyIncome;
        }
    }

    public static void updateSavingsGoal(int monthlySavingsGoal) {
        savingsGoal = monthlySavingsGoal;
    }

    public static int calculateMonthlyExpenses(ExpenseList expenseList) {
        int monthlyExpenses = 0;
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");

        Date now = new Date();
        currentMonth = monthFormat.format(now);

        for (Expense expense : expenseList.getExpenseList()) {
            if (monthFormat.format(expense.getDate()).equals(currentMonth)) {
                monthlyExpenses += expense.getAmount();
            }
        }

        return monthlyExpenses;
    }

    public static void updateMonthlyBudget(ArrayList<RecurringExpense> recurringExpenses) {
        int totalRecurringExpenses = 0;

        for(RecurringExpense recurringExpense:recurringExpenses){
            if(recurringExpense.getFrequency().equals("monthly")){
                totalRecurringExpenses += recurringExpense.getAmount();
            } else if(recurringExpense.getFrequency().equals("weekly")){
                totalRecurringExpenses += recurringExpense.getAmount() * 4;
            } else if(recurringExpense.getFrequency().equals("daily")){
                totalRecurringExpenses += recurringExpense.getAmount() * 30;
            }
        }

        monthlyBudget += income - savingsGoal - totalRecurringExpenses;
    }

    public static void updateCurrentMonthlyBudget(Expense expense) {
        currentMonthlyBudget -= expense.getAmount();
    }

    public static void addToCurrentMonthlyBudget(Expense expense) {
        currentMonthlyBudget += expense.getAmount();
    }

    public static void addToSavings(int newSavings) {
        totalSavings += newSavings;
    }

    public void addIncome() {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String currentMonthCheck = monthFormat.format(new Date());

        if (!currentMonthCheck.equals(currentMonth)) {
            currentMonth = currentMonthCheck;
            totalSavings += income;
            currentMonthlyBudget = monthlyBudget;
        }
    }

    public static void updateTotalSavings(int expenseAmount) {
        totalSavings -= expenseAmount;
    }
}
