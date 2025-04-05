package seedu.fintrack.commands;

import seedu.fintrack.Savings;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

public class UpdateSavingsGoalCommand implements Command {
    private final Parser parser;

    public UpdateSavingsGoalCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute() throws FinTrackException {
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
