//@@author akaash02
package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;
import seedu.fintrack.Savings;

public class UpdateIncomeCommand implements Command {
    private static final int MAX_EXPENSE_AMOUNT = 100000000; // 1 million dollars in cents
    private final Parser parser;


    public UpdateIncomeCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try{
            ui.printBorder();
            ui.showMessage("Your current income: ");
            float oldIncome = (float)(Savings.getIncome()) / 100;
            ui.showMessage(Float.toString(oldIncome));
            int newIncome = parser.readInt("Enter your new income in dollars " +
                    "(your income is only input in dollars) :");

            if(newIncome <= 0 ){
                throw new FinTrackException("Please enter an amount larger than 0.");
            }

            if(newIncome > MAX_EXPENSE_AMOUNT){
                throw new FinTrackException("Please enter an amount less than $1000000.");
            }

            Savings.updateIncome(newIncome * 100);
            Storage.saveSavingsToFile(newIncome * 100, Savings.getSavingsGoal(), Savings.getCurrentSavings());
            float finalIncome = (float)(newIncome * 100) / 100;
            ui.showMessage("Your income has been update to $" + finalIncome);
            ui.printBorder();
        } catch (FinTrackException e) {
            ui.showError(e.getMessage());
            ui.printBorder();
        } catch (Exception e) {
            ui.showError("Please enter a valid integer input" + e.getMessage());
            ui.printBorder();
        }
    }
}
