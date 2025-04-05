package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class HelpCommand implements Command {
    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        ui.showMessage("\nDetailed Usage Instructions:");
        ui.showMessage(Ui.cyan + " - 'add': Add expenses. For example:" +
                "\"4,50,1, Chicken Rice, 2025-03-28\"" + Ui.reset);

        ui.showMessage(Ui.yellow + " - 'viewmonth': Shows this month's expenses" + Ui.reset);
        ui.showMessage(Ui.yellow + " - 'history': Shows your spending history" + Ui.reset);
        ui.showMessage(Ui.purple + " - 'update': Modifies the details of a chosen expense entry" + Ui.reset);
        ui.showMessage(Ui.red + " - 'delete': Deletes a chosen expense entry" + Ui.reset);
        ui.showMessage(Ui.blue + " - 'budget': Sets a monthly budget" + Ui.reset);
        ui.showMessage(Ui.blue + " - 'recurring': Adds a recurring expense into the expense list" + Ui.reset);
        ui.showMessage(Ui.green + " - 'category add': Adds a new category into the category list" + Ui.reset);
        ui.showMessage(Ui.red + " - 'category del': Deletes a chosen category from category list" + Ui.reset);
        ui.showMessage(Ui.cyan + " - 'update income': Updates the income of the expense entry" + Ui.reset);
        ui.showMessage(Ui.cyan + " - 'update savings goal': Updates the monthly savings goal" + Ui.reset);
        ui.showMessage(Ui.red + " - 'exit': Exits the program" + Ui.reset);
        ui.printBorder();
    }
}
