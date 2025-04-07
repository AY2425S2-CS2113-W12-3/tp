package seedu.fintrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExpenseList {
    private ArrayList<Expense> expenseList;
    private ArrayList<RecurringExpense> recurringExpenses;
    private int monthlyBudget = 0;
    private int remainingBudget;


    public ExpenseList() {
        this.expenseList = new ArrayList<>();
        this.recurringExpenses = new ArrayList<>();
        this.monthlyBudget =  0;
        this.remainingBudget =  0;
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
        remainingBudget = remainingBudget - expense.getAmount();
        Savings.updateTotalSavings(expense.getAmount());
    }

    public void deleteExpense(Expense expense) {
        expenseList.remove(expense);
    }

    public Expense getExpense(int index) { // Changed visibility to public
        return expenseList.get(index);
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public int size() {
        return expenseList.size();
    }

    public void updateExpense(int index, Expense expense) {
        expenseList.set(index-1, expense);
    }

    public void addRecurringExpense(RecurringExpense recurringExpense) {
        recurringExpenses.add(recurringExpense);
        addRecurringExpenses(recurringExpense);
    }

    public void deleteRecurringExpense(int index) {
        recurringExpenses.remove(index);
    }

    public ArrayList<RecurringExpense> getRecurringExpenses() {
        return recurringExpenses;
    }

    public RecurringExpense getRecurringExpense(int index) { // Changed visibility to public
        return recurringExpenses.get(index);
    }

    public void setMonthlyBudget(int budget) {
        this.monthlyBudget = budget;
        this.remainingBudget = budget;
    }

    public void updateMonthlyBudget(int budget) {
        int amountSpent = this.monthlyBudget - this.remainingBudget;
        this.monthlyBudget = budget;
        this.remainingBudget = budget - amountSpent;
    }

    public int getMonthlyBudget() {
        return monthlyBudget;
    }


    public int getRemainingBudget() {
        return remainingBudget;
    }

    public void updateRecurringExpense(int index, RecurringExpense expense) {
        expenseList.set(index-1, expense);
    }

    public void addAllRecurringExpenses() {
        for (RecurringExpense r: recurringExpenses) {
            addRecurringExpenses(r);
        }
    }

    public void addRecurringExpenses(RecurringExpense recurringExpense) {
        assert recurringExpense != null : "RecurringExpense cannot be null";

        Date startDate = recurringExpense.getStartDate();
        Date currentDate = new Date();
        assert recurringExpense.getStartDate() != null : "Start date must be set";

        Date nextDate = recurringExpense.getLastProcessedDate() != null?
                recurringExpense.getLastProcessedDate() : startDate;

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(nextDate);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);

        List<Expense> newExpenses = new ArrayList<>();
        while (startCalendar.getTime().before(currentDate) || startCalendar.getTime().equals(currentDate)) {
            Date previousDate = startCalendar.getTime();
            Expense expense = new Expense(
                    recurringExpense.getAmount(),
                    recurringExpense.getCategory(),
                    recurringExpense.getDescription(),
                    startCalendar.getTime()
            );
            newExpenses.add(expense);
            Savings.updateTotalSavings(recurringExpense.getAmount());

            assert recurringExpense.getFrequency() != null : "Frequency must be set";
            switch (recurringExpense.getFrequency().toLowerCase()) {
            case "daily":
                startCalendar.add(Calendar.DAY_OF_MONTH,1);
                break;
            case "weekly":
                startCalendar.add(Calendar.WEEK_OF_YEAR, 1);
                break;
            case "monthly":
                startCalendar.add(Calendar.MONTH, 1);
                break;
            case "yearly":
                startCalendar.add(Calendar.YEAR, 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid frequency: " + recurringExpense.getFrequency());
            }
            assert startCalendar.getTime().after(previousDate)
                    : "Date did not advance for frequency: " + recurringExpense.getFrequency();
        }
        if (!newExpenses.isEmpty()) {
            recurringExpense.setLastProcessedDate(newExpenses.get(newExpenses.size() - 1).getDate());
            assert recurringExpense.getLastProcessedDate() != null : "Last processed date was not set";
            expenseList.addAll(newExpenses);
        } else {
            recurringExpense.setLastProcessedDate(currentCalendar.getTime());
        }
    }
}
