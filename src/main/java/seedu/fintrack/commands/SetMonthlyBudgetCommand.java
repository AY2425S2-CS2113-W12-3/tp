package seedu.fintrack.commands;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Ui;

public class SetMonthlyBudgetCommand implements Command {
    private final ExpenseList expenseList;
    private final Parser parser;

    public SetMonthlyBudgetCommand(ExpenseList expenseList, Parser parser) {
        this.expenseList = expenseList;
        this.parser = parser;
    }

    @Override
    public void execute() throws FinTrackException {
        int budget = parser.readInt("Enter monthly budget:");
        if (budget < 0) {
            throw new FinTrackException("Budget must be non-negative");
        }
        expenseList.setMonthlyBudget(budget);
        Ui.showMessage("Monthly budget set to: " + budget);
        Ui.printBorder();
    }
}
