package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;
import seedu.fintrack.Savings;

public class UpdateSavingsGoalCommand implements Command {
    private final Parser parser;

    public UpdateSavingsGoalCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        ui.printBorder();
        ui.showMessage("Your current savings goal: ");
        ui.showMessage(Integer.toString(Savings.getSavingsGoal()));
        int newSavingsGoal = parser.readInt("Enter your new savings goal:");
        if (newSavingsGoal > 0) {
            Savings.updateSavingsGoal(newSavingsGoal);
            storage.saveSavingsToFile(Savings.getIncome(), newSavingsGoal, Savings.getCurrentSavings());
            ui.showMessage("Savings goal updated to " + newSavingsGoal + ".");
        } else {
            ui.showMessage("Savings goal not updated as the number you entered is invalid.");
        }
        ui.printBorder();
    }
}
