package seedu.fintrack;

import seedu.fintrack.command.Command;
import seedu.fintrack.command.AddExpenseCommand;
import seedu.fintrack.command.ViewMonthCommand;
import seedu.fintrack.command.ViewHistoryCommand;
import seedu.fintrack.command.UpdateExpenseCommand;
import seedu.fintrack.command.DeleteExpenseCommand;
import seedu.fintrack.command.AddRecurringExpenseCommand;
import seedu.fintrack.command.AddCategoryCommand;
import seedu.fintrack.command.DeleteCategoryCommand;
import seedu.fintrack.command.UpdateIncomeCommand;
import seedu.fintrack.command.UpdateSavingsGoalCommand;
import seedu.fintrack.command.ClearHistoryCommand;
import seedu.fintrack.command.ExitCommand;
import seedu.fintrack.command.HelpCommand;
import seedu.fintrack.command.ViewRecurringExpensesCommand;
import seedu.fintrack.command.DeleteRecurringExpenseCommand;
import seedu.fintrack.command.UpdateRecurringExpenseCommand;
import seedu.fintrack.command.TipsCommand;
import seedu.fintrack.command.ExportCommand;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.utils.Parser;

import java.util.HashMap;

public class AllCommands {
    private final ExpenseList expenseList;
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private final Categories categories;
    // Map of descriptive command keywords to their Command objects
    private final HashMap<String, Command> commands = new HashMap<>();

    public AllCommands(ExpenseList expenseList, Parser parser, Storage storage, Ui ui, Categories categories) {
        assert expenseList != null : "Expense list should not be null";
        assert parser != null : "Parser should not be null";
        assert storage != null : "Storage should not be null";
        assert ui != null : "UI should not be null";
        assert categories != null : "Categories should not be null";

        this.expenseList = expenseList;
        this.parser = parser;
        this.storage = storage;
        this.ui = ui;
        this.categories = categories;

        // Initialize all commands
        commands.put("add", new AddExpenseCommand(parser));
        commands.put("viewmonth", new ViewMonthCommand());
        commands.put("history", new ViewHistoryCommand());
        commands.put("update", new UpdateExpenseCommand(parser));
        commands.put("delete", new DeleteExpenseCommand(parser));
        commands.put("recurring", new AddRecurringExpenseCommand(parser));
        commands.put("category add", new AddCategoryCommand(parser));
        commands.put("category del", new DeleteCategoryCommand(parser));
        commands.put("update income", new UpdateIncomeCommand(parser));
        commands.put("update savings goal", new UpdateSavingsGoalCommand(parser));
        commands.put("clear", new ClearHistoryCommand(parser));
        commands.put("exit", new ExitCommand());
        commands.put("view recurring", new ViewRecurringExpensesCommand());
        commands.put("delete recurring", new DeleteRecurringExpenseCommand(parser));
        commands.put("update recurring", new UpdateRecurringExpenseCommand(parser));
        commands.put("help", new HelpCommand());
        commands.put("tips", new TipsCommand(parser));
        commands.put("export", new ExportCommand());

        assert commands.size() == 17 : "Commands map should contain 18 commands (including help and export)";
    }

    /**
     * Executes the command associated with the given key.
     * If the command is invalid, an error message is shown with instructions.
     *
     * @param commandKey the command keyword entered by the user.
     */
    public void fetchCommand(String commandKey) {
        // Check if the input looks like expense parameters (contains commas)
        if (commandKey.contains(",")) {
            ui.showError("Please type 'add' and press Enter first, then enter the expense details.");
            ui.printBorder();
            return;
        }

        Command command = commands.get(commandKey);
        if (command != null) {
            try {
                command.execute(expenseList, ui, storage, categories);
            } catch (FinTrackException e) {
                ui.showError(e.getMessage());
                ui.printBorder();
            }
        } else {
            ui.showError("Invalid command. Please type 'help' to see the available commands and their proper formats.");
            ui.printBorder();
        }
    }
}
