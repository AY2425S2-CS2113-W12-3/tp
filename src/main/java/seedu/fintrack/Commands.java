package seedu.fintrack;

import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Commands {
    private final ExpenseList expenseList;
    private final Parser parser;
    // Map of descriptive command keywords to their actions.
    private final HashMap<String, Runnable> commands = new HashMap<>();

    public Commands(ExpenseList expenseList, Parser parser) {
        assert expenseList != null : "Expense list should not be null";
        assert parser != null : "Parser should not be null";

        this.expenseList = expenseList;
        this.parser = parser;

        commands.put("add", this::addExpense);
        commands.put("viewmonth", this::viewMonth);
        commands.put("history", this::viewHistory);
        commands.put("update", this::updateExpense);
        commands.put("delete", this::deleteExpense);
        commands.put("budget", () -> {
            try {
                setMonthlyBudget();
            } catch (FinTrackException e) {
                Ui.showError(e.getMessage());
            }
        });
        commands.put("recurring", () -> {
            try {
                addRecurringExpense();
            } catch (FinTrackException e) {
                Ui.showError(e.getMessage());
            }
        });
        commands.put("category add", this::addCategory);
        commands.put("category del", this::deleteCategory);
        commands.put("update income", this::updateIncome);
        commands.put("update savings goal", this::updateSavingsGoal);
        commands.put("exit", this::exit);


        commands.put("help", () -> {
            Ui.showMessage("\nDetailed Usage Instructions:");
            Ui.showMessage(Ui.cyan + " - 'add': Add expenses. For example:" +
                    "\"4,50,1, Chicken Rice, 2025-03-28\"" + Ui.reset);

            Ui.showMessage(Ui.yellow + " - 'viewmonth': Shows this month's expenses" + Ui.reset);
            Ui.showMessage(Ui.yellow + " - 'history': Shows your spending history" + Ui.reset);
            Ui.showMessage(Ui.purple + " - 'update': Modifies the details of a chosen expense entry" + Ui.reset);
            Ui.showMessage(Ui.red + " - 'delete': Deletes a chosen expense entry" + Ui.reset);
            Ui.showMessage(Ui.blue + " - 'budget': Sets a monthly budget" + Ui.reset);
            Ui.showMessage(Ui.blue + " - 'recurring': Adds a recurring expense into the expense list" + Ui.reset);
            Ui.showMessage(Ui.green + " - 'category add': Adds a new category into the category list" + Ui.reset);
            Ui.showMessage(Ui.red + " - 'category del': Deletes a chosen category from category list" + Ui.reset);
            Ui.showMessage(Ui.cyan + " - 'update income': Updates the income of the expense entry" + Ui.reset);
            Ui.showMessage(Ui.cyan + " - 'update savings goal': Updates the monthly savings goal" + Ui.reset);
            Ui.showMessage(Ui.red + " - 'exit': Exits the program" + Ui.reset);
            Ui.printBorder();
        });


        assert commands.size() == 13 : "Commands map should contain 11 commands (including help)";
    }

    /**
     * Executes the command associated with the given key.
     * If the command is invalid, an error message is shown with instructions.
     *
     * @param commandKey the command keyword entered by the user.
     */
    public void fetchCommand(String commandKey) {
        Runnable command = commands.get(commandKey);
        if (command != null) {
            command.run();
        } else {
            Ui.showError("Invalid command. Please type 'help' to see the available commands and their" +
                    " proper formats.");
        }
    }


    public void addExpense() {
        try {
            // Use parser to read all expense details in one go.
            Expense expense = parser.readExpenseDetails();
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
        } catch (FinTrackException e) {
            Ui.showError("C'mon Imma need more info that that!\n" + "type 'add' and enter " +
                    "then follow this format: " + " \"4,50,1, Chicken Rice, 2025-03-28\" ");
            Ui.printBorder();
        }
    }

    public void viewMonth() {
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

    public void viewHistory() {
        try {
            Ui.showMessage("Spending history:");
            for (int i = 0; i < expenseList.size(); i++) {
                Expense expense = expenseList.getExpense(i);
                Ui.showMessage((i + 1) + ": " + expense.getDescription() +
                        " - " + expense.getAmount() + " cents on " + expense.getDate() + " (" +
                        expense.getCategory() + ")");
            }
            Ui.printBorder();
        } catch (Exception e) {
            Ui.showError("An error occurred while displaying history: " + e.getMessage());
            Ui.printBorder();
        }
    }


    public void updateExpense() {
        try {
            viewHistory();
            int index = parser.readInt("Enter the index of the expense to update:");
            if (index <= 0 || index > expenseList.size()) {
                Ui.showError("Invalid index. Please type 'update' and then follow the instructions: " +
                        "enter a valid index from your spending history.");
                Ui.printBorder();
                return;
            }
            Expense updatedExpense = parser.readExpenseDetails();
            if (expenseList.getMonthlyBudget() > 0) {
                Ui.showMessage("Your remaining budget for the month is: " + expenseList.getRemainingBudget());
            }
            expenseList.updateExpense(index, updatedExpense);
            Ui.showMessage("Expense updated.");
            Storage.saveExpensesToFile(expenseList);
            Ui.printBorder();
        } catch (FinTrackException e) {
            Ui.showError("Error updating expense: " + e.getMessage() +
                    "\nPlease type 'update' and follow this format: \"<dollars>, <cents>, <category index>, " +
                    "<description>, <date (yyyy-MM-dd)>\".");
            Ui.printBorder();
        }
    }

    public void deleteExpense() {
        try {
            viewHistory();
            int index = parser.readInt("Enter the index of the expense to delete:");
            if (index <= 0 || index > expenseList.size()) {
                Ui.showError("Invalid index. Please type 'delete' and then choose a valid index " +
                        "from your spending history.");
                Ui.printBorder();
                return;
            }
            Expense expense = expenseList.getExpense(index - 1);
            int sizeBefore = expenseList.size();
            expenseList.deleteExpense(expense);
            if (expenseList.size() != sizeBefore - 1) {
                throw new FinTrackException("Expense list did not decrement as expected");
            }
            Ui.showMessage("Expense deleted.");
            Storage.saveExpensesToFile(expenseList);
            Ui.printBorder();
        } catch (FinTrackException e) {
            Ui.showError("Error deleting expense: " + e.getMessage() + "\nPlease type 'delete'" +
                    "and choose a valid index from your spending history.");
            Ui.printBorder();
        }
    }


    public void exit() {
        Storage.saveExpensesToFile(expenseList);
        Ui.showMessage("Make sure to have good saving habits yeah, cyaaaa.");
        System.exit(0);
    }

    public void setMonthlyBudget() throws FinTrackException {
        int budget = parser.readInt("Enter monthly budget:");
        if (budget < 0) {
            throw new FinTrackException("Budget must be non-negative");
        }
        if (expenseList.getMonthlyBudget() != 0) {
            expenseList.updateMonthlyBudget(budget);
        } else {
            expenseList.setMonthlyBudget(budget);
        }
        Ui.showMessage("Monthly budget set to: " + budget);
        Ui.printBorder();
    }

    public void addRecurringExpense() throws FinTrackException {
        int amount = parser.readInt("Enter expense amount (in cents):");
        if (amount < 0) {
            throw new FinTrackException("Expense amount must be non-negative.");
        }
        String category = parser.promptInput("Enter expense category:");
        String frequency = parser.promptInput("Enter frequency (Weekly, Monthly, Yearly):");
        if (!frequency.equalsIgnoreCase("Weekly") &&
                !frequency.equalsIgnoreCase("Monthly") &&
                !frequency.equalsIgnoreCase("Yearly")) {
            throw new FinTrackException("Invalid frequency. Must be Weekly, Monthly, or Yearly.");
        }
        String description = parser.promptInput("Enter expense description:");
        Date date = parser.readDate("Enter current date (format yyyy-MM-dd):");
        Date startDate = parser.readDate("Enter start date (format yyyy-MM-dd):");

        RecurringExpense recurringExpense = new RecurringExpense(amount, category,
                frequency, description, startDate, startDate);
        expenseList.addRecurringExpense(recurringExpense);
        Ui.showMessage("Recurring expense added.");
        Ui.printBorder();
    }

    public void addCategory() {
        String newCategory = parser.promptInput("Please enter the name of the new category:");
        if(newCategory.isEmpty()) {
            Ui.showError("Empty input. Please enter a category.");
            Ui.printBorder();
            return;
        }
        if(Categories.getCategories().contains(newCategory)) {
            Ui.showError("Category already exists.");
            Ui.printBorder();
            return;
        }
        Categories.addCategory(newCategory);
        Ui.showMessage(newCategory + " has been added to the list of categories.");
        Storage.saveCategoriesToFile();
        Ui.printBorder();
    }

    public void deleteCategory() {
        Categories.printCategories();
        if(Categories.getCategories().isEmpty()) {
            Ui.printBorder();
            return;
        }
        int index = parser.readInt("Enter the index of the category to delete:");
        if (index <= 0 || index > Categories.size()) {
            Ui.showError("Invalid index.");
            Ui.printBorder();
            return;
        }
        String deletedCategory = Categories.getCategory(index);
        Categories.removeCategory(index);
        Ui.showMessage(deletedCategory + " has been deleted.");
        Storage.saveCategoriesToFile();
        Ui.printBorder();
    }

    public void updateIncome() {
        Ui.printBorder();
        Ui.showMessage("Your current income: ");
        Ui.showMessage(Integer.toString(Savings.getIncome()));
        int newIncome = parser.readInt("Enter your new income:");
        if (newIncome > 0) {
            Savings.updateIncome(newIncome);
            Storage.saveSavingsToFile(newIncome, Savings.getSavingsGoal(), Savings.getCurrentSavings());
            Ui.showMessage("Income updated to " + newIncome + ".");
        } else {
            Ui.showMessage("Income not updated as the number you entered is invalid.");
        }
        Ui.printBorder();
    }

    public void updateSavingsGoal() {
        Ui.printBorder();
        Ui.showMessage("Your current savings goal: ");
        Ui.showMessage(Integer.toString(Savings.getSavingsGoal()));
        int newSavingsGoal = parser.readInt("Enter your new savings goal:");
        if (newSavingsGoal > 0) {
            Savings.updateSavingsGoal(newSavingsGoal);
            Storage.saveSavingsToFile(Savings.getIncome(), newSavingsGoal, Savings.getCurrentSavings());
            Ui.showMessage("Savings goal updated to " + newSavingsGoal + ".");
        } else {
            Ui.showMessage("Savings goal not updated as the number you entered is invalid.");
        }
        Ui.printBorder();
    }
}
