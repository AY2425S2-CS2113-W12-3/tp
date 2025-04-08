//@@author akaash02
package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;
import seedu.fintrack.Savings;

public class UpdateSavingsGoalCommand implements Command {
    private static final int MAX_EXPENSE_AMOUNT = 100000000; // 1 million dollars in cents
    private static final int MAX_CENTS = 99;
    private final Parser parser;


    public UpdateSavingsGoalCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try{
            ui.printBorder();
            ui.showMessage("Your current savings goal: ");
            float currentSavingsGoal = (float)(Savings.getSavingsGoal()) / 100;
            ui.showMessage(Float.toString(currentSavingsGoal));
            ui.showMessage("Enter the new monthly savings goals following these steps");
            int dollars = parser.readInt("First enter the dollar amount:\n");

            if(dollars <= 0){
                throw new FinTrackException("Dollar amount goal must be above 0");
            }


            if(dollars >= MAX_EXPENSE_AMOUNT){
                throw new FinTrackException("Dollar amount must be below max expense amount of $1000000");
            }


            int cents = parser.readInt("Next enter your cents amount:\n");

            if(cents < 0){
                throw new FinTrackException("Cents amount cannot be negative.");
            }


            if(cents >= MAX_CENTS){
                throw new FinTrackException("Cents amount must be below max expense amount of 99c");
            }

            int newSavingsGoal = (dollars * 100) + cents;

            if(newSavingsGoal <= 0){
                throw new FinTrackException("Savings goal must be above 0");
            }


            if(newSavingsGoal >= Savings.getIncome()){
                float monthlyIncome = (float)(Savings.getIncome()) / 100;
                throw new FinTrackException("Savings goal must be less than monthly income of $" + monthlyIncome);
            }

            if(newSavingsGoal >= MAX_EXPENSE_AMOUNT){
                throw new FinTrackException("Savings goal must be below max expense amount of $1000000.00");
            }


            Savings.updateSavingsGoal(newSavingsGoal);
            storage.saveSavingsToFile(Savings.getIncome(), newSavingsGoal, Savings.getCurrentSavings());
            float finalSavingsGoal = (float)(newSavingsGoal) / 100;
            ui.showMessage("Savings goal updated to $" + finalSavingsGoal);
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
