package seedu.fintrack;

import seedu.fintrack.commands.AddCategoryCommand;
import seedu.fintrack.commands.AddExpenseCommand;
import seedu.fintrack.commands.AddRecurringExpenseCommand;
import seedu.fintrack.commands.DeleteCategoryCommand;
import seedu.fintrack.commands.DeleteExpenseCommand;
import seedu.fintrack.commands.ExitCommand;
import seedu.fintrack.commands.HelpCommand;
import seedu.fintrack.commands.SetMonthlyBudgetCommand;
import seedu.fintrack.commands.UpdateExpenseCommand;
import seedu.fintrack.commands.UpdateIncomeCommand;
import seedu.fintrack.commands.UpdateSavingsGoalCommand;
import seedu.fintrack.commands.ViewHistoryCommand;
import seedu.fintrack.commands.ViewMonthCommand;
import seedu.fintrack.commands.Command;

import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Ui;

import java.util.HashMap;

public class AllCommands {
    private final ExpenseList expenseList;
    private final Parser parser;
    private final HashMap<String, Command> commands = new HashMap<>();

    public AllCommands(ExpenseList expenseList, Parser parser) {
        assert expenseList != null : "Expense list should not be null";
        assert parser != null : "Parser should not be null";

        this.expenseList = expenseList;
        this.parser = parser;

        // Initialize commands
        commands.put("add", new AddExpenseCommand(expenseList, parser));
        commands.put("viewmonth", new ViewMonthCommand(expenseList));
        commands.put("history", new ViewHistoryCommand(expenseList));
        commands.put("update", new UpdateExpenseCommand(expenseList, parser));
        commands.put("delete", new DeleteExpenseCommand(expenseList, parser));
        commands.put("budget", new SetMonthlyBudgetCommand(expenseList, parser));
        commands.put("recurring", new AddRecurringExpenseCommand(expenseList, parser));
        commands.put("category add", new AddCategoryCommand(parser));
        commands.put("category del", new DeleteCategoryCommand(parser));
        commands.put("update income", new UpdateIncomeCommand(parser));
        commands.put("update savings goal", new UpdateSavingsGoalCommand(parser));
        commands.put("exit", new ExitCommand(expenseList));
        commands.put("help", new HelpCommand());

        assert commands.size() == 13 : "Commands map should contain 13 commands (including help)";
    }

    public void fetchCommand(String commandKey) {
        Command command = commands.get(commandKey);
        if (command != null) {
            try {
                command.execute();
            } catch (FinTrackException e) {
                Ui.showError(e.getMessage());
                Ui.printBorder();
            }
        } else {
            Ui.showError("Invalid command. Please type 'help' to see the available commands and their" +
                    " proper formats.");
            Ui.printBorder();
        }
    }
}
