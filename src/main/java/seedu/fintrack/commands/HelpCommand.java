package seedu.fintrack.commands;

import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Ui;

public class HelpCommand implements Command {
    @Override
    public void execute() throws FinTrackException {
        Ui.showMessage("\nDetailed Usage Instructions:");
        Ui.showMessage(Ui.cyan + " - 'add': Add expenses. For example:" +
                "\"4,50,1, Chicken Rice, 2025-03-28\"" + Ui.reset);

        Ui.showMessage(Ui.yellow + " - 'viewmonth': Shows this month's expenses" + Ui.reset);
        Ui.showMessage(Ui.yellow + " - 'history': Shows your spending history" + Ui.reset);
        Ui.showMessage(Ui.purple + " - 'update': Modifies the details of a chosen expense entry" + Ui.reset);
        Ui.showMessage(Ui.red + " - 'delete': Deletes a chosen expense entry" + Ui.reset);
        Ui.showMessage(Ui.blue + " - 'budget': Sets a monthly budget" + Ui.reset);
        Ui.showMessage(Ui.blue + " - 'recurring': Adds a recurring expense into the expense list" + Ui.reset);
        Ui.showMessage(Ui.green + " - 'category add': Adds a new category into the category list" + Ui.reset);
        Ui.showMessage(Ui.red + " - 'category del': Deletes a chosen category from category list" + Ui.reset);
        Ui.showMessage(Ui.cyan + " - 'update income': Updates the income of the expense entry" + Ui.reset);
        Ui.showMessage(Ui.cyan + " - 'update savings goal': Updates the monthly savings goal" + Ui.reset);
        Ui.showMessage(Ui.red + " - 'exit': Exits the program" + Ui.reset);
        Ui.printBorder();
    }
}
