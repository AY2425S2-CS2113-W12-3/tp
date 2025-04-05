package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class SetMonthlyBudgetCommand implements Command {
    private final Parser parser;

    public SetMonthlyBudgetCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        int budget = parser.readInt("Enter monthly budget:");
        if (budget < 0) {
            throw new FinTrackException("Budget must be non-negative");
        }
        if (expenseList.getMonthlyBudget() != 0) {
            expenseList.updateMonthlyBudget(budget);
        } else {
            expenseList.setMonthlyBudget(budget);
        }
        ui.showMessage("Monthly budget set to: " + budget);
        ui.printBorder();
    }
}

