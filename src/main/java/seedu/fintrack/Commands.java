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

        commands.put("add", () -> {
            try {
                addExpense();
            } catch (FinTrackException e) {
                Ui.showError(e.getMessage());
            }
        });
        commands.put("viewmonth", this::viewMonth);
        commands.put("history", this::viewHistory);
        commands.put("update", () -> {
            try {
                updateExpense();
            } catch (FinTrackException e) {
                Ui.showError(e.getMessage());
            }
        });
        commands.put("delete", () -> {
            try {
                deleteExpense();
            } catch (FinTrackException e) {
                Ui.showError(e.getMessage());
            }
        });
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
        commands.put("exit", this::exit);


        commands.put("help", () -> {
            Ui.showMessage("\nDetailed Usage Instructions:");
            Ui.showMessage(Ui.cyan + " - 'add': Adds a new expense into the expense list" + Ui.reset);
            Ui.showMessage(Ui.green + " - 'viewmonth': Shows this month's expenses" + Ui.reset);
            Ui.showMessage(Ui.yellow + " - 'history': Shows your spending history" + Ui.reset);
            Ui.showMessage(Ui.purple + " - 'update': Modifies the details of a chosen expense entry" + Ui.reset);
            Ui.showMessage(Ui.red + " - 'delete': Deletes a chosen expense entry" + Ui.reset);
            Ui.showMessage(Ui.blue + " - 'budget': Sets a monthly budget" + Ui.reset);
            Ui.showMessage(Ui.cyan + " - 'recurring': Adds a recurring expense into the expense list" + Ui.reset);
            Ui.showMessage(Ui.green + " - 'category add': Adds a new category into the category list" + Ui.reset);
            Ui.showMessage(Ui.yellow + " - 'category del': Deletes a chosen category from category list" + Ui.reset);
            Ui.showMessage(Ui.red + " - 'exit': Exits the program" + Ui.reset);
            Ui.printBorder();
        });


        assert commands.size() == 11 : "Commands map should contain 11 commands (including help)";
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
            Ui.showError("Invalid command. Please type 'help' to see the available commands and their proper formats.");
        }
    }

    public void addExpense() throws FinTrackException {
        // Use parser to read all expense details in one go.
        Expense expense = parser.readExpenseDetails();
        int sizeBefore = expenseList.size();
        expenseList.addExpense(expense);
        if (expenseList.getMonthlyBudget() > 0) {
            Ui.showMessage("Your remaining budget for the month is: " + expenseList.getRemainingBudget());
        }
        assert expenseList.size() == sizeBefore + 1 : "Expense list did not increment as expected";
        Ui.showMessage("Expense added.");
        Storage.saveExpensesToFile(expenseList);
        Ui.printBorder();
    }

    private void viewMonth() {
        Date now = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM-yyyy");
        String currentMonth = monthFormat.format(now);
        Ui.showMessage("Expenses for " + currentMonth + ":");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.getExpense(i);
            if (monthFormat.format(expense.getDate()).equals(currentMonth)) {
                Ui.showMessage(i+1 + ": " + expense.getDescription() +
                        " - " + expense.getAmount() + " cents");
            }
        }
        Ui.printBorder();
    }

    private void viewHistory() {
        Ui.showMessage("Spending history:");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.getExpense(i);
            Ui.showMessage(i+1 + ": " + expense.getDescription() +
                    " - " + expense.getAmount() + " cents on " + expense.getDate() + " (" +
                    expense.getCategory() + ")");
        }
        Ui.printBorder();
    }

    private void updateExpense() throws FinTrackException {
        viewHistory();
        int index = parser.readInt("Enter the index of the expense to update:");
        if (index <= 0 || index > expenseList.size()) {
            Ui.showError("Invalid index.");
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
    }

    private void deleteExpense() throws FinTrackException {
        viewHistory();
        int index = parser.readInt("Enter the index of the expense to delete:");
        if (index <= 0 || index > expenseList.size()) {
            Ui.showError("Invalid index.");
            return;
        }
        Expense expense = expenseList.getExpense(index-1);
        int sizeBefore = expenseList.size();
        expenseList.deleteExpense(expense);
        assert expenseList.size() == sizeBefore - 1 : "Expense list did not decrement as expected";
        Ui.showMessage("Expense deleted.");
        Storage.saveExpensesToFile(expenseList);
        Ui.printBorder();
    }

    private void exit() {
        Storage.saveExpensesToFile(expenseList);
        Ui.showMessage("Exiting program.");
        System.exit(0);
    }

    private void setMonthlyBudget() throws FinTrackException {
        int budget = parser.readInt("Enter monthly budget:");
        if (budget < 0) {
            throw new FinTrackException("Budget must be non-negative");
        }
        expenseList.setMonthlyBudget(budget);
        Ui.showMessage("Monthly budget set to: " + budget);
        Ui.printBorder();
    }

    private void addRecurringExpense() throws FinTrackException {
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

    private void addCategory() {
        String newCategory = parser.promptInput("Please enter the name of the new category:");
        Categories.addCategory(newCategory);
        Ui.showMessage(newCategory + " has been added to the list of categories.");
        Storage.saveCategoriesToFile();
        Ui.printBorder();
    }

    private void deleteCategory() {
        Categories.printCategories();
        int index = parser.readInt("Enter the index of the category to delete:");
        if (index <= 0 || index > Categories.size()) {
            Ui.showError("Invalid index.");
            return;
        }
        String deletedCategory = Categories.getCategory(index);
        Categories.removeCategory(index);
        Ui.showMessage(deletedCategory + " has been deleted.");
        Storage.saveCategoriesToFile();
        Ui.printBorder();
    }
}
