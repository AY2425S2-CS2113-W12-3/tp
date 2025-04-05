package seedu.fintrack.commands;

import seedu.fintrack.Savings;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

public class UpdateIncomeCommand implements Command {
    private final Parser parser;

    public UpdateIncomeCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute() throws FinTrackException {
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
}
