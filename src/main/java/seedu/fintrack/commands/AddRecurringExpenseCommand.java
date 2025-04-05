package seedu.fintrack.commands;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Ui;

import java.util.Date;

public class AddRecurringExpenseCommand implements Command {
    private final ExpenseList expenseList;
    private final Parser parser;

    public AddRecurringExpenseCommand(ExpenseList expenseList, Parser parser) {
        this.expenseList = expenseList;
        this.parser = parser;
    }

    @Override
    public void execute() throws FinTrackException {
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
                frequency, description, startDate, date);
        expenseList.addRecurringExpense(recurringExpense);
        Ui.showMessage("Recurring expense added.");
        Ui.printBorder();
    }
}
