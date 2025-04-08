package seedu.fintrack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExpenseList {
    private static ArrayList<Expense> expenseList;
    private ArrayList<RecurringExpense> recurringExpenses;

    public ExpenseList() {
        this.expenseList = new ArrayList<>();
        this.recurringExpenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
        Savings.updateTotalSavings(expense.getAmount());
    }

    public void deleteExpense(Expense expense) {
        expenseList.remove(expense);
        Savings.addToSavings(expense.getAmount());
    }

    public Expense getExpense(int index) { // Changed visibility to public
        return expenseList.get(index);
    }

    public static ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public int size() {
        return expenseList.size();
    }

    public void updateExpense(int index, Expense expense) {
        Expense oldExpense = expenseList.get(index - 1);
        Savings.updateTotalSavings(expense.getAmount());
        Savings.addToSavings(oldExpense.getAmount());

        expenseList.set(index - 1, expense);
    }

    public void addRecurringExpense(RecurringExpense recurringExpense) {
        recurringExpenses.add(recurringExpense);
        int previousMonthlyBudget = Savings.getMonthlyBudget();
        Savings.updateMonthlyBudget(recurringExpenses);
        Savings.updateCurrentMonthlyBudget(previousMonthlyBudget);
    }

    public void deleteRecurringExpense(int index) {
        recurringExpenses.remove(index);
        int previousMonthlyBudget = Savings.getMonthlyBudget();
        Savings.updateMonthlyBudget(recurringExpenses);
        Savings.updateCurrentMonthlyBudget(previousMonthlyBudget);
    }

    public ArrayList<Expense> getExpensesList() {
        return expenseList;
    }

    public ArrayList<RecurringExpense> getRecurringExpenses() {
        return recurringExpenses;
    }

    public RecurringExpense getRecurringExpense(int index) { // Changed visibility to public
        return recurringExpenses.get(index);
    }

    public void updateRecurringExpense(int index, RecurringExpense expense) {
        recurringExpenses.set(index - 1, expense);
        int previousMonthlyBudget = Savings.getMonthlyBudget();
        Savings.updateMonthlyBudget(recurringExpenses);
        Savings.updateCurrentMonthlyBudget(previousMonthlyBudget);
    }

    public void addAllRecurringExpenses() {
        for (RecurringExpense r : recurringExpenses) {
            addRecurringExpenses(r);
        }
    }


    public void addRecurringExpenses(RecurringExpense recurringExpense) {
        assert recurringExpense != null : "RecurringExpense cannot be null";

        Date nextDate = recurringExpense.getLastProcessedDate();

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(nextDate);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);


        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);
        Date currentDate = currentCalendar.getTime();

        if (nextDate.equals(currentDate)) {
            return;
        }
        List<Expense> newExpenses = new ArrayList<>();
        while ((startCalendar.getTime().before(currentDate) || startCalendar.getTime().equals(currentDate))
                && (recurringExpense.getLastProcessedDate() != currentDate)) {
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
                startCalendar.add(Calendar.DAY_OF_MONTH, 1);
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
        }
    }

    public static int getTotalByCategory(ExpenseList expenseList, String category) {
        int total = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.getExpense(i);
            if (expense.getCategory().equalsIgnoreCase(category)) {
                total += expense.getAmount();
            }
        }
        return total;
    }
}

